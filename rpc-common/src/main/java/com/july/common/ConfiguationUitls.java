package com.july.common;

/**
 * RPC配置帮助类
 */
public class ConfiguationUitls {


    public static int getPort(int port) {
        if(port <= 0) {
            return 54999;
        }
        return port;
    }

    public static String getHost(String host) {
        if(null == host || "".equals(host)) {
            return NetUtil.getLocalAddress();
        }
        return host;
    }
}
