package com.bjsxt.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaApplicationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplicationServiceApplication.class, args);
	}
	
}
