package com.bjsxt.eureka.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.api.pojo.FeignTestPOJO;
import com.bjsxt.api.service.FirstFeignService;

@RestController
public class TestHttpClientFeignAppServiceController implements FirstFeignService {

	@Override
	public List<String> testFeign() {
		
		List<String> result = new ArrayList<>();
		
		result.add("test feign");
		result.add("this is first spring cloud with feign");
		
		return result;
	}

	@Override
	public FeignTestPOJO getById(Long id) {
		try {
			// 假设是访问其他服务，或访问数据库。需要一定的时长。
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new FeignTestPOJO(id, "getById");
	}
	
	@Override
	public FeignTestPOJO getByIdWithPOST(@RequestBody Long id) {
		return new FeignTestPOJO(id, "getByIdWithPOST");
	}

	@Override
	public FeignTestPOJO add(Long id, String name) {
		return new FeignTestPOJO(id, name);
	}
	
	@Override
	public FeignTestPOJO add(@RequestBody FeignTestPOJO pojo) {
		System.out.println("add method run");
		return pojo;
	}

	@Override
	public FeignTestPOJO addWithPOST(@RequestBody FeignTestPOJO pojo) {
		System.out.println("addWithPOST method run");
		return pojo;
	}
	
}
