package com.advanceweb.afc.jb.admin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.web.controller.JobPostForm;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.dao.UserDao;
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
/*@Scope("session")*/
public class AdminController extends AbstractController{
	private static final Logger LOGGER = Logger
			.getLogger("AdminController.class");

	// private static final String = null;

	@Autowired
	private JobPostService employerJobPost;

	@Autowired
	private AdminValidation adminValidation;

	@Autowired
	private ProfileRegistration adminService;

	@Autowired
	private TransformAdminImpersonation transformAdminImpersonation;

	@Autowired
	private AdminService service;

	@Autowired
	private AdService adService;
	@Autowired
	private FacilityService facilityService;
	@Autowired
	private UserDao userDAO;
	@Value("${adminChangeMailSubject}")
	private String adminChangeMailSubject;
	@Autowired
	private MMEmailService emailService;
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;
	@Value("${navigationPath}")
	private String navigationPath;
	@Value("${employerPageExtention}")
	private String employerPageExtention;
	private static final String LOGINFORM ="adminLoginForm";
	
	@RequestMapping(value = "/adminMenu", method = RequestMethod.GET)
	public ModelAndView adminMenuPage(ModelMap map, HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		AdminLoginForm adminLoginForm = new AdminLoginForm();
		model.addObject(adminLoginForm);
		getAds(request, session, model);
		model.setViewName("adminLogin");
		return model;
	}

	/**
	 * This method displays the ads 
	 * 
	 * @param session
	 * @param request
	 * @param model
	 */
	private void getAds(HttpServletRequest request, HttpSession session,
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
			model.addObject("adPageTop", bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject("adPageRightTop", bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject("adPageRightMiddle", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject("adPageBottom", bannerString);
		} catch (Exception e) {
			LOGGER.error("Error occurred while getting the html content for Ads"
					, e);
		}
	}

	@RequestMapping(value = "/login")
	public ModelAndView adminImpersonationPage(
			@ModelAttribute(LOGINFORM) AdminLoginForm form) {
		ModelAndView model = new ModelAndView();
		AdminLoginForm adminLoginForm = new AdminLoginForm();
		model.addObject("adminLoginForm", adminLoginForm);
		model.setViewName("adminImpersonation");
		return model;

	}

	@ResponseBody
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String impersonateTheUser(
			@ModelAttribute(LOGINFORM) AdminLoginForm form,
			BindingResult result, HttpServletRequest request,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		adminValidation.validate(form, result);
		model.setViewName("adminLogin");
		if (result.hasErrors()) {
			return result.getFieldError().getDefaultMessage();
		}
		if (!adminService.validateEmail(form.getEmpOrAgencyEmail())) {
			return "Not a registered Employer / Agency!";
		}
		// This is used to check if user authenticated with Open AM.
		// boolean isAuthenticated =
		// OpenAMEUtility.getOpenAMAuthentication(form.getUserEmail(),form.getPassword());
		// if(isAuthenticated){s
		// LOGGER.info("OpenAM : valid user!");
		// }else{
		// LOGGER.info("OpenAM : Invalid User e-mail or password");
		// return "Invalid User e-mail or password";
		// }

		if (!service.validateAdminCredentials(form.getUserEmail(),
				form.getPassword())) {
			return "Invalid User e-mail or password";
		}
		AdminDTO adminDTO = transformAdminImpersonation
				.transformAdminFormToDTO(form);
		boolean adminuserDto = service.impersonateUser(adminDTO);
		if(adminuserDto){
			sendAdministratorUpdateMail(form.getEmpOrAgencyEmail(),request,MMJBCommonConstants.ADMIN_IMPERSONATION_CHANGE);
			return "true";
		}
		return "false";
	}

	/**
	 * method to send mail when change made by administrator
	 * @param form
	 */
	public void sendAdministratorUpdateMail(String email,HttpServletRequest request,String ChangeRsn) {
		UserDTO merUserdto = userDAO.getUser(email);
		EmployerInfoDTO facilityDetail = facilityService
				.facilityDetails(merUserdto.getUserId());
		StringBuffer admChangeDetail = new StringBuffer();
		int start, end;
		String userName=merUserdto.getFirstName()+" " + merUserdto.getLastName();
		String loginPath = navigationPath.substring(2);
		String employerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), loginPath)
				+ dothtmlExtention + employerPageExtention;
		start = MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY.toString().indexOf(
				"?userName");
		end = start + "?userName".length();
		if (start > 0 && end > 0) {
			MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY
					.replace(start, end,userName);
		}
		start = MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY.toString().indexOf(
				"?companyName");
		end = start + "?companyName".length();
		if (start > 0 && end > 0) {
			MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY
					.replace(start, end,facilityDetail.getCustomerName());
		}
		start = MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY.toString().indexOf(
				"?empdashboardLink");
		end = start + "?empdashboardLink".length();
		if (start > 0 && end > 0) {
			MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY
					.replace(start, end,employerloginUrl);
		}
		
		start = MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY.toString().indexOf(
				"?changeType");
		end = start + "?changeType".length();
		if (start > 0 && end > 0) {
			MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY
					.replace(start, end,ChangeRsn);
		}
		EmailDTO emailDTO = new EmailDTO();
		InternetAddress[] jsToAddress = new InternetAddress[1];

		try {
			jsToAddress[0] = new InternetAddress(email);
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		emailDTO.setToAddress(jsToAddress);
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(adminChangeMailSubject);
		admChangeDetail.append(MMJBCommonConstants.EMPLOYEREMAILHEADER);
		admChangeDetail.append(MMJBCommonConstants.ADMINSTRATORCHANGEEMAILBODY);
		admChangeDetail.append(MMJBCommonConstants.EMAILFOOTER);
		emailDTO.setBody(admChangeDetail.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
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
		try {
			String advJobId = request.getParameter("advJobId");
			session.removeAttribute("postedJobList");
			List<JobPostDTO> postedJobList = new ArrayList<JobPostDTO>();
			int advSearchId = Integer.parseInt(advJobId);
			postedJobList = employerJobPost
					.retrieveAllJobPostByADvSearch(advSearchId);
			if(postedJobList.get(0).getEndDt() !=null){
			session.setAttribute("postedJobList", postedJobList);
			}

		} catch (Exception e) {
			LOGGER.info("Manager Edit Job Posting Search Option");
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
			AdmFacilityJpAudit admFacilityJpAudit=employerJobPost.getinvDtlByJobId(jobId);
			if(null != admFacilityJpAudit){
			UserDTO userDTO=userDAO.getUserByUserId(admFacilityJpAudit.getId().getUserId());
			sendAdministratorUpdateMail(userDTO.getEmailId(), request, MMJBCommonConstants.ADMIN_JOBPOST_EXPIREDATE_CHANGE.replace("?jobId", String.valueOf(jobId)));	
			}
		} catch (Exception e) {
			LOGGER.info("Manager Edit Job Posting Search Option");
		}
		return "";
	}

}
