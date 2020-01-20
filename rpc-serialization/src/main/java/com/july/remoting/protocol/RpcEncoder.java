package com.july.remoting.protocol;

import com.july.remoting.protocol.serializable.RpcSerializableFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * 编码器
 */
public class RpcEncoder extends MessageToByteEncoder<RpcMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage, ByteBuf out) throws Exception {

        //byte[] bytes = RpcSerializableFactory.getSerializeClient().serialize(rpcMessage, RpcRequest.class);
        //out.writeInt(bytes.length).writeBytes(bytes);
        try {
            out.writeBytes(rpcMessage.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(rpcMessage);
        }
    }
}
