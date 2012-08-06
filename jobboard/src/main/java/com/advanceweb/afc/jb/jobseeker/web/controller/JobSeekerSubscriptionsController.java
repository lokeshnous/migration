package com.advanceweb.afc.jb.jobseeker.web.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerSubscriptionService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * This controller belongs to all jobseekers Subscriptions
 * 
 * @author sharadk
 * 
 */

@Controller
@RequestMapping("/subscriptions")
public class JobSeekerSubscriptionsController {

	@Autowired
	private TransformJobSeekerSubscription transformJobSeekerSubscription;

	@Autowired
	private JobSeekerSubscriptionService jobSeekerSubscriptionsService;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/**
	 * to view subscription page
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/modifySubscription", method = RequestMethod.GET)
	public ModelAndView viewCurrentSubscriptions() {
		
		ModelAndView model = new ModelAndView();
		JobSeekerSubscriptionForm form = new JobSeekerSubscriptionForm();
		
		List<DropDownDTO> listSubscriptions = populateDropdownsService.getSubscriptionsList();		
		List<JobSeekerSubscriptionsDTO> currentSubsList = jobSeekerSubscriptionsService.getCurrentSubscriptions(1564);
		transformJobSeekerSubscription.jsSubscriptionDTOToJobSeekerSubscriptions(currentSubsList,form, listSubscriptions);
//		form.setUserId(1564);
		model.addObject("jobSubscriptionsList", listSubscriptions);				
		model.addObject("jobSeekerSubscriptionForm",form);
		model.setViewName("jobseekermodifysubscriptions");	
		return model;
	}
	
	/**
	 * This method is called to save subscription
	 * 
	 * @param JobSeekerSubscriptionForm
	 * @param result
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveJobSeekerSubscription", method = RequestMethod.POST)
	public String saveJobSeekerSubscription(JobSeekerSubscriptionForm form, BindingResult result,HttpSession session) {
		
		try {
			
//			form.setUserId(Integer.valueOf(String.valueOf(session.getAttribute("userId"))));
			form.setUserId(1564);
			List<JobSeekerSubscriptionsDTO>	listSubsDTO = transformJobSeekerSubscription.jsSubscriptionFormToJobSeekerSubsDTO(form);			
			boolean bSaved = jobSeekerSubscriptionsService.saveJobSeekerSubscription(listSubsDTO, form.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
