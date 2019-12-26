package com.july.demo.rpc.spring.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 初始化bean
 */
@Configuration
public class RpcAutoConfiguration {


    @Bean
    public RpcConfigurationRegistrar rpcConfigurationRegistrar() {
        return new RpcConfigurationRegistrar();
    }
}
