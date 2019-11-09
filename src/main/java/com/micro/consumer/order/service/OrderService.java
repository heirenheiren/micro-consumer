package com.micro.consumer.order.service;

import java.util.List;

import com.micro.consumer.order.model.OrderEntity;

public interface OrderService {
	public OrderEntity getOrder(int id);
	public List<OrderEntity> getOrders();
	public void updateOrder(OrderEntity entity);
	public OrderEntity insertOrder(OrderEntity entity);
}
