package com.micro.consumer.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.micro.consumer.order.model.OrderEntity;
@Repository
@Mapper
public interface OrderMapper {
	public List<OrderEntity> getOrders();
	public OrderEntity getOrder(int id);
}
