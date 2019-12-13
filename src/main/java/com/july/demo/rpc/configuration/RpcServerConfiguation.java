package com.july.demo.rpc.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

@ConfigurationProperties("rpc")
public class RpcServerConfiguation extends AbstractConfiguation {


    Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1, new ThreadFactory() {
        private final AtomicLong tNum = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,THREAD_PREFIX + tNum.getAndIncrement());
        }
    });

}
