package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 23rd August, 2012
 */

@Controller
@RequestMapping("/inventory")
public class JobPostingInventoryController {

	/**
	 * This method to login in to the forgot your password page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/jobInventory", method = RequestMethod.GET)
	public ModelAndView jobInventory(Map<String, InventoryForm> model,
			@RequestParam(value = "page", required = false) String page,
			Model modelconstants, HttpSession session) {
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		
		return new ModelAndView("jobPostingInventoryPopup");
	}
}
