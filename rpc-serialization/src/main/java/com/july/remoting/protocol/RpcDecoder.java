package com.july.remoting.protocol;

import com.july.remoting.protocol.serializable.RpcSerializableFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 *   ********************************************************************************************************
 *                                            Protocol
 *   ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
 *          2   │   1   │    1   │     8     │      4      │
 *   ├ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┤
 *              │       │        │           │             │
 *   │  MAGIC     Sign    Status   Invoke Id   Body Length                   Body Content            │
 *              │       │        │           │             │
 *   └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┘
 *
 *   消息头16个字节定长
 *   = 2 // MAGIC = (short) 0xbabe
 *   + 1 // 消息标志位, 用来表示消息类型Request/Response/Heartbeat
 *   + 1 // 状态位, 设置请求响应状态
 *   + 8 // 消息 id, long 类型
 *   + 4 // 消息体 body 长度, int类型
 *
 *   Sign: -128 ~ 127的范围(byte)有点浪费, 可以将高地址2~4位作为序列化标识, 暂时未支持
 *
 *
 *
 */
public class RpcDecoder extends ByteToMessageDecoder implements Serializable {

    private static final long serialVersionUID = 3898609544546448632L;
    //private static final int MAX_BODY_SIZE = SystemPropertyUtil.getInt("jupiter.decoder.max.body.size", 1024 * 1024 * 5);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        // 私有协议check
       /* if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();*/
        // 读取body大小
        int dataLength = in.readInt();

        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];

        in.readBytes(data);
        // 序列化目标对象
        RpcMessage message = RpcSerializableFactory.getSerializeClient().deSerialize(data, RpcMessage.class);

        out.add(message);
    }
}
