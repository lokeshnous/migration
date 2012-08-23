package com.advanceweb.afc.jb.employer.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
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
	
	@Autowired
	private TransformEmpoyerBrandTemp transformEmpoyerBrandTemp;

	@Autowired
	private BrandingTemplateValidation brandingTemplateValidation;
	
	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;
	
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
	@ResponseBody
	@RequestMapping(value = "/saveEmpBrandTemp", method = RequestMethod.POST)
	public ModelAndView createEmpBrandTemp( @ModelAttribute("brandingTemplateForm") BrandingTemplateForm brandingTemplateForm, 
			BindingResult result, HttpSession session) 
	{
		Boolean status = null;
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
		ModelAndView model = new ModelAndView();

//		Validate the form data
//		brandingTemplateValidation.validate(brandingTemplateForm, result);
		
		if(result.hasErrors()){
			model.setViewName("empBrandTemplate");
			return model;
		}

	
		
//		Transform form data to DTO
		empBrandTempDTO = transformEmpoyerBrandTemp.createEmpBrandTempDTO(brandingTemplateForm);
		
		
//		uploadImage();
		
		MultipartFile logoFile = brandingTemplateForm.getLogoFileData();
		MultipartFile mainImageFile = brandingTemplateForm.getMainImageFileData();
		
		if (null != logoFile && logoFile.getSize() > 0) {
//			if (file.getSize() > 100000) {
//				// return "/uploadfile";
//			} else {
				String logoFileName = logoFile.getOriginalFilename();
				String logoFilePath = baseDirectoryPathImageAndMedia;
				empBrandTempDTO.setFileServer(baseDirectoryPathImageAndMedia+"Logos\\");
				empBrandTempDTO.setLogoFileName(logoFileName);
				empBrandTempDTO.setLogoFilePath(logoFilePath);
				
				String mainImageFileName = logoFile.getOriginalFilename();
				String mainImageFilePath = baseDirectoryPathImageAndMedia+"MainImages\\";
//				empBrandTempDTO.setFileServer(baseDirectoryPathImageAndMedia);
				empBrandTempDTO.setMainImageFileName(mainImageFileName);
				empBrandTempDTO.setMainImageFilePath(mainImageFilePath);
				
//				empBrandTempDTO = resumeService.createResumeUpload(resumeDTO);

				File dest = new File(empBrandTempDTO.getLogoFilePath());
				try {
					logoFile.transferTo(dest);
					mainImageFile.transferTo(dest);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			}
		}

		model.addObject("brandingTemplateForm",brandingTemplateForm);
		model.setViewName("empBrandTempListPopup");
		
//		Call to service layer and DAO		
		status = empBrandTemp.createEmpBrandTemp(empBrandTempDTO);
		if (status) {
//			return null;
			return model;
		}


		
		
		
		
		return model;
		
	}
	
	
	public void uploadImage()
	{
//		ResumeDTO resumeDTO = transCreateResume
//				.transformCreateResumeToResumeDTO(createResume);
//		 
//		
//		String fileName = null, filePath = null;
//		try {
//			MultipartFile file = createResume.getFileData();
//
//			if (null != file && file.getSize() > 0) {
//				if (file.getSize() > 100000) {
//					// return "/uploadfile";
//				} else {
//					fileName = file.getOriginalFilename();
//					filePath = basedirectorypathUpload;
//					resumeDTO.setFileServer(basedirectorypathUpload);
//					resumeDTO.setFileName(fileName);
//					resumeDTO.setFilePath(filePath);
//					resumeDTO.setUserId((Integer) session
//							.getAttribute(MMJBCommonConstants.USER_ID));
//					resumeDTO = resumeService.createResumeUpload(resumeDTO);
//
//					File dest = new File(resumeDTO.getFilePath());
//					file.transferTo(dest);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		
	}

	/**
	 * The method is called to view the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/viewEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView viewEmpBrandTemp(Map model) {
//		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
//		empBrandTempDTO.setJpBrandTempId(1);
//		empBrandTempDTO = empBrandTemp.viewEmpBrandTemp(empBrandTempDTO);
//		return new ModelAndView("empBrandTempView");
//	}

	/**
	 * The method is called to edit the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/editEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView editEmpBrandTemp(Map model) {
//		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
//		empBrandTempDTO.setTemplateName("Test Template Desc updated");
//		empBrandTempDTO.setEmployerId(30);
//		empBrandTempDTO.setJpBrandTempId(9);
//		empBrandTempDTO.setImagePath("c://imageupd2.jpg");
//		empBrandTempDTO.setLogoPath("c://logoupd2.jpg");
//		empBrandTempDTO.setColor("#ffff00");
////		empBrandTempDTO.setUpdatedDate(new Date().toString());
//		empBrandTempDTO = empBrandTemp.editEmpBrandTemp(empBrandTempDTO);
//		return new ModelAndView("empBrandTempEdit");
//	}

	/**
	 * The method is called to delete the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/deleteEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView deleteEmpBrandTemp(Map model) {
//		Boolean status = null;
//		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
//
//		empBrandTempDTO.setJpBrandTempId(11);
//		status = empBrandTemp.deleteEmpBrandTemp(empBrandTempDTO);
//		if (status) {
//			return null;
//		}
//		return new ModelAndView("empBrandTempListPopup");
//	}

	/**
	 * The method is called to close the empBrandTempList popup.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/cancelEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView cancelEmpBrandTemp(Map model) {
////		return new ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
//		return new ModelAndView("redirect:/employer/employerDashBoard.html");
//	}
	
	/**
	 * The method is called to create a the empBrandTempList popup.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newEmpBrandTemp", method = RequestMethod.GET)
	public ModelAndView newBrandTemp(Map model) {
		model.put("brandingTemplateForm", new BrandingTemplateForm());
		return new ModelAndView("empBrandTemplate");
	}
	
}
