package com.july.remoting.transport.netty;

public interface ServerFactory {

    default public RpcServer createRpcServer() {
       return  null;
    }
}
