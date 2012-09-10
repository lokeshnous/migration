package com.advanceweb.afc.jb.netsuite.service.impl;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.netsuite.NetSuiteCredential;
import com.advanceweb.afc.jb.netsuite.service.NetSuiteMethod;


@Service("netSuiteMethod")
public class NetSuiteMethodImpl implements NetSuiteMethod{

	
	private static final String AUTHORIZATION_STRING = "Authorization";
	private static final String CONTENT_TYPE_STRING = "Content-Type";
	private static final String CONTENT_TYPE_VALUE = "application/json";
	private static final Logger LOGGER = Logger.getLogger(NetSuiteMethodImpl.class);
	
	@Autowired
	private NetSuiteCredential netSuiteCredential;

	/**
	 * 
	 */
	
	public Response netSuiteGet(Map<String, String> queryparamMap) {
		return createWebClient(queryparamMap).get();
	}

	
	/**
	 * 
	 */
	
	public Response netSuitePost(Map<String, String> queryparamMap, Object obj) {
		return createWebClient(queryparamMap).post(obj);
	}
	
	
	/**
	 * This method is used to create a WebClient Object by taking the web
	 * services URL.
	 * 
	 * @return WebClient obj
	 */
	private WebClient createWebClient(Map<String, String> queryparamMap) {
		String authorization = createAuthorization();
		WebClient client = WebClient.create(queryparamMap.get("baseUrl"));
		client.query("script", queryparamMap.get("script"));
		client.query("deploy", queryparamMap.get("deploy"));
		
		client.header(AUTHORIZATION_STRING, authorization);
		client.header(CONTENT_TYPE_STRING, CONTENT_TYPE_VALUE);
		
		LOGGER.info("Web Client=>"+client);
		LOGGER.info("Web Client=>"+client.getCurrentURI());
		
		return client;
	}
	
	/**
	 * 
	 * @return
	 */
	
	private String createAuthorization(){
		String authorization = "NLAuth nlauth_account=" + netSuiteCredential.getAccount()
				+ ", nlauth_email=" + netSuiteCredential.getEmail() + ", nlauth_signature=" + netSuiteCredential.getPassword()
				+ ", nlauth_role=" + netSuiteCredential.getRole() + "";
		
		LOGGER.info("Authorization=>"+authorization);
		return authorization;
	}
	
	
}
