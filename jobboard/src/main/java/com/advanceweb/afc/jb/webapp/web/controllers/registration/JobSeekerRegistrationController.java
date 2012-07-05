package com.advanceweb.afc.jb.webapp.web.controllers.registration;



import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.registration.ProfileRegistration;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerProfileSettingsForm;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerRegistrationForm;
import com.advanceweb.afc.jb.webapp.web.transformers.TransformJobSeekerRegistration;

@Controller
@RequestMapping("/jobseekerregistration")
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
	@RequestMapping(value="/createJobSeekerProfile",method = RequestMethod.GET)
	public ModelAndView createJobSeekerRegistration(Map model) {
		
		JobSeekerRegistrationForm jobSeekerRegistrationForm = new JobSeekerRegistrationForm();
		model.put("jobSeekerRegistrationForm", jobSeekerRegistrationForm);
		return new ModelAndView("jobseekerregistration");
	}

	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveJobSeekerProfile",method = RequestMethod.POST)
	public ModelAndView saveJobSeekerRegistration(@Valid JobSeekerRegistrationForm jobSeekerRegistrationForm,
			BindingResult result,Map model) {

		try {
				if (result.hasErrors()) {
					return new ModelAndView("jobseekerregistration");
				}
		
				// Transform JobSeeker Registration Form to JobSeekerRegistrationDTO
				JobSeekerRegistrationDTO jobSeekerRegistrationDTO = transformJobSeekerRegistration
						.jsRegistrationFormTojsRegistrationDTO(jobSeekerRegistrationForm);
		
				// Call to service layer
				profileRegistration.createNewProfile(jobSeekerRegistrationDTO);
		
				model.put("jobSeekerRegistrationForm", jobSeekerRegistrationForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("registrationsuccess");
	}
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewJobSeekerProfile",method = RequestMethod.GET)
	public String viewJobSeekerProfileSettings(Map model) {

		try {

			// Call to service layer
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration.viewProfile(1);
			JobSeekerProfileSettingsForm jsProfileSettingsForm = new JobSeekerProfileSettingsForm();
			jsProfileSettingsForm = transformJobSeekerRegistration
					.jsRegistrationDTOTojsProfilesSettingsForm(jsRegistrationDTO, jsProfileSettingsForm);

			model.put("jobSeekerRegistrationForm", jsProfileSettingsForm);
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
	@RequestMapping(value="/updateJobSeekerProfile",method = RequestMethod.GET)
	public String updateJobSeekerProfileSettings(@Valid JobSeekerProfileSettingsForm jsProfileSettingsForm,
			BindingResult result,Map model) {

		try {
			
			JobSeekerRegistrationDTO jsRegistrationDTO = transformJobSeekerRegistration.
					jsProfileSettingsFormTojsRegistrationDTO(jsProfileSettingsForm);
			// Call to service layer
			profileRegistration.modifyProfile(jsRegistrationDTO);
			model.put("jobSeekerRegistrationForm", jsProfileSettingsForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationsuccess";
	}
	

}
