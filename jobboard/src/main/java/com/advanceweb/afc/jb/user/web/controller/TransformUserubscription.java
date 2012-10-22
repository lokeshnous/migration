package com.advanceweb.afc.jb.user.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;

@Repository("userubscription")
public class TransformUserubscription {

	/**
	 * Converting JobSeekerRegistrationDTO to JobSeekerRegistrationForm
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public UserSubscriptionForm jsSubscriptionDTOToJobSeekerSubscriptionForm(
			List<UserSubscriptionsDTO> currentSubsList,
			UserSubscriptionForm form) {

		if (null != currentSubsList) {
			for (UserSubscriptionsDTO dto : currentSubsList) {

				// dto.getLookUpId().
			}
			// form.setJobAlerts(jobSeekerSubscriptionsDTO.getJobAlerts());
			// /form.setMagazine(jobSeekerSubscriptionsDTO.getMagazines());
			// form.setSubscription(jobSeekerSubscriptionsDTO.getSubscriptions());

		}

		return form;

	}

	/**
	 * This method is called to select current subscriptions
	 * 
	 * @param currentSubsList
	 * @param form
	 * @param listSubscriptions
	 * @return
	 */
	public List<DropDownDTO> jsSubscriptionDTOToJobSeekerSubscriptions(
			List<UserSubscriptionsDTO> currentSubsList,
			UserSubscriptionForm form, List<DropDownDTO> listSubscriptions) {

		List<String> currSubList = new ArrayList<String>();
		List<DropDownDTO> selSubList = new ArrayList<DropDownDTO>();
		if (null != currentSubsList) {
			for (UserSubscriptionsDTO dto : currentSubsList) {
				for (DropDownDTO subdto : listSubscriptions) {
					if (subdto.getOptionId().equals(
							String.valueOf(dto.getSubscriptionId()))) {
						currSubList.add(subdto.getOptionId());
						selSubList.add(subdto);
					}
				}
			}
			if (null != form) {
				form.setCurrentsubs(currSubList.toArray(new String[currSubList
						.size()]));
			}
		}
		return selSubList;
	}

	/**
	 * This method is called to select current Magazines
	 * 
	 * @param currentSubsList
	 * @param form
	 * @param listSubscriptions
	 * @return
	 */
	public List<MagazinesDTO> jsSubscriptionDTOToJobSeekerMagazines(
			List<UserSubscriptionsDTO> currentSubsList,
			UserSubscriptionForm form, List<MagazinesDTO> listMagaziness) {
		List<String> currMagList = new ArrayList<String>();
		List<MagazinesDTO> selMagList = new ArrayList<MagazinesDTO>();
		if (null != currentSubsList) {
			for (UserSubscriptionsDTO dto : currentSubsList) {

				if (null != listMagaziness) {
					for (MagazinesDTO subdto : listMagaziness) {
						//
						// if(dto.getLookUpId().equals(subdto.getMagazineId())){
						// currMagList.add(dto.getLookUpId().toString());
						// selMagList.add(subdto);
						// }
					}
				}
			}

			if (null != form) {
				form.setCurrentmagazines(currMagList
						.toArray(new String[currMagList.size()]));
			}
		}
		return selMagList;
	}

	/**
	 * This method is called to select current Job Alerts
	 * 
	 * @param currentSubsList
	 * @param form
	 * @param listSubscriptions
	 * @return
	 */
	public List<JobAlertsDTO> jsSubscriptionDTOToJobSeekerAlerts(
			List<UserSubscriptionsDTO> currentSubsList,
			UserSubscriptionForm form, List<JobAlertsDTO> listAlerts) {
		List<String> currAlrtList = new ArrayList<String>();
		List<JobAlertsDTO> currentSelectedSubsList = new ArrayList<JobAlertsDTO>();
		if (null != currentSubsList) {
			for (UserSubscriptionsDTO dto : currentSubsList) {
				if (null != listAlerts) {
					for (JobAlertsDTO subdto : listAlerts) {
						//
						// if(dto.getLookUpId().equals(subdto.getAlertId())){
						// currAlrtList.add(dto.getLookUpId().toString());
						// currentSelectedSubsList.add(subdto);
						// }
					}
				}
			}

			if (null != form) {
				form.setCurrentJobAlerts(currAlrtList
						.toArray(new String[currAlrtList.size()]));
			}
		}
		return currentSelectedSubsList;
	}

	/**
	 * This method is called to save selected subscriptions
	 * 
	 * @param currentSubsList
	 * @param form
	 * @param listSubscriptions
	 * @return
	 */
	public List<UserSubscriptionsDTO> jsSubscriptionFormToJobSeekerSubsDTO(
			UserSubscriptionForm form) {
		List<UserSubscriptionsDTO> selectedSubsList = new ArrayList<UserSubscriptionsDTO>();
		if (null != form.getCurrentsubs()) {
			for (String selSubscription : form.getCurrentsubs()) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(Integer.valueOf(selSubscription));
				dto.setUserId(form.getUserId());
				dto.setActive(1);
				selectedSubsList.add(dto);
			}
		}

		return selectedSubsList;
	}

	/**
	 * Converting JobSeekerRegistrationDTO to JobSeekerRegistrationForm
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public List<DropDownDTO> jsSubscriptionDTOToJobSeekerSubscriptionForm(
			List<UserSubscriptionsDTO> currentSubsList,
			List<DropDownDTO> listSubscriptions) {

		List<DropDownDTO> currentSubs = new ArrayList<DropDownDTO>();
		if (null != currentSubsList) {

			for (UserSubscriptionsDTO dto : currentSubsList) {
				for (DropDownDTO dropDown : listSubscriptions) {
					if (dropDown.getOptionId().equals(
							String.valueOf(dto.getSubscriptionId()))) {
						currentSubs.add(dropDown);
					}
				}
			}
		}
		return currentSubs;

	}

}