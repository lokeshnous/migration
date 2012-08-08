package com.advanceweb.afc.jb.search.engine;


import net.sf.json.JSONObject;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;

/**
 * This class has been created as a service interface for converting to JSON object
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 31ST July 2012
 */

public interface JSONConverterService {
	
	/**
	 * This method will convert the JobSearchResultDTO to JSON object
	 * @param JobSearchResultDTO
	 * @return JSONObject
	 */
	JSONObject convertToJSON(final JobSearchResultDTO jSResultDTO);
	

}
