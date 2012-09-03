package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * 
 * @author deviprasadm
 * @Created: Aug 03, 2012
 * @Purpose: This class will act as a Controller for the Manage Access Permission
 */

@Controller
@RequestMapping("/employer")
public class ManageAccessPermissionController {

	@Autowired
	private JobPostService employerJobPost;
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@RequestMapping(value = "/manageAccessPermission")
	public ModelAndView showPostJob() {
		ModelAndView model = new ModelAndView();
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(1,
				"facility_admin");
		List<DropDownDTO> jbOwnerList = populateDropdownsService
				.populateJobOwnersDropdown(employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId(),
						employerInfoDTO.getRoleId());
		model.addObject("jobOwners", jbOwnerList);
		model.setViewName("manageAccessPermission");
		return model;
	}
	
	@RequestMapping(value = "/addNewJobOwner", method = RequestMethod.POST)
	public ModelAndView addNewJobOwner() {
		ModelAndView model = new ModelAndView();
		model.setViewName("addNewJobOwner");
		return model;
	}
	@RequestMapping(value = "/updateJobOwner", method = RequestMethod.POST)
	public ModelAndView updateJobOwner(ManageAccessPermissionForm manageAccessPermissionForm,@RequestParam("update") String update,@RequestParam("userId") int userId) {
		ModelAndView model = new ModelAndView();
		if(null != update && update.equals("delete")){
			System.out.println("Request For -" + update + "user Id :"+userId);
		}
		model.addObject("manageAccessPermissionForm", manageAccessPermissionForm);
		model.setViewName("forward:/employer/manageAccessPermission.html");
		return model;
	}
	
	@RequestMapping(value = "/addJobOwner", method = RequestMethod.POST)
	public ModelAndView addJobOwner(ManageAccessPermissionForm manageAccessPermissionForm) {
		ModelAndView model = new ModelAndView();
		model.addObject("manageAccessPermissionForm", manageAccessPermissionForm);
		model.setViewName("forward:/employer/manageAccessPermission.html");
		return model;
	}

}
