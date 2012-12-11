package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.NewsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.VideoDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;
import com.advanceweb.afc.jb.employer.service.EmployerNewsFeedService;
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
@RequestMapping("/jobsearch")
public class JobSearchController extends AbstractController {

	private static final Logger LOGGER = Logger
			.getLogger(JobSearchController.class);

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private JobSeekerJobDetailService jobSeekerJobDetailService;

	@Autowired
	private JobSearchService jobSearchService;

	@Autowired
	private AdService adService;

	@Autowired
	private LookupService lookupService;

	@Autowired
	private CheckSessionMap checkSessionMap;

	@Autowired
	private SaveSearchService saveSearchService;

	@Autowired
	private BrandingTemplateService brandingTemplateService;

	@Autowired
	private EmployerNewsFeedService employerNewsFeedService;

	@Autowired
	private JobSearchValidator jobSearchValidator;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private CoverLetterService coverLetterService;

	@Autowired
	private ClickService clickService;

	@Value("${navigationPath}")
	private String navigationPath;

	@Value("${jobseekerJobApplicationSub}")
	private String jobseekerJobApplicationSub;

	@Value("${jobseekerJobApplicationBody}")
	private String jobseekerJobApplicationBody;

	@Value("${employeJobApplicationSub}")
	private String employeJobApplicationSub;

	@Value("${employeJobApplicationBody}")
	private String employeJobApplicationBody;

	@Value("${saveJobsLimit}")
	private String saveJobsLimit;

	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;

	@Value("${employerPageExtention}")
	private String employerPageExtention;

	@Value("${defaultResumeExtension}")
	private String defaultResumeExtension;

	@Autowired
	private ResumeService resumeService;

	@Value("${saveThisJobSuccessMsg}")
	private String saveThisJobSuccessMsg;

	@Value("${saveThisJobErrMsg}")
	private String saveThisJobErrMsg;

	@Value("${applyJobSuccessMsg}")
	private String applyJobSuccessMsg;

	@Value("${commonMailErrMsg}")
	private String commonMailErrMsg;

	@Value("${applyJobErrMsg}")
	private String applyJobErrMsg;

	@Value("${resumeNotFoundMsg}")
	private String resumeNotFoundMsg;

	@Value("${ajaxMsg}")
	private String ajaxMsg;

	@Value("${ajaxNavigationPath}")
	private String ajaxNavigationPath;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	private @Value("${SUBJECT_OF_MAIL}")
	String subOfmail;

	private @Value("${BODY_OFMAIL_FIRST}")
	String bodyOfMailFirst;

	private @Value("${BODY_OFMAIL_SECOND}")
	String bodyOfMailSecond;

	private @Value("${JOB_TITLE_HEADING}")
	String jobTitleHeading;

	private @Value("${COMAPNY_NAME_HEADING}")
	String cmpNameHeading;

	private @Value("${URL_LINK_FIRST}")
	String urlLinkFirst;

	private @Value("${URL_LINK_SECOND}")
	String urlLinkSecond;

	private @Value("${URL_REDIRECT_MAIL}")
	String urlRedirectMail;

	private @Value("${ERROR_SENDING_MAIL}")
	String errSendingMail;

	private @Value("${EMAIL_MESSAGE}")
	String emailMsg;

	private @Value("${WEB_MAIL_SERVER}")
	String webMailServer;

	private @Value("${EMAIL_MESSAGE_BLANK}")
	String emailMsgBlank;

	private @Value("${mediaPath}")
	String mediaPath;

	@Value("${recentSearchsLimit}")
	private String recentSearchsLimit;

	@Autowired
	@Resource(name = "seoConfiguration")
	private Properties seoConfiguration;
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Autowired
	private ClickController clickController;

