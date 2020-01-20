package com.july.remoting.protocol;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class RpcUrl implements Serializable {

    private static final String PROTOCOL_HEADER = "rpc://";

    private static final long serialVersionUID = 4604792141835801459L;

    private String ip;

    private int port;

    private List<Method> methods;

    private Class serviceClass;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public Class getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public String toString() {
        return PROTOCOL_HEADER + ip + ":" + port + "/" + serviceClass.getName() + "/" + methods.stream().map(Method::getName).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
    }
}
