package com.bjsxt.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableCircuitBreaker
@EnableCaching
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class HystrixApplicationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixApplicationClientApplication.class, args);
	}
	
}
