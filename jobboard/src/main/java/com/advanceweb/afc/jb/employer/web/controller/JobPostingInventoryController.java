package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.JobPostInventoryService;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 23rd August, 2012
 */

@Controller
@RequestMapping("/inventory")
public class JobPostingInventoryController {

	private static final Logger LOGGER = Logger
			.getLogger(JobPostingInventoryController.class);

	@Autowired
	private JobPostInventoryService inventoryService;

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/employer/jobInventory", method = RequestMethod.GET)
	public ModelAndView jobInventory(
			@ModelAttribute("alertForm") InventoryForm inventoryForm,
			BindingResult result, HttpSession session) {

		ModelAndView model = new ModelAndView();

		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		List<JobPostingInventoryDTO> inventiryDTO = inventoryService
				.getInventoryDetails(userId, facilityId);

		/*
		 * List<JobPostingInventoryDTO> jobTypeList =
		 * (List<JobPostingInventoryDTO>) inventiryDTO .get(0);
		 * List<JobPostingInventoryDTO> inventoryList =
		 * (List<JobPostingInventoryDTO>) inventiryDTO .get(1);
		 */

		
		List<JobPostingInventoryDTO> jobTypeList = new ArrayList<JobPostingInventoryDTO>();
		//Standard Job Posting
		List<JobPostingInventoryDTO> jbPostList = new ArrayList<JobPostingInventoryDTO>();
		//Job Posting Slot
		List<JobPostingInventoryDTO> jbSlotList = new ArrayList<JobPostingInventoryDTO>();
		
		model.addObject("jobTypeList", jobTypeList);
		model.addObject("jbPostList", jbPostList);
		model.addObject("jbSlotList", jbSlotList);
		model.setViewName("jobPostingInventoryPopup");

		return model;
	}
}
