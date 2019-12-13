package com.july.demo.rpc.configuration;

import com.july.demo.common.util.NetUtil;
import sun.net.util.IPAddressUtil;

public interface Configuation {

    public ServerInfo serverInfo();

    class ServerInfo {

        private int port;
        private String address;

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
