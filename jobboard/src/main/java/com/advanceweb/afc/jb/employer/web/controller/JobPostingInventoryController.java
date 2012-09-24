package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

	@Autowired
	private JobPostInventoryService inventoryService;

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
			@RequestParam(value = "page", required = false) String page,
			HttpSession session) {
		String status = null;
		ModelAndView model = new ModelAndView();

		if (page != null && page.equals(MMJBCommonConstants.POST_JOB_PAGE)) {
			inventoryForm.setPostJobPage("true");
		} else {
			status = "true";

		}

		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		List<JobPostingInventoryDTO> inventiryDTO = inventoryService
				.getInventoryDetails(userId, facilityId);

		List<JobPostingInventoryDTO> jbPostList = new ArrayList<JobPostingInventoryDTO>();
		List<JobPostingInventoryDTO> jbSlotList = new ArrayList<JobPostingInventoryDTO>();
		JobPostingInventoryDTO postingInventoryDTO = new JobPostingInventoryDTO();
		String Duration = Integer.toString(MMJBCommonConstants.PLAN_DAYS) + " "
				+ MMJBCommonConstants.DAYS;
		for (int i = 0; i < inventiryDTO.size(); i++) {
			postingInventoryDTO = inventiryDTO.get(i);
			JobPostingInventoryDTO dto = new JobPostingInventoryDTO();
			if (postingInventoryDTO.getJbType().equalsIgnoreCase(
					MMJBCommonConstants.STANDARD_JOB_POSTING)
					&& postingInventoryDTO.getProductType().equals(
							MMJBCommonConstants.JOB_TYPE_COMBO)) {
				dto.setAddon(MMJBCommonConstants.BASIC_JOB_TYPE + "+"
						+ postingInventoryDTO.getAddon());
				dto.setDuration(Duration);
				dto.setQuantity(postingInventoryDTO.getQuantity());
				dto.setAvailableQty(postingInventoryDTO.getAvailableQty());
				jbPostList.add(dto);
			} else if (postingInventoryDTO.getJbType().equalsIgnoreCase(
					MMJBCommonConstants.STANDARD_JOB_POSTING)
					&& postingInventoryDTO.getProductType().equals(
							MMJBCommonConstants.JOB_TYPE)) {
				dto.setAddon(MMJBCommonConstants.BASIC_JOB_TYPE);
				dto.setDuration(Duration);
				dto.setQuantity(postingInventoryDTO.getQuantity());
				dto.setAvailableQty(postingInventoryDTO.getAvailableQty());
				jbPostList.add(dto);
			} else if (postingInventoryDTO.getJbType().equalsIgnoreCase(
					MMJBCommonConstants.JOB_POSTING_SLOT)
					&& postingInventoryDTO.getProductType().equals(
							MMJBCommonConstants.JOB_TYPE_COMBO)) {
				dto.setAddon(MMJBCommonConstants.BASIC_JOB_TYPE + "+"
						+ postingInventoryDTO.getAddon());
				dto.setDuration(Duration);
				dto.setQuantity(postingInventoryDTO.getQuantity());
				dto.setAvailableQty(postingInventoryDTO.getAvailableQty());
				jbSlotList.add(dto);
			} else if (postingInventoryDTO.getJbType().equalsIgnoreCase(
					MMJBCommonConstants.JOB_POSTING_SLOT)
					&& postingInventoryDTO.getProductType().equals(
							MMJBCommonConstants.JOB_TYPE)) {
				dto.setAddon(MMJBCommonConstants.BASIC_JOB_TYPE);
				dto.setDuration(Duration);
				dto.setQuantity(postingInventoryDTO.getQuantity());
				dto.setAvailableQty(postingInventoryDTO.getAvailableQty());
				jbSlotList.add(dto);
			}
		}
		model.addObject("jbPostList", jbPostList);
		model.addObject("jbSlotList", jbSlotList);
		model.addObject("isAction", status);
		model.setViewName("jobPostingInventoryPopup");

		return model;
	}
}
