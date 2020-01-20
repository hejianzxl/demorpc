package com.july.remoting.transport.handler;

import com.july.remoting.protocol.RpcMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcSeverHandler extends SimpleChannelInboundHandler<RpcMessage> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcMessage msg) throws Exception {
        System.out.println(msg);
        byte[] body = msg.getBody();

        // do invoke romoting method
        // writer response
        ctx.write(msg);
    }
}
