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
		Region region = new Region(regionNode.getName(),regionNode.getType());
		response.setRegion(region);
		response.setMessage("Region Fetched Successfully");
		return response;
	}

	@Override
	public RegionResponse editRegion(RegionRequest reuest) {
		// TODO Auto-generated method stub
		return null;
	}

}
