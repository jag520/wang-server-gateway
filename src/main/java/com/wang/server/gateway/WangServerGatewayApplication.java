package com.wang.server.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.wang.server.gateway.config.ConfigServerDataConfiguration;

@AutoConfigureBefore(ConfigServerDataConfiguration.class)
@EnableScheduling
@ComponentScan
@SpringBootApplication
@EnableDiscoveryClient
public class WangServerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WangServerGatewayApplication.class, args);
    }
}
