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
	String LATITIDE_VALUE_FIELD = "lat";
	String LONGITUDE_VALUE_FIELD = "lon";
	String TIER_PREFIX_FIELD = "tier_";

	// Spatial index ranges
	double MAX_DISTANCE = 100;
	double MIN_DISTANCE = 1;

	// Parameters passed to the methods
	String LATITUDE_PARAM = "latitude";
	String LONGITUDE_PARAM = "longitude";
	String RADIUS_PARAM = "radius";

}
