package com.advanceweb.common.ads.location.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.AdLocation;
import com.advanceweb.common.ads.location.index.LocationIndex;
import com.advanceweb.common.ads.location.service.LocationIndexService;
import com.advanceweb.common.index.lucene.LuceneIndex;
import com.advanceweb.common.index.lucene.LuceneResult;

/**
 * The implementation of the location index using LuceneIndex
 * 
 * @author sukeshnambiar
 * 
 */
@Service
public class LocationIndexServiceImpl implements LocationIndexService {

	private static final Logger LOGGER = Logger
			.getLogger(LocationIndexServiceImpl.class);

	@Autowired
	private LuceneIndex locationIndex;

	/**
	 * Finds nearby location using spatial query by lucene.
	 */
	@Override
	public List<AdLocation> findNearByLocations(Float latitude,
			Float longitude, Float radius) throws JobBoardServiceException {

		Map<String, String> params = new HashMap<String, String>();
		params.put(LocationIndex.LATITUDE_PARAM, Double.toString(latitude));
		params.put(LocationIndex.LONGITUDE_PARAM, Double.toString(longitude));
		params.put(LocationIndex.RADIUS_PARAM, Double.toString(radius));

		return executeQuery(params);
	}

	/**
	 * Find locations with city / state name matching the given arguments
	 */
	@Override
	public List<AdLocation> findMatchingLocation(String city, String state)
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

	/**
	 * Runs a query on the lucene index
	 * 
	 * @param params
	 *            The parameters for the query. The Query builder will interpret
	 *            the parameters based on the presense of a parameter with key
	 *            'radius'. If the list contains 'radius' it is assumed to be a
	 *            spatial query
	 * @return The search result based on the type of the query
	 * @throws JobBoardServiceException
	 */
	private List<AdLocation> executeQuery(Map<String, String> params)
			throws JobBoardServiceException {
		List<AdLocation> nearbyLocations = new ArrayList<AdLocation>();

		LOGGER.debug("Searching for nearby locations");
		for (LuceneResult result : locationIndex.search(params)) {

			Document doc = result.getDocument();
			AdLocation location = createLocation(doc);
			nearbyLocations.add(location);

		}
		return nearbyLocations;
	}

	/**
	 * Create a location object from the lucene document
	 * 
	 * @param doc
	 *            The lucene document representing the locations
	 * @return The location object created from the lucene documetn
	 */
	private AdLocation createLocation(Document doc) {
		AdLocation location = new AdLocation();

		location.setCity(doc.getFieldable("city").stringValue());
		location.setCountry(doc.getFieldable("country").stringValue());
		location.setLabel(doc.getFieldable("label").stringValue());
		location.setLatitude(DocUtil.getFloatValue(doc, "latitude"));
		location.setLongitude(DocUtil.getFloatValue(doc, "longitude"));
		location.setState(doc.getFieldable("state").stringValue());
		return location;
	}

	/**
	 * This class is a helper to retrive the various data types from the lucen
	 * document
	 * 
	 * @author sukeshnambiar
	 * 
	 */
	private static class DocUtil {
		private static Float getFloatValue(Document doc, String fieldName) {
			String value = doc.getFieldable(fieldName).stringValue();
			try {
				return Float.valueOf(value);
			} catch (NumberFormatException ex) {
				return null;
			}
		}

		private static Integer getIntegerValue(Document doc, String fieldName) {
			String value = doc.getFieldable(fieldName).stringValue();
			try {
				return Integer.valueOf(value);
			} catch (NumberFormatException ex) {
				return null;
			}
		}

	}
}
