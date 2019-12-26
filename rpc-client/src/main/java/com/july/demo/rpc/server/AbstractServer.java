package com.july.demo.rpc.server;

import com.july.demo.rpc.configuration.Configuation;
import com.july.demo.transport.demo.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 抽象RPC server
 */
public abstract class AbstractServer implements RpcServer, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(AbstractServer.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        this.start();
    }

    private AtomicBoolean status = new AtomicBoolean(false);

    private Configuation configuation;

    private Registry registry;

    AbstractServer() {
    }

    AbstractServer(final Configuation configuation) {
        this.configuation = configuation;
    }

    AbstractServer(final Configuation configuation, Registry registry) {
        this.configuation = configuation;
        this.registry = registry;
    }

    public void start() {
        if(status.get()) {
            logger.warn("rpc server status is run");
            return;
        }
        status.compareAndSet(false, true);
        RpcServer rpcServer = this.createRpcServer();
        if(logger.isInfoEnabled())
            logger.info("Rpc Server {} is start success", rpcServer.getServerInfo().getAddress() + ":" + rpcServer.getServerInfo().getPort());
    }

    protected Configuation getConfiguation() {
        return  configuation;
    }

    public abstract RpcServer createRpcServer();

    /**
     * 获取注册中心
     * @return
     */
    public Registry getRegistry() {
        return this.registry;
    }

    /**
     * 获取当前RPC服务状态
     * @return
     */
    public boolean isRun() {
        return this.status.get();
    }
}
