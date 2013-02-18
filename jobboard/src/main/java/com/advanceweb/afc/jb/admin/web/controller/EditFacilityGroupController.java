package com.advanceweb.afc.jb.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.job.service.JobPostInventoryService;
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
	private JobPostInventoryService inventoryService;
	
	@Autowired
	private FacilityService facilityService;
	
	@Value("${adminEditFacilityErrMsg}")
	private String adminEditFacilityErrMsg;

	@Autowired
	private AdService adService;
	
	@RequestMapping(value = "/jobSearchBycompanyName")
	public ModelAndView jobSearchBycompanyName(
			@ModelAttribute("adminForm") AdminForm adminForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		int nsId = (Integer) session
				.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID);
		EmpSearchDTO dto1 = adminService
				.getUserIdAndFacilityId(nsId);
		session.setAttribute("nsId", nsId);
		session.setAttribute("empList", dto1.getCompanyName());
		List<EmpSearchDTO> dto = adminService.getEmpdataByNetSuiteId(nsId);
		
		boolean isHealthSys = false;
			if (dto.get(0).getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY_GROUP)) {
				isHealthSys = true;
				dto.get(0).setFacilityType(MMJBCommonConstants.FACILITY);
			}
		
		model.addObject("facilityList", dto);
		session.setAttribute("isHealthSys", isHealthSys);
		adminForm.setHealthSystem(isHealthSys);
		model.addObject("adminForm", adminForm);
		model.addObject("isHealthLable", "Health System");
		model.addObject("result","result");
		model.setViewName("manageFacilityGroup");
		return model;

	}

	@RequestMapping(value = "/saveEditedFacilty")
	public@ResponseBody
	JSONObject saveEditedFacilty(AdminForm adminForm,
			HttpSession session, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		ModelAndView model = new ModelAndView();
		boolean isHealthSys = false;
		
		populateAds(session, request, model);
		
		
		// validation for any purchase packages
		if (checkInventoryDetails((Integer) session
				.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID))) {
			jsonObject.put("failureMsg", adminEditFacilityErrMsg);
			return jsonObject;
		}
		
		if(session.getAttribute("isHealthSys")!=null){
			isHealthSys =(Boolean) session.getAttribute("isHealthSys");
		}
		if(isHealthSys == adminForm.isHealthSystem()){
			jsonObject.put("success", "success");
			return jsonObject;
		}
		EmpSearchDTO dto = new EmpSearchDTO();
		if(!adminForm.getCompName().isEmpty()){
			int nsId =(Integer) session.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID);
			adminForm.setNsId(String.valueOf(nsId));
		}
		try{
		dto = transformAdminImpersonation.convertFormToDTO(adminForm);
		adminService.saveEditFacilityGroup(dto);
		LOGGER.debug("Data saved successfully");
		}catch(Exception e){
			LOGGER.error("Exception while saving the edited facility from Admin functionality :"+e.getMessage());
		}
		model.setViewName("adminLogin");
		return jsonObject;

	}

	/**
	 * The method helps to check for inventory details for given user by net suite Id
	 * 
	 * @param netSuiteId
	 * @return - true if inventory detail present
	 *         - false if not
	 */
	private boolean checkInventoryDetails(int netSuiteId) {
		boolean isInventoryDtlPresent = false;
		try {
			List<EmpSearchDTO> empData = adminService
					.getEmpdataByNetSuiteId(netSuiteId);
			int userId = empData.get(0).getUserId();
			int facilityId = empData.get(0).getFacilityId();
			facilityId = facilityService.getParentFacility(facilityId)
					.getFacilityId();
			List<JobPostingInventoryDTO> inventiryDTOList = inventoryService
					.getInventoryDetails(userId, facilityId);
			if (!inventiryDTOList.isEmpty()) {
				isInventoryDtlPresent = true;
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return isInventoryDtlPresent;
	}

	/**
	 * The method helps to populate the ads for the page 
	 * 
	 * @param session
	 * @param request
	 * @param model
	 */
	private void populateAds(HttpSession session, HttpServletRequest request,
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
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);
			
			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService
					.getBanner(clientContext, size, position).getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception e) {
			LOGGER.error("Error occurred while getting the html content for Ads"
					, e);
		}
	}
}
