package com.july.remoting.transport.handler;

import com.july.remoting.protocol.RpcMessage;
import com.july.remoting.protocol.RpcResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * client handler
 *
 * @Sharable注解 代表当前Handler是一个可以共享的处理器，也就是意味着，服务器注册此Handler后，可以给多个客户端同时使用。
 * 如果不使用注解描述类型，则每次客户端请求时，必须为客户端重新创建一个新的Handler对象（实际开发中，推荐共享一个Handler，节约内存资源）。
 * 如果Handler是一个Sharable的，一定避免定义可写的实例变量，因为这不安全。
 * bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
 * protected void initChannel(SocketChannel ch) throws Exception {
 * ch.pipeline().addLast(new XxxHandler());
 * };
 * });
 */
@ChannelHandler.Sharable
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage) throws Exception {
        if (rpcMessage.isResponseType()) {
            // response
            // 获取当前请求的的future，然后将远程调用返回的response返回给client调用方 同步非阻塞


        } else {
            // request


        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
        // 移除channel
        // 重新加入重试队列。定时重连 每次失败，间隔时间加1，重试10次
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("active");
    }
}
