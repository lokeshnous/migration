package com.advanceweb.afc.jb.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * @author muralikc
 * @version 1.0
 * @since 24th September, 2012
 *
 */
@Controller
@RequestMapping("/impersonationForFacility")
public class EditFacilityGroupContorller {
	
	private static final Logger LOGGER = Logger
			.getLogger("AdminManageFacilitiesContorller.class");
	
	@Autowired
	AdminService adminService;

	@RequestMapping(value="/facilityToFacilityGroup")
	public ModelAndView editJobPostInventory(){
		ModelAndView model = new ModelAndView();
		model.setViewName("adminFacilityToFacGroup");
		return model;
	}
	
	@RequestMapping(value="/jobSearchBycompanyName")
	public ModelAndView jobSearchBycompanyName(HttpSession session){
		int nsId = (Integer) session.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID);
		EmpSearchDTO dto1 = adminService
				.getUserIdAndFacilityId(nsId);
		int usId = dto1.getUserId();
		List<EmpSearchDTO> dto =adminService.getEmpdataByNetSuiteId(nsId);
		System.out.println(nsId +"***" +usId);
		return null;
		
	}
	@RequestMapping(value="/saveEditedFacilty")
	public ModelAndView saveEditedFacilty(){
		return null;
		
	}
}
