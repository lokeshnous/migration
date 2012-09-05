package com.advanceweb.afc.jb.webservice.service.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;




/**
 * This service class helps to call the different WebServices 
 * from NetSuite.
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */


@Service("netSuiteService")
public class NetSuiteServiceImpl implements NetSuiteService{
	
	
	private static final Logger LOGGER = Logger
			.getLogger(NetSuiteServiceImpl.class);
	
	/**
	 * This method is used to create a WebClient Object by taking the 
	 * web services URL. 
	 * @return WebClient obj
	 */
	 private WebClient createWebClient(String wsUrl) {
        String authorization = getAuthString();
		WebClient client = WebClient.create(wsUrl);
        client.header("Authorization", authorization);
        client.header("Content-Type", "application/json");
        return client;
	 }
	
	 /**
	  * This method is used to create the Authorization String
	  * to set it in the Web services header.
	  * @return String
	  */
	
	 private String getAuthString(){
			String account = "1338577";
	        String email = "pravinma@nousinfo.com";
	        String password = "pravin123";
	        String role = "3";
	        String authorization = "NLAuth nlauth_account=" + account + ", nlauth_email=" + email + ", nlauth_signature=" + password + ", nlauth_role=" + role + "";
			return authorization;
		 }
	
	 /**
	  * This method is used to create a customer through NetSuite
	  * @param Object in JSON format
	  * @return String in JSON format
	  */
	 
	 public String createCustomer(Object jsonCustomer){
		 
		 String authorization = getAuthString();
		 
		 //Object entityId = "{\"companyname\": \"Customer2\", \"recordtype\" : \"Customer\"}";
		 
		 	// Object entityId = "{companyname : Customer1, recordtype : Customer}";
		 
		WebClient client = createWebClient("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=154&deploy=1");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
	    
	    
		Response response = client.post(jsonCustomer);
		 
		 //Response response = client.get();
	     String jsonResponse= null;
		 
	     try {
        	jsonResponse = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
	     LOGGER.info("Json for create Customer ="+jsonResponse);
        return jsonResponse;
	 }
	

}
