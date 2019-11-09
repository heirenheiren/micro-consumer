package com.micro.consumer.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.micro.consumer.order.model.OrderEntity;
@Repository
@Mapper
public interface OrderMapper {
	public OrderEntity getOrder(int id);
	public List<OrderEntity> getOrders();
	public void updateOrder(OrderEntity entity);
	public void insertOrder(OrderEntity entity);
}
