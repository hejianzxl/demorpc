package com.july.remoting.protocol.serializable;

public class RpcSerializableFactory {

    private RpcSerializableFactory() {
    }

    public static ProtobufSerialize getSerializeClient() {
        return  ProtobufSerializeHolder.PROTOBUF_SERIALIZE;
    }

    private static class ProtobufSerializeHolder {
        public static final ProtobufSerialize PROTOBUF_SERIALIZE = new ProtobufSerialize();

        private ProtobufSerializeHolder() {
        }
    }

}
