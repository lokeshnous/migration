package com.advanceweb.afc.jb.admin.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.afc.jb.employer.web.controller.InventoryForm;
import com.advanceweb.afc.jb.employer.web.controller.JobPostForm;

/**
 * 
 * @author MuraliKrishna
 * @version 1.0
 * @since 23rd August, 2012
 */

@Controller
@RequestMapping("/admininventory")
public class AdminJobPostingInventoryController {

	private static final String SUCCESS = "success";

	private static final String ERR_MSG = "errMsg";

	private static final Logger LOGGER = Logger
			.getLogger(AdminJobPostingInventoryController.class);

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;

	@RequestMapping(value = "/jobPostSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject jobSearchByComName(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform, BindingResult result) {
		JSONObject jsonObject = new JSONObject();
		boolean status = true;
		try {
			String empList = request.getParameter("empList");
			String netSuiteId = request.getParameter("nsId");
			session.setAttribute("empList", empList);
			session.setAttribute("nsId", netSuiteId);
			if (StringUtils.isEmpty(empList) && StringUtils.isEmpty(netSuiteId)) {
				status = false;
				jsonObject.put(ERR_MSG, "Please enter Company name or NetSuite ID");
				jsonObject.put(SUCCESS, status);
				return jsonObject;
			}
			int nsId = 0;
			if (empList.length() != 0) {
				return validate(empList,netSuiteId,session);
			}
			if (netSuiteId.length() != 0) {
				try{
				nsId = Integer.parseInt(netSuiteId);
				}catch(Exception ex){
					status = false;
					LOGGER.info("Excption occurred in jobSearchByComName Netsute Format : "+ex);
					jsonObject.put(ERR_MSG, "Please enter a valid Net Suite Id");
					jsonObject.put(SUCCESS, status);
					return jsonObject;
				}
				boolean val = adminService.validateNetSuitId(nsId);
				if (val) {
					session.setAttribute(MMJBCommonConstants.NS_CUSTOMER_ID,
							nsId);
				} else {
					status = false;
					jsonObject.put(ERR_MSG, "Please enter a valid Net Suite Id");
					jsonObject.put(SUCCESS, status);
					return jsonObject;
				}
			}
		} catch (Exception e) {
			LOGGER.info("Excption occurred in jobSearchByComName : "+e);
		}
		jsonObject.put(SUCCESS, status);
		return jsonObject;
	}
	
