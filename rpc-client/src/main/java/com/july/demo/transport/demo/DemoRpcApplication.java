package com.july.demo.transport.demo;

import com.july.demo.transport.demo.rpc.configuration.RpcServerConfiguation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RpcServerConfiguation.class})
public class DemoRpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRpcApplication.class, args);
	}

}
