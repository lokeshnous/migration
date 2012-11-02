package com.advanceweb.afc.jb.jobseeker.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilitySubscription;
import com.advanceweb.afc.jb.data.entities.AdmFacilitySubscriptionPK;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscriptionPK;
import com.advanceweb.afc.jb.data.entities.MerPublication;
import com.advanceweb.afc.jb.data.entities.ResCoverletter;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Repository("subscriptionHelper")
public class UserSubscriptionsConversionHelper {

	/**
	 * Converting into Job seeker Subscriptions DTO
	 * 
	 * @param listSubsAlerts
	 * @return
	 */
	public List<UserSubscriptionsDTO> transformMerUserAlertsTojsSubsDTO(
			List<AdmUserSubscription> listSubs) {

		List<UserSubscriptionsDTO> subsList = new ArrayList<UserSubscriptionsDTO>();

		if (null != listSubs) {
			for (AdmUserSubscription alert : listSubs) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(alert.getSubscriptionPK()
						.getSubscriptionId());
				dto.setPublicationId(alert.getSubscriptionPK()
						.getPublicationId());
				dto.setUserId(alert.getSubscriptionPK().getUserId());
				subsList.add(dto);
			}
		}
		return subsList;
	}

	/**
	 * Converting into MerUserAlerts entity
	 * 
	 * @param listSubsAlerts
	 * @return
	 */
	public List<AdmUserSubscription> transformjsSubsDTOToAdmUserSubs(
			List<UserSubscriptionsDTO> listSubsDTO,
			List<AdmUserSubscription> listSubsAlerts) {
		List<AdmUserSubscription> subsEntityList = new ArrayList<AdmUserSubscription>();

		if (null != listSubsDTO) {
			for (UserSubscriptionsDTO dto : listSubsDTO) {
				AdmUserSubscription entity = new AdmUserSubscription();
				AdmUserSubscriptionPK subscription = new AdmUserSubscriptionPK();
				subscription.setSubscriptionId(dto.getSubscriptionId());
				subscription.setUserId(dto.getUserId());
				subscription.setPublicationId(dto.getPublicationId());
				entity.setSubscriptionPK(subscription);
				entity.setActive(dto.getActive());
				subsEntityList.add(entity);
			}
		}
		return subsEntityList;
	}

	/**
	 * 
	 * @param subDTO
	 * @return
	 */
	public ResCoverLetterDTO toTransFormListToDTO(List<ResCoverletter> subDTO) {
		ResCoverLetterDTO rfc = new ResCoverLetterDTO();
		if (rfc != null) {
			for (ResCoverletter resFac : subDTO) {
				rfc.setActive(resFac.getActive());
				rfc.setCoverletterId(resFac.getCoverletterId());
				rfc.setCoverletterText(resFac.getCoverletterText());
				rfc.setName(resFac.getName());
				rfc.setUserId(resFac.getUserId());
				// rfc.setCreateDt(resFac.getCreateDt());
			}
		}
		return rfc;
	}

	/**
	 * 
	 * @param coverLet
	 * @return List
	 * @author kartikm This is list to DTO converter
	 * @version v.0.1
	 */

	public List<ResCoverLetterDTO> transformCoverLeterlistToDTO(
			List<ResCoverletter> coverLet) {
		List<ResCoverLetterDTO> manageCoverLetterDTOList = new ArrayList<ResCoverLetterDTO>();
		int clIndex = 0;
		for (ResCoverletter merUser : coverLet) {
			ResCoverLetterDTO res = new ResCoverLetterDTO();
			res.setActive(merUser.getActive());
			res.setName(merUser.getName());
			res.setCoverletterId(merUser.getCoverletterId());
			res.setCreateDt(dateToStringConveter(merUser.getCreateDt()));
			res.setCoverletterText(merUser.getCoverletterText());
			res.setUpdateDt(dateToStringConveter(merUser.getUpdateDt()));
			res.setUserId(merUser.getUserId());
			manageCoverLetterDTOList.add(res);
			clIndex = clIndex + 1;
		}
		return manageCoverLetterDTOList;

	}

	/**
	 * 
	 * @param date
	 * @return date of string format Date format to String format converter
	 * @author kartikm
	 * @version v.0.1
	 */

	public String dateToStringConveter(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = sdf.format(date);
		return strDate;
	}

	/**
	 * Converting facility subscription list to subscription DTO
	 * 
	 * @param listSubsAlerts
	 * @return
	 */
	public List<UserSubscriptionsDTO> transformFacilitySubTojsSubsDTO(
			List<AdmFacilitySubscription> listSubs) {

		List<UserSubscriptionsDTO> subsList = new ArrayList<UserSubscriptionsDTO>();

		if (null != listSubs) {
			for (AdmFacilitySubscription alert : listSubs) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setSubscriptionId(alert.getAdmFacilitySubscriptionPK()
						.getSubscriptionId());
				dto.setFacilityId(alert.getAdmFacilitySubscriptionPK()
						.getFacilityId());
				dto.setPublicationId(alert.getAdmFacilitySubscriptionPK()
						.getPublicationId());
				subsList.add(dto);
			}
		}
		return subsList;
	}

	/**
	 * This method convert merPublication list to subscription DTO
	 * 
	 * @param digSubList
	 * @return
	 */
	public List<UserSubscriptionsDTO> transferDigitalSubToSubDTO(
			List<MerPublication> digSubList) {
		List<UserSubscriptionsDTO> listDTOs = new ArrayList<UserSubscriptionsDTO>();
		if (null != digSubList) {
			for (MerPublication publication : digSubList) {
				UserSubscriptionsDTO dto = new UserSubscriptionsDTO();
				dto.setPublicationId(publication.getPublicationId());
				dto.setPublicationName(publication.getPublicationName());
				listDTOs.add(dto);
			}
		}
		return listDTOs;
	}

	/**
	 * Method to convert from subscription DTO to Facility subscription Entity
	 * 
	 * @param listSubsDTO
	 * @param listSubsAlerts
	 * @return
	 */
	public List<AdmFacilitySubscription> transformjsSubsDTOToAdmFacilitySubs(
			List<UserSubscriptionsDTO> listSubsDTO,
			List<AdmFacilitySubscription> listSubsAlerts) {
		List<AdmFacilitySubscription> subscriptions = new ArrayList<AdmFacilitySubscription>();
		if (null != listSubsDTO) {
			for (UserSubscriptionsDTO dto : listSubsDTO) {
				AdmFacilitySubscriptionPK subscriptionPK = new AdmFacilitySubscriptionPK();
				subscriptionPK.setFacilityId(dto.getFacilityId());
				subscriptionPK.setSubscriptionId(dto.getSubscriptionId());
				subscriptionPK.setPublicationId(dto.getPublicationId());
				AdmSubscription admSubscription = new AdmSubscription();
				admSubscription.setSubscriptionId(dto.getSubscriptionId());
				AdmFacilitySubscription facSubscription = new AdmFacilitySubscription();
				facSubscription.setActive(dto.getActive());
				facSubscription.setCreateDt(new Date());
				facSubscription.setAdmFacilitySubscriptionPK(subscriptionPK);
				facSubscription.setAdmSubscription(admSubscription);
				subscriptions.add(facSubscription);
			}
		}
		return subscriptions;
	}

	/**
	 * Validating the selected facility subscriptions with existing to remove
	 * and to save newly selected subscriptions
	 * 
	 * @param subsDTO
	 * @param listSubsAlerts
	 * @return
	 */
	public boolean validateAdmFacilitySubscriptions(
			UserSubscriptionsDTO subsDTO,
			List<AdmFacilitySubscription> listSubsAlerts) {

		if (null != subsDTO && null != listSubsAlerts) {
			for (AdmFacilitySubscription entity : listSubsAlerts) {
				if (subsDTO.getSubscriptionId() == entity.getAdmSubscription()
						.getSubscriptionId()
						&& subsDTO.getFacilityId() == entity.getAdmFacility()
								.getFacilityId()) {
					listSubsAlerts.remove(entity);
					return true;
				}
			}
		}

		return false;
	}
}
