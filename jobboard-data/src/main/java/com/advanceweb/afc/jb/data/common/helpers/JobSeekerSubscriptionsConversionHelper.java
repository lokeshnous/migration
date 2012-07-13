package com.advanceweb.afc.jb.data.common.helpers;

import java.util.ArrayList;
import java.util.List;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.data.entities.MerUserAlerts;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class JobSeekerSubscriptionsConversionHelper {
	
	/**
	 * Converting into Job seeker Subscriptions DTO
	 * @param listSubsAlerts
	 * @return
	 */
	public List<JobSeekerSubscriptionsDTO> transformMerUserAlertsTojsSubsDTO(List<MerUserAlerts> listSubsAlerts){
		
		List<JobSeekerSubscriptionsDTO> subsList = new ArrayList<JobSeekerSubscriptionsDTO>();
		
		if(null != listSubsAlerts){
			for(MerUserAlerts alert : listSubsAlerts){
				JobSeekerSubscriptionsDTO dto = new JobSeekerSubscriptionsDTO();
				dto.setAlertId(alert.getAlertId());
				dto.setAlertValue(alert.getAlertvalue());
				dto.setLookUpId(String.valueOf(alert.getLookupid()));
				dto.setCreatedDate(String.valueOf(alert.getCreated_date()));
				dto.setUserId(alert.getUserid());
				
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
				entity.setAlertId(dto.getAlertId());
				entity.setAlertvalue(dto.getAlertValue());
				entity.setLookupid(Integer.valueOf(dto.getLookUpId()));
				entity.setUserid(dto.getUserId());				
				subsEntityList.add(entity);
			}
		}		
		return subsEntityList;		
	}
}
