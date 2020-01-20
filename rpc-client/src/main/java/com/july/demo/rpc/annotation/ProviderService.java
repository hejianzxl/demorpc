package com.july.demo.rpc.annotation;

import java.lang.annotation.*;

/**
 * 服务暴露
 * @author hejian
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ProviderService {

    String name = "";
}
