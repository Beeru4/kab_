package com.beerus.kab_admin_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.beerus"})
@EnableDiscoveryClient
public class KabAdminProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(KabAdminProviderApplication.class, args);
	}

}
