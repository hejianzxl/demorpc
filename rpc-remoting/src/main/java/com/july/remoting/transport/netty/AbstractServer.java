package com.july.remoting.transport.netty;

import com.july.demo.rpc.configuration.Configuation;
import com.july.remoting.demo.registry.Registry;
import com.july.remoting.transport.support.ChannelHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.annotation.CreatedDate;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * 抽象RPC server
 */
public abstract class AbstractServer implements RpcServer, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(AbstractServer.class);
    // channel缓存
    private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>(); // <ip:port, channel>

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
        if(logger.isInfoEnabled()) {
            logger.info("Rpc Server {} is start success", rpcServer.getServerInfo().getAddress() + ":" + rpcServer.getServerInfo().getPort());
        }
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

    @Override
    public Collection<Channel> getChannels() {
        return null;
    }

    @Override
    public Channel getChannel(InetSocketAddress remoteAddress) {
        return null;
    }

    /**
     * 获取当前RPC服务状态
     * @return
     */
    public boolean isRun() {
        return this.status.get();
    }
}
