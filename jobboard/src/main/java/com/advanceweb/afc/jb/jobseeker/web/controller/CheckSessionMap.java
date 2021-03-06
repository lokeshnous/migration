/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.search.SearchParamDTO;

/**
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 24 Aug 2012
 */
@Repository("checkSessionMap")
@SuppressWarnings("unchecked")
public class CheckSessionMap {
	
	
	/**
	 * This method is used to get the session map for search operations.
	 * If session map present in the session then it will return the same
	 * or else it will create a empty map and return it.
	 * @param session
	 * @return Map<String, String>
	 */
	
	public Map<String, String> getSearchSessionMap(HttpSession session){
		
		if(session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) == null){
			return new HashMap<String, String>();
		}else{
			return (Map<String, String>) session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		}
		
	}
	
	/**
	 * This method is used to get the session map for Resume search operations.
	 * If session map present in the session then it will return the same
	 * or else it will create a empty map and return it.
	 * @param session
	 * @return Map<String, String>
	 */
	
	public Map<String, String> getResumeSearchSessionMap(HttpSession session){
		
		if(session.getAttribute(MMJBCommonConstants.RESUME_SEARCH_SESSION_MAP) == null){
			return new HashMap<String, String>();
		}else{
			return (Map<String, String>) session.getAttribute(MMJBCommonConstants.RESUME_SEARCH_SESSION_MAP);
		}
		
		
	}

}
