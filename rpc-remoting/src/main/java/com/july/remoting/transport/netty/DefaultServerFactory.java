package com.july.remoting.transport.netty;

/***
 * ServerFactory
 * @author hejian
 */
public class DefaultServerFactory implements ServerFactory {

    @Override
    public RpcServer createRpcServer() {
        return new NettyRpcServer();
    }

    private DefaultServerFactory() {
        throw new IllegalArgumentException("not create bean");
    }

    public static ServerFactory getInstances() {
        return SingletonHolder.serverFactory;
    }

    private static class SingletonHolder {
        private SingletonHolder() {
        }
        private static final ServerFactory serverFactory = new DefaultServerFactory();
    }
}
