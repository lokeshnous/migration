package com.advanceweb.afc.jb.search.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobSearchResultDTO;

/**
 * This class has been created as a service interface for converting to JSON
 * object
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 31ST July 2012
 */

@Service("jsonConverterService")
public class JSONConverterServiceImpl implements JSONConverterService {

	private static final Logger LOGGER = Logger
			.getLogger("JSONConverterServiceImpl.class");

	/**
	 * This method will convert the JobSearchResultDTO to JSON object
	 * 
	 * @param JobSearchResultDTO
	 * @return JSONObject
	 */
	public JSONObject convertToJSON(final JobSearchResultDTO jSResultDTO) {
		final JSONObject jobSrchJsonObj = new JSONObject();
		final JSONArray jsonRows = new JSONArray();

		final List<JobDTO> jobDTOList = jSResultDTO
				.getJobResultList();

		for (JobDTO jobDTO : jobDTOList) {

			final JSONObject jobSrchJson = new JSONObject();

			jobSrchJson.put(MMJBCommonConstants.AD_TEXT,
					MMUtils.isNull(jobDTO.getAdText()));
			jobSrchJson.put(MMJBCommonConstants.CAP_COMPANY,
					MMUtils.isNull(jobDTO.getCompany()));
			jobSrchJson.put(MMJBCommonConstants.JOB_TITLE,
					MMUtils.isNull(jobDTO.getJobTitle()));
			String location = null;
			if (jobDTO.getCity() != null && jobDTO.getState() != null) {
				location = jobDTO.getCity() + MMJBCommonConstants.COMMA
						+ jobDTO.getState();
			} else if (jobDTO.getCity() != null && jobDTO.getState() == null) {
				location = jobDTO.getCity();
			}
			jobSrchJson.put(MMJBCommonConstants.CAP_CITY,
					MMUtils.isNull(location));
			jobSrchJson.put(MMJBCommonConstants.POSTED_DATE,
					convertToReqdDateString(jobDTO.getPostedDate().toString()));
			jobSrchJson.put(MMJBCommonConstants.APPLY_ONLINE,
					jobDTO.getApplyOnline());
			jobSrchJson.put(MMJBCommonConstants.BLIND_AD,
					jobDTO.getBlindAd());
			jobSrchJson.put(MMJBCommonConstants.FACILITY_NAME,
					MMUtils.isNull(jobDTO.getFacilityName()));
			jobSrchJson.put(MMJBCommonConstants.EMAIL_DISPLAY,
					MMUtils.isNull(jobDTO.getEmailDisplay()));
			jobSrchJson.put(MMJBCommonConstants.EMAIL,
					MMUtils.isNull(jobDTO.getEmail()));
			jobSrchJson.put(MMJBCommonConstants.IS_INTERNATIONAL,
					jobDTO.isInternationalJob());
			jobSrchJson.put(MMJBCommonConstants.IS_NATIONAL,
					jobDTO.isNationalJob());
			jobSrchJson.put(MMJBCommonConstants.IS_FEATURED,
					jobDTO.isFeatured());
			jobSrchJson.put(MMJBCommonConstants.JOB_COUNT,
					jobDTO.getJobCount());
			jobSrchJson.put(MMJBCommonConstants.JOB_ID,
					MMUtils.isNull(jobDTO.getJobId()));
			jobSrchJson.put(MMJBCommonConstants.JOB_NUMBER,
					MMUtils.isNull(jobDTO.getJobNumber()));
			jobSrchJson.put(MMJBCommonConstants.JOB_GEO,
					MMUtils.isNull(jobDTO.getJobGeo()));
			jobSrchJson.put(MMJBCommonConstants.JOB_POSITION,
					MMUtils.isNull(jobDTO.getJobPosition()));
			jobSrchJson.put(MMJBCommonConstants.JOB_GEO_0_LATLON,
					MMUtils.isNull(jobDTO.getJobGeo0LatLon()));
			jobSrchJson.put(MMJBCommonConstants.JOB_GEO_1_LATLON,
					MMUtils.isNull(jobDTO.getJobGeo1LatLon()));
			jobSrchJson.put(MMJBCommonConstants.URL_DISPLAY,
					MMUtils.isNull(jobDTO.getUrlDisplay()));
			jobSrchJson.put(MMJBCommonConstants.STATE,
					MMUtils.isNull(jobDTO.getState()));
			jobSrchJson.put(MMJBCommonConstants.URL,
					MMUtils.isNull(jobDTO.getUrl()));
			
			
			jsonRows.add(jobSrchJson);

		}

		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jSResultDTO.getTotalNumSearchResult());
		jobSrchJsonObj.put(MMJBCommonConstants.JSON_ROWS, jsonRows);

		return jobSrchJsonObj;
	}

	
	/**
	 * This method converts the date in string format to required format
	 * for displaying it in the job search result page.
	 * @param dateString
	 * @return String
	 */

	private String convertToReqdDateString(String dateString) {

		Date date = new Date();
		String dateStr = "";
		SimpleDateFormat parser = new SimpleDateFormat(
				MMJBCommonConstants.SOLR_DATE_PATTERN, Locale.US);
		SimpleDateFormat formatter = new SimpleDateFormat(MMJBCommonConstants.REQ_SOLR_DATE_PATTERN, Locale.US);
		try {
			date = parser.parse(dateString);
			dateStr = formatter.format(date);

		} catch (ParseException e) {
			LOGGER.info(e + " Error while converting the date.");
		}

		return dateStr;

	}

}
