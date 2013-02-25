/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchFacetDTO;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;

/**
 * <code>JobsController</code>The controller get the list of jobs with the job
 * title links which refers to job view page, and it containing absolute path
 * which helps to index the jobs for search engine optimization.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 02 NOV 2012
 * 
 */

@Controller
@RequestMapping("/jobs")
public class JobsController extends AbstractController {

	/** The Constant NO_OF_PAGES. */
	private static final String NO_OF_PAGES = "noOfPages";

	/** The Constant JOBBOARD_SEARCHRESULTS_BYJOBTITLE. */
	private static final String JOBBOARD_SEARCHRESULTS_BYJOBTITLE = "jobboardsearchresultsbyjobtitles";

	/** The Constant JOB_SEARCH_RESULT_FORM. */
	private static final String JOB_SEARCH_RESULT_FORM = "jobSearchResultForm";
	
	/** The Constant JOBTITLE_REPLACE_WORD. */
	private static final String JOBTITLE_REPLACE_WORD = "?jobtitle";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JobsController.class);
	
	/** The job search service. */
	@Autowired
	private JobSearchService jobSearchService;

	/** The check session map. */
	@Autowired
	private CheckSessionMap checkSessionMap;

	/** The seo configuration. */
	@Autowired
	@Resource(name = "seoConfiguration")
	private Properties seoConfiguration;

	/**
	 * Fetch the all active jobs and also set the links to get the next or
	 * previous set of searched job titles to index the pages for SEO.
	 * 
	 * @param session
	 * @param desc
	 * @param jobSearchResultForm
	 * @param request
	 * 
	 */
	@RequestMapping(value = "/alljobs", method = RequestMethod.GET)
	public ModelAndView searchAllJobs(HttpSession session,
			JobSearchResultForm jobSearchResultForm, HttpServletRequest request) {
		// clear the session map
		session.removeAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		// Set the search type for SOLR facets
		String searchName = MMJBCommonConstants.KEYWORD_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the keyword as * to get all jobs
		request.setAttribute(SearchParamDTO.KEYWORDS, "*");
		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				searchName, MMJBCommonConstants.POSTED_DT, session, request);

		// set default page value
		int page = 1;
		// set the number of records per page
		int recordsPerPage = MMJBCommonConstants.JOBTITLES_GRID_PAGESIZE;
		int noOfRecords = 0;
		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// fetch the jobs
			jobSearchResultDTO = jobSearchService.jobSearch(
					paramMap, start, rows);
			noOfRecords = (int) jobSearchResultDTO.getResultCount();
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			// convert the results to JSON object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		}
		// set the absolute path for next and previous links to fetch the set of
		// records
		modelAndView.addObject(MMJBCommonConstants.JOBTITLES_NEXT_PAGE_URL,
				request.getRequestURL());
		if (page != 1) {
			modelAndView.addObject(MMJBCommonConstants.JOBTITLES_PREV_PAGE_URL,
					request.getRequestURL());

		}
		// set the number of pages
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.PAGE, page);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(NO_OF_PAGES, noOfPages);
		// Add the SEO details for the page
		addSEODetailsForJobTitlesPage(modelAndView, request,
				PageNames.JOBSEEKER_BROWSE_JOB_TITLES);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_BYJOBTITLE);
		return modelAndView;
	}

	/**
	 * Fetch the jobs by job title using search type and facets of SOLR and also
	 * create a next and previous links to get the set of searched job titles to
	 * index the pages for SEO.
	 * 
	 * @param session
	 * @param desc
	 * @param jobSearchResultForm
	 * @param request
	 * 
	 */
	@RequestMapping(value = "/title/{desc}", method = RequestMethod.GET)
	public ModelAndView searchJobsByJobtitle(HttpSession session,
			@PathVariable("desc") String desc,
			JobSearchResultForm jobSearchResultForm, HttpServletRequest request) {
		// Clear the session map
		session.removeAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		// set the search type for SOLR facets
//		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		String searchName = MMJBCommonConstants.KEYWORD_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String jobTitle = MMUtils.decodeString(desc.trim());
//		request.setAttribute(MMJBCommonConstants.FIRST_FQ_PARAM, jobTitle);
		jobSearchResultForm.setKeywords(jobTitle);
		request.setAttribute(SearchParamDTO.KEYWORDS, jobTitle);

		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				searchName, MMJBCommonConstants.POSTED_DT, session, request);

		// set default page value
		int page = 1;
		// set the number of records per page
		int recordsPerPage = MMJBCommonConstants.JOBTITLES_GRID_PAGESIZE;
		int noOfRecords = 0;
		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// fetch the jobs
			jobSearchResultDTO = jobSearchService.jobSearch(
					paramMap, start, rows);
			noOfRecords = (int) jobSearchResultDTO.getResultCount();
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			// convert the results to JSON object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		}
		// set the absolute path for next and previous links to fetch the set of
		// records
		modelAndView.addObject(MMJBCommonConstants.JOBTITLES_NEXT_PAGE_URL,
				request.getRequestURL());
		if (page != 1) {
			modelAndView.addObject(MMJBCommonConstants.JOBTITLES_PREV_PAGE_URL,
					request.getRequestURL());

		}
		// set the number of pages
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.PAGE, page);
		modelAndView.addObject("jobTitle", jobTitle);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(NO_OF_PAGES, noOfPages);
		String[] seoInfos = {jobTitle}; 
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, "title", seoInfos, noOfRecords);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_BYJOBTITLE);
		return modelAndView;
	}

	/**
	 * Fetch the jobs by employer using search type and facets of SOLR and also
	 * create a next and previous links to get the set of searched job titles to
	 * index the pages for SEO.
	 * 
	 * @param session
	 * @param desc
	 * @param facilityId
	 * @param jobSearchResultForm
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/employer/{facilityId}/{desc}", method = RequestMethod.GET)
	public ModelAndView searchJobByEmployer(HttpSession session,
			@PathVariable("desc") String desc,
			@PathVariable("facilityId") String facilityId,
			JobSearchResultForm jobSearchResultForm, HttpServletRequest request) {
		// Clear the session map
		session.removeAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		// set the search type for SOLR facets
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String employerName = MMUtils.decodeString(desc.trim());
//		request.setAttribute(MMJBCommonConstants.SECOND_FQ_PARAM, employerName);
		request.setAttribute(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);

		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				searchName, MMJBCommonConstants.POSTED_DT, session, request);
		// Add the facility Id param to map
		paramMap.put(MMJBCommonConstants.FacilityId_FQ_PARAM,
				MMJBCommonConstants.FQ_FACILITY_ID + facilityId + '"');
		paramMap.put(MMJBCommonConstants.FacilityId_NAME_FQ_PARAM,
				MMJBCommonConstants.EMPTY);

		// set default page value
		int page = 1;
		// set the number of records per page
		int recordsPerPage = MMJBCommonConstants.JOBTITLES_GRID_PAGESIZE;
		int noOfRecords = 0;
		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// fetch the jobs
			jobSearchResultDTO = jobSearchService.jobSearch(
					paramMap, start, rows);
			noOfRecords = (int) jobSearchResultDTO.getResultCount();
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			// convert the results to JSON object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		}
		// set the absolute path for next and previous links to fetch the set of
		// records
		modelAndView.addObject(MMJBCommonConstants.JOBTITLES_NEXT_PAGE_URL,
				request.getRequestURL());
		if (page != 1) {
			modelAndView.addObject(MMJBCommonConstants.JOBTITLES_PREV_PAGE_URL,
					request.getRequestURL());

		}
		// set the number of pages
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.PAGE, page);
		modelAndView.addObject("employerName", employerName);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(NO_OF_PAGES, noOfPages);
		String[] seoInfos = {employerName}; 
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, "employer", seoInfos, noOfRecords);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_BYJOBTITLE);
		return modelAndView;
	}

	/**
	 * Fetch the jobs by location using search type and facets of SOLR and also
	 * create a next and previous links to get the set of searched job titles to
	 * index the pages for SEO.
	 * 
	 * @param jobSearchResultForm
	 * @param result
	 * @param model
	 * @return JSON Object
	 * 
	 */
	@RequestMapping(value = "/location/{desc}", method = RequestMethod.GET)
	public ModelAndView searchJobByLocation(HttpSession session,
			@PathVariable("desc") String desc,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		// Clear the session map
		session.removeAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		// set the search type for SOLR facets
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String selectedSate = MMUtils.decodeString(desc.trim());
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM, selectedSate);
		request.setAttribute(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);

		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				searchName, MMJBCommonConstants.POSTED_DT, session, request);

		// set default page value
		int page = 1;
		// set the number of records per page
		int recordsPerPage = MMJBCommonConstants.JOBTITLES_GRID_PAGESIZE;
		int noOfRecords = 0;
		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// fetch the jobs
			jobSearchResultDTO = jobSearchService.jobSearch(
					paramMap, start, rows);
			noOfRecords = (int) jobSearchResultDTO.getResultCount();
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			// convert the results to JSON object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		}
		// set the absolute path for next and previous links to fetch the set of
		// records
		modelAndView.addObject(MMJBCommonConstants.JOBTITLES_NEXT_PAGE_URL,
				request.getRequestURL());
		if (page != 1) {
			modelAndView.addObject(MMJBCommonConstants.JOBTITLES_PREV_PAGE_URL,
					request.getRequestURL());

		}
		// set the number of pages
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.PAGE, page);
		// modelAndView.addObject("jobTitle", location);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(NO_OF_PAGES, noOfPages);
		String[] seoInfos = {selectedSate}; 
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, "location", seoInfos, noOfRecords);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_BYJOBTITLE);
		return modelAndView;
	}

	/**
	 * Fetch the jobs by area in location using search type and facets of SOLR
	 * and also create a next and previous links to get the set of searched job
	 * titles to index the pages for SEO.
	 * 
	 * @param jobSearchResultForm
	 * @param result
	 * @param model
	 * @return JSON Object
	 * 
	 */
	@RequestMapping(value = "/location/{location}/{area}", method = RequestMethod.GET)
	public ModelAndView searchJobByLocationReg(HttpSession session,
			@PathVariable("location") String location,
			@PathVariable("area") String area,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		// Clear the session map
		session.removeAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		// set the search type for SOLR facets
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String selectedState = MMUtils.decodeString(location.trim());
		String selectedArea = MMUtils.decodeString(area.trim());
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM, selectedState);
		request.setAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM, selectedArea);
		request.setAttribute(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);

		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				searchName, MMJBCommonConstants.POSTED_DT, session, request);

		// set default page value
		int page = 1;
		// set the number of records per page
		int recordsPerPage = MMJBCommonConstants.JOBTITLES_GRID_PAGESIZE;
		int noOfRecords = 0;
		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// fetch the jobs
			jobSearchResultDTO = jobSearchService.jobSearch(
					paramMap, start, rows);
			noOfRecords = (int) jobSearchResultDTO.getResultCount();
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			// convert the results to JSON object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		}
		// set the absolute path for next and previous links to fetch the set of
		// records
		modelAndView.addObject(MMJBCommonConstants.JOBTITLES_NEXT_PAGE_URL,
				request.getRequestURL());
		if (page != 1) {
			modelAndView.addObject(MMJBCommonConstants.JOBTITLES_PREV_PAGE_URL,
					request.getRequestURL());

		}
		// set the number of pages
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.PAGE, page);
		modelAndView.addObject("jobTitle", location);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(NO_OF_PAGES, noOfPages);
		String[] seoInfos = {selectedArea, selectedState}; 
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, "area", seoInfos, noOfRecords);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_BYJOBTITLE);
		return modelAndView;
	}

	/**
	 * This method is used to create parameter map and populate the required
	 * values into it.
	 * 
	 * @param jobSearchResultForm
	 * @param sessionId
	 * @param searchName
	 * @param session
	 * @param request
	 * @param sessionMap
	 * @return Map<String, String>
	 */
	private Map<String, String> getParameterMap(
			JobSearchResultForm jobSearchResultForm, String searchName,
			String sortByParam, HttpSession session, HttpServletRequest request) {

		int searchSeq = MMJBCommonConstants.ZERO_INT;
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);
		String sessionId = null;
		if (session != null && jobSearchResultForm != null) {
			sessionId = session.getId();
			if (sessionMap.get(SearchParamDTO.SEARCH_SEQ) == null) {
				sessionMap.put(SearchParamDTO.SEARCH_SEQ,
						String.valueOf(searchSeq + 1));
			} else {
				sessionMap.put(SearchParamDTO.SEARCH_SEQ, String
						.valueOf(Integer.parseInt(sessionMap
								.get(SearchParamDTO.SEARCH_SEQ)) + 1));
			}

			sessionMap.put(MMJBCommonConstants.SEARCH_TYPE, jobSearchResultForm
					.getSearchtype().trim());
			
			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);
		}

		String firstFQParam = MMJBCommonConstants.EMPTY;
		if (request.getAttribute(MMJBCommonConstants.FIRST_FQ_PARAM) != null) {
			firstFQParam = MMJBCommonConstants.FQ_JOB_POSITION
					+ request.getAttribute(MMJBCommonConstants.FIRST_FQ_PARAM)
					+ '"';
		}
		String secondFQParam = MMJBCommonConstants.EMPTY;
		if (request.getAttribute(MMJBCommonConstants.SECOND_FQ_PARAM) != null) {
			secondFQParam = MMJBCommonConstants.FQ_COMPANY
					+ request.getAttribute(MMJBCommonConstants.SECOND_FQ_PARAM)
					+ '"';
		}
		String thirdFQParam = MMJBCommonConstants.EMPTY;
		if (request.getAttribute(MMJBCommonConstants.THIRD_FQ_PARAM) != null) {
			thirdFQParam = MMJBCommonConstants.FQ_STATE
					+ request.getAttribute(MMJBCommonConstants.THIRD_FQ_PARAM)
					+ '"';
		}
		String fouthFQParam = MMJBCommonConstants.EMPTY;
		if (request.getAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM) != null) {
			fouthFQParam = MMJBCommonConstants.FQ_CITY
					+ request.getAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM)
					+ '"';
		}
		String fifthFQParam = MMJBCommonConstants.EMPTY;
		if (request.getAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM) != null) {
			fifthFQParam = MMJBCommonConstants.FQ_AREA
					+ request.getAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM)
					+ '"';
		}
		String facetSort = MMJBCommonConstants.INDEX_STR;
		// set the sort order for search results
		String sortOrder = MMJBCommonConstants.DESC_STR;

		// Putting all the parameters coming from the UI into a Map for further
		// processing.
		Map<String, String> fqParamMap = getFQMap(firstFQParam, secondFQParam,
				thirdFQParam, fouthFQParam, fifthFQParam, sortOrder, facetSort);

		Map<String, String> paramMap = new HashMap<String, String>();
		String keywords = String.valueOf(request
				.getAttribute(SearchParamDTO.KEYWORDS));
		paramMap.put(SearchParamDTO.KEYWORDS, keywords);
		paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
		paramMap.put(SearchParamDTO.SEARCH_SEQ,
				sessionMap.get(SearchParamDTO.SEARCH_SEQ));
		paramMap.put(SearchParamDTO.SEARCH_NAME, searchName.trim());

		// For testing. Remove it while committing
		paramMap.put(MMJBCommonConstants.SORT_PARAM, sortByParam);
		paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
				fqParamMap.get(MMJBCommonConstants.FIRST_FQ_PARAM));
		paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
				fqParamMap.get(MMJBCommonConstants.SECOND_FQ_PARAM));
		paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
				fqParamMap.get(MMJBCommonConstants.THIRD_FQ_PARAM));
		paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
				fqParamMap.get(MMJBCommonConstants.FOURTH_FQ_PARAM));
		paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
				fqParamMap.get(MMJBCommonConstants.FIFTH_FQ_PARAM));
		paramMap.put(MMJBCommonConstants.SORT_ORDER,
				fqParamMap.get(MMJBCommonConstants.SORT_ORDER));
		paramMap.put(MMJBCommonConstants.FACET_SORT,
				fqParamMap.get(MMJBCommonConstants.FACET_SORT));

		return paramMap;

	}

	/**
	 * This method is used to set the FQ param values into the Map and return
	 * the Map.
	 * 
	 * @param firstFQParam
	 * @param secondFQParam
	 * @param thirdFQParam
	 * @param fouthFQParam
	 * @param fifthFQParam
	 * @param sortOrder
	 * @param facetSort
	 * @return Map<String, String>
	 */

	public Map<String, String> getFQMap(String firstFQParam,
			String secondFQParam, String thirdFQParam, String fouthFQParam,
			String fifthFQParam, String sortOrder, String facetSort) {

		Map<String, String> fqMap = new HashMap<String, String>();
		fqMap.put(MMJBCommonConstants.FIRST_FQ_PARAM, firstFQParam);
		fqMap.put(MMJBCommonConstants.SECOND_FQ_PARAM, secondFQParam);
		fqMap.put(MMJBCommonConstants.THIRD_FQ_PARAM, thirdFQParam);
		fqMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM, fouthFQParam);
		fqMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM, fifthFQParam);
		fqMap.put(MMJBCommonConstants.SORT_ORDER, sortOrder);
		fqMap.put(MMJBCommonConstants.FACET_SORT, facetSort);

		return fqMap;
	}

	/**
	 * This method will convert the JobSearchResultDTO to JSON object
	 * 
	 * @param JobSearchResultDTO
	 * @return JSONObject
	 */
	private JSONObject searchJobToJSON(final JobSearchResultDTO jSResultDTO) {
		final JSONObject jobSrchJsonObj = new JSONObject();
		final JSONArray jsonRows = new JSONArray();

		final List<JobDTO> jobDTOList = jSResultDTO.getResultList();

		for (JobDTO jobDTO : jobDTOList) {
			final JSONObject jobSrchJson = new JSONObject();
			jobSrchJson.put(MMJBCommonConstants.AD_TEXT,
					MMUtils.isNull(jobDTO.getAdText()));
			jobSrchJson.put(MMJBCommonConstants.CAP_COMPANY,
					MMUtils.isNull(jobDTO.getCompany()));
			jobSrchJson.put(MMJBCommonConstants.JOB_TITLE,
					MMUtils.isNull(jobDTO.getJobTitle()));
			String title = MMUtils.isNull(jobDTO.getJobTitle());
			if (!title.isEmpty()) {
				jobSrchJson
						.put(MMJBCommonConstants.JOB_TITLE_ENCODE,
								title.replaceAll(
										MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
										""));
			} else {
				jobSrchJson.put(MMJBCommonConstants.JOB_TITLE_ENCODE, title);
			}
			StringBuffer location = new StringBuffer();
			if (jobDTO.getHideCity() == 0) {
				location.append(jobDTO.getCity());
				if (jobDTO.getHideState() == 0) {
					location.append(MMJBCommonConstants.COMMA+" ");
				}
			}
			if (jobDTO.getHideState() == 0) {
				location.append(jobDTO.getState());
			}
			jobSrchJson.put(MMJBCommonConstants.CAP_CITY, location.toString()
					.equals("null, null") ? "Multiple Locations" : location.toString());
			jobSrchJson.put(MMJBCommonConstants.POSTED_DATE,
					DateUtils.convertDateStringToDisplayDatePattern(jobDTO.getPostedDate().toString()));
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
					MMUtils.isNull(String.valueOf(jobDTO.getJobId())));
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
	 * This method is used to get the region based on location for Browse by
	 * location.
	 * 
	 * @param searchFacetMap
	 * @param jobSrchJsonObj
	 */

	private void getLocationRegionResults(
			Map<String, List<SearchFacetDTO>> searchFacetMap,
			JSONObject jobSrchJsonObj) {

		final Map<String, List<SearchFacetDTO>> searchFacetDTOMap = searchFacetMap;

		// Get the list of cities along with the job count
		List<String> areaList = generateRegionResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_AREA));

		jobSrchJsonObj.put(MMJBCommonConstants.AREA, areaList);
	}

	/**
	 * This is used to get the Region results.
	 * 
	 * @param facetList
	 * @return List<String>
	 */

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

	/**
	 * Add the SEO details - meta title , meta description and canonical URL for
	 * job titles list page
	 * 
	 * @param modelAndView
	 * @param request
	 * @param pageName
	 */
	private void addSEODetailsForJobTitlesPage(ModelAndView modelAndView,
			HttpServletRequest request, String pageName) {
		String metaDesc = null;
		String metaTitle = null;
		if (pageName.equalsIgnoreCase(PageNames.JOBSEEKER_BROWSE_JOB_TITLES)) {
			metaTitle = seoConfiguration
					.getProperty("jobtitlespage.alljobs.meta.title").trim();
			metaDesc = seoConfiguration
					.getProperty("jobtitlespage.alljobs.meta.description").trim();
			/*// merge the parameters
			JobSearchResultDTO jobSearchResultDTO = null;
			long totalNoOfActiveJobs = 0;
			jobCountparamMap.put(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.CITY_STATE, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.RADIUS, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.REFINED, String.valueOf(false));
			jobCountparamMap.put(SearchParamDTO.SESSION_ID, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.SEARCH_NAME, MMJBCommonConstants.BROWSE_SEARCH);
			jobCountparamMap.put(MMJBCommonConstants.SEARCH_TYPE, MMJBCommonConstants.BASIC_SEARCH_TYPE);
			jobCountparamMap.put(MMJBCommonConstants.SORT_PARAM, MMJBCommonConstants.POSTED_DT);
			jobCountparamMap.put(MMJBCommonConstants.FIRST_FQ_PARAM, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.SECOND_FQ_PARAM, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.THIRD_FQ_PARAM, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM, MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.SORT_ORDER, MMJBCommonConstants.DESC_STR);
			jobCountparamMap.put(MMJBCommonConstants.FACET_SORT, MMJBCommonConstants.INDEX_STR);
			jobCountparamMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(0));
			try {
				jobSearchResultDTO = jobSearchService.jobSearch(jobCountparamMap, 0, 0);

			} catch (JobBoardException e) {
				LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
			}
			if(jobSearchResultDTO != null){
				totalNoOfActiveJobs = (int) jobSearchResultDTO.getResultCount();
			}
			metaDesc = metaDesc.replace("?jobsCount", String.valueOf(totalNoOfActiveJobs));*/
		}
		modelAndView.addObject("metaDesc", metaDesc);
		modelAndView.addObject("metaTitle", metaTitle);
		modelAndView.addObject("canonicalUrl", request.getRequestURL());
	}
	
	/**
	 * Add the SEO details - meta title , meta description and canonical URL for
	 * job search results pages.
	 * 
	 * @param modelAndView
	 * @param request 
	 * @param request
	 * @param category
	 * @param categoryDesc 
	 * @param jobCount 
	 */
	private void addSEODetailsForJobsSearchPages(ModelAndView modelAndView,
			HttpServletRequest request, String category, String[] categoryDesc, int jobCount) {

		String metaTitle = null;
		String metaDesc = null;
		// set the meta title and description tags value for job titles list page.
		if (category.equalsIgnoreCase("title")) {
			metaTitle = seoConfiguration
					.getProperty("jobsearchpage.jobtitle.meta.title").trim();
			metaTitle = metaTitle.replace(JOBTITLE_REPLACE_WORD, categoryDesc[0]);
			metaDesc = seoConfiguration
					.getProperty("jobsearchpage.jobtitle.meta.description").trim();
			metaDesc = metaDesc.replace(JOBTITLE_REPLACE_WORD, categoryDesc[0].toLowerCase());
			metaDesc = metaDesc.replace("?jobscount", String.valueOf(jobCount));
		}
		// set the meta title and description tags value for employers list page.		
		if (category.equalsIgnoreCase("employer")) {
			metaTitle = seoConfiguration
					.getProperty("jobsearchpage.employer.meta.title").trim();
			metaTitle = metaTitle.replace("?employer", categoryDesc[0]);
			metaDesc = seoConfiguration
					.getProperty("jobsearchpage.employer.meta.description").trim();
			metaDesc = metaDesc.replace("?employer", categoryDesc[0]);
			metaDesc = metaDesc.replace("?jobscount", String.valueOf(jobCount));
		}
		// set the meta title and description tags value for locations list page.		
		if (category.equalsIgnoreCase("location")) {
			metaTitle = seoConfiguration
					.getProperty("jobsearchpage.location.meta.title").trim();
			metaTitle = metaTitle.replace("?state", categoryDesc[0]);
			metaDesc = seoConfiguration
					.getProperty("jobsearchpage.location.meta.description").trim();
			metaDesc = metaDesc.replace("?state", categoryDesc[0]);
			metaDesc = metaDesc.replace("?jobscount", String.valueOf(jobCount));
		}
		// set the meta title and description tags value for areas list page.		
		if (category.equalsIgnoreCase("area")) {
			metaTitle = seoConfiguration
					.getProperty("jobsearchpage.location.area.meta.title").trim();
			metaTitle = metaTitle.replace("?area", categoryDesc[0]);
			metaTitle = metaTitle.replace("?state", categoryDesc[1]);
			metaDesc = seoConfiguration
					.getProperty("jobsearchpage.location.area.meta.description").trim();
			metaDesc = metaDesc.replace("?area", categoryDesc[0]);
			metaDesc = metaDesc.replace("?state", categoryDesc[1]);
			metaDesc = metaDesc.replace("?jobscount", String.valueOf(jobCount));
		}

		modelAndView.addObject("metaDesc", metaDesc);
		modelAndView.addObject("metaTitle", metaTitle);
		modelAndView.addObject("canonicalUrl", request.getRequestURL());
	}
}
