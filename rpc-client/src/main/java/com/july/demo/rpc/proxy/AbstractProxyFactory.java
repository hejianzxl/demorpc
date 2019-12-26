package com.july.demo.rpc.proxy;

/**
 * 抽象代理工厂类
 * @author hejian
 * @param <T>
 */
public class AbstractProxyFactory<T> implements ProxyFactory<T> {

   private T target;

   private Class interfaceClass;


}

