package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YoupingouWebOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoupingouWebOrderApplication.class, args);
    }

}

