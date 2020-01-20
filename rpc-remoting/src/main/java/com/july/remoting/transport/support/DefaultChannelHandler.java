package com.july.remoting.transport.support;

import com.july.remoting.exception.RemotingException;
import com.july.remoting.transport.netty.NettyRpcClient;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class DefaultChannelHandler implements ChannelHandler{

    // channel
    protected final ConcurrentHashMap<String,Channel> channels = new ConcurrentHashMap<String, Channel>(64);
    // 重试channel
    protected final  ConcurrentHashMap<String,Channel> retryChannels = new ConcurrentHashMap<String, Channel>(64);


    @PostConstruct
    public void init() {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(()-> {
           if(!retryChannels.isEmpty()) {
               retryChannels.forEach((k,v)->{
                   if(v.isConnected()) {

                   }
               });
           }
        },1,1, TimeUnit.SECONDS);
    }

    private void restart() {
        new NettyRpcClient();
    }

    @Override
    public void connected(Channel channel) throws RemotingException {
        channels.putIfAbsent(channel.getRemoteAddress().getHostName(), channel);
    }

    @Override
    public void disconnected(Channel channel) throws RemotingException {
        channels.remove(channel.getRemoteAddress().getHostName());
        retryChannels.putIfAbsent(channel.getRemoteAddress().getHostName(), channel);
    }

    @Override
    public void sent(Channel channel, Object message) throws RemotingException {

    }

    @Override
    public void received(Channel channel, Object message) throws RemotingException {

    }

    @Override
    public void caught(Channel channel, Throwable exception) throws RemotingException {
        channels.remove(channel.getRemoteAddress().getHostName());
        retryChannels.putIfAbsent(channel.getRemoteAddress().getHostName(), channel);
    }
}
