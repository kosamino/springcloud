package com.bjsxt.hystrix.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;

@CacheConfig(cacheNames={"test.hystrix.cache"})
@Service
public class HystrixService {

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Value("${app.zuul.serviceId}")
	private String zuulServiceId;
	@Value("${app.eureka-application-service.serviceId}")
	private String remoteServiceId;

	// @HystrixCommand(fallbackMethod="downgradeFallback")
	public List<Map<String, Object>> testDowngrade() {
		ServiceInstance si = 
				this.loadBalancerClient.choose(zuulServiceId);
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(si.getHost())
			.append(":").append(si.getPort()).append("/"+remoteServiceId+"/test");
		System.out.println("request application service URL : " + sb.toString());
		RestTemplate rt = new RestTemplate();
		ParameterizedTypeReference<List<Map<String, Object>>> type = 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
		};
		ResponseEntity<List<Map<String, Object>>> response = 
				rt.exchange(sb.toString(), HttpMethod.GET, null, type);
		List<Map<String, Object>> result = response.getBody();
		return result;
	}
	
	private List<Map<String, Object>> downgradeFallback(){
		List<Map<String, Object>> result = new ArrayList<>();
		
		Map<String, Object> data = new HashMap<>();
		data.put("id", -1);
		data.put("name", "downgrade fallback datas");
		data.put("age", 0);
		
		result.add(data);
		
		return result;
	}
	
	@Cacheable("testCache4Get")
	public List<Map<String, Object>> testCache4Get() {
		ServiceInstance si = 
				this.loadBalancerClient.choose(zuulServiceId);
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(si.getHost())
			.append(":").append(si.getPort()).append("/"+remoteServiceId+"/test");
		System.out.println("request application service URL : " + sb.toString());
		RestTemplate rt = new RestTemplate();
		ParameterizedTypeReference<List<Map<String, Object>>> type = 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
		};
		ResponseEntity<List<Map<String, Object>>> response = 
				rt.exchange(sb.toString(), HttpMethod.GET, null, type);
		List<Map<String, Object>> result = response.getBody();
		return result;
	}
	
	@CacheEvict("testCache4Get")
	public List<Map<String, Object>> testCache4Del() {
		ServiceInstance si = 
				this.loadBalancerClient.choose(zuulServiceId);
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(si.getHost())
			.append(":").append(si.getPort()).append("/"+remoteServiceId+"/test");
		System.out.println("request application service URL : " + sb.toString());
		RestTemplate rt = new RestTemplate();
		ParameterizedTypeReference<List<Map<String, Object>>> type = 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
		};
		ResponseEntity<List<Map<String, Object>>> response = 
				rt.exchange(sb.toString(), HttpMethod.GET, null, type);
		List<Map<String, Object>> result = response.getBody();
		return result;
	}
	
	@HystrixCollapser(batchMethod = "mergeRequest", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,  
    		collapserProperties = {  
    		// 请求时间间隔在20ms之内的请求会被合并为一个请求,默认为10ms
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "20"),
            // 设置触发批处理执行之前，在批处理中允许的最大请求数。
            @HystrixProperty(name = "maxRequestsInBatch", value = "200"),  
    })  
	public Future<Map<String, Object>> testMergeRequest(Long id){
		return null;
	}
	
	@HystrixCommand
	public List<Map<String, Object>> mergeRequest(List<Long> ids){
		ServiceInstance si = 
				this.loadBalancerClient.choose(zuulServiceId);
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(si.getHost())
			.append(":").append(si.getPort()).append("/"+remoteServiceId+"/testMerge?");
		for(int i = 0; i < ids.size(); i++){
			Long id = ids.get(i);
			if(i != 0){
				sb.append("&");
			}
			sb.append("ids=").append(id);
		}
		System.out.println("request application service URL : " + sb.toString());
		RestTemplate rt = new RestTemplate();
		ParameterizedTypeReference<List<Map<String, Object>>> type = 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
		};
		ResponseEntity<List<Map<String, Object>>> response = 
				rt.exchange(sb.toString(), HttpMethod.GET, null, type);
		List<Map<String, Object>> result = response.getBody();
		return result;
	}
	
	@HystrixCommand(fallbackMethod = "breakerFallback",
			commandProperties = {
			  // 默认20个;10s内请求数大于20个时就启动熔断器，当请求符合熔断条件时将触发getFallback()。
		      @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, 
		    		  	value="10"),
		      // 请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件时将触发getFallback()。
		      @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, 
		      			value="50"),
		      // 默认5秒;熔断多少秒后去尝试请求
		      @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, 
		      			value="5000")}
	)
	public List<Map<String, Object>> testBreaker() {
		System.out.println("testBreaker method thread name : " + Thread.currentThread().getName());
		ServiceInstance si = 
				this.loadBalancerClient.choose(zuulServiceId);
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(si.getHost())
			.append(":").append(si.getPort()).append("/"+remoteServiceId+"/test");
		System.out.println("request application service URL : " + sb.toString());
		RestTemplate rt = new RestTemplate();
		ParameterizedTypeReference<List<Map<String, Object>>> type = 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
		};
		ResponseEntity<List<Map<String, Object>>> response = 
				rt.exchange(sb.toString(), HttpMethod.GET, null, type);
		List<Map<String, Object>> result = response.getBody();
		return result;
	}
	
	private List<Map<String, Object>> breakerFallback(){
		System.out.println("breakerFallback method thread name : " + Thread.currentThread().getName());
		List<Map<String, Object>> result = new ArrayList<>();
		
		Map<String, Object> data = new HashMap<>();
		data.put("id", -1);
		data.put("name", "breaker fallback datas");
		data.put("age", 0);
		
		result.add(data);
		
		return result;
	}
	
	@HystrixCommand(groupKey="test-thread-quarantine", 
		commandKey = "testThreadQuarantine",
	    threadPoolKey="test-thread-quarantine", 
		threadPoolProperties = {
	        @HystrixProperty(name="coreSize", value="30"),
	        @HystrixProperty(name="maxQueueSize", value="100"),
	        @HystrixProperty(name="keepAliveTimeMinutes", value="2"),
	        @HystrixProperty(name="queueSizeRejectionThreshold", value="15")
	    },
		fallbackMethod = "threadQuarantineFallback")
	public List<Map<String, Object>> testThreadQuarantine() {
		System.out.println("testQuarantine method thread name : " + Thread.currentThread().getName());
		ServiceInstance si = 
				this.loadBalancerClient.choose(zuulServiceId);
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(si.getHost())
			.append(":").append(si.getPort()).append("/"+remoteServiceId+"/test");
		System.out.println("request application service URL : " + sb.toString());
		RestTemplate rt = new RestTemplate();
		ParameterizedTypeReference<List<Map<String, Object>>> type = 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
		};
		ResponseEntity<List<Map<String, Object>>> response = 
				rt.exchange(sb.toString(), HttpMethod.GET, null, type);
		List<Map<String, Object>> result = response.getBody();
		return result;
	}
	
	private List<Map<String, Object>> threadQuarantineFallback(){
		System.out.println("threadQuarantineFallback method thread name : " + Thread.currentThread().getName());
		List<Map<String, Object>> result = new ArrayList<>();
		
		Map<String, Object> data = new HashMap<>();
		data.put("id", -1);
		data.put("name", "thread quarantine fallback datas");
		data.put("age", 0);
		
		result.add(data);
		
		return result;
	}
	
	@HystrixCommand(fallbackMethod="semaphoreQuarantineFallback",
			commandProperties={
		      @HystrixProperty(
		    		  name=HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, 
		    		  value="SEMAPHORE"), // 信号量隔离
		      @HystrixProperty(
		    		  name=HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, 
		    		  value="100") // 信号量最大并发数
	})
	public List<Map<String, Object>> testSemaphoreQuarantine() {
		System.out.println("testQuarantine method thread name : " + Thread.currentThread().getName());
		ServiceInstance si = 
				this.loadBalancerClient.choose(zuulServiceId);
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(si.getHost())
			.append(":").append(si.getPort()).append("/"+remoteServiceId+"/test");
		System.out.println("request application service URL : " + sb.toString());
		RestTemplate rt = new RestTemplate();
		ParameterizedTypeReference<List<Map<String, Object>>> type = 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
		};
		ResponseEntity<List<Map<String, Object>>> response = 
				rt.exchange(sb.toString(), HttpMethod.GET, null, type);
		List<Map<String, Object>> result = response.getBody();
		return result;
	}
	
	private List<Map<String, Object>> semaphoreQuarantineFallback(){
		System.out.println("threadQuarantineFallback method thread name : " + Thread.currentThread().getName());
		List<Map<String, Object>> result = new ArrayList<>();
		
		Map<String, Object> data = new HashMap<>();
		data.put("id", -1);
		data.put("name", "thread quarantine fallback datas");
		data.put("age", 0);
		
		result.add(data);
		
		return result;
	}

}
