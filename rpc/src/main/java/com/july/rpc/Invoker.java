package com.july.rpc;

import com.july.common.URL;

public interface Invoker<T> {
    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * get service url.
     *
     * @return service url.
     */
    URL getUrl();

    /**
     * is available.
     *
     * @return available.
     */
    boolean isAvailable();

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws RpcException
     */
    //Result invoke(Invocation invocation) throws RpcException;

    /**
     * destroy.
     */
    void destroy();
}
