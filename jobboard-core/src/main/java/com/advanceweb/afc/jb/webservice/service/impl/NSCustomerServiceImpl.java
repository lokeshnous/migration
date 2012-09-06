package com.advanceweb.afc.jb.webservice.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.webservice.NetSuiteHelper;
import com.advanceweb.afc.jb.webservice.service.NSCustomerService;
import com.advanceweb.afc.jb.webservice.service.WebServiceMethod;

/**
 * This service class helps to call the different WebServices from NetSuite.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

@Service("nsCustomerService")
public class NSCustomerServiceImpl implements NSCustomerService {

	private static final Logger LOGGER = Logger
			.getLogger(NSCustomerServiceImpl.class);

	@Autowired
	private NetSuiteHelper netSuiteHelper;
	
	@Autowired
	private WebServiceMethod webServiceMethod;

	/**
	 * 
	 * @return
	 */
	
	public String createWSUrl() {
		Properties entries = netSuiteHelper.getWSProperties();
		
		String wsUrl = entries.getProperty("baseUrl").concat("?script=")
				.concat(entries.getProperty("scriptForCreateCustomer")).concat("&deploy=")
				.concat(entries.getProperty("deployForCreateCustomer"));
		LOGGER.info("WS Url=>" + wsUrl);
		
		return wsUrl;
	}
	
	

	/**
	 * This method is used to create a customer through NetSuite
	 * 
	 * @param Object
	 *            in JSON format
	 * @return String in JSON format
	 */

	public String createCustomer(Object jsonCustomer) {

		String wsURL = createWSUrl();
		WebClient client = netSuiteHelper.createWebClient(wsURL);
		Response response = webServiceMethod.netSuitePost(client, jsonCustomer);
		String jsonResponse = null;
		try {
			jsonResponse = IOUtils.readStringFromStream((InputStream) response
					.getEntity());

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Failed to get a string represenation of the response", e);
		}
		LOGGER.info("Json for create Customer =" + jsonResponse);
		return jsonResponse;
	}

}
