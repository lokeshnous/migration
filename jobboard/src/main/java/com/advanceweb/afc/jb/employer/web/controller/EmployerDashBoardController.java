package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * @author Prince
 * @version 1.0
 * @since 27th August, 2012
 */

@Controller
@RequestMapping("/employer")
public class EmployerDashBoardController {

	@Autowired
	private LoginService loginService;

	@Autowired
	PopulateDropdowns populateDropdownsService;

	@RequestMapping("/employerDashBoard")
	public ModelAndView displayDashBoard(HttpSession session) {
		ModelAndView model = new ModelAndView();
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		MetricsDTO metricsDTO = new MetricsDTO();

		// Get the job post details of logged in employer
		List<MetricsDTO> metricsDTOs = loginService.getJobPostTotal(facilityId);

		// Geting mtrics values from look up table
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
		//int count = loginService.getEmployerCount();
		if (size > 0) {
			swAvgViews = views / size;
			swAvgClicks = clicks / size;
			swAvgApplies = applies / 2;
		}
		metricsDTO.setMetricsName(metricsList.get(2).getOptionName());
		metricsDTO.setViews(swAvgViews);
		metricsDTO.setClicks(swAvgClicks);
		metricsDTO.setApplies(swAvgApplies);
		jbPostTotalList.add(2, metricsDTO);

		model.addObject("jbPostTotalList", jbPostTotalList);
		model.setViewName("employerDashboard");
		return model;
	}
}
