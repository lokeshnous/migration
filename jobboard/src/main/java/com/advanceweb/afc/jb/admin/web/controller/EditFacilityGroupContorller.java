package com.advanceweb.afc.jb.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@Autowired
	TransformAdminImpersonation transformAdminImpersonation;

	@RequestMapping(value = "/jobSearchBycompanyName")
	public ModelAndView jobSearchBycompanyName(
			@ModelAttribute("adminForm") AdminForm adminForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		int nsId = (Integer) session
				.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID);
		EmpSearchDTO dto1 = adminService.getUserIdAndFacilityId(nsId);
		int usId = dto1.getUserId();
		List<EmpSearchDTO> dto = adminService.getEmpdataByNetSuiteId(nsId);
		model.addObject("facilityList", dto);
		boolean isHealthSys = false;
		for (EmpSearchDTO empSearchDTO : dto) {
			if (empSearchDTO.getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY_GROUP)) {
				isHealthSys = true;
			}
		}
		session.setAttribute("isHealthSys", isHealthSys);
		adminForm.setHealthSystem(isHealthSys);
		model.addObject("adminForm", adminForm);
		model.addObject("isHealthLable", "Health System");
		model.addObject("facilityList", dto);
		model.setViewName("manageFacilityGroup");
		return model;

	}

	@RequestMapping(value = "/saveEditedFacilty")
	public ModelAndView saveEditedFacilty(
			@ModelAttribute("adminForm") AdminForm adminForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		boolean isHealthSys =(Boolean) session.getAttribute("isHealthSys");
		if(isHealthSys == adminForm.isHealthSystem()){
			model.setViewName("adminLogin");
			return model;
		}
		EmpSearchDTO dto = new EmpSearchDTO();
		dto = transformAdminImpersonation.convertFormToDTO(adminForm);
		boolean val = adminService.saveEditFacilityGroup(dto);
		model.setViewName("adminLogin");
		return model;

	}
}
