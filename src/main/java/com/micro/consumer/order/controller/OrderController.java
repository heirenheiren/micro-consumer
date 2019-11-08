package com.micro.consumer.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("order")
public class OrderController {
	@GetMapping("/getOrder")
	@HystrixCommand(fallbackMethod="getOrderFallback") 
	public void getOrder() {
		log.info("before");
		throw new RuntimeException("部门信息不存在！") ;
	}
	
	public void getOrderFallback() {    // 此时方法的参数和返回值与get()一致
		log.info("getFallback");
	}
}
