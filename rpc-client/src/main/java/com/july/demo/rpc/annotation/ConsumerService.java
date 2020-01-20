package com.july.demo.rpc.annotation;

import java.lang.annotation.*;

/**
 * 服务引用
 * @author hejian
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ConsumerService {

    String name = "";
}
