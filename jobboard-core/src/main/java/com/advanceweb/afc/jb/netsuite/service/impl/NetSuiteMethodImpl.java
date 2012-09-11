package com.advanceweb.afc.jb.netsuite.service.impl;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.netsuite.NetSuiteCredential;
import com.advanceweb.afc.jb.netsuite.service.NetSuiteMethod;

/**
 * This service class helps to call the different WebServices of NetSuite.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

@Service("netSuiteMethod")
public class NetSuiteMethodImpl implements NetSuiteMethod{

	
	private static final String AUTHORIZATION_STRING = "Authorization";
	private static final String CONTENT_TYPE_STRING = "Content-Type";
	private static final String CONTENT_TYPE_VALUE = "application/json";
	private static final Logger LOGGER = Logger.getLogger(NetSuiteMethodImpl.class);
	
	@Autowired
	private NetSuiteCredential netSuiteCredential;

	/**
	 * This method is used to call the WebClent get method.
	 * @param  Map<String, String> queryparamMap
	 * @param String paramString
	 * @return Response object
	 */
	
	public Response netSuiteGet(Map<String, String> queryparamMap, String paramString) {
		return createGETWebClient(queryparamMap, paramString).get();
	}

	
	/**
	 * This method is used to call the WebClent post method.
	 * @param  Map<String, String> queryparamMap
	 * @param String paramString
	 * @return Object 
	 */
	
	public Response netSuitePost(Map<String, String> queryparamMap, Object obj) {
		return createPOSTWebClient(queryparamMap).post(obj);
	}
	
	
	/**
	 * This method is used to create a WebClient Object by taking the web
	 * services URL.
	 * 
	 * @return WebClient obj
	 */
	private WebClient createPOSTWebClient(Map<String, String> queryparamMap) {
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
	 * This method is used to create a WebClient Object by taking the web
	 * services URL.
	 * 
	 * @return WebClient obj
	 */
	private WebClient createGETWebClient(Map<String, String> queryparamMap, String paramString) {
		String authorization = createAuthorization();
		WebClient client = WebClient.create(queryparamMap.get("baseUrl")+"?script="+queryparamMap.get("script")+"&deploy="+queryparamMap.get("deploy")+paramString);
		
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
