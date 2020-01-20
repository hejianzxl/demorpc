package com.july.remoting.transport.support;


import com.july.remoting.exception.RemotingException;

import java.net.InetSocketAddress;

public interface Endpoint {
    /**
     * get url.
     *
     * @return url
     */
//    URL getUrl();

    /**
     * get channel handler.
     *
     * @return channel handler
     */
    ChannelHandler getChannelHandler();

    /**
     * get local address.
     *
     * @return local address.
     */
    InetSocketAddress getLocalAddress();

}
