package com.bjsxt.stream.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * 用于处理消息的业务逻辑类型。
 * 当前类型必须被spring容器管理。所以使用@Service|@Componenet等注解描述。
 * 
 * @EnableBinding - 绑定。将使用stream相关内容开发的接口，绑定到当前的类型上。
 *  参数value是Class[]类型的。可以绑定多个接口。
 *  绑定成功后，在类中的用@StreamListener描述的方法上，就可以直接接收并消费消息了。
 */
@Service
@EnableBinding(value={IFirstMessageReciver.class})
public class MessageService {

	/**
	 * @StreamListener - 绑定监听到指定的Exchange。
	 *  注意： 绑定交换器Exchange的时候，必须和Stream开发的相关接口中绑定的@Input|@Output的value属性一致。
	 * @param message 接收到的消息内容。此参数类型必须是可序列化的。
	 *  参数类型任意。常见类型： byte[]，自定义的POJO类型。
	 *  byte[] - 是所有数据的基础类型。
	 *  要求： 如果传递的是自定义的POJO类型，则必须实现序列化接口Serializable。
	 * 
	 * 不推荐处理消息的方法返回数据。
	 */
	@StreamListener("inputName")
	public void onMessage(String message){
		System.out.println("recive message content : " + message);
	}
	
}
