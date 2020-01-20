package com.july.remoting;

import com.july.remoting.transport.netty.NettyRpcServer;
import com.july.remoting.transport.netty.RpcServer;
import sun.applet.Main;

public class RpcServertTest {

    public static void main(String[] args) throws Exception{
        RpcServer rpcServer = new NettyRpcServer().createRpcServer();
        System.out.println(rpcServer.getChannels().size());
        System.out.println("1");
        Thread.sleep(1000000L);
    }
}
