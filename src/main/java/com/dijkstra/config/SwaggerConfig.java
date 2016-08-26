package com.dijkstra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Class for including Swagger library
 * This library helps in documenting Your Application Rest Services
 * Swagger provides UI that contains description of your Application Rest Service
 * @author mmt3262
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build().apiInfo(apiInfo());                                        
    }
    
    /**
     * Contains your REST API Information
     * @return
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
          "Route Provider Rest API",
          "Provides all the airline routes for requested route.",
          "API TOS",
          "Terms of service",
          "anil.kushwaha@makemytrip.com",
          "License of API",
          "API license URL");
        return apiInfo;
    }	
}