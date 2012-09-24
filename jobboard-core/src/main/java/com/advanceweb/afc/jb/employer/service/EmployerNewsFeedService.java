package com.advanceweb.afc.jb.employer.service;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.NewsDTO;

/**
 * <code> EmployerNewsFeedService </code> is the service interface for 
 * reading the XML news feed.
 * @author Reetesh RN
 * @version 1.0
 * @since 22nd September 2012
 * 
 */

public interface EmployerNewsFeedService {
	
	/**
	 * This method is used to get the XML news feed in the form of NewsDTO to
	 * display for the Platinum user.
	 * @param 
	 * @return object of Map<String, List<NewsDTO>>
	 */
	Map<String, List<NewsDTO>> getNewsFromXML();

}
