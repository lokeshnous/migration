/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.location.index;

import java.util.List;

import com.advanceweb.common.ads.AdLocation;

/**
 * The Ad Location can be from various data sources like database, excel file,
 * text file etc. This interface is used to abstract the actual implementation
 * of the location data source.
 * 
 * @author sukeshnambiar
 * 
 */
public interface LocationIndexSource {
	/**
	 * Returns all the location information available in the source
	 * 
	 * @return The list of all locations available in the source
	 */
	List<AdLocation> findAll();
}
