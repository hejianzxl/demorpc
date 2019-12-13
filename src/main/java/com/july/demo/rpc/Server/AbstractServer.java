package com.july.demo.rpc.Server;

import com.july.demo.registry.Registry;
import com.july.demo.rpc.configuration.Configuation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 抽象RPC server
 */
public abstract class AbstractServer implements RpcServer{

    private static final Logger logger = LoggerFactory.getLogger(AbstractServer.class);

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
}
