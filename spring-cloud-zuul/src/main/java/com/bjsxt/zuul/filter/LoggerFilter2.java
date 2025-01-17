package com.bjsxt.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class LoggerFilter2 extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(LoggerFilter2.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * return 可以返回任意的对象，当前实现忽略。（spring-cloud-zuul官方解释）
	 * 直接返回null即可。
	 */
	@Override
	public Object run() throws ZuulException {
		// 通过zuul，获取请求上下文
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest request = rc.getRequest();

		logger.info("LogFilter2.....method={},url={}",
				request.getMethod(),request.getRequestURL().toString());
		// throw new RuntimeException("in LoggerFilter2");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
