package com.advanceweb.afc.jb.webapp.web.controllers.registration;



import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.dropdowns.PopulateDropdowns;
import com.advanceweb.afc.jb.registration.ProfileRegistration;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerRegistrationForm;
import com.advanceweb.afc.jb.webapp.web.transformers.TransformJobSeekerRegistration;

@Controller
@RequestMapping("/jobseekerregistration")
public class JobSeekerRegistrationController {
	
	@Autowired
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;

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
		List<CountryDTO> listCountryDTO= populateDropdownsService.getCountryList();
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
	public ModelAndView saveJobSeekerRegistration(@Valid JobSeekerRegistrationForm form,
			BindingResult result) {

		try {
			
				if (result.hasErrors()) {
					return new ModelAndView("jobseekerregistration");
				}
		
				// Transform JobSeeker Registration Form to JobSeekerRegistrationDTO
				JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
				AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(form);
				MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(form);
				JobSeekerProfileDTO jsProfileSettingsDTO = transformJobSeekerRegistration.createJSProfileSettingsDTO(form);
				jsRegistrationDTO.setAddressDTO(addDTO);
				jsRegistrationDTO.setJobSeekerProfileDTO(jsProfileSettingsDTO);
				jsRegistrationDTO.setMerUserDTO(userDTO);
				// Call to service layer
				profileRegistration.createNewProfile(jsRegistrationDTO);
		
//				model.put("jobSeekerRegistrationForm", jobSeekerRegistrationForm);
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
	public ModelAndView viewJobSeekerProfileSettings(JobSeekerRegistrationForm form,Map model) {

		try {

			// Call to service layer
			
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration.viewProfile(1);
			form = transformJobSeekerRegistration.jsRegistrationDTOToJobSeekerRegistrationForm(jsRegistrationDTO);

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
			AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(jsRegistrationForm);
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
