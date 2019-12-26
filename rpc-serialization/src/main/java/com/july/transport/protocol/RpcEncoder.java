package com.july.transport.protocol;

import com.july.transport.protocol.serializable.RpcSerializableFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 */
public class RpcEncoder extends MessageToByteEncoder<RpcRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest, ByteBuf out) throws Exception {

        byte[] bytes = RpcSerializableFactory.getSerializeClient().serialize(rpcRequest, RpcRequest.class);
        out.writeInt(bytes.length).writeBytes(bytes);
    }
}
