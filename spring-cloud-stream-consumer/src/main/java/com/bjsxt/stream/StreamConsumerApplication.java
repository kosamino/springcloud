package com.bjsxt.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.bjsxt.stream.consumer.IFirstMessageReciver;

/**
 * @EnableBinding 用于绑定消息提供者或消息消费者的注解。
 *  用于通知spring cloud为对应的接口提供动态代理对象
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding(value={IFirstMessageReciver.class})
public class StreamConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamConsumerApplication.class, args);
	}
	
}
