package com.july.demo.rpc.configuration;

public abstract class AbstractConfiguation implements Configuation{

    private ServerInfo serverInfo;

    protected static final String THREAD_PREFIX = "rpc-";

    public void setServerInfo(final ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public ServerInfo getServerInfo() {
        return this.serverInfo;
    }

}
