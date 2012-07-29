package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will act as a Controller for the Post New Job 
 */

@Controller
@RequestMapping("/postjob")
public class JobPostController {
	@Autowired
	private JobPostService employerJobPost;
	
	@Autowired
	private TransformJobPost transformJobPost;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	
	//@RequestParam("userId") int userId ,
	
	@RequestMapping(value="/postnewjob",method = RequestMethod.GET)
	public ModelAndView showPostJob( Map model) {

		JobPostForm employerJobPostForm=new JobPostForm();
		EmployerInfoDTO employerInfoDTO=employerJobPost.getEmployerInfo(1);
		List<StateDTO> stateList=employerJobPost.getStateList();
		List<EmploymentTypeDTO> empTypeList=populateDropdownsService.getEmploymentTypeList();
		model.put("stateList",stateList);
		model.put("empTypeList",empTypeList);
		employerJobPostForm.setCustomerNo(employerInfoDTO.getCustomerNo());
		employerJobPostForm.setCompanyName(employerInfoDTO.getCustomerName());
		model.put("employerJobPostForm",employerJobPostForm);
		
		return new ModelAndView("postnewjob");
	}
	
	
	@RequestMapping(value="/savenewjob",method = RequestMethod.GET)
	public ModelAndView savePostJob( @Valid JobPostForm form,BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("postnewjob");
		}
		
		
		JobPostDTO dto=new JobPostDTO();
		dto=transformJobPost.jobPostFormToJobPostDTO(form);
		employerJobPost.savePostJob(dto);
		return new ModelAndView("postnewjob");
	}	
	
}
