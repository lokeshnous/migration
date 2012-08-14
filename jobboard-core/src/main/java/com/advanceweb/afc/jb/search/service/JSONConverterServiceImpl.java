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

import com.advanceweb.afc.jb.common.util.CheckNullUtil;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.search.service.JSONConverterService;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchDTO;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;

/**
 * This class has been created as a service interface for converting to JSON
 * object
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 31ST July 2012
 */

@Service("jSONConverterService")
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

		final List<JobSearchDTO> jobSearchDTOList = jSResultDTO
				.getSearchResultList();

		for (JobSearchDTO jobSrchDTO : jobSearchDTOList) {

			final JSONObject jobSrchJson = new JSONObject();

			jobSrchJson.put(MMJBCommonConstants.AD_TEXT,
					CheckNullUtil.isNull(jobSrchDTO.getAdText()));
			jobSrchJson.put(MMJBCommonConstants.CAP_COMPANY,
					CheckNullUtil.isNull(jobSrchDTO.getCompany()));
			jobSrchJson.put(MMJBCommonConstants.JOB_TITLE,
					CheckNullUtil.isNull(jobSrchDTO.getJobTitle()));
			jobSrchJson.put(MMJBCommonConstants.CAP_CITY,
					CheckNullUtil.isNull(jobSrchDTO.getCity()));
			jobSrchJson.put(MMJBCommonConstants.POSTED_DATE,
					convertToReqdDateString(jobSrchDTO.getPostedDate()
							.toString()));
			jobSrchJson.put(MMJBCommonConstants.APPLY_ONLINE,
					jobSrchDTO.getApplyOnline());
			jobSrchJson.put(MMJBCommonConstants.BLIND_AD,
					jobSrchDTO.getBlindAd());
			jobSrchJson.put(MMJBCommonConstants.FACILITY_NAME,
					CheckNullUtil.isNull(jobSrchDTO.getFacilityName()));
			jobSrchJson.put(MMJBCommonConstants.EMAIL_DISPLAY,
					CheckNullUtil.isNull(jobSrchDTO.getEmailDisplay()));
			jobSrchJson.put(MMJBCommonConstants.EMAIL,
					CheckNullUtil.isNull(jobSrchDTO.getEmail()));
			jobSrchJson.put(MMJBCommonConstants.IS_INTERNATIONAL,
					jobSrchDTO.isInternationalJob());
			jobSrchJson.put(MMJBCommonConstants.IS_NATIONAL,
					jobSrchDTO.isNationalJob());
			jobSrchJson.put(MMJBCommonConstants.IS_FEATURED,
					jobSrchDTO.isFeatured());
			jobSrchJson.put(MMJBCommonConstants.JOB_COUNT,
					jobSrchDTO.getJobCount());
			jobSrchJson.put(MMJBCommonConstants.JOB_ID,
					CheckNullUtil.isNull(jobSrchDTO.getJobId()));
			jobSrchJson.put(MMJBCommonConstants.JOB_NUMBER,
					CheckNullUtil.isNull(jobSrchDTO.getJobNumber()));
			jobSrchJson.put(MMJBCommonConstants.JOB_GEO,
					CheckNullUtil.isNull(jobSrchDTO.getJobGeo()));
			jobSrchJson.put(MMJBCommonConstants.JOB_POSITION,
					CheckNullUtil.isNull(jobSrchDTO.getJobPosition()));
			jobSrchJson.put(MMJBCommonConstants.JOB_GEO_0_LATLON,
					CheckNullUtil.isNull(jobSrchDTO.getJobGeo0LatLon()));
			jobSrchJson.put(MMJBCommonConstants.JOB_GEO_1_LATLON,
					CheckNullUtil.isNull(jobSrchDTO.getJobGeo1LatLon()));
			jobSrchJson.put(MMJBCommonConstants.URL_DISPLAY,
					CheckNullUtil.isNull(jobSrchDTO.getUrlDisplay()));
			jobSrchJson.put(MMJBCommonConstants.STATE,
					CheckNullUtil.isNull(jobSrchDTO.getState()));
			jobSrchJson.put(MMJBCommonConstants.URL,
					CheckNullUtil.isNull(jobSrchDTO.getUrl()));
			
			
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