	private static final String PLATINUM_LIST = "PlatinumNewsList";
	private static final String IS_SORTING = "isSorting";
	private static final String CURRENT_URL = "currentUrl";
	private static final String END_TAGS = "</TD></TR>\n";
	private static final String COMPANY_NAME = "?companyName";
	private static final String CITY = "?city";
	private static final String COUNTRY = "?country";
	private static final String STATE = "?state";
	private static final String JOBTITLE = "jobtitle";
	private static final String UNCHECKED = "unchecked";
	private static final String ERROR_SOLR = "Error occured while getting the Job Search Result from SOLR...";
	private static final String JOBBOARD_SEARCHRESULTS_PAGE = "jobboardsearchresults";
	private static final String JOB_SEARCH_RESULT_FORM = "jobSearchResultForm";
	private static final String JOBTITLE_REPLACE_WORD = "?jobtitle";
	private static final String LOCATION = "location";
	private static final String LOCNAME_REPLACE_WORD = "?locationname";
	private static final String JOB_SRCH_MTCH_INFO = "jobSearchMatchInfo";
	private static final String JOBSEARCH = "jobsearch";
	private static final String JOBS = "jobs";
	private static final String JOBS_URL = "jobsUrl";
	private static final String FTR_PAGE_MESSAGE = "footerpage.jobsurlmessage";
	private static final String JOBS_URL_TITLE = "jobsUrlTitle";
	private static final String JOB_SRCH_MATCH = "jobsearchpage.jobsearchmatchinfo";
	private static final String Q_KEYWORD = "?keyword";
	private static final String BROWSE_BY_EMPLOYER = "browseByEmployer";
	private static final String BROWSE_BY_STATE = "browseBystate";
	private static final String AREA = "area";
	private static final String LATEST_RECENT_LIST = "latestRecentList";
	private static final String Q_JOBSCOUNT = "?jobscount";
	private static final String FRESH_JOB_SRCH = "freshjobsearch";

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
					MMJBCommonConstants.CLICKTYPE_CLICK, request, response);
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
			modelView.addObject("basePath",request.getRequestURL().toString().replace(request.getServletPath(), ""));
			modelView.setViewName("jobseekerJobDetails");
			// get the Ads
			populateAds(request, session, modelView, PageNames.JOB_VIEW);
		} else {
			List<JobPostDTO> jobPostDTOList = jobSearchService
					.getRecentJobsPostedByEmployer(jobDTO.getFacilityId(),
							jobDTO.getJobId());

			// For getting the News feed from XML file
			Map<String, List<NewsDTO>> newsMap = employerNewsFeedService
					.getNewsFromXML();
			List<NewsDTO> newsDTOList = newsMap.get(PLATINUM_LIST);

			if (null != newsDTOList && newsDTOList.size() > 5) {
				List<NewsDTO> modNewsDTOList = newsDTOList.subList(0, 5);
				model.put("newsDTOList", modNewsDTOList);
			} else {
				model.put("newsDTOList", newsDTOList);
			}
			
			List<String> videoList = setVideoURL(jobDTO, request);
			model.put("jobDTOList", jobPostDTOList);
			model.put("videoList", videoList);
			modelView.setViewName("jobseekerJobDetailsTemplate");
			// get the Ads
			populateAds(request, session, modelView, PageNames.PREMIUM_JOB_VIEW);
		}
	}

	
	/**
	 * This method retrieves all the news related to Platinum customers
	 * 
	 * @return modelView
	 */
	@RequestMapping(value = "/getPlatinumNewsList")
	public ModelAndView getPlatinumNewsList(HttpServletRequest request, HttpSession session)
	{
		ModelAndView modelView = new ModelAndView();
		
		Map<String, List<NewsDTO>> newsMap = employerNewsFeedService
				.getNewsFromXML();
		List<NewsDTO> newsDTOList = newsMap.get(PLATINUM_LIST);
		modelView.addObject("newsDTOList", newsDTOList);
		modelView.setViewName("newsList");
		// get the Ads
		populateAds(request, session, modelView, PageNames.PREMIUM_JOB_VIEW);

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
	 */
	private void populateAds(HttpServletRequest request, HttpSession session,
			ModelAndView model, String pageName) {
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
				model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);
			}else if (pageName.equalsIgnoreCase(PageNames.JOBSEEKER_ADVC_JOB_SEARCH)) {
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
			metaTitle = metaTitle.replace(COMPANY_NAME + ",",
					MMJBCommonConstants.EMPTY);
			metaDesc = metaDesc.replace(COMPANY_NAME + ",",
					MMJBCommonConstants.EMPTY);
		} else {
			metaTitle = metaTitle.replace(COMPANY_NAME, jobDTO.getCompany());
			metaDesc = metaDesc.replace(COMPANY_NAME, jobDTO.getCompany());
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
	public ModelAndView trackClicks(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("id") int jobId,
			@RequestParam("clickType") String clickType) {

		ModelAndView modelAndView = new ModelAndView();

		if (clickType.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_CLICK)) {
			clickController.getclickevent(jobId, clickType, request, response);
		}

		return modelAndView;
	}

	/**
	 * Method called to fetch the public Cover Letter by user Id
	 * 
	 * @param userId
	 * @return
	 */
	private String fetchPublicCoverLetter(int userId) {
		ResCoverLetterDTO dto = coverLetterService
				.fetchPublicCoverLetter(userId);
		return dto.getCoverletterText();
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
	JSONObject applyJob(Map<String, Object> map, @RequestParam String userID,
			@RequestParam("id") int jobId,
			@RequestParam(CURRENT_URL) String currentUrl,
			HttpServletResponse response, HttpSession session,
			HttpServletRequest request) {

		JSONObject jsonObject = new JSONObject();
		clickController.getclickevent(jobId,
				MMJBCommonConstants.CLICKTYPE_APPLY, request, response);
		try {
			// Get the Job details
			JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
			JobApplyTypeDTO jobApplyTypeDTO = jobSearchService
					.applyJobDetails(jobId);
			if (!jobSearchValidator.validateApplyType(jobId, jsonObject,
					jobApplyTypeDTO)) {
				return jsonObject;
			}
			if (jobDTO.getEmail() == null) {
				jobDTO.setEmail(jobApplyTypeDTO.getApplyLink());
			}
			if (!jobSearchValidator.isLoggedIn(map, jobId,
					jobDTO.getJobTitle(), currentUrl, session, jsonObject,
					request)) {
				return jsonObject;
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
					int oldJobId = appliedJobDTOList.get(0).getSaveJobId();
					jobSeekerJobDetailService.updateAppliedSavedJobs(oldJobId);
				}
			} catch (JobBoardException e) {
				LOGGER.error("Error occured while getting the saved job of curresponding  user or while updating the particular job details"
						, e);
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

			// Fetch the public resume
			List<String> attachmentpaths = fetchPublicVisibleResume(userId);
			// Fetch the public Cover Letter
			String coverLetterText = fetchPublicCoverLetter(userId);
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
		String employerMailBody = employeJobApplicationBody.replace(
				"?empDashboardLink", employerloginUrl);
		employerMailBody = employerMailBody.replace("?jobseekername", userName);
		mailBody.append(emailConfiguration.getProperty("employer.email.header")
				.trim());

		if (coverLetterTxt == null) {
			mailBody.append(employerMailBody);
		} else {
			coverLetterTxt = coverLetterTxt.replace("\r\n", "<br/>");
			mailBody.append(coverLetterTxt + "<br/>" + employerMailBody);
		}

		mailBody.append(emailConfiguration.getProperty("email.footer").trim());
		employerEmailDTO.setBody(mailBody.toString());
		employerEmailDTO.setHtmlFormat(true);
		employerEmailDTO.setAttachmentPaths(attachmentpaths);
		emailService.sendEmail(employerEmailDTO);
		LOGGER.info("Mail sent to employer");

		// Send confirmation mail to job seeker regarding job
		// application
		EmailDTO jobSeekerEmailDTO = new EmailDTO();
		jobSeekerEmailDTO.setFromAddress(advanceWebAddress);
		InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
		jobSeekerToAddress[0] = new InternetAddress(userEmail);
		jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
		String jobseekerMailSub = "";
		String jonseekerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), loginPath)
				+ dothtmlExtention + jobseekerPageExtention;
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
		LOGGER.info("Mail sent to jobseeker");
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
	private List<String> fetchPublicVisibleResume(int userId) {
		List<String> attachmentpaths = null;
		try {
			ResumeDTO resumeDTO = resumeService
					.fetchPublicResumeByUserId(userId);
			if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
				// TODO: Need to clarify
				LOGGER.info("Resume type : resume type builder");
			} else if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
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
						LOGGER.info("File has been renamed.");
					}
					temp.deleteOnExit();

					// Write to temp file
					BufferedWriter out = new BufferedWriter(new FileWriter(
							newFile));
					out.write(resumeDTO.getResumeText());
					out.close();
					resumeDTO.setFilePath(newFile.getAbsolutePath());
				} catch (IOException e) {
					LOGGER.info("Copy Paste resume error");
				}
			} else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
				// TODO: Need to clarify
				LOGGER.info("Resume type : resume type upload");
			}
			if (resumeDTO.getFilePath() != null) {
				attachmentpaths = new ArrayList<String>();
				attachmentpaths.add(resumeDTO.getFilePath());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.info("Resume not found");
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
	public ModelAndView findJobPage(Map<String, JobSearchResultForm> model,
			HttpSession session, HttpServletRequest request) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);

		if (!sessionMap.isEmpty()) {

			String searchType = sessionMap.get(MMJBCommonConstants.SEARCH_TYPE);
			String radius = MMJBCommonConstants.EMPTY;
			String cityState = MMJBCommonConstants.EMPTY;
			String keywords = MMJBCommonConstants.EMPTY;
			String saveSearchName = MMJBCommonConstants.EMPTY;
			keywords = sessionMap.get(SearchParamDTO.KEYWORDS);
			cityState = sessionMap.get(SearchParamDTO.CITY_STATE);
			radius = sessionMap.get(SearchParamDTO.RADIUS);
			saveSearchName = sessionMap
					.get(SearchParamDTO.SEARCH_NAME);
//			jobSearchResultForm.setSaveSearchName(saveSearchName);
			jobSearchResultForm.setSearchName(saveSearchName);
			jobSearchResultForm.setSearchtype(searchType);
			jobSearchResultForm.setKeywords(keywords);
			jobSearchResultForm.setCityState(cityState);
			jobSearchResultForm.setRadius(radius);
			jobSearchResultForm.setAutoload(true);

			LOGGER.info("Removing keywords, city,state, autoload from session....");

			session.removeAttribute(sessionMap.remove(SearchParamDTO.KEYWORDS));
			session.removeAttribute(sessionMap
					.remove(SearchParamDTO.CITY_STATE));
			session.removeAttribute(sessionMap.remove(SearchParamDTO.RADIUS));
			session.removeAttribute(sessionMap
					.remove(MMJBCommonConstants.AUTOLOAD));

		}

		model.put(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		// get the Ads
		populateAds(request, session, modelAndView, PageNames.JOBSEEKER_JOB_SEARCH);
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
			String searchName = getSearchName(jobSearchResultForm, session,
					request);
			jobSearchResultForm.setSearchName(searchName);
			jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);
			
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

		// populate the ads for search results grid
		populateAds(request, session, jobSrchJsonObj,
				recordsPerPage);
		modelAndView.addObject(MMJBCommonConstants.ADPGCENTER_MIDDLE_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.ADPGCENTER_MIDDLE_LIST));

		model.put(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		// get the Ads
		populateAds(request, session, modelAndView, PageNames.JOBSEEKER_JOB_SEARCH);
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
	@RequestMapping(value = "/browse/{category}", method = RequestMethod.GET)
	public ModelAndView browseJobsByCategory(HttpSession session,
			HttpServletRequest request,
			@PathVariable("category") String category) {

		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		ModelAndView modelAndView = new ModelAndView();
		JobSearchResultDTO jobSearchResultDTO = null;
		JSONObject jobSrchJsonObj = null;
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

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
		}
		// Check for job titles category
		if (category.equalsIgnoreCase("jobtitles")) {
			clearSession(session);
			try {
				@SuppressWarnings("unchecked")
				// get the areas list
				List<String> jobPositionList = (List<String>) jobSrchJsonObj
						.get(MMJBCommonConstants.JOB_POSITION);
				modelAndView.addObject("jbsByTitleList", jobPositionList);
				modelAndView.addObject("jobTitlePage", true);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		// Check for employers category
		if (category.equalsIgnoreCase("employers")) {
			clearSession(session);
			try {
				Map<String, List<String>> emplyrsByName = new TreeMap<String, List<String>>();
				Set<String> nameList = new HashSet<String>();

				@SuppressWarnings("unchecked")
				// get the areas list
				List<String> employerList = (List<String>) jobSrchJsonObj
						.get(MMJBCommonConstants.COMPANY);
				int totalEmployers = 0;
				// set the employers list in dictionary format
				for (String job : employerList) {
					if (job != null && !job.split("\\(")[0].trim().isEmpty()) {
						String nameLetter = job.substring(0, 1).toUpperCase();
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
				int rowsCount = (int) Math.ceil((totalEmployers + totalKeyCount) / 3);
				Map<Integer, TreeMap<String, List<String>>> list = new TreeMap<Integer, TreeMap<String, List<String>>>();
				Iterator<Entry<String, List<String>>> keyIt = emplyrsByName
						.entrySet().iterator();
				int i = 0, j = 1;
				TreeMap<String, List<String>> sets = null;
				boolean isNewList = true;
				while (keyIt.hasNext()) {
					Entry<String, List<String>> entry = (Entry<String, List<String>>) keyIt
							.next();
					if (i < (rowsCount * j)) {
						if (isNewList) {
							sets = new TreeMap<String, List<String>>();
						}
						isNewList = false;
						sets.put(entry.getKey(), entry.getValue());
						list.put(j, sets);
					} else {
						j++;
						isNewList = true;
					}
					i++;
					i = entry.getValue().size() + i;
				}
				TreeMap<String, List<String>> employerFirstList = null;
				TreeMap<String, List<String>> employerSecList = null;
				TreeMap<String, List<String>> employerThirdList = null;
				if(!list.isEmpty() && list.get(1) != null){
					employerFirstList = list.get(1);
				}
				if(!list.isEmpty() && list.get(2) != null){
					employerSecList = list.get(2);
				}
				if(!list.isEmpty() && list.get(3) != null){
					employerThirdList = list.get(3);
				}
				modelAndView.addObject("employerFirstList", employerFirstList);
				modelAndView.addObject("employerSecList", employerSecList);
				modelAndView.addObject("employerThirdList", employerThirdList);
				modelAndView.addObject("employerPage", true);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		// Check for locations category
		if (category.equalsIgnoreCase("locations")) {
			clearSession(session);
			try {
				@SuppressWarnings("unchecked")
				// get the areas list
				List<String> statesList = (List<String>) jobSrchJsonObj
						.get(MMJBCommonConstants.STATE);
				modelAndView.addObject("jbsByLocationList", statesList);
				modelAndView.addObject("locationPage", true);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		// get SEO details
		addSEODetailsForBrowsePages(modelAndView, request, category);
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS);
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
	@RequestMapping(value = "/browse/{location}/areas", method = RequestMethod.GET)
	public ModelAndView browseJobsBySubCategory(HttpSession session,
			HttpServletRequest request, @PathVariable(LOCATION) String location) {

		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		ModelAndView modelAndView = new ModelAndView();
		JobSearchResultDTO jobSearchResultDTO = null;
		JSONObject jobSrchJsonObj = null;

		// clear session attributes of pagination
		clearSession(session);

		// set the search type
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);
		
		// set the FQ parameters
		String state = location.replace("-", " ");
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM, state);
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
		}

		@SuppressWarnings(UNCHECKED)
		// get the areas list
		List<String> areaList = (List<String>) jobSrchJsonObj
				.get(MMJBCommonConstants.AREA);

		modelAndView.addObject("areaList", areaList);
		modelAndView.addObject("locationPage", true);
		modelAndView.addObject("areaPage", true);
		String jobSearchMatchInfo = seoConfiguration.getProperty(
				"browsepage.location.areaslist.matchinfo").trim();
		// get full location name
		String stateFullName = lookupService.getStateFullName(state);
		jobSearchMatchInfo = jobSearchMatchInfo.replace(LOCNAME_REPLACE_WORD,
				state);
		modelAndView.addObject(LOCATION, state);
		modelAndView.addObject("locationFullname", stateFullName + " (" + state
				+ ") ");
		modelAndView.addObject(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
		modelAndView.addObject(JOB_SEARCH_RESULT_FORM, jobSearchResultForm);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);

		// add SEO details to page
		addSEODetailsForBrowsePages(modelAndView, request, "areas");

		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS);
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
	@RequestMapping(value = "/jobtitle/{desc}", method = RequestMethod.GET)
	public ModelAndView searchJobsByJobtitle(HttpSession session,
			@PathVariable("desc") String desc,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		// set the search type
		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String jobTitle = desc.replace("-", " ");
		request.setAttribute(MMJBCommonConstants.FIRST_FQ_PARAM, jobTitle);

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
		}
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
		modelAndView.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, jobTitle);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);

		// populate the ads for search results grid
		populateAds(request, session, jobSrchJsonObj,
				recordsPerPage);
		String[] seoInfos = { jobTitle };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, JOBTITLE,
				seoInfos, noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		return modelAndView;
	}

	/**
	 * Fetch the jobs depending on the selection of employer by using search
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
	@RequestMapping(value = "/employer/{desc}", method = RequestMethod.GET)
	public ModelAndView searchJobByEmployer(HttpSession session,
			@PathVariable("desc") String desc,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);
		
		modelAndView.addObject(MMJBCommonConstants.SEARCH_TYPE, searchName);

		// set the FQ parameters
		String employer = desc.replace("-", " ");
		request.setAttribute(MMJBCommonConstants.SECOND_FQ_PARAM, employer);

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
		}

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
		modelAndView.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
		// added the selected employer in session to disable the link of selected employer in refine results list
		session.setAttribute(BROWSE_BY_EMPLOYER, employer);
		session.removeAttribute(BROWSE_BY_STATE);

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, employer);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);

		// populate the ads for search results grid
		populateAds(request, session, jobSrchJsonObj,
				recordsPerPage);
		String[] seoInfos = { employer };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request,
				BROWSE_BY_EMPLOYER, seoInfos, noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
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
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String state = desc.replace("-", " ");
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM, state);

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
		}

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
		modelAndView.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, state);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
		// added the selected state in session to disable the link of selected state in refine results list
		session.setAttribute(BROWSE_BY_STATE, state);
		session.removeAttribute(BROWSE_BY_EMPLOYER);

		// populate the ads for search results grid
		populateAds(request, session, jobSrchJsonObj,
				recordsPerPage);
		String[] seoInfos = { state };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, LOCATION,
				seoInfos, noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
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
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String selectedLocation = location.replace("-", " ");
		String selectedArea = area.replace("-", " ")
				.replace(MMJBCommonConstants.METRO_AREA, "").trim();
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM,
				selectedLocation);
		request.setAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM, selectedArea);

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
		}
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
		modelAndView.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
		// added the selected state in session to disable the link of selected state in refine results list
		session.setAttribute(BROWSE_BY_STATE, selectedLocation);
		session.removeAttribute(BROWSE_BY_EMPLOYER);

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, selectedArea
				+ "," + selectedLocation);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);

		// populate the ads for search reults grid
		populateAds(request, session, jobSrchJsonObj,
				recordsPerPage);
		String[] seoInfos = { selectedArea, selectedLocation };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, AREA, seoInfos,
				noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
		return modelAndView;
	}

	/**
	 * Fetch the jobs depending on the selection of area in location by using
	 * search type and facets from SOLR and function calls when area is
	 * separated by slash.
	 * 
	 * @param session
	 * @param location
	 * @param area
	 * @param areasubpart
	 * @param jobSearchResultForm
	 * @param result
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/location/{location}/{area}/{areasubpart}" }, method = RequestMethod.GET)
	public ModelAndView searchJobByLocationReg(HttpSession session,
			@PathVariable(LOCATION) String location,
			@PathVariable(AREA) String area,
			@PathVariable("areasubpart") String areasubpart,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		JSONObject jobSrchJsonObj = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		String searchName = MMJBCommonConstants.BROWSE_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// set the FQ parameters
		String selectedLocation = location.replace("-", " ");
		String selectedArea = area
				+ "/"
				+ areasubpart.replace("-", " ")
						.replace(MMJBCommonConstants.METRO_AREA, "").trim();
		request.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM,
				selectedLocation);
		request.setAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM, selectedArea);

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
		}
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
		modelAndView.addObject(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		modelAndView.addObject(MMJBCommonConstants.CURRENT_SEARCH_LIST, null);
		modelAndView.addObject(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		modelAndView.addObject(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		modelAndView.addObject(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
		// added the selected state in session to disable the link of selected state in refine results list
		session.setAttribute(BROWSE_BY_STATE, selectedLocation);
		session.removeAttribute(BROWSE_BY_EMPLOYER);

		String jobSearchMatchInfo = seoConfiguration
				.getProperty(JOB_SRCH_MATCH).trim();
		jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD, selectedArea
				+ "," + selectedLocation);
		session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);

		// populate the ads for search reults grid
		populateAds(request, session, jobSrchJsonObj,
				recordsPerPage);
		String[] seoInfos = { selectedArea, selectedLocation };
		// Add the SEO details for job search results page
		addSEODetailsForJobsSearchPages(modelAndView, request, AREA, seoInfos,
				noOfRecords);
		// populate the ads
		populateAds(request, session, modelAndView,
				PageNames.JOBSEEKER_BROWSE_JOBS);
		modelAndView.setViewName(JOBBOARD_SEARCHRESULTS_PAGE);
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
	private void populateAds(HttpServletRequest request,
			HttpSession session, JSONObject jobSrchJsonObj, int recordsPerPage) {

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
			jobSrchJsonObj.put(MMJBCommonConstants.ADPGCENTER_MIDDLE_LIST, adsList);
			
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
			jobSrchJsonObj.put(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Get the search name by search Parameter
	 * 
	 * @param jobSearchResultForm
	 * @param session
	 * @param request
	 * @return
	 */
	public String getSearchName(JobSearchResultForm jobSearchResultForm,
			HttpSession session, HttpServletRequest request) {
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
	 * The method helps to get the current search results list which contains list of keywords separated 
	 * by space, city and radius so user can delete the items and perform the search
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
				String[] keyWordslist = keyWords.split(" ");
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
			if (!radius.equalsIgnoreCase(MMJBCommonConstants.ZERO)) {
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
		LOGGER.info("Removing from session....");
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
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		clickController.getclickevent(jobId,
				MMJBCommonConstants.CLICKTYPE_CLICK, request, response);

		// Check for job seeker login ,open popup if not logged in.
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			jsonObject.put(ajaxNavigationPath, request.getContextPath()
					+ "/jobsearch/jobseekersaveThisJobPopUp");
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
				int oldJobId = savedJobDTOList.get(0).getSaveJobId();
				jobSeekerJobDetailService.updateAppliedSavedJobs(oldJobId);
			}
		} catch (JobBoardException e) {
			LOGGER.error("Error occured while getting the saved job of curresponding  user or while updating the particular job details"
					, e);
		}

		// Get the Job details
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);

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
	 * @return
	 */
	@RequestMapping(value = "/jobseekerPostYourResume")
	public @ResponseBody
	JSONObject callPostYourResumePopUp(HttpSession session,
			Map<String, Object> map) {
		JSONObject jsonObject = new JSONObject();

		// Check for job seeker login ,open popup if not logged in.
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			map.put("loginForm", new LoginForm());
			jsonObject.put("LoggedInNavigationPath", navigationPath
					+ dothtmlExtention + jobseekerPageExtention);
			return jsonObject;
		}
		jsonObject
				.put(ajaxNavigationPath,
						"../jobSeekerResume/createResumePopUp.html?resumeType=createResume");
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

		LOGGER.info("State List=" + stateList.size());

		model.addObject("stateList", stateList);
		model.addObject("jobseekerAdvanceSearchForm",
				jobseekerAdvanceSearchForm);

		model.setViewName("jobboardadvancedsearch");
		// get the Ads
		populateAds(request, session, model, PageNames.JOBSEEKER_ADVC_JOB_SEARCH);
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
					MMJBCommonConstants.CLICKTYPE_CLICK, request, response);
			String jobTitle = request.getParameter(JOBTITLE);
			jobTitle = jobTitle.replace(" ", "-").toLowerCase();

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
							"/jobsearch/jobview/" + jobId + "/" + jobTitle
									+ dothtmlExtention);
			sendtofriendmail.setJobId(jobId);
			sendtofriendmail.setJoburl(fullPath);
			model.addAttribute("joburl", fullPath);
			model.addAttribute("jobId", request.getParameter("id"));
			model.addAttribute(CURRENT_URL, request.getParameter(CURRENT_URL));
			model.addAttribute("sendtofriendmail", sendtofriendmail);
		} catch (Exception e) {
			LOGGER.info("ERROR");
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
		String bodyMesg = MMJBCommonConstants.EMPTY;
		try {
			String data = sendtofriendmail.getEmail().toString();
			if ((null == data.trim())
					|| (MMJBCommonConstants.EMPTY.equals(data.trim()))) {
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
				String msgSubject = MMJBCommonConstants.EMPTY;
				if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {

					jobseekerName = "XXXX-XXX";
					msgSubject = subOfmail + " " + jobseekerName;
				} else {
					jobseekerName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
					msgSubject = subOfmail + " " + jobseekerName;
				}

				jobSeekerEmailDTO.setSubject(msgSubject);
				JobDTO jobDTO = jobSearchService
						.viewJobDetails(sendtofriendmail.getJobId());

				String Subject = subOfmail + " " + jobseekerName;
				String bodyHead1 = bodyOfMailFirst + " " + jobseekerName + " "
						+ bodyOfMailSecond;
				String bodyHead2 = sendtofriendmail.getMessage();
				String jobTitle = jobTitleHeading;
				String companyName = cmpNameHeading;
				// String jobUrl = sendtofriendmail.getJoburl();
				/*
				 * String joburl = urlLinkFirst + MMJBCommonConstants.EMPTY +
				 * jobUrl + MMJBCommonConstants.EMPTY + urlLinkSecond;
				 */
				mesg = mesg.append(emailConfiguration.getProperty(
						"jobseeker.email.header").trim());
				mesg = mesg.append("<TABLE><TR><TD>" + Subject + END_TAGS);
				mesg = mesg.append("<TR><TD>" + bodyHead1 + "\n" + bodyHead2
						+ END_TAGS);
				mesg = mesg.append("<TR><TD><B>[" + jobTitle + "]</B>"
						+ jobDTO.getJobTitle() + END_TAGS);
				if (jobDTO.getCompanyNameDisp() != null) {
					mesg = mesg.append("<TR><TD><B>[" + companyName + "]</B>"
							+ jobDTO.getCompanyNameDisp() + END_TAGS);
				}
				mesg = mesg.append("<TR><TD>");
				mesg = mesg.append(urlLinkFirst);
				mesg = mesg.append(MMJBCommonConstants.EMPTY);
				mesg = mesg.append(sendtofriendmail.getJoburl());
				mesg = mesg.append(MMJBCommonConstants.EMPTY);
				mesg = mesg.append(urlLinkSecond);
				mesg = mesg.append("</TD></TR>\n\n\n");
				mesg = mesg.append("<TR><TD>" + sendtofriendmail.getJoburl()
						+ "</TD></TR></TABLE>");
				mesg = mesg.append(emailConfiguration.getProperty(
						"email.footer").trim());
				bodyMesg = mesg.toString();
				jobSeekerEmailDTO.setBody(bodyMesg);
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
			} catch (Exception e) {
				LOGGER.info(errSendingMail);
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

		if (locationDTOList != null) {
			/*
			 * Returning the List<String> based on Post code search or CityState
			 * search
			 */
			if (MMUtils.isIntNumber(keyword)) {
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
		modelAndView.addObject("basePath",request.getRequestURL().toString().replace(request.getServletPath(), ""));
		modelAndView.setViewName("jobboardsearchresultsBody");
		return modelAndView;
	}

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

	public ResponseEntity<byte[]> handleGetMyBytesRequest(byte[] imageInByte) {
		// Get bytes from somewhere...
		byte[] byteData = imageInByte;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(byteData.length);

		return new ResponseEntity<byte[]>(byteData, responseHeaders,
				HttpStatus.OK);
	}

	@RequestMapping("/viewTestimonial")
	public ModelAndView enlargeTestimonial(
			@RequestParam("id") String testimonyId,
			HttpServletResponse response, HttpServletRequest request,
			BrandingTemplateForm brandingTemplateForm) {
		ModelAndView model = new ModelAndView();
		brandingTemplateForm.setTestimonyContainer(testimonyId);
		model.addObject("brandingTemplateForm", brandingTemplateForm);
		model.setViewName("viewTestimony");
		return model;
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
			if (purchasedPackages.contains(MMJBCommonConstants.PLATINUM_90)
					|| purchasedPackages
							.contains(MMJBCommonConstants.PLATINUM_180)
					|| purchasedPackages
							.contains(MMJBCommonConstants.PLATINUM_365)) {
				jobDTO.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_PLATINUM;
			} else if (purchasedPackages.contains(MMJBCommonConstants.GOLD_90)
					|| purchasedPackages.contains(MMJBCommonConstants.GOLD_180)
					|| purchasedPackages.contains(MMJBCommonConstants.GOLD_365)) {
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
			LOGGER.info("Deleting the current search : "
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
		StringBuffer videoURL = new StringBuffer();

		videoURL.append(request.getRequestURL().toString()
				.replace(request.getRequestURI(), MMJBCommonConstants.EMPTY));
		videoURL.append(mediaPath);

		if (null != listVideoDTO && !listVideoDTO.isEmpty()) {
			for (VideoDTO dto : listVideoDTO) {
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
	 * The method helps to save the search results criteria in DB on every keyword search
	 * and display the recent searches list
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
		if (recentSearches.size() == Integer.parseInt(recentSearchsLimit)) {
			int saveSearchId = recentSearches.get(recentSearches.size()-1).getSaveSearchID();
			saveSearchService.deleteSavedSearch(saveSearchId);
		}
		
		// save the search criteria
		SaveSearchedJobsDTO currentSearch = saveSearchService
				.saveSearchedJobs(searchedJobsDTO);

		recentSearches.add(currentSearch);
		// get the latest searches and check for latest searches limit
		List<SaveSearchedJobsDTO> latestSearches = recentSearches;
		if (recentSearches.size() > MMJBCommonConstants.LATEST_SEARCHES_LIMIT) {
			latestSearches = recentSearches.subList(0 , MMJBCommonConstants.LATEST_SEARCHES_LIMIT);
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
	String clearLatestSearches(HttpSession session, 
			HttpServletRequest request) {
		// get the userId from session
		int userId = getUserID(session);

		// clear the recent searches
		jobSearchService.clearRecentSearches(userId);
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
		LOGGER.info("Get the latest searches of user ");
		
		ModelAndView modelAndView = new ModelAndView();
		session.removeAttribute(LATEST_RECENT_LIST);
		// get the UserId from session
		int userId = getUserID(session);

		if (userId != 0) {

			// get the recent searches of user
			List<SaveSearchedJobsDTO> recentSearches = saveSearchService
					.viewMySavedSearches(userId, true);
			
			// get the latest searches and check for latest searches limit
			List<SaveSearchedJobsDTO> latestSearches = recentSearches;
			if(recentSearches.size() > MMJBCommonConstants.LATEST_SEARCHES_LIMIT){
				latestSearches = recentSearches.subList(0 , MMJBCommonConstants.LATEST_SEARCHES_LIMIT);
			}

			session.setAttribute(LATEST_RECENT_LIST, latestSearches);
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
					MMUtils.convertToReqdDateString(jobDTO.getPostedDate()));
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
		// Update Views for the list of jobs which appeared in the search
		clickService.saveJobViews(jobDTOList);

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

		jobSrchJsonObj.put(MMJBCommonConstants.JOB_POSITION,
				jobPositionDisplayList);
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
		if (category.equalsIgnoreCase("jobtitles")) {
			metaTitle = seoConfiguration.getProperty(
					"browsepage.jobtitleslist.meta.title").trim();
			metaDesc = seoConfiguration.getProperty(
					"browsepage.jobtitleslist.meta.description").trim();
		}
		// set the meta title and description tags value for employers list
		// page.
		if (category.equalsIgnoreCase("employers")) {
			metaTitle = seoConfiguration.getProperty(
					"browsepage.employerslist.meta.title").trim();
			metaDesc = seoConfiguration.getProperty(
					"browsepage.employerslist.meta.description").trim();
		}
		// set the meta title and description tags value for locations list
		// page.
		if (category.equalsIgnoreCase("locations")) {
			metaTitle = seoConfiguration.getProperty(
					"browsepage.locationslist.meta.title").trim();
			metaDesc = seoConfiguration.getProperty(
					"browsepage.locationslist.meta.description").trim();
		}
		// set the meta title and description tags value for areas list page.
		if (category.equalsIgnoreCase("areas")) {
			// String locationName = (String)
			// modelAndView.getModelMap().get(LOCATION);
			String locationFullName = (String) modelAndView.getModelMap().get(
					"locationFullname");
			metaTitle = seoConfiguration.getProperty(
					"browsepage.location.areaslist.meta.title").trim();
			metaTitle = metaTitle.replace(LOCNAME_REPLACE_WORD,
					locationFullName);
			metaDesc = seoConfiguration.getProperty(
					"browsepage.location.areaslist.meta.description").trim();
			metaDesc = metaDesc.replace(LOCNAME_REPLACE_WORD, locationFullName);
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
	 *            TODO
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
			metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.jobtitle.meta.title").trim();
			metaTitle = metaTitle.replace(JOBTITLE_REPLACE_WORD,
					categoryDesc[0]);
			metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.jobtitle.meta.description").trim();
			metaDesc = metaDesc.replace(JOBTITLE_REPLACE_WORD, categoryDesc[0]);
			metaDesc = metaDesc.replace(Q_JOBSCOUNT, String.valueOf(jobCount));
		}
		// set the meta title and description tags value for employers list
		// page.
		if (category.equalsIgnoreCase(BROWSE_BY_EMPLOYER)) {
			metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.employer.meta.title").trim();
			metaTitle = metaTitle.replace("?employername", categoryDesc[0]);
			metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.employer.meta.description").trim();
			metaDesc = metaDesc.replace("?employername", categoryDesc[0]);
			metaDesc = metaDesc.replace(Q_JOBSCOUNT, String.valueOf(jobCount));
		}
		// set the meta title and description tags value for locations list
		// page.
		if (category.equalsIgnoreCase(LOCATION)) {
			metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.location.meta.title").trim();
			metaTitle = metaTitle
					.replace(LOCNAME_REPLACE_WORD, categoryDesc[0]);
			metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.location.meta.description").trim();
			metaDesc = metaDesc.replace(LOCNAME_REPLACE_WORD, categoryDesc[0]);
			metaDesc = metaDesc.replace(Q_JOBSCOUNT, String.valueOf(jobCount));
		}
		// set the meta title and description tags value for areas list page.
		if (category.equalsIgnoreCase(AREA)) {
			metaTitle = seoConfiguration.getProperty(
					"jobsearchpage.location.area.meta.title").trim();
			metaTitle = metaTitle.replace("?areaname", categoryDesc[0]);
			metaTitle = metaTitle
					.replace(LOCNAME_REPLACE_WORD, categoryDesc[1]);
			metaDesc = seoConfiguration.getProperty(
					"jobsearchpage.location.area.meta.description").trim();
			metaDesc = metaDesc.replace("?areaname", categoryDesc[0]);
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
			LOGGER.info("The fresh job search is started.");
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
				thirdFQParam = MMJBCommonConstants.FQ_STATE
						+ request
								.getAttribute(MMJBCommonConstants.THIRD_FQ_PARAM)
						+ '"';
			}
			String fouthFQParam = MMJBCommonConstants.EMPTY;
			if (request.getAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM) != null) {
				fouthFQParam = MMJBCommonConstants.FQ_CITY
						+ request
								.getAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM)
						+ '"';
			}
			String fifthFQParam = MMJBCommonConstants.EMPTY;
			if (request.getAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM) != null) {
				fifthFQParam = MMJBCommonConstants.FQ_AREA
						+ request
								.getAttribute(MMJBCommonConstants.FIFTH_FQ_PARAM)
						+ '"';
			}
			String facetSort = MMJBCommonConstants.COUNT_STR;
			// set the sort order for search results
			String sortOrder = setSortOrder(session, request);

			// Putting all the parameters coming from the UI into a Map for
			// further
			// processing.
			Map<String, String> fqParamMap = getFQMap(firstFQParam,
					secondFQParam, thirdFQParam, fouthFQParam, fifthFQParam,
					sortOrder, facetSort);
			String jobSearchMatchInfo = seoConfiguration.getProperty(
					JOB_SRCH_MATCH).trim();
			if (jobSearchResultForm.getKeywords() != null) {
				paramMap.put(SearchParamDTO.KEYWORDS, jobSearchResultForm
						.getKeywords().trim());
				jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD,
						jobSearchResultForm.getKeywords().trim());
			} else {
				paramMap.put(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);
				jobSearchMatchInfo = jobSearchMatchInfo.replace(Q_KEYWORD,
						MMJBCommonConstants.EMPTY);
			}
			session.setAttribute(JOB_SRCH_MTCH_INFO, jobSearchMatchInfo);
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
			// Check for search name in session map for save search functionality 
			if(session
					.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) != null){
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
		} else {
			LOGGER.info("Loading the search parameters from session");
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

		if (fQParamName.equalsIgnoreCase(MMJBCommonConstants.FIRST_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
						MMJBCommonConstants.FQ_REFINE_KEYWORD
								+ MMJBCommonConstants.FQ_JOB_POSITION
								+ fQParamVal + "\"");
			}
		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.SECOND_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
						MMJBCommonConstants.FQ_REFINE_KEYWORD
								+ MMJBCommonConstants.FQ_COMPANY + fQParamVal
								+ "\"");
			}

		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.THIRD_FQ_PARAM)) {

			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
						MMJBCommonConstants.FQ_REFINE_KEYWORD
								+ MMJBCommonConstants.FQ_STATE + fQParamVal
								+ "\"");
			}

		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.FOURTH_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
						MMJBCommonConstants.FQ_REFINE_KEYWORD
								+ MMJBCommonConstants.FQ_CITY + fQParamVal
								+ "\"");
			}

		} else if (fQParamName
				.equalsIgnoreCase(MMJBCommonConstants.FIFTH_FQ_PARAM)) {
			if (fQParamVal.isEmpty()) {
				paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
						MMJBCommonConstants.EMPTY);
			} else {
				paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
						MMJBCommonConstants.FQ_REFINE_KEYWORD
								+ MMJBCommonConstants.FQ_AREA + fQParamVal
								+ "\"");
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
			String searchName = getSearchName(jobSearchResultForm, session,
					request);
			jobSearchResultForm.setSearchName(searchName);
			jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);
			
			jobSrchJsonObj = jobSearchValidator.validateJobSearch(
					jobSearchResultForm, session);
			if (jobSrchJsonObj != null) {
				return jobSrchJsonObj;
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
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);
			noOfRecords = (int) jobSearchResultDTO.getResultCount();
			//
			if (jobSearchResultDTO != null) {
				// Calling the service layer for converting the
				// JobSearchResultDTO
				// object into JSON Object
				jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
			}
		} catch (JobBoardException e) {
			LOGGER.debug(ERROR_SOLR);
		}

		// get the current search items
		List<HashMap<String, Object>> currentSearchList = getCurrentSearchResultsList(session);
		session.setAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST,
				currentSearchList);
		// set the pagination values in session
		setSessionForGrid(session, request, jobSearchResultDTO, page,
				recordsPerPage, noOfRecords, jobSrchJsonObj);

		// populate the ads for search results grid
		populateAds(request, session, jobSrchJsonObj,
				recordsPerPage);
		
		// save the search results to DB to set the list of recent searches by
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
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
	}
	
}
