package com.bjsxt.stream.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * 使用spring-cloud-stream开发消息的提供者，不需要提供接口定义的实现。
 * Spring Cloud Stream会提供一个接口的动态代理对象。
 * 后续代码中对消息的发送处理是通过SubscribableChannel信道对象实现的。
 */
public interface IFirstMessageSender {

	/**
	 * @Output - 绑定RabbitMQ中的Exchange，属性value为Exchange的命名。
	 *  Exchange的种类由spring-cloud-stream管理，默认使用topic。
	 *  默认使用的Exchange是订阅发布类型的，发送到RabbitMQ中的消息会让所有对应的消息消费者处理。
	 * @return SubscribableChannel 信道对象。用于实现数据输出的具体对象。
	 */
	@Output("outputName")
	SubscribableChannel sendMessage();
	
}
