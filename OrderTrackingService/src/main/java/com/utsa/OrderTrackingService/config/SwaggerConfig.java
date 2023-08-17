package com.utsa.OrderTrackingService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket swagConfig(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.utsa"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Order trcking application",
                "Documentation for order tracking application",
                "1.0",
                "Terms of service for using order tracking application",
                new Contact("Utsa Ahmed","https://www.youtube.com/channel/UCaH2MTg94hrJZTolW01a3ZA","utsaahmed@gmail.com"),
                "MIT Licence",
                "https://opensource.org/licenses/MIT",
                new ArrayList<>()
        );
    }
}

