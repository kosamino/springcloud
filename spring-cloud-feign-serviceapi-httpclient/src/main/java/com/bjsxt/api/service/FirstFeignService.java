package com.bjsxt.api.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bjsxt.api.pojo.FeignTestPOJO;

public interface FirstFeignService {
	
	@RequestMapping(value="/testFeign", method=RequestMethod.GET)
	public List<String> testFeign();
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public FeignTestPOJO getById(@RequestParam(value="id") Long id);
	
	@RequestMapping(value="/get", method=RequestMethod.POST)
	public FeignTestPOJO getByIdWithPOST(@RequestBody Long id);
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public FeignTestPOJO add(@RequestParam("id") Long id, @RequestParam("name") String name);
	
	@RequestMapping(value="/addWithGET", method=RequestMethod.GET)
	public FeignTestPOJO add(@RequestBody FeignTestPOJO pojo);
	
	@RequestMapping(value="/addWithPOST", method=RequestMethod.POST)
	public FeignTestPOJO addWithPOST(@RequestBody FeignTestPOJO pojo);
	
}
