package com.cesar31.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaGatewayApplication.class, args);
	}
}
