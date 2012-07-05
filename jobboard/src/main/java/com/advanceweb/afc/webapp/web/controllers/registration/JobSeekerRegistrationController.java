package com.advanceweb.afc.webapp.web.controllers.registration;



import java.util.Map;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.advanceweb.afc.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.registration.ProfileRegistration;
import com.advanceweb.afc.webapp.web.forms.registration.JobSeekerRegistrationForm;
import com.advanceweb.afc.webapp.web.transformers.TransformJobSeekerRegistration;

@Controller
@RequestMapping("/jobseekerregistration.html")
public class JobSeekerRegistrationController {
	


	@Autowired
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

	public JobSeekerRegistrationController() {
	}

	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String createJobSeekerRegistration(Map model) {
		
		JobSeekerRegistrationForm jobSeekerRegistrationForm = new JobSeekerRegistrationForm();
		model.put("jobSeekerRegistrationForm", jobSeekerRegistrationForm);

		return "jobseekerregistration";
	}

	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String saveJobSeekerRegistration(
			@Valid JobSeekerRegistrationForm jobSeekerRegistrationForm,
			BindingResult result, Map model) {

/*		if (result.hasErrors()) {
			return "jobseekerregistration";
		}*/

		// Transform JobSeeker Registration Form to JobSeekerRegistrationDTO
		JobSeekerRegistrationDTO jobSeekerRegistrationDTO = transformJobSeekerRegistration
				.JobSeekerRegistrationFormToJobSeekerRegistrationDTO(jobSeekerRegistrationForm);

		// Call to service layer
		profileRegistration.createNewProfile(jobSeekerRegistrationDTO);

		model.put("jobSeekerRegistrationForm", jobSeekerRegistrationForm);

		return "registrationsuccess";
	}
	
	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value="/editJSProfileSettings",method = RequestMethod.GET)
	public String editJobSeekerProfileSettings(
			@Valid JobSeekerRegistrationForm jobSeekerRegistrationForm,
			BindingResult result, Map model) {

		if (result.hasErrors()) {
			return "jobseekerregistration";
		}

		// Transform JobSeeker Registration Form to JobSeekerRegistrationDTO
		JobSeekerRegistrationDTO jobSeekerRegistrationDTO = transformJobSeekerRegistration
				.JobSeekerRegistrationFormToJobSeekerRegistrationDTO(jobSeekerRegistrationForm);

		// Call to service layer
		profileRegistration.createNewProfile(jobSeekerRegistrationDTO);

		model.put("jobSeekerRegistrationForm", jobSeekerRegistrationForm);
		return "registrationsuccess";
	}*/

}
