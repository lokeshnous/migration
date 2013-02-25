/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.admin.web.controller;

import java.util.ArrayList;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.AdminSeoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobTitleDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.web.controller.JobPostForm;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * @author muralikc
 * 
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes("adminLoginForm")
@Scope("session")
public class AdminController extends AbstractController{
	
	/** The Constant ADMIN_DASHBOARD. */
	private static final String ADMIN_DASHBOARD = "adminDashboard";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AdminController.class);

	// private static final String = null;

	/** The employer job post. */
	@Autowired
	private JobPostService employerJobPost;

	/** The admin validation. */
	@Autowired
	private AdminValidation adminValidation;

	/** The admin service. */
	@Autowired
	private ProfileRegistration adminService;
	
	/** The job search service. */
	@Autowired
	private JobSearchService jobSearchService;
	
	/** The facility service. */
	@Autowired
	private FacilityService facilityService;
	
	/** The user service. */
	@Autowired
	private UserService userService;


	/** The transform admin impersonation. */
	@Autowired
	private TransformAdminImpersonation transformAdminImpersonation;

	/** The service. */
	@Autowired
	private AdminService service;
	
	/** The login err msg. */
	@Value("${loginErrMsg}")
	private String loginErrMsg;
	
	/** The ad service. */
	@Autowired
	private AdService adService;
	
	/** The seo configuration. */
	@Autowired
	@Resource(name = "seoConfiguration")
	private Properties seoConfiguration;
	
	/** The logger configuration. */
	@Autowired
	@Resource(name = "logConfiguration")
	private Properties logConfiguration;
	
	/** The Constant LOGINFORM. */
	private static final String LOGINFORM ="adminLoginForm";
	
	/**
	 * Admin login.
	 *
	 * @param error the error
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/loginPage")
	public String adminLogin(@RequestParam(value = "error", required = false) boolean error,ModelMap model){
		if (error) {
			model.put("error", loginErrMsg);
		}
		else {
			model.put("error", MMJBCommonConstants.EMPTY);
		}
		return "adminLoginPage";
	}
	
	/**
	 * Admin menu page.
	 *
	 * @param map the map
	 * @param request the request
	 * @param session the session
	 * @return the model and view
	 */
	@RequestMapping(value = "/adminMenu", method = RequestMethod.GET)
	public ModelAndView adminMenuPage(ModelMap map, HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		AdminLoginForm adminLoginForm = new AdminLoginForm();
		model.addObject(adminLoginForm);
		populateAds(request, session, model);
		model.setViewName("adminLogin");
		return model;
	}

	/**
	 * The method helps to populate the ads for the page 
	 * 
	 * @param session
	 * @param request
	 * @param model
	 */
	private void populateAds(HttpServletRequest request, HttpSession session,
			ModelAndView model) {
		// Add the Ads 
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.ADMIN_LOGIN);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception e) {
			LOGGER.error("Error occurred while getting the html content for Ads"
					, e);
		}
	}

	/**
	 * Admin impersonation page.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/login")
	public ModelAndView adminImpersonationPage() {
		ModelAndView model = new ModelAndView();
		AdminLoginForm adminLoginForm = new AdminLoginForm();
		model.addObject("adminLoginForm", adminLoginForm);
		model.setViewName("adminImpersonation");
		return model;

	}

	/**
	 * Impersonate the user.
	 *
	 * @param form the form
	 * @param result the result
	 * @param session the session
	 * @return the string
	 */
	@ResponseBody
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String impersonateTheUser(
			@ModelAttribute(LOGINFORM) AdminLoginForm form, BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		adminValidation.validate(form, result);
		model.setViewName("adminLogin");
		if (result.hasErrors()) {
			return result.getFieldError().getDefaultMessage();
		}
		if (!adminService.validateEmail(form.getEmpOrAgencyEmail())) {
			return "Not a registered Employer / Agency!";
		}
		int adminUserId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
		form.setAdminUserId(adminUserId);
		AdminDTO adminDTO = transformAdminImpersonation
				.transformAdminFormToDTO(form);
		boolean impersonationResult = service.impersonateUser(adminDTO);
		if(impersonationResult){
			return "true";
		}
		return "false";
	}

	/**
	 * 
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/editJobPosting")
	public ModelAndView editJobPosting(HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.removeAttribute("postedJobList");
		model.setViewName("adminEditJobPosting");
		return model;

	}

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer1/jobInventorypopup", method = RequestMethod.GET)
	public ModelAndView jobInventory(HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.removeAttribute("empList");
		session.removeAttribute("nsId");
		model.addObject("pageName", ADMIN_DASHBOARD);
		model.setViewName("adminEditJobPostInventory");
		return model;
	}

	/**
	 * This method to get Manage / Edit facility group
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer1/manageFacilityGroup", method = RequestMethod.GET)
	public ModelAndView manageFacilityGroup(
			@ModelAttribute("adminForm") AdminForm adminForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.removeAttribute("empList");
		session.removeAttribute("nsId");
		model.addObject("pageName", ADMIN_DASHBOARD);
		model.setViewName("manageFacilityGroup");
		return model;
	}

	/**
	 * @author kartikm Called a function to get the adminEditJobSave page. for
	 *         search display
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @param result
	 * @return jsonObject
	 */
	@RequestMapping(value = "/manageEditJobSearch", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getJobPostDetails(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform, BindingResult result) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("record","");
		try {
			String advJobId = request.getParameter("advJobId");
			session.removeAttribute("postedJobList");
			List<JobPostDTO> postedJobList = new ArrayList<JobPostDTO>();
			List<JobPostDTO> jobPostDetailList = new ArrayList<JobPostDTO>();
			int advSearchId = Integer.parseInt(advJobId);
			postedJobList = employerJobPost
					.retrieveAllJobPostByADvSearch(advSearchId);
			if(postedJobList.isEmpty()){
				jsonObject.put("record","no record");
			}
			boolean scheduleOrDraft=employerJobPost.checkDraftAndSchedule(advSearchId);
			if(scheduleOrDraft){
				jsonObject.put("record","scheduleOrDraft");
			}
			// Get the email_id who has posted the job
			if (postedJobList.size() > 0) {

				int mainFacilityId = facilityService.getParentFacility(
						postedJobList.get(0).getFacilityId()).getFacilityId();

				FacilityDTO facilityDTO = facilityService
						.getFacilityByFacilityId(mainFacilityId);
				for (JobPostDTO jobPostDTO : postedJobList) {
					if (null != facilityDTO && facilityDTO.getUserId() > 0) {
						UserDTO userDTO = userService
								.getUserByUserId(facilityDTO.getUserId());
						jobPostDTO.setEmailId(userDTO.getEmailId());
					}
					jobPostDetailList.add(jobPostDTO);
				}
			}
			if(!jobPostDetailList.isEmpty() && !scheduleOrDraft){
			session.setAttribute("postedJobList", jobPostDetailList);
			}

		} catch (Exception e) {
			LOGGER.error("Manager Edit Job Posting Search Option");
		}
		return jsonObject;
	}

	/**
	 * @author kartikm Called a function to get the adminEditJobSave page. for
	 *         search display
	 * @param response
	 * @param request
	 * @param model
	 * @return modelAndView
	 */
	@RequestMapping(value = "/adminEditJobSave")
	public ModelAndView getAdminEditJobSave(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminEditJobSave");
		return modelAndView;
	}

	/**
	 * @author kartikm This is the save method call when date change and save
	 *         button click then it is Change from active, inactive, Draft,
	 *         Expired
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manageEditJobSearchSave", method = RequestMethod.GET)
	public String getJobPostDetailsSave(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform, BindingResult result) {

		try {
			JobPostDTO dto = new JobPostDTO();
			String advJobId = request.getParameter("advJobId");
			int jobId = Integer.parseInt(advJobId);
			String endDate = request.getParameter("endDate");
			String startDate = request.getParameter("startDate");
			dto.setJobId(jobId);
			dto.setStartDt(startDate);
			dto.setEndDt(endDate);

			employerJobPost.jobSaveByAdmin(dto, jobId);
		} catch (Exception e) {
			LOGGER.error("Manager Edit Job Posting Search Option");
		}
		return "";
	}
	
	/**
	 * The method helps to list out all job titles to edit SEO information.
	 * 
	 * @param request
	 * @param session
	 */
	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public ModelAndView jobsByCategory(HttpSession session,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, List<String>> titlesByName = new TreeMap<String, List<String>>();
		Set<String> titleKeysList = new HashSet<String>();
		int totalEmployers = 0;
		try {
			List<JobTitleDTO> positionList = jobSearchService.getJobTitleList();
			// set the employers list in dictionary format
			for (JobTitleDTO titleDTO : positionList) {
				if (titleDTO != null
						&& !titleDTO.getJobtitle().trim().isEmpty()) {
					String nameLetter = titleDTO.getJobtitle().substring(0, 1)
							.toUpperCase();
					if (titleKeysList.add(nameLetter)) {
						List<String> jobList = new ArrayList<String>();
						jobList.add(titleDTO.getJobtitle());
						titlesByName.put(nameLetter, jobList);
					} else {
						titlesByName.get(nameLetter)
								.add(titleDTO.getJobtitle());
					}
					totalEmployers++;
				}
			}
			int totalKeyCount = titlesByName.keySet().size();
			int rowsCount = (int) Math
					.ceil((double) (totalEmployers + totalKeyCount) / 3);
			Map<Integer, TreeMap<String, List<HashMap<String, String>>>> list = new TreeMap<Integer, TreeMap<String, List<HashMap<String, String>>>>();
			Iterator<Entry<String, List<String>>> keyIt = titlesByName
					.entrySet().iterator();
			int i = 0, j = 1;
			// check for blocks if in first column its exceeded
			TreeMap<String, List<HashMap<String, String>>> sets = new TreeMap<String, List<HashMap<String, String>>>();
			List<HashMap<String, String>> empDetail;
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
				empDetail = new ArrayList<HashMap<String, String>>();
				List<String> empNames = entry.getValue();
				HashMap<String, String> empNamesEncode;
				for (String empName : empNames) {
					empNamesEncode = new HashMap<String, String>();
					empNamesEncode.put("jobtitle", empName);
					empNamesEncode.put("encodeJobtitle",
							MMUtils.encodeString(empName));
					empDetail.add(empNamesEncode);
				}
				sets.put(entry.getKey(), empDetail);
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
			modelAndView
					.addObject("firstColPositionList", firstColPositionList);
			modelAndView.addObject("secColPositionList", secColPositionList);
			modelAndView
					.addObject("thirdColPositionList", thirdColPositionList);
			modelAndView.addObject("jobTitlePage", true);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		modelAndView.addObject("jobTitlePage", true);
		modelAndView.setViewName("jobboardadmintitleslist");
		return modelAndView;
	}
	
	/**
	 * The method helps to show SEO popup by retrieving value from property file or DB 
	 * on presence.  
	 * 
	 * @param session
	 * @param jobTitle
	 * @return
	 */
	@RequestMapping(value = "/search/{jobtitle}", method = RequestMethod.GET)
	public ModelAndView adminTitlePopup(HttpSession session,
			@PathVariable("jobtitle") String jobTitle) {
		ModelAndView modelAndView = new ModelAndView();
		AdminSeoForm adminSeoForm = new AdminSeoForm();
		jobTitle = MMUtils.decodeString(jobTitle);
		adminSeoForm.setJobTitle(jobTitle);
		AdminSeoDTO seoDTO = jobSearchService.getSeoInfoByJobTitle(jobTitle);
		if (seoDTO == null) {
			String metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.jobtitle.meta.title").trim();
			metaTitle = metaTitle.replace("?jobtitle", jobTitle);
			String metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.jobtitle.meta.description").trim();
			metaDesc = metaDesc.replace("?jobtitle", jobTitle.toLowerCase());

			adminSeoForm.setMetaDesc(metaDesc);
			adminSeoForm.setMetaTitle(metaTitle);
			adminSeoForm.setStaticContent("");
		} else {
			adminSeoForm.setMetaDesc(seoDTO.getMetaDesc());
			adminSeoForm.setMetaTitle(seoDTO.getMetaTitle());
			adminSeoForm.setStaticContent(seoDTO.getStaticContent());
			adminSeoForm.setSeoInfoId(seoDTO.getSeoInfoId());
		}
		modelAndView.addObject("adminSeoForm", adminSeoForm);
		modelAndView.setViewName("admineditseoInfopopup");
		return modelAndView;
	}

	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 * @param JobSearchViewDetailForm
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/saveseoinfo", method = RequestMethod.GET)
	public @ResponseBody
	String saveAdminSEOInfo(Map<String, Object> map,
			HttpServletRequest request,
			@ModelAttribute("adminSeoForm") AdminSeoForm adminSeoForm,
			HttpSession session, HttpServletResponse response) {		
		AdminSeoDTO seoDTO = new AdminSeoDTO();
		seoDTO.setSeoInfoId(adminSeoForm.getSeoInfoId());
		seoDTO.setJobTitle(adminSeoForm.getJobTitle());
		seoDTO.setMetaTitle(adminSeoForm.getMetaTitle());
		seoDTO.setMetaDesc(adminSeoForm.getMetaDesc());
		seoDTO.setStaticContent(adminSeoForm.getDescription());
		jobSearchService.saveJobTitleSeoInfo(seoDTO);
		return "success";

	}
	
	/**
	 * The method changes the logger level throughout the application.
	 * 
	 * @param request
	 * @param session
	 */
	@RequestMapping(value = "/manageLogLevel", method = RequestMethod.GET)
	public ModelAndView manageLogLevel(HttpSession session,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manageLoggerLevel");
		return modelAndView;
	}

	/**
	 * The method changes the logger level throughout the application.
	 * 
	 * @param request
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value = "/changeLogLevel", method = RequestMethod.GET)
	public JSONObject changeLogLevel(HttpSession session,
			HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String logLevel = request.getParameter("logLevel");
		jsonObject.put("status", "");
		try {
			if (logLevel.equalsIgnoreCase("INFO")
					|| logLevel.equalsIgnoreCase("DEBUG")
					|| logLevel.equalsIgnoreCase("TRACE")) {

				logConfiguration.setProperty("log4j.logger.com.advanceweb",
						logLevel.toUpperCase());
				LogManager.resetConfiguration();
				PropertyConfigurator.configure(logConfiguration);
				LOGGER.debug("Logger changed to level: "+ logLevel);
			}
		} catch (Exception e) {
			LOGGER.error(
					"An error occurred while setting the Logger level to: "
							+ logLevel, e);
			jsonObject.put("status", "error");
			return jsonObject;
		}

		return jsonObject;
	}
}
