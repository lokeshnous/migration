package com.advanceweb.afc.jb.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * @author muralikc
 * @version 1.0
 * @since 24th September, 2012
 * 
 */
@Controller
@RequestMapping("/impersonationForFacility")
public class EditFacilityGroupController extends AbstractController{

	private static final Logger LOGGER = Logger
			.getLogger("AdminManageFacilitiesContorller.class");

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private TransformAdminImpersonation transformAdminImpersonation;

	@Autowired
	private AdService adService;
	
	@RequestMapping(value = "/jobSearchBycompanyName")
	public ModelAndView jobSearchBycompanyName(
			@ModelAttribute("adminForm") AdminForm adminForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		int nsId = (Integer) session
				.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID);
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
			HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		boolean isHealthSys = false;
		
		getAds(session, request, model);
		
		if(session.getAttribute("isHealthSys")!=null){
			isHealthSys =(Boolean) session.getAttribute("isHealthSys");
		}
		if(isHealthSys == adminForm.isHealthSystem()){
			model.setViewName("adminLogin");
			return model;
		}
		EmpSearchDTO dto = new EmpSearchDTO();
		if(!adminForm.getCompName().isEmpty()){
			int nsId =(Integer) session.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID);
			adminForm.setNsId(String.valueOf(nsId));
		}
		dto = transformAdminImpersonation.convertFormToDTO(adminForm);
		adminService.saveEditFacilityGroup(dto);
		LOGGER.info("Data saved successfully");
		model.setViewName("adminLogin");
		return model;

	}

	/**
	 * This method displays the ads 
	 * 
	 * @param session
	 * @param request
	 * @param model
	 */
	private void getAds(HttpSession session, HttpServletRequest request,
			ModelAndView model) {
		// Add the Ads 
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.ADMIN_LOGIN);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject("adPageTop", bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.TOP_RIGHT;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject("adPageTopRight", bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.BOTTOM_RIGHT;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject("adPageBottomRight", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject("adPageBottom", bannerString);
		} catch (Exception e) {
			LOGGER.error("Error occurred while getting the html content for Ads"
					, e);
		}
	}
}
