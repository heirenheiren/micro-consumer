package com.micro.consumer.order.service;

import java.util.List;

import com.micro.consumer.order.model.OrderEntity;

public interface OrderInterface {
	public OrderEntity getOrder(int id);
	public List<OrderEntity> getOrders();
	public void importOrder();
}
