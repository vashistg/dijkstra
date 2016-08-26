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
			/*Region region1 = new Region("DEL","city");
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
			regionBuilderService.addRegion(regionRequest4);*/
			
			/*Region region11 = new Region("BLR","city");
			RegionRequest regionRequest7 = new RegionRequest(region11,"GET");
			RegionResponse response = regionBuilderService.getRegion(regionRequest7);*/
			
			/*Region region9 = new Region("DEL","city");
			RegionRequest regionRequest5 = new RegionRequest(region9,"DELETE");
			regionBuilderService.deleteRegion(regionRequest5);
			
			Region region10 = new Region("IND","country");
			RegionRequest regionRequest6 = new RegionRequest(region10,"DELETE");
			regionBuilderService.deleteRegion(regionRequest6);*/
			
		/*	Region region = new Region("AMD","city");
			RegionRequest regionRequest = new RegionRequest(region,"DELETE");
			regionBuilderService.deleteRegion(regionRequest);
			
			regionRequest.setRequestType("ADD");
			regionBuilderService.addRegion(regionRequest);*/
			
			
			Region region12 = new Region("BLR","metroCity");
			RegionRequest regionRequest8 = new RegionRequest(region12,"UPDATE");
			RegionResponse response = regionBuilderService.editRegion(regionRequest8);
			
			
			Region region13 = new Region("AMD","city");
			RegionRequest regionRequest9 = new RegionRequest(region13,"ADD_PARENT");
			List<String> parentRegions = new ArrayList<>();
			parentRegions.add("CHI");
			regionRequest9.setParentRegions(parentRegions);
			response = regionBuilderService.editRegion(regionRequest9);
			
			
			Region region14 = new Region("AMD","city");
			RegionRequest regionRequest10 = new RegionRequest(region14,"REMOVE_PARENT");
			List<String> parentRegions1 = new ArrayList<>();
			parentRegions1.add("CHI");
			regionRequest10.setParentRegions(parentRegions1);
			response = regionBuilderService.editRegion(regionRequest10);
			
			
			Region region15 = new Region("CHI","country");
			RegionRequest regionRequest11 = new RegionRequest(region15,"ADD_CHILD");
			List<String> childRegions = new ArrayList<>();
			childRegions.add("AMD");
			regionRequest11.setChildRegions(childRegions);
			response = regionBuilderService.editRegion(regionRequest11);
			
			
			Region region16 = new Region("CHI","city");
			RegionRequest regionRequest12 = new RegionRequest(region16,"REMOVE_CHILD");
			List<String> childRegions1 = new ArrayList<>();
			childRegions1.add("AMD");
			regionRequest12.setChildRegions(childRegions1);
			response = regionBuilderService.editRegion(regionRequest12);
				
			
			return "Query Executed Successfully";
		}	
}
