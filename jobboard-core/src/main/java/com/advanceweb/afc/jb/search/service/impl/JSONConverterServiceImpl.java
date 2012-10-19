package com.advanceweb.afc.jb.search.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchFacetDTO;
import com.advanceweb.afc.jb.search.service.JSONConverterService;

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

	/**
	 * This method will convert the JobSearchResultDTO to JSON object
	 * 
	 * @param JobSearchResultDTO
	 * @return JSONObject
	 */
	public JSONObject convertToJSON(final JobSearchResultDTO jSResultDTO) {
		final JSONObject jobSrchJsonObj = new JSONObject();
		final JSONArray jsonRows = new JSONArray();

		final List<JobDTO> jobDTOList = jSResultDTO.getResultList();

		for (JobDTO jobDTO : jobDTOList) {
			final JSONObject jobSrchJson = new JSONObject();
			jobSrchJson.put(MMJBCommonConstants.AD_TEXT,
					MMUtils.isNull(jobDTO.getAdText()));
			jobSrchJson.put(MMJBCommonConstants.CAP_COMPANY,
					jobDTO.getCompany());
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
					convertToReqdDateString(jobDTO.getPostedDate()));
			jobSrchJson.put(MMJBCommonConstants.APPLY_ONLINE,
					jobDTO.getApplyOnline());
			jobSrchJson.put(MMJBCommonConstants.BLIND_AD, jobDTO.getBlindAd());
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
			jobSrchJson
					.put(MMJBCommonConstants.JOB_COUNT, jobDTO.getJobCount());
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
			// Newly added fields
			jobSrchJson.put(MMJBCommonConstants.TEMPLATE_ID_STRING,
					jobDTO.getTemplateId());
			jobSrchJson.put(MMJBCommonConstants.PACKAGE_NAME_STRING,
					MMUtils.isNull(jobDTO.getPackageName()));
			jobSrchJson.put(MMJBCommonConstants.IS_PREMIUM_STRING,
					jobDTO.getIsPremium());
			jobSrchJson.put(MMJBCommonConstants.IS_UNIVERSAL_GEO_STRING,
					MMUtils.isNull(String.valueOf(jobDTO.isUniversalGeo())));
			jobSrchJson.put(MMJBCommonConstants.HIDE_CITY_STRING,
					MMUtils.isNull(String.valueOf(jobDTO.getHideCity())));
			jobSrchJson.put(MMJBCommonConstants.HIDE_STATE_STRING,
					MMUtils.isNull(String.valueOf(jobDTO.getHideState())));
			jobSrchJson.put(MMJBCommonConstants.HIDE_POSCODE_STRING,
					MMUtils.isNull(String.valueOf(jobDTO.getHidePostcode())));
			jobSrchJson.put(MMJBCommonConstants.HIDE_COUNTRY_STRING,
					MMUtils.isNull(String.valueOf(jobDTO.getHideCountry())));
			jobSrchJson.put(MMJBCommonConstants.COUNTRY,
					MMUtils.isNull(jobDTO.getCountry()));

			jsonRows.add(jobSrchJson);

		}

		// Get the refine results along with the job count
		fetchRefineResults(jSResultDTO.getFacetMap(), jobSrchJsonObj);

		// Get the location region list
		getLocationRegionResults(jSResultDTO.getFacetMap(), jobSrchJsonObj);

		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jSResultDTO.getResultCount());
		jobSrchJsonObj.put(MMJBCommonConstants.JSON_ROWS, jsonRows);

		return jobSrchJsonObj;
	}

	/**
	 * This method will convert the ResumeSearchResultDTO to JSON object
	 * 
	 * @param ResumeSearchResultDTO
	 * @return JSONObject
	 */
	public JSONObject convertToJSONForResume(
			final ResumeSearchResultDTO resumeSearchResultDTO) {

		final JSONObject jobSrchJsonObj = new JSONObject();
		// final JSONArray jsonRows = new JSONArray();
		// final List<ResumeDTO> jobDTOList =
		// resumeSearchResultDTO.getResultList();

		return jobSrchJsonObj;
	}

	/**
	 * This method converts the date in string format to required format for
	 * displaying it in the job search result page.
	 * 
	 * @param dateString
	 * @return String
	 */

	private String convertToReqdDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				MMJBCommonConstants.JSON_DATE_FORMAT, Locale.US);
		return formatter.format(date);
	}

	/**
	 * This method retrieves the Refine Results data and updates the JSONObject
	 * 
	 * @param searchFacetMap
	 * @param jobSrchJsonObj
	 */
	private void fetchRefineResults(
			Map<String, List<SearchFacetDTO>> searchFacetMap,
			JSONObject jobSrchJsonObj) {
		final Map<String, List<SearchFacetDTO>> searchFacetDTOMap = searchFacetMap;

		// Get the list of cities along with the job count
		List<String> cityDisplayList = generateRefineResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_CITY));

		// Get the list of states along with the job count
		List<String> stateDisplayList = generateRefineResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_STATE));

		// Get the list of Employers along with the job count
		List<String> employerDisplayList = generateRefineResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_COMPANY));

		jobSrchJsonObj.put(MMJBCommonConstants.CITY, cityDisplayList);
		jobSrchJsonObj.put(MMJBCommonConstants.STATE, stateDisplayList);
		jobSrchJsonObj.put(MMJBCommonConstants.COMPANY, employerDisplayList);

	}

	/**
	 * This method provides the facetList along with the count of jobs in each
	 * facet
	 * 
	 * @param facetList
	 * @return displayFacetList
	 */
	private List<String> generateRefineResults(List<SearchFacetDTO> facetList) {
		List<String> displayFacetList = new ArrayList<String>();
		String displayFacet = MMJBCommonConstants.EMPTY;
		if (null != facetList) {
			for (SearchFacetDTO dto : facetList) {
				if (null != dto
						&& MMJBCommonConstants.ZERO_INT != dto.getCount()) {
					displayFacet = dto.getFacetValue();
					displayFacet = displayFacet
							.concat(MMJBCommonConstants.SPACE_OPN_BRCKT);
					displayFacet = displayFacet.concat(String.valueOf(dto
							.getCount()));
					displayFacet = displayFacet
							.concat(MMJBCommonConstants.CLSG_BRCKT);
					displayFacetList.add(displayFacet);
				}
			}
		}
		return displayFacetList;
	}

	/**
	 * This method is used to convert the ResumeDTOList coming from DB to JSON
	 * object.
	 * 
	 * @param List
	 *            <ResumeDTO>
	 * @return JSONObject
	 */
	public JSONObject convertToJSONForResumeFromDB(List<ResumeDTO> resumeDTOList) {

		final JSONObject jobSrchJsonObj = new JSONObject();
		final JSONArray jsonRows = new JSONArray();

		for (ResumeDTO resumeDTO : resumeDTOList) {

			final JSONObject jobSrchJson = new JSONObject();

			jobSrchJson.put(MMJBCommonConstants.UPLOAD_RESUME_ID,
					resumeDTO.getUploadResumeId());
			jobSrchJson.put(MMJBCommonConstants.PUBLISH_RESUME_ID,
					resumeDTO.getPublishResumeId());
			jobSrchJson.put(MMJBCommonConstants.RESUME_DESIRED_POSTION,
					MMUtils.isNull(resumeDTO.getResumeName()));
			jobSrchJson.put(MMJBCommonConstants.APPLICANT_NAME,
					resumeDTO.getFullName());

			/*
			 * String location = null; if (resumeDTO.getCity() != null &&
			 * resumeDTO.getState() != null) { location = resumeDTO.getCity() +
			 * MMJBCommonConstants.COMMA + resumeDTO.getState(); } else if
			 * (resumeDTO.getCity() != null && resumeDTO.getState() == null) {
			 * location = resumeDTO.getCity(); }
			 */
			jobSrchJson.put(MMJBCommonConstants.LOCATION,
					MMUtils.isNull(resumeDTO.getState()));
			jobSrchJson.put(MMJBCommonConstants.EXPERIENCE,
					resumeDTO.getExperience());
			jobSrchJson.put(MMJBCommonConstants.EMPLOYMENT_TYPE,
					resumeDTO.getEmploymentType());
			jobSrchJson.put(MMJBCommonConstants.RELOCATE, "Yes");
			jobSrchJson.put(MMJBCommonConstants.POSTED_DT,
					convertToReqdDateString(resumeDTO.getPostDt()));

			jsonRows.add(jobSrchJson);

		}

		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				resumeDTOList.size());
		jobSrchJsonObj.put(MMJBCommonConstants.JSON_ROWS, jsonRows);

		return jobSrchJsonObj;
	}

	// Data to get location region results
	private void getLocationRegionResults(
			Map<String, List<SearchFacetDTO>> searchFacetMap,
			JSONObject jobSrchJsonObj) {

		final Map<String, List<SearchFacetDTO>> searchFacetDTOMap = searchFacetMap;

		// Get the list of cities along with the job count
		List<String> areaList = generateRegionResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_AREA));

		jobSrchJsonObj.put(MMJBCommonConstants.AREA, areaList);
	}

	private List<String> generateRegionResults(List<SearchFacetDTO> facetList) {
		List<String> displayFacetList = new ArrayList<String>();
		String displayFacet = MMJBCommonConstants.EMPTY;
		if (null != facetList) {
			for (SearchFacetDTO dto : facetList) {
				if (null != dto
						&& MMJBCommonConstants.ZERO_INT != dto.getCount()) {
					displayFacet = dto.getFacetValue();
					displayFacet = displayFacet.concat(" ");
					displayFacet = displayFacet
							.concat(MMJBCommonConstants.METRO_AREA);
					displayFacetList.add(displayFacet);
				}
			}
		}
		return displayFacetList;
	}

}
