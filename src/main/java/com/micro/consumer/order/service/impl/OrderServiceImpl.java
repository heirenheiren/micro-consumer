package com.micro.consumer.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.consumer.order.mapper.OrderMapper;
import com.micro.consumer.order.model.OrderEntity;
import com.micro.consumer.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	public OrderMapper orderMapper;
	
	@CacheResult(cacheKeyMethod = "getOrderId")//不设置cacheKeyMethod则把所有入参默认做key，可以用@CacheKey在入参处代替，@CacheKey也是可以不写入参
	@HystrixCommand(fallbackMethod="getOrderFallback",commandKey = "getOrderCommandKey")
	@Override
	public OrderEntity getOrder(@CacheKey("id") int id) {
		log.info("before");
		OrderEntity orderEntity = orderMapper.getOrder(id);
		if(orderEntity == null) {
			throw new RuntimeException("订单信息不存在.");
		}
		log.info(orderEntity.toString());
		return orderEntity;
	}
	
	/**
     * 使用注解清除缓存，@CacheKey除了可以指定方法参数为缓存key之外，还可以指定对象中的属性作为缓存Key,对象入参查询的时候可以用到
     * @CacheRemove 必须指定commandKey才能进行清除指定缓存
     */
    @CacheRemove(commandKey = "getOrderCommandKey", cacheKeyMethod = "getOrderId")
    @HystrixCommand
	@Override
	public void updateOrder(@CacheKey("id") OrderEntity entity) {
		// TODO Auto-generated method stub
    	//flushCacheByAnnotation(entity.getId());
    	orderMapper.updateOrder(entity);
    	log.info(entity.toString());
	}
	
    /**
     * 使用注解清除缓存
     * @CacheRemove 必须指定commandKey才能进行清除指定缓存
     */
    @CacheRemove(commandKey = "getOrderCommandKey", cacheKeyMethod = "getOrderId")
    @HystrixCommand
    public void flushCacheByAnnotation(int id){
        log.info("请求缓存已清空！");
        //这个@CacheRemove注解直接用在更新方法上效果更好
    }
    
	/**
	 * 
	 * @param id
	 * @return   必须是String类型
	 */
	public String getOrderId(int id) {
		log.info("getOrder cacheKeyMethod id:{}",id);
		return String.valueOf(id);
	}
	
	/**
	 * 在update方法的入参是OrderEntity对象，需要重写getOrderId方法保持入参一致
	 * @param entity
	 * @return
	 */
	public String getOrderId(OrderEntity entity) {
		log.info("updateOrder cacheKeyMethod entity:{}",entity.toString());
		return String.valueOf(entity.getId());
	}
	
	public OrderEntity getOrderFallback(int id, Throwable e) {// 此时方法的参数和返回值与get()一致
		log.info("getFallback id:{}",id);
		return null;
	}
	
	@Transactional
	@Override
	public OrderEntity insertOrder(OrderEntity entity) {
		// TODO Auto-generated method stub
		orderMapper.insertOrder(entity);
		return entity;
	}
	
	@Override
	public List<OrderEntity> getOrders() {
		log.debug("");
		return orderMapper.getOrders();
	}
	
}