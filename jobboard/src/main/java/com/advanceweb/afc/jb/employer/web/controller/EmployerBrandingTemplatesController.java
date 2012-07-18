package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.EmployerBrandingTemplatesDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.employer.service.EmployerBrandingTemplates;

/**
 * <code>EmployerBrandingTemplateController</code>This controller 
 * belongs to manage job posting Branding Template
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Controller
@RequestMapping("/brandingTemplates")
@SuppressWarnings("rawtypes")
public class EmployerBrandingTemplatesController {

	@Autowired
	private EmployerBrandingTemplates employerBrandingTemplates;

	/**
	 * The method is called to fetch the job posting Branding Templates
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/manageJobPostingBrandingTemplates", method = RequestMethod.GET)
	public ModelAndView fetchJobPostingBrandingTemplates(Map model) {

		MerUserDTO merUserDTO = new MerUserDTO();
		merUserDTO.setUserId(13115);
		List<EmployerBrandingTemplatesDTO> employerBrandingTemplatesDTOList = employerBrandingTemplates
				.fetchJobPostingBrandingTemplates(merUserDTO);

//		for (EmployerBrandingTemplatesDTO templatesDTO : employerBrandingTemplatesDTOList) {
//			System.out.println(templatesDTO);
//		}
		return new ModelAndView("jobPostingBrandingTemplatesList");
	}
	
	/**
	 * The method is called to create the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	public ModelAndView createJobPostingBrandingTemplate(
			@Valid EmployerBrandingTemplatesForm form) {
		Boolean status = null;
		EmployerBrandingTemplatesDTO brandingTemplatesDTO = new EmployerBrandingTemplatesDTO();
		// brandingTemplatesDTO.setDescription(form.getDescription());
		// brandingTemplatesDTO.setImageTemplatePath(form.getImageTemplatePath());
		// brandingTemplatesDTO.setLogoPath(form.getLogoPath());
		// brandingTemplatesDTO.setColor(form.getColor());
		// brandingTemplatesDTO.setCreatedDate(form.getCreatedDate());
		// brandingTemplatesDTO.setUpdatedDate(form.getUpdatedDate());

		brandingTemplatesDTO.setDescription("Test Template Desc");
		brandingTemplatesDTO.setImagePath("c://image1.jpg");
		brandingTemplatesDTO.setLogoPath("c://logo.jpg");
		brandingTemplatesDTO.setColor("#ff0000");
		brandingTemplatesDTO.setCreatedDate(new Date());
		brandingTemplatesDTO.setUpdatedDate(null);
		status = employerBrandingTemplates
				.createJobPostingBrandingTemplates(brandingTemplatesDTO);
		if(status){
			return null;
		}
		return new ModelAndView("jobPostingBrandingTemplateCreate");
	}

	/**
	 * The method is called to view the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	public ModelAndView viewJobPostingBrandingTemplate(Map model) {
		EmployerBrandingTemplatesDTO employerBrandingTemplatesDTO = new EmployerBrandingTemplatesDTO();
		employerBrandingTemplatesDTO.setJpBrandTempId(1);
		employerBrandingTemplatesDTO = employerBrandingTemplates
				.viewJobPostingBrandingTemplates(employerBrandingTemplatesDTO);
		return new ModelAndView("jobPostingBrandingTemplateView");
	}

	/**
	 * The method is called to edit the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	public ModelAndView editJobPostingBrandingTemplate(Map model) {
		EmployerBrandingTemplatesDTO employerBrandingTemplatesDTO = new EmployerBrandingTemplatesDTO();
		employerBrandingTemplatesDTO.setJpBrandTempId(1);
		employerBrandingTemplatesDTO.setUpdatedDate(new Date());
		employerBrandingTemplatesDTO = employerBrandingTemplates
				.editJobPostingBrandingTemplates(employerBrandingTemplatesDTO);
		return new ModelAndView("jobPostingBrandingTemplateEdit");
	}

	/**
	 * The method is called to delete the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	public ModelAndView deleteJobPostingBrandingTemplate(Map model) {
		Boolean status = null;
		EmployerBrandingTemplatesDTO brandingTemplatesDTO = new EmployerBrandingTemplatesDTO();

		brandingTemplatesDTO.setJpBrandTempId(1);
		status = employerBrandingTemplates
				.deleteJobPostingBrandingTemplates(brandingTemplatesDTO);
		if(status){
			return null;
		}
		return new ModelAndView("jobPostingBrandingTemplateDelete");
	}
}
