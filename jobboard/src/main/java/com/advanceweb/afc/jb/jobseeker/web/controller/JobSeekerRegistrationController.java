package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * @Author : Sasibhushana
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class is used as controller for job seeker regigstration
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
    protected AuthenticationManager customAuthenticationManager;
	
	@Autowired
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;
	
	@Autowired
	private JobSeekerRegistrationValidation registerValidation;
		
	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;
	
	private Long placeKey;
	
	/**
	 * This method is called to display job seeker registration page Step1
	 * Create Your Account
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createJobSeekerCreateYrAcct",method = RequestMethod.GET)
	public ModelAndView createJobSeekerRegistrationStep1(HttpSession session) {		
		
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
		
		placeKey = (new Random()).nextLong();
		
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
			BindingResult result, HttpSession session, HttpServletRequest request) {
			ModelAndView model = new ModelAndView();
		try {
			
			if (((Long) session.getAttribute("LAST_PLACE_KEY"))!=null && ((Long) session.getAttribute("LAST_PLACE_KEY")).equals(placeKey)) {
					model.setViewName("forward:/jobSeeker/jobSeekerDashBoard.html");
					return model;
				}
			
				if(null != registerForm.getListProfAttribForms()){
					model.setViewName("jobSeekerCreateAccountInfo");
					for(JobSeekerProfileAttribForm form : registerForm.getListProfAttribForms()){
						
						//Checking validation for input text box
						if(form.getbRequired() !=0 && StringUtils.isEmpty(form.getStrLabelValue()) 
								&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(form.getStrLabelName())){
							model.addObject("message","Please fill the Required fields");
							return model;
						}
						
						//Checking validation for dropdowns & checkboxes etc
						if(form.getbRequired() !=0 && MMJBCommonConstants.ZERO.equals(form.getStrLabelValue()) 
								&& (MMJBCommonConstants.DROP_DOWN.equals(form.getStrAttribType())
								|| MMJBCommonConstants.CHECK_BOX.equals(form.getStrAttribType()))){
							model.addObject("message","Please fill the Required fields");
							return model;
						}
						//validation mobile number
						if(MMJBCommonConstants.PHONE_NUMBER.equals(form.getStrLabelName()) && !StringUtils.isEmpty(form.getStrLabelValue())
								&& !registerValidation.validateMobileNumberPattern(form.getStrLabelValue())){
							model.addObject("message",jobseekerRegPhoneMsg);
							return model;
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
				userDTO = profileRegistration.createNewProfile(jsRegistrationDTO);
				session.setAttribute("userName", userDTO.getFirstName()+" "+userDTO.getLastName());
				session.setAttribute("userId", userDTO.getUserId());
				session.setAttribute("userEmail", userDTO.getEmailId());
				session.setAttribute(MMJBCommonConstants.LAST_PLACE_KEY, placeKey);
				
				model.setViewName("forward:/jobSeeker/jobSeekerDashBoard.html");
			    authenticateUserAndSetSession(userDTO, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	 private void authenticateUserAndSetSession(MerUserDTO user,
		        HttpServletRequest request)
		{
		 List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		 authList.add(new SimpleGrantedAuthority(MMJBCommonConstants.ROLE_JOB_SEEKER));
		    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
		            user.getEmailId(), user.getPassword(),authList);
		    token.setDetails(new WebAuthenticationDetails(request));
		    Authentication authenticatedUser = customAuthenticationManager.authenticate(token);
		    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
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
			BindingResult result, HttpSession session) {
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
					if(MMJBCommonConstants.PHONE_NUMBER.equals(form.getStrLabelName()) && !StringUtils.isEmpty(form.getStrLabelValue()) 
							&& !registerValidation.validateMobileNumberPattern(form.getStrLabelValue())){
						return jobseekerRegPhoneMsg;
					}
					
					//validation mobile number
					if(MMJBCommonConstants.EMAIL_ADDRESS.equals(form.getStrLabelName())){
						if(!registerValidation.validateEmailPattern(form.getStrLabelValue())){
							return "Please enter the correct E-Mail Address";
						}/*else{
							if(profileRegistration.validateEmail(form.getStrLabelValue())){
								return "Email Id already Exists!";
							}
						}*/
					}					
				}
			}
			
			JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
			List<MerProfileAttribDTO> attribList = transformJobSeekerRegistration.
					transformProfileAttribFormToDTO(registerForm.getListProfAttribForms());
			MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(registerForm);
			userDTO.setUserId((Integer) session.getAttribute("userId"));
			jsRegistrationDTO.setAttribList(attribList);
			jsRegistrationDTO.setMerUserDTO(userDTO);
			// Call to service layer
			profileRegistration.modifyProfile(jsRegistrationDTO);
			session.setAttribute("userName", userDTO.getFirstName()+" "+userDTO.getLastName());
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
	public String updateNewPassword(ChangePasswordForm form, HttpSession session) {
		try {		
			
			JobSeekerRegistrationDTO jsRegistrationDTO = new  JobSeekerRegistrationDTO();
			
			String errorMessage =validatePasswords(form.getPassword(), form.getRetypepassword());
			if(!StringUtils.isEmpty(errorMessage)){
				return errorMessage;
			}
			
			MerUserDTO userDTO = transformJobSeekerRegistration.transformChangePasswordFormToMerUserDTO(form);
			userDTO.setUserId((Integer) session.getAttribute("userId"));
			jsRegistrationDTO.setMerUserDTO(userDTO);

			// Call to service layer
			if(profileRegistration.validatePassword(jsRegistrationDTO)){
				profileRegistration.changePassword(jsRegistrationDTO);
			}else{
				return "Invalid Current Password";				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * This method is called to validate passwords
	 * 
	 * @param password
	 * @param retypePassword
	 * @return
	 */
	private String validatePasswords(String password, String retypePassword){
		 if(StringUtils.isEmpty(password)){
			 return "Password Should not be empty";
		 }
		 
		 if(StringUtils.isEmpty(retypePassword)){
			 return "Password Should not be empty";
		 }
		 
		 if(!StringUtils.isEmpty(password) 
				 && !StringUtils.isEmpty(retypePassword)){
			 
			 if(!registerValidation.validatePasswordPattern(password)){
				 return "Password should contain 8-20 characters, including at least 1 number"; 
			 }
			 
			 if(!registerValidation.validatePasswordPattern(retypePassword)){
				 return "Password should contain  8-20 characters, including at least 1 number"; 
			 }
			 
			 if(!password.equals(retypePassword)){
				return "Passwords Doesn't match.";
			 }
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
