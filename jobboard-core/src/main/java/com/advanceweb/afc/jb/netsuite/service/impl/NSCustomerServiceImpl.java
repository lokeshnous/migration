package com.advanceweb.afc.jb.netsuite.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.JsonUtil;
import com.advanceweb.afc.jb.netsuite.NSCustomer;
import com.advanceweb.afc.jb.netsuite.NetSuiteHelper;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.netsuite.service.NetSuiteMethod;

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
	private NetSuiteMethod netSuiteMethod;
	
	@Autowired
	private NetSuiteHelper netSuiteHelper;

	
	

	/**
	 * This method is used to create a customer through NetSuite
	 * 
	 * @param Object
	 *            in JSON format
	 * @return String in JSON format
	 */

	public String createCustomer(UserDTO userDTO) {
		
		NSCustomer nsCustomer = createNSCustomer(userDTO);	
		
		String jsonCustomer = JsonUtil.toJson(nsCustomer);
		
		LOGGER.info("Json for Customer=>"+jsonCustomer);
		
		String wsURL = createNSUrl();
		
		Response response = netSuiteMethod.netSuitePost(wsURL, jsonCustomer);
		
		return getJSONFromResponse(response);
	}
	
	/**
	 * 
	 * @param empProfileDTO
	 * @return
	 */
	
	private NSCustomer createNSCustomer(UserDTO userDTO){
		
		NSCustomer nsCustomer = new NSCustomer();
		//custDTO.setCustomerId(460460);
		nsCustomer.setCompanyName(userDTO.getFirstName() + " " + userDTO.getLastName());
		nsCustomer.setRecordType("customer");
		return nsCustomer;
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	private String createNSUrl() {
		
		Properties entries = netSuiteHelper.getNSProperties();
		String wsUrl = entries.getProperty("baseUrl").concat("?script=")
				.concat(entries.getProperty("scriptForCreateCustomer")).concat("&deploy=")
				.concat(entries.getProperty("deployForCreateCustomer"));
		LOGGER.info("NS Create Customer Url=>" + wsUrl);
		
		return wsUrl;
	}
	
	/**
	 * 
	 * @param response
	 * @return
	 */
	
	private String getJSONFromResponse(Response response){
		String jsonResponse = null;
		try {
			jsonResponse = IOUtils.readStringFromStream((InputStream) response
					.getEntity());

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Failed to get a string represenation of the response", e);
		}
		
		return jsonResponse;
	}
	

}
