package com.dijkstra.config;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dijkstra.pojo.City;
import com.dijkstra.pojo.RegionNodes;
import com.dijkstra.pojo.Regions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	
	@Bean
    public Regions getRegions(){
           String response = new RestTemplate().getForObject("http://10.96.12.10/makemytrip/cityData.htm",String.class  , "");
           Type listType = new TypeToken<ArrayList<City>>(){}.getType();
           List<City> airportList = new Gson().fromJson(response, listType);
           Regions regions = new Regions();
           regions.setCities(airportList);
           return regions;
    }
	
	@Bean
    public RegionNodes getRegionNodes(){
           return new RegionNodes();
    }
	
	/**
	 * Method for initializing Swagger UI for your Application Rest Services 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
