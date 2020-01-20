package com.july.remoting.transport.handler;

import com.july.remoting.transport.support.ChannelHandler;
import com.july.remoting.transport.support.DefaultChannelHandler;
import com.july.remoting.transport.support.NettyChannel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * rpc connect manager
 * 缓存channel对象
 */
@io.netty.channel.ChannelHandler.Sharable
public class RpcConnectManageHandler extends ChannelDuplexHandler {

    // channel包装类
    private ChannelHandler channelHandler;

    public RpcConnectManageHandler() {
        channelHandler = new DefaultChannelHandler();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        channelHandler.connected(new NettyChannel(ctx.channel()));
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
       channelHandler.disconnected(new NettyChannel(ctx.channel()));
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        channelHandler.caught(new NettyChannel(ctx.channel()), cause);
        super.exceptionCaught(ctx, cause);
    }
}
