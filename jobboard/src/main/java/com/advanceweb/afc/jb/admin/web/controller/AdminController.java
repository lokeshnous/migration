package com.advanceweb.afc.jb.admin.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
			return "Not a valid User E-Mail Or Password from openAM";
		}
		if(!impersonateUserService.validateAdminCredentials(form.getUserEmail(), form.getPassword())){
			return "Not a valid User E-Mail Or Password";
		}
		AdminDTO adminDTO =transformAdminImpersonation.transformAdminFormToDTO(form);
		boolean adminuserDto = impersonateUserService.impersonateUser(adminDTO);
		return "";
	}
	
	@RequestMapping(value="/editJobPosting")
	public ModelAndView editJobPosting(){
		ModelAndView model = new ModelAndView();
		model.setViewName("adminEditJobPosting");
		return model;
		
	}
	@RequestMapping(value="/editJobPostInventory")
	public ModelAndView editJobPostInventory(){
		ModelAndView model = new ModelAndView();
		model.setViewName("adminEditJobPostInventory");
		return model;
		
	}
	
	

}
