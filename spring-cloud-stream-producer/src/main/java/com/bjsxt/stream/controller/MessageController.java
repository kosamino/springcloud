package com.bjsxt.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.stream.producer.IFirstMessageSender;

/**
 * 消息处理控制器
 */
@Controller
public class MessageController {
	
	/**
	 * Stream开发的接口类型。由Spring容器创建动态代理对象并注入。
	 */
	@Autowired
	private IFirstMessageSender sender ;
	
	/**
	 * 通过IFirstMessageSender发送消息到RabbitMQ。
	 * @param message 要发送的消息内容。
	 * @return
	 */
	@RequestMapping(value="/sendMsg", produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String sendMsg(String message){
		
		// 创建消息对象。Message对象。是由spring提供，与具体的MQ应用无关。
		// withPayload方法参数类型为Object，即可以发送任意类型数据的消息。
		// 要求要传递的消息数据对象必须可序列化。
		Message<String> m = MessageBuilder.withPayload(message).build();
		
		// 通过Stream获取信道
		SubscribableChannel channel = this.sender.sendMessage();
		// 通过信道发送消息。
		channel.send(m);
		
		return "{\"status\":\"OK\"}";
	}
	
}
