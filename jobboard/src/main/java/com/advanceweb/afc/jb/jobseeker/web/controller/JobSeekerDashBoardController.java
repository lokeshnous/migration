package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerSubscriptionService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Controller
@RequestMapping("/jobSeeker")
@Scope("session")
public class JobSeekerDashBoardController {
	
	@Autowired
	private JobSeekerSubscriptionService jobSeekerSubscriptionsService;

	@Autowired
	private TransformJobSeekerSubscription transformJobSeekerSubscription;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@RequestMapping("/jobSeekerDashBoard")
	public ModelAndView displayDashBoard(HttpSession session){
		ModelAndView model = new ModelAndView();
		JobSeekerDashBoardForm form = new JobSeekerDashBoardForm();
		
		//Retrieve Current subscriptions of the user
		List<JobAlertsDTO> listAlerts = populateDropdownsService.getJobAlertsList();		
		List<SubscriptionsDTO> listSubscriptions = populateDropdownsService.getSubscriptionsList();		
		List<MagazinesDTO> listMagazines = populateDropdownsService.getMagazinesList();

		List<JobSeekerSubscriptionsDTO> currentSubsList = jobSeekerSubscriptionsService.getCurrentSubscriptions(0);
		List<SubscriptionsDTO> selSubs = transformJobSeekerSubscription.jsSubscriptionDTOToJobSeekerSubscriptions(currentSubsList,null, listSubscriptions);
		List<MagazinesDTO> selMags =transformJobSeekerSubscription.jsSubscriptionDTOToJobSeekerMagazines(currentSubsList,null, listMagazines);
		List<JobAlertsDTO> selAlerts = transformJobSeekerSubscription.jsSubscriptionDTOToJobSeekerAlerts(currentSubsList,null, listAlerts);
		form.setUserName((String)session.getAttribute("UserName"));
		model.addObject("jobAlertsList", selAlerts);		
		model.addObject("jobSubscriptionsList", selSubs);		
		model.addObject("jobMagazinesList", selMags);			
		model.addObject("jobSeekerDashBoardForm", form);
		model.setViewName("jobSeekerDashBoard");
		return model;			
	}	
}
