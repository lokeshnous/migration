package com.advanceweb.afc.jb.user.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

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
			UserSubscriptionForm form, List<DropDownDTO> listSubscriptions,
			HttpSession session) {

		List<String> currSubList = new ArrayList<String>();
		List<String> currPrintList = new ArrayList<String>();
		List<String> currDigList = new ArrayList<String>();
		List<String> currNewsList = new ArrayList<String>();
		List<DropDownDTO> selSubList = new ArrayList<DropDownDTO>();
		if (null != currentSubsList) {
			for (UserSubscriptionsDTO dto : currentSubsList) {
				for (DropDownDTO subdto : listSubscriptions) {
					if (subdto.getOptionId().equals(
							String.valueOf(dto.getSubscriptionId()))) {
						currSubList.add(subdto.getOptionId());
						selSubList.add(subdto);
						if (subdto
								.getOptionId()
								.equalsIgnoreCase(
										Integer.toString(MMJBCommonConstants.PRINT_JS_SUBSCRIPTION))) {
							currPrintList.add(Integer.toString(dto
									.getPublicationId()));
						}
						if (subdto
								.getOptionId()
								.equalsIgnoreCase(
										Integer.toString(MMJBCommonConstants.DIGITAL_JS_SUBSCRIPTION))) {
							currDigList.add(Integer.toString(dto
									.getPublicationId()));
						}
						if (subdto
								.getOptionId()
								.equalsIgnoreCase(
										Integer.toString(MMJBCommonConstants.ENEWS_JS_SUBSCRIPTION))) {
							currNewsList.add(Integer.toString(dto
									.getPublicationId()));
						}
					}
				}
			}
			if (selSubList != null && !selSubList.isEmpty()) {
				for (DropDownDTO downDTO : selSubList) {
					if (downDTO.getOptionName().equalsIgnoreCase(
							"Print-Magazine ")) {
						form.setPrintCheckbox(true);
						form.setPrintSub(currPrintList
								.toArray(new String[currPrintList.size()]));
					}
					if (downDTO.getOptionName().equalsIgnoreCase(
							"Digital-Magazine ")) {
						form.setDigCheckbox(true);
						form.setDigSub(currDigList
								.toArray(new String[currDigList.size()]));
					}
					if (downDTO.getOptionName().equalsIgnoreCase(
							"E-newsletters")) {
						form.setEnewsCheckbox(true);
						form.setNewsSub(currNewsList
								.toArray(new String[currNewsList.size()]));
					}
					if (downDTO.getOptionName().equalsIgnoreCase("E-mailer")) {
						form.setMailCheckbox(true);
					}
				}
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
			UserSubscriptionForm form, boolean printCheckbox,
			boolean digCheckbox, boolean enewsCheckbox, boolean mailCheckbox) {
		List<UserSubscriptionsDTO> selSubsList = new ArrayList<UserSubscriptionsDTO>();
		if ((form.getPrintSub().length != 0) && printCheckbox
				&& (null != form.getPrintSub())) {
			for (String selSubscription : form.getPrintSub()) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(MMJBCommonConstants.PRINT_JS_SUBSCRIPTION);
				dto.setPublicationId(Integer.valueOf(selSubscription));
				dto.setUserId(form.getUserId());
				dto.setActive(1);
				selSubsList.add(dto);
			}
		} else {
			UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
			dto.setSubscriptionId(MMJBCommonConstants.PRINT_JS_SUBSCRIPTION);
			dto.setPublicationId(0);
			dto.setUserId(form.getUserId());
			dto.setActive(1);
			selSubsList.add(dto);
		}
		if ((form.getDigSub().length != 0) && (null != form.getDigSub())
				&& digCheckbox) {
			for (String selSubscription : form.getDigSub()) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(MMJBCommonConstants.DIGITAL_JS_SUBSCRIPTION);
				dto.setPublicationId(Integer.valueOf(selSubscription));
				dto.setUserId(form.getUserId());
				dto.setActive(1);
				selSubsList.add(dto);
			}
		} else if (digCheckbox) {
			UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
			dto.setSubscriptionId(MMJBCommonConstants.DIGITAL_JS_SUBSCRIPTION);
			dto.setPublicationId(0);
			dto.setUserId(form.getUserId());
			dto.setActive(1);
			selSubsList.add(dto);
		}
		if ((form.getNewsSub().length != 0) && (null != form.getNewsSub())
				&& enewsCheckbox) {
			for (String selSubscription : form.getNewsSub()) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(MMJBCommonConstants.ENEWS_JS_SUBSCRIPTION);
				dto.setPublicationId(Integer.valueOf(selSubscription));
				dto.setUserId(form.getUserId());
				dto.setActive(1);
				selSubsList.add(dto);
			}
		} else if (enewsCheckbox) {
			UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
			dto.setSubscriptionId(MMJBCommonConstants.ENEWS_JS_SUBSCRIPTION);
			dto.setPublicationId(0);
			dto.setUserId(form.getUserId());
			dto.setActive(1);
			selSubsList.add(dto);
		}
		if (mailCheckbox) {
			UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
			dto.setSubscriptionId(MMJBCommonConstants.EMAIL_JS_SUBSCRIPTION);
			dto.setPublicationId(0);
			dto.setUserId(form.getUserId());
			dto.setActive(1);
			selSubsList.add(dto);
		}
		return selSubsList;
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

	public List<DropDownDTO> jsSubDTOToDropDownDTO(
			List<UserSubscriptionsDTO> digitalSubList,
			UserSubscriptionForm subscriptform) {
		List<DropDownDTO> facsub = new ArrayList<DropDownDTO>();
		if (null != digitalSubList) {
			for (UserSubscriptionsDTO subscriptionsDTO : digitalSubList) {
				DropDownDTO downDTO = new DropDownDTO();
				downDTO.setOptionId(Integer.toString(subscriptionsDTO
						.getPublicationId()));
				downDTO.setOptionName(subscriptionsDTO.getPublicationName());
				facsub.add(downDTO);
			}
		}
		return facsub;
	}

	/**
	 * This method is called to save selected subscriptions
	 * 
	 * @param currentSubsList
	 * @param form
	 * @param listSubscriptions
	 * @return
	 */
	public List<UserSubscriptionsDTO> jsSubscriptionFormToUserSubsDTO(
			UserSubscriptionForm form, boolean digCheckbox,
			boolean enewsCheckbox, boolean mailCheckbox) {
		List<UserSubscriptionsDTO> selSubsList = new ArrayList<UserSubscriptionsDTO>();
		if ((form.getDigSub().length != 0) && (null != form.getDigSub())
				&& digCheckbox) {
			for (String selSubscription : form.getDigSub()) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(MMJBCommonConstants.DIGITAL_SUBSCRIPTION);
				dto.setPublicationId(Integer.valueOf(selSubscription));
				dto.setFacilityId(form.getFacilityId());
				dto.setActive(1);
				selSubsList.add(dto);
			}
		} else if (digCheckbox) {
			UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
			dto.setSubscriptionId(MMJBCommonConstants.DIGITAL_SUBSCRIPTION);
			dto.setPublicationId(0);
			dto.setFacilityId(form.getFacilityId());
			dto.setActive(1);
			selSubsList.add(dto);
		}
		if ((form.getNewsSub().length != 0) && (null != form.getNewsSub())
				&& enewsCheckbox) {
			for (String selSubscription : form.getNewsSub()) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(MMJBCommonConstants.ENEWS_LETTER_SUBSCRIPTION);
				dto.setPublicationId(Integer.valueOf(selSubscription));
				dto.setFacilityId(form.getFacilityId());
				dto.setActive(1);
				selSubsList.add(dto);
			}
		} else if (enewsCheckbox) {
			UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
			dto.setSubscriptionId(MMJBCommonConstants.ENEWS_LETTER_SUBSCRIPTION);
			dto.setPublicationId(0);
			dto.setFacilityId(form.getFacilityId());
			dto.setActive(1);
			selSubsList.add(dto);
		}
		if (mailCheckbox) {
			UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
			dto.setSubscriptionId(MMJBCommonConstants.EMAIL_SUBSCRIPTION);
			dto.setPublicationId(0);
			dto.setFacilityId(form.getFacilityId());
			dto.setActive(1);
			selSubsList.add(dto);
		}
		return selSubsList;
	}

	/**
	 * This method is called to select current subscriptions
	 * 
	 * @param currentSubsList
	 * @param form
	 * @param listSubscriptions
	 * @return
	 */
	public List<DropDownDTO> jsSubscriptionDTOToFacilitySubscriptions(
			List<UserSubscriptionsDTO> currentSubsList,
			UserSubscriptionForm form, List<DropDownDTO> listSubscriptions) {
		List<String> currSubList = new ArrayList<String>();
		List<String> currDigList = new ArrayList<String>();
		List<String> currNewsList = new ArrayList<String>();
		List<DropDownDTO> selSubList = new ArrayList<DropDownDTO>();
		if (null != currentSubsList) {
			for (UserSubscriptionsDTO dto : currentSubsList) {
				for (DropDownDTO subdto : listSubscriptions) {
					if (subdto.getOptionId().equals(
							String.valueOf(dto.getSubscriptionId()))) {
						currSubList.add(subdto.getOptionId());
						selSubList.add(subdto);
						if (subdto
								.getOptionId()
								.equalsIgnoreCase(
										Integer.toString(MMJBCommonConstants.DIGITAL_SUBSCRIPTION))) {
							currDigList.add(Integer.toString(dto
									.getPublicationId()));
						}
						if (subdto
								.getOptionId()
								.equalsIgnoreCase(
										Integer.toString(MMJBCommonConstants.ENEWS_LETTER_SUBSCRIPTION))) {
							currNewsList.add(Integer.toString(dto
									.getPublicationId()));
						}
					}
				}
			}
			if (selSubList != null && !selSubList.isEmpty()) {
				for (DropDownDTO downDTO : selSubList) {
					if (downDTO.getOptionName().equalsIgnoreCase(
							"Digital-Magazine ")) {
						form.setDigCheckbox(true);
						form.setDigSub(currDigList
								.toArray(new String[currDigList.size()]));
					}
					if (downDTO.getOptionName().equalsIgnoreCase(
							"E-newsletters")) {
						form.setEnewsCheckbox(true);
						form.setNewsSub(currNewsList
								.toArray(new String[currNewsList.size()]));
					}
					if (downDTO.getOptionName().equalsIgnoreCase("E-mailer")) {
						form.setMailCheckbox(true);
					}
				}
			}
		}
		return selSubList;
	}
}