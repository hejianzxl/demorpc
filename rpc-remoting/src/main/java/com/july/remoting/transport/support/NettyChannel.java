package com.july.remoting.transport.support;

import io.netty.channel.Channel;
import java.net.InetSocketAddress;

public class NettyChannel extends AbstractChannel {

    public NettyChannel(Channel channel) {
        super(channel);
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return null;
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return null;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return null;
    }

    //private final ChannelHandler handler;


}
