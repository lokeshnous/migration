package com.advanceweb.afc.jb.webservice.service;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public interface WebServiceMethod {
	
	public Response netSuiteGet(WebClient client);
	
	public Response netSuitePost(WebClient client, Object obj);

}
