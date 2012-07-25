package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;

@Repository("transformJobSeekerSubscription")
public class TransformJobSeekerSubscription {

	/**
	 * Converting JobSeekerRegistrationDTO to JobSeekerRegistrationForm
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public JobSeekerSubscriptionForm jsSubscriptionDTOToJobSeekerSubscriptionForm(List<JobSeekerSubscriptionsDTO> 
	currentSubsList,JobSeekerSubscriptionForm form) {

		if (null != currentSubsList) {
			for(JobSeekerSubscriptionsDTO dto :currentSubsList){
				
//				dto.getLookUpId().
			}
			//form.setJobAlerts(jobSeekerSubscriptionsDTO.getJobAlerts());
			///form.setMagazine(jobSeekerSubscriptionsDTO.getMagazines());
			//form.setSubscription(jobSeekerSubscriptionsDTO.getSubscriptions());

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
	public List<SubscriptionsDTO> jsSubscriptionDTOToJobSeekerSubscriptions(List<JobSeekerSubscriptionsDTO> 
		currentSubsList,JobSeekerSubscriptionForm form,List<SubscriptionsDTO> listSubscriptions){
		List<String> currSubList = new ArrayList<String>();
		List<SubscriptionsDTO> selSubList = new ArrayList<SubscriptionsDTO>();
		if (null != currentSubsList) {
			for(JobSeekerSubscriptionsDTO dto :currentSubsList){
				
				for(SubscriptionsDTO subdto :listSubscriptions){
					
					if(dto.getLookUpId().equals(subdto.getSubscriptionId())){
						currSubList.add(dto.getLookUpId().toString());
						selSubList.add(subdto);
					}
				}
				
			}
			
			if(null != form){
				form.setCurrentsubs(currSubList.toArray(new String[currSubList.size()]));
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
	public List<MagazinesDTO> jsSubscriptionDTOToJobSeekerMagazines(List<JobSeekerSubscriptionsDTO> 
		currentSubsList,JobSeekerSubscriptionForm form,List<MagazinesDTO> listMagaziness){
		List<String> currMagList = new ArrayList<String>();
		List<MagazinesDTO> selMagList = new ArrayList<MagazinesDTO>();
		if (null != currentSubsList) {
			for(JobSeekerSubscriptionsDTO dto :currentSubsList){
				
				if(null != listMagaziness){
					for(MagazinesDTO subdto :listMagaziness){
						
						if(dto.getLookUpId().equals(subdto.getMagazineId())){
							currMagList.add(dto.getLookUpId().toString());
							selMagList.add(subdto);
						}
					}
				}				
			}
			
			if(null != form){
				form.setCurrentmagazines(currMagList.toArray(new String[currMagList.size()]));
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
	public List<JobAlertsDTO> jsSubscriptionDTOToJobSeekerAlerts(List<JobSeekerSubscriptionsDTO> 
		currentSubsList,JobSeekerSubscriptionForm form,List<JobAlertsDTO> listAlerts){
		List<String> currAlrtList = new ArrayList<String>();
		List<JobAlertsDTO> currentSelectedSubsList = new ArrayList<JobAlertsDTO>();
		if (null != currentSubsList) {
			for(JobSeekerSubscriptionsDTO dto :currentSubsList){
				if(null != listAlerts){
					for(JobAlertsDTO subdto :listAlerts){
						
						if(dto.getLookUpId().equals(subdto.getAlertId())){
							currAlrtList.add(dto.getLookUpId().toString());
							currentSelectedSubsList.add(subdto);
						}
					}
				}				
			}
			
			if(null != form){
				form.setCurrentJobAlerts(currAlrtList.toArray(new String[currAlrtList.size()]));
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
	public List<JobSeekerSubscriptionsDTO> jsSubscriptionFormToJobSeekerSubsDTO(JobSeekerSubscriptionForm form){
		List<JobSeekerSubscriptionsDTO> selectedSubsList = new ArrayList<JobSeekerSubscriptionsDTO>();
		if (null != form.getCurrentsubs()) {
			for(String selSubscription : form.getCurrentsubs()){
				JobSeekerSubscriptionsDTO dto = new JobSeekerSubscriptionsDTO();
				dto.setAlertValue("");
				dto.setLookUpId(selSubscription);
				dto.setUserId(form.getUserId());
				selectedSubsList.add(dto);
			}
		}
		
		if (null != form.getCurrentsubs()) {
			for(String selSubscription : form.getCurrentmagazines()){
				JobSeekerSubscriptionsDTO dto = new JobSeekerSubscriptionsDTO();
				dto.setAlertValue("");
				dto.setLookUpId(selSubscription);
				dto.setUserId(form.getUserId());
				selectedSubsList.add(dto);
			}
		}
		
		if (null != form.getCurrentsubs()) {
			for(String selSubscription : form.getCurrentJobAlerts()){
				JobSeekerSubscriptionsDTO dto = new JobSeekerSubscriptionsDTO();
				dto.setAlertValue("");
				dto.setLookUpId(selSubscription);
				dto.setUserId(form.getUserId());
				selectedSubsList.add(dto);
			}
		}
		
		return selectedSubsList;
	}
	
}