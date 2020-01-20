package com.july.demo.rpc.annotation;

import com.july.demo.rpc.spring.autoconfig.RpcAutoConfiguration;
import com.july.demo.rpc.spring.autoconfig.RpcConfigurationRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RpcAutoConfiguration.class, RpcConfigurationRegistrar.class})
public @interface EnableRpc {

    String name = "";
}
