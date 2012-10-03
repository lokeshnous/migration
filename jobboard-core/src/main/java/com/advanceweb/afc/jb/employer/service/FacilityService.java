package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
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
	
	/**
	 * This method is to get facility parent id
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	int getFacilityParent(int facilityId)
			throws JobBoardServiceException;
}