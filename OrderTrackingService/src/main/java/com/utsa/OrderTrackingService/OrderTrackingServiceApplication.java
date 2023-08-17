package com.utsa.OrderTrackingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@EnableCaching
@SpringBootApplication
public class OrderTrackingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderTrackingServiceApplication.class, args);
	}

}
