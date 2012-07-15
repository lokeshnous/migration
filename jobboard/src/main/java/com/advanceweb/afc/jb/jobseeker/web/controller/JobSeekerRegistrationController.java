package com.advanceweb.afc.jb.jobseeker.web.controller;



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
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.user.ProfileRegistration;

@Controller
@RequestMapping("/jobseekerregistration")
public class JobSeekerRegistrationController {
	
	@Autowired(required=true)
	private ProfileRegistration profileRegistration;

	@Autowired(required=true)
	private TransformJobSeekerRegistration transformJobSeekerRegistration;
	
	@Autowired(required=true)
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
		ContactInfoForm contactInfo = new ContactInfoForm();
		List<CountryDTO> countryList= populateDropdownsService.getCountryList();
		List<EmploymentInfoDTO> empInfoList= populateDropdownsService.getEmployementInfoList();
		List<EthenticityDTO> ethnicityList= populateDropdownsService.getEthenticityList();
		List<GenderDTO> genderList= populateDropdownsService.getGenderList();
		List<VeteranStatusDTO> veteranStatusList= populateDropdownsService.getVeteranStatusList();
		jobSeekerRegistrationForm.setContactForm(contactInfo);
		
		model.put("countryList",countryList);
		model.put("employmentInfoList",empInfoList);
		model.put("genderList",genderList);
		model.put("ethnicityList",ethnicityList);
		model.put("veteranStatusList",veteranStatusList);
		
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
				AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(form.getContactForm());
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
	public ModelAndView viewJobSeekerProfileSettings(Map model) {
		try {

			// Call to service layer
			
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration.viewProfile(1);
			JobSeekerRegistrationForm form = transformJobSeekerRegistration.jsRegistrationDTOToJobSeekerRegistrationForm(jsRegistrationDTO);

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
