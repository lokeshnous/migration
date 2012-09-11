package com.advanceweb.afc.jb.netsuite.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.cxf.helpers.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.JsonUtil;
import com.advanceweb.afc.jb.netsuite.NSCustomer;
import com.advanceweb.afc.jb.netsuite.NetSuiteHelper;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.netsuite.service.NetSuiteMethod;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;


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

	private static final String RECORD_TYPE = "customer";
	private static final String IS_PERSION_STRING = "isperson";
	private static final String IS_PERSON_VAL = "T";

	private static final String BASE_URL_STRING = "baseUrl";
	private static final String SCRIPT_STRING = "script";
	private static final String DEPLOY_STRING = "deploy";

	private static final String SCRIPT_STRING_CREATE_USER = "scriptForCreateUser";
	private static final String DEPLOY_STRING_CREATE_USER = "deployForCreateUser";

	private static final String SCRIPT_STRING_UPDATE_USER = "scriptForUpdateUser";
	private static final String DEPLOY_STRING_UPDATE_USER = "deployForUpdateUser";
	
	private static final String SCRIPT_STRING_GET_USER_DETAILS = "scriptForGetUserDetails";
	private static final String DEPLOY_STRING_GET_USER_DETAILS = "deployForGetUserDetails";
	
	private static final String IS_FEATURED = "custentityfeaturedemployee";
	

	/**
	 * This method is used to create a customer through NetSuite.
	 * 
	 * @param Object
	 *            of UserDTO
	 * @return Object of UserDTO
	 * @throws JobBoardNetSuiteServiceException
	 */

	public UserDTO createCustomer(UserDTO userDTO)
			throws JobBoardNetSuiteServiceException {

		NSCustomer nsCustomer = getNSCustomerForCreateUser(userDTO);
		String jsonCustomer = JsonUtil.toJson(nsCustomer);
		JSONObject json = getIsPerson(jsonCustomer);

		LOGGER.info("Customer Json sending to NetSuite=>" + json.toString());

		Map<String, String> queryparamMap = createCustomerQueryMap();
		Response response = netSuiteMethod.netSuitePost(queryparamMap,
				json.toString());

		return getCreatedUserDTOFromResponse(response);
	}

	/**
	 * This method is used to edit and update a customer through NetSuite.
	 * 
	 * @param Object
	 *            of UserDTO
	 * @return Object of UserDTO
	 * @throws JobBoardNetSuiteServiceException
	 */

	public UserDTO editCustomer(UserDTO userDTO)
			throws JobBoardNetSuiteServiceException {

		NSCustomer nsCustomer = getNSCustomerForUpdateUser(userDTO);
		String jsonCustomer = JsonUtil.toJson(nsCustomer);
		JSONObject json = getIsPerson(jsonCustomer);

		Map<String, String> queryparamMap = updateCustomerQueryMap();
		Response response = netSuiteMethod.netSuitePost(queryparamMap,
				json.toString());

		return getUpdatedUserDTOFromResponse(response);
	}
	
	
	/**
	 * This method is used to get the customer details through NetSuite.
	 * @param userDTO
	 * @return userDTO
	 * @throws JobBoardNetSuiteServiceException 
	 */
	
	public UserDTO getNSCustomerDetails(UserDTO userDTO) throws JobBoardNetSuiteServiceException{
		
		Map<String, String> queryparamMap = getCustomerDetailsQueryMap();
		String formParameterString = formParameterForGetCustomerDetails(userDTO, queryparamMap);
		Response response = netSuiteMethod.netSuiteGet(queryparamMap,
				formParameterString);

		return getUserDTOFromResponse(response);
		
	}
	
	

	/**
	 * This method id used to create Net suite customer object from User object.
	 * 
	 * @param object
	 *            of userDTO
	 * @return object of NSCustomer
	 */

	private NSCustomer getNSCustomerForCreateUser(UserDTO userDTO) {

		NSCustomer nsCustomer = new NSCustomer();
		nsCustomer.setInternalID(0);
		nsCustomer.setRecordType(RECORD_TYPE);
		nsCustomer.setFirstName(userDTO.getFirstName());
		nsCustomer.setMiddleName(userDTO.getMiddleName());
		nsCustomer.setLastname(userDTO.getLastName());
		nsCustomer.setIsPerson(IS_PERSON_VAL);
		nsCustomer.setAddr1(userDTO.getStreetAddress());
		nsCustomer.setZip(userDTO.getZipCode());
		nsCustomer.setCity(userDTO.getCity());
		nsCustomer.setState(userDTO.getState());
		nsCustomer.setCountry(userDTO.getCountry());
		nsCustomer.setEmail(userDTO.getEmailId());
		nsCustomer.setCompanyName(userDTO.getCompany());
		nsCustomer.setPhone(userDTO.getPrimaryPhone());
		nsCustomer.setAltPhone(userDTO.getSecondaryPhone());
		return nsCustomer;
	}

	/**
	 * This method id used to update Net suite customer object from User object.
	 * 
	 * @param object
	 *            of userDTO
	 * @return object of NSCustomer
	 */

	private NSCustomer getNSCustomerForUpdateUser(UserDTO userDTO) {

		NSCustomer nsCustomer = new NSCustomer();
		nsCustomer.setInternalID(userDTO.getNsCustomerID());
		nsCustomer.setRecordType(RECORD_TYPE);
		nsCustomer.setFirstName(userDTO.getFirstName());
		nsCustomer.setMiddleName(userDTO.getMiddleName());
		nsCustomer.setLastname(userDTO.getLastName());
		nsCustomer.setIsPerson(IS_PERSON_VAL);
		nsCustomer.setAddr1(userDTO.getStreetAddress());
		nsCustomer.setZip(userDTO.getZipCode());
		nsCustomer.setCity(userDTO.getCity());
		nsCustomer.setState(userDTO.getState());
		nsCustomer.setCountry(userDTO.getCountry());
		nsCustomer.setEmail(userDTO.getEmailId());
		nsCustomer.setCompanyName(userDTO.getCompany());
		nsCustomer.setPhone(userDTO.getPrimaryPhone());
		nsCustomer.setAltPhone(userDTO.getSecondaryPhone());
		return nsCustomer;
	}

	/**
	 * This method is used for creating customer query map which will be used
	 * while creation of the WebClient object for communicating with the net
	 * suite. The values will be read from the netSuite.properties file.
	 * 
	 * @returnMap<String, String>
	 */

	private Map<String, String> createCustomerQueryMap() {

		Properties entries = netSuiteHelper.getNSProperties();
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap
				.put(BASE_URL_STRING, entries.getProperty(BASE_URL_STRING));
		queryParamMap.put(SCRIPT_STRING,
				entries.getProperty(SCRIPT_STRING_CREATE_USER));
		queryParamMap.put(DEPLOY_STRING,
				entries.getProperty(DEPLOY_STRING_CREATE_USER));

		return queryParamMap;
	}

	/**
	 * This method is used for updating customer query map which will be used
	 * while creation of the WebClient object for communicating with the net
	 * suite. The values will be read from the netSuite.properties file.
	 * 
	 * @returnMap<String, String>
	 */

	private Map<String, String> updateCustomerQueryMap() {

		Properties entries = netSuiteHelper.getNSProperties();
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap
				.put(BASE_URL_STRING, entries.getProperty(BASE_URL_STRING));
		queryParamMap.put(SCRIPT_STRING,
				entries.getProperty(SCRIPT_STRING_UPDATE_USER));
		queryParamMap.put(DEPLOY_STRING,
				entries.getProperty(DEPLOY_STRING_UPDATE_USER));

		return queryParamMap;
	}
	
	
	/**
	 * This method is used for creating net suite service url map. 
	 * The values will be read from the netSuite.properties file.
	 * @returnMap<String, String>
	 */

	private Map<String, String> getCustomerDetailsQueryMap() {

		Properties entries = netSuiteHelper.getNSProperties();
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap
				.put(BASE_URL_STRING, entries.getProperty(BASE_URL_STRING));
		queryParamMap.put(SCRIPT_STRING,
				entries.getProperty(SCRIPT_STRING_GET_USER_DETAILS));
		queryParamMap.put(DEPLOY_STRING,
				entries.getProperty(DEPLOY_STRING_GET_USER_DETAILS));

		return queryParamMap;
	}
	
	

	/**
	 * This method is used to get the User object from the Net Suite Response
	 * for Creating a Customer. The net suite response will be parsed and
	 * encapsulated in the User object and return it back.
	 * 
	 * @param Object
	 *            of Response
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */

	private UserDTO getCreatedUserDTOFromResponse(Response response)
			throws JobBoardNetSuiteServiceException {
		String jsonResponse = null;
		UserDTO userDTO = new UserDTO();
		try {
			jsonResponse = IOUtils.readStringFromStream((InputStream) response
					.getEntity());
			if (jsonResponse.contains("error")) {
				LOGGER.info("Error occurred while getting the response from NetSuite.");
				throw new JobBoardNetSuiteServiceException(
						"Error occurred while getting the response from NetSuite.");
			} else if (jsonResponse.contains("already exist")) {
				userDTO.setNsStatus("record already exist");
			} else {
				userDTO.setNsCustomerID(Integer.parseInt(jsonResponse
						.replaceAll("\"", " ").trim()));
				userDTO.setNsStatus("true");
			}

		} catch (IOException e) {
			throw new JobBoardNetSuiteServiceException(
					"Failed to get a string represenation of the NetSuite response"
							+ e);
		}

		return userDTO;
	}

	/**
	 * This method is used to get the User object from the Net Suite Response
	 * for updating a Customer. The net suite response will be parsed and
	 * encapsulated in the User object and return it back.
	 * 
	 * @param Object
	 *            of Response
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */

	private UserDTO getUpdatedUserDTOFromResponse(Response response)
			throws JobBoardNetSuiteServiceException {
		String jsonResponse = null;
		UserDTO userDTO = new UserDTO();
		try {
			jsonResponse = IOUtils.readStringFromStream((InputStream) response
					.getEntity());
			if (jsonResponse.contains("error")) {
				LOGGER.info("Error occurred while record updation in NetSuite.");
				throw new JobBoardNetSuiteServiceException(
						"Error occurred while record updation in NetSuite.");
			} else if (jsonResponse.contains("updated")) {
				userDTO.setNsStatus("true");
			}

		} catch (IOException e) {
			throw new JobBoardNetSuiteServiceException(
					"Failed to get a string represenation of the NetSuite response"
							+ e);
		}

		return userDTO;
	}
	
	/**
	 * This method is used to get the User object from the Net Suite Response
	 * for updating a Customer. The net suite response will be parsed and
	 * encapsulated in the User object and return it back.
	 * 
	 * @param Object
	 *            of Response
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */

	private UserDTO getUserDTOFromResponse(Response response)
			throws JobBoardNetSuiteServiceException {
		String jsonResponse = null;
		UserDTO userDTO = new UserDTO();
		
		try {
			jsonResponse = IOUtils.readStringFromStream((InputStream) response
					.getEntity());
			
			
			if (jsonResponse.contains("error")) {
				LOGGER.info("Error occurred while record updation in NetSuite.");
				throw new JobBoardNetSuiteServiceException(
						"Error occurred while record updation in NetSuite.");
			} else{
				
				try {
					org.codehaus.jettison.json.JSONObject jsonObject  = new org.codehaus.jettison.json.JSONObject(jsonResponse);
					LOGGER.info("IS_FEATURED===>"+jsonObject.get(IS_FEATURED));
					userDTO.setFeatured(Boolean.parseBoolean(jsonObject.get(IS_FEATURED).toString()));
				} catch (JSONException e) {
					LOGGER.error(e);
				}
				
			}

		} catch (IOException e) {
			throw new JobBoardNetSuiteServiceException(
					"Failed to get a string represenation of the NetSuite response"
							+ e);
		}

		return userDTO;
	}
	
	
	

	/**
	 * This method is used to get the isPersion string from the Json object.
	 * 
	 * @param String
	 *            jsonCustomer
	 * @return JSONObject
	 */

	private JSONObject getIsPerson(String jsonCustomer) {
		JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonCustomer
				.toLowerCase());
		json.put(IS_PERSION_STRING, json.getString(IS_PERSION_STRING)
				.toUpperCase());
		return json;
	}
	
	/**
	 * This method created the parameter url for netSuite GET request.
	 * @param object of UserDTO
	 * @param Map<String, String> queryparamMap
	 * @return String
	 */
	
	public String formParameterForGetCustomerDetails(UserDTO userDTO, Map<String, String> queryparamMap){
		
		return "&recordtype="+userDTO.getRecordType()+"&id="+userDTO.getEntityId();
		
	}
	

}
