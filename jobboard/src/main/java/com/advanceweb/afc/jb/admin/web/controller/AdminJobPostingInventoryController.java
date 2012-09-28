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
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
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

	private static final Logger LOGGER = Logger
			.getLogger(AdminJobPostingInventoryController.class);

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/jobPostSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject jobSearchByComName(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform, BindingResult result) {
		JSONObject jsonObject = new JSONObject();
		boolean status = true;
		try {
			String empList = request.getParameter("empList");
			String id = request.getParameter("nsId");
			session.setAttribute("empList", empList);
			session.setAttribute("nsId", id);
			if (StringUtils.isEmpty(empList) && StringUtils.isEmpty(id)) {
				status = false;
				jsonObject.put("errMsg", "Please enter any one data to find");
				jsonObject.put("success", status);
				return jsonObject;
			}
			int nsId = 0;
			int empNsId = 0;
			if (empList.length() != 0) {
				EmpSearchDTO dto = adminService
						.validateCompName(empList);
				if (dto.getNsId() != 0) {
					empNsId = dto.getNsId();
					session.setAttribute(MMJBCommonConstants.NS_CUSTOMER_ID,
							empNsId);
					if (id.length() == 0 || (Integer.parseInt(id) == empNsId)) {
						jsonObject.put("success", status);
						return jsonObject;
					}
				} else {
					status = false;
					jsonObject.put("errMsg", "Please enter valid company name");
					if (id.length() != 0) {
						jsonObject.put("errMsg", "Please enter valid company name OR Net Suite Id");
						jsonObject.put("success", status);
						return jsonObject;
					}
				}
			}

			if (id.length() != 0) {
				try{
				nsId = Integer.parseInt(id);
				}catch(Exception ex){
					status = false;
					LOGGER.info("Excption occurred in jobSearchByComName Netsute Format : "+ex);
					jsonObject.put("errMsg", "Please enter valid Net Suite Id");
					jsonObject.put("success", status);
					return jsonObject;
				}
				boolean val = adminService.validateNetSuitId(nsId);
				if (val) {
					session.setAttribute(MMJBCommonConstants.NS_CUSTOMER_ID,
							nsId);
				} else {
					status = false;
					jsonObject.put("errMsg", "Please enter valid Net Suite Id");
					jsonObject.put("success", status);
					return jsonObject;
				}
			}

		} catch (Exception e) {
			LOGGER.info("Excption occurred in jobSearchByComName : "+e);
		}
		jsonObject.put("success", status);
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

			List<JobPostingInventoryDTO> inventiryDTO = adminService
					.getInventoryDetails(userId, facilityId);

			List<JobPostingInventoryDTO> jbPostList = new ArrayList<JobPostingInventoryDTO>();
			List<JobPostingInventoryDTO> jbSlotList = new ArrayList<JobPostingInventoryDTO>();
			JobPostingInventoryDTO postingInventoryDTO = new JobPostingInventoryDTO();
			String Duration = Integer.toString(MMJBCommonConstants.PLAN_DAYS)
					+ " " + MMJBCommonConstants.DAYS;
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
					dto.setInvDetailId(postingInventoryDTO.getInvDetailId());
					jbPostList.add(dto);
				} else if (postingInventoryDTO.getJbType().equalsIgnoreCase(
						MMJBCommonConstants.STANDARD_JOB_POSTING)
						&& postingInventoryDTO.getProductType().equals(
								MMJBCommonConstants.JOB_TYPE)) {
					dto.setAddon(MMJBCommonConstants.BASIC_JOB_TYPE);
					dto.setDuration(Duration);
					dto.setQuantity(postingInventoryDTO.getQuantity());
					dto.setInvDetailId(postingInventoryDTO.getInvDetailId());
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
					dto.setInvDetailId(postingInventoryDTO.getInvDetailId());
					jbSlotList.add(dto);
				} else if (postingInventoryDTO.getJbType().equalsIgnoreCase(
						MMJBCommonConstants.JOB_POSTING_SLOT)
						&& postingInventoryDTO.getProductType().equals(
								MMJBCommonConstants.JOB_TYPE)) {
					dto.setAddon(MMJBCommonConstants.BASIC_JOB_TYPE);
					dto.setDuration(Duration);
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
	JSONObject saveAvailJobQty(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("stringObjNew") String stringObjNew) {

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
		JSONObject saveStatusJson = new JSONObject();
		if (saveData) {
			saveStatusJson.put("success", "Data Updated Successfully");
		} else {
			saveStatusJson.put("failed", "Failed to update the data");
		}
		return saveStatusJson;
	}

}
