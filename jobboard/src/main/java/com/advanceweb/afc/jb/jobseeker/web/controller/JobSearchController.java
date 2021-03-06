/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.AdminSeoDTO;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobTitleDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.NewsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.TestimonyDTO;
import com.advanceweb.afc.jb.common.VideoDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;
import com.advanceweb.afc.jb.employer.service.EmployerNewsFeedService;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.web.controller.BrandingTemplateForm;
import com.advanceweb.afc.jb.event.service.ClickService;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.home.web.controller.ClickController;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.service.CoverLetterService;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.login.web.controller.LoginForm;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchFacetDTO;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.afc.jb.web.utils.PDFGenerator;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * <code>JobSearchController</code>This controller belongs to all searched jobs.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */

@Controller
@RequestMapping("/search")
public class JobSearchController extends AbstractController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobSearchController.class);

	/** The job countparam map. */
	private static Map<String, String> jobCountparamMap = new HashMap<String, String>();

	/** The email service. */
	@Autowired
	private MMEmailService emailService;

	/** The job seeker job detail service. */
	@Autowired
	private JobSeekerJobDetailService jobSeekerJobDetailService;

	/** The job search service. */
	@Autowired
	private JobSearchService jobSearchService;

	/** The facility service. */
	@Autowired
	private FacilityService facilityService;

	/** The ad service. */
	@Autowired
	private AdService adService;

	/** The pdf generator. */
	@Autowired
	private PDFGenerator pdfGenerator;

	/** The lookup service. */
	@Autowired
	private LookupService lookupService;

	/** The check session map. */
	@Autowired
	private CheckSessionMap checkSessionMap;

	/** The save search service. */
	@Autowired
	private SaveSearchService saveSearchService;

	/** The branding template service. */
	@Autowired
	private BrandingTemplateService brandingTemplateService;

	/** The employer news feed service. */
	@Autowired
	private EmployerNewsFeedService employerNewsFeedService;

	/** The job search validator. */
	@Autowired
	private JobSearchValidator jobSearchValidator;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The cover letter service. */
	@Autowired
	private CoverLetterService coverLetterService;

	/** The click service. */
	@Autowired
	private ClickService clickService;

	/** The navigation path. */
	@Value("${navigationPath}")
	private String navigationPath;

	/** The jobseeker job application sub. */
	@Value("${jobseekerJobApplicationSub}")
	private String jobseekerJobApplicationSub;

	/** The jobseeker job application body. */
	@Value("${jobseekerJobApplicationBody}")
	private String jobseekerJobApplicationBody;

	/** The employe job application sub. */
	@Value("${employeJobApplicationSub}")
	private String employeJobApplicationSub;

	/** The employe job application body. */
	@Value("${employeJobApplicationBody}")
	private String employeJobApplicationBody;

	/** The save jobs limit. */
	@Value("${saveJobsLimit}")
	private String saveJobsLimit;

	/** The dothtml extention. */
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	/** The jobseeker page extention. */
	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;

	/** The employer page extention. */
	@Value("${employerPageExtention}")
	private String employerPageExtention;

	/** The default resume extension. */
	@Value("${defaultResumeExtension}")
	private String defaultResumeExtension;

	/** The resume service. */
	@Autowired
	private ResumeService resumeService;

	/** The save this job success msg. */
	@Value("${saveThisJobSuccessMsg}")
	private String saveThisJobSuccessMsg;

	/** The save this job err msg. */
	@Value("${saveThisJobErrMsg}")
	private String saveThisJobErrMsg;

	/** The apply job success msg. */
	@Value("${applyJobSuccessMsg}")
	private String applyJobSuccessMsg;

	/** The common mail err msg. */
	@Value("${commonMailErrMsg}")
	private String commonMailErrMsg;

	/** The apply job err msg. */
	@Value("${applyJobErrMsg}")
	private String applyJobErrMsg;

	/** The resume not found msg. */
	@Value("${resumeNotFoundMsg}")
	private String resumeNotFoundMsg;

	/** The ajax msg. */
	@Value("${ajaxMsg}")
	private String ajaxMsg;

	/** The ajax navigation path. */
	@Value("${ajaxNavigationPath}")
	private String ajaxNavigationPath;

	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	/** The sub ofmail. */
	private @Value("${jobseekerSuggestFrdSub}")
	String subOfmail;

	/** The body of mail body. */
	private @Value("${jobseekerSuggestFrdBody}")
	String bodyOfMailBody;

	/** The url redirect mail. */
	private @Value("${URL_REDIRECT_MAIL}")
	String urlRedirectMail;

	/** The err sending mail. */
	private @Value("${ERROR_SENDING_MAIL}")
	String errSendingMail;

	/** The email msg. */
	private @Value("${EMAIL_MESSAGE}")
	String emailMsg;

	/** The web mail server. */
	private @Value("${WEB_MAIL_SERVER}")
	String webMailServer;

	/** The email msg blank. */
	private @Value("${EMAIL_MESSAGE_BLANK}")
	String emailMsgBlank;

	/** The media path. */
	private @Value("${mediaPath}")
	String mediaPath;

	/** The recent searchs limit. */
	@Value("${recentSearchsLimit}")
	private String recentSearchsLimit;
	
	/** The NetSuite package internal ID. */
	@Value("${PLATINUM_90}")
	private String PLATINUM_90;

	/** The NetSuite package internal ID. */
	@Value("${PLATINUM_180}")
	private String PLATINUM_180;
	
	/** The NetSuite package internal ID. */
	@Value("${PLATINUM_365}")
	private String PLATINUM_365;
	
	/** The NetSuite package internal ID. */
	@Value("${GOLD_90}")
	private String GOLD_90;
	
	/** The NetSuite package internal ID. */
	@Value("${GOLD_180}")
	private String GOLD_180;
	
	/** The NetSuite package internal ID. */
	@Value("${GOLD_365}")
	private String GOLD_365;

	/** The seo configuration. */
	@Autowired
	@Resource(name = "seoConfiguration")
	private Properties seoConfiguration;
	
	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/** The click controller. */
	@Autowired
	private ClickController clickController;

	/** The Constant PLATINUM_LIST. */
	private static final String PLATINUM_LIST = "PlatinumNewsList";
	
	/** The Constant IS_SORTING. */
	private static final String IS_SORTING = "isSorting";
	
	/** The Constant CURRENT_URL. */
	private static final String CURRENT_URL = "currentUrl";
	
	/** The Constant CITY. */
	private static final String CITY = "?city";
	
	/** The Constant COUNTRY. */
	private static final String COUNTRY = "?country";
	
	/** The Constant STATE. */
	private static final String STATE = "?state";
	
	/** The Constant JOBTITLE. */
	private static final String JOBTITLE = "jobtitle";
	
	/** The Constant UNCHECKED. */
	private static final String UNCHECKED = "unchecked";
	
	/** The Constant ERROR_SOLR. */
	private static final String ERROR_SOLR = "Error occured while getting the Job Search Result from SOLR...";
	
	/** The Constant JOBBOARD_SEARCHRESULTS_PAGE. */
	private static final String JOBBOARD_SEARCHRESULTS_PAGE = "jobboardsearchresults";
	
	/** The Constant JOB_SEARCH_RESULT_FORM. */
	private static final String JOB_SEARCH_RESULT_FORM = "jobSearchResultForm";
	
	/** The Constant JOBTITLE_REPLACE_WORD. */
	private static final String JOBTITLE_REPLACE_WORD = "?jobtitle";
	
	/** The Constant LOCATION. */
	private static final String LOCATION = "location";
	
	/** The Constant LOCNAME_REPLACE_WORD. */
	private static final String LOCNAME_REPLACE_WORD = "?state";
	
	/** The Constant JOB_SRCH_MTCH_INFO. */
	private static final String JOB_SRCH_MTCH_INFO = "jobSearchMatchInfo";
	
	/** The Constant JOBSEARCH. */
	private static final String JOBSEARCH = "search";
	
	/** The Constant JOBS. */
	private static final String JOBS = "jobs";
	
	/** The Constant JOBS_URL. */
	private static final String JOBS_URL = "jobsUrl";
	
	/** The Constant FTR_PAGE_MESSAGE. */
	private static final String FTR_PAGE_MESSAGE = "footerpage.jobsurlmessage";
	
	/** The Constant JOBS_URL_TITLE. */
	private static final String JOBS_URL_TITLE = "jobsUrlTitle";
	
	/** The Constant JOB_SRCH_CATEGORY_MATCH. */
	private static final String JOB_SRCH_CATEGORY_MATCH = "jobsearchpage.category.matchinfo";
	
	/** The Constant JOB_SRCH_MATCH. */
	private static final String JOB_SRCH_MATCH = "jobsearchpage.matchinfo";
	
	/** The Constant Q_KEYWORD. */
	private static final String Q_KEYWORD = "?keyword";
	
	/** The Constant Q_CITYSTATE. */
	private static final String Q_CITYSTATE = "?cityState";
	
	/** The Constant BROWSE_BY_EMPLOYER. */
	private static final String BROWSE_BY_EMPLOYER = "browseByEmployer";
	
	/** The Constant BROWSE_BY_STATE. */
	private static final String BROWSE_BY_STATE = "browseBystate";
	
	/** The Constant AREA. */
	private static final String AREA = "area";
	
	/** The Constant LATEST_RECENT_LIST. */
	private static final String LATEST_RECENT_LIST = "latestRecentList";
	
	/** The Constant Q_JOBSCOUNT. */
	private static final String Q_JOBSCOUNT = "?jobscount";
	
	/** The Constant FRESH_JOB_SRCH. */
	private static final String FRESH_JOB_SRCH = "freshjobsearch";
	
	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/**
	 * The view action is called to get the job details by jobId and navigate to
	 * job view details page.
	 * 
	 * @param jobId
	 * @param request
	 * @return : modelandview for respected Jobid
	 */
	@RequestMapping(value = "/jobview/{jobId}/{jobtitle}")
	public ModelAndView viewJobDetails(@PathVariable("jobId") int jobId,
			@PathVariable(JOBTITLE) String jobTitle, Map<String, Object> model,
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();

		try {
			clickController.getclickevent(jobId,
					MMJBCommonConstants.CLICKTYPE_JOBVIEW, request, response);
			boolean isReturnResults = true;
			getJobDetails(model, request, session, modelView, jobId,
					isReturnResults);

		} catch (Exception e) {
			// logger call
			LOGGER.error("ERROR viewJobDetails:", e);
		}

		return modelView;
	}

	/**
	 * The view action is called to get the job details by jobId and navigate to
	 * job view details page.
	 * 
	 * @param jobId
	 * @param request
	 * @return : modelandview for respected Jobid
	 */
	@RequestMapping(value = "/viewMyJobDetails")
	public ModelAndView viewMyJobDetails(Map<String, Object> model,
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();

		try {
			int jobId = 0;
			if (request.getParameter("id") != null) {
				jobId = Integer.parseInt(request.getParameter("id"));
			}
			boolean isReturnResults = false;
			getJobDetails(model, request, session, modelView, jobId,
					isReturnResults);

		} catch (Exception e) {
			// logger call
			LOGGER.error("ERROR viewMyJobDetails:", e);
		}

		return modelView;
	}

	/**
	 * Method called to get the Job details
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @param modelView
	 * @param jobId
	 * @param isReturnResults
	 */
	private void getJobDetails(Map<String, Object> model,
			HttpServletRequest request, HttpSession session,
			ModelAndView modelView, int jobId, boolean isReturnResults) {
		Map<String, String> sessionMap = null;
		sessionMap = checkSessionMap.getSearchSessionMap(session);
		// View the job with template
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		if (MMJBCommonConstants.ZERO_INT != jobDTO.getTemplateId()) {
			jobDTO = checkBrand(jobDTO);
		}
		
		if(jobDTO.getPositionType()!=null && !jobDTO.getPositionType().equals("") && !jobDTO.getPositionType().equals("None")){
			if(isNumeric(jobDTO.getPositionType())){
				List<DropDownDTO> empTypeList = populateDropdownsService
						.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
				Map<String, String> employmentMap = new HashMap<String, String>();
				for (int i = 0; i < empTypeList.size(); i++) {
					employmentMap.put(empTypeList.get(i).getOptionId(),
							empTypeList.get(i).getOptionName());
				}
			jobDTO.setPositionType(employmentMap.get(jobDTO.getPositionType()));
			}
			else{
				jobDTO.setPositionType(jobDTO.getPositionType());
			}
		}
		
		model.put("jobDetail", jobDTO);
		model.put("isFeatureEmployer", jobDTO.isFeatured());
		model.put("isReturnResults", isReturnResults);
		// Get the SEO Details
		addSEODetailsForJobView(model, request, jobDTO);
		if (sessionMap != null) {
			sessionMap.put(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));
			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);
		}

		if (MMJBCommonConstants.ZERO_INT == jobDTO.getTemplateId()) {
			modelView.setViewName("jobseekerJobDetails");
			// get the Ads
			populateAds(request, session, modelView, PageNames.JOB_VIEW, 0);
		} else {
			// Code to get the recent Jobs posted by employer
			/*List<JobPostDTO> jobPostDTOList = jobSearchService
					.getRecentJobsPostedByEmployer(jobDTO.getFacilityId(),
							jobDTO.getJobId());*/
			// Code to get the employer More Job Opportunities
			List<JobDTO> jobPostDTOList = employerMoreJobOpportunities(
					jobDTO.getFacilityId(), session);
			if (null != jobPostDTOList && !jobPostDTOList.isEmpty()) {
				// Add the encoded job title to list
				String encodedTitle;
				for (JobDTO jobDTO2 : jobPostDTOList) {
					encodedTitle = jobDTO2.getJobTitle();
					jobDTO2.setJobTitleEncode(encodedTitle
							.replaceAll(
									MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
									""));
				}
				// Remove the current job from the list
				for (JobDTO jobPostDTO : jobPostDTOList) {
					if (jobPostDTO.getJobId() == jobId) {
						jobPostDTOList.remove(jobPostDTO);
						break;
					}
				}
				// If current job is not listed in list then remove the last job
				if (jobPostDTOList.size() >= 6) {
					jobPostDTOList.remove(jobPostDTOList.size()-1);
				}
			}
			// For getting the News feed from XML file
			Map<String, List<NewsDTO>> newsMap = employerNewsFeedService
					.getNewsFromXML();
			List<NewsDTO> newsDTOList = newsMap.get(PLATINUM_LIST);
			List<NewsDTO> updatedNewsDTOList = new ArrayList<NewsDTO>();
			if (null != newsDTOList) {
				for (NewsDTO dto : newsDTOList) {

					if (dto.getFacility() != null
							&& dto.getFacility().equals(
									String.valueOf(jobDTO.getFacilityId()))) {

						updatedNewsDTOList.add(dto);
					}
				}
			}

			if (null != updatedNewsDTOList && updatedNewsDTOList.size() > 5) {
				List<NewsDTO> modNewsDTOList = updatedNewsDTOList.subList(0, 5);
				model.put("newsDTOList", modNewsDTOList);
			} else {
				model.put("newsDTOList", updatedNewsDTOList);
			}

			List<String> videoList = setVideoURL(jobDTO, request);
			model.put("jobDTOList", jobPostDTOList);
			model.put("videoList", videoList);
			modelView.setViewName("jobseekerJobDetailsTemplate");
			// get the Ads
			populateAds(request, session, modelView,
					PageNames.PREMIUM_JOB_VIEW, 0);
		}
		modelView.addObject("basePath", request.getRequestURL().toString()
				.replace(request.getServletPath(), ""));
	}

	/**
	 * The method helps to get the list of other job opportunities of employer by checking the 
	 * recent search done by application. If job is viewed from other way without search , then the 
	 * latest jobs posted by employer will be taken.  
	 * 
	 * @param facilityId
	 * @param session
	 * @return
	 */
	private List<JobDTO> employerMoreJobOpportunities(int facilityId, HttpSession session) {
		FacilityDTO facilityDTO = facilityService.getParentFacility(facilityId);
		List<JobDTO> jobPostDTOList = null;		
		JobSearchResultDTO jobSearchResultDTO = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		String sessionId = "";
		paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
		paramMap.put(MMJBCommonConstants.SEARCH_TYPE,
				MMJBCommonConstants.BASIC_SEARCH_TYPE);
		paramMap.put(MMJBCommonConstants.FacilityId_FQ_PARAM,
				MMJBCommonConstants.FQ_FACILITY_ID + facilityDTO.getFacilityId() + '"');
		paramMap.put(MMJBCommonConstants.FacilityId_NAME_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.SORT_ORDER,
				MMJBCommonConstants.DESC_STR);

		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);
		if(null != sessionMap.get(SearchParamDTO.KEYWORDS)){
			paramMap.put(SearchParamDTO.KEYWORDS, sessionMap.get(SearchParamDTO.KEYWORDS).toString());
		}else{
			paramMap.put(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);
		}
		paramMap.put(SearchParamDTO.CITY_STATE, MMJBCommonConstants.EMPTY);
		paramMap.put(SearchParamDTO.RADIUS, MMJBCommonConstants.EMPTY);
		paramMap.put(SearchParamDTO.REFINED, String.valueOf(false));
		paramMap.put(SearchParamDTO.SEARCH_NAME, MMJBCommonConstants.BROWSE_SEARCH.trim());
		paramMap.put(MMJBCommonConstants.SORT_PARAM,
				MMJBCommonConstants.POSTED_DT);
		paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FACET_SORT,
				MMJBCommonConstants.INDEX_STR);
		paramMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(0));
		int page = 1;
		int recordsPerPage = 0;

		try {
			long start = (page - 1) * recordsPerPage;
			long rows = 6;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}
		if (jobSearchResultDTO != null) {
			jobPostDTOList = jobSearchResultDTO.getResultList();
		}
		return jobPostDTOList ;
	}

	/**
	 * Checks if is numeric.
	 *
	 * @param str the str
	 * @return true, if is numeric
	 */
	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	
	
	/**
	 * This method retrieves all the news related to Platinum customers
	 * 
	 * @return modelView
	 */
	@RequestMapping(value = "/getPlatinumNewsList")
	public ModelAndView getPlatinumNewsList(HttpServletRequest request,
			HttpSession session, @RequestParam("jobId") int jobId) {
		ModelAndView modelView = new ModelAndView();
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		Map<String, List<NewsDTO>> newsMap = employerNewsFeedService
				.getNewsFromXML();
		List<NewsDTO> newsDTOList = newsMap.get(PLATINUM_LIST);
		List<NewsDTO> updatedNewsDTOList = new ArrayList<NewsDTO>();

        for(NewsDTO dto:newsDTOList){

                if(dto.getFacility()!=null && dto.getFacility().equals(String.valueOf(jobDTO.getFacilityId()))){

                        updatedNewsDTOList.add(dto);
                }
        }
		modelView.addObject("newsDTOList", updatedNewsDTOList);
		modelView.setViewName("newsList");
		// get the Ads
		populateAds(request, session, modelView, PageNames.PREMIUM_JOB_VIEW, 0);

		return modelView;
	}

	/**
	 * Populate Ads for job view, premium job view and browse category pages
	 * page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param pageName
	 * @param recordsPerPage
	 */
	private void populateAds(HttpServletRequest request, HttpSession session,
			ModelAndView model, String pageName, int recordsPerPage) {
		String bannerString = null;
		try {

			ClientContext clientContext = getClientContextDetails(request,
					session, pageName);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);

			if (pageName.equalsIgnoreCase(PageNames.JOBSEEKER_BROWSE_JOBS)) {
				if(recordsPerPage != 0){
				List<String> adsList = new ArrayList<String>();
				for (int index = 0; index < (recordsPerPage / MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT); index++) {
					size = AdSize.IAB_LEADERBOARD;
					position = AdPosition.CENTER_MIDDLE;
					bannerString = adService.getBanner(clientContext, size,
							position).getTag();
					adsList.add(bannerString);
				}
				session.setAttribute("adPageCenterMiddleList", adsList);
				}
				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_TOP;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);

			} else if (pageName.equalsIgnoreCase(PageNames.JOB_VIEW)) {
				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_TOP;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);

				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_MIDDLE;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE,
						bannerString);
			} else if (pageName
					.equalsIgnoreCase(PageNames.JOBSEEKER_ADVC_JOB_SEARCH)
					|| pageName
							.equalsIgnoreCase(PageNames.JOBSEEKER_JOB_SEARCH)) {
				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_TOP;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);
				
				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_MIDDLE;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE,
						bannerString);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Get the SEO details for job view page.
	 * 
	 * @param model
	 * @param request
	 * @param jobDTO
	 */
	private void addSEODetailsForJobView(Map<String, Object> model,
			HttpServletRequest request, JobDTO jobDTO) {

		String metaDesc = seoConfiguration.getProperty(
				"jobviewpage.meta.description").trim();
		String metaTitle = seoConfiguration.getProperty(
				"jobviewpage.meta.title").trim();
		metaDesc = metaDesc
				.replace(JOBTITLE_REPLACE_WORD, jobDTO.getJobTitle());
		metaTitle = metaTitle.replace(JOBTITLE_REPLACE_WORD,
				jobDTO.getJobTitle());
		if (jobDTO.getCompany() == null) {
			metaTitle = metaTitle.replace("?employer" + ",",
					MMJBCommonConstants.EMPTY);
			metaDesc = metaDesc.replace("?employer" + ",",
					MMJBCommonConstants.EMPTY);
		} else {
			metaTitle = metaTitle.replace("?employer", jobDTO.getCompany());
			metaDesc = metaDesc.replace("?employer", jobDTO.getCompany());
		}
		if (jobDTO.getCity() == null) {
			metaDesc = metaDesc.replace(CITY + ",", MMJBCommonConstants.EMPTY);
			metaTitle = metaTitle
					.replace(CITY + ",", MMJBCommonConstants.EMPTY);
		} else {
			metaDesc = metaDesc.replace(CITY, jobDTO.getCity());
			metaTitle = metaTitle.replace(CITY, jobDTO.getCity());
		}
		if (jobDTO.getState() == null) {
			metaDesc = metaDesc.replace(STATE, MMJBCommonConstants.EMPTY);
			metaTitle = metaTitle.replace(STATE, MMJBCommonConstants.EMPTY);
		} else {
			metaDesc = metaDesc.replace(STATE, jobDTO.getState());
			metaTitle = metaTitle.replace(STATE, jobDTO.getState());
		}
		if (jobDTO.getCountry() == null) {
			metaDesc = metaDesc.replace(" in " + COUNTRY,
					MMJBCommonConstants.EMPTY);
			metaTitle = metaTitle.replace(" in " + COUNTRY,
					MMJBCommonConstants.EMPTY);
		} else {
			metaDesc = metaDesc.replace(COUNTRY, jobDTO.getCountry());
			metaTitle = metaTitle.replace(COUNTRY, jobDTO.getCountry());
		}
		if (jobDTO.getCompany() == null && jobDTO.getCity() == null
				&& jobDTO.getState() != null) {
			metaDesc = metaDesc.replace(" in", MMJBCommonConstants.EMPTY);
			metaTitle = metaTitle.replace(" in", MMJBCommonConstants.EMPTY);
		}
		model.put("metaDesc", metaDesc);
		model.put("metaTitle", metaTitle);
		model.put("canonicalUrl", request.getRequestURL());
	}

	/**
	 * The method track the number of clicks done by user.
	 * 
	 * @param response
	 * @param request
	 * @param jobId
	 * @param clickType
	 * @return
	 */
	@RequestMapping(value = "/clicksTrack")
	public @ResponseBody
	String trackClicks(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("id") int jobId,
			@RequestParam("clickType") String clickType) {

		String status = "success";
		try {
			clickController.getclickevent(jobId, clickType, request, response);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			status = "failure";
		}

		return status;
	}

	/**
	 * Method called to fetch the public Cover Letter by user Id
	 * 
	 * @param userId
	 * @return
	 */
	private String fetchPublicCoverLetter(int userId,String coverLetterId) {
		ResCoverLetterDTO dto = coverLetterService
				.fetchPublicCoverLetter(userId,coverLetterId);
		return dto.getCoverletterText();
	}

	/**
	 * Select resume.
	 *
	 * @param jobId the job id
	 * @param position the position
	 * @param response the response
	 * @param session the session
	 * @param request the request
	 * @return the jSON object
	 */
	@RequestMapping(value = "/selectResume", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject selectResume(@RequestParam("id") int jobId,@RequestParam("position")String position,HttpServletResponse response, HttpSession session,
			HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		JobApplyTypeDTO jobApplyTypeDTO = jobSearchService
				.applyJobDetails(jobId);
		if (!jobSearchValidator.isLoggedIn(jobId,
				jobDTO.getJobTitle(), null, session,
				request)) {
			jsonObject.put(ajaxNavigationPath, request.getContextPath()
					+ "/search/jobseekerApplyJobPopUp"+dothtmlExtention);
			return jsonObject;
		}
		if (null !=jobApplyTypeDTO && (null == jobApplyTypeDTO.getApplyLink()
				|| jobApplyTypeDTO.getApplyLink().isEmpty())) {
			if (null !=jobDTO.getUrl() && !jobDTO.getUrl().isEmpty()) {
				jobApplyTypeDTO.setApplyLink(jobDTO.getUrl());
			}
		}
		if (!jobSearchValidator.validateApplyType(jobId, jsonObject,
				jobApplyTypeDTO)) {
			clickController.getclickevent(jobId,
					MMJBCommonConstants.CLICKTYPE_APPLYNOW, request,
					response);
			return jsonObject;
		}
		if (null !=jobApplyTypeDTO && null== jobDTO.getEmail() ) {
			jobDTO.setEmail(jobApplyTypeDTO.getApplyLink());
		}
		
		if (null != jobDTO.getEmail()
				&& !jobSearchValidator.validateEmailPattern(jobDTO.getEmail())) {
			if (jobDTO.getEmail().isEmpty() && null != jobDTO.getUrl()
					&& !jobDTO.getUrl().isEmpty()) {
				String finalUrl = jobDTO.getUrl();
				boolean httpsStatus = false;
				if (jobDTO.getUrl().startsWith("https://")) {
					httpsStatus = true;

				} else if (jobDTO.getUrl().startsWith("http://")) {
					httpsStatus = true;
				}
				if (!httpsStatus) {
					finalUrl = "http://" + jobDTO.getUrl();
				}
				jsonObject.put("applyLink", finalUrl);
			} else {
				jsonObject.put("applyLink", jobDTO.getEmail());
			}
			return jsonObject;
		}
		int userId = getUserID(session);
		List<ResumeDTO> resumeDTO = resumeService
				.retrieveAllResumes(userId);
		if(resumeDTO==null || resumeDTO.isEmpty()){
			
			jsonObject.put("AjaxMSG", "You must create a resume before you can apply for this job.");
			 return jsonObject;
		}
		// Validate if job is already applied
					AppliedJobDTO appliedJobDTO = jobSearchService
							.fetchSavedOrAppliedJob(jobDTO, userId);
					if (appliedJobDTO != null && appliedJobDTO.getAppliedDt() != null) {
						applyJobErrMsg = applyJobErrMsg.replace("?",
								appliedJobDTO.getAppliedDt());
						jsonObject.put(ajaxMsg, applyJobErrMsg);
						return jsonObject;
					}
		 jsonObject.put(ajaxNavigationPath, request.getContextPath()
				+ "/search/selectResumePopup"+dothtmlExtention+"?id="+jobId+"&userId="+userId+"&position="+position);
		 return jsonObject;
	}

	/**
	 * Show select resume popup.
	 *
	 * @param jobId the job id
	 * @param userId the user id
	 * @param position the position
	 * @return the model and view
	 */
	@RequestMapping(value = "/selectResumePopup")
	public ModelAndView showSelectResumePopup(@RequestParam("id") int jobId,@RequestParam("userId") int userId,@RequestParam("position")String position) {
		ModelAndView model=new ModelAndView();
		
		List<ResumeDTO> resumeDTOList=null;
		List<ResCoverLetterDTO>coverLetterList=null;
		try{
		resumeDTOList = resumeService
				.retrieveAllResumes(userId);
		coverLetterList = coverLetterService.getJobOwnerList(userId);
		List<ResumeVisibilityDTO> visiblityList = populateDropdownsService
				.getResumeVisibilityList();
		
		Map<String, String> visibilityMap = new HashMap<String, String>();
		
		for (int i = 0; i < visiblityList.size(); i++) {
			visibilityMap.put(visiblityList.get(i).getVisibilityId(),
					visiblityList.get(i).getVisibilityName());
		}

		List<ResumeDTO> resumeDTOListNew = new ArrayList<ResumeDTO>();
		
		for (ResumeDTO resumeDTO : resumeDTOList) {
			resumeDTO.setResumeVisibility(visibilityMap.get(resumeDTO
					.getResumeVisibility()));
			resumeDTOListNew.add(resumeDTO);
		}
		}
		catch(Exception e){
			LOGGER.error("Exception while displaying the resume and cover letter pop up :"+e.getMessage());
		}
		model.setViewName("selectResumePopup");
		
		model.addObject("position",position);
		model.addObject("jobId",jobId);
		model.addObject("userId",userId);
		model.addObject("resumeList", resumeDTOList);
		model.addObject("coverLetterList", coverLetterList);
		return model;
	}
	
	/**
	 * Method called to apply for job
	 * 
	 * @param map
	 * @param userID
	 * @param jobId
	 * @param currentUrl
	 * @param response
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applyJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject applyJob(Map<String, Object> map, @RequestParam("userId") String userID,
			@RequestParam("id") int jobId,
			@RequestParam("resumeId") int resumeId,
			@RequestParam("cletterId") String cletterId,
			@RequestParam(CURRENT_URL) String currentUrl,
			HttpServletResponse response, HttpSession session,
			HttpServletRequest request) {

		JSONObject jsonObject = new JSONObject();
		clickController.getclickevent(jobId,
				MMJBCommonConstants.CLICKTYPE_APPLYNOW, request,
				response);
		try {
			// Get the Job details
			JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
			JobApplyTypeDTO jobApplyTypeDTO = jobSearchService
					.applyJobDetails(jobId);
			if (null != jobApplyTypeDTO && null == jobDTO.getEmail()) {
				jobDTO.setEmail(jobApplyTypeDTO.getApplyLink());
			}

			int userId = getUserID(session);
			int savedJobsCount = 0;
			try {
				List<AppliedJobDTO> appliedJobDTOList = jobSeekerJobDetailService
						.getAppliedJobs((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				if (null != appliedJobDTOList) {
					savedJobsCount = appliedJobDTOList.size();
				}
				if (savedJobsCount >= Integer.parseInt(saveJobsLimit)) {
					int oldJobId = appliedJobDTOList.get(appliedJobDTOList.size()-1).getSaveJobId();
					jobSeekerJobDetailService.updateAppliedSavedJobs(oldJobId);
				}
			} catch (JobBoardException e) {
				LOGGER.error("Error occured while getting the saved job of curresponding  user or while updating the particular job details"
						, e);
			}

			// Validate if job is already applied
			AppliedJobDTO appliedJobDTO = jobSearchService
					.fetchSavedOrAppliedJob(jobDTO, userId);

			// Fetch the public resume
			List<String> attachmentpaths = fetchPublicVisibleResume(userId,resumeId);
			// Fetch the public Cover Letter
			String coverLetterText=null;
			if(cletterId!=null && !cletterId.equals("none")){
			coverLetterText = fetchPublicCoverLetter(userId,cletterId);
			}
			if (attachmentpaths == null) {
				jsonObject.put(ajaxMsg, resumeNotFoundMsg);
				return jsonObject;
			}
			try {
				sendMailOfAppliedJob(session, request, jobDTO, attachmentpaths,
						coverLetterText);
			} catch (Exception e) {
				jsonObject.put(ajaxMsg, commonMailErrMsg);
				LOGGER.error(e.getMessage(), e);
				return jsonObject;
			}

			saveAppliedJob(jobId, userId, jobDTO, appliedJobDTO);
			jsonObject.put(ajaxMsg, applyJobSuccessMsg);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return jsonObject;
	}


	/**
	 * Send mail of applied job to jobseeker and employer
	 * 
	 * @param form
	 * @param request
	 * @param userName
	 * @param jobDTO
	 * @param attachmentpaths
	 * @throws AddressException
	 */
	public void sendMailOfAppliedJob(HttpSession session,
			HttpServletRequest request, JobDTO jobDTO,
			List<String> attachmentpaths, String coverLetterText)
			throws AddressException {
		String coverLetterTxt = coverLetterText;
		String userName = (String) session
				.getAttribute(MMJBCommonConstants.USER_NAME);
		String userEmail = (String) session
				.getAttribute(MMJBCommonConstants.USER_EMAIL);
		String jobTitle = jobDTO.getJobTitle();
		String jbDatail = jobTitle +" "+"(" +jobDTO.getJobNumber()+")";
		StringBuffer mailBody = new StringBuffer();
		// Send mail to Employer regarding job application
		String loginPath = navigationPath.substring(2);
		EmailDTO employerEmailDTO = new EmailDTO();
		employerEmailDTO.setFromAddress(advanceWebAddress);
		InternetAddress[] employerToAddress = new InternetAddress[1];
		employerToAddress[0] = new InternetAddress(jobDTO.getEmail());
		employerEmailDTO.setToAddress(employerToAddress);
		String employerMailSub = employeJobApplicationSub.replace(
				"?jobseekername", userName);
		employerEmailDTO.setSubject(employerMailSub);
		String employerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), loginPath)
				+ dothtmlExtention + employerPageExtention;
		String employerMailBodyMsg = employeJobApplicationBody.replace(
				"?empDashboardLink", employerloginUrl);
		String employerMailBody = employerMailBodyMsg.replace("?jobTitle",
				jbDatail);
		employerMailBody = employerMailBody.replace("?jobseekername", userName);
		mailBody.append(emailConfiguration.getProperty("employer.email.header")
				.trim());

		if (coverLetterTxt == null) {
			mailBody.append(employerMailBody);
		} else {
			coverLetterTxt = coverLetterTxt.replace("\r\n", "<br/>");
			employerMailBody = employerMailBody
					.replace(
							"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">",
							"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\"><tr><td height=\"138\" align=\"left\" valign=\"top\" style=\"border-top:1px solid #cccccc; border-bottom:1px solid #cccccc;\"><br /><br /><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; color:#333333;\"><br />"
									+ coverLetterTxt + "</span></td></tr>");
			mailBody.append(employerMailBody);
		}

		mailBody.append(emailConfiguration.getProperty("email.footer").trim());
		employerEmailDTO.setBody(mailBody.toString());
		employerEmailDTO.setHtmlFormat(true);
		employerEmailDTO.setAttachmentPaths(attachmentpaths);
		emailService.sendEmail(employerEmailDTO);
		LOGGER.debug("Mail sent to employer");

		// Send confirmation mail to job seeker regarding job
		// application
		EmailDTO jobSeekerEmailDTO = new EmailDTO();
		jobSeekerEmailDTO.setFromAddress(advanceWebAddress);
		InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
		jobSeekerToAddress[0] = new InternetAddress(userEmail);
		jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
		String jobseekerMailSub = "";
		String jonseekerloginUrl = request
				.getRequestURL()
				.toString()
				.replace(
						request.getServletPath(),
						emailConfiguration.getProperty(
								"jobSeeker.email.login.url").trim());
		String jobseekerMailBody = jobseekerJobApplicationBody.replace(
				"?jsdashboardLink", jonseekerloginUrl);
		if (jobDTO.getCompanyNameDisp() == null) {
			jobseekerMailSub = jobseekerJobApplicationSub.replace(
					"to ?companyname", "");
			jobseekerMailBody = jobseekerMailBody
					.replace("to ?companyname", "");
		} else {
			jobseekerMailSub = jobseekerJobApplicationSub.replace(
					"?companyname", jobDTO.getCompanyNameDisp());
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",
					jobDTO.getCompanyNameDisp());
		}
		StringBuffer stringBuffer = new StringBuffer();

		jobSeekerEmailDTO.setSubject(jobseekerMailSub);

		stringBuffer.append(emailConfiguration.getProperty(
				"jobseeker.email.header").trim());
		stringBuffer.append(jobseekerMailBody);
		stringBuffer.append(emailConfiguration.getProperty("email.footer")
				.trim());
		jobSeekerEmailDTO.setBody(stringBuffer.toString());
		jobSeekerEmailDTO.setHtmlFormat(true);
		emailService.sendEmail(jobSeekerEmailDTO);
		LOGGER.debug("Mail sent to jobseeker");
	}

	/**
	 * Save or Update the applied job
	 * 
	 * @param jobId
	 * @param userId
	 * @param jobDTO
	 * @param appliedJobDTO
	 */
	public void saveAppliedJob(int jobId, int userId, JobDTO jobDTO,
			AppliedJobDTO appliedJobDTO) {
		// save the applied job in DB
		Date currentDate = MMUtils.getCurrentDateAndTime();
		AppliedJobDTO applyJobDTO = null;
		if (appliedJobDTO == null || appliedJobDTO.getAppliedDt() != null) {
			applyJobDTO = new AppliedJobDTO();
			JobPostDTO jpJob = new JobPostDTO();
			jpJob.setJobId(jobId);
			applyJobDTO.setJpJob(jpJob);
			applyJobDTO.setUserId(userId);
			applyJobDTO.setJobTitle(jobDTO.getJobTitle());
			applyJobDTO.setFacilityName(jobDTO.getCompanyNameDisp());
			applyJobDTO.setCreateDt(currentDate.toString());
			applyJobDTO.setAppliedDt(currentDate.toString());
			applyJobDTO.setDeleteDt(null);
			jobSearchService.saveOrApplyJob(applyJobDTO);
		} else {
			applyJobDTO = appliedJobDTO;
			applyJobDTO.setAppliedDt(currentDate.toString());
			jobSearchService.updateSaveOrApplyJob(applyJobDTO);
		}
	}

	/**
	 * Method called to fetch the public assigned resume by user Id
	 * 
	 * @param userId
	 * @return
	 */
	private List<String> fetchPublicVisibleResume(int userId,int uploadResumeId) {
		List<String> attachmentpaths = null;
		try {
			ResumeDTO resumeDTO = resumeService
					.fetchPublicResumeByUserId(userId,uploadResumeId);
			if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
				LOGGER.debug("Resume type : ADVANCE Resume Builder");
				File newFile = pdfGenerator.generateAndSaveAsPdf(resumeDTO);
				resumeDTO.setFilePath(newFile.getAbsolutePath());
				
			} else if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
				LOGGER.debug("Resume type : Copy and Paste");
				try {
					// Create temp file.
					File temp = File.createTempFile(resumeDTO.getResumeName(),
							defaultResumeExtension);
					File newFile = new File(temp.getParent() + "\\"
							+ resumeDTO.getResumeName()
							+ defaultResumeExtension);
					// Rename
					newFile.deleteOnExit();
					if (temp.renameTo(newFile)) {
						LOGGER.debug("File has been renamed.");
					}
					temp.deleteOnExit();

					// Write to temp file
					BufferedWriter out = new BufferedWriter(new FileWriter(
							newFile));
					String jobDesc = Jsoup.parse(resumeDTO.getResumeText()).html();
					jobDesc = jobDesc.replaceAll("\\<.*?\\>", "");
					out.write(jobDesc);
					out.close();
					resumeDTO.setFilePath(newFile.getAbsolutePath());
				} catch (IOException e) {
					LOGGER.error("Copy Paste resume error",e);
				}
			}
			if (resumeDTO.getFilePath() != null) {
				attachmentpaths = new ArrayList<String>();
				attachmentpaths.add(resumeDTO.getFilePath());
			}
		} catch (Exception e) {
			LOGGER.error("Resume not found"+e.getMessage(), e);
		}
		return attachmentpaths;
	}


	/**
	 * This method is called to forward to job search page
	 * 
	 * @param model
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findJobPage", method = RequestMethod.GET)
	public ModelAndView findJobPage(
			Map<String, JobSearchResultForm> model,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "isNewSearch", required = false) boolean isNewSearch) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		ModelAndView modelAndView = new ModelAndView();
		if (!isNewSearch) {
			Map<String, String> sessionMap = checkSessionMap
					.getSearchSessionMap(session);

			if (!sessionMap.isEmpty()) {

				String searchType = sessionMap
						.get(MMJBCommonConstants.SEARCH_TYPE);
				String radius = MMJBCommonConstants.EMPTY;
				String cityState = MMJBCommonConstants.EMPTY;
				String keywords = MMJBCommonConstants.EMPTY;
				String saveSearchName = MMJBCommonConstants.EMPTY;
				keywords = sessionMap.get(SearchParamDTO.KEYWORDS);
				cityState = sessionMap.get(SearchParamDTO.CITY_STATE);
				radius = sessionMap.get(SearchParamDTO.RADIUS);
				saveSearchName = sessionMap.get(SearchParamDTO.SEARCH_NAME);
				// jobSearchResultForm.setSaveSearchName(saveSearchName);
				jobSearchResultForm.setSearchName(saveSearchName);
				jobSearchResultForm.setSearchtype(searchType);
				jobSearchResultForm.setKeywords(keywords);
				jobSearchResultForm.setCityState(cityState);
				jobSearchResultForm.setRadius(radius);
				jobSearchResultForm.setAutoload(true);

				LOGGER.debug("Removing keywords, city,state, autoload from session....");

				session.removeAttribute(sessionMap
						.remove(SearchParamDTO.KEYWORDS));
				session.removeAttribute(sessionMap
						.remove(SearchParamDTO.CITY_STATE));
				session.removeAttribute(sessionMap
						.remove(SearchParamDTO.RADIUS));
				session.removeAttribute(sessionMap
						.remove(MMJBCommonConstants.AUTOLOAD));

			}
		} else {
			clearSession(session);
		}
		model.put(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		// get the Ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_JOB_SEARCH, 0);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		return modelAndView;
	}

	/**
	 * This method is called to search jobs by reloading the page
	 * 
	 * @param model
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findjobs", method = RequestMethod.POST)
	public ModelAndView findjobs(
			Map<String, JobSearchResultForm> model,
			@ModelAttribute("jobSearchResultForm") JobSearchResultForm jobSearchResultForm,
			HttpSession session, HttpServletRequest request) {
		// JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		// clear session attributes
		clearSession(session);

		// validate the inputs
		JobSearchResultDTO jobSearchResultDTO = null;
		boolean freshjobsearch = true;
		if (request.getParameter("freshjobsearch") != null) {
			freshjobsearch = Boolean.valueOf(request
					.getParameter("freshjobsearch"));
		}
		if (freshjobsearch) {
			// clear refine searches
			session.getAttribute("employer");
			session.getAttribute("browseBystate");

			// get the search name
			String searchName = getSearchNameForSearch(jobSearchResultForm,
					session, request);
			jobSearchResultForm.setSearchName(searchName);
			jobSearchResultForm
					.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

			jobSrchJsonObj = jobSearchValidator.validateJobSearch(
					jobSearchResultForm, session);
			/*
			 * if (jobSrchJsonObj != null) { return jobSrchJsonObj; }
			 */
		}

		// merge the parameters and set it in session
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);

		int recordsPerPage = MMJBCommonConstants.JOBSEACRH_DEFAULT_PAGE_SIZE;
		int page = 1;
		int noOfRecords = 0;
		if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
			page = Integer.parseInt(request
					.getParameter(MMJBCommonConstants.PAGE));
		}
		if (request.getParameter(MMJBCommonConstants.JOBSEACRH_PAGE_SIZE) != null) {
			recordsPerPage = Integer.parseInt(request
					.getParameter(MMJBCommonConstants.JOBSEACRH_PAGE_SIZE));
		}
		long start = (page - 1) * recordsPerPage;
		long rows = recordsPerPage;
		try {
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);
			//
			if (jobSearchResultDTO != null) {
				noOfRecords = (int) jobSearchResultDTO.getResultCount();
				// Calling the service layer for converting the
				// JobSearchResultDTO
				// object into JSON Object
				jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
			}
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		// get the current search items
		List<HashMap<String, Object>> currentSearchList = getCurrentSearchResultsList(session);
		session.setAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST,
				currentSearchList);
		// set the pagination values in session
		setSessionForGrid(session, request, jobSearchResultDTO, page,
				recordsPerPage, noOfRecords, jobSrchJsonObj);

		modelAndView.addObject(MMJBCommonConstants.ADPGCENTER_MIDDLE_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.ADPGCENTER_MIDDLE_LIST));

		model.put(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		// get the Ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_JOB_SEARCH, recordsPerPage);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		return modelAndView;
	}

	/**
	 * Depending on the selection of category the method help to display the
	 * list of job titles or locations or employers from DB .
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @param category
	 */
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public ModelAndView browseJobsByCategory(HttpSession session,
			HttpServletRequest request,
			@PathVariable("category") String category) {
		ModelAndView modelAndView = new ModelAndView();
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		// Check for job titles category
		if (category.equalsIgnoreCase("title")) {
			clearSession(session);
			Map<String, List<String>> titlesByName = new TreeMap<String, List<String>>();
			Set<String> titleKeysList = new HashSet<String>();
			int totalTitles = 0;
			try {
				List<JobTitleDTO> positionList =jobSearchService
						.getJobTitleList();
				// set the employers list in dictionary format
				for (JobTitleDTO titleDTO : positionList) {
					if (titleDTO != null && !titleDTO.getJobtitle().trim().isEmpty()) {
						String nameLetter = titleDTO.getJobtitle().substring(0, 1).toUpperCase();
						if (titleKeysList.add(nameLetter)) {
							List<String> jobList = new ArrayList<String>();
							jobList.add(titleDTO.getJobtitle());
							titlesByName.put(nameLetter, jobList);
						} else {
							titlesByName.get(nameLetter).add(titleDTO.getJobtitle());
						}
						totalTitles++;
					}
				}
				int totalKeyCount = titlesByName.keySet().size();
				int rowsCount = (int) Math
						.ceil((double) (totalTitles + totalKeyCount) / 3);
				Map<Integer, TreeMap<String, List<HashMap<String, String>>>> list = new TreeMap<Integer, TreeMap<String, List<HashMap<String, String>>>>();
				Iterator<Entry<String, List<String>>> keyIt = titlesByName
						.entrySet().iterator();
				int i = 0, j = 1;
				// check for blocks if in first column its exceeded
				TreeMap<String, List<HashMap<String, String>>> sets = new TreeMap<String, List<HashMap<String, String>>>();
				List<HashMap<String, String>> titleDetail;
				while (keyIt.hasNext()) {
					Entry<String, List<String>> entry = (Entry<String, List<String>>) keyIt
							.next();
					if (!(i < (rowsCount * j))) {
						if (j == 1) {
							rowsCount = rowsCount + (i - rowsCount);
						}
						j++;
						sets = new TreeMap<String, List<HashMap<String, String>>>();
					}
					titleDetail =new ArrayList<HashMap<String,String>>();
					List<String> titles = entry.getValue();
					HashMap<String,String> titleEncode;
					for (String title : titles) {	
						titleEncode = new HashMap<String, String>();
						titleEncode.put("jobtitle" , title);
						titleEncode.put("encodeJobtitle" , MMUtils.encodeString(title).toLowerCase());
						titleDetail.add(titleEncode);
					}
					sets.put(entry.getKey(), titleDetail);
					list.put(j, sets);
					i++;
					i = entry.getValue().size() + i;
				}
				TreeMap<String, List<HashMap<String, String>>> firstColPositionList = null;
				TreeMap<String, List<HashMap<String, String>>> secColPositionList = null;
				TreeMap<String, List<HashMap<String, String>>> thirdColPositionList = null;
				if (!list.isEmpty() && list.get(1) != null) {
					firstColPositionList = list.get(1);
				}
				if (!list.isEmpty() && list.get(2) != null) {
					secColPositionList = list.get(2);
				}
				if (!list.isEmpty() && list.get(3) != null) {
					thirdColPositionList = list.get(3);
				}
				modelAndView.addObject("firstColPositionList", firstColPositionList);
				modelAndView.addObject("secColPositionList", secColPositionList);
				modelAndView.addObject("thirdColPositionList", thirdColPositionList);
				modelAndView.addObject("jobTitlePage", true);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			// get SEO details
			addSEODetailsForBrowsePages(modelAndView, request, category);
			populateAds(request, session, modelAndView,
					PageNames.JOBSEEKER_BROWSE_JOBS, 0);
			modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
			modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
			return modelAndView;
		}
		JobSearchResultDTO jobSearchResultDTO = null;
		JSONObject jobSrchJsonObj = null;
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm
				.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);

		int page = 1;
		int recordsPerPage = 0;

		try {
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		// Check for employers category
		if (category.equalsIgnoreCase("employer")) {
			clearSession(session);
			try {
				Map<String, List<String>> emplyrsByName = new TreeMap<String, List<String>>();
				Set<String> nameList = new HashSet<String>();

				@SuppressWarnings("unchecked")
				// get the areas list
				List<String> employerList = (List<String>) jobSrchJsonObj
						.get(MMJBCommonConstants.COMPANY_ID_NAME);
				int totalEmployers = 0;
				// set the employers list in dictionary format
				String emplyrName = null;
				for (String job : employerList) {
					emplyrName = job.substring(job.indexOf("_")+1);
					if (emplyrName != null && !emplyrName.split("\\(")[0].trim().isEmpty()) {
						String nameLetter = emplyrName.substring(0, 1).toUpperCase();
						if (nameList.add(nameLetter)) {
							List<String> jobList = new ArrayList<String>();
							jobList.add(job);
							emplyrsByName.put(nameLetter, jobList);
						} else {
							emplyrsByName.get(nameLetter).add(job);
						}
						totalEmployers++;
					}
				}
				int totalKeyCount = emplyrsByName.keySet().size();
				int rowsCount = (int) Math
						.ceil((double) (totalEmployers + totalKeyCount) / 3);
				Map<Integer, TreeMap<String, List<HashMap<String, Object>>>> list = new TreeMap<Integer, TreeMap<String, List<HashMap<String, Object>>>>();
				Iterator<Entry<String, List<String>>> keyIt = emplyrsByName
						.entrySet().iterator();
				int i = 0, j = 1;
				// check for blocks if in first column its exceeded
				TreeMap<String, List<HashMap<String, Object>>> sets = new TreeMap<String, List<HashMap<String, Object>>>();
				List<HashMap<String, Object>> empDetail;
				while (keyIt.hasNext()) {
					Entry<String, List<String>> entry = (Entry<String, List<String>>) keyIt
							.next();
					if (!(i < (rowsCount * j))) {
						if (j == 1) {
							rowsCount = rowsCount + (i - rowsCount);
						}
						j++;
						sets = new TreeMap<String, List<HashMap<String, Object>>>();
					}
					empDetail =new ArrayList<HashMap<String,Object>>();
					List<String> empNames = entry.getValue();
					HashMap<String,Object> empNamesEncode;
					int empId = 0;
					for (String empName : empNames) {
						try{
						empId = Integer.parseInt(empName.substring(0, empName.indexOf("_")));
						}catch (Exception e) {
							LOGGER.error(e.getMessage(), e);
						}
						emplyrName = empName.substring(empName.indexOf("_")+1);
						empNamesEncode = new HashMap<String, Object>();
						empNamesEncode.put("empName" , emplyrName);
						empNamesEncode.put("empId" , empId);
						empName = emplyrName.substring(0, emplyrName.lastIndexOf("(")).trim();
						empNamesEncode.put("encodedEmpName" , MMUtils.encodeString(empName.trim().replaceAll(
								MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
								"")).toLowerCase());
						empDetail.add(empNamesEncode);
					}
					sets.put(entry.getKey(), empDetail);
					list.put(j, sets);
					i++;
					i = entry.getValue().size() + i;
				}
				TreeMap<String, List<HashMap<String, Object>>> employerFirstList = null;
				TreeMap<String, List<HashMap<String, Object>>> employerSecList = null;
				TreeMap<String, List<HashMap<String, Object>>> employerThirdList = null;
				if (!list.isEmpty() && list.get(1) != null) {
					employerFirstList = list.get(1);
				}
				if (!list.isEmpty() && list.get(2) != null) {
					employerSecList = list.get(2);
				}
				if (!list.isEmpty() && list.get(3) != null) {
					employerThirdList = list.get(3);
				}
				modelAndView.addObject("employerFirstList", employerFirstList);
				modelAndView.addObject("employerSecList", employerSecList);
				modelAndView.addObject("employerThirdList", employerThirdList);
				modelAndView.addObject("employerPage", true);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}else if (category.equalsIgnoreCase("location")) {
			// Check for locations category
			clearSession(session);
			try {
				@SuppressWarnings("unchecked")
				// get the areas list
				List<String> jobsStatesList = (List<String>) jobSrchJsonObj
						.get(MMJBCommonConstants.STATE);
				List<HashMap<String, String>> statesList =new ArrayList<HashMap<String,String>>();
				HashMap<String, String> stateDetail;
				String stateFullName;
				for (String state : jobsStatesList) {
					stateDetail = new HashMap<String, String>();
					stateDetail.put("state", state);
					state = state.substring(0,
							state.lastIndexOf("(")).trim();
					// Add the state full name by DB
					stateFullName = lookupService.getStateFullName(state);
					stateDetail.put("encodestate",
							MMUtils.encodeString(stateFullName)+"-"+state);
					statesList.add(stateDetail);
				}
				
				double statesCount = statesList.size();
				int divider = (int) (Math.ceil(statesCount / 5));
				List<HashMap<String, String>> firstColStatesList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> secColStatesList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> thirdColStatesList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> fourtColStatesList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> fifthColStatesList = new ArrayList<HashMap<String, String>>();

				firstColStatesList = statesList.subList(0, divider);
				if (statesCount >= divider * 2) {
					secColStatesList = statesList.subList(divider, divider * 2);
				}
				if (statesCount >= divider * 3) {
					thirdColStatesList = statesList.subList(divider * 2,
							divider * 3);
				}
				if (statesCount >= divider * 4) {
					fourtColStatesList = statesList.subList(divider * 3,
							divider * 4);
				}
				fifthColStatesList = statesList.subList(divider * 4,
						(int) statesCount);
				
				modelAndView
						.addObject("firstColStatesList", firstColStatesList);
				modelAndView.addObject("secColStatesList", secColStatesList);
				modelAndView
						.addObject("thirdColStatesList", thirdColStatesList);
				modelAndView
						.addObject("fourtColStatesList", fourtColStatesList);
				modelAndView
						.addObject("fifthColStatesList", fifthColStatesList);
				modelAndView.addObject("locationPage", true);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		}
		// get SEO details
		addSEODetailsForBrowsePages(modelAndView, request, category);
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS, recordsPerPage);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		return modelAndView;
	}

	/**
	 * Depending on the selection of location the method help to display the
	 * list of areas under locations from SOLR.
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @param location
	 */
//	@RequestMapping(value = "/location/{location}", method = RequestMethod.GET)
	private ModelAndView browseJobsBySubCategory(HttpSession session,
			HttpServletRequest request, String location) {

		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		ModelAndView modelAndView = new ModelAndView();
		JobSearchResultDTO jobSearchResultDTO = null;
		JSONObject jobSrchJsonObj = null;

		// clear session attributes of pagination
		clearSession(session);

		// set the search type
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm
				.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String state = MMUtils.decodeString(location.split("-")[location.split("-").length - 1].trim());
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM, state);
		request.setAttribute(MMJBCommonConstants.IS_FQ_PARAM_LOWERCASE, true);
		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);

		int page = 1;
		int recordsPerPage = 0;

		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			// set the max areas count
			recordsPerPage = MMJBCommonConstants.JOB_SEARCH_MAX_AREAS;
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

		} catch (JobBoardException e) {
			LOGGER.debug(ERROR_SOLR);
		}

		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);

		@SuppressWarnings(UNCHECKED)
		// get the areas list
		List<String> areaList = (List<String>) jobSrchJsonObj
				.get(MMJBCommonConstants.AREA);
		List<HashMap<String, String>> encodedAreaList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> encodedArea;
		for (String area : areaList) {
			encodedArea = new HashMap<String, String>();
			encodedArea.put("area", area);
			area = area.replace(MMJBCommonConstants.METRO_AREA, "").trim();
			encodedArea.put("encodedArea", MMUtils.encodeString(area));
			encodedAreaList.add(encodedArea);
		}
		
		double statesCount = areaList.size();
		int divider = (int) Math.ceil(statesCount / 2);
		
		List<HashMap<String, String>> firstColAreasList = encodedAreaList
				.subList(0, divider);
		List<HashMap<String, String>> secColAreasList = encodedAreaList.subList(divider,
				(int) statesCount);
		modelAndView
				.addObject("firstColAreasList", firstColAreasList);
		modelAndView.addObject("secColAreasList", secColAreasList);
		}
		modelAndView.addObject("locationPage", true);
		modelAndView.addObject("areaPage", true);
		String jobSearchMatchInfo = seoConfiguration.getProperty(
				"browsepage.location.areaslist.matchinfo").trim();
		// get full location name
		state = state.toUpperCase();
		String stateFullName = lookupService.getStateFullName(state);
		jobSearchMatchInfo = jobSearchMatchInfo.replace(LOCNAME_REPLACE_WORD,
				state);
		modelAndView.addObject(LOCATION, state);
		modelAndView.addObject("stateFullName", stateFullName);
		modelAndView.addObject("locationFullname", stateFullName + " (" + state
				+ ") ");
		modelAndView.addObject(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);

		// add SEO details to page
		addSEODetailsForBrowsePages(modelAndView, request, "areas");

		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS, recordsPerPage);
		// code to save the current URL for 'return search results' link in view
		// details page
		//session.setAttribute(MMJBCommonConstants.BROWSE_JOB_VIEW_LINK, request
		//				.getRequestURL().toString());
		return modelAndView;
	}

	/**
	 * Fetch the jobs depending on the selection of job title by using search
	 * type and facets from SOLR.
	 * 
	 * @param session
	 * @param desc
	 * @param jobSearchResultForm
	 * @param result
	 * @param request
	 * 
	 */
	@RequestMapping(value = "/title/{desc}", method = RequestMethod.GET)	
	public ModelAndView searchJobsByJobtitle(HttpSession session,
			@PathVariable("desc") String desc,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		// set the search type
//		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		String searchName = MMJBCommonConstants.KEYWORD_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm
				.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		StringBuffer buffer = new StringBuffer();
		buffer.append(desc.trim().replace("-", " "));
		String jobTitle = formatProperText(buffer).toString();
//		request.setAttribute(MMJBCommonConstants.FIRST_FQ_PARAM, jobTitle);
		jobSearchResultForm.setKeywords(jobTitle);

		// create the link to get the searched jobs titles list page
		String jobsUrl = request.getRequestURL().toString()
				.replace(JOBSEARCH, JOBS)
				+ "?page=1";
		modelAndView.addObject(JOBS_URL, jobsUrl);
		String jobsUrlMessage = seoConfiguration.getProperty(FTR_PAGE_MESSAGE)
				.trim();
		modelAndView.addObject(JOBS_URL_TITLE, jobsUrlMessage);

		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);

		// fetch the jobs of selected job title
		int page = 1;
		int recordsPerPage = MMJBCommonConstants.JOBSEACRH_DEFAULT_PAGE_SIZE;
		int noOfRecords = 0;
		String next = request.getParameter(MMJBCommonConstants.NEXT);

		try {
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
					* recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + jobSearchResultDTO
							.getResultList().size()));

			noOfRecords = (int) jobSearchResultDTO.getResultCount();

		} catch (JobBoardException e) {
			LOGGER.debug(ERROR_SOLR);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null == next || next.isEmpty()) {
			beginVal = (page / MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT)
					* MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT;
		} else {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		}
		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
		modelAndView.addObject(MMJBCommonConstants.BEGIN, (beginVal <= 0 ? 1
				: beginVal));
		modelAndView.addObject(MMJBCommonConstants.BEGIN_VAL, beginVal);
		modelAndView.addObject(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(MMJBCommonConstants.SEARCH_TYPE, searchName);
		String jobCount = NumberFormat.getInstance().format(
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView
				.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT, jobCount);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_CATEGORY_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_JOBSCOUNT, jobCount);
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, jobTitle);
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_CITYSTATE,
				MMJBCommonConstants.EMPTY);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
		}
		String[] seoInfos = { jobTitle };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, JOBTITLE,
				seoInfos, noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS, recordsPerPage);
		modelAndView.addObject("basePath", request.getRequestURL().toString()
				.replace(request.getServletPath(), ""));
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		// code to save the current URL for 'return search results' link in view
		// details page
