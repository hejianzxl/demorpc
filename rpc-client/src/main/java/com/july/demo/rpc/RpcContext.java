package com.july.demo.rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * RPC上下文
 */
public class RpcContext {

    /**
     * 上下文key
     */
    interface ContextKey {
        public static final String REQUEST_ID = "requestId";
        public static final String CLIENT_IP = "ip";
        public static final String EXCEPTION = "exception";
    }

    private static final ThreadLocal<Map<Object, Object>> CONTEXT = new ThreadLocal<Map<Object, Object>>() {
        @Override
        protected Map<Object, Object> initialValue() {
            return new HashMap<Object, Object>();
        }
    };

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public static Object getValue(Object key) {
        if(CONTEXT.get() == null) {
            return null;
        }
        return CONTEXT.get().get(key);
    }

    /**
     * 存储
     * @param key
     * @param value
     * @return
     */
    public static Object setValue(Object key, Object value) {
        Map<Object, Object> cacheMap = CONTEXT.get();
        if(cacheMap == null) {
            cacheMap = new HashMap<Object, Object>();
            CONTEXT.set(cacheMap);
        }
        return cacheMap.put(key, value);
    }

    /**
     * 根据key移除值
     * @param key
     */
    public static void removeValue(Object key) {
        Map<Object, Object> cacheMap = CONTEXT.get();
        if(cacheMap != null) {
            cacheMap.remove(key);
        }
    }

    /**
     * 重置
     */
    public static void reset() {
        if(CONTEXT.get() != null) {
            CONTEXT.get().clear();
        }
    }
}
