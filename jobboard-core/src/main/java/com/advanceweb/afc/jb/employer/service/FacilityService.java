package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 3rd oct 2012
 */

public interface FacilityService {
	/**
	 * This method is to get the facilityId for logged in user
	 * 
	 * @param userId
	 * @return
	 */
	EmployerInfoDTO facilityDetails(int userId);
	
	/**
	 * This method is get the metrics details for logged in employer
	 * 
	 * @param facilityId
	 * @return
	 */
	List<MetricsDTO> getJobPostTotal(int facilityId);
	
	/**
	 * This method is used to get all jobs stats for Site – wide average per job posting.
	 * 
	 * @return
	 */
	MetricsDTO getAllJobStats();
	
	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardServiceException
	 */
	long getEmployerCount() throws JobBoardServiceException;
	
	
	FacilityDTO getFacilityByFacilityId(int facilityId);
	
	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardServiceException;
	
	int getfacilityUserId(int facilityId);
	/**
	 * This method is to get all facility list 
	 * @return List<SchedulerDTO>
	 */
	List<SchedulerDTO> getAllFacilityList();
	UserDTO getUser(String email);
	
	/**
	 * The method helps to get main facility. If job owner login then method
	 * retrieves the main facility group. 
	 * 
	 * @param currentFacilityId
	 * @return
	 */
	AdmFacility getParentFacility(int currentFacilityId);
	
	/**
	 * The method returns true if application logged in by job owner otherwise false 
	 * 
	 * @param facilityId
	 * @return
	 */
	public boolean isJobOwner(int facilityId);
}
