package com.advanceweb.common.index.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.common.Logger;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.lucene.LuceneIndex;
import com.advanceweb.common.index.lucene.LuceneResult;
import com.advanceweb.common.index.service.LocationIndexService;

@Service
public class LocationIndexServiceImpl implements LocationIndexService {

	private static final Logger LOGGER = Logger
			.getLogger(LocationIndexServiceImpl.class);

	@Autowired
	private LuceneIndex locationIndex;

	@Autowired
	private LocationDAO locationDao;

	private Map<Integer, LocationDTO> locations;

	private synchronized void initLocations() {
		if (locations == null) {
			LOGGER.debug("Loading locations");
			locations = new HashMap<Integer, LocationDTO>();
			for (LocationDTO dto : locationDao.findAll()) {
				locations.put(dto.getId(), dto);
			}
		}
	}

	@Override
	public List<LocationDTO> findLocation(Double latitude, Double longitude,
			Double radius) throws JobBoardServiceException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("latitude", Double.toString(latitude));
		params.put("longitude", Double.toString(longitude));
		params.put("radius", Double.toString(radius));

		return executeQuery(params);
	}

	@Override
	public List<LocationDTO> findMatchingLocation(String city, String state)
			throws JobBoardServiceException {
		Map<String, String> params = new HashMap<String, String>();
		if (city != null) {
			params.put("city", city);
		}

		if (state != null) {
			params.put("state", state);
		}
		
		return executeQuery(params);
	}

	private List<LocationDTO> executeQuery(Map<String, String> params)
			throws JobBoardServiceException {
		initLocations();
		List<LocationDTO> nearbyLocations = new ArrayList<LocationDTO>();
		for (LuceneResult result : locationIndex.search(params)) {
			
			Document doc = result.getDocument();
			Fieldable field = (Field) doc.getFieldable("id");

			int id = Integer.parseInt(field.stringValue());
			nearbyLocations.add(locations.get(id));
		}
		return nearbyLocations;
	}
}
