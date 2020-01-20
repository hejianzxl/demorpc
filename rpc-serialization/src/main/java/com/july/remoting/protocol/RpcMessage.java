package com.july.remoting.protocol;

import java.io.Serializable;

public class RpcMessage implements Serializable {

    private static final long serialVersionUID = 440376430205634636L;

    private int flag = 0;
    private static final int RPC_TYPE = 0;
    private byte[] body;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public long getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(long invokeId) {
        this.invokeId = invokeId;
    }

    private long invokeId;

    public boolean isResponseType() {
        int bits = 1 << RPC_TYPE;
        return (this.flag & bits) == bits;
    }
}