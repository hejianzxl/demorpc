package com.july.remoting.transport.netty;

import com.july.demo.rpc.configuration.Configuation;
import com.july.remoting.protocol.RpcDecoder;
import com.july.remoting.protocol.RpcEncoder;
import com.july.remoting.transport.handler.RpcClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

public class NettyRpcClient extends AbstractServer {

    EventLoopGroup worker;
    Channel channel;
    @Override
    public Configuation.ServerInfo getServerInfo() {
        return null;
    }

    public NettyRpcClient() {
        this(null);
    }

    public NettyRpcClient(Configuation configuation) {
        super(configuation);
        worker =  worker = new NioEventLoopGroup(1);
    }

    @Override
    public RpcServer createRpcServer() {
        start();
        return this;
    }

    public void start(final InetSocketAddress socketAddress){
        Bootstrap bootstrap = new Bootstrap();
        final CountDownLatch clientWatch = new CountDownLatch(1);
        bootstrap.group(worker).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline cp = ch.pipeline();
                cp.addLast(new RpcDecoder());
                cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
                cp.addLast(new RpcEncoder());
                cp.addLast(new RpcClientHandler());
            }
        });
        ChannelFuture channelFuture = bootstrap.connect(socketAddress);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println(("Successfully connect to remote server. remote peer = " + socketAddress));
                    RpcClientHandler handler = channelFuture.channel().pipeline().get(RpcClientHandler.class);
                    //connectedHandlers.add(handler);
                    clientWatch.countDown();
                    channel = channelFuture.channel();
                }
            }
        });
        try {
            clientWatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
