/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.job.service.JobPostInventoryService;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 23rd August, 2012
 */

@Controller
@RequestMapping("/inventory")
public class JobPostingInventoryController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobPostingInventoryController.class);
	
	
	/** The inventory service. */
	@Autowired
	private JobPostInventoryService inventoryService;
	
	/** The Constant PAGE. */
	private static final String PAGE = "page";
	
	/** The facility service. */
	@Autowired
	private FacilityService facilityService;

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer/jobInventory", method = RequestMethod.GET)
	public ModelAndView jobInventory(
			@ModelAttribute("alertForm") InventoryForm inventoryForm,
			BindingResult result,
			@RequestParam(value = PAGE, required = false) String page,
			HttpSession session) {
		
		ModelAndView model = new ModelAndView();
		try{
		// If Inventory page is from dashboard then we need to provide action
		// column
		
		int userId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		facilityId = facilityService.getParentFacility(facilityId).getFacilityId();
		List<JobPostingInventoryDTO> inventiryDTOList = inventoryService.getInventoryDetails(userId, facilityId);

		List<JobPostingInventoryDTO> jbPostList = new ArrayList<JobPostingInventoryDTO>();
		List<JobPostingInventoryDTO> jbSlotList = new ArrayList<JobPostingInventoryDTO>();
		JobPostingInventoryDTO dto = null;
		
		String duration = Integer.toString(MMJBCommonConstants.PLAN_DAYS) + " "+ MMJBCommonConstants.DAYS;
		
		for (JobPostingInventoryDTO postingInventoryDTO : inventiryDTOList) {
			
			dto = new JobPostingInventoryDTO();
			
			if (MMJBCommonConstants.STANDARD_JOB_POSTING.equalsIgnoreCase(postingInventoryDTO.getJbType())) {
				dto.setAddon(postingInventoryDTO.getAddon());
				dto.setDuration(duration);
				dto.setQuantity(postingInventoryDTO.getQuantity());
				dto.setAvailableQty(postingInventoryDTO.getAvailableQty());
				dto.setInvDetailId(postingInventoryDTO.getInvDetailId());
				jbPostList.add(dto);
			}else if (MMJBCommonConstants.JOB_POSTING_SLOT.equalsIgnoreCase(postingInventoryDTO.getJbType())) {
				dto.setAddon(postingInventoryDTO.getAddon());
				dto.setDuration(duration);
				dto.setQuantity(postingInventoryDTO.getQuantity());
				dto.setAvailableQty(postingInventoryDTO.getAvailableQty());
				dto.setInvDetailId(postingInventoryDTO.getInvDetailId());
				jbSlotList.add(dto);
			}
		}
		model.addObject("jbPostList", jbPostList);
		model.addObject("jbSlotList", jbSlotList);
		model.addObject("pageName", page);
		model.setViewName("jobPostingInventoryPopup");
		}catch(Exception e){
			LOGGER.error(e);
		}
		return model;
	}
	
	/**
	 * This method to get resume inventory details
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer/resumeInventory", method = RequestMethod.GET)
	public ModelAndView resumeInventory(
			@ModelAttribute("alertForm") InventoryForm inventoryForm,
			BindingResult result,HttpSession session) {

		ModelAndView model = new ModelAndView();
		try {
			// If Inventory page is from dashboard then we need to provide
			// action column

			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			facilityId = facilityService.getParentFacility(facilityId)
					.getFacilityId();
			List<JobPostingInventoryDTO> inventiryDTOList = inventoryService
					.getResumeInventoryDetails(userId, facilityId);

			List<JobPostingInventoryDTO> inventoryList = new ArrayList<JobPostingInventoryDTO>();
			JobPostingInventoryDTO dto = null;

			for (JobPostingInventoryDTO postingInventoryDTO : inventiryDTOList) {

				dto = new JobPostingInventoryDTO();
				dto.setProductType(postingInventoryDTO.getProductType());
				dto.setStartDt(postingInventoryDTO.getStartDt());
				dto.setEndtDt(postingInventoryDTO.getEndtDt());
				inventoryList.add(dto);
			}
			model.addObject("jbPostList", inventoryList);
			model.setViewName("resumeInventoryPopup");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return model;
	}
}
