package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * @author Prince
 * @version 1.0
 * @since 27th August, 2012
 */

@Controller
@RequestMapping("/employer")
public class EmployerDashBoardController {

	private static final Logger LOGGER = Logger
			.getLogger(EmployerDashBoardController.class);

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@RequestMapping("/employerDashBoard")
	public ModelAndView displayDashBoard(
			@ModelAttribute("employerDashBoardForm") MetricsForm employerDashBoardForm,
			HttpSession session) {
		session.removeAttribute("jbPostTotalList");
		String enableAccess = "true";
		String enablePostEditAccess = "true";
		ModelAndView model = new ModelAndView();
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		EmployerInfoDTO roleList = facilityService.facilityDetails(userId);
		if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.FULL_ACCESS)) {
			enableAccess = "false";
			model.addObject("enableAccess", enableAccess);
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.MANAGEEDITACCESS)) {
			enablePostEditAccess = "false";
			model.addObject("enablePostEditAccess", enablePostEditAccess);
		}
		model.addObject("enableAccess", enableAccess);
		model.addObject("enablePostEditAccess", enablePostEditAccess);
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();

		// Get All facilities
		List<DropDownDTO> downDTOs = new ArrayList<DropDownDTO>();
		try {
			downDTOs = facilityService.getFacilityGroup(facilityId);
		} catch (JobBoardException e) {
			LOGGER.info("Error occurred while getting data for metrics" + e);
		}

		// getting the metrics details
		jbPostTotalList = getMetricsDetails(facilityId);

		model.addObject("downDTOs", downDTOs);
		model.addObject("jbPostTotalList", jbPostTotalList);
		session.setAttribute("jbPostTotalList", jbPostTotalList);
		model.addObject("employerDashBoardForm", employerDashBoardForm);
		model.setViewName("employerDashboard");
		return model;
	}

	/**
	 * This method is used to display the metrics details for selected employer
	 * in the drop down
	 * 
	 * @param employerDashBoardForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewEmployerMetrics", method = RequestMethod.GET)
	public @ResponseBody
	void viewEmployerMetrics(
			@ModelAttribute("employerDashBoardForm") MetricsForm employerDashBoardForm,
			BindingResult result, HttpSession session,
			@RequestParam("selEmployerId") int selEmployerId) {
		session.removeAttribute("jbPostTotalList");
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		// getting the metrics details
		jbPostTotalList = getMetricsDetails(selEmployerId);
		session.setAttribute("jbPostTotalList", jbPostTotalList);

	}

	/**
	 * Get the metric details page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/metricsDetails")
	public ModelAndView getMetricsDetails(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("metricsDetails");
		return modelAndView;
	}

	/**
	 * This method is to get the metrics details for facility
	 * 
	 * @param facilityId
	 * @return
	 */
	private List<MetricsDTO> getMetricsDetails(int facilityId) {
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		MetricsDTO metricsDTO = new MetricsDTO();
		// Get the job post details of logged in employer
		List<MetricsDTO> metricsDTOs = facilityService
				.getJobPostTotal(facilityId);

		// Getting metrics values from look up table
		List<DropDownDTO> metricsList = populateDropdownsService
				.populateDropdown("Metrics");

		// jbPostTotalList will be having job post total details for metrics
		int views = 0;
		int clicks = 0;
		int applies = 0;
		int size = metricsDTOs.size();
		for (int i = 0; i < metricsDTOs.size(); i++) {
			MetricsDTO dto = new MetricsDTO();
			dto = (MetricsDTO) metricsDTOs.get(i);
			views = views + dto.getViews();
			clicks = clicks + dto.getClicks();
			applies = applies + dto.getApplies();
		}
		metricsDTO.setMetricsName(metricsList.get(0).getOptionName());
		metricsDTO.setViews(views);
		metricsDTO.setClicks(clicks);
		metricsDTO.setApplies(applies);
		jbPostTotalList.add(0, metricsDTO);
		metricsDTO = new MetricsDTO();

		// Calculating average per job posting
		int avgViews = 0;
		int avgClicks = 0;
		int avgApplies = 0;
		if (size > 0) {
			avgViews = views / size;
			avgClicks = clicks / size;
			avgApplies = applies / size;
		}
		metricsDTO.setMetricsName(metricsList.get(1).getOptionName());
		metricsDTO.setViews(avgViews);
		metricsDTO.setClicks(avgClicks);
		metricsDTO.setApplies(avgApplies);
		jbPostTotalList.add(1, metricsDTO);
		metricsDTO = new MetricsDTO();

		// Calculating site - wide average per job posting
		int swAvgViews = 0;
		int swAvgClicks = 0;
		int swAvgApplies = 0;
		long count = 0;
		try {
			count = facilityService.getEmployerCount();
		} catch (JobBoardException e) {
			LOGGER.info("Error occured while getting the Result from Database");
		}

		if (count > 0) {
			swAvgViews = (int) (views / count);
			swAvgClicks = (int) (clicks / count);
			swAvgApplies = (int) (applies / count);
		}
		metricsDTO.setMetricsName(metricsList.get(2).getOptionName());
		metricsDTO.setViews(swAvgViews);
		metricsDTO.setClicks(swAvgClicks);
		metricsDTO.setApplies(swAvgApplies);
		jbPostTotalList.add(2, metricsDTO);

		return jbPostTotalList;
	}
}
