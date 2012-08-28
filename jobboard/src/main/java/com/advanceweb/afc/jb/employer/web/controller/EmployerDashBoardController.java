package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.service.LoginService;

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

	@RequestMapping("/employerDashBoard")
	public ModelAndView displayDashBoard(HttpSession session) {

		int facilityId = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		//Get the job post details of logged in employer
		List<MetricsDTO>  metricsDTOs = loginService.getJobPostTotal(facilityId);
		MetricsDTO dto = new MetricsDTO();
		//jbPostTotalList will be having job post total details for metrics
		MetricsDTO jbPostTotalList = new MetricsDTO();
		int views = 0;
		int clicks = 0;
		int applies = 0;
		for (int i = 0; i < metricsDTOs.size(); i++) {
			dto = (MetricsDTO) metricsDTOs.get(i);
			views = views + dto.getViews();
			clicks = clicks + dto.getClicks();
			applies = applies + dto.getApplies();
		}	
		jbPostTotalList.setViews(views);
		jbPostTotalList.setClicks(clicks);
		jbPostTotalList.setApplies(applies);
		return new ModelAndView("employerDashboard");
	}
}
