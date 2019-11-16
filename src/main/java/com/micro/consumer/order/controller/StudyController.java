package com.micro.consumer.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.consumer.order.model.OrderEntity;
import com.micro.consumer.order.service.OrderService;
import com.netflix.hystrix.strategy.concurrency.HystrixContextRunnable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("study")
public class StudyController {
	@Autowired
	public OrderService orderServiceImpl;
	
	@GetMapping("/getOrder1")
	public OrderEntity getOrder1(@RequestParam(value="id", required=true) Integer id) {
		OrderEntity e1 = orderServiceImpl.getOrder(id);
		OrderEntity e2 = orderServiceImpl.getOrder(id);
		return null;
	}
	
	@GetMapping("/getOrder2")
	public OrderEntity getOrder2(@RequestParam(value="id", required=true) Integer id) {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		OrderEntity e1 = orderServiceImpl.getOrder(id);
		OrderEntity e2 = orderServiceImpl.getOrder(id);
		context.close();
		return null;
	}
	
	/**
	 * 查询-》设置缓存-》更新-》清理缓存-》再查询-》设置缓存-》再查询-》读取缓存
	 * @param id
	 * @return
	 */
	@GetMapping("/getOrderById/{id}")
	public OrderEntity getOrderById(@PathVariable(value="id", required=true) Integer id) {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		orderServiceImpl.getOrder(id);
		
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(id);
		orderEntity.setName("wwwwww");
		orderEntity.setType(3);
		orderEntity.setCode("666666");
		orderServiceImpl.updateOrder(orderEntity);
		orderServiceImpl.getOrder(id);
		orderServiceImpl.getOrder(id);
		context.close();
		return orderEntity;
	}
	
	@GetMapping("/getOrder3")
	public OrderEntity getOrder3(@RequestParam(value="id", required=true) Integer id) {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		final HystrixRequestVariableDefault<String> variableDefault = new HystrixRequestVariableDefault<>();
		variableDefault.set("444444444");
	    HystrixContextRunnable runnable = new HystrixContextRunnable(() -> {
	    	System.out.println(variableDefault.get());
	    	variableDefault.set("555555");
	    });
	    new Thread(runnable, "sub").start();
	    
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(variableDefault.get());
	    context.close();
		return null;
	}
}
