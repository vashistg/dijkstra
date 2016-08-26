package com.dijkstra.pojo;

import java.util.Set;

public class RegionResponse {
	private Region region;
	private Set<Region> parentRegions;
	private Set<Region> childRegions;
	private String getParentStrings;
	private String getChildrenString;
	private String message;
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Set<Region> getParentRegions() {
		return parentRegions;
	}
	public void setParentRegions(Set<Region> parentRegions) {
		this.parentRegions = parentRegions;
	}
	public Set<Region> getChildRegions() {
		return childRegions;
	}
	public void setChildRegions(Set<Region> childRegions) {
		this.childRegions = childRegions;
	}
	public String getGetParentStrings() {
		return getParentStrings;
	}
	public void setGetParentStrings(String getParentStrings) {
		this.getParentStrings = getParentStrings;
	}
	public String getGetChildrenString() {
		return getChildrenString;
	}
	public void setGetChildrenString(String getChildrenString) {
		this.getChildrenString = getChildrenString;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