	/**
	 * @param empList
	 * @param netSuiteId
	 * @param session
	 * @return
	 */
	public JSONObject validate(String empList,String netSuiteId,HttpSession session){
		JSONObject jsonObject = new JSONObject();
		boolean status = true;
		int empNsId = 0;
		EmpSearchDTO dto = adminService
				.validateCompName(empList);
		if (dto.getNsId() == 0) {
			status = false;
			jsonObject.put(ERR_MSG, "Please enter valid company name");
			if (netSuiteId.length() != 0) {
				jsonObject.put(ERR_MSG, "Please enter valid company name OR Net Suite Id");
				jsonObject.put(SUCCESS, status);
				return jsonObject;
			}
		} else {
			empNsId = dto.getNsId();
			session.setAttribute(MMJBCommonConstants.NS_CUSTOMER_ID,
					empNsId);
			if (netSuiteId.length() == 0 || (Integer.parseInt(netSuiteId) == empNsId)) {
				jsonObject.put(SUCCESS, status);
				return jsonObject;
			}
		}
		return jsonObject;
	
	}
	/**
	 * This method to get job posting inventory details
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer1/jobInventory1", method = RequestMethod.GET)
	public ModelAndView jobInventory(
			@ModelAttribute("alertForm") InventoryForm inventoryForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (session.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID) != null) {
			int nsId = (Integer) session
					.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID);
			EmpSearchDTO dto1 = adminService
					.getUserIdAndFacilityId(nsId);
			int userId = dto1.getUserId();
			int facilityId = dto1.getFacilityId();
			List<JobPostingInventoryDTO> inventiryDTOList = adminService
					.getInventoryDetails(userId, facilityId);
			List<JobPostingInventoryDTO> jbPostList = new ArrayList<JobPostingInventoryDTO>();
			List<JobPostingInventoryDTO> jbSlotList = new ArrayList<JobPostingInventoryDTO>();
			
			JobPostingInventoryDTO dto = null;
			
			String duration = Integer.toString(MMJBCommonConstants.PLAN_DAYS)
					+ " " + MMJBCommonConstants.DAYS;
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
		}
		model.setViewName("adminEditJobPostInventory");
		return model;
	}

	@RequestMapping(value = "/saveAvailJobQty", method = RequestMethod.GET)
	public @ResponseBody
	boolean saveAvailJobQty(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("stringObjNew") String stringObjNew) {
		boolean saveStatusJson = false;
		// Splitting the string
		StringTokenizer stringNew = new StringTokenizer(stringObjNew, ";");
		List<JobPostingInventoryDTO> jobPostDTOs = new ArrayList<JobPostingInventoryDTO>();

		while (stringNew.hasMoreElements()) {
			JobPostingInventoryDTO jobPostInvDto = new JobPostingInventoryDTO();
			String stringObject = (String) stringNew.nextElement();
			StringTokenizer stringAlter = new StringTokenizer(stringObject, "=");
			int invId = Integer.parseInt((String) stringAlter.nextElement());
			int availQty = Integer.parseInt((String) stringAlter.nextElement());

			jobPostInvDto.setInvDetailId(invId);
			jobPostInvDto.setAvailableQty(availQty);
			jobPostDTOs.add(jobPostInvDto);
		}
		// update the data in DB
		boolean saveData = adminService.saveModifiedData(jobPostDTOs);
//		JSONObject saveStatusJson = new JSONObject();
		if (saveData) {
			saveStatusJson = true;
		}
		return saveStatusJson;
	}
	
	@RequestMapping(value = "/addJobPosting", method = RequestMethod.GET)
	public ModelAndView addJobPosting(@ModelAttribute("inventoryForm") InventoryForm inventoryForm,HttpSession session) {
				
			ModelAndView model = new ModelAndView();
			
			int nsCustomerID = 0;
			List<DropDownDTO> jobPostingList = null;
			if(null != session.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID)){
				jobPostingList = adminService.listJobPostings();
				
				nsCustomerID = Integer.parseInt(String.valueOf(session.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID)));
				
				// Get the list of valid packages purchased by customers from NetSuite
				List<String> purchasedPackages = manageFeaturedEmployerProfile
						.getNSCustomerPackages(nsCustomerID);
				
				List<DropDownDTO> removeJbPostingList = new ArrayList<DropDownDTO>();
				//remove the packages which are purchased offline & expired
				for(DropDownDTO dropDownDTO : jobPostingList){
					if(!purchasedPackages.contains(dropDownDTO.getNetSuiteId())){
						removeJbPostingList.add(dropDownDTO);
					}
				}
				
				jobPostingList.removeAll(removeJbPostingList);
				model.addObject("jobPostingList", jobPostingList);
			}
			
			model.setViewName("adminAddJobPosting");
			
			return model;
	}
	
	@RequestMapping(value = "/updateJobPostInventory", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject updateJobPostInventory(
			@ModelAttribute("inventoryForm") InventoryForm inventoryForm,
			HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String nsCustomerId = String.valueOf(session.getAttribute(MMJBCommonConstants.NS_CUSTOMER_ID));
		boolean status = false;
		if (null != nsCustomerId && !nsCustomerId.isEmpty()) {
			EmpSearchDTO empSearchDTO = adminService.getUserIdAndFacilityId(Integer.parseInt(nsCustomerId));
			status = adminService.updateJobPostInventory(empSearchDTO.getFacilityId(),
					inventoryForm.getJbTypeId(), inventoryForm.getQuantity());
		}

		if(status){
			jsonObject.put("status", SUCCESS);
		}
		else{
			jsonObject.put("status", "Failed to update the inventory for this empoyer");
		}
		return jsonObject;
	}

}
