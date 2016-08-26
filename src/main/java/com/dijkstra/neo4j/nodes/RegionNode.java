package com.dijkstra.neo4j.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class RegionNode {
	
	@GraphId
	private Long id;
	
	private String name;
	private String type;
	
	public RegionNode(){
		
	}
	
	@Relationship(type = "SUB_REGION_OF", direction = "INCOMING")
	private Set<RegionNode> subRegions = new HashSet<>();
	
	@Relationship(type = "MASTER_REGION_OF", direction = "INCOMING")
	private Set<RegionNode> masterRegions = new HashSet<>();
	
	public RegionNode(String name,String type){
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<RegionNode> getSubRegions() {
		return subRegions;
	}
	public void setSubRegions(Set<RegionNode> subRegions) {
		this.subRegions = subRegions;
	}
	public Set<RegionNode> getMasterRegions() {
		return masterRegions;
	}
	public void setMasterRegions(Set<RegionNode> masterRegions) {
		this.masterRegions = masterRegions;
	}
	
	public void addSubRegion(RegionNode region){
		if(!this.subRegions.contains(region)){
			this.subRegions.add(region);
		}
	}
	
	public void addMasterRegion(RegionNode region){
		if(!this.masterRegions.contains(region)){
			this.masterRegions.add(region);
		}
	}

}
