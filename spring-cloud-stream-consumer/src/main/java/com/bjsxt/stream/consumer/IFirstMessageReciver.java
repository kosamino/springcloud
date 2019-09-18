package com.bjsxt.stream.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 消息的消费端接口。
 * 使用Stream开发消息消费端的时候，不需要考虑具体的MQ应用。
 * 不需要和MQ产品耦合。
 * 所有的代码都和spring-cloud-stream耦合。
 * 
 * 接口不需要定义任何实现。SpringCloudStream会提供一个基于JDK的动态代理实现类的对象。
 */
public interface IFirstMessageReciver {

	/**
	 * @Input - 绑定MQ中的Exchange，属性value为Exchange的命名。
	 *  如果和@Output注解的value属性相同，代表当前消息消费者处理对应消息提供者发送的消息。
	 * @return SubscribableChannel 信道
	 *  就是真实的，和MQ中间件进行数据交互的通道。
	 *  后期所有的针对消息的操作，都是面向Channel的。
	 */
	@Input("inputName")
	SubscribableChannel recive();
	
}
