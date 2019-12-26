package com.july.demo.rpc.server;

import com.july.demo.rpc.configuration.Configuation;
import com.july.demo.transport.demo.registry.Registry;


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
}
