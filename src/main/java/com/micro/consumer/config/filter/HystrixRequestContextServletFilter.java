package com.micro.consumer.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
/**
 * 在启动类加入@ServletComponentScan注解,这个filter才会起效果，controller入口方法就不需要设置HystrixRequestContext.initializeContext()
 * @author Polin
 *
 */
@WebFilter(filterName = "hystrixRequestContextServletFilter", urlPatterns = "/*", asyncSupported = true)
public class HystrixRequestContextServletFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 初始化Hystrix请求上下文
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			// 请求正常通过
			chain.doFilter(request, response);
		} finally {
			// 关闭Hystrix请求上下文
			context.shutdown();
		}
	}

}
