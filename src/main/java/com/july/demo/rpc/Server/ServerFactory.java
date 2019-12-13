package com.july.demo.rpc.Server;

public interface ServerFactory {

    default public RpcServer createRpcServer() {
       return  null;
    }
}
