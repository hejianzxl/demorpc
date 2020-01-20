package com.july.remoting.transport.handler;

import com.july.demo.rpc.exchange.RpcChannel;
import com.july.remoting.transport.netty.NettyRpcServer;
import com.july.remoting.transport.support.ChannelHandler;
import com.july.remoting.transport.support.NettyChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcChannelHandler extends SimpleChannelInboundHandler {

    private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>(); // <ip:port, channel>

    private ChannelHandler channelHandler;

    public RpcChannelHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    public Map<String, Channel> getChannels() {
        return channels;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
       /* NettyChannel channel = NettyChannel.getOrAddChannel(ctx.getChannel(), url, handler);
        try {
            channelHandlerContext.caught(channel, e.getCause());
        } finally {
            NettyChannel.removeChannelIfDisconnected(channelHandlerContext.channel());
        }*/
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        NettyChannel nettyChannel = new NettyChannel(ctx.channel());
        if (nettyChannel != null) {
            channels.put(ctx.channel().remoteAddress().toString(), ctx.channel());
        }
        channelHandler.connected(nettyChannel);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println(o.getClass().getName());
    }
}
