package com.advanceweb.afc.jb.netsuite.service;

import java.util.Map;

import javax.ws.rs.core.Response;

public interface NetSuiteMethod {
	
	public Response netSuiteGet(Map<String, String> queryparamMap);
	
	public Response netSuitePost(Map<String, String> queryparamMap, Object obj);

}
