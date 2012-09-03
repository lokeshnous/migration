package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * 
 * @author deviprasadm
 *
 */

@Controller
@RequestMapping("/employer")
public class ManageAccessPermissionController {

	@Autowired
	private JobPostService employerJobPost;
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@RequestMapping(value = "/manageAccessPermission", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/addNewJobOwner", method = RequestMethod.GET)
	public ModelAndView addNewJobOwner() {
		ModelAndView model = new ModelAndView();
		model.setViewName("addNewJobOwner");
		return model;
	}

}
