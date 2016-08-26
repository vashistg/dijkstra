package com.dijkstra.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dijkstra.neo4j.nodes.RegionNode;
import com.dijkstra.pojo.Region;
import com.dijkstra.pojo.RegionRequest;
import com.dijkstra.pojo.RegionResponse;
import com.dijkstra.repository.RegionRepository;
import com.dijkstra.service.api.RegionBuilderServiceApi;

@Service("regionBuilderServiceImpl")
public class RegionBuilderServiceImpl implements RegionBuilderServiceApi{
	
	@Autowired
	private RegionRepository regionRepository;

	@Override
	public RegionResponse addRegion(RegionRequest reuest) {
		RegionNode regionNode = new RegionNode(reuest.getRegion().getName(),reuest.getRegion().getType());
		if(null != reuest.getChildRegions() && reuest.getChildRegions().size()>0){
			Set<RegionNode> childNodes = getNodesFromName(reuest.getChildRegions());
			for(RegionNode node :childNodes){
				node.addMasterRegion(regionNode);
			}
			regionNode.setSubRegions(childNodes);
		}
		if(null != reuest.getParentRegions() && reuest.getParentRegions().size()>0){
			Set<RegionNode> parentNodes = getNodesFromName(reuest.getParentRegions());
			for(RegionNode node :parentNodes){
				node.addSubRegion(regionNode);
			}
			regionNode.setMasterRegions(parentNodes);
		}
		regionRepository.save(regionNode);
		RegionResponse response = new RegionResponse();
		response.setMessage("Region - "+ reuest.getRegion().getName() +" - Added Successfully");
		return response;
	}

	private Set<RegionNode> getNodesFromName(List<String> regions) {
		Set<RegionNode> regionNodes = new HashSet<>();
		RegionNode regionNode = null;
		for(String regionName : regions){
			regionNode = regionRepository.findByName(regionName);
			regionNodes.add(regionNode);
		}
		return regionNodes;
	}

	@Override
	public RegionResponse deleteRegion(RegionRequest reuest) {
		// TODO Auto-generated method stub
		RegionNode regionNode = regionRepository.findByName(reuest.getRegion().getName());
		regionRepository.delete(regionNode);
		RegionResponse response = new RegionResponse();
		response.setMessage("Region - "+ reuest.getRegion().getName() + " - Deleted Successfully");
		return response;
	}

	@Override
	public RegionResponse getRegion(RegionRequest reuest) {
		RegionNode regionNode = regionRepository.findByName(reuest.getRegion().getName());
		RegionResponse response = new RegionResponse();
		if(null != regionNode){
			Region region = new Region(regionNode.getName(),regionNode.getType());
			response.setRegion(region);
			Set<Region> parentRegions = convertToRegions(regionNode.getMasterRegions());
			response.setParentRegions(parentRegions);
			Set<Region> childRegions = convertToRegions(regionNode.getSubRegions());
			response.setChildRegions(childRegions);
			
			List<RegionNode> allParents = regionRepository.getAllParentRegions(reuest.getRegion().getName());
			String parentStrings = getRegionNames(allParents);
			response.setParentStrings(parentStrings);
			List<RegionNode> allChildren = regionRepository.getAllChildRegions(reuest.getRegion().getName());
			String childrenStrings = getRegionNames(allChildren);
			response.setChildrenString(childrenStrings);
			response.setMessage("Region Fetched Successfully");
		}
		return response;
	}

	private String getRegionNames(List<RegionNode> nodes) {
		String nameString = "";
		if(null != nodes && nodes.size() > 0){
			for(RegionNode node : nodes){
				nameString = nameString + node.getName() +"|";
			}
		}
		return nameString;
	}

	private Set<Region> convertToRegions(Set<RegionNode> subRegions) {
		Set<Region> regions = new HashSet<>();
		if(null != subRegions && subRegions.size() > 0){
			for(RegionNode regionNode : subRegions){
				Region region = new Region(regionNode.getName(),regionNode.getType());
				regions.add(region);
			}
		}
		return regions;
	}

