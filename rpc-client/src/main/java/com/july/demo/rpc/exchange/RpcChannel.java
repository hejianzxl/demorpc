package com.july.demo.rpc.exchange;

import com.sun.org.apache.bcel.internal.generic.RET;
import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * @author hejian
 * 包装channel对象
 */
public class RpcChannel implements Serializable {

    private static final long serialVersionUID = -4663083141863506613L;

    private Channel channel;


    public RpcChannel write(Object msg) {
        channel.writeAndFlush(msg, channel.voidPromise());
        return this;
    }


    public Channel getChannel() {
        return this.channel;
    }


}
