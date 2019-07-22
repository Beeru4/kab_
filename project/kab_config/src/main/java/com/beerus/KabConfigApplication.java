package com.beerus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class KabConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(KabConfigApplication.class, args);
    }

}
