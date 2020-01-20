package com.july.remoting.transport.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractChannel implements Channel{

    private static final ConcurrentMap<io.netty.channel.Channel, NettyChannel> channelMap = new ConcurrentHashMap<io.netty.channel.Channel, NettyChannel>();

    private final io.netty.channel.Channel channel;

    private final Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();

    protected AbstractChannel(io.netty.channel.Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("netty channel is null");
        }
        this.channel = channel;
    }

    public boolean isConnected() {
        return channel.isOpen();
    }
}
