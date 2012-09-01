package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscriptionPK;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Repository("jsSubscriptionHelper")
public class JobSeekerSubscriptionsConversionHelper {
	
	/**
	 * Converting into Job seeker Subscriptions DTO
	 * @param listSubsAlerts
	 * @return
	 */
	public List<JobSeekerSubscriptionsDTO> transformMerUserAlertsTojsSubsDTO(List<AdmUserSubscription> listSubs){
		
		List<JobSeekerSubscriptionsDTO> subsList = new ArrayList<JobSeekerSubscriptionsDTO>();
		
		if(null != listSubs){
			for(AdmUserSubscription alert : listSubs){
				JobSeekerSubscriptionsDTO dto = new JobSeekerSubscriptionsDTO();
				dto.setSubscriptionId(alert.getSubscriptionPK().getSubscriptionId());
				dto.setUserId(alert.getSubscriptionPK().getUserId());			
				subsList.add(dto);
			}
		}		
		return subsList;		
	}
	
	
	/**
	 * Converting into MerUserAlerts entity
	 * @param listSubsAlerts
	 * @return
	 */
	public List<AdmUserSubscription> transformjsSubsDTOToAdmUserSubs(List<JobSeekerSubscriptionsDTO> listSubsDTO, 
			List<AdmUserSubscription> listSubsAlerts){
		
		List<AdmUserSubscription> subsEntityList = new ArrayList<AdmUserSubscription>();
		
		if(null != listSubsDTO){
			for(JobSeekerSubscriptionsDTO dto : listSubsDTO){
				if(!validateAdmUserSubscriptions(dto, listSubsAlerts)){
					AdmUserSubscription entity = new AdmUserSubscription();
						AdmUserSubscriptionPK pk = new AdmUserSubscriptionPK();
						pk.setSubscriptionId(dto.getSubscriptionId());
						pk.setUserId(dto.getUserId());
					entity.setSubscriptionPK(pk);
					entity.setActive(dto.getActive());
					subsEntityList.add(entity);
				}
			}
		}		
		return subsEntityList;		
	}
	
	/**
	 * Converting into MerUserAlerts entity
	 * @param listSubsAlerts
	 * @return
	 */
	private boolean validateAdmUserSubscriptions(JobSeekerSubscriptionsDTO subDTO, List<AdmUserSubscription> listSubsAlerts){
			
		if(null != subDTO && null != listSubsAlerts){
			for(AdmUserSubscription entity : listSubsAlerts){
				if(subDTO.getSubscriptionId() == entity.getSubscriptionPK().getSubscriptionId() && 
						subDTO.getUserId() == entity.getSubscriptionPK().getUserId()){
					listSubsAlerts.remove(entity);
					return true;
				}
			}
		}		
		return false;		
	}
	
}
