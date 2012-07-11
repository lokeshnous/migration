package com.advanceweb.afc.jb.webapp.web.job.seeker.subscription.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.advanceweb.afc.jb.jobseeker.subscription.JobSeekerSubscriptions;
import com.advanceweb.afc.jb.webapp.web.transformers.TransformJobSeekerSubscription;

/**
 * This controller belongs to all jobseekers activity
 * 
 * @author sharadk
 * 
 */

@Controller
public class JobSeekerSubscriptionsController {

	@Autowired
	private TransformJobSeekerSubscription transformJobSeekerSubscription;

	@Autowired
	private JobSeekerSubscriptions jobSeekerSubscriptionsService;
	
	/** Default constructor */
	public JobSeekerSubscriptionsController() {
	}

	
	
	@RequestMapping(value = "/modifySubscription", method = RequestMethod.GET)
	public ModelAndView getAppliedJob(Map model) {

		
		
		return new ModelAndView("jobseekersubscription");
	}
	
	/**
	 * This method is called to save subscription
	 * 
	 * @param JobSeekerSubscriptionForm
	 * @param result
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/saveJobSeekerSubscription", method = RequestMethod.POST)
	public ModelAndView saveJobSeekerSubscription(
			@Valid JobSeekerSubscriptionForm form, BindingResult result) {

		try {

			if (result.hasErrors()) {
				return new ModelAndView("jobseekersubscription");
			}
			JobSeekerSubscriptionsDTO jobSeekerSubscriptionsDTO = new JobSeekerSubscriptionsDTO();
			JobSeekerSubscriptionForm jobSeekerSubscriptionForm = transformJobSeekerSubscription
					.jsSubscriptionDTOToJobSeekerSubscriptionForm(jobSeekerSubscriptionsDTO);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("jobseekersubscription");
	}*/
	
	
	
	@RequestMapping(value = "/saveJobSeekerSubscription")
	public ModelAndView saveSubscription(@RequestParam("id") Long id) {

		jobSeekerSubscriptionsService.saveJobSeekerSubscription(id);
		return new ModelAndView("jobseekersubscription");
	}

}
