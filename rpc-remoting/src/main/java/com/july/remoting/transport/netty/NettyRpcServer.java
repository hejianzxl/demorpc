package com.july.remoting.transport.netty;

import com.july.common.ConfiguationUitls;
import com.july.demo.rpc.configuration.Configuation;
import com.july.remoting.exception.RemotingException;
import com.july.remoting.transport.handler.RpcChannelHandler;
import com.july.remoting.transport.handler.RpcClientHandler;
import com.july.remoting.demo.registry.Registry;
import com.july.remoting.protocol.RpcDecoder;
import com.july.remoting.protocol.RpcEncoder;
import com.july.remoting.protocol.RpcRequest;
import com.july.remoting.transport.handler.RpcConnectManageHandler;
import com.july.remoting.transport.support.Channel;
import com.july.remoting.transport.support.DefaultChannelHandler;
import com.july.remoting.transport.support.NettyChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.Map;

/**
 * netty server
 * @author hejian
 */
public class NettyRpcServer extends AbstractServer {

    private ServerBootstrap serverBootstrap;
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private Map<String, Channel> channels; // <ip:port, channel>
    private RpcChannelHandler rpcChannelHandler = null;
    private RpcConnectManageHandler rpcConnectManageHandler = null;

    public NettyRpcServer() {
       this(new Configuation() {
           @Override
           public ServerInfo serverInfo() {
               return new ServerInfo();
           }
       });
    }

    public NettyRpcServer(Configuation configuation) {
        super(configuation);
        init();
    }

    public NettyRpcServer(Configuation configuation, Registry registry) {
        super(configuation, registry);
        init();
    }

    private void init() {
        serverBootstrap = new ServerBootstrap();
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() + 1);
    }

    @Override
    public RpcServer createRpcServer() {
        int port = ConfiguationUitls.getPort(super.getConfiguation().serverInfo().getPort());
        String host = ConfiguationUitls.getHost(super.getConfiguation().serverInfo().getAddress());
        rpcChannelHandler = new RpcChannelHandler(new DefaultChannelHandler());
        rpcConnectManageHandler = new RpcConnectManageHandler();
        try {
            serverBootstrap.group(boss, worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline()
                            .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0))
                            //字符串解码 和 编码
                            .addLast(new RpcDecoder())
                            .addLast(new RpcEncoder())
                            .addLast(rpcConnectManageHandler)
                            .addLast(rpcChannelHandler)
                    .addLast(new IdleStateHandler(10,10,5));
                }
            }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);

            ChannelFuture future = serverBootstrap.bind(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // shutdownGracefully 是一个安全关闭的方法，可以保证不放弃任何一个已接收的客户端请求
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }

        return this;
    }
}
