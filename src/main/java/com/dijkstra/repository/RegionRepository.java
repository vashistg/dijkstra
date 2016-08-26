package com.dijkstra.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dijkstra.neo4j.nodes.RegionNode;


@Repository
@Transactional
public interface RegionRepository extends GraphRepository<RegionNode> {
		RegionNode findByName(String name);
		
		@Query("match (fromNode:RegionNode{name:{regionName}})-[:SUB_REGION_OF] -> (toNode:RegionNode) RETURN toNode")	
		List<RegionNode> getParentRegions(@Param("regionName") String regionName);
	
		@Query("match (fromNode:RegionNode{name:{regionName}})-[:SUB_REGION_OF*..100] -> (toNode:RegionNode) RETURN toNode")	
		List<RegionNode> getAllParentRegions(@Param("regionName") String regionName);
		
		@Query("match (fromNode:RegionNode)-[:SUB_REGION_OF] -> (toNode:RegionNode{name:{regionName}}) RETURN fromNode")	
		List<RegionNode> getChildRegions(@Param("regionName") String regionName);
		
		@Query("match (fromNode:RegionNode)-[:SUB_REGION_OF*..100] -> (toNode:RegionNode{name:{regionName}}) RETURN fromNode")	
		List<RegionNode> getAllChildRegions(@Param("regionName") String regionName);
		
		@Query("match n return n.name")
		List<String> getAllNodeNames();
}
