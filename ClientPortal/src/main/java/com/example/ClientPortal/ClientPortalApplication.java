package com.example.ClientPortal;

import com.example.ClientPortal.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@EnableEurekaClient
@RibbonClient(name = "add-service",configuration = RibbonConfiguration.class)
//@RibbonClient(name = "buying-and-payment-service",configuration = RibbonConfiguration.class)
//@RibbonClient(name = "search-service",configuration = RibbonConfiguration.class)
@SpringBootApplication
public class ClientPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientPortalApplication.class, args);
	}

}
