package com.dijkstra.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dijkstra.neo4j.nodes.RegionNode;


@Repository
@Transactional
public interface RegionRepository extends GraphRepository<RegionNode> {
	RegionNode findByName(String name);
	}
