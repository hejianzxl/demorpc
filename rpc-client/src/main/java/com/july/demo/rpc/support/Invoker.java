package com.july.demo.rpc.support;

import com.july.remoting.protocol.RpcMessage;

import java.lang.reflect.Method;

public interface Invoker {

    public RpcMessage doInvoke(Method method, Object target, Object[] args);
}
