package com.july.demo.rpc.listener;

import com.july.demo.rpc.Server.DefaultServerFactory;
import com.july.demo.rpc.Server.ServerFactory;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationRunListener implements SpringApplicationRunListener {



    @Override
    public void running(ConfigurableApplicationContext context) {
        ServerFactory serverFactory = DefaultServerFactory.getInstances();
        serverFactory.createRpcServer();
    }
}
