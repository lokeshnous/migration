package com.advanceweb.afc.jb.webapp.web.transformers;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;

import com.advanceweb.afc.jb.webapp.web.forms.subscription.JobSeekerSubscriptionForm;

public class TransformJobSeekerSubscription {

	/**
	 * Converting JobSeekerRegistrationDTO to JobSeekerRegistrationForm
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public JobSeekerSubscriptionForm jsSubscriptionDTOToJobSeekerSubscriptionForm(
			JobSeekerSubscriptionsDTO jobSeekerSubscriptionsDTO) {

		JobSeekerSubscriptionForm form = new JobSeekerSubscriptionForm();

		if (null != jobSeekerSubscriptionsDTO) {

			//form.setJobAlerts(jobSeekerSubscriptionsDTO.getJobAlerts());
			///form.setMagazine(jobSeekerSubscriptionsDTO.getMagazines());
			//form.setSubscription(jobSeekerSubscriptionsDTO.getSubscriptions());

		}

		return form;

	}
}