package com.july.remoting.transport.netty;

import com.july.demo.rpc.configuration.Configuation;
import com.july.remoting.demo.registry.Registry;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Collection;


/**
 * RpcServer服务接口
 */
public interface RpcServer {

    /**
     * 启动rpc server
     */
    default public void start() {
        return;
    }

    /**
     * 获取注册中心
     * @return
     */
    public Registry getRegistry();


    /**
     * 获取服务信息
     * @return
     */
    default Configuation.ServerInfo getServerInfo() {
        return  null;
    }

    Collection<Channel> getChannels();

    /**
     * get channel.
     *
     * @param remoteAddress
     * @return channel
     */
    Channel getChannel(InetSocketAddress remoteAddress);
}
