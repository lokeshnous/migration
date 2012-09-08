package com.advanceweb.afc.jb.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
	
	@Autowired
	private AdminValidation adminValidation;
	
	@Autowired
	private ProfileRegistration adminService;
	
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
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public ModelAndView impersonateTheUser(AdminLoginForm adminLoginForm, BindingResult result){
		ModelAndView model = new ModelAndView();
		adminValidation.validate(adminLoginForm, result);
		model.setViewName("adminLogin");
		if(result.hasErrors()){
			return model;
		}
		if (adminService.validateEmail(adminLoginForm.getJobSeekerOrEmpOrAgeEmail())) {
		}else{
			result.rejectValue("jobSeekerOrEmpOrAgeEmail", "NotEmpty",
					"Not a register user!");
		}
		return model;
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
