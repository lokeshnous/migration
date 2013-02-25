/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


@SuppressWarnings("unchecked")
public class JsonUtil {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JsonUtil.class);

	/**
	 * Converting the DTO into JSON string.
	 * 
	 * @param obj
	 * @return String of JSON data
	 */
	public static String toJson(Object obj) {

		ObjectMapper mapper = new ObjectMapper();
		// Object jsonString = new Object();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);

		} catch (JsonGenerationException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}

		
		return jsonString;
	}

	/**
	 * Retriving DTO from JSON data
	 * 
	 * @param jsonString
	 * @return
	 */

	public static <T> Object toObject(String jsonString, Class<T> clazz) {

		ObjectMapper mapper = new ObjectMapper();
		Object obj = null;
		try {

			obj = mapper.readValue(jsonString, clazz);

		} catch (JsonParseException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}

		// Displaying the DTO attributes
		//JsonUtil.displayObject(obj);

		return obj;
	}
	

	/**
	 * Populating values into the JobDTO
	 * 
	 * @return JobDTO object
	 */

	/*private static JobDTO populateObject() {
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

	}*/

	/**
	 * Displaying the DTO attributes
	 * 
	 * @param jobDTO
	 *//*

	private static void displayObject(Object obj) {

		JobDTO jobDTO = (JobDTO)obj;
		
		LOGGER.info("Ad text iss =>" + jobDTO.getAdText());
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

	}*/

}
