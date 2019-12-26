package com.july.demo.rpc.server;

public interface ServerFactory {

    default public RpcServer createRpcServer() {
       return  null;
    }
}
