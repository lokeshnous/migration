package com.advanceweb.afc.jb.search.service;


import java.util.List;

import net.sf.json.JSONObject;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;

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
	
	/**
	 * This method will convert the ResumeSearchResultDTO to JSON object
	 * @param ResumeSearchResultDTO
	 * @return JSONObject
	 */
	JSONObject convertToJSONForResume(final ResumeSearchResultDTO resumeSearchResultDTO);
	
	/**
	 * This method is  used to convert the ResumeDTOList 
	 * coming from DB to JSON object.
	 * @param List<ResumeDTO>
	 * @return JSONObject
	 */
	JSONObject convertToJSONForResumeFromDB(List<ResumeDTO> resumeDTOList);


}
