package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * @Author : Sasibhushana
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class is used as controller for job seeker regigstration
 */
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.web.controller.ChangePasswordForm;
import com.advanceweb.afc.jb.user.ProfileRegistration;

@Controller
@RequestMapping("/jobseekerregistration")
@SessionAttributes("registerForm")
@Scope("session")
public class JobSeekerRegistrationController {
	
	@Autowired
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;
	
	@Autowired
	private JobSeekerRegistrationValidation registerValidation;

	
	/**
	 * This method is called to display job seeker registration page Step1
	 * Create Your Account
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createJobSeekerCreateYrAcct",method = RequestMethod.GET)
	public ModelAndView createJobSeekerRegistrationStep1() {
		
		ModelAndView model = new ModelAndView();
		JobSeekerRegistrationForm registerForm = new JobSeekerRegistrationForm();
		model.setViewName("jobSeekerCreateAccount");
		model.addObject("registerForm", registerForm);	
		return model;
		
	}
	
	
	/**
	 * This method is called to display job seeker registration page
	 * Create Your information
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createJobSeekerYourInfo",method = RequestMethod.POST, params="Next")
	public ModelAndView createJobSeekerRegistration(@ModelAttribute("registerForm") JobSeekerRegistrationForm registerForm, 
			BindingResult result) {
		
		ModelAndView model = new ModelAndView();
				
		registerValidation.validate(registerForm, result);
		
		if(result.hasErrors()){
			model.setViewName("jobSeekerCreateAccount");
			return model;
		}
		
		if(profileRegistration.validateEmail(registerForm.getEmailId())){
			model.setViewName("jobSeekerCreateAccount");
			result.rejectValue("emailId", "NotEmpty", "Email Id already Exists!");
			return model;
		}
		
		JobSeekerRegistrationDTO registerDTO = (JobSeekerRegistrationDTO) profileRegistration.getProfileAttributes();

		List<JobSeekerProfileAttribForm> listProfAttribForms = 
				transformJobSeekerRegistration.transformDTOToProfileAttribForm(registerDTO);
		registerForm.setListProfAttribForms(listProfAttribForms);
		model.setViewName("jobSeekerCreateAccountInfo");
		model.addObject("registerForm", registerForm);
		return model;
		
	}
	
	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveJobSeekerProfile",method = RequestMethod.POST, params="Finish")
	public ModelAndView saveJobSeekerRegistration(@ModelAttribute("registerForm")  JobSeekerRegistrationForm registerForm,
			BindingResult result, HttpSession session) {
			ModelAndView model = new ModelAndView();
		try {
			
				if(null != registerForm.getListProfAttribForms()){
					for(JobSeekerProfileAttribForm form : registerForm.getListProfAttribForms()){
						
						//Checking validation for input text box
						if(form.getbRequired() !=0 && StringUtils.isEmpty(form.getStrLabelValue()) 
								&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(form.getStrLabelName())){
							return new ModelAndView("jobSeekerCreateAccountInfo","message","Please fill the Required fields");
						}
						
						//Checking validation for dropdowns & checkboxes etc
						if(form.getbRequired() !=0 && MMJBCommonConstants.ZERO.equals(form.getStrLabelValue()) 
								&& (MMJBCommonConstants.DROP_DOWN.equals(form.getStrAttribType())
								|| MMJBCommonConstants.CHECK_BOX.equals(form.getStrAttribType()))){
							return new ModelAndView("jobSeekerCreateAccountInfo","message","Please fill the Required fields");
						}
						//validation mobile number
						if(MMJBCommonConstants.PHONE_NUMBER.equals(form.getStrLabelName()) 
								&& !registerValidation.validateMobileNumberPattern(form.getStrLabelValue())){
							return new ModelAndView("jobSeekerCreateAccountInfo","message","Phone number should contain only numbers");
						}
					}
				}
			
			
				JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
				MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(registerForm);
				List<MerProfileAttribDTO> attribLists = transformJobSeekerRegistration.
						transformProfileAttribFormToDTO(registerForm.getListProfAttribForms());
				jsRegistrationDTO.setAttribList(attribLists);
				jsRegistrationDTO.setMerUserDTO(userDTO);

				// Call to service layer
				profileRegistration.createNewProfile(jsRegistrationDTO);
				session.setAttribute("userName", registerForm.getFirstName()+" "+registerForm.getLastName());
				model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveJobSeekerProfile",method = RequestMethod.POST, params="Back")
	public ModelAndView backToCreateJobSeekerCreateYrAcct(@ModelAttribute("registerForm") @Valid JobSeekerRegistrationForm registerForm,
			BindingResult result) {
		return new ModelAndView("jobSeekerCreateAccount","registerForm", registerForm);
	}
		
	/**
	 * This method is called to navigate to home page on click of cancel
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveJobSeekerProfile",method = RequestMethod.POST, params="Cancel")
	public ModelAndView backToHomePage() {

		return new ModelAndView("healthcarejobs/advanceweb.html","", "");
	}
			
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewJobSeekerProfile", method=RequestMethod.GET)
	public ModelAndView viewJobSeekerProfileSettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			JobSeekerRegistrationForm form = new JobSeekerRegistrationForm();
			// Call to service layer
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration.viewProfile
					((Integer) session.getAttribute("userId"));
			List<JobSeekerProfileAttribForm> listProfAttribForms = 
					transformJobSeekerRegistration.transformDTOToProfileAttribForm(jsRegistrationDTO);
			form.setListProfAttribForms(listProfAttribForms);
			model.addObject("registerForm", form);
			model.setViewName("jobseekerEditProfileSettings");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateJobSeekerProfile", method=RequestMethod.POST)
	public String updateJobSeekerProfileSettings(@ModelAttribute("registerForm") JobSeekerRegistrationForm registerForm,
			BindingResult result) {
			ModelAndView model = new ModelAndView();
		try {	
			
			if(null != registerForm.getListProfAttribForms()){
				for(JobSeekerProfileAttribForm form : registerForm.getListProfAttribForms()){
					
					//Checking validation for input text box
					if(form.getbRequired() !=0 && StringUtils.isEmpty(form.getStrLabelValue())){
						return "Please fill the Required fields";
					}
					
					//Checking validation for dropdowns & checkboxes etc
					if(form.getbRequired() !=0 && !MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(form.getStrLabelName()) 
							&& MMJBCommonConstants.ZERO.equals(form.getStrLabelValue()) 
							&& (MMJBCommonConstants.DROP_DOWN.equals(form.getStrAttribType())
							|| MMJBCommonConstants.CHECK_BOX.equals(form.getStrAttribType()))){
						return "Please fill the Required fields";
					}
					//validation mobile number
					if(MMJBCommonConstants.PHONE_NUMBER.equals(form.getStrLabelName()) 
							&& !registerValidation.validateMobileNumberPattern(form.getStrLabelValue())){
						return "Phone number should contain only numbers";
					}
					
					//validation mobile number
					if(MMJBCommonConstants.EMAIL_ADDRESS.equals(form.getStrLabelName())){
						if(!registerValidation.validateEmailPattern(form.getStrLabelValue())){
							return "Please enter the correct E-Mail Address";
						}else{
							if(profileRegistration.validateEmail(form.getStrLabelValue())){
								return "Email Id already Exists!";
							}
						}
					}					
				}
			}
			
			JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
			List<MerProfileAttribDTO> attribList = transformJobSeekerRegistration.
					transformProfileAttribFormToDTO(registerForm.getListProfAttribForms());
			MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(registerForm);
			userDTO.setUserId(1565);
			jsRegistrationDTO.setAttribList(attribList);
			jsRegistrationDTO.setMerUserDTO(userDTO);

			// Call to service layer
			profileRegistration.modifyProfile(jsRegistrationDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/jobSeekerUpdatePassword",method = RequestMethod.POST)
	public String updateNewPassword(@Valid ChangePasswordForm form,
			BindingResult result) {
			ModelAndView model = new ModelAndView();
		try {		
			
			JobSeekerRegistrationDTO jsRegistrationDTO = new  JobSeekerRegistrationDTO();
			MerUserDTO userDTO = transformJobSeekerRegistration.transformChangePasswordFormToMerUserDTO(form);
			jsRegistrationDTO.setMerUserDTO(userDTO);
			model.addObject("changePasswordForm", form);			
			
			// Call to service layer
			if(profileRegistration.validatePassword(jsRegistrationDTO)){
				registerValidation.validatePassoword(form.getPassword(), form.getRetypepassword(), result);
				if(result.hasErrors()){
					model.setViewName("jobseekerchangepassword");
					return "jobseekerchangepassword";
				}
				profileRegistration.changePassword(jsRegistrationDTO);
			}else{
				model.setViewName("jobseekerchangepassword");
				result.rejectValue("currentPassword", "NotEmpty", "Invalid Current Password");
				return "jobseekerchangepassword";
			}
			model.setViewName("registrationsuccess");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/jobSeekerChangePassword",method = RequestMethod.GET)
	public String jsChangePassword(Map model, HttpSession session) {
		
		try {		
			ChangePasswordForm form = new ChangePasswordForm();
			form.setEmailId((String) session.getAttribute("userEmail"));
			model.put("changePasswordForm", form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jobseekerchangepassword";
	}
	

}
