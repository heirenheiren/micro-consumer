package com.micro.consumer.config.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAsync(proxyTargetClass=true)//利用@EnableAsync注解开启异步任务支持
//@ComponentScan("com.micro.consumer.order.thread") //必须加此注解扫描包
public class TreadPoolConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		// TODO Auto-generated method stub
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池数量，方法: 返回可用处理器的Java虚拟机的数量。
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        //最大线程数量
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors()*5);
        //线程池的队列容量
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors()*2);
        //线程名称的前缀
        executor.setThreadNamePrefix("AsyncExecutor-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行，也就是同步执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(10);
        executor.initialize();
        log.info("Initializing ThreadPoolTaskExecutor.");
		return executor;
	}
	
	/**
    * 队列满了以后,抛弃任务,但是会抛出 rejectedExecution 如果不处理会中断线程
    * @return
    */
   @Bean
   public Executor abortPolicyExecutor() {
       ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
       executor.setCorePoolSize(1);
       executor.setMaxPoolSize(2);
       executor.setQueueCapacity(20);
       executor.setThreadNamePrefix("AbortPolicyExecutor-");
       executor.setAllowCoreThreadTimeOut(false);
       executor.setRejectedExecutionHandler( new ThreadPoolExecutor.AbortPolicy());
       executor.initialize();
       return executor;
   }
   
   /**
    * 队列满了,直接丢弃当前任务,不抛出异常
    * @return
    */
   @Bean
   public Executor discardPolicyExecutor() {
       ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
       executor.setCorePoolSize(1);
       executor.setMaxPoolSize(2);
       executor.setQueueCapacity(20);
       executor.setThreadNamePrefix("DiscardPolicyExecutor-");
       executor.setAllowCoreThreadTimeOut(false);
       executor.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardPolicy());
       executor.initialize();
       return executor;
   }
   
   /**
    * 队列满了,丢弃最老的任务,不抛出异常
    * @return
    */
   @Bean
   public Executor discardOldestPolicyExecutor() {
       ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
       executor.setCorePoolSize(1);
       executor.setMaxPoolSize(2);
       executor.setQueueCapacity(20);
       executor.setThreadNamePrefix("DiscardOldestPolicyExecutor-");
       executor.setAllowCoreThreadTimeOut(false);
       executor.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardOldestPolicy());
       executor.initialize();
       return executor;
   }

	/*异步任务中异常处理*/
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		log.error("getAsyncUncaughtExceptionHandler");
		return new SimpleAsyncUncaughtExceptionHandler();
	}

}
