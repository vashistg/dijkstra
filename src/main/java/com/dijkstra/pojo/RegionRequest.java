package com.dijkstra.pojo;

import java.util.List;

public class RegionRequest {
	private Region region;
	List<String> parentRegions;
	List<String> childRegions;
	private String requestType;
	
	public RegionRequest(){
		super();
	}
	
	public RegionRequest(Region region, String requestType) {
		super();
		this.region = region;
		this.requestType = requestType;
	}
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public List<String> getParentRegions() {
		return parentRegions;
	}
	public void setParentRegions(List<String> parentRegions) {
		this.parentRegions = parentRegions;
	}
	public List<String> getChildRegions() {
		return childRegions;
	}
	public void setChildRegions(List<String> childRegions) {
		this.childRegions = childRegions;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
