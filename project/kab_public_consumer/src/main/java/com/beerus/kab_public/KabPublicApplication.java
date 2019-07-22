package com.beerus.kab_public;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
public class KabPublicApplication {

    public static void main(String[] args) {
        SpringApplication.run(KabPublicApplication.class, args);
    }

}
