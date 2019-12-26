package com.july.demo.rpc.handler;

import java.nio.channels.Channel;
import java.util.List;

public interface Handler {

    default public List<Channel> getChannel() {
        return null;
    }



}
