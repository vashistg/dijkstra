package com.dijkstra.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dijkstra.pojo.Region;
import com.dijkstra.pojo.RegionRequest;
import com.dijkstra.pojo.RegionResponse;
import com.dijkstra.service.api.RegionBuilderServiceApi;

@RestController
public class RegionController {

		@Autowired
		@Qualifier("regionBuilderServiceImpl")
		private RegionBuilderServiceApi regionBuilderService;
		
		@RequestMapping(value="/region", method=RequestMethod.POST)
		public RegionResponse handleRegionRequest(@RequestBody RegionRequest regionRequest){
			RegionResponse regionResponse = null;
			switch(regionRequest.getRequestType().trim()){
			case "ADD":{
				regionResponse = regionBuilderService.addRegion(regionRequest);
			    }break;
			case "DELETE":{
				regionResponse = regionBuilderService.deleteRegion(regionRequest);
			    }break;
			case "GET":{
				regionResponse = regionBuilderService.getRegion(regionRequest);
			    }break;
			case "UPDATE":{
				regionResponse = regionBuilderService.editRegion(regionRequest);
			    }break;
			case "ADD_PARENT":{
				regionResponse = regionBuilderService.editRegion(regionRequest);
			    }break;
			case "REMOVE_PARENT":{
				regionResponse = regionBuilderService.editRegion(regionRequest);
			    }break;
			case "ADD_CHILD":{
				regionResponse = regionBuilderService.editRegion(regionRequest);
			    }break;
			case "REMOVE_CHILD":{
				regionResponse = regionBuilderService.editRegion(regionRequest);
			    }break;
			}
			return regionResponse;
		}
		
		@RequestMapping(value="/test", method=RequestMethod.GET)
		public String handleRegionRequest(){
			/*Region region = new Region("DEL","city");
			RegionRequest regionRequest = new RegionRequest(region,"ADD");
			RegionResponse regionResponse = regionBuilderService.addRegion(regionRequest);
			regionRequest.setRequestType("GET");
			regionResponse = regionBuilderService.getRegion(regionRequest);
			regionRequest.setRequestType("DELETE");
			regionResponse = regionBuilderService.deleteRegion(regionRequest);*/
			Region region1 = new Region("DEL","city");
			Region region2 = new Region("CHA","city");
			Region region3 = new Region("AMD","city");
			Region region4 = new Region("CHE","city");
			Region region5 = new Region("BLR","city");
			
			RegionRequest regionRequest = new RegionRequest(region1,"ADD");
			regionBuilderService.addRegion(regionRequest);
			regionRequest.setRegion(region2);
			regionBuilderService.addRegion(regionRequest);
			regionRequest.setRegion(region3);
			regionBuilderService.addRegion(regionRequest);
			regionRequest.setRegion(region4);
			regionBuilderService.addRegion(regionRequest);
			regionRequest.setRegion(region5);
			regionBuilderService.addRegion(regionRequest);
			
			
			Region region6 = new Region("IND","country");
			RegionRequest regionRequest2 = new RegionRequest(region6,"ADD");
			List<String> childRegions = new ArrayList<>();
			childRegions.add("DEL");
			childRegions.add("CHA");
			childRegions.add("AMD");
			regionRequest2.setChildRegions(childRegions);
			regionBuilderService.addRegion(regionRequest2);
			
			
			Region region7 = new Region("ASI","continent");
			RegionRequest regionRequest3 = new RegionRequest(region7,"ADD");
			List<String> childRegions1 = new ArrayList<>();
			childRegions1.add("IND");
			regionRequest3.setChildRegions(childRegions1);
			regionBuilderService.addRegion(regionRequest3);
			
			
			
			Region region8 = new Region("CHI","country");
			RegionRequest regionRequest4 = new RegionRequest(region8,"ADD");
			List<String> childRegions3 = new ArrayList<>();
			childRegions3.add("CHE");
			childRegions3.add("BLR");
			regionRequest4.setChildRegions(childRegions3);
			List<String> parentRegions = new ArrayList<>();
			parentRegions.add("ASI");
			regionRequest4.setParentRegions(parentRegions);
			regionBuilderService.addRegion(regionRequest4);
			
			
			return "Query Executed Successfully";
		}	
}
