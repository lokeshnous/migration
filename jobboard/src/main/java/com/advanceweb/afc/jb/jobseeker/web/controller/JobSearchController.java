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
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.VideoDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;
import com.advanceweb.afc.jb.employer.service.EmployerNewsFeedService;
import com.advanceweb.afc.jb.employer.web.controller.BrandingTemplateForm;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.home.web.controller.ClickController;
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
@SuppressWarnings("unchecked")
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
	private BrandingTemplateService brandingTemplateService;

	@Autowired
	private EmployerNewsFeedService employerNewsFeedService;

	@Autowired
	private JobSearchValidator jobSearchValidator;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private CoverLetterService coverLetterService;

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

	@Autowired
	@Resource(name = "seoConfiguration")
	private Properties seoConfiguration;

	@Autowired
	private ClickController clickController;

	private static final String PLATINUM_LIST = "PlatinumNewsList";
	private static final String IS_SORTING = "isSorting";
	private static final String CURRENT_URL = "currentUrl";
	private static final String END_TAGS = "</TD></TR>\n";
	private static final String ADPAGETOP = "adPageTop";
	private static final String ADPAGEBTM = "adPageBtm";
	private static final String COMPANY_NAME = "?companyName";
	private static final String CITY = "?city";
	private static final String COUNTRY = "?country";
	private static final String STATE = "?state";
	
	/**
	 * The view action is called to get the job details by jobId and navigate to
	 * job view details page.
	 * 
	 * @param jobId
	 * @param request
	 * @return : modelandview for respected Jobid
	 */
	@RequestMapping(value = "/viewJobDetails/{jobId}/{jobTitle}")
	public ModelAndView viewJobDetails(@PathVariable("jobId") int jobId,
			@PathVariable("jobTitle") String jobTitle,
			Map<String, Object> model, HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();

		try {
			clickController.getclickevent(jobId,
					MMJBCommonConstants.CLICKTYPE_VIEW, request, response);
			boolean isReturnResults = true;
			getJobDetails(model, request, session, modelView, jobId,
					isReturnResults);

		} catch (Exception e) {
			// loggers call
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
			// loggers call
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
		if (session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) != null) {
			sessionMap = (Map<String, String>) session
					.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
		}
		// View the job with template
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		if (MMJBCommonConstants.ZERO_INT != jobDTO.getTemplateId()) {
			jobDTO = checkBrand(jobDTO);
		}
		model.put("jobDetail", jobDTO);
		model.put("isFeatureEmployer", jobDTO.isFeatured());
		model.put("isReturnResults", isReturnResults);
		// Get the SEO Details
		getSEODetails(model, request, jobDTO);
		if (sessionMap != null) {
			sessionMap.put(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));
			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);
		}

		if (MMJBCommonConstants.ZERO_INT == jobDTO.getTemplateId()) {
			modelView.setViewName("jobseekerJobDetails");
			// get the Ads
			getAdsForJobView(request, session, model);
		} else {
			List<JobPostDTO> jobPostDTOList = jobSearchService
					.getRecentJobsPostedByEmployer(jobDTO.getFacilityId(),
							jobDTO.getJobId());

			// For getting the News feed from XML file
			Map<String, List<NewsDTO>> newsMap = employerNewsFeedService
					.getNewsFromXML();
			List<NewsDTO> newsDTOList = newsMap.get(PLATINUM_LIST);

			List<String> videoList = setVideoURL(jobDTO, request);
			model.put("newsDTOList", newsDTOList);
			model.put("jobDTOList", jobPostDTOList);
			model.put("videoList", videoList);
			modelView.setViewName("jobseekerJobDetailsTemplate");
			// get the Ads
			getAdsForPremiumJobView(request, session, model);
		}
	}

	/**
	 * Get Ads for page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void getAdsForJobView(HttpServletRequest request,
			HttpSession session, Map<String, Object> model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOB_VIEW);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.put(ADPAGETOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.put("adPageRightTop", bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.put("adPageRightMiddle", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.put(ADPAGEBTM, bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Get Ads for page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void getAdsForPremiumJobView(HttpServletRequest request,
			HttpSession session, Map<String, Object> model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.PREMIUM_JOB_VIEW);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.put(ADPAGETOP, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.put(ADPAGEBTM, bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Get the SEO details.
	 * 
	 * @param model
	 * @param request
	 * @param jobDTO
	 */
	private void getSEODetails(Map<String, Object> model,
			HttpServletRequest request, JobDTO jobDTO) {

		String metaDesc = seoConfiguration
				.getProperty("jobviewpage.meta.description");
		String metaTitle = seoConfiguration
				.getProperty("jobviewpage.meta.title");
		metaDesc = metaDesc.replace("?jobtitle", jobDTO.getJobTitle());
		metaTitle = metaTitle.replace("?jobtitle", jobDTO.getJobTitle());
		if (jobDTO.getCompanyNameDisp() != null) {
			metaDesc = metaDesc.replace(COMPANY_NAME,
					jobDTO.getCompanyNameDisp());
			metaTitle = metaTitle.replace(COMPANY_NAME,
					jobDTO.getCompanyNameDisp());
		} else {
			metaDesc = metaDesc.replace(COMPANY_NAME, "");
			metaTitle = metaTitle.replace(COMPANY_NAME, "");
		}
		if (jobDTO.getCity() != null) {
			metaDesc = metaDesc.replace(CITY, "");
			metaTitle = metaTitle.replace(CITY, "");
		} else {
			metaDesc = metaDesc.replace(CITY, "");
			metaTitle = metaTitle.replace(CITY, "");
		}
		if (jobDTO.getState() != null) {
			metaDesc = metaDesc.replace(STATE, jobDTO.getState());
			metaTitle = metaTitle.replace(STATE, jobDTO.getState());
		} else {
			metaDesc = metaDesc.replace(STATE, "");
			metaTitle = metaTitle.replace(STATE, "");
		}
		if (jobDTO.getCountry() != null) {
			metaDesc = metaDesc.replace(COUNTRY, jobDTO.getCountry());
			metaTitle = metaTitle.replace(COUNTRY, jobDTO.getCountry());
		} else {
			metaDesc = metaDesc.replace(COUNTRY, "");
			metaTitle = metaTitle.replace(COUNTRY, "");
		}
		model.put("metaDesc", metaDesc);
		model.put("metaTitle", metaTitle);
		model.put("canonicalUrl", request.getRequestURL());
	}

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
	 * @param form
	 * @param map
	 * @param userID
	 * @param jobId
	 * @param currentUrl
	 * @param response
	 * @param model
	 * @param clickType
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
			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
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
		// Send mail to Employer regarding job application
		String loginPath = navigationPath.substring(2);
		EmailDTO employerEmailDTO = new EmailDTO();
		employerEmailDTO.setFromAddress(advanceWebAddress);
		InternetAddress[] employerToAddress = new InternetAddress[1];
		employerToAddress[0] = new InternetAddress(
				jobDTO.getEmail());
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
		if (coverLetterTxt == null) {
			employerEmailDTO.setBody(employerMailBody);
		} else {
			coverLetterTxt = coverLetterTxt.replace("\r\n", "<br/>");
			employerEmailDTO.setBody(coverLetterTxt + "<br/>"
					+ employerMailBody);
		}
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
		if(jobDTO.getCompanyNameDisp() == null){
			jobseekerMailSub = jobseekerJobApplicationSub.replace("to ?companyname", "");
			jobseekerMailBody = jobseekerMailBody.replace("to ?companyname", "");
		}else{
			jobseekerMailSub = jobseekerJobApplicationSub.replace(
					"?companyname", jobDTO.getCompanyNameDisp());
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",
					jobDTO.getCompanyNameDisp());
		}
		jobSeekerEmailDTO.setSubject(jobseekerMailSub);
		jobSeekerEmailDTO.setBody(jobseekerMailBody);
		jobSeekerEmailDTO.setHtmlFormat(true);
		emailService.sendEmail(jobSeekerEmailDTO);
		LOGGER.info("Mail sent to jobseeker");
	}

	/**
	 * Save or Update the applied job
	 * 
	 * @param form
	 * @param userId
	 * @param jobDTO
	 * @param appliedJobDTO
	 */
	public void saveAppliedJob(int jobId, int userId,
			JobDTO jobDTO, AppliedJobDTO appliedJobDTO) {
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
					.get(MMJBCommonConstants.SAVE_SEARCH_NAME);
			jobSearchResultForm.setSaveSearchName(saveSearchName);
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

		model.put("jobSearchResultForm", jobSearchResultForm);
		// get the Ads
		getAdsForJobseekerSearch(request, session, modelAndView);
		modelAndView.setViewName("jobboardsearchresults");
		return modelAndView;
	}

	/**
	 * Get Ads for page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void getAdsForJobseekerSearch(HttpServletRequest request,
			HttpSession session, ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOBSEEKER_JOB_SEARCH);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(ADPAGETOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageRightTop", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(ADPAGEBTM, bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
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
	@RequestMapping(value = "/searchJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchJob(HttpSession session,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		JSONObject jobSrchJsonObj = null;
		boolean refined = Boolean.valueOf(request
				.getParameter(MMJBCommonConstants.REFINED));
		session.setAttribute(MMJBCommonConstants.REFINED, refined);
		// set the sort order for search results
		removeSession(session);
		JobSearchResultDTO jobSearchResultDTO = null;

		// If search is browse by title, by employer , by location then set
		// browse by as true in form
		if ((request.getParameter(MMJBCommonConstants.BROWSE_BY_TITLE) != null)
				|| (request
						.getParameter(MMJBCommonConstants.BROWSE_BY_LOCATION) != null)
				|| (request
						.getParameter(MMJBCommonConstants.BROWSE_BY_EMPLOYER) != null)) {
			jobSearchResultForm.setBrowseBy(true);
			session.setAttribute(MMJBCommonConstants.BROWSE_BY_SEARCH, true);
		}
		if (!jobSearchResultForm.isBrowseBy()) {
			jobSrchJsonObj = jobSearchValidator
					.validateJobSearch(jobSearchResultForm);
			if (jobSrchJsonObj != null) {
				return jobSrchJsonObj;
			}
		}
		String searchName = getSearchName(jobSearchResultForm, session);

		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				searchName, MMJBCommonConstants.POSTED_DT, session, request);

		int page = 1;
		int displayRecordsPerPage = 0;
		int recordsPerPage = 0;
		int noOfRecords = 0;
		String next = request.getParameter(MMJBCommonConstants.NEXT);

		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			if (request.getParameter(MMJBCommonConstants.RECORDS_PER_PAGE) != null) {
				displayRecordsPerPage = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.RECORDS_PER_PAGE));
			}

			if (0 == displayRecordsPerPage) {
				displayRecordsPerPage = MMJBCommonConstants.DEFAULT_PAGE_SIZE;
			}
			recordsPerPage = displayRecordsPerPage;
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(searchName,
					paramMap, start, rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
					* recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + jobSearchResultDTO
							.getResultList().size()));

			noOfRecords = (int) jobSearchResultDTO.getResultCount();

			// TODO: Advertise part is not done
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null == next || next.isEmpty()) {
			beginVal = (page / 10) * 10;
		} else {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		}
		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchJobToJSON(jobSearchResultDTO);
		}
		List<HashMap<String, Object>> currentSearchList = getCurrentSearchResultsList(session);
		setSessionForGrid(session, page, noOfPages, beginVal, jobSrchJsonObj,
				currentSearchList);
		if ((request.getParameter(MMJBCommonConstants.BROWSE_BY_LOCATION) != null)) {
			session.setAttribute("locationPage", true);
			session.setAttribute("areaPage", true);
			List<String> areaList = (List<String>) jobSrchJsonObj
					.get(MMJBCommonConstants.AREA);
			session.setAttribute("areaList", areaList);
			session.setAttribute("location",
					request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM));
			jobSrchJsonObj.put("location",
					request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM));
		}
		// get SEO details for search Page
		// getSEODetails(model, request, jobDTO);
		session.setAttribute("jobSearchResultForm", jobSearchResultForm);
		return jobSrchJsonObj;
	}

	/**
	 * Get the search name by search Parameter
	 * 
	 * @param jobSearchResultForm
	 * @param session
	 * @return
	 */
	public String getSearchName(JobSearchResultForm jobSearchResultForm,
			HttpSession session) {
		// Check if city state and radius field is not empty to check for
		// LOCATION search
		String searchName = MMJBCommonConstants.EMPTY;
		if ((jobSearchResultForm.getCityState() != null)
				&& (StringUtils.isEmpty(jobSearchResultForm.getCityState()
						.trim()))) {
			if (!StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())) {
				searchName = MMJBCommonConstants.KEYWORD_SEARCH;
			}
			// If search is browse by then set search name as BROWSE-JB2
		} else if (jobSearchResultForm.isBrowseBy()) {
			searchName = MMJBCommonConstants.BROWSE_SEARCH;
			jobSearchResultForm.setSearchtype(searchName);
			session.setAttribute(MMJBCommonConstants.SEARCH_TYPE, searchName);

		} else if (!StringUtils.isEmpty(jobSearchResultForm.getCityState()
				.trim())) {
			searchName = MMJBCommonConstants.LOCATION_SEARCH;
			session.setAttribute(MMJBCommonConstants.DISPLAY_RADIUS, true);
		}
		return searchName;
	}

	/**
	 * This method provides a map of FQ parameters for Solr
	 * 
	 * @param request
	 * @return mapFQ
	 */
	public Map<String, String> getFQParams(HttpServletRequest request,
			HttpSession session) {
		String secondFQParam = MMJBCommonConstants.EMPTY;
		String thirdFQParam = MMJBCommonConstants.EMPTY;
		String fouthFQParam = MMJBCommonConstants.EMPTY;

		Map<String, String> mapFQ = new HashMap<String, String>();

		if (null != request.getParameter(MMJBCommonConstants.SECOND_FQ_PARAM)
				&& !request.getParameter(MMJBCommonConstants.SECOND_FQ_PARAM)
						.isEmpty()) {
			secondFQParam = MMJBCommonConstants.FQ_COMPANY
					+ request.getParameter(MMJBCommonConstants.SECOND_FQ_PARAM)
					+ '"';
			session.setAttribute(MMJBCommonConstants.SECOND_FQ_PARAM,
					request.getParameter(MMJBCommonConstants.SECOND_FQ_PARAM));
		} else {
			session.removeAttribute(MMJBCommonConstants.SECOND_FQ_PARAM);
		}

		if (null != request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM)
				&& !request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM)
						.isEmpty()) {
			thirdFQParam = MMJBCommonConstants.FQ_STATE
					+ request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM)
					+ '"';
			session.setAttribute(MMJBCommonConstants.THIRD_FQ_PARAM,
					request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM));
		} else {
			session.removeAttribute(MMJBCommonConstants.THIRD_FQ_PARAM);
		}

		if (null != request.getParameter(MMJBCommonConstants.FOURTH_FQ_PARAM)
				&& !request.getParameter(MMJBCommonConstants.FOURTH_FQ_PARAM)
						.isEmpty()) {
			fouthFQParam = MMJBCommonConstants.FQ_CITY
					+ request.getParameter(MMJBCommonConstants.FOURTH_FQ_PARAM)
					+ '"';
			session.setAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM,
					request.getParameter(MMJBCommonConstants.FOURTH_FQ_PARAM));
		} else {
			session.removeAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM);
		}

		if (null != request.getParameter(MMJBCommonConstants.RADIUS)
				&& !request.getParameter(MMJBCommonConstants.RADIUS).isEmpty()) {
			session.setAttribute(MMJBCommonConstants.REFINERADIUS,
					request.getParameter(MMJBCommonConstants.RADIUS));
		}

		mapFQ.put(MMJBCommonConstants.SECOND_FQ_PARAM, secondFQParam);
		mapFQ.put(MMJBCommonConstants.THIRD_FQ_PARAM, thirdFQParam);
		mapFQ.put(MMJBCommonConstants.FOURTH_FQ_PARAM, fouthFQParam);

		return mapFQ;
	}

	/**
	 * Method helps to set the sort order in session
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
	 * This method is used to setting the required values into the session for
	 * displaying the results in the grid.
	 * 
	 * @param session
	 * @param page
	 * @param noOfPages
	 * @param beginVal
	 * @param jobSrchJsonObj
	 * @param currentSearchList
	 */
	private void setSessionForGrid(HttpSession session, int page,
			int noOfPages, int beginVal, JSONObject jobSrchJsonObj,
			List<HashMap<String, Object>> currentSearchList) {
		// TODO: Need to use session Map
		session.setAttribute(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		session.setAttribute(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		session.setAttribute(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		session.setAttribute(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
		session.setAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST,
				currentSearchList);
		session.setAttribute(MMJBCommonConstants.RECORDS_COUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		session.setAttribute(MMJBCommonConstants.BEGIN_VAL, beginVal);
		session.setAttribute(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
		session.setAttribute(MMJBCommonConstants.CURRENT_PAGE, page);
		session.setAttribute(MMJBCommonConstants.BEGIN, (beginVal <= 0 ? 1
				: beginVal));
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
	}

	/**
	 * Load the current search results list
	 * 
	 * @param session
	 * @return
	 */
	public List<HashMap<String, Object>> getCurrentSearchResultsList(
			HttpSession session) {
		// Load the current search results list
		List<HashMap<String, Object>> currentSearchList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, String> sessionMap = (HashMap<String, String>) session
				.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP);

		// if search is not browse by then check for the current search list
		if ((sessionMap.get(MMJBCommonConstants.SEARCH_TYPE) != null)
				&& (!sessionMap.get(MMJBCommonConstants.SEARCH_TYPE).equals(
						MMJBCommonConstants.BROWSE_SEARCH))) {
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
	private void removeSession(HttpSession session) {
		// TODO :Need to Use sessionMap
		LOGGER.info("Removing from session....");
		session.removeAttribute(MMJBCommonConstants.SEARCH_RESULTS_LIST);
		session.removeAttribute(MMJBCommonConstants.CITY);
		session.removeAttribute(MMJBCommonConstants.STATE);
		session.removeAttribute(MMJBCommonConstants.COMPANY);
		session.removeAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST);
		session.removeAttribute(MMJBCommonConstants.NO_OF_PAGES);
		session.removeAttribute(MMJBCommonConstants.CURRENT_PAGE);
		session.removeAttribute(MMJBCommonConstants.RECORDS_PER_PAGE);
		session.removeAttribute(MMJBCommonConstants.RECORDS_COUNT);
		session.removeAttribute(MMJBCommonConstants.TOTAL_NO_RECORDS);
		session.removeAttribute(MMJBCommonConstants.START_ROW);
		session.removeAttribute(MMJBCommonConstants.END_ROW);
		session.removeAttribute(MMJBCommonConstants.BEGIN_VAL);
		session.removeAttribute(MMJBCommonConstants.BEGIN);
		session.removeAttribute(MMJBCommonConstants.DISPLAY_RADIUS);

		// Added for Browse By job title, By Employer And By Location task
		session.removeAttribute("jobTitlePage");
		session.removeAttribute("employerPage");
		session.removeAttribute("locationPage");
		session.removeAttribute(MMJBCommonConstants.BROWSE_BY_TITLE);
		session.removeAttribute("list");
		// session.removeAttribute("locationPage");
		session.removeAttribute("areaPage");

		// Remove FQ params for non Refine Search
		if (null == session.getAttribute(MMJBCommonConstants.REFINED)
				|| session.getAttribute(MMJBCommonConstants.REFINED).toString()
						.isEmpty()
				|| !Boolean.valueOf(session.getAttribute(
						MMJBCommonConstants.REFINED).toString())) {
			session.removeAttribute(MMJBCommonConstants.SECOND_FQ_PARAM);
			session.removeAttribute(MMJBCommonConstants.THIRD_FQ_PARAM);
			session.removeAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM);
			session.removeAttribute(MMJBCommonConstants.REFINERADIUS);
		}
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);
		if (sessionMap.get(SearchParamDTO.KEYWORDS) != null) {
			session.setAttribute(MMJBCommonConstants.PREV_JOB_SEARCH_KEYWORDS,
					sessionMap.get(SearchParamDTO.KEYWORDS));
		}
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
			@RequestParam("id") int jobId, HttpSession session) {
		JSONObject jsonObject = new JSONObject();

		// Check for job seeker login ,open popup if not logged in.
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			jsonObject.put(ajaxNavigationPath, request.getContextPath()
					+ "/jobsearch/jobseekersaveThisJobPopUp");
			return jsonObject;
		}
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int savedJobsCount = 0;
		try {
			List<AppliedJobDTO> savedJobDTOList = jobSeekerJobDetailService
					.getSavedJobs(userId);
			savedJobsCount = savedJobDTOList.size();
			if (savedJobsCount >= Integer.parseInt(saveJobsLimit)) {
				int oldJobId = savedJobDTOList.get(0).getSaveJobId();
				jobSeekerJobDetailService.updateAppliedSavedJobs(oldJobId);
			}
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the saved job of curresponding  user or while updating the particular job details"
					+ e);
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
		getAdsForAdvcSearch(request, session, model);
		removeSession(session);
		return model;
	}

	/**
	 * Get Ads for advance search results page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void getAdsForAdvcSearch(HttpServletRequest request,
			HttpSession session, ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOBSEEKER_ADVC_JOB_SEARCH);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(ADPAGETOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageRightTop", bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageRightMiddle", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(ADPAGEBTM, bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
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
			BindingResult result, HttpServletRequest request, Model model) {

		try {

			int jobId = Integer.parseInt(request.getParameter("id"));
			String jobTitle = request.getParameter("jobtitle");
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
			 * "jobsearch/viewJobDetails.html?id=" + jobId + "&currentUrl=" +
			 * parentId + "&clickType=view".toString();
			 */

			String fullPath = request
					.getRequestURL()
					.toString()
					.replace(
							request.getServletPath(),
							"/jobsearch/viewJobDetails/" + jobId + "/"
									+ jobTitle + dothtmlExtention);
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
		StringBuffer dataString = new StringBuffer();
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
					userId = (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID);
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
				String jobUrl = sendtofriendmail.getJoburl();
				String joburl = urlLinkFirst + MMJBCommonConstants.EMPTY
						+ jobUrl + MMJBCommonConstants.EMPTY + urlLinkSecond;
				mesg = mesg.append("<TABLE><TR><TD>" + Subject + END_TAGS);
				mesg = mesg.append("<TR><TD>" + bodyHead1 + "\n" + bodyHead2
						+ END_TAGS);
				mesg = mesg.append("<TR><TD><B>[" + jobTitle + "]</B>"
						+ jobDTO.getJobTitle() + END_TAGS);
				if(jobDTO.getCompanyNameDisp() != null){
					mesg = mesg.append("<TR><TD><B>[" + companyName + "]</B>"
							+ jobDTO.getCompanyNameDisp()+ END_TAGS);
				}
				mesg = mesg.append("<TR><TD>" + joburl + "</TD></TR>\n\n\n");
				mesg = mesg.append("<TR><TD>" + jobUrl + "</TD></TR></TABLE>");
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
			// Setting the values into sessionMap
			sessionMap = setValuesToSessionMap(sessionMap, searchSeq,
					jobSearchResultForm);
			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);

		}

		// IMP:::This value should be taken from the UI while sorting
		// Based on the browse by search setting the value for parameters
		// jobposition:"asdfasdf".
		String firstFQParam = MMJBCommonConstants.EMPTY;
		if ((jobSearchResultForm.isBrowseBy())
				&& request.getParameter(MMJBCommonConstants.FIRST_FQ_PARAM) != null) {
			firstFQParam = MMJBCommonConstants.BROWSE_JOB_POSITION
					+ request.getParameter(MMJBCommonConstants.FIRST_FQ_PARAM)
					+ '"';
		}
		String secondFQParam = MMJBCommonConstants.EMPTY;
		if ((jobSearchResultForm.isBrowseBy())
				&& request.getParameter(MMJBCommonConstants.SECOND_FQ_PARAM) != null) {
			secondFQParam = MMJBCommonConstants.BROWSE_COMPANY
					+ request.getParameter(MMJBCommonConstants.SECOND_FQ_PARAM)
					+ '"';
		}
		String thirdFQParam = MMJBCommonConstants.EMPTY;
		if ((jobSearchResultForm.isBrowseBy())
				&& request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM) != null) {
			thirdFQParam = MMJBCommonConstants.BROWSE_LOCATION
					+ request.getParameter(MMJBCommonConstants.THIRD_FQ_PARAM)
					+ '"';
		}
		String fouthFQParam = MMJBCommonConstants.EMPTY;
		String fifthFQParam = MMJBCommonConstants.EMPTY;
		String facetSort = MMJBCommonConstants.COUNT_STR;
		// set the sort order for search results
		String sortOrder = setSortOrder(session, request);

		// Parameter value setting if its refined search
		if (session.getAttribute(MMJBCommonConstants.REFINED).equals(true)) {
			Map<String, String> mapFQParams = getFQParams(request, session);
			secondFQParam = mapFQParams
					.get(MMJBCommonConstants.SECOND_FQ_PARAM);
			thirdFQParam = mapFQParams.get(MMJBCommonConstants.THIRD_FQ_PARAM);
			fouthFQParam = mapFQParams.get(MMJBCommonConstants.FOURTH_FQ_PARAM);
		}

		// Putting all the parameters coming from the UI into a Map for further
		// processing.
		Map<String, String> fqParamMap = getFQMap(firstFQParam, secondFQParam,
				thirdFQParam, fouthFQParam, fifthFQParam, sortOrder, facetSort);

		Map<String, String> paramMap = new HashMap<String, String>();

		// if search is browse jobs then no need of setting the form values
		if (jobSearchResultForm.isBrowseBy()) {
			paramMap.put(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);
		} else {
			paramMap.put(SearchParamDTO.KEYWORDS, jobSearchResultForm
					.getKeywords().trim());

			paramMap.put(SearchParamDTO.CITY_STATE, jobSearchResultForm
					.getCityState().trim());
			paramMap.put(SearchParamDTO.RADIUS, jobSearchResultForm.getRadius()
					.trim());
			paramMap.put(SearchParamDTO.REFINED,
					String.valueOf(jobSearchResultForm.isRefined()));
		}
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
	 * This method is used to set values into the session map.
	 * 
	 * @param sessionMap
	 * @param searchSeq
	 * @param jobSearchResultForm
	 * @return Map<String, String>
	 */

	private Map<String, String> setValuesToSessionMap(
			Map<String, String> sessionMap, int searchSeq,
			JobSearchResultForm jobSearchResultForm) {

		if (sessionMap.get(SearchParamDTO.SEARCH_SEQ) == null) {
			sessionMap.put(SearchParamDTO.SEARCH_SEQ,
					String.valueOf(searchSeq + 1));
		} else {
			sessionMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(Integer
					.parseInt(sessionMap.get(SearchParamDTO.SEARCH_SEQ)) + 1));
		}

		if (!jobSearchResultForm.isBrowseBy()) {
			sessionMap.put(SearchParamDTO.KEYWORDS, jobSearchResultForm
					.getKeywords().trim());
			sessionMap.put(SearchParamDTO.CITY_STATE, jobSearchResultForm
					.getCityState().trim());
			sessionMap.put(SearchParamDTO.RADIUS, jobSearchResultForm
					.getRadius().trim());
		}
		sessionMap.put(MMJBCommonConstants.SEARCH_TYPE, jobSearchResultForm
				.getSearchtype().trim());
		return sessionMap;

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
	 * The method is called to read the branding information from database.
	 * 
	 * @param form
	 * @param facility_id
	 * @return brandingTemplate
	 */
	public JobDTO checkBrand(JobDTO dto) {
		int packageId = 1;
		JobDTO jobDTO = dto;

		// Getting the customer ID from Adm Facility table.
		int nsCustomerID = brandingTemplateService
				.getNSCustomerIDFromAdmFacility(jobDTO.getFacilityId());

		UserDTO userDTO = brandingTemplateService
				.getBrandingInformation(nsCustomerID);

		if (null == userDTO.getPackageName()) {
			jobDTO.setIsSilverCustomer(Boolean.TRUE);
		} else {
			if (userDTO.getPackageName().equalsIgnoreCase("Gold")) {
				jobDTO.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_GOLD;
			} else if (userDTO.getPackageName().equalsIgnoreCase("Platinum")) {
				jobDTO.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_PLATINUM;
			} else {
				jobDTO.setIsSilverCustomer(Boolean.TRUE);
			}
		}

		jobDTO.setPackageId(packageId);
		return jobDTO;
	}

	/**
	 * Delete the Current Search item
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
	 * Added for Browse jobs task. This method is used to get the browse jobs
	 * list by job title
	 * 
	 * @return jsonObject
	 */
	@RequestMapping(value = "/searchJbsByTitle", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchJbsByTitle(HttpServletRequest request,
			HttpSession session, JobSearchResultForm jobSearchResultForm,
			BindingResult result) {
		JSONObject jsonObject = new JSONObject();
		removeSession(session);
		try {
			jobSearchResultForm.setJobTitlePage("true");
			List<JobDTO> jbsByTitleList = jobSearchService
					.getJobsByTitle();
			session.setAttribute("jbsByTitleList", jbsByTitleList);
			session.setAttribute("jobTitlePage", true);
		} catch (Exception e) {
			LOGGER.error("JobSearchController : searchJbsByTitle Exception" + e);
		}
		return jsonObject;
	}

	/**
	 * Added for Browse jobs task. This method is used to get the browse jobs
	 * list by Employer
	 * 
	 * @return jsonObject
	 */
	@RequestMapping(value = "/searchJbsByEmployer", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchJbsByEmployer(HttpServletRequest request,
			HttpSession session, JobSearchResultForm jobSearchResultForm,
			BindingResult result) {

		JSONObject jsonObject = new JSONObject();
		removeSession(session);
		try {
			jobSearchResultForm.setEmployerPage("true");
			List<JobDTO> jbsByEmployerList = jobSearchService
					.getJobsByEmployer();
			session.setAttribute("jbsByEmployerList", jbsByEmployerList);
			session.setAttribute("employerPage", true);
		} catch (Exception e) {
			LOGGER.error("JobSearchController : searchJbsByEmployer Exception"
					+ e);
		}
		return jsonObject;
	}

	/**
	 * Added for Browse jobs task. This method is used to get the browse jobs
	 * list by Location
	 * 
	 * @return jsonObject
	 */
	@RequestMapping(value = "/searchJbsByLocation", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchJbsByLocation(HttpServletRequest request,
			HttpSession session, JobSearchResultForm jobSearchResultForm,
			BindingResult result) {
		JSONObject jsonObject = new JSONObject();
		removeSession(session);
		try {
			jobSearchResultForm.setLocationPage("true");
			List<JobDTO> jbsByLocationList = jobSearchService
					.getJobsByLocation();
			session.setAttribute("jbsByLocationList", jbsByLocationList);
			session.setAttribute("locationPage", true);
		} catch (Exception e) {
			LOGGER.error("JobSearchController : searchJbsByLocation Exception"
					+ e);
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
	public List<String> setVideoURL(JobDTO jobDTO,
			HttpServletRequest request) {
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
	 * This method is used to get the region based on location for
	 * Browse by location.
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

}
