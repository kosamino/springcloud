package com.bjsxt.eureka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.api.pojo.FeignTestPOJO;
import com.bjsxt.eureka.service.FirstClientFeignService;

@RestController
public class TestHttpClientFeignAppClientController {

	@Autowired
	private FirstClientFeignService service;
	
	@GetMapping("/testFeign")
	public List<String> testFeign(){
		System.out.println(this.service.getClass().getName());
		return this.service.testFeign();
	}
	
	@GetMapping("/get/{id}")
	public FeignTestPOJO getById(@PathVariable("id") Long id){
		return this.service.getById(id);
	}
	
	@GetMapping("/get")
	public FeignTestPOJO getByIdWithPOST(Long id){
		return this.service.getByIdWithPOST(id);
	}
	
	@GetMapping("/add/{id}/{name}")
	public FeignTestPOJO add(@PathVariable("id") Long id, @PathVariable("name") String name){
		return this.service.add(id, name);
	}
	
	@GetMapping("/add")
	public FeignTestPOJO add(FeignTestPOJO pojo){
		return this.service.add(pojo);
	}
	
	@GetMapping("/add2")
	public FeignTestPOJO add2(FeignTestPOJO pojo){
		return this.service.addWithPOST(pojo);
	}
	
}
