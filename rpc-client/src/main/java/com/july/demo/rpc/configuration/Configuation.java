package com.july.demo.rpc.configuration;

public interface Configuation {

    public ServerInfo serverInfo();

    class ServerInfo {

        private int port;
        private String address;

        public ServerInfo() {}

        public ServerInfo(int port, String address) {
            this.port = port;
            this.address = address;
        }

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
