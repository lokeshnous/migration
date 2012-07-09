package com.advanceweb.afc.jb.webapp.web.controllers.resume;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.webapp.web.forms.resume.CreateResume;
import com.advanceweb.afc.jb.webapp.web.transformers.TransformCreateResume;
import com.advanceweb.afc.jb.webapp.web.transformers.TransformJobSeekerRegistration;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 9, 2012
 */

@Controller
public class ResumeController {

	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private TransformCreateResume transCreateResume;
	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/manageResume", method = RequestMethod.GET)
	public String getResumes(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {

		List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(1);

		for (ResumeDTO resumeDTO : resumeDTOList) {
			System.out.println(resumeDTO);
		}

		return "manageResume";
	}

	
	/**
	 * Called to create resume
	 * it Contains 
	 * 		1.Contact information
	 * 		2.Objective
	 * 		3.Work Experience
	 * 		4.Education
	 * 		5.Certifiation
	 * 		6.Skills
	 * 		7.Awards
	 * 		8.Memberships
	 * 		9.Other Details
	 * 		10.References	
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	public String saveResume(@ModelAttribute("saveResume")
		CreateResume createResume, BindingResult result,Model model){		
		
		ResumeDTO resumeDTO = new ResumeDTO();
		AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(createResume.getContactInfoForm());
		List<CertificationDTO> listCertDTO = transCreateResume.createCertificationDTO(createResume.getListCertForm());
		List<ReferenceDTO> listRefDTO = transCreateResume.createReferenceDTO(createResume.getListRefForm());
		List<WorkExpDTO> listWorkExpDTO = transCreateResume.createWorkExpDTO(createResume.getListWorkExpForm());
		List<EducationDTO> listEduDTO = transCreateResume.createEducationDTO(createResume.getListEduForm());
		List<LanguageDTO> listLangDTO = transCreateResume.createLanguageDTO(createResume.getListLangForm());
		resumeDTO = transCreateResume.createResumeDTO(resumeDTO, createResume);
		resumeDTO.setAddDTO(addDTO);
		resumeDTO.setListCertDTO(listCertDTO);
		resumeDTO.setListEduDTO(listEduDTO);
		resumeDTO.setListLangDTO(listLangDTO);
		resumeDTO.setListRefDTO(listRefDTO);
		resumeDTO.setListWorkExpDTO(listWorkExpDTO);
		
		return null;
		
	}

	
	/**
	 * This method is called to fetch the resume data to edit
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/editResume", method = RequestMethod.GET)
	public String editResume(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {

		ResumeDTO resumeDTO = resumeService.editResume(1);

		System.out.println(resumeDTO);

		return "manageResume";
	}
	
	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/deleteResume", method = RequestMethod.GET)
	public String deleteResume(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {

		boolean deleteStatus = resumeService.deleteResume(24);
		
		if(deleteStatus){
			System.out.println("Resume has been deleted succesfully ........");
		}

		return "manageResume";
	}

}
