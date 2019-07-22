package com.beerus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KabEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KabEurekaApplication.class, args);
    }

}
