package com.bjsxt.stream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {

	@RequestMapping("/")
	public String toIndex(){
		return "index";
	}

}
