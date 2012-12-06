package com.advanceweb.common.index.service;

import java.util.List;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface LocationIndexService {

	List<LocationDTO> findLocation(Double latitude, Double longitude,
			Double radius) throws JobBoardServiceException;

	List<LocationDTO> findMatchingLocation(String city, String state)
			throws JobBoardServiceException;
}
