package com.dijkstra.pojo;

import java.util.Set;

public class RegionResponse {
	private Region region;
	private Set<Region> parentRegions;
	private Set<Region> childRegions;
	private String parentStrings;
	private String childrenString;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getParentStrings() {
		return parentStrings;
	}
	public void setParentStrings(String parentStrings) {
		this.parentStrings = parentStrings;
	}
	public String getChildrenString() {
		return childrenString;
	}
	public void setChildrenString(String childrenString) {
		this.childrenString = childrenString;
	}

}
