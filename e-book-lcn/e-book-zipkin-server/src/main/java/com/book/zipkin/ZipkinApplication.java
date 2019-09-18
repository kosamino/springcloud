package com.book.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * @EnableZipkinStreamServer - 开启Zipkin服务端，并通知Zipkin是一个基于Stream的服务端。
 *  Zipkin技术会自动根据全局配置中的RabbitMQ相关配置，到RabbitMQ中消费对应消息。
 *  Zipkin使用的RabbitMQ的Exchange命名为sleuth。
 *  队列命名为sleuth.sleuth。
 */
@EnableZipkinStreamServer
@SpringBootApplication
public class ZipkinApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}
}
