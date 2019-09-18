package com.bjsxt.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RefreshScope - 刷新当前对象的管理作用域。
 *  保证spring容器管理的bean对象，在刷新的时候，连同作用域数据同步刷新。
 *  当前类型的对象中的属性同步刷新。
 */
@RestController
@RefreshScope
public class TestController {

	@Value("${test.config.db.url}")
	private String url;
	@Value("${test.config.db.driver-class-name}")
	private String driverClassName;
	@Value("${test.config.db.username}")
	private String username;
	@Value("${test.config.db.password}")
	private String password;
	@Value("${a}")
	private String a;
	@Value("${b}")
	private String b;
	
	@GetMapping("/test")
	public String test(){
		System.out.println("url : " + url);
		System.out.println("driverClassName : " + driverClassName);
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		
		return "{\"msg\" : \"OK\"}";
	}
	
}
