package com.micro.consumer.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.consumer.order.mapper.OrderMapper;
import com.micro.consumer.order.model.OrderEntity;
import com.micro.consumer.order.service.OrderInterface;
import com.micro.consumer.order.thread.ThreadTasks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderInterface{

	@Autowired
	public OrderMapper orderMapper;
	
	@Override
	public OrderEntity getOrder(int id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrder(id);
	}
	
	@Override
	public List<OrderEntity> getOrders() {
		log.debug("");
		return orderMapper.getOrders();
	}
	
	@Autowired
    private ThreadTasks threadTasks;

	@Override
	public void importOrder() {
		log.info("start");
        for (int i = 0; i < 10; i++) {
        	threadTasks.startThreadTasks();
        }
        
		log.info("end");
	}

}
