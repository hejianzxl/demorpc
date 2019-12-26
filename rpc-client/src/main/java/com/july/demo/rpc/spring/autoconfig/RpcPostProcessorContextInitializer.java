package com.july.demo.rpc.spring.autoconfig;

import com.july.demo.rpc.proxy.ProxyFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class RpcPostProcessorContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        configurableApplicationContext.addBeanFactoryPostProcessor(new RpcRegistryPostProcessor());
        // TODO
        configurableApplicationContext.getBeanFactory().registerSingleton("proxyFactory", new ProxyFactory() {
        });

    }
}
