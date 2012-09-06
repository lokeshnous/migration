package com.advanceweb.afc.jb.webservice;

import java.io.IOException;
import java.util.Properties;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Repository;


@Repository("netSuiteHelper")
public class NetSuiteHelper {

	private static final String PROPERTIES_FILE_NAME = "netSuite.properties";
	private static final String AUTHORIZATION_STRING = "Authorization";
	private static final String CONTENT_TYPE_STRING = "Content-Type";
	private static final String CONTENT_TYPE_VALUE = "application/json";
	private static final Logger LOGGER = Logger.getLogger(NetSuiteHelper.class);
	
	@Autowired
	private NetSuiteCredential netSuiteCredential;

	
	/**
	 * This method is used to create a WebClient Object by taking the web
	 * services URL.
	 * 
	 * @return WebClient obj
	 */
	public WebClient createWebClient(String wsUrl) {
		String authorization = createAuthorization();
		WebClient client = WebClient.create(wsUrl);
		client.header(AUTHORIZATION_STRING, authorization);
		client.header(CONTENT_TYPE_STRING, CONTENT_TYPE_VALUE);
		return client;
	}
	
	public Properties getWSProperties() {

		Properties entries = null;
		try {
			entries = PropertiesLoaderUtils.loadAllProperties(PROPERTIES_FILE_NAME);
		} catch (IOException e) {
			LOGGER.info("ERROR in NetSuiteHelper", e);
		}

		return entries;
	}

	
	
	public String createAuthorization(){
		
		String authorization = "NLAuth nlauth_account=" + netSuiteCredential.getAccount()
				+ ", nlauth_email=" + netSuiteCredential.getEmail() + ", nlauth_signature=" + netSuiteCredential.getPassword()
				+ ", nlauth_role=" + netSuiteCredential.getRole() + "";
		
		LOGGER.info("Authorization=>"+authorization);
		return authorization;
	}
	

}
