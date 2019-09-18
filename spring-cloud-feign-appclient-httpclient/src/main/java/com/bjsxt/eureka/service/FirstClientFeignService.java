package com.bjsxt.eureka.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.bjsxt.api.service.FirstFeignService;

@FeignClient(name="test-feign-application-service-httpclient")
public interface FirstClientFeignService extends FirstFeignService {

}
