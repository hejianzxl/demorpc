package com.july.demo.rpc.support;

import com.july.remoting.protocol.RpcMessage;
import com.july.remoting.protocol.serializable.RpcSerializableFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokerImpl implements Invoker {

    @Override
    public RpcMessage doInvoke(Method method, Object target, Object[] args) {
        RpcMessage rpcMessage = new RpcMessage();
        try {
            Object result = method.invoke(target, args);
            rpcMessage.setBody(RpcSerializableFactory.getSerializeClient().serialize(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rpcMessage;
    }
}
