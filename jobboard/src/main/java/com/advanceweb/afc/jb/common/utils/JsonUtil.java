package com.advanceweb.afc.jb.common.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.netsuite.CustomerDTO;
import com.advanceweb.afc.jb.webservice.CustomerDetailsWSTest;

@SuppressWarnings("unchecked")
public class JsonUtil {
	
	private static final Logger LOGGER = Logger
			.getLogger(JsonUtil.class);

	public static void main(String[] str) {

		// Populating the JobDTO
			//JobDTO jobDTO = JsonUtil.populateObject();
		// Converting into JSON format
			//String objJson = JsonUtil.saveToJson(jobDTO);
		// Retrieving to DTO object
			//jobDTO = (JobDTO) JsonUtil.retrieveFromJson(objJson);
		
		//Test for cutomer
		//CustomerDTO custDTO = new CustomerDTO();
		//custDTO.setInternalID(460460);
		//custDTO.setPhone("121-454-789");
		//custDTO.setRecordType("customer");
		//custDTO.setCompanyName("MyCompany");
		
		//String objJson = JsonUtil.convertToJson(custDTO);
		//objJson = objJson.toLowerCase();
		//LOGGER.info("objJson in Lowercase=>"+objJson);
		
		//CustomerDetailsWSTest customerDetailsWSTest = new CustomerDetailsWSTest();
		//customerDetailsWSTest.updateCustomer((Object)objJson);
		//customerDetailsWSTest.createCustomer(compName, recdType);

	}

	/**
	 * Converting the DTO into JSON string.
	 * 
	 * @param obj
	 * @return String of JSON data
	 */
	public static String convertToJson(Object obj) {

		ObjectMapper mapper = new ObjectMapper();
		// Object jsonString = new Object();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LOGGER.info("JSON data is =>" + jsonString);
		return jsonString;
	}

	/**
	 * Retriving DTO from JSON data
	 * 
	 * @param jsonString
	 * @return
	 */

	public static Object retrieveFromJson(String jsonString) {

		ObjectMapper mapper = new ObjectMapper();
		JobDTO jobDTO = null;
		try {

			jobDTO = mapper.readValue(jsonString, JobDTO.class);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Displaying the DTO attributes
		JsonUtil.displayObject(jobDTO);

		return jobDTO;
	}
	

	/**
	 * Retriving DTO from JSON data
	 * 
	 * @param jsonString
	 * @return
	 */

	public static Object retrieveFromJsonForCustomer(String jsonString) {

		ObjectMapper mapper = new ObjectMapper();
		CustomerDTO custDTO = null;
		try {

			custDTO = mapper.readValue(jsonString, CustomerDTO.class);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return custDTO;
	}
	
	
	/**
	 * Populating values into the JobDTO
	 * 
	 * @return JobDTO object
	 */

	private static JobDTO populateObject() {
		JobDTO jobDTO = new JobDTO();
		jobDTO.setAdText("<b>Assistant Program Manager</b><br />"
				+ "<b>Area of Interest</b> : Rehabilitation Services - OT<br/><b>Position Type</b>"
				+ " : Full Time - Permanent<BR/><br/><b>Recruiter</b> : ");
		jobDTO.setCity("BLR");
		jobDTO.setEmail("nous@nousinfo.com");
		jobDTO.setEmailDisplay("hr@nousinfo.com");
		jobDTO.setJobTitle("Java Developer");
		jobDTO.setJobId("JR001");
		jobDTO.setCompany("NOUS");
		jobDTO.setState("KARNATAKA");
		return jobDTO;

	}

	/**
	 * Displaying the DTO attributes
	 * 
	 * @param jobDTO
	 */

	private static void displayObject(JobDTO jobDTO) {

		LOGGER.info("Ad text is =>" + jobDTO.getAdText());
		LOGGER.info("City is =>" + jobDTO.getCity());
		LOGGER.info("Email is =>" + jobDTO.getEmail());
		LOGGER.info("Email Display is =>" + jobDTO.getEmailDisplay());
		LOGGER.info("Job title is =>" + jobDTO.getJobTitle());
		LOGGER.info("Job Id is =>" + jobDTO.getJobId());
		LOGGER.info("Apply online is =>" + jobDTO.getApplyOnline());
		LOGGER.info("Company is =>" + jobDTO.getCompany());
		LOGGER.info("Facility Name is =>" + jobDTO.getFacilityName());
		LOGGER.info("Job count is =>" + jobDTO.getJobCount());
		LOGGER.info("Job Geo is =>" + jobDTO.getJobGeo());
		LOGGER.info("JobGeo0Laton is =>" + jobDTO.getJobGeo0LatLon());
		LOGGER.info("JobGeo1Laton is =>" + jobDTO.getJobGeo1LatLon());
		LOGGER.info("Job Number is =>" + jobDTO.getJobNumber());
		LOGGER.info("Job Position is =>" + jobDTO.getJobPosition());
		LOGGER.info("Postcode is =>" + jobDTO.getPostCode());
		LOGGER.info("State is =>" + jobDTO.getState());
		LOGGER.info("URL is =>" + jobDTO.getUrl());
		LOGGER.info("URL display is =>" + jobDTO.getUrlDisplay());

	}

}
