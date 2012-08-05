package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.MerUserAlerts;

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

				dto.setSubscriptionId(alert.getId().getSubscriptionId());
				dto.setUserId(alert.getId().getUserId());			
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
	public List<MerUserAlerts> transformjsSubsDTOToMerUserAlerts(List<JobSeekerSubscriptionsDTO> listSubsDTO){
		
		List<MerUserAlerts> subsEntityList = new ArrayList<MerUserAlerts>();
		
		if(null != listSubsDTO){
			for(JobSeekerSubscriptionsDTO dto : listSubsDTO){
				MerUserAlerts entity = new MerUserAlerts();
				entity.setUserid(dto.getUserId());				
				subsEntityList.add(entity);
			}
		}		
		return subsEntityList;		
	}
}
