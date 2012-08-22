package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerProfileAttribForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerRegistrationForm;

/**
 * <code>EmpBrandTempController</code>This controller belongs to manage job
 * posting Branding Template
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Controller
@RequestMapping("/brandingTemplates")
@SessionAttributes("brandingTemplateForm")
@SuppressWarnings("rawtypes")
public class BrandingTemplateController {
	
	@Autowired
	private BrandingTemplateService empBrandTemp;

	/**
	 * The method is called to fetch the job posting Branding Templates
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/empBrandTempList", method = RequestMethod.GET)
	public ModelAndView fetchEmpBrandTemp(Map<String, Object> model) {

//		MerUserDTO merUserDTO = new MerUserDTO();
//		merUserDTO.setUserId(36);
//		List<EmpBrandTempDTO> empBrandTempDTOs = empBrandTemp
//				.fetchEmpBrandTemp(merUserDTO);
//		
//		model.put("templatesList", empBrandTempDTOs);

		return new ModelAndView("empBrandTempListPopup");
	}

	/**
	 * The method is called to create the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveEmpBrandTemp", method = RequestMethod.POST)
	public ModelAndView createEmpBrandTemp() 
	{
		Boolean status = null;
		BrandingTemplateForm brandingTemplateForm = new BrandingTemplateForm();
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
		// empBrandTempDTO.setDescription(form.getDescription());
		// empBrandTempDTO.setEmployerId(form.getEmployerId());
		// empBrandTempDTO.setImageTemplatePath(form.getImageTemplatePath());
		// empBrandTempDTO.setLogoPath(form.getLogoPath());
		// empBrandTempDTO.setColor(form.getColor());
		// empBrandTempDTO.setCreatedDate(form.getCreatedDate());
		// empBrandTempDTO.setUpdatedDate(form.getUpdatedDate());

//		empBrandTempDTO.setTemplateName("Sample Template Name");
//		empBrandTempDTO.setEmployerId(26);
//		empBrandTempDTO.setImagePath("c://image1.jpg");
//		empBrandTempDTO.setLogoPath("c://logo.jpg");
//		empBrandTempDTO.setColor("#ff0000");
//		empBrandTempDTO.setCreatedDate(new Date());
//		empBrandTempDTO.setCompanyOverview("Sample overview");
//		empBrandTempDTO.setFacilityId(1);
//		empBrandTempDTO.setJpBrandTempId(1);
		
//		empBrandTempDTO.setUpdatedDate(null);
		
		ModelAndView model = new ModelAndView();
//		Write validation code here
//		registerValidation.validate(registerForm, result);
		
//		if(result.hasErrors()){
//			model.setViewName("empBrandTempCreate");
//			return model;
//		}
		
 		empBrandTempDTO.setTemplateName(brandingTemplateForm.getTemplateName());
 		
// 		int userId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
 		empBrandTempDTO.setEmployerId(26);
		empBrandTempDTO.setImagePath(brandingTemplateForm.getImageTemplatePath());
		empBrandTempDTO.setLogoPath(brandingTemplateForm.getLogoPath());
		empBrandTempDTO.setColor(brandingTemplateForm.getColor());
		empBrandTempDTO.setCreatedDate(new Date());
		empBrandTempDTO.setCompanyOverview(brandingTemplateForm.getCompanyOverview());
		empBrandTempDTO.setFacilityId(1);
		
//		empBrandTempDTO.setUpdatedDate(brandingTemplateForm.getUpdatedDate());

		
		status = empBrandTemp.createEmpBrandTemp(empBrandTempDTO);
		if (status) {
			return null;
		}
		model.addObject("brandingTemplateForm",brandingTemplateForm);
		model.setViewName("empBrandTempCreate");
		
//		return new ModelAndView("empBrandTempCreate");
		return model;
		

		
	}

	/**
	 * The method is called to view the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewEmpBrandTemp", method = RequestMethod.POST)
	public ModelAndView viewEmpBrandTemp(Map model) {
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
		empBrandTempDTO.setJpBrandTempId(1);
		empBrandTempDTO = empBrandTemp.viewEmpBrandTemp(empBrandTempDTO);
		return new ModelAndView("empBrandTempView");
	}

	/**
	 * The method is called to edit the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editEmpBrandTemp", method = RequestMethod.POST)
	public ModelAndView editEmpBrandTemp(Map model) {
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
		empBrandTempDTO.setTemplateName("Test Template Desc updated");
		empBrandTempDTO.setEmployerId(30);
		empBrandTempDTO.setJpBrandTempId(9);
		empBrandTempDTO.setImagePath("c://imageupd2.jpg");
		empBrandTempDTO.setLogoPath("c://logoupd2.jpg");
		empBrandTempDTO.setColor("#ffff00");
//		empBrandTempDTO.setUpdatedDate(new Date().toString());
		empBrandTempDTO = empBrandTemp.editEmpBrandTemp(empBrandTempDTO);
		return new ModelAndView("empBrandTempEdit");
	}

	/**
	 * The method is called to delete the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteEmpBrandTemp", method = RequestMethod.POST)
	public ModelAndView deleteEmpBrandTemp(Map model) {
		Boolean status = null;
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();

		empBrandTempDTO.setJpBrandTempId(11);
		status = empBrandTemp.deleteEmpBrandTemp(empBrandTempDTO);
		if (status) {
			return null;
		}
		return new ModelAndView("empBrandTempListPopup");
	}

	/**
	 * The method is called to close the empBrandTempList popup.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cancelEmpBrandTemp", method = RequestMethod.POST)
	public ModelAndView cancelEmpBrandTemp(Map model) {
//		return new ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
		return new ModelAndView("redirect:/employer/employerDashBoard.html");
	}
	
	/**
	 * The method is called to create a the empBrandTempList popup.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newEmpBrandTemp", method = RequestMethod.GET)
	public ModelAndView newBrandTemp(Map model) {
		return new ModelAndView("empBrandTemplate");
	}
	
}
