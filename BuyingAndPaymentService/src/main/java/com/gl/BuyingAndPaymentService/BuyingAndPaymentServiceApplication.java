package com.gl.BuyingAndPaymentService;

import com.gl.BuyingAndPaymentService.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCaching
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "payment-service", configuration = RibbonConfiguration.class)
public class BuyingAndPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyingAndPaymentServiceApplication.class, args);
	}

}
