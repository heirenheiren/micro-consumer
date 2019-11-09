package com.micro.consumer.order.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.consumer.order.model.OrderEntity;
import com.micro.consumer.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {
	
	private Lock lock = new ReentrantLock();
	
	@Autowired
	public OrderService orderServiceImpl;
	
	@GetMapping("/getOrder")
	public OrderEntity getOrder(@RequestParam(value="id", required=true) Integer id) {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		orderServiceImpl.getOrder(id);
		orderServiceImpl.getOrder(id);
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
		orderEntity.setName("wutong");
		orderEntity.setType(2);
		orderEntity.setCode("2222222222222");
		orderServiceImpl.updateOrder(orderEntity);
		orderServiceImpl.getOrder(id);
		orderServiceImpl.getOrder(id);
		context.close();
		return orderEntity;
	}
	
	@PostMapping("/addOrder")
	public void addOrder() {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setName("卧槽，无情"+new Random().nextInt());
		orderEntity.setType(2);
		orderEntity.setCode("333333333333333");
		orderServiceImpl.insertOrder(orderEntity);
		this.getOrder(orderEntity.getId());
		this.getOrder(orderEntity.getId());
		context.close();
	}
	
	/**
	 * 更新-》设置缓存-》查询-》读取缓存
	 */
	@PostMapping("/updateOrder")
	public void updateOrder() {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1);
		orderEntity.setName("卧槽，无情"+new Random().nextInt());
		orderEntity.setType(2);
		orderEntity.setCode("update");
		orderServiceImpl.updateOrder(orderEntity);
		
		this.getOrder(orderEntity.getId());
		this.getOrder(orderEntity.getId());
		context.close();
	}
	
	@GetMapping("/getOrders")
	@HystrixCommand(
			/*
			 * 配置全局唯一标识服务的名称，比如，库存系统有一个获取库存服务，那么就可以为这个服务起一个名字来唯一识别该服务，
			 * 如果不配置，则默认是@HystrixCommand注解修饰的函数的函数名。
			 */
			commandKey = "testCommand", 
			/*
			 * 一个比较重要的注解，配置全局唯一标识服务分组的名称，比如，库存系统就是一个服务分组。
			 * 通过设置分组，Hystrix会根据组来组织和统计命令的告、仪表盘等信息。Hystrix命令默认的线程划分也是根据命令组来实现。
			 * 默认情况下，Hystrix会让相同组名的命令使用同一个线程池，所以我们需要在创建Hystrix命令时为其指定命令组来实现默认的线程池划分。
			 * 此外，Hystrix还提供了通过设置threadPoolKey来对线程池进行设置。建议最好设置该参数，使用threadPoolKey来控制线程池组。
			 */
			groupKey = "testGroup", 
			/*
			 * 对线程池进行设定，细粒度的配置，相当于对单个服务的线程池信息进行设置，也可多个服务设置同一个threadPoolKey构成线程组。
			 */
			threadPoolKey = "testThreadKey",
			//commandProperties：配置该命令的一些参数，如executionIsolationStrategy配置执行隔离策略，默认是使用线程隔离，此处我们配置为THREAD，即线程池隔离。参见：com.netflix.hystrix.HystrixCommandProperties中各个参数的定义。
			//observableExecutionMode：定义hystrix observable command的模式
			//raiseHystrixExceptions：任何不可忽略的异常都包含在HystrixRuntimeException中；
			//defaultFallback：默认的回调函数，该函数的函数体不能有入参，返回值类型与@HystrixCommand修饰的函数体的返回值一致。如果指定了fallbackMethod，则fallbackMethod优先级更高。
			/*
			 * 调用服务时，除了HystrixBadRequestException之外，其他@HystrixCommand修饰的函数抛出的异常均会被Hystrix认为命令执行失败而触发服务降级的处理逻辑
			 * （调用fallbackMethod指定的回调函数），所以当需要在命令执行中抛出不触发降级的异常时来使用它，通过这个参数指定，哪些异常抛出时不触发降级
			 * （不去调用fallbackMethod），而是将异常向上抛出。
			 */
			ignoreExceptions = {NullPointerException.class},
			/*
			 * @HystrixCommand注解修饰的函数的回调函数，@HystrixCommand修饰的函数必须和这个回调函数定义在同一个类中，
			 * 因为定义在了同一个类中，所以fackback method可以是public/private均可。
			 */
			fallbackMethod="getOrdersFallback",
			/*
			 * 线程池相关参数设置，具体可以设置哪些参数请见：com.netflix.hystrix.HystrixThreadPoolProperties
			 */
			threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "300"),
                    @HystrixProperty(name = "maxQueueSize", value = "0"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }) 
	public void getOrders() throws InterruptedException {
		lock.lock();
		List<OrderEntity> orders = orderServiceImpl.getOrders();
		log.info(Thread.currentThread().getName());
		Thread.sleep(100);
		log.info("orders"+orders);
		lock.unlock();
		
//		synchronized (orders) {
//			log.info("orders"+orders);
//		}
	}
	
	public void getOrdersFallback() {    // 此时方法的参数和返回值与get()一致
		log.info("getOrdersFallback");
	}
	
}
