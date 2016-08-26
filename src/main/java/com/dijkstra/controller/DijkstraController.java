package com.dijkstra.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dijkstra.pojo.City;
import com.dijkstra.pojo.Region;
import com.dijkstra.pojo.RegionNodes;
import com.dijkstra.pojo.RegionRequest;
import com.dijkstra.pojo.Regions;
import com.dijkstra.service.api.RegionBuilderServiceApi;

@RestController
public class DijkstraController {
	
	@Autowired
	@Qualifier("regionBuilderServiceImpl")
	private RegionBuilderServiceApi regionBuilderService;
	
	@Autowired
	private Regions regions;
	
	@Autowired
	private RegionNodes regionNodes;
	
	@ResponseBody
	@RequestMapping(value="/")
	public String welcome(){
		return "<h1><b>I am up and running &#9786</b></h1>";
	}
	
	@RequestMapping(value="/getMatchingRegion")
	public @ResponseBody List<String> getMatchingPatterns(@RequestParam String pattern){
		List<String> filteredList = new ArrayList<>();
		if(regionNodes.getNodes().size()==0){
			regionNodes.getNodes().addAll(regionBuilderService.getAllRegionNames());
		}
		for(String node:regionNodes.getNodes()){
			if(node.toLowerCase().startsWith(pattern.toLowerCase())){
				filteredList.add(node);
			}
		}
		return filteredList;
	}
	
	
	
	
	@ResponseBody
    @RequestMapping(value="/insertData")
    public void insertCities(){
           /**
           * Add all cities first
           * Add all countries as there parent 
            * While doing this add the parent in every child as well.
           */
           Map<String,List<String>> countryCityMap = new HashMap<>();
           int counter = 0;
           for(City city:regions.getCities()){
        	   counter++;
        	   if(counter > 100){
        		   break;
        	   }
                  //ADD city as a node.
        	   Region region = new Region(city.getCityName(),"city");
        	   RegionRequest regionRequest = new RegionRequest(region,"ADD");
        	   regionBuilderService.addRegion(regionRequest);
                 // regionService.upsertRegion(city.getCityName(), null);
                  //ADD cities to the Country Map.
                  if(countryCityMap.get(city.getCountryName())==null){
                        countryCityMap.put(city.getCountryName(),new ArrayList<>());
                  }
                  countryCityMap.get(city.getCountryName()).add(city.getCityName());
           }
           
           /**
           * Now do the insertion for the countries.
           */
           for(Entry<String,List<String>> entry : countryCityMap.entrySet()){
                  String country = entry.getKey();
                  List<String> cities = entry.getValue();
                  System.out.println(country + " airports : "+ cities.size());
                  Region region = new Region(country,"country");
           	       RegionRequest regionRequest = new RegionRequest(region,"ADD");
           	       regionRequest.setChildRegions(cities);
                  //Add every country with there child nodes set.
           	      regionBuilderService.addRegion(regionRequest);
                  
           }
    }

}
