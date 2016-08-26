package com.dijkstra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class for Spring configuration that initializes Spring beans
 *
 */
@Configuration
@ComponentScan(basePackages="com.dijkstra") 
@EnableWebMvc   						 
public class SpringContextConfig extends WebMvcConfigurerAdapter{
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	
	/**
	 * Provides Message converter that converts Java Object to Json String and vice versa
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter getMappingJacksonHttpMessageConverter(){
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(getObjectMapper());
		return converter;
	}
	
	/**
	 * Helps to convert Java Object to JSON String and vice versa
	 * @return
	 */
	@Bean
	public ObjectMapper getObjectMapper(){
		return new ObjectMapper ();
	}
}
