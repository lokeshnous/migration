/**
 * 
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ManageFacilityDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.job.service.ManageFacilityService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @author deviprasadm
 *@Created: Sep 22, 2012
 * @Purpose: This class will act as a Controller for the Manage Access Facility
 */
@Controller
@RequestMapping("/facility")
public class ManageFacilityController {

	private static final Logger LOGGER = Logger
			.getLogger(ManageFacilityController.class);
	@Autowired
	private ManageFacilityService facilityService;
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	@Autowired
	private JobPostService employerJobPost;

	@RequestMapping(value = "/addFacility")
	public ModelAndView addfacilityDetails(ManageFacilityForm facilityForm,
			HttpSession session) {
		LOGGER.info("Facility Details List");
		ModelAndView model = new ModelAndView();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(
				(Integer) session.getAttribute("userId"), "facility_admin");

		List<DropDownDTO> templateList = populateDropdownsService
				.populateBrandingTemplateDropdown(
						employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId());
		model.addObject("manageFacilityForm", facilityForm);
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

		model.addObject("templateList", templateList);
		model.setViewName("addNewFacility");

		return model;
	}

	@RequestMapping(value = "/updateFacilityDetail")
	public ModelAndView updateFacilityDetails(ManageFacilityForm facilityForm,
			HttpSession session) {
		LOGGER.info("Facility Details List"
				+ (Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID) + "uid"
				+ (Integer) session.getAttribute(MMJBCommonConstants.USER_ID));
		ModelAndView model = new ModelAndView();
		ManageFacilityDTO facilityDTOs = new ManageFacilityDTO();
		List<FacilityDTO> facilityDTOList = new ArrayList<FacilityDTO>();
		try {
			facilityDTOs = facilityService.getFacilityList((Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID), true);
			facilityDTOList = facilityService
					.getFacilityListByGroup((Integer) session
							.getAttribute(MMJBCommonConstants.FACILITY_ID));
			if (null != facilityDTOs) {
				facilityForm.setFacilityId(facilityDTOs.getFacilityId());
				facilityForm.setFacilityName(facilityDTOs.getFacilityName());
				facilityForm.setFacilityType(facilityDTOs.getFacilityType());
			}
			if (null != facilityDTOList && !facilityDTOList.isEmpty()) {
				facilityForm.setFacilityDTOList(facilityDTOList);
			}
		} catch (JobBoardException jbex) {
			LOGGER.error("Error occured while retriving the Facility List",
					jbex);
		}

		model.addObject("facilityList", facilityDTOList);
		model.addObject("manageFacilityForm", facilityForm);
		model.setViewName("updateFacility");

		return model;
	}

	@RequestMapping(value = "/saveNewFacility", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject saveNewFacility(HttpSession session,
			ManageFacilityForm facilityFormP, HttpServletRequest request) {
		LOGGER.info("Save facility : Process to save facility detail Starts !");
		ManageFacilityForm facilityForm = facilityFormP;
		JSONObject message = new JSONObject();

		ManageFacilityDTO facilityDTO = transformFacilityFromtoFacilityDTO(facilityForm);
		/*
		 * try { facilityService.createFacility(facilityDTO, (Integer) session
		 * .getAttribute(MMJBCommonConstants.FACILITY_ID));
		 * message.put("success", "Added successfully"); } catch
		 * (JobBoardServiceException ex) {
		 * LOGGER.error("Error occured while saving the Facility ", ex);
		 * message.put("failure", "Error occured While saving data"); }
		 */

		return message;

	}

	@RequestMapping(value = "/deleteFacility", method = RequestMethod.POST)
	public @ResponseBody
	String deleteFacility(HttpSession session,
			ManageFacilityForm facilityFormP, HttpServletRequest request,
			@RequestParam("facilityId") int facilityId) {
		LOGGER.info("Delete facility : Process to save facility detail Starts !");

		try {
			facilityService.deleteFacility(facilityId);

		} catch (JobBoardServiceException ex) {
			LOGGER.error("Error occured while saving the Facility ", ex);
			return "failure";
		}

		return "";

	}

	@RequestMapping(value = "/editFacility", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView editFacility(HttpSession session,
			ManageFacilityForm facilityFormP, HttpServletRequest request,
			@RequestParam("facilityId") int facilityId) {
		LOGGER.info("Edit facility : Process to Edit facility detail Starts !");
		ManageFacilityDTO facilityDTOs = new ManageFacilityDTO();
		ManageFacilityForm facilityForm = facilityFormP;

		try {
			facilityDTOs = facilityService.getFacilityList(facilityId, false);

			if (null != facilityDTOs) {
				facilityForm.setFacilityId(facilityDTOs.getFacilityId());
				facilityForm.setFacilityName(facilityDTOs.getFacilityName());
				facilityForm.setFacilityType(facilityDTOs.getFacilityType());
				facilityForm.setFacilityCity(facilityDTOs.getFacilityCity());
				facilityForm.setFacilityCountry(facilityDTOs
						.getFacilityCountry());
				facilityForm.setFacilityState(facilityDTOs.getFacilityState());
				facilityForm
						.setFacilityStreet(facilityDTOs.getFacilityStreet());
				facilityForm.setZipCode(facilityDTOs.getZipCode());
				facilityForm.setPhoneNumber(facilityDTOs.getPhoneNumber());
				facilityForm.setTemplateId(facilityDTOs.getTemplateId());
			}

		} catch (JobBoardException jbex) {
			LOGGER.error("Error occured while retriving the Facility List",
					jbex);
		}

		ModelAndView model = new ModelAndView();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(
				(Integer) session.getAttribute("userId"), "facility_admin");

		List<DropDownDTO> templateList = populateDropdownsService
				.populateBrandingTemplateDropdown(
						employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId());
		model.addObject("manageFacilityForm", facilityForm);
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

		model.addObject("templateList", templateList);
		model.setViewName("addNewFacility");

		return model;

	}

	public ManageFacilityDTO transformFacilityFromtoFacilityDTO(
			ManageFacilityForm facilityForm) {
		ManageFacilityDTO manageFacilityDTO = new ManageFacilityDTO();
		if (facilityForm.getFacilityId() > 0) {
			manageFacilityDTO.setFacilityId(facilityForm.getFacilityId());
		}
		manageFacilityDTO.setFacilityName(facilityForm.getFacilityName());
		manageFacilityDTO.setFacilityStreet(facilityForm.getFacilityStreet());
		manageFacilityDTO.setFacilityCity(facilityForm.getFacilityCity());
		manageFacilityDTO.setFacilityCountry(facilityForm.getFacilityCountry());
		manageFacilityDTO.setFacilityState(facilityForm.getFacilityState());
		manageFacilityDTO.setPhoneNumber(facilityForm.getPhoneNumber());
		manageFacilityDTO.setZipCode(facilityForm.getZipCode());
		manageFacilityDTO.setTemplateId(facilityForm.getTemplateId());
		return manageFacilityDTO;

	}
}
