package com.bjsxt.test;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjsxt.Sender;
import com.bjsxt.SpringbootServerApplication;
import com.bjsxt.message.LogMessage;

/**
 * Direct交换器
 * Producer测试。
 * 注意：
 * 在rabbitmq中，consumer都是listener监听模式消费消息的。
 * 一般来说，在开发的时候，都是先启动consumer，确定有什么exchange、queue、routing-key。
 * 然后再启动producer发送消息。
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootServerApplication.class)
public class QueueTest {

	@Autowired
	private Sender sender;
	
	/*
	 * 测试消息队列
	 */
	@Test
	public void testSend()throws Exception{
		Long id = 1L;
		while(true){
			Thread.sleep(1000);
			this.sender.send(new LogMessage(id,"test log", "error", "订单服务", new Date(), id));
			id++;
		}
	}
}
