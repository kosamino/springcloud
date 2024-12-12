package com.bjsxt.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 需要增加一个新的注解
 * @EnableEurekaServer - 用于开启EurekaServer服务。
 * 
 * 可选启动注解 ： @SpringCloudApplication。 这个注解类似@SpringBootApplication
 * 在当前时间段，不能使用@SpringCloudApplication。会缺少jar包，导致异常，不能启动。
 * 异常为： ClassNotFoundException
 *
 * http://localhost:8761/
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerSingleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerSingleApplication.class, args);
	}
}