//		session.setAttribute(MMJBCommonConstants.BROWSE_JOB_VIEW_LINK, request
//						.getRequestURL().toString());
		return modelAndView;
	}

	/**
	 * Fetch the jobs depending on the selection of employer by using search
	 * type and facets from SOLR.
	 * 
	 * @param session
	 * @param desc
	 * @param facilityId
	 * @param jobSearchResultForm
	 * @param result
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/employer/{facilityId}/{desc}", method = RequestMethod.GET)
	public ModelAndView searchJobByEmployer(HttpSession session,
			@PathVariable("desc") String desc,
			@PathVariable("facilityId") String facilityId,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm
				.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		modelAndView.addObject(MMJBCommonConstants.SEARCH_TYPE, searchName);

		// set the FQ parameters
		String employer = MMUtils.decodeString(desc.trim());
		// request.setAttribute(MMJBCommonConstants.SECOND_FQ_PARAM, employer);
		// request.setAttribute(MMJBCommonConstants.FQ_FACILITY_ID, facilityId);

		// create the link to get the searched jobs titles list page
		String jobsUrl = request.getRequestURL().toString()
				.replace(JOBSEARCH, JOBS);
		modelAndView.addObject(JOBS_URL, jobsUrl);
		String jobsUrlMessage = seoConfiguration.getProperty(FTR_PAGE_MESSAGE)
				.trim();
		modelAndView.addObject(JOBS_URL_TITLE, jobsUrlMessage);

		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);
		// Add the facility Id param to map
		paramMap.put(MMJBCommonConstants.FacilityId_FQ_PARAM,
				MMJBCommonConstants.FQ_FACILITY_ID + facilityId + '"');
		paramMap.put(MMJBCommonConstants.FacilityId_NAME_FQ_PARAM,
				MMJBCommonConstants.EMPTY);

		// fetch the jobs of selected employer
		int page = 1;
		int recordsPerPage = MMJBCommonConstants.JOBSEACRH_DEFAULT_PAGE_SIZE;
		int noOfRecords = 0;
		String next = request.getParameter(MMJBCommonConstants.NEXT);

		try {
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
					* recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + jobSearchResultDTO
							.getResultList().size()));

			noOfRecords = (int) jobSearchResultDTO.getResultCount();

		} catch (JobBoardException e) {
			LOGGER.debug(ERROR_SOLR);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null == next || next.isEmpty()) {
			beginVal = (page / MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT)
					* MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT;
		} else {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		}
		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);

			modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
			modelAndView.addObject(MMJBCommonConstants.BEGIN,
					(beginVal <= 0 ? 1 : beginVal));
			modelAndView.addObject(MMJBCommonConstants.BEGIN_VAL, beginVal);
			modelAndView.addObject(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
			modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
			jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
					jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
			modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
					jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
			modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
					jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
			modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
			modelAndView.addObject(MMJBCommonConstants.SEARCH_TYPE, searchName);
			String jobCount = NumberFormat.getInstance().format(
					jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
			modelAndView.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
					jobCount);
			modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST,
					null);
			modelAndView.addObject(MMJBCommonConstants.CITY,
					jobSrchJsonObj.get(MMJBCommonConstants.CITY));
			modelAndView.addObject(MMJBCommonConstants.STATE,
					jobSrchJsonObj.get(MMJBCommonConstants.STATE));
			modelAndView.addObject(MMJBCommonConstants.COMPANY,
					jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
			// added the selected employer in session to disable the link of
			// selected employer in refine results list
			if (!jobSearchResultDTO.getResultList().isEmpty()) {
				FacilityDTO facilityDTO = facilityService
						.getFacilityByFacilityId(Integer.parseInt(facilityId));
				employer = facilityDTO.getName();
			} else {
				StringBuffer buffer = new StringBuffer(employer);
				employer = formatProperText(buffer).toString();
			}
			session.setAttribute(BROWSE_BY_EMPLOYER, employer);
			session.removeAttribute(BROWSE_BY_STATE);

			String jobSearchMatchInfo = seoConfiguration.getProperty(
					JOB_SRCH_CATEGORY_MATCH).trim();
			jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_JOBSCOUNT,
					jobCount);
			jobSearchMatchInfo = jobSearchMatchInfo
					.replace(Q_KEYWORD, employer);
			jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_CITYSTATE,
					MMJBCommonConstants.EMPTY);
			session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
		}
		String[] seoInfos = { employer };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request,
				BROWSE_BY_EMPLOYER, seoInfos, noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS, recordsPerPage);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		modelAndView.addObject("basePath", request.getRequestURL().toString()
				.replace(request.getServletPath(), ""));
		// code to save the current URL for 'return search results' link in view
		// details page
//		session.setAttribute(MMJBCommonConstants.BROWSE_JOB_VIEW_LINK, request
//				.getRequestURL().toString());
		return modelAndView;
	}

	/**
	 * Fetch the jobs depending on the selection of location by using search
	 * type and facets from SOLR.
	 * 
	 * @param session
	 * @param desc
	 * @param jobSearchResultForm
	 * @param result
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/location/{desc}", method = RequestMethod.GET)
	public ModelAndView searchJobByLocation(HttpSession session,
			@PathVariable("desc") String desc,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		
		if(desc.contains("-")){
			return browseJobsBySubCategory(session, request, desc);
		}
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm
				.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String state = MMUtils.decodeString(desc.trim());
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM, state);
		request.setAttribute(MMJBCommonConstants.IS_FQ_PARAM_LOWERCASE, true);

		// create the link to get the searched jobs titles list page
		String jobsUrl = request.getRequestURL().toString()
				.replace(JOBSEARCH, JOBS);
		modelAndView.addObject(JOBS_URL, jobsUrl);
		String jobsUrlMessage = seoConfiguration.getProperty(FTR_PAGE_MESSAGE)
				.trim();
		modelAndView.addObject(JOBS_URL_TITLE, jobsUrlMessage);
		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);
		// fetch the jobs of selected location
		int page = 1;
		int recordsPerPage = MMJBCommonConstants.JOBSEACRH_DEFAULT_PAGE_SIZE;
		int noOfRecords = 0;
		String next = request.getParameter(MMJBCommonConstants.NEXT);

		try {
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
					* recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + jobSearchResultDTO
							.getResultList().size()));

			noOfRecords = (int) jobSearchResultDTO.getResultCount();

		} catch (JobBoardException e) {
			LOGGER.debug(ERROR_SOLR);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null == next || next.isEmpty()) {
			beginVal = (page / MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT)
					* MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT;
		} else {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		}
		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);

		modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
		modelAndView.addObject(MMJBCommonConstants.BEGIN, (beginVal <= 0 ? 1
				: beginVal));
		modelAndView.addObject(MMJBCommonConstants.BEGIN_VAL, beginVal);
		modelAndView.addObject(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(MMJBCommonConstants.SEARCH_TYPE, searchName);
		String jobCount = NumberFormat.getInstance().format(
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView
				.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT, jobCount);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_CATEGORY_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_JOBSCOUNT, jobCount);
		if(!jobSearchResultDTO.getResultList().isEmpty()){
			state = jobSearchResultDTO.getResultList().get(0).getState();
		}else{
			StringBuffer buffer = new StringBuffer(state);
			state = formatProperText(buffer).toString();
		}
		
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, state);
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_CITYSTATE,
				MMJBCommonConstants.EMPTY);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
		}
		// added the selected state in session to disable the link of selected
		// state in refine results list
		session.setAttribute(BROWSE_BY_STATE, state);
		session.removeAttribute(BROWSE_BY_EMPLOYER);

		String[] seoInfos = { state };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, LOCATION,
				seoInfos, noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS, recordsPerPage);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		modelAndView.addObject("basePath", request.getRequestURL().toString()
				.replace(request.getServletPath(), ""));
		return modelAndView;
	}

	/**
	 * Fetch the jobs depending on the selection of area in location by using
	 * search type and facets from SOLR.
	 * 
	 * @param session
	 * @param location
	 * @param area
	 * @param jobSearchResultForm
	 * @param result
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/location/{location}/{area}" }, method = RequestMethod.GET)
	public ModelAndView searchJobByLocationReg(HttpSession session,
			@PathVariable(LOCATION) String location,
			@PathVariable(AREA) String area,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm
				.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String selectedLocation = MMUtils.decodeString(location.trim());
		StringBuffer buffer = new StringBuffer(MMUtils.decodeString(area.trim()));
		String selectedArea = formatProperText(buffer).toString();
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM,
				selectedLocation);
		request.setAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM, selectedArea);
		request.setAttribute(MMJBCommonConstants.IS_FQ_PARAM_LOWERCASE, true);

		// create the link to get the searched jobs titles list page
		String jobsUrl = request.getRequestURL().toString()
				.replace(JOBSEARCH, JOBS);
		modelAndView.addObject(JOBS_URL, jobsUrl);
		String jobsUrlMessage = seoConfiguration.getProperty(FTR_PAGE_MESSAGE)
				.trim();
		modelAndView.addObject(JOBS_URL_TITLE, jobsUrlMessage);
		// merge the parameters
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);
		// fetch the jobs of selected area in location
		int page = 1;
		int recordsPerPage = MMJBCommonConstants.JOBSEACRH_DEFAULT_PAGE_SIZE;
		int noOfRecords = 0;
		String next = request.getParameter(MMJBCommonConstants.NEXT);

		try {
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
					* recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + jobSearchResultDTO
							.getResultList().size()));

			noOfRecords = (int) jobSearchResultDTO.getResultCount();

		} catch (JobBoardException e) {
			LOGGER.debug(ERROR_SOLR);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null == next || next.isEmpty()) {
			beginVal = (page / MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT)
					* MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT;
		} else {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		}
		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
		modelAndView.addObject(MMJBCommonConstants.BEGIN, (beginVal <= 0 ? 1
				: beginVal));
		modelAndView.addObject(MMJBCommonConstants.BEGIN_VAL, beginVal);
		modelAndView.addObject(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_PAGE, page);
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		modelAndView.addObject(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.addObject(MMJBCommonConstants.SEARCH_TYPE, searchName);
		String jobCount = NumberFormat.getInstance().format(
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView
				.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT, jobCount);
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
		// added the selected state in session to disable the link of selected
		// state in refine results list
		if(!jobSearchResultDTO.getResultList().isEmpty()){
			selectedLocation = jobSearchResultDTO.getResultList().get(0).getState();;
		}else{
			buffer = new StringBuffer(selectedLocation);
			selectedLocation = formatProperText(buffer).toString();
		}
		session.setAttribute(BROWSE_BY_STATE, selectedLocation);
		session.removeAttribute(BROWSE_BY_EMPLOYER);

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_CATEGORY_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_JOBSCOUNT, jobCount);
//		selectedArea = jobSearchResultDTO.getResultList().get(0).getCity();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, selectedArea
				+ "," + selectedLocation);
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_CITYSTATE,
				MMJBCommonConstants.EMPTY);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
		}
		String[] seoInfos = { selectedArea, selectedLocation };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, AREA, seoInfos,
				noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS, recordsPerPage);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		modelAndView.addObject("basePath", request.getRequestURL().toString()
				.replace(request.getServletPath(), ""));
		// code to save the current URL for 'return search results' link in view
		// details page
//		session.setAttribute(MMJBCommonConstants.BROWSE_JOB_VIEW_LINK, request
//						.getRequestURL().toString());
		return modelAndView;
	}

	/**
	 * populate Ads for job search results page
	 * 
	 * @param request
	 * @param session
	 * @param recordsPerPage
	 * @param jobSrchJsonObj
	 */
	private void populateAds(HttpServletRequest request, HttpSession session,
			JSONObject jobSrchJsonObj, int recordsPerPage) {

		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOBSEEKER_JOB_SEARCH);
			List<String> adsList = new ArrayList<String>();
			for (int index = 0; index < (recordsPerPage / MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT); index++) {
				AdSize size = AdSize.IAB_LEADERBOARD;
				AdPosition position = AdPosition.CENTER_MIDDLE;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				adsList.add(bannerString);
			}
			// session.setAttribute("adPageCenterMiddleList", adsList);
			jobSrchJsonObj.put(MMJBCommonConstants.ADPGCENTER_MIDDLE_LIST,
					adsList);

			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			jobSrchJsonObj.put(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			jobSrchJsonObj.put(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			jobSrchJsonObj.put(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			jobSrchJsonObj.put(MMJBCommonConstants.ADPGRIGHT_MIDDLE,
					bannerString);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Get the search name for job search
	 * 
	 * @param jobSearchResultForm
	 * @param session
	 * @param request
	 * @return
	 */
	public String getSearchNameForSearch(
			JobSearchResultForm jobSearchResultForm, HttpSession session,
			HttpServletRequest request) {
		String searchName = MMJBCommonConstants.EMPTY;
		// Check if city state and radius field is not empty to check for
		// LOCATION search
		searchName = MMJBCommonConstants.KEYWORD_SEARCH;
		if ((jobSearchResultForm.getCityState() != null)
				&& !(StringUtils.isEmpty(jobSearchResultForm.getCityState()
						.trim()))
				&& !StringUtils.isEmpty(jobSearchResultForm.getKeywords()
						.trim())) {
			searchName = MMJBCommonConstants.LOCATION_SEARCH;
			session.setAttribute(MMJBCommonConstants.DISPLAY_RADIUS, true);
		}
		return searchName;
	}

	/**
	 * Method helps to set the sort order for job search results grid and toggle
	 * the order.
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public String setSortOrder(HttpSession session, HttpServletRequest request) {
		String sortOrder = MMJBCommonConstants.DESC_STR;
		boolean isSorting = Boolean.parseBoolean(request
				.getParameter(IS_SORTING));
		if (session.getAttribute(MMJBCommonConstants.SORT_ORDER) == null) {
			session.setAttribute(MMJBCommonConstants.SORT_ORDER,
					MMJBCommonConstants.DESC_STR);
		}
		if (isSorting) {
			String prevOrder = (String) session
					.getAttribute(MMJBCommonConstants.SORT_ORDER);
			if (request.getParameter(MMJBCommonConstants.PAGE) == null) {
				if (prevOrder.equalsIgnoreCase(MMJBCommonConstants.DESC_STR)) {
					session.setAttribute(MMJBCommonConstants.SORT_ORDER,
							MMJBCommonConstants.ASC_STR);
					sortOrder = MMJBCommonConstants.ASC_STR;
				} else {
					session.setAttribute(MMJBCommonConstants.SORT_ORDER,
							MMJBCommonConstants.DESC_STR);
					sortOrder = MMJBCommonConstants.DESC_STR;
				}
			} else {
				sortOrder = prevOrder;
			}

		} else {
			session.setAttribute(MMJBCommonConstants.SORT_ORDER,
					MMJBCommonConstants.DESC_STR);
		}
		return sortOrder;
	}

	/**
	 * Method to return User id.
	 * 
	 * @param session
	 * @return
	 */
	private Integer getUserID(HttpSession session) {

		int userId = 0;

		if ((null != session.getAttribute(MMJBCommonConstants.USER_ID))
				&& StringUtils.isNotEmpty(session.getAttribute(
						MMJBCommonConstants.USER_ID).toString())) {

			userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);

		}

		return userId;
	}

	/**
	 * The method helps to get the current search results list which contains
	 * list of keywords separated by space, city and radius so user can delete
	 * the items and perform the search
	 * 
	 * @param session
	 * @return
	 */
	public List<HashMap<String, Object>> getCurrentSearchResultsList(
			HttpSession session) {
		// Load the current search results list
		List<HashMap<String, Object>> currentSearchList = new ArrayList<HashMap<String, Object>>();
		@SuppressWarnings(UNCHECKED)
		HashMap<String, String> sessionMap = (HashMap<String, String>) session
				.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP);

		// if search is not browse by then check for the current search list
		if (sessionMap.get(MMJBCommonConstants.SEARCH_TYPE) != null) {
			String keyWords = sessionMap.get(SearchParamDTO.KEYWORDS).trim();
			if (!keyWords.isEmpty()) {
				String[] keyWordslist = keyWords.split(SPACE);
				for (String keyWord : keyWordslist) {
					if (!keyWord.trim().isEmpty()) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put(MMJBCommonConstants.HASHMAP_KEY,
								SearchParamDTO.KEYWORDS);
						map.put(MMJBCommonConstants.HASHMAP_VALUE, keyWord);
						currentSearchList.add(map);
					}
				}
			}
			String city = sessionMap.get(SearchParamDTO.CITY_STATE).trim();
			if (!city.isEmpty()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(MMJBCommonConstants.HASHMAP_KEY,
						SearchParamDTO.CITY_STATE);
				map.put(MMJBCommonConstants.HASHMAP_VALUE, city);
				currentSearchList.add(map);
			}
			String radius = sessionMap.get(SearchParamDTO.RADIUS).trim();
			// TODO: validate for refine radius search
			if (!radius.isEmpty()
					&& !radius.equalsIgnoreCase(MMJBCommonConstants.ZERO)) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(MMJBCommonConstants.HASHMAP_KEY, SearchParamDTO.RADIUS);
				map.put(MMJBCommonConstants.HASHMAP_VALUE, radius
						+ MMJBCommonConstants.MILES);
				currentSearchList.add(map);
			}
		}
		session.removeAttribute(sessionMap.get(MMJBCommonConstants.SEARCH_TYPE));
		return currentSearchList;
	}

	/**
	 * removing session for search results grid
	 * 
	 * @param session
	 */
	private void clearSession(HttpSession session) {
		// TODO :Need to Use sessionMap
		LOGGER.debug("Removing from session....");
		session.removeAttribute(MMJBCommonConstants.SEARCH_RESULTS_LIST);
		session.removeAttribute(MMJBCommonConstants.CITY);
		session.removeAttribute(MMJBCommonConstants.STATE);
		session.removeAttribute(MMJBCommonConstants.COMPANY);
		session.removeAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST);
		session.removeAttribute(MMJBCommonConstants.NO_OF_PAGES);
		session.removeAttribute(MMJBCommonConstants.CURRENT_PAGE);
		session.removeAttribute(MMJBCommonConstants.JOBSEACRH_PAGE_SIZE);
		// session.removeAttribute(MMJBCommonConstants.RECORDS_COUNT);
		session.removeAttribute(MMJBCommonConstants.TOTAL_NO_RECORDS);
		session.removeAttribute(MMJBCommonConstants.START_ROW);
		session.removeAttribute(MMJBCommonConstants.END_ROW);
		session.removeAttribute(MMJBCommonConstants.BEGIN_VAL);
		session.removeAttribute(MMJBCommonConstants.BEGIN);
		session.removeAttribute(MMJBCommonConstants.DISPLAY_RADIUS);
		session.removeAttribute(MMJBCommonConstants.SEARCHED_JOBSCOUNT);

		// Remove the category pages enabling variables from session
		session.removeAttribute("jobTitlePage");
		session.removeAttribute("employerPage");
		session.removeAttribute("locationPage");
		session.removeAttribute("list");
		session.removeAttribute("areaPage");

	}

	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 * @param JobSearchViewDetailForm
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/saveThisJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveThisJob(Map<String, Object> map, HttpServletRequest request,
			@RequestParam("id") int jobId, HttpSession session,
			@RequestParam(CURRENT_URL) String currentUrl,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		clickController.getclickevent(jobId,
				MMJBCommonConstants.CLICKTYPE_SAVEJOB, request, response);

		// Get the Job details
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);

		// Check for job seeker login ,open popup if not logged in.
		if (!jobSearchValidator.isLoggedIn(jobId, jobDTO.getJobTitleEncode(),
				currentUrl, session, request)) {
			jsonObject.put(ajaxNavigationPath, request.getContextPath()
					+ "/search/jobseekersaveThisJobPopUp");
			return jsonObject;
		}

		int userId = getUserID(session);
		int savedJobsCount = 0;
		try {
			List<AppliedJobDTO> savedJobDTOList = jobSeekerJobDetailService
					.getSavedJobs(userId);
			if (null != savedJobDTOList) {
				savedJobsCount = savedJobDTOList.size();
			}
			if (savedJobsCount >= Integer.parseInt(saveJobsLimit)) {
				int oldJobId = savedJobDTOList.get(savedJobDTOList.size() - 1)
						.getSaveJobId();
				jobSeekerJobDetailService.updateAppliedSavedJobs(oldJobId);
			}
		} catch (JobBoardException e) {
			LOGGER.error(
					"Error occured while getting the saved job of curresponding  user or while updating the particular job details",
					e);
		}

		// Validate if job is already applied
		AppliedJobDTO appliedJobDTO = jobSearchService.fetchSavedOrAppliedJob(
				jobDTO, userId);
		if (appliedJobDTO != null) {
			if (appliedJobDTO.getAppliedDt() == null) {
				saveThisJobErrMsg = saveThisJobErrMsg.replace("?",
						appliedJobDTO.getCreateDt().toString());
				jsonObject.put(ajaxMsg, saveThisJobErrMsg);
				return jsonObject;
			} else {
				applyJobErrMsg = applyJobErrMsg.replace("?", appliedJobDTO
						.getAppliedDt().toString());
				jsonObject.put(ajaxMsg, applyJobErrMsg);
				return jsonObject;
			}
		}

		// save the applied job in DB
		AppliedJobDTO saveJobDTO = new AppliedJobDTO();
		Date currentDate = new Date();
		JobPostDTO jpJob = new JobPostDTO();
		jpJob.setJobId(jobId);
		saveJobDTO.setJpJob(jpJob);
		saveJobDTO.setUserId(userId);
		saveJobDTO.setJobTitle(jobDTO.getJobTitle());
		saveJobDTO.setFacilityName(jobDTO.getCompanyNameDisp());
		saveJobDTO.setCreateDt(currentDate.toString());
		saveJobDTO.setAppliedDt(null);
		saveJobDTO.setDeleteDt(null);
		jobSearchService.saveOrApplyJob(saveJobDTO);
		jsonObject.put(ajaxMsg, saveThisJobSuccessMsg);
		return jsonObject;
	}

	/**
	 * The method is called to close the SaveThisJob popup
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jobseekersaveThisJobPopUp")
	public ModelAndView callSaveThisJobPopUp() {
		return new ModelAndView("jobseekersaveThisJobPopUp");
	}

	/**
	 * The method is called to close the SaveThisJob popup
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jobseekerApplyJobPopUp")
	public ModelAndView callJobseekerApplyJobPopUp() {
		return new ModelAndView("jobseekerApplyJobPopUp");
	}

	/**
	 * The method is called to post the resume
	 * 
	 * @param session
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jobseekerPostYourResume")
	public @ResponseBody
	JSONObject callPostYourResumePopUp(HttpSession session,
			Map<String, Object> map, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();

		// Check for job seeker login ,open popup if not logged in.
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			map.put("loginForm", new LoginForm());
			String loginPath = navigationPath.substring(2);
			String jonseekerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + jobseekerPageExtention;
			jsonObject.put("LoggedInNavigationPath", jonseekerloginUrl);
			return jsonObject;
		}
		jsonObject
				.put(ajaxNavigationPath,
						request.getRequestURL().toString()
								.replace(request.getServletPath(), "")
								+ "/jobSeekerResume/createResumePopUp.html?resumeType=createResume");
		return jsonObject;
	}

	/**
	 * The method is called to navigate job seeker to login
	 * 
	 * @param JobSearchViewDetailForm
	 * @return
	 */
	@RequestMapping(value = "/navigateToLogin")
	public ModelAndView navigateToLogin(Map<String, Object> model) {
		/**
		 * Maintain the saving job detail in session
		 */
		model.put("loginForm", new LoginForm());
		return new ModelAndView("jobSeekerLogin");
	}

	/**
	 * This method is called to forward to Advance job search page
	 * 
	 * @param request
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/advanceSearch", method = RequestMethod.GET)
	public ModelAndView advanceSearch(HttpSession session,
			JobseekerAdvanceSearchForm jobseekerAdvanceSearchForm,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();

		// JobseekerAdvanceSearchForm jobseekerAdvanceSearchForm = new
		// JobseekerAdvanceSearchForm();
		// List<RadiusDTO> radiusList =
		// populateDropdownsService.getRadiusList();
		// List<ExcludeFromDTO> excludeFromList =
		// populateDropdownsService.getExcludeFromList();
		// List<FromZipcodeDTO> fromZipcodeList =
		// populateDropdownsService.getFromZipcodeList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		// List<MetroAreaDTO> metroAreaList =
		// populateDropdownsService.getMetroAreaList();
		// List<EmploymentTypeDTO> employmentTypeList =
		// populateDropdownsService.getEmploymentTypeList();
		// List<JobPostedDateDTO> jobPostedDateList =
		// populateDropdownsService.getJobPostedDateList();

		LOGGER.debug("State List=" + stateList.size());

		model.addObject("stateList", stateList);
		model.addObject("jobseekerAdvanceSearchForm",
				jobseekerAdvanceSearchForm);

		model.setViewName("jobboardadvancedsearch");
		// get the Ads
		populateAds(request, session, model,
				PageNames.JOBSEEKER_ADVC_JOB_SEARCH, 0);
		clearSession(session);
		return model;
	}

	/**
	 * This method is called to send job to a friend for call the Mail page open
	 * and here hold URl userid and job id etc.
	 * 
	 * @author kartikm
	 * @version V.0.1
	 * @param sendtofriendmail
	 *            -
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendtofriend", method = RequestMethod.GET)
	public ModelAndView sendToFriend(SendToFriend sendtofriendmail,
			BindingResult result, HttpServletRequest request, Model model,
			HttpServletResponse response) {

		try {
			int jobId = Integer.parseInt(request.getParameter("id"));
			clickController.getclickevent(jobId,
					MMJBCommonConstants.CLICKTYPE_EMAILFRIEND, request,
					response);
			String jobTitle = request.getParameter(JOBTITLE);
			jobTitle = jobTitle.replace(SPACE, "-").toLowerCase();

			/*
			 * InetAddress addr = InetAddress.getLocalHost(); String
			 * httpData="http://"; // Get IP Address byte[] ipAddr =
			 * addr.getAddress(); // Get hostname String hostname =
			 * addr.getHostName(); int port=request.getServerPort(); String
			 * pathData=hostname+":"+port; String
			 * fullLength=pathData.toString(); String
			 * folderPath=request.getServletPath(); String str[] =
			 * folderPath.split("/"); //int countString = str.length; String
			 * foldername=""; // for (int i=1;i<countString-1;i++) // {
			 * foldername=str[1].toString(); // } String
			 * pathValue="/"+foldername+"/".toString();
			 * 
			 * String fullPath=httpData+""+fullLength+""+pathValue+
			 * "jobsearch/jobview.html?id=" + jobId + "&currentUrl=" + parentId
			 * + "&clickType=view".toString();
			 */

			String fullPath = request
					.getRequestURL()
					.toString()
					.replace(
							request.getServletPath(),
							"/search/jobview/"
									+ jobId
									+ "/"
									+ jobTitle
											.replace(SPACE, "-")
											.toLowerCase()
											.replaceAll(
													MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
													"") + dothtmlExtention);
			sendtofriendmail.setJobId(jobId);
			sendtofriendmail.setJoburl(fullPath);
			model.addAttribute("joburl", fullPath);
			model.addAttribute("jobId", request.getParameter("id"));
			model.addAttribute(CURRENT_URL, request.getParameter(CURRENT_URL));
			model.addAttribute("sendtofriendmail", sendtofriendmail);
		} catch (Exception e) {
			LOGGER.error("ERROR",e);
		}

		return new ModelAndView("jobseekersendtofriendpopup");
	}

	/**
	 * Mail Sending method sendTofriendPost take Bean file Binding result and
	 * Http servlet request and Session for Many place it is hold User id and
	 * facilityid
	 * 
	 * @author kartikm
	 * @version V.0.1
	 * @param sendtofriendmail
	 * @param result
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/sendtofriendpost", method = RequestMethod.POST)
	public String sendToFriendPost(
			@ModelAttribute("sendtofriendmail") SendToFriend sendtofriendmail,
			BindingResult result, HttpServletRequest request,
			HttpSession session) {
		ModelAndView modelData = new ModelAndView();
		Boolean status = Boolean.TRUE;
		String finalmailbody;
		StringBuffer mesg = new StringBuffer();
		try {
			String data = sendtofriendmail.getEmail().toString();
			String name = sendtofriendmail.getName();
			if ((null == data.trim())
					|| (MMJBCommonConstants.EMPTY.equals(data.trim()))) {
				return emailMsgBlank;
			}
			if((null == name.trim())
					|| (MMJBCommonConstants.EMPTY.equals(name.trim()))){
				return emailMsgBlank;
			}
			
			data = data.replace(',', ';');
			int len = data.length();
			if (data.charAt(len - 1) == ';') {
				data = data.substring(0, len - 1);
			}
			String str[] = data.split(";");
			int countString = str.length;

			try {
				int userId = 0;
				String userName = null;
				String userEmail = null;
				String jobseekerName = null;
				if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
					userId = getUserID(session);
					userName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
					userEmail = (String) session
							.getAttribute(MMJBCommonConstants.USER_EMAIL);
				}
				EmailDTO jobSeekerEmailDTO = new EmailDTO();
				jobSeekerEmailDTO.setFromAddress(webMailServer);

				int iterationCount = 0;
				InternetAddress[] jobSeekerToAddress = new InternetAddress[str.length];
				for (String string : str) {

					if (!jobSearchValidator.validateEmailPattern(string.trim())) {
						return emailMsg;
					}

					jobSeekerToAddress[iterationCount] = new InternetAddress(
							string.trim());
					iterationCount++;

				}
				jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
				/*if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {

					jobseekerName = name;
				} else {
					jobseekerName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
				}*/
				String subject = subOfmail.replace("?Jobseekername",
						name);
				jobSeekerEmailDTO.setSubject(subject);
				JobDTO jobDTO = jobSearchService
						.viewJobDetails(sendtofriendmail.getJobId());

				String emailBody = bodyOfMailBody.replace("?Jobseekername",
						name);
				emailBody = emailBody
						.replace("?Jobtitle", jobDTO.getJobTitle());
				emailBody = emailBody.replace("?Companyname",
						jobDTO.getCompanyNameDisp());
				emailBody = emailBody.replace("?joburl",
						sendtofriendmail.getJoburl());
				emailBody = emailBody.replace("?msgBody",
						sendtofriendmail.getMessage());

				mesg = mesg.append(emailConfiguration.getProperty(
						"jobseeker.email.header").trim());
				mesg = mesg.append(emailBody);

				mesg = mesg.append(emailConfiguration.getProperty(
						"email.footer").trim());
				jobSeekerEmailDTO.setBody(mesg.toString());
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
			} catch (Exception e) {
				LOGGER.error(errSendingMail,e);
			}

		} catch (Exception e) {
			status = Boolean.FALSE;
			throw new MailParseException(e);
		}
		modelData.setViewName(urlRedirectMail);
		return "";

	}

	/**
	 * This method will be used for Autocomplete for city, state or Postcode and
	 * Return List<String>.
	 * 
	 * @param String
	 *            keyword
	 * @return List<String> Object
	 */

	@RequestMapping(value = "/findLocation", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	List<String> findLocation(@RequestParam("term") String keyword) {

		List<LocationDTO> locationDTOList = lookupService
				.locationSearch(keyword.trim());
		// Chk for alphanumeric keyword : if so show zip codes otherwise city,
		// state
		if (locationDTOList != null) {
			/*
			 * Returning the List<String> based on Post code search or CityState
			 * search
			 */
			if (MMUtils.isAlphaNumeric(keyword)) {
				return MMUtils.convertToPostcodeStringList(locationDTOList);
			} else {
				return MMUtils.convertToCityStateStringList(locationDTOList);
			}
		}

		return null;
	}

	/**
	 * Get the jobboardsearchresultsBody page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jobboardsearchresultsBody")
	public ModelAndView getjobboardsearchresultsBody(
			HttpServletResponse response, HttpServletRequest request,
			Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("basePath", request.getRequestURL().toString()
				.replace(request.getServletPath(), ""));
		modelAndView.setViewName("jobboardsearchresultsBody");
		return modelAndView;
	}

	/**
	 * Gets the photo.
	 *
	 * @param imageId the image id
	 * @param response the response
	 * @param request the request
	 * @param brandingTemplateForm the branding template form
	 * @return the photo
	 */
	@RequestMapping("/viewImage")
	public void getPhoto(@RequestParam("id") String imageId,
			HttpServletResponse response, HttpServletRequest request,
			BrandingTemplateForm brandingTemplateForm) {

		try {
			BufferedImage originalImage = ImageIO.read(new File(imageId));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage,
					imageId.substring(imageId.length() - 3, imageId.length()),
					baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
			// Display the image
			write(response, result.getBody());
		} catch (Exception e) {

			LOGGER.error(e);

		}
	}
	
	/**
	 * The method helps to get the testimonial for employer by 
	 * testimonial id.
	 * 
	 * @param testimonyId
	 * @param response
	 * @param request
	 * @param brandingTemplateForm
	 * @return
	 */
	@RequestMapping("/viewTestimonial")
	public ModelAndView enlargeTestimonial(@RequestParam("id") int testimonyId,
			HttpServletResponse response, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		BrandingTemplateForm brandingTemplateForm = new BrandingTemplateForm();
		String templateId = request.getParameter("templateId");
		String testimonialTxt = null;
		if (templateId != null) {
			BrandingTemplateDTO brandingTemplateDTO = brandingTemplateService
					.editBrandingTemplate(Integer.parseInt(templateId));
			for (TestimonyDTO testimonialDto : brandingTemplateDTO
					.getListTestimony()) {
				if (testimonialDto.getTestimonyId() == testimonyId) {
					testimonialTxt = testimonialDto.getTestimony();
					break;
				}
			}
		}
		brandingTemplateForm.setTestimonyContainer(testimonialTxt);
		model.addObject("brandingTemplateForm", brandingTemplateForm);
		model.setViewName("viewTestimony");
		return model;
	}

	/**
	 * Writes the report to the output stream
	 */
	public void write(HttpServletResponse response, byte[] byteArray) {
		ServletOutputStream outputStream = null;
		try {
			// Retrieve the output stream
			outputStream = response.getOutputStream();
			// Write to the output stream
			outputStream.write(byteArray);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * Handle get my bytes request.
	 *
	 * @param imageInByte the image in byte
	 * @return the response entity
	 */
	public ResponseEntity<byte[]> handleGetMyBytesRequest(byte[] imageInByte) {
		// Get bytes from somewhere...
		byte[] byteData = imageInByte;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(byteData.length);

		return new ResponseEntity<byte[]>(byteData, responseHeaders,
				HttpStatus.OK);
	}

	/**
	 * The method is called to read the branding information from NetSuite.
	 * 
	 * @param form
	 * @param facility_id
	 * @return JobDTO
	 */
	public JobDTO checkBrand(JobDTO dto) {
		int packageId = MMJBCommonConstants.INT_SILVER;
		JobDTO jobDTO = dto;
		List<String> purchasedPackages = null;
		// Getting the customer ID from Adm Facility table.
		int nsCustomerID = brandingTemplateService
				.getNSCustomerIDFromAdmFacility(jobDTO.getFacilityId());

		purchasedPackages = brandingTemplateService
				.getBrandingInformation(nsCustomerID);
		if (null != purchasedPackages && !purchasedPackages.isEmpty()) {
			if (purchasedPackages.contains(PLATINUM_90)
					|| purchasedPackages
							.contains(PLATINUM_180)
					|| purchasedPackages
							.contains(PLATINUM_365)) {
				jobDTO.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_PLATINUM;
			} else if (purchasedPackages.contains(GOLD_90)
					|| purchasedPackages.contains(GOLD_180)
					|| purchasedPackages.contains(GOLD_365)) {
				jobDTO.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_GOLD;
			} else {
				jobDTO.setIsSilverCustomer(Boolean.TRUE);
			}
		} else {
			jobDTO.setIsSilverCustomer(Boolean.TRUE);
		}

		jobDTO.setPackageId(packageId);
		return jobDTO;
	}

	/**
	 * The method delete selected search item from the current search list which
	 * is listed on keyword, city/Zip and radius selected and delete the radius
	 * if the city is deleted and perform the search on remaining search items.
	 * 
	 * @param session
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteCurrentSearch")
	public @ResponseBody
	JSONObject deleteCurrentSearch(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Model model) {
		JSONObject jsonObject = new JSONObject();
		// boolean status = true;
		try {
			String key = request.getParameter(MMJBCommonConstants.HASHMAP_KEY);
			String value = request
					.getParameter(MMJBCommonConstants.HASHMAP_VALUE);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(MMJBCommonConstants.HASHMAP_KEY, key);
			map.put(MMJBCommonConstants.HASHMAP_VALUE, value);
			LOGGER.debug("Deleting the current search : "
					+ map.get(MMJBCommonConstants.HASHMAP_KEY) + "-"
					+ map.get(MMJBCommonConstants.HASHMAP_VALUE));
			@SuppressWarnings(UNCHECKED)
			List<HashMap<String, Object>> currentSearchList = (List<HashMap<String, Object>>) session
					.getAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST);
			currentSearchList.remove(map);
			Map<String, String> sessionMap = checkSessionMap
					.getSearchSessionMap(session);
			jsonObject.put(MMJBCommonConstants.AUTOLOAD, true);
			if (key.equalsIgnoreCase(SearchParamDTO.KEYWORDS)) {
				String keyWord = sessionMap.get(SearchParamDTO.KEYWORDS);
				keyWord = keyWord.replace(value, MMJBCommonConstants.EMPTY);
				jsonObject.put(SearchParamDTO.KEYWORDS, keyWord);
			} else if (key.equalsIgnoreCase(SearchParamDTO.CITY_STATE)) {
				String city = sessionMap.get(SearchParamDTO.CITY_STATE);
				city = city.replace(value, MMJBCommonConstants.EMPTY);
				jsonObject.put(SearchParamDTO.CITY_STATE, city);
				String radius = sessionMap.get(SearchParamDTO.RADIUS);
				if (!radius.equalsIgnoreCase(MMJBCommonConstants.ZERO)) {
					radius = radius.replace(value, MMJBCommonConstants.ZERO);
					jsonObject.put(SearchParamDTO.RADIUS, radius
							+ MMJBCommonConstants.MILES);
				}
			} else if (key.equalsIgnoreCase(SearchParamDTO.RADIUS)) {
				String radius = sessionMap.get(SearchParamDTO.RADIUS);
				radius = radius.replace(value, MMJBCommonConstants.ZERO);
				jsonObject.put(SearchParamDTO.RADIUS, radius
						+ MMJBCommonConstants.MILES);
			}

			session.removeAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST);
			session.setAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST,
					currentSearchList);
		} catch (Exception e) {
			LOGGER.error("JobSearchController : deleteCurrentSearch Ex :" + e);
		}
		return jsonObject;
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
	 * This method converts the video file path to playable video URL
	 * 
	 * @param jobDTO
	 * @param request
	 * @return listVideoURL
	 */
	public List<String> setVideoURL(JobDTO jobDTO, HttpServletRequest request) {
		List<VideoDTO> listVideoDTO = jobDTO.getListVideos();
		List<String> listVideoURL = new ArrayList<String>();
		StringBuffer videoURL = null;

		if (null != listVideoDTO && !listVideoDTO.isEmpty()) {
			for (VideoDTO dto : listVideoDTO) {
				videoURL = new StringBuffer();

				videoURL.append(request.getRequestURL().toString()
						.replace(request.getRequestURI(), MMJBCommonConstants.EMPTY));
				videoURL.append(mediaPath);
				int index = 0;
				String path = dto.getMediaPath();
				index = dto.getMediaPath().lastIndexOf('/');
				if (index == -1) {
					index = dto.getMediaPath().lastIndexOf('\\');
				}
				listVideoURL.add(videoURL.append(path.substring(index + 1))
						.toString());
			}
		}
		return listVideoURL;
	}

	/**
	 * The method helps to save the search results criteria in DB on every
	 * keyword search and display the recent searches list
	 * 
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	private void recentSearch(HttpSession session) {

		HashMap<String, String> sessionMap = (HashMap<String, String>) session
				.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		session.removeAttribute(LATEST_RECENT_LIST);
		// get the user Id from session
		int userId = getUserID(session);

		SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();
		searchedJobsDTO.setUserID(userId);
		searchedJobsDTO.setUrl(MMJBCommonConstants.SEARCH_TYPE
				+ MMJBCommonConstants.EQUAL_TO
				+ sessionMap.get(MMJBCommonConstants.SEARCH_TYPE)
				+ MMJBCommonConstants.SEMICOLON + SearchParamDTO.KEYWORDS
				+ MMJBCommonConstants.EQUAL_TO
				+ sessionMap.get(SearchParamDTO.KEYWORDS)
				+ MMJBCommonConstants.SEMICOLON + SearchParamDTO.CITY_STATE
				+ MMJBCommonConstants.EQUAL_TO
				+ sessionMap.get(SearchParamDTO.CITY_STATE)
				+ MMJBCommonConstants.SEMICOLON + SearchParamDTO.RADIUS
				+ MMJBCommonConstants.EQUAL_TO
				+ sessionMap.get(SearchParamDTO.RADIUS));
		searchedJobsDTO.setSearchName(MMJBCommonConstants.EMPTY);
		searchedJobsDTO.setCreatedDate(MMUtils.getCurrentDateAndTime());

		List<SaveSearchedJobsDTO> recentSearches = saveSearchService
				.viewMySavedSearches(userId, true);

		// If the searches are exceeding the limit then delete the old search
		if (recentSearches.size() >= Integer.parseInt(recentSearchsLimit)) {
			for (int i = Integer.parseInt(recentSearchsLimit); i <= recentSearches
					.size(); i++) {
				int saveSearchId = recentSearches.get(i - 1).getSaveSearchID();
				saveSearchService.deleteSavedSearch(saveSearchId);
			}
		}

		// save the search criteria
		SaveSearchedJobsDTO currentSearch = saveSearchService
				.saveSearchedJobs(searchedJobsDTO);

		recentSearches.add(currentSearch);
		// get the latest searches and check for latest searches limit
		List<SaveSearchedJobsDTO> latestSearches = recentSearches;
		if (recentSearches.size() > MMJBCommonConstants.LATEST_SEARCHES_LIMIT) {
			latestSearches = recentSearches.subList(0,
					MMJBCommonConstants.LATEST_SEARCHES_LIMIT);
		}
		session.setAttribute(LATEST_RECENT_LIST, latestSearches);
	}

	/**
	 * The method helps to clear the latest recent searches
	 * 
	 * @param session
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clearrecentsearches", method = RequestMethod.GET)
	public @ResponseBody
	String clearrecentsearches(HttpSession session, HttpServletRequest request) {
		// get the userId from session
		int userId = getUserID(session);

		// clear the recent searches
		jobSearchService.clearRecentSearches(userId,
				Integer.parseInt(recentSearchsLimit));
		session.removeAttribute(LATEST_RECENT_LIST);

		return "success";
	}

	/**
	 * The method helps to get the searches history and get the list of latest
	 * searches and returns the history list page.
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/jobboardSearchesHistory")
	public ModelAndView getJobboardSearchesHistoryPage(
			HttpServletResponse response, HttpServletRequest request,
			Model model, HttpSession session) {
		LOGGER.debug("Get the latest searches of user ");

		ModelAndView modelAndView = new ModelAndView();
		session.removeAttribute(LATEST_RECENT_LIST);
if(session.getAttribute(MMJBCommonConstants.FACILITY_ID)==null){
		// get the UserId from session
		int userId = getUserID(session);
		if (userId != 0) {

			// get the recent searches of user
			List<SaveSearchedJobsDTO> recentSearches = saveSearchService
					.viewMySavedSearches(userId, true);

			// get the latest searches and check for latest searches limit
			List<SaveSearchedJobsDTO> latestSearches = recentSearches;
			if (recentSearches.size() > MMJBCommonConstants.LATEST_SEARCHES_LIMIT) {
				latestSearches = recentSearches.subList(0,
						MMJBCommonConstants.LATEST_SEARCHES_LIMIT);
			}

			session.setAttribute(LATEST_RECENT_LIST, latestSearches);
		}
}
		modelAndView.setViewName("jobboardSearchesHistory");
		return modelAndView;
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
			try {
			final JSONObject jobSrchJson = new JSONObject();
			jobSrchJson.put(MMJBCommonConstants.AD_TEXT,
			// MMUtils.isNull(jobDTO.getAdText()));
					MMUtils.isNull(Jsoup.parse(jobDTO.getAdText()).text()));
			if(jobDTO.getBlindAd() == 0){
				jobSrchJson.put(MMJBCommonConstants.CAP_COMPANY,
						MMUtils.isNull(jobDTO.getCompany()));
			}else{
				jobSrchJson.put(MMJBCommonConstants.CAP_COMPANY,
						MMJBCommonConstants.BLIND_AD_COMPANY_TXT);
			}
			jobSrchJson.put(MMJBCommonConstants.JOB_TITLE,
					MMUtils.isNull(jobDTO.getJobTitle()));
			String title = MMUtils.isNull(jobDTO.getJobTitle());
			if (!title.isEmpty()) {
				jobSrchJson
						.put(MMJBCommonConstants.JOB_TITLE_ENCODE,
								title.replaceAll(
										MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
										""));
				/*try {
					jobSrchJson
					.put(MMJBCommonConstants.JOB_TITLE_ENCODE_URL,
							URLEncoder.encode(title, "ISO-8859-1"));
				} catch (UnsupportedEncodingException e) {
					LOGGER.error(e.getMessage(), e);
				}*/
			} else {
				jobSrchJson.put(MMJBCommonConstants.JOB_TITLE_ENCODE, title);
			}
			StringBuffer location = new StringBuffer();
			if (jobDTO.getHideCity() == 0) {
				location.append(null == jobDTO.getCity()?MMJBCommonConstants.EMPTY:jobDTO.getCity());
				if (jobDTO.getHideState() == 0 && null != jobDTO.getCity()) {
					location = formatProperText(location);
					location.append(MMJBCommonConstants.COMMA + SPACE);
				}
			}
			if (jobDTO.getHideState() == 0 && null != jobDTO.getState()) {
				location.append(jobDTO.getState());
			}
			if (jobDTO.isUniversalGeo()
					&& null != jSResultDTO.getUniversalLocation()
					&& !jSResultDTO.getUniversalLocation().isEmpty()) {
				jobSrchJson.put(MMJBCommonConstants.CAP_CITY,
						jSResultDTO.getUniversalLocation());
			} else {
				jobSrchJson.put(MMJBCommonConstants.CAP_CITY,
						jobDTO.isUniversalGeo() ? MMJBCommonConstants.UINIVERSAL_GEO_TXT
								: location.toString());
			}

			jobSrchJson.put(MMJBCommonConstants.POSTED_DATE, DateUtils
					.convertDateStringToDisplayDatePattern(jobDTO
							.getPostedDate().toString()));
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
			// TODO: add facility Id from solr Check for feature employer : if
			// it expires then remove feature employer
			/*
			 * boolean isFeaturedEmp = false; if (jobDTO.isFeatured() &&
			 * jobDTO.getFacilityId() != 0) { isFeaturedEmp =
			 * manageFeaturedEmployerProfile
			 * .validateFeaturedEmp(jobDTO.getFacilityId()); }
			 * jobSrchJson.put(MMJBCommonConstants.IS_FEATURED, isFeaturedEmp);
			 */
			jobSrchJson.put(MMJBCommonConstants.IS_FEATURED,
					jobDTO.isFeatured());

			jobSrchJson.put(MMJBCommonConstants.FACILITY_ID,
					jobDTO.getFacilityId());

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
		} catch (Exception e) {
			LOGGER.error("Error Occured while converting to job to JSON",e);
		}

		}
		// Update Views for the list of jobs which appeared in the search
		clickService.saveJobViews(jobDTOList);
		try {
		// Get the refine results along with the job count
		fetchRefineResults(jSResultDTO.getFacetMap(), jobSrchJsonObj);

		// Get the location region list
		getLocationRegionResults(jSResultDTO.getFacetMap(), jobSrchJsonObj);
		} catch (Exception e) {
			LOGGER.error("Error Occured while converting to job to JSON",e);
		}
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jSResultDTO.getResultCount());
		jobSrchJsonObj.put(MMJBCommonConstants.JSON_ROWS, jsonRows);

		return jobSrchJsonObj;
	}

	/**
	 * This method converts normal text to proper text
	 * 
	 * @param text
	 * @return properText
	 */
	private StringBuffer formatProperText(StringBuffer text) {
		StringBuffer properText = new StringBuffer();
		if (null != text && !text.toString().isEmpty()) {
			String[] textArray = text.toString().split(SPACE);
			for (int i = 0; i < textArray.length; i++) {
				properText.append(textArray[i].substring(0, 1).toUpperCase()
						+ textArray[i].substring(1).toLowerCase()+SPACE);
			}
		}
		return properText.replace(0, properText.length(), properText.toString().trim());
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

		// Get the list of job positions along with the job count
		List<String> jobPositionDisplayList = generateRefineResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_JOBPOSITION));

		// Get the list of cities along with the job count
		List<String> cityDisplayList = generateRefineResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_CITY));

		// Get the list of states along with the job count
		List<String> stateDisplayList = generateRefineResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_STATE));

		// Get the list of Employers along with the job count
		List<String> employerDisplayList = generateRefineResults(searchFacetDTOMap
				.get(SearchFacetDTO.FACET_COMPANY));
		
		// Get the list of Employers Id with Name along with the job count
		List<String> employerIdDisplayList = generateRefineResults(searchFacetDTOMap
						.get(SearchFacetDTO.FACET_COMPANY_ID_NAME));

		jobSrchJsonObj.put(MMJBCommonConstants.JOB_POSITION,
				jobPositionDisplayList);
		jobSrchJsonObj.put(MMJBCommonConstants.CITY, cityDisplayList);
		jobSrchJsonObj.put(MMJBCommonConstants.STATE, stateDisplayList);
		jobSrchJsonObj.put(MMJBCommonConstants.COMPANY, employerDisplayList);
		jobSrchJsonObj.put(MMJBCommonConstants.COMPANY_ID_NAME, employerIdDisplayList);

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
					displayFacet = displayFacet.concat(SPACE);
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
	 * browse category pages.
	 * 
	 * @param modelAndView
	 * @param request
	 *            TODO
	 * @param category
	 * @param request
	 */
	private void addSEODetailsForBrowsePages(ModelAndView modelAndView,
			HttpServletRequest request, String category) {

		String metaTitle = null;
		String metaDesc = null;
		// set the meta title and description tags value for job titles list
		// page.
		if (category.equalsIgnoreCase("title")) {
			metaTitle = seoConfiguration.getProperty(
					"browsepage.jobtitleslist.meta.title").trim();
			metaDesc = seoConfiguration.getProperty(
					"browsepage.jobtitleslist.meta.description").trim();
		}else if (category.equalsIgnoreCase("employer")) {
			// set the meta title and description tags value for employers list
			// page.
			metaTitle = seoConfiguration.getProperty(
					"browsepage.employerslist.meta.title").trim();
			metaDesc = seoConfiguration.getProperty(
					"browsepage.employerslist.meta.description").trim();
		}else if (category.equalsIgnoreCase("location")) {
			// set the meta title and description tags value for locations list
			// page.
			metaTitle = seoConfiguration.getProperty(
					"browsepage.locationslist.meta.title").trim();
			metaDesc = seoConfiguration.getProperty(
					"browsepage.locationslist.meta.description").trim();
		}else if (category.equalsIgnoreCase("areas")) {
			// set the meta title and description tags value for areas list page.
			String stateFullName = modelAndView.getModelMap().get(
					"stateFullName").toString().trim();
			metaTitle = seoConfiguration.getProperty(
					"browsepage.location.areaslist.meta.title").trim();
			metaTitle = metaTitle.replace(LOCNAME_REPLACE_WORD,
					stateFullName);
			metaDesc = seoConfiguration.getProperty(
					"browsepage.location.areaslist.meta.description").trim();
			metaDesc = metaDesc.replace(LOCNAME_REPLACE_WORD, stateFullName);
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
	 * @param category
	 * @param categoryDesc
	 * @param jobCount
	 */
	private void addSEODetailsForJobsSearchPages(ModelAndView modelAndView,
			HttpServletRequest request, String category, String[] categoryDesc,
			int jobCount) {

		String metaTitle = null;
		String metaDesc = null;
		// set the meta title and description tags value for job titles list
		// page.
		if (category.equalsIgnoreCase(JOBTITLE)) {
			//Check the DB for seo Info
			AdminSeoDTO seoDTO = jobSearchService.getSeoInfoByJobTitle(categoryDesc[0]);
			String staticContent = null;
			if(seoDTO == null){
				metaTitle = seoConfiguration.getProperty(
						"jobsearchpage.jobtitle.meta.title").trim();
				metaTitle = metaTitle.replace(JOBTITLE_REPLACE_WORD,
						categoryDesc[0]);
				metaDesc = seoConfiguration.getProperty(
						"jobsearchpage.jobtitle.meta.description").trim();
				metaDesc = metaDesc.replace(JOBTITLE_REPLACE_WORD, categoryDesc[0].toLowerCase());
				metaDesc = metaDesc.replace(Q_JOBSCOUNT, String.valueOf(jobCount));
				
			}else{
				metaTitle = seoDTO.getMetaTitle();
				metaDesc = seoDTO.getMetaDesc();
				staticContent = seoDTO.getStaticContent();
			}
			modelAndView.addObject("staticContent", staticContent);
		}else if (category.equalsIgnoreCase(BROWSE_BY_EMPLOYER)) {
			// set the meta title and description tags value for employers list
			// page.
			metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.employer.meta.title").trim();
			metaTitle = metaTitle.replace("?employer", categoryDesc[0]);
			metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.employer.meta.description").trim();
			metaDesc = metaDesc.replace("?employer", categoryDesc[0]);
			metaDesc = metaDesc.replace(Q_JOBSCOUNT, String.valueOf(jobCount));
		}else if (category.equalsIgnoreCase(LOCATION)) {
			// set the meta title and description tags value for locations list
			// page.
			metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.location.meta.title").trim();
			metaTitle = metaTitle
					.replace(LOCNAME_REPLACE_WORD, categoryDesc[0]);
			metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.location.meta.description").trim();
			metaDesc = metaDesc.replace(LOCNAME_REPLACE_WORD, categoryDesc[0]);
			metaDesc = metaDesc.replace(Q_JOBSCOUNT, String.valueOf(jobCount));
		}else if (category.equalsIgnoreCase(AREA)) {
			// set the meta title and description tags value for areas list page.
			metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.location.area.meta.title").trim();
			metaTitle = metaTitle.replace("?area", categoryDesc[0]);
			metaTitle = metaTitle
					.replace(LOCNAME_REPLACE_WORD, categoryDesc[1]);
			metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.location.area.meta.description").trim();
			metaDesc = metaDesc.replace("?area", categoryDesc[0]);
			metaDesc = metaDesc.replace(LOCNAME_REPLACE_WORD, categoryDesc[1]);
			metaDesc = metaDesc.replace(Q_JOBSCOUNT, String.valueOf(jobCount));
		}

		modelAndView.addObject("metaDesc", metaDesc);
		modelAndView.addObject("metaTitle", metaTitle);
		modelAndView.addObject("canonicalUrl", request.getRequestURL());
	}

	/**
	 * * This method is used to create parameter map and populate the required
	 * values into it.
	 * 
	 * @param jobSearchResultForm
	 * @param sortByParam
	 * @param session
	 * @param request
	 * @return
	 */
	@SuppressWarnings(UNCHECKED)
	private Map<String, String> getParameterMap(
			JobSearchResultForm jobSearchResultForm, String sortByParam,
			HttpSession session, HttpServletRequest request) {

		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);

		Map<String, String> paramMap = new HashMap<String, String>();

		// saving the previous job search keywords in session to populate ads
		if (sessionMap.get(SearchParamDTO.KEYWORDS) != null) {
			session.setAttribute(MMJBCommonConstants.PREV_JOB_SEARCH_KEYWORDS,
					sessionMap.get(SearchParamDTO.KEYWORDS));
		}
		// for fresh job search parameter is not assigned and pagination purpose
		// it is false value
		boolean freshjobsearch = true;
		if (request.getParameter(FRESH_JOB_SRCH) != null) {
			freshjobsearch = Boolean.valueOf(request
					.getParameter(FRESH_JOB_SRCH));
		}
		if (freshjobsearch) {
			LOGGER.debug("The fresh job search is started.");
			paramMap = new HashMap<String, String>();
			int searchSeq = 0;
			String sessionId = null;
			sessionId = session.getId();
			if (sessionMap.get(SearchParamDTO.SEARCH_SEQ) == null) {
				paramMap.put(SearchParamDTO.SEARCH_SEQ,
						String.valueOf(searchSeq + 1));
			} else {
				paramMap.put(SearchParamDTO.SEARCH_SEQ,
						String.valueOf(Integer.parseInt(sessionMap
								.get(SearchParamDTO.SEARCH_SEQ)) + 1));
			}
			paramMap.put(MMJBCommonConstants.SEARCH_TYPE, jobSearchResultForm
					.getSearchtype().trim());
			String firstFQParam = MMJBCommonConstants.EMPTY;
			if (request.getAttribute(MMJBCommonConstants.FIRST_FQ_PARAM) != null) {
				firstFQParam = MMJBCommonConstants.FQ_JOB_POSITION
						+ request
								.getAttribute(MMJBCommonConstants.FIRST_FQ_PARAM)
						+ '"';
			}
			String secondFQParam = MMJBCommonConstants.EMPTY;
			if (request.getAttribute(MMJBCommonConstants.SECOND_FQ_PARAM) != null) {
				secondFQParam = MMJBCommonConstants.FQ_COMPANY
						+ request
								.getAttribute(MMJBCommonConstants.SECOND_FQ_PARAM)
						+ '"';
			}
			String thirdFQParam = MMJBCommonConstants.EMPTY;
			if (request.getAttribute(MMJBCommonConstants.THIRD_FQ_PARAM) != null) {
				if(request.getAttribute(MMJBCommonConstants.IS_FQ_PARAM_LOWERCASE) != null){
					thirdFQParam = MMJBCommonConstants.FQ_STATE_LOWER_CASE
							+ request
									.getAttribute(MMJBCommonConstants.THIRD_FQ_PARAM)
							+ '"';
				}else{
				thirdFQParam = MMJBCommonConstants.FQ_STATE
						+ request
								.getAttribute(MMJBCommonConstants.THIRD_FQ_PARAM)
						+ '"';
				}
			}
			String fouthFQParam = MMJBCommonConstants.EMPTY;
			if (request.getAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM) != null) {
				if(request.getAttribute(MMJBCommonConstants.IS_FQ_PARAM_LOWERCASE) != null){
					fouthFQParam = MMJBCommonConstants.FQ_CITY_LOWER_CASE
							+ request
									.getAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM)
							+ '"';
				}else{
				fouthFQParam = MMJBCommonConstants.FQ_CITY
						+ request
								.getAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM)
						+ '"';
				}
			}
			String fifthFQParam = MMJBCommonConstants.EMPTY;
			if (request.getAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM) != null) {
				if(request.getAttribute(MMJBCommonConstants.IS_FQ_PARAM_LOWERCASE) != null){
					fifthFQParam = MMJBCommonConstants.FQ_AREA_LOWER_CASE
							+ request
							.getAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM)
							+ '"';
				}else{
				fifthFQParam = MMJBCommonConstants.FQ_AREA
						+ request
								.getAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM)
						+ '"';
				}
			}
			paramMap.put(MMJBCommonConstants.FacilityId_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			paramMap.put(MMJBCommonConstants.FacilityId_NAME_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			String facetSort = MMJBCommonConstants.INDEX_STR;
			// set the sort order for search results
			String sortOrder = setSortOrder(session, request);

			// Putting all the parameters coming from the UI into a Map for
			// further
			// processing.
			Map<String, String> fqParamMap = getFQMap(firstFQParam,
					secondFQParam, thirdFQParam, fouthFQParam, fifthFQParam,
					sortOrder, facetSort);
			if (jobSearchResultForm.getKeywords() != null) {
				paramMap.put(SearchParamDTO.KEYWORDS, jobSearchResultForm
						.getKeywords().trim());
			} else {
				paramMap.put(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);
			}
			if (jobSearchResultForm.getCityState() != null) {
				paramMap.put(SearchParamDTO.CITY_STATE, jobSearchResultForm
						.getCityState().trim());
			} else {
				paramMap.put(SearchParamDTO.CITY_STATE,
						MMJBCommonConstants.EMPTY);
			}
			if (jobSearchResultForm.getRadius() != null) {
				paramMap.put(SearchParamDTO.RADIUS, jobSearchResultForm
						.getRadius().trim());
			} else {
				paramMap.put(SearchParamDTO.RADIUS, MMJBCommonConstants.EMPTY);
			}
			paramMap.put(SearchParamDTO.REFINED,
					String.valueOf(jobSearchResultForm.isRefined()));
			// }
			paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
			// set the search name
			paramMap.put(SearchParamDTO.SEARCH_NAME, jobSearchResultForm
					.getSearchName().trim());
			paramMap.put(MMJBCommonConstants.SEARCH_TYPE, jobSearchResultForm
					.getSearchtype().trim());

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
			// Check for search name in session map for save search
			// functionality
			if (session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) != null) {
				String saveSearchId = ((Map<String, String>) session
						.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP))
						.get(MMJBCommonConstants.SAVE_SEARCH_ID);

				String performSearch = ((Map<String, String>) session
						.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP))
						.get(MMJBCommonConstants.PERFORM_SAVED_SEARCH);
				if (saveSearchId != null) {
					paramMap.put(MMJBCommonConstants.SAVE_SEARCH_ID,
							saveSearchId);
					paramMap.put(MMJBCommonConstants.PERFORM_SAVED_SEARCH,
							performSearch);
				}
			}
			// code to save the current URL for 'return search results' link in view
			// details page
			//session.removeAttribute(MMJBCommonConstants.BROWSE_JOB_VIEW_LINK);
		} else {
			LOGGER.debug("Loading the search parameters from session");
			paramMap = (HashMap<String, String>) session
					.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
			// set the sort order for search results
			String sortOrder = setSortOrder(session, request);
			paramMap.put(MMJBCommonConstants.SORT_ORDER, sortOrder);

			// display the radius list if location search is enabled
			if (paramMap.get(SearchParamDTO.SEARCH_NAME).equalsIgnoreCase(
					MMJBCommonConstants.LOCATION_SEARCH)) {
				session.setAttribute(MMJBCommonConstants.DISPLAY_RADIUS, true);
			}

			// Parameter value setting if its refined search
			if (jobSearchResultForm.isRefined()) {
				setRefineParams(jobSearchResultForm, request, paramMap);
			}
		}
		session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, paramMap);
		return paramMap;

	}

	/**
	 * Load the refine result parameters to paramMap.
	 * 
	 * @param jobSearchResultForm
	 * @param request
	 * @param paramMap
	 */
	private void setRefineParams(JobSearchResultForm jobSearchResultForm,
			HttpServletRequest request, Map<String, String> paramMap) {
		paramMap.put(SearchParamDTO.REFINED,
				String.valueOf(jobSearchResultForm.isRefined()));
		String fQParamName = request.getParameter("refineKey");
		String fQParamVal = request.getParameter("refineVal");
		// Commented the usage of {!tag=dt} as per the new requirement
		if (fQParamName.equalsIgnoreCase(MMJBCommonConstants.FIRST_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
				// MMJBCommonConstants.FQ_REFINE_KEYWORD +
						MMJBCommonConstants.FQ_JOB_POSITION + fQParamVal + "\"");
			}
		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.SECOND_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
				// MMJBCommonConstants.FQ_REFINE_KEYWORD +
						MMJBCommonConstants.FQ_COMPANY + fQParamVal + "\"");
			}

		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.THIRD_FQ_PARAM)) {

			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
				// MMJBCommonConstants.FQ_REFINE_KEYWORD +
						MMJBCommonConstants.FQ_STATE + fQParamVal + "\"");
			}

		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.FOURTH_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
				// MMJBCommonConstants.FQ_REFINE_KEYWORD +
						MMJBCommonConstants.FQ_CITY + fQParamVal + "\"");
			}

		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.FIFTH_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
				// MMJBCommonConstants.FQ_REFINE_KEYWORD +
						MMJBCommonConstants.FQ_AREA + fQParamVal + "\"");
			}
		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.REFINERADIUS)) {

			if (fQParamVal.isEmpty()) {
				paramMap.put(SearchParamDTO.RADIUS, String.valueOf(0));
				paramMap.put(MMJBCommonConstants.REFINERADIUS,
						String.valueOf(0));
			} else {
				paramMap.put(SearchParamDTO.RADIUS, fQParamVal);
				paramMap.put(MMJBCommonConstants.REFINERADIUS, fQParamVal);
			}
		}
	}

	/**
	 * This method will be used for doing Job search and Return a JSON Object
	 * which will later be parsed at the UI end and all the results will be
	 * displayed
	 * 
	 * @param jobSearchResultForm
	 * @param result
	 * @param model
	 * @return JSON Object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchJob(HttpSession session,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		JSONObject jobSrchJsonObj = null;
		// clear session attributes
		clearSession(session);

		// validate the inputs
		JobSearchResultDTO jobSearchResultDTO = null;
		boolean freshjobsearch = true;
		if (request.getParameter(FRESH_JOB_SRCH) != null) {
			freshjobsearch = Boolean.valueOf(request
					.getParameter(FRESH_JOB_SRCH));
		}
		if (freshjobsearch) {
			// clear sessions of selected employer and state for keyword search
			session.getAttribute(BROWSE_BY_EMPLOYER);
			session.getAttribute(BROWSE_BY_STATE);

			// get the search name
			String searchName = getSearchNameForSearch(jobSearchResultForm,
					session, request);
			jobSearchResultForm.setSearchName(searchName);
			jobSearchResultForm
					.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

			jobSrchJsonObj = jobSearchValidator.validateJobSearch(
					jobSearchResultForm, session);
			if (jobSrchJsonObj != null) {
				return jobSrchJsonObj;
			} else {
				if (session.getAttribute("origanlCityStateVal") != null) {
					jobSearchResultForm.setCityState((String) session
							.getAttribute("origanlCityStateVal"));
					session.removeAttribute("origanlCityStateVal");
				}
			}
		}

		// merge the parameters and set it in session
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				MMJBCommonConstants.POSTED_DT, session, request);

		int recordsPerPage = MMJBCommonConstants.JOBSEACRH_DEFAULT_PAGE_SIZE;
		int page = 1;
		int noOfRecords = 0;
		if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
			page = Integer.parseInt(request
					.getParameter(MMJBCommonConstants.PAGE));
		}
		if (request.getParameter(MMJBCommonConstants.JOBSEACRH_PAGE_SIZE) != null) {
			recordsPerPage = Integer.parseInt(request
					.getParameter(MMJBCommonConstants.JOBSEACRH_PAGE_SIZE));
		}
		long start = (page - 1) * recordsPerPage;
		long rows = recordsPerPage;
		try {
			// Calling the jobSearch() of Service layer from getting the
			// object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);
			noOfRecords = (int) jobSearchResultDTO.getResultCount();
			//
			if (jobSearchResultDTO != null) {
				jobSearchResultDTO.setUniversalLocation(jobSearchResultForm.getCityState());
				// Calling the service layer for converting the
				// JobSearchResultDTO
				// object into JSON Object
				jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
			}
		} catch (JobBoardException e) {
			LOGGER.error(ERROR_SOLR + e);
		}

		// get the current search items
		List<HashMap<String, Object>> currentSearchList = getCurrentSearchResultsList(session);
		session.setAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST,
				currentSearchList);
		// set the pagination values in session
		setSessionForGrid(session, request, jobSearchResultDTO, page,
				recordsPerPage, noOfRecords, jobSrchJsonObj);

		// populate the ads for search results grid
		populateAds(request, session, jobSrchJsonObj, recordsPerPage);

		// save the search results to DB to set the list of recent searches
		// by
		// checking user login , keyword search and location search
		if (freshjobsearch
				&& getUserID(session) != 0
				&& session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) != null
				&& !((HashMap<String, String>) session
						.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP)).get(
						SearchParamDTO.SEARCH_NAME).equalsIgnoreCase(
						MMJBCommonConstants.BROWSE_SEARCH)) {
			recentSearch(session);
		}
		String jobCount = NumberFormat.getInstance().format(
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		String jobSearchMatchInfo = seoConfiguration.getProperty(
				JOB_SRCH_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_JOBSCOUNT, jobCount);
		if (jobSearchResultForm.getKeywords() != null) {
			jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD,
					jobSearchResultForm.getKeywords().trim());
			if (null != jobSearchResultForm.getCityState()
					&& !jobSearchResultForm.getCityState().isEmpty()) {
				jobSearchMatchInfo = jobSearchMatchInfo
						.replace(Q_CITYSTATE, "in "
								+ jobSearchResultForm.getCityState().trim());
			}
		} else {
			jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD,
					MMJBCommonConstants.EMPTY);
		}
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_CITYSTATE,
				MMJBCommonConstants.EMPTY);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);

		return jobSrchJsonObj;
	}

	/**
	 * Help to set the pagination values, refined result data in session
	 * 
	 * @param session
	 * @param request
	 * @param jobSearchResultDTO
	 * @param page
	 * @param recordsPerPage
	 * @param noOfRecords
	 * @param jobSrchJsonObj
	 */
	private void setSessionForGrid(HttpSession session,
			HttpServletRequest request, JobSearchResultDTO jobSearchResultDTO,
			int page, int recordsPerPage, int noOfRecords,
			JSONObject jobSrchJsonObj) {
		String next = request.getParameter(MMJBCommonConstants.NEXT);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null == next || next.isEmpty()) {
			beginVal = (page / MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT)
					* MMJBCommonConstants.JOBSEARCH_GRID_PAGES_COUNT;
		} else {
			beginVal = Integer.parseInt(next);
			// page = Integer.parseInt(next);
		}

		session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
				* recordsPerPage + 1));
		session.setAttribute(MMJBCommonConstants.END_ROW,
				(((page - 1) * recordsPerPage) + jobSearchResultDTO
						.getResultList().size()));
		session.setAttribute(MMJBCommonConstants.BEGIN_VAL, beginVal);
		session.setAttribute(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
		session.setAttribute(MMJBCommonConstants.CURRENT_PAGE, page);
		session.setAttribute(MMJBCommonConstants.BEGIN, (beginVal <= 0 ? 1
				: beginVal));
		session.setAttribute(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		session.setAttribute(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		session.setAttribute(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
		session.setAttribute(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		session.setAttribute(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
				NumberFormat.getInstance().format(jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS)));
	}

	/**
	 * This method is used to get the total number of Active jobs.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/activeJobs", method = RequestMethod.GET)
	public @ResponseBody
	String activeJobs() {
		long totalNoOfActiveJobs = 0;
		try {
			JobSearchResultDTO jobSearchResultDTO = null;
			// merge the parameters

			jobCountparamMap.put(SearchParamDTO.KEYWORDS,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.CITY_STATE,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.RADIUS,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.REFINED, String.valueOf(false));
			jobCountparamMap.put(SearchParamDTO.SESSION_ID,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.SEARCH_NAME,
					MMJBCommonConstants.BROWSE_SEARCH);
			jobCountparamMap.put(MMJBCommonConstants.SEARCH_TYPE,
					MMJBCommonConstants.BASIC_SEARCH_TYPE);
			jobCountparamMap.put(MMJBCommonConstants.SORT_PARAM,
					MMJBCommonConstants.POSTED_DT);
			jobCountparamMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FacilityId_NAME_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FacilityId_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.SORT_ORDER,
					MMJBCommonConstants.DESC_STR);
			jobCountparamMap.put(MMJBCommonConstants.FACET_SORT,
					MMJBCommonConstants.INDEX_STR);
			jobCountparamMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(0));
			try {
				jobSearchResultDTO = jobSearchService.jobSearch(
						jobCountparamMap, 0, 0);

			} catch (JobBoardException e) {
				LOGGER.error("Error occured while getting the Job Search Result from SOLR...");
			}
			if (jobSearchResultDTO != null) {
				totalNoOfActiveJobs = (int) jobSearchResultDTO.getResultCount();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return String.valueOf(NumberFormat.getInstance().format(totalNoOfActiveJobs));
	}
	
	/**
	 *  The method generate the site map for application.
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	/*@RequestMapping(value = "/generatesitemap", method = RequestMethod.GET)
	public ModelAndView startSiteMapGeneratot(HttpServletRequest request, Model model,
			HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		File myDir = new File(System.getProperty("catalina.home")
				+ appRootPath);
		boolean status = true;
		WebSitemapGenerator wsg;
		try {
			String websiteAdd = request.getRequestURL().toString()
					.replace(request.getServletPath(), "");
			wsg = new WebSitemapGenerator(websiteAdd, myDir);
			JobSearchResultDTO jobSearchResultDTO = null;
			JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();

			// Set the search type for SOLR facets
			String searchName = MMJBCommonConstants.KEYWORD_SEARCH;
			jobSearchResultForm.setSearchName(searchName);
			jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

			// merge the parameters
			int searchSeq = MMJBCommonConstants.ZERO_INT;
			String sessionId = "";
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put(SearchParamDTO.KEYWORDS, "*");
			paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
			paramMap.put(SearchParamDTO.SEARCH_SEQ,
					String.valueOf(searchSeq + 1));
			paramMap.put(SearchParamDTO.SEARCH_NAME, searchName.trim());

			// For testing. Remove it while committing
			paramMap.put(MMJBCommonConstants.SORT_PARAM, MMJBCommonConstants.POSTED_DT);
			paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			paramMap.put(MMJBCommonConstants.SORT_ORDER,
					MMJBCommonConstants.DESC_STR);
			paramMap.put(MMJBCommonConstants.FACET_SORT,
					MMJBCommonConstants.INDEX_STR);

			try {
				int totalJobs = Integer.parseInt(activeJobs().replace(",", ""));
				int divider = (int) Math.ceil((double)totalJobs/20);
				for(int i = 1 ; i<=20 ; i++){
					long start = (i - 1) * divider;
					long rows = divider;
					List<JobDTO> jobDTOList = null;
					WebSitemapUrl url = null;
					String title = null;
					String jobId = null;
					try {
						jobSearchResultDTO = jobSearchService.jobSearch(
								paramMap, start, rows);
						jobDTOList = jobSearchResultDTO.getResultList();
					} catch (JobBoardException je) {
						LOGGER.error(je.getMessage(), je);
						status = false;
					}catch (Exception e) {
						LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
					}
					if(jobDTOList != null){
					for (JobDTO jobDTO : jobDTOList) {
						title = MMUtils.isNull(jobDTO.getJobTitle()).trim();
						jobId = MMUtils.isNull(String.valueOf(jobDTO.getJobId()));
						if (!title.isEmpty()) {
							title = title
									.replaceAll(
											MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
											"").toLowerCase().replace(" ", "-");
						} 
						url = new WebSitemapUrl.Options(websiteAdd+"/search/jobview/"+jobId+"/"+title+".html")
						.lastMod(new java.util.Date()).priority(1.0).changeFreq(ChangeFreq.HOURLY).build();
						wsg.addUrl(url);
					}
					}
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				status = false;
			}
			wsg.write();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			status = false;
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			status = false;
		}
		modelAndView.addObject("status", status);
		modelAndView.setViewName("generatesitemap");
		return modelAndView;
	}*/
	
		
}
