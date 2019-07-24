package com.uek.project.crm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Hellocontroller {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String  hello(){
		return "hello crm";
	}
}
