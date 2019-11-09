package com.micro.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Hello world!
 *	http://127.0.0.1:8084/hystrix.stream
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class MicroConsumerApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(MicroConsumerApplication.class, args);
    }
    
}
