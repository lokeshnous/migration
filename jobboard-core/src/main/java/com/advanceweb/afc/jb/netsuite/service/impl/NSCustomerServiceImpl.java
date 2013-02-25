/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.netsuite.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.cxf.helpers.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.JsonUtil;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.netsuite.NSCustomer;
import com.advanceweb.afc.jb.netsuite.NetSuiteHelper;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.netsuite.service.NetSuiteMethod;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

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

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(NSCustomerServiceImpl.class);

	/** The net suite method. */
	@Autowired
	private NetSuiteMethod netSuiteMethod;

	/** The net suite helper. */
	@Autowired
	private NetSuiteHelper netSuiteHelper;

	/** The Constant RECORD_TYPE. */
	private static final String RECORD_TYPE = "customer";
	
	/** The Constant IS_PERSION_STRING. */
	private static final String IS_PERSION_STRING = "isperson";
	
	/** The Constant IS_PERSON_VAL. */
	private static final String IS_PERSON_VAL = "F";
	
	/** The Constant COMPANY_NAME. */
	private static final String COMPANY_NAME = "companyname";
	
	/** The Constant COUNTRY. */
	private static final String COUNTRY = "country";
	
	/** The Constant USA. */
	private static final String USA = "USA";

	/** The Constant BASE_URL_STRING. */
	private static final String BASE_URL_STRING = "baseUrl";
	
	/** The Constant SCRIPT_STRING. */
	private static final String SCRIPT_STRING = "script";
	
	/** The Constant DEPLOY_STRING. */
	private static final String DEPLOY_STRING = "deploy";

	/** The Constant SCRIPT_STRING_CREATE_USER. */
	private static final String SCRIPT_STRING_CREATE_USER = "scriptForCreateUser";
	
	/** The Constant DEPLOY_STRING_CREATE_USER. */
	private static final String DEPLOY_STRING_CREATE_USER = "deployForCreateUser";

	/** The Constant SCRIPT_STRING_UPDATE_USER. */
	private static final String SCRIPT_STRING_UPDATE_USER = "scriptForUpdateUser";
	
	/** The Constant DEPLOY_STRING_UPDATE_USER. */
	private static final String DEPLOY_STRING_UPDATE_USER = "deployForUpdateUser";

	/** The Constant SCRIPT_STRING_GET_USER_DETAILS. */
	private static final String SCRIPT_STRING_GET_USER_DETAILS = "scriptForGetUserDetails";
	
	/** The Constant DEPLOY_STRING_GET_USER_DETAILS. */
	private static final String DEPLOY_STRING_GET_USER_DETAILS = "deployForGetUserDetails";

	/** The Constant SCRIPT_STRING_GET_CUST_PACKAGES. */
	private static final String SCRIPT_STRING_GET_CUST_PACKAGES = "scriptForGetCustomerPackages";
	
	/** The Constant DEPLOY_STRING_GET_CUST_PACKAGES. */
	private static final String DEPLOY_STRING_GET_CUST_PACKAGES = "deployForGetCustomerPackages";
	
	/** The Constant SCRIPT_STRING_GET_FE_DATES. */
	private static final String SCRIPT_STRING_GET_FE_DATES = "scriptForGetFeatureDates";
	
	/** The Constant DEPLOY_STRING_GET_FE_DATES. */
	private static final String DEPLOY_STRING_GET_FE_DATES = "deployForGetFeatureDates";
	
	/** The Constant AMP_RECORD_TYPE. */
	private static final String AMP_RECORD_TYPE = "&recordtype=";

	/** The Constant AMP_ID. */
	private static final String AMP_ID = "&id=";
	
	/** The Constant ERROR_STRING. */
	private static final String ERROR_STRING = "error";

	/** The Constant RECORD_ALREADY_EXIST_MSG. */
	private static final String RECORD_ALREADY_EXIST_MSG = "record already exist";
	
	/** The Constant TRUE_STRING. */
	private static final String TRUE_STRING = "true";

	/** The Constant IS_INVOICE_ENABLED. */
	private static final String IS_INVOICE_ENABLED = "custentityinvoiceenabled";

	/** The Constant PACKAGE_TYPE_STRING. */
	private static final String PACKAGE_TYPE_STRING = "custentitypackagetype";
	
	/** The Constant NAME_STRING. */
	private static final String NAME_STRING = "name";

	/** The Constant CONTACT_ROLES_STRING. */
	private static final String CONTACT_ROLES_STRING = "contactroles";
	
	/** The Constant EMAIL_STRING. */
	private static final String EMAIL_STRING = "email";
	
	/** The Constant LEFT_SQ_BRKT_STRING. */
	private static final String LEFT_SQ_BRKT_STRING = "[";
	
	/** The Constant RIGHT_SQ_BRKT_STRING. */
	private static final String RIGHT_SQ_BRKT_STRING = "]";
	
	/** The Constant DOUBLE_QUOTE_STRING. */
	private static final String DOUBLE_QUOTE_STRING = "\"";
	
	/** The Constant NS_ERROR. */
	private static final String NS_ERROR = "Failed to get a string represenation of the NetSuite response";
	
	/** The Constant NS_ERROR2. */
	private static final String NS_ERROR2 = "Error occurred while record updation in NetSuite.";

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
		JSONObject json = getIsPerson(jsonCustomer, userDTO);

		LOGGER.debug("Customer Json sending to NetSuite=>" + json.toString());

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

		JSONObject json = getIsPerson(jsonCustomer, userDTO);

		Map<String, String> queryparamMap = updateCustomerQueryMap();
		LOGGER.debug("Json Data sent to NS is " + json);
		Response response = netSuiteMethod.netSuitePost(queryparamMap,
				json.toString());

		return getUpdatedUserDTOFromResponse(response);
	}

	/**
	 * This method is used to get the customer details through NetSuite.
	 * 
	 * @param userDTO
	 * @return userDTO
	 * @throws JobBoardNetSuiteServiceException
	 */

	public UserDTO getNSCustomerDetails(UserDTO userDTO)
			throws JobBoardNetSuiteServiceException {

		Map<String, String> queryparamMap = getCustomerDetailsQueryMap();
		String formParameterString = formParameterForGetCustomerDetails(
				userDTO, queryparamMap);
		Response response = netSuiteMethod.netSuiteGet(queryparamMap,
				formParameterString);

		return getUserDTOFromResponse(response);

	}
	
	/**
	 * This method is used to get the Featured employer package start/End dates.
	 * 
	 * @param userDTO
	 * @return userDTO
	 * @throws JobBoardNetSuiteServiceException
	 */

	public UserDTO getNSFeatureDates(UserDTO userDTO)
			throws JobBoardNetSuiteServiceException {

		Map<String, String> queryparamMap = getFeatureDatesQueryMap();
		String formParameterString = formParameterForGetCustomerDetails(
				userDTO, queryparamMap);
		Response response = netSuiteMethod.netSuiteGet(queryparamMap,
				formParameterString);
		return getFEDateFromResponse(response);

	}

	/**
	 * This method is used to get the customer purchased packages through
	 * NetSuite.
	 * 
	 * @param userDTO
	 * @return List<String>
	 * @throws JobBoardNetSuiteServiceException
	 */

	public List<String> getNSCustomerPackages(UserDTO userDTO)
			throws JobBoardNetSuiteServiceException {

		Map<String, String> queryparamMap = getCustomerPackagesQueryMap();
		String formParameterString = formParameterForGetCustomerDetails(
				userDTO, queryparamMap);
		Response response = netSuiteMethod.netSuiteGet(queryparamMap,
				formParameterString);

		return getPackagesFromResponse(response);

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
	 * This method is used for creating net suite service url map. The values
	 * will be read from the netSuite.properties file.
	 * 
	 * @returnMap<String, String>
	 */

	private Map<String, String> getFeatureDatesQueryMap() {

		Properties entries = netSuiteHelper.getNSProperties();
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap
				.put(BASE_URL_STRING, entries.getProperty(BASE_URL_STRING));
		queryParamMap.put(SCRIPT_STRING,
				entries.getProperty(SCRIPT_STRING_GET_FE_DATES));
		queryParamMap.put(DEPLOY_STRING,
				entries.getProperty(DEPLOY_STRING_GET_FE_DATES));

		return queryParamMap;
	}

	/**
	 * This method is used for creating net suite service url map. The values
	 * will be read from the netSuite.properties file.
	 * 
	 * @returnMap<String, String>
	 */

	private Map<String, String> getCustomerPackagesQueryMap() {

		Properties entries = netSuiteHelper.getNSProperties();
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap
				.put(BASE_URL_STRING, entries.getProperty(BASE_URL_STRING));
		queryParamMap.put(SCRIPT_STRING,
				entries.getProperty(SCRIPT_STRING_GET_CUST_PACKAGES));
		queryParamMap.put(DEPLOY_STRING,
				entries.getProperty(DEPLOY_STRING_GET_CUST_PACKAGES));

		return queryParamMap;
	}

	/**
	 * This method is used for creating net suite service url map. The values
	 * will be read from the netSuite.properties file.
	 * 
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
			LOGGER.debug("jsonResponse: "+jsonResponse);
			if (jsonResponse.contains(ERROR_STRING)) {
				LOGGER.error("Error occurred while getting the response from NetSuite.");
				throw new JobBoardNetSuiteServiceException(
						"Error occurred while getting the response from NetSuite.");
			} else if (jsonResponse.contains("already exist")) {
				userDTO.setNsStatus(RECORD_ALREADY_EXIST_MSG);
			} else {
				userDTO.setNsCustomerID(Integer.parseInt(jsonResponse
						.replaceAll(DOUBLE_QUOTE_STRING, " ").trim()));
				userDTO.setNsStatus(TRUE_STRING);
			}

		} catch (IOException e) {
			throw new JobBoardNetSuiteServiceException(NS_ERROR + e);
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
			LOGGER.debug("jSON response=>" + jsonResponse);
			if (jsonResponse.contains(ERROR_STRING)) {
				LOGGER.error("Error occurred while record updation in Net Suite.");
				throw new JobBoardNetSuiteServiceException(
						"Error occurred while updating record in NetSuite.");
			} else if (jsonResponse.contains("updated")) {
				userDTO.setNsStatus(TRUE_STRING);
			}

		} catch (IOException e) {
			throw new JobBoardNetSuiteServiceException(NS_ERROR + e);
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
			LOGGER.debug("jsonResponse: "+jsonResponse);
			if (jsonResponse.contains(ERROR_STRING)) {
				LOGGER.error(NS_ERROR2);
				throw new JobBoardNetSuiteServiceException(
						NS_ERROR2);
			} else {

				try {
					org.codehaus.jettison.json.JSONObject jsonObject = new org.codehaus.jettison.json.JSONObject(
							jsonResponse);
					userDTO = populateUserDTO(userDTO, jsonObject);
				} catch (JSONException e) {
					LOGGER.error(e);
				}

			}

		} catch (IOException e) {
			throw new JobBoardNetSuiteServiceException(NS_ERROR + e);
		}

		return userDTO;
	}

	/**
	 * This method is used to get the User object from the Net Suite Response
	 * for updating a Customer. The net suite response will be parsed and
	 * encapsulated in the User object and return it back containing start and
	 * end date of featured package.
	 * 
	 * @param Object
	 *            of Response
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */

	private UserDTO getFEDateFromResponse(Response response)
			throws JobBoardNetSuiteServiceException {
		String strResponse = null;
		UserDTO userDTO = new UserDTO();

		try {
			strResponse = IOUtils.readStringFromStream((InputStream) response
					.getEntity());
			LOGGER.debug("jsonResponse: "+strResponse);
			if (strResponse.contains(ERROR_STRING)) {
				LOGGER.error(NS_ERROR2);
				throw new JobBoardNetSuiteServiceException(
						NS_ERROR2);
			} else {
				try {
					String strArray[] = strResponse.split(",");
					if (strArray.length > 2) {
						userDTO.setFeaturedStartDate(CommonUtil.convertToDate(strArray[1]
								.substring(strArray[1].lastIndexOf(':')+1).trim()));
						userDTO.setFeaturedEndDate(CommonUtil.convertToDate(strArray[2]
								.substring(strArray[2].lastIndexOf(':')+1).trim()));
					}
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}

		} catch (IOException e) {
			throw new JobBoardNetSuiteServiceException(NS_ERROR + e);
		}

		return userDTO;
	}
	
	/**
	 * This method is used to get the Job Post Package object from the Net Suite
	 * Response. The Net Suite response will be parsed and encapsulated in the
	 * List of String and returned back.
	 * 
	 * @param Object
	 *            of Response
	 * @return List<String>
	 * @throws JobBoardServiceException
	 */

	private List<String> getPackagesFromResponse(Response response)
			throws JobBoardNetSuiteServiceException {
		String stringResponse = null;
		List<String> listPackages = new ArrayList<String>();

		try {
			stringResponse = IOUtils
					.readStringFromStream((InputStream) response.getEntity());
			LOGGER.debug("jsonResponse: "+stringResponse);
			if (stringResponse.contains(LEFT_SQ_BRKT_STRING)) {
				stringResponse = stringResponse.replace(LEFT_SQ_BRKT_STRING,
						MMJBCommonConstants.EMPTY);
				stringResponse = stringResponse.replace(RIGHT_SQ_BRKT_STRING,
						MMJBCommonConstants.EMPTY);
			}
				List<String> listResponse = new ArrayList<String>(
						Arrays.asList(stringResponse.split(",")));

				for (String pakageId : listResponse) {
					listPackages.add(pakageId.replace(DOUBLE_QUOTE_STRING, "")
							.trim());
				}
			LOGGER.debug("List of packages purchased by customer"
					+ listPackages);
		} catch (Exception e) {
			throw new JobBoardNetSuiteServiceException(NS_ERROR + e);
		}

		return listPackages;
	}
	
	/**
	 * This method is used to populate USerDTO from JSON object.
	 * 
	 * @param userDTO
	 * @param jsonObject
	 * @return UserDTO Object
	 * @throws JSONException
	 */
	private UserDTO populateUserDTO(UserDTO userDTO,
			org.codehaus.jettison.json.JSONObject jsonObject)
			throws JSONException {
		if(jsonObject.has(IS_INVOICE_ENABLED)){
		userDTO.setInvoiceEnabled(Boolean.parseBoolean(jsonObject.get(
				IS_INVOICE_ENABLED).toString()));
		}
		try {
			setPackageTypeInUserDTO(userDTO, jsonObject);
			List<String> emailList = setContactEmailList(jsonObject);
			userDTO.setEmailList(emailList);
		} catch (Exception e) {
			LOGGER.error("Exception occurred while getting package type and email from Netsuite customer object "
					+ e);
		}

		LOGGER.debug("IS_INVOICE_ENABLED===>"
				+ jsonObject.get(IS_INVOICE_ENABLED));

		return userDTO;
	}

	/**
	 * This method is used to get the Email address from the netsuite customer
	 * object and returning a list of emails.
	 * 
	 * @param jsonObject
	 * @return List<String> emailList
	 * @throws JSONException
	 */

	private List<String> setContactEmailList(
			org.codehaus.jettison.json.JSONObject jsonObject)
			throws JSONException {
		JSONArray jsonArray = new JSONArray();
		List<String> emailList = new ArrayList<String>();
		if (jsonObject.has(CONTACT_ROLES_STRING)) {
			jsonArray = (JSONArray) jsonObject.get(CONTACT_ROLES_STRING);
		}
		for (int index = 0; index < jsonArray.length(); index++) {
			org.codehaus.jettison.json.JSONObject innerJsonObj = jsonArray
					.getJSONObject(index);
			if (innerJsonObj.has(EMAIL_STRING)) {
				emailList.add(innerJsonObj.getString(EMAIL_STRING));
			}
		}
		
		LOGGER.debug("Email List is " + emailList);
		return emailList;
	}

	/**
	 * This method is used for setting the package type into the UserDTO.
	 * 
	 * @param userDTO
	 * @param jsonObject
	 * @throws JSONException
	 */

	private void setPackageTypeInUserDTO(UserDTO userDTO,
			org.codehaus.jettison.json.JSONObject jsonObject)
			throws JSONException {
		if (jsonObject.has(PACKAGE_TYPE_STRING)) {
			org.codehaus.jettison.json.JSONObject jsonObj = (org.codehaus.jettison.json.JSONObject) jsonObject
					.get(PACKAGE_TYPE_STRING);
			if (jsonObj.has(NAME_STRING)) {
				LOGGER.debug("PACKAGE TYP IS " + jsonObj.get(NAME_STRING));
				userDTO.setPackageName(jsonObj.get(NAME_STRING).toString());
			}
		}
	}

	/**
	 * This method is used to get the isPersion string from the Json object.
	 * 
	 * @param String
	 *            jsonCustomer
	 * @return JSONObject
	 */

	private JSONObject getIsPerson(String jsonCustomer, UserDTO userDTO) {
		JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonCustomer
				.toLowerCase(Locale.ENGLISH));
		json.put(IS_PERSION_STRING, json.getString(IS_PERSION_STRING)
				.toUpperCase());
		json.put(COMPANY_NAME, userDTO.getCompany());
		json.put(COUNTRY, userDTO.getCountry().equals(USA) ? userDTO
				.getCountry().substring(0, 2).toUpperCase() : userDTO
				.getCountry().toUpperCase());
		return json;
	}

	/**
	 * This method created the parameter url for netSuite GET request.
	 * 
	 * @param object
	 *            of UserDTO
	 * @param Map
	 *            <String, String> queryparamMap
	 * @return String
	 */

	public String formParameterForGetCustomerDetails(UserDTO userDTO,
			Map<String, String> queryparamMap) {

		return AMP_RECORD_TYPE + userDTO.getRecordType() + AMP_ID
				+ userDTO.getEntityId();

	}

	/**
	 * This method is used to convert a String date to Date object into the
	 * required format.
	 * 
	 * @param date
	 * @return Date object
	 */

	public Date convertToDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH);
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return convertedDate;
	}

}
