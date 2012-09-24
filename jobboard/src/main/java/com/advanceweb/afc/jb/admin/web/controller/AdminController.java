package com.advanceweb.afc.jb.admin.web.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.employer.web.controller.JobPostForm;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.admin.service.ImpersonateUserService;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.util.OpenAMEUtility;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author muralikc
 *
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes("adminLoginForm")
@Scope("session")
public class AdminController {
	private static final Logger LOGGER = Logger
			.getLogger("AdminController.class");

	//private static final String  = null;

	@Autowired
	private JobPostService employerJobPost;
	
	@Autowired
	private AdminValidation adminValidation;
	
	@Autowired
	private ProfileRegistration adminService;
	
	@Autowired
	TransformAdminImpersonation transformAdminImpersonation;
	
	@Autowired
	ImpersonateUserService impersonateUserService;
	
	@RequestMapping(value = "/adminMenu", method = RequestMethod.GET)
	public ModelAndView adminMenuPage(ModelMap map) {
		ModelAndView model = new ModelAndView();
		AdminLoginForm adminLoginForm = new AdminLoginForm();
		model.addObject(adminLoginForm);
		model.setViewName("adminLogin");
		return model;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView adminImpersonationPage(){
		ModelAndView model = new ModelAndView();
		model.setViewName("adminImpersonation");
		return model;
		
	}
	
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String impersonateTheUser(@ModelAttribute("adminLoginForm") AdminLoginForm form, BindingResult result){
		ModelAndView model = new ModelAndView();
		adminValidation.validate(form, result);
		model.setViewName("adminLogin");
		if(result.hasErrors()){
			return result.getFieldError().getDefaultMessage();
		}
		if (!adminService.validateEmail(form.getEmpOrAgencyEmail())) {
			return "Not a register Employer/Agency!";
		}
		// This is used to check if user authenticated with Open AM.
		boolean isAuthenticated = OpenAMEUtility.getOpenAMAuthentication(form.getUserEmail(),form.getPassword());
		if(isAuthenticated){
			LOGGER.info("OpenAM : valid user!");
		}else{
			LOGGER.info("OpenAM : Invalid User E-mail, password");
			return "Not a valid User E-Mail Or Password";
		}
		if(!impersonateUserService.validateAdminCredentials(form.getUserEmail(), form.getPassword())){
			return "Not a valid User E-Mail Or Password";
		}
		AdminDTO adminDTO =transformAdminImpersonation.transformAdminFormToDTO(form);
		boolean adminuserDto = impersonateUserService.impersonateUser(adminDTO);
		return "";
	}
	/**
	 * 
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/editJobPosting")
	public ModelAndView editJobPosting(HttpSession session){
		ModelAndView model = new ModelAndView();
		session.removeAttribute("postedJobList");
		model.setViewName("adminEditJobPosting");
		return model;
		
	}
	/*@RequestMapping(value="/editJobPostInventory")
	public ModelAndView editJobPostInventory(){
		ModelAndView model = new ModelAndView();
		model.setViewName("adminEditJobPostInventory");
		return model;
		
	}*/
	/**
	 * @author kartikm
	 * Called a function to get the adminEditJobSave page.
	 * for search display
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @param result
	 * @return jsonObject
	 */
	@RequestMapping(value = "/manageEditJobSearch", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getJobPostDetails(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform,BindingResult result) {
		JSONObject jsonObject = new JSONObject();
		try{
		String id=request.getParameter("advJobId");
		session.removeAttribute("postedJobList");
		List<JobPostDTO> postedJobList = new ArrayList<JobPostDTO>();
		int advSearchId=Integer.parseInt(id);		
		postedJobList = employerJobPost.retrieveAllJobPostByADvSearch(advSearchId);
		session.setAttribute("postedJobList", postedJobList);

		}catch (Exception e) {
			LOGGER.info("Manager Edit Job Posting Search Option");
		}
		return jsonObject;
	}
	
	/**
	 * @author kartikm
	 * Called a function to get the adminEditJobSave page.
	 * for search display
	 * @param response
	 * @param request
	 * @param model
	 * @return modelAndView
	 */
	@RequestMapping(value = "/adminEditJobSave")
	public ModelAndView getAdminEditJobSave(HttpServletResponse response,
			HttpServletRequest request, Model model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminEditJobSave");
		return modelAndView;
	}
	/**
	 * @author kartikm
	 * This is the save method call when date change and save button 
	 * click then it is Change from active, inactive, Draft, Expired 
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manageEditJobSearchSave", method = RequestMethod.GET)
	public String getJobPostDetailsSave(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform,BindingResult result) {

		try{
			JobPostDTO dto=new JobPostDTO();
		String id=request.getParameter("advJobId");
		int jobId=Integer.parseInt(id);
		String endDate=request.getParameter("endDate");
		String startDate=request.getParameter("startDate");
		dto.setJobId(jobId);
		dto.setStartDt(startDate);
		dto.setEndDt(endDate);

		employerJobPost.jobSaveByAdmin(dto,jobId);
		}catch (Exception e) {
			LOGGER.info("Manager Edit Job Posting Search Option");
		}
		return "";
	}
	
	
	

}
