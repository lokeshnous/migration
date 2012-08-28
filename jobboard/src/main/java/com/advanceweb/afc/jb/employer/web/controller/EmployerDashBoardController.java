package com.advanceweb.afc.jb.employer.web.controller;

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

		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// Get the job post details of logged in employer
		List<MetricsDTO> metricsDTOs = loginService.getJobPostTotal(facilityId);
		MetricsDTO dto = new MetricsDTO();
		MetricsDTO jbPostTotalList = new MetricsDTO();
		// Geting mtrics values from look up table
		List<DropDownDTO> metricsList = populateDropdownsService
				.populateDropdown("Metrics");
		// jbPostTotalList will be having job post total details for metrics
		int views = 0;
		int clicks = 0;
		int applies = 0;
		int size = metricsDTOs.size();
		for (int i = 0; i < metricsDTOs.size(); i++) {
			dto = (MetricsDTO) metricsDTOs.get(i);
			views = views + dto.getViews();
			clicks = clicks + dto.getClicks();
			applies = applies + dto.getApplies();
		}
		//jbPostTotalList.setMetricsName(metricsList.indexOf(0));
		jbPostTotalList.setViews(views);
		jbPostTotalList.setClicks(clicks);
		jbPostTotalList.setApplies(applies);
		int avgViews = 0;
		int avgClicks = 0;
		int avgApplies = 0;
		if (size > 0) {
			avgViews = views / size;
			avgClicks = clicks / size;
			avgApplies = applies / size;
		}
		return new ModelAndView("employerDashboard");
	}
}
