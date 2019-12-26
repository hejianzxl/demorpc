package com.july.demo.rpc.proxy;

/**
 * 定义代理工厂接口
 * @author hejian
 * @param <T>
 */
public interface ProxyFactory<T> {

    default public T getProxy(Class interfaceClassName) {
        return  null;
    }
}
