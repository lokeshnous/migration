package com.advanceweb.afc.jb.jobseeker.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscriptionPK;
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
				if (!validateAdmUserSubscriptions(dto, listSubsAlerts)) {
					AdmUserSubscription entity = new AdmUserSubscription();
					AdmUserSubscriptionPK subscription = new AdmUserSubscriptionPK();
					subscription.setSubscriptionId(dto.getSubscriptionId());
					subscription.setUserId(dto.getUserId());
					entity.setSubscriptionPK(subscription);
					entity.setActive(dto.getActive());
					subsEntityList.add(entity);
				}
			}
		}
		return subsEntityList;
	}

	/**
	 * Converting into MerUserAlerts entity
	 * 
	 * @param listSubsAlerts
	 * @return
	 */
	private boolean validateAdmUserSubscriptions(UserSubscriptionsDTO subDTO,
			List<AdmUserSubscription> listSubsAlerts) {

		if (null != subDTO && null != listSubsAlerts) {
			for (AdmUserSubscription entity : listSubsAlerts) {
				if (subDTO.getSubscriptionId() == entity.getSubscriptionPK()
						.getSubscriptionId()
						&& subDTO.getUserId() == entity.getSubscriptionPK()
								.getUserId()) {
					listSubsAlerts.remove(entity);
					return true;
				}
			}
		}
		return false;
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

}
