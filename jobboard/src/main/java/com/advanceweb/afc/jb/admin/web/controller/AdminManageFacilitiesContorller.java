package com.advanceweb.afc.jb.admin.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author muralikc
 * @version 1.0
 * @since 24th September, 2012
 *
 */
@Controller
@RequestMapping("/impersonationForFacility")
public class AdminManageFacilitiesContorller {
	
	private static final Logger LOGGER = Logger
			.getLogger("AdminManageFacilitiesContorller.class");

	@RequestMapping(value="/facilityToFacilityGroup")
	public ModelAndView editJobPostInventory(){
		ModelAndView model = new ModelAndView();
		model.setViewName("adminFacilityToFacGroup");
		return model;
		
	}

}
