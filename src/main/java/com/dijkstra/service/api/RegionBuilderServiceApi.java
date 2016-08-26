package com.dijkstra.service.api;

import com.dijkstra.pojo.RegionRequest;
import com.dijkstra.pojo.RegionResponse;

public interface RegionBuilderServiceApi {
	RegionResponse addRegion(RegionRequest reuest);
	RegionResponse deleteRegion(RegionRequest reuest);
	RegionResponse getRegion(RegionRequest reuest);
	RegionResponse editRegion(RegionRequest reuest);
}
