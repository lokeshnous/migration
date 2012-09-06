package com.advanceweb.afc.jb.netsuite.service;

import javax.ws.rs.core.Response;

public interface NetSuiteMethod {
	
	public Response netSuiteGet(String wsUrl);
	
	public Response netSuitePost(String wsUrl, Object obj);

}
