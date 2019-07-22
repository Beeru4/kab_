package com.beerus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
public class KabCommentProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(KabCommentProviderApplication.class, args);
	}

}
