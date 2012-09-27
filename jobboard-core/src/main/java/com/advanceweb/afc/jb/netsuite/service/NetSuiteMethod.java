package com.advanceweb.afc.jb.netsuite.service;

import java.util.Map;

import javax.ws.rs.core.Response;

/**
 * This service interface helps to call the different WebServices method of NetSuite.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

public interface NetSuiteMethod {
	
	/**
	 * This method is used to call the WebClent get method.
	 * @param  Map<String, String> queryparamMap
	 * @param String paramString
	 * @return Response object
	 */
	
	Response netSuiteGet(Map<String, String> queryparamMap, String paramString);
	
	/**
	 * This method is used to call the WebClent post method.
	 * @param  Map<String, String> queryparamMap
	 * @param String paramString
	 * @return Object 
	 */
	Response netSuitePost(Map<String, String> queryparamMap, Object obj);

}
