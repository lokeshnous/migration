package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * <code> JobSeekerProfileManagement <code>
 * @author sasibhushanam
 * @version 1.0
 * @since July 2nd, 2012
 *
 */
@Controller
@RequestMapping("/jobseekerprofilemgmt")
public class JobSeekerProfileManagement {
	

	@Autowired
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;
	
	/**
	 * This method is called to view job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewJobSeekerProfile",method = RequestMethod.GET)
	public ModelAndView viewJobSeekerProfileSettings(JobSeekerRegistrationForm form,Map model) {

		try {
			// Call to service layer
			
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration.viewProfile(1);
			transformJobSeekerRegistration.jsRegistrationDTOToJobSeekerRegistrationForm(jsRegistrationDTO);

			model.put("jobseekerregistration", form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("jobseekerregistration");
	}
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateJobSeekerProfile",method = RequestMethod.GET)
	public String updateJobSeekerProfileSettings(@Valid JobSeekerRegistrationForm jsRegistrationForm,
			BindingResult result,Map model) {
		
		try {			
			JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
			AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(jsRegistrationForm.getContactForm());
			MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(jsRegistrationForm);
			jsRegistrationDTO.setAddressDTO(addDTO);
			jsRegistrationDTO.setMerUserDTO(userDTO);
			// Call to service layer
			profileRegistration.modifyProfile(jsRegistrationDTO);
			model.put("jobseekerregistration", jsRegistrationForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationsuccess";
	}
	
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/jobSeekerChangePassword",method = RequestMethod.GET)
	public String jsChangePassword(@Valid JobSeekerRegistrationForm jsRegistrationForm,
			BindingResult result,Map model) {
		
		try {			
			JobSeekerRegistrationDTO jsRegistrationDTO = new  JobSeekerRegistrationDTO();
			MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(jsRegistrationForm);
			jsRegistrationDTO.setMerUserDTO(userDTO);
			// Call to service layer
			profileRegistration.changePassword(jsRegistrationDTO);
			model.put("jobSeekerRegistrationForm", jsRegistrationForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationsuccess";
	}
	

	
}