	@Override
	public RegionResponse editRegion(RegionRequest reuest) {
		RegionResponse regionResponse = null;
		switch(reuest.getRequestType().trim()){
		case "UPDATE":{
			regionResponse = updateRegionNode(reuest);
		    }break;
		case "ADD_PARENT":{
			regionResponse = addParentToRegionNode(reuest);
		    }break;
		case "REMOVE_PARENT":{
			regionResponse = removeParentFromRegionNode(reuest);
		    }break;
		case "ADD_CHILD":{
			regionResponse = addChildToRegionNode(reuest);
		    }break;
		case "REMOVE_CHILD":{
			regionResponse = removeChildFromRegionNode(reuest);
		    }break;
		}
		return regionResponse;
	}

	private RegionResponse removeChildFromRegionNode(RegionRequest reuest) {
		RegionNode regionNode = regionRepository.findByName(reuest.getRegion().getName());
		RegionResponse response = new RegionResponse();
		String message = "";
		if(null != regionNode && reuest.getChildRegions() != null && reuest.getChildRegions().size() >0){
			for(String child : reuest.getChildRegions()){
				RegionNode childNode = regionRepository.findByName(child);
				if(regionNode.getSubRegions().contains(childNode)){
					regionNode.getSubRegions().remove(regionNode);
					message = message + regionNode.getName()+"|";
					childNode.getMasterRegions().remove(regionNode);
					regionRepository.save(childNode);
				}
			}
		}
		regionRepository.save(regionNode);
		response.setMessage("Children - "+message +"- removed from region - "+ regionNode.getName());
		return response;
	}

	private RegionResponse addChildToRegionNode(RegionRequest reuest) {
		RegionNode regionNode = regionRepository.findByName(reuest.getRegion().getName());
		RegionResponse response = new RegionResponse();
		String message = "";
		if(null != regionNode && reuest.getChildRegions() != null && reuest.getChildRegions().size() >0){
			for(String child : reuest.getChildRegions()){
				RegionNode childNode = regionRepository.findByName(child);
				if(!regionNode.getSubRegions().contains(childNode)){
					regionNode.addSubRegion(childNode);
					message = message + childNode.getName()+"|";
					childNode.addMasterRegion(regionNode);
					regionRepository.save(childNode);
				}
			}
		}
		regionRepository.save(regionNode);
		response.setMessage("Children - "+message +"- added for region - "+ regionNode.getName());
		return response;
	}

	private RegionResponse removeParentFromRegionNode(RegionRequest reuest) {
		RegionNode regionNode = regionRepository.findByName(reuest.getRegion().getName());
		RegionResponse response = new RegionResponse();
		String message = "";
		if(null != regionNode && reuest.getParentRegions() != null && reuest.getParentRegions().size() >0){
			for(String parent : reuest.getParentRegions()){
				RegionNode parentNode = regionRepository.findByName(parent);
				if(regionNode.getMasterRegions().contains(parentNode)){
					regionNode.getMasterRegions().remove(parentNode);
					message = message + parentNode.getName()+"|";
					parentNode.getSubRegions().remove(regionNode);
					regionRepository.save(parentNode);
				}
			}
		}
		regionRepository.save(regionNode);
		response.setMessage("Parents - "+message +"- removed  from region - "+ regionNode.getName());
		return response;
	}

	private RegionResponse addParentToRegionNode(RegionRequest reuest) {
		RegionNode regionNode = regionRepository.findByName(reuest.getRegion().getName());
		RegionResponse response = new RegionResponse();
		String message = "";
		if(null != regionNode && reuest.getParentRegions() != null && reuest.getParentRegions().size() >0){
			for(String parent : reuest.getParentRegions()){
				RegionNode parentNode = regionRepository.findByName(parent);
				if(!regionNode.getMasterRegions().contains(parentNode)){
					regionNode.addMasterRegion(parentNode);
					message = message + parentNode.getName()+"|";
					parentNode.addSubRegion(regionNode);
					regionRepository.save(parentNode);
				}
			}
		}
		regionRepository.save(regionNode);
		response.setMessage("Parents - "+message +"- added for region - "+ regionNode.getName());
		return response;
	}

	private RegionResponse updateRegionNode(RegionRequest reuest) {
		RegionNode regionNode = regionRepository.findByName(reuest.getRegion().getName());
		RegionResponse response = new RegionResponse();
		if(null != regionNode){
			regionNode.setType(reuest.getRegion().getType());
			regionRepository.save(regionNode);
			response.setMessage("Region - "+ reuest.getRegion().getName() + " - Updated Successfully");
		}
		return response;
	}

}
