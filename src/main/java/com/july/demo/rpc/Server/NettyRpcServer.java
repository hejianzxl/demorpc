package com.july.demo.rpc.Server;

import com.july.demo.common.util.NetUtil;
import com.july.demo.registry.Registry;
import com.july.demo.rpc.configuration.Configuation;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.net.InetAddress;

/**
 * netty server
 * @author hejian
 */
public class NettyRpcServer extends AbstractServer {

    private ServerBootstrap serverBootstrap;
    private EventLoopGroup boss;
    private EventLoopGroup worker;

    NettyRpcServer() {
       this(null);
    }

    NettyRpcServer(Configuation configuation) {
        super(configuation);
        init();
    }

    NettyRpcServer(Configuation configuation, Registry registry) {
        super(configuation, registry);
        init();
    }

    private void init() {
        serverBootstrap = new ServerBootstrap();
        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()+1);
        this.start();
    }

    @Override
    public RpcServer createRpcServer() {
        int port = super.getConfiguation().serverInfo().getPort();
        String host = super.getConfiguation().serverInfo().getAddress();

        try {
            serverBootstrap.group(boss, worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0))
                            //字符串解码 和 编码
                            //.addLast(new DeedDecoder(DeedRequest.class))
                            //.addLast(new DeedEncoder(DeedResponse.class))
                            //.addLast(new ServerHandler());
                    .addLast(new IdleStateHandler(10,10,5));
                }
            }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);


            ChannelFuture future = serverBootstrap.bind(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }

        return this;
    }
}
