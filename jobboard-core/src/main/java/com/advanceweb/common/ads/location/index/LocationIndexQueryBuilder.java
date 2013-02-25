/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.location.index;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.spatial.tier.DistanceQueryBuilder;

import com.advanceweb.common.index.lucene.LuceneQueryBuilder;

/**
 * Used for creating location queries for Ad Locations. This class allows
 * queries by matching the city/state names and also by using spatial queri3s. A
 * combination of name and location is not yet supported
 * 
 * @author sukeshnambiar
 * 
 */
@SuppressWarnings("deprecation")
public class LocationIndexQueryBuilder extends LocationIndexBase implements
		LuceneQueryBuilder {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(LocationIndexQueryBuilder.class);

	/**
	 * Build a Lucene query for the given parameters. This method looks for a
	 * parameter representing the radius value (RADIUS_PARAM). if one is found,
	 * it assumes this is a spatial query and create a spatial query. Otherwise
	 * a pattern query for matching city / state is created.
	 */
	@Override
	public Query buildQuery(Map<String, String> params) {
		if (params.containsKey(LocationIndex.RADIUS_PARAM)) {
			return buildRadiusQuery(params);
		} else {
			return buildNameQuery(params);
		}
	}

	/**
	 * Create a spatial query to find locations within the radius and the
	 * refernece latitude longitude coordinates
	 * 
	 * @param params
	 *            A map containing, the radius,latitude and longitude
	 * @return The query for searching the index for nearby locations (locations
	 *         within a circle of radius from the latitude longitude combination
	 *         passed as parameters)
	 */
	private Query buildRadiusQuery(Map<String, String> params) {
		Double latitude = Double.parseDouble(params
				.get(LocationIndex.LATITUDE_PARAM));
		Double longitude = Double.parseDouble(params
				.get(LocationIndex.LONGITUDE_PARAM));
		Double radius = Double.parseDouble(params
				.get(LocationIndex.RADIUS_PARAM));
		DistanceQueryBuilder dqb = new DistanceQueryBuilder(latitude,
				longitude, radius, LocationIndex.LATITIDE_VALUE_FIELD,
				LocationIndex.LONGITUDE_VALUE_FIELD,
				LocationIndex.TIER_PREFIX_FIELD, true, getMinTier(),
				getMaxTier());
		Query query = new MatchAllDocsQuery();
		return dqb.getQuery(query);
	}

	/**
	 * Builds a query to match the given name to a city or state name. The
	 * caller my pass both city and state or pass one of them
	 * 
	 * @param params
	 *            The city / state parameters to match
	 * @return The Locations that has city / state with the names matching with
	 *         the parameter passed
	 */
	private Query buildNameQuery(Map<String, String> params) {
		BooleanQuery query = new BooleanQuery();

		BooleanQuery cityQuery = new BooleanQuery();
		BooleanQuery stateQuery = new BooleanQuery();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toLowerCase();

			if (key.equals("state")) {
				stateQuery.add(createPhraseQuery("state", value), Occur.SHOULD);
				stateQuery.add(createPhraseQuery("state_fullname", value),
						Occur.SHOULD);

			} else if (entry.getKey().equals("city")) {
				cityQuery.add(createPhraseQuery("city", value), Occur.SHOULD);
				cityQuery.add(createPhraseQuery("city_alias", value),
						Occur.SHOULD);
			}
		}

		if (params.containsKey("state")) {
			query.add(stateQuery, Occur.MUST);
		}
		if (params.containsKey("city")) {
			query.add(cityQuery, Occur.MUST);
		}

		LOGGER.debug("Generated Name query " + query);
		return query;
	}

	/**
	 * Create a query to match a given phrase from the key value pair. The query
	 * building require the tokens to be passed separately to ensure the order
	 * of words is maintained
	 * 
	 * @param key
	 *            The key of the field (city or state)
	 * @param value
	 *            The pattern to match. The value will be tokenized to create
	 *            the actual query
	 * @return The query representing the key value paird passed into the method
	 */
	private Query createPhraseQuery(String key, String value) {
		PhraseQuery pq = new PhraseQuery();
		for (String token : value.split(" ")) {
			pq.add(new Term(key, token));
		}
		return pq;

	}

}
