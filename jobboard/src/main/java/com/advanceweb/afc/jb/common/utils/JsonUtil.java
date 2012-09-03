package com.advanceweb.afc.jb.common.utils;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.advanceweb.afc.jb.common.JobDTO;

@SuppressWarnings("unchecked")
public class JsonUtil {

	public static void main(String[] str) {

		// Populating the JobDTO
		JobDTO jobDTO = JsonUtil.populateObject();
		// Converting into JSON format
		String objJson = JsonUtil.saveToJson(jobDTO);
		// Retrieving to DTO object
		jobDTO = (JobDTO) JsonUtil.retrieveFromJson(objJson);

	}

	/**
	 * Converting the DTO into JSON string.
	 * 
	 * @param obj
	 * @return String of JSON data
	 */
	public static String saveToJson(Object obj) {

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

		System.out.println("JSON data is =>" + jsonString);
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

			/*
			 * BufferedReader br = new BufferedReader(new
			 * FileReader("d:\\jobs.json")); obj = mapper.readValue(br,
			 * Object.class);
			 */

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

		System.out.println("Ad text is =>" + jobDTO.getAdText());
		System.out.println("City is =>" + jobDTO.getCity());
		System.out.println("Email is =>" + jobDTO.getEmail());
		System.out.println("Email Display is =>" + jobDTO.getEmailDisplay());
		System.out.println("Job title is =>" + jobDTO.getJobTitle());
		System.out.println("Job Id is =>" + jobDTO.getJobId());
		System.out.println("Apply online is =>" + jobDTO.getApplyOnline());
		System.out.println("Company is =>" + jobDTO.getCompany());
		System.out.println("Facility Name is =>" + jobDTO.getFacilityName());
		System.out.println("Job count is =>" + jobDTO.getJobCount());
		System.out.println("Job Geo is =>" + jobDTO.getJobGeo());
		System.out.println("JobGeo0Laton is =>" + jobDTO.getJobGeo0LatLon());
		System.out.println("JobGeo1Laton is =>" + jobDTO.getJobGeo1LatLon());
		System.out.println("Job Number is =>" + jobDTO.getJobNumber());
		System.out.println("Job Position is =>" + jobDTO.getJobPosition());
		System.out.println("Postcode is =>" + jobDTO.getPostCode());
		System.out.println("State is =>" + jobDTO.getState());
		System.out.println("URL is =>" + jobDTO.getUrl());
		System.out.println("URL display is =>" + jobDTO.getUrlDisplay());

	}

}
