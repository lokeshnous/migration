/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.location.index;

/**
 * The base interface for Location index. Contains constants defining the field
 * names, spatial inex parameters etc
 * 
 * @author sukeshnambiar
 * 
 */
public interface LocationIndex {

	// Fields used by the spatial queries
	/** The latitide value field. */
	String LATITIDE_VALUE_FIELD = "lat";
	
	/** The longitude value field. */
	String LONGITUDE_VALUE_FIELD = "lon";
	
	/** The tier prefix field. */
	String TIER_PREFIX_FIELD = "tier_";

	// Spatial index ranges
	/** The max distance. */
	double MAX_DISTANCE = 100;
	
	/** The min distance. */
	double MIN_DISTANCE = 1;

	// Parameters passed to the methods
	/** The latitude param. */
	String LATITUDE_PARAM = "latitude";
	
	/** The longitude param. */
	String LONGITUDE_PARAM = "longitude";
	
	/** The radius param. */
	String RADIUS_PARAM = "radius";

}
