package com.micro.consumer.config;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@Configuration
public class ConfigurationBean {

	@Bean
	public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>(
				streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}

	@Bean
	public MetricRegistry metricRegistry() {
		MetricRegistry registry = new MetricRegistry();
		// 输出到控制台
		ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
				// influxdb的ip,port，用户名,密码,数据库
		        //.protocol(InfluxdbProtocols.http("192.168.200.99",8086,"root","root","my-influxdb"))
		        .convertRatesTo(TimeUnit.MINUTES)
		        .convertDurationsTo(TimeUnit.MILLISECONDS)
		        //.filter(MetricFilter.ALL)
		        //.skipIdleMetrics(false)
		        .build();
		reporter.start(10, TimeUnit.MINUTES);
		return registry;
	}

	@Bean
	public Meter getMeter(MetricRegistry registry) {
		Meter meter = registry.meter("getMeter");
		return meter;
	}

	@Bean
	public Timer getTimer(MetricRegistry registry) {
		Timer timer = registry.timer("getTimer");
		return timer;
	}
	
	@Bean
	public Histogram responseSizes(MetricRegistry metrics) {
	    return metrics.histogram("response-sizes");
	}
	
	@Bean
	public Counter pendingJobs(MetricRegistry metrics) {
	    return metrics.counter("requestCount");
	}
}
