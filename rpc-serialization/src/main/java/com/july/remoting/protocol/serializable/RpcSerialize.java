package com.july.remoting.protocol.serializable;

/**
 * 定义序列化接口
 * @param <T>
 */
public interface RpcSerialize<T> {

    /**
     * 序列化
     * @param t
     * @param <T>
     * @return
     */
    public <T> byte[] serialize(T t);

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T deSerialize(byte[] data,Class<T> clazz);
}
