package com.july.remoting.protocol;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 8279867656163247341L;
       //方法名称
    private String method;
    //接口名称
    private  String interfaceName;
    //参数类型
    private Class<?>[]	parameterTypes;
    //参数
    private Object[]	parameters;

    public String getMethod() {
        return method;
    }

    public RpcRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public RpcRequest setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public RpcRequest setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public RpcRequest setParameters(Object[] parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                ", method='" + method + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
