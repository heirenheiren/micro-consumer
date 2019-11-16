package com.micro.consumer.order.service.init;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.micro.consumer.order.mapper.OrderMapper;
import com.micro.consumer.order.model.OrderEntity;
import com.micro.consumer.order.service.SyncService;

import lombok.extern.slf4j.Slf4j;
/**
 *  两种方式在容器启动的时候初始化资源，第一重写Bean的初始化；第二容器初始化的时候；第三使用Bean注解
 * @author Polin
 *
 */
@Slf4j
@Component()
public class OrderServiceInit implements InitializingBean,ApplicationContextAware,ServletContextListener {

	@Autowired
	public OrderMapper orderMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		log.info("afterPropertiesSet");
		OrderEntity orderEntity = orderMapper.getOrder(1);
		log.info(orderEntity.toString());
	}

	public OrderServiceInit() {
		log.info("OrderServiceInit");
	}

	@PostConstruct
	public void postConstruct() {
		log.info("postConstruct");
	}

	public void initMethod() {
		log.info("initMethod");
	}

	private static ApplicationContext applicationContext;
	
	/**
	 * 加载Spring配置文件时，会自动调用ApplicationContextAware中的这个接口
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		log.info(applicationContext.toString());
		this.applicationContext = applicationContext;
	}

	/**
	 * Servlet容器启动,serveltContextListener就会调用contextInitialized方法,ServeltContext是一个上下文对象,
	 * 他的数据供所有的应用程序共享,进行一些业务的初始化.servelt容器关闭,serveltContextListener就会调用contextDestroyed.
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application=sce.getServletContext();
		SyncService syncService = (SyncService)applicationContext.getBean("syncServiceImpl");
		syncService.importOrder();
		application.setAttribute("key", "value");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
