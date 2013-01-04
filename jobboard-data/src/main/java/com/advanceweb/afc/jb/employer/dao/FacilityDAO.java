package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 3rd oct 2012
 */

public interface FacilityDAO {
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
	 * @return metricsDTO
	 */
	List<MetricsDTO> getJobPostTotal(int facilityId);

	/**
	/**
	 * This method is used to get all jobs stats for Site – wide average per job posting.
	 * 
	 * @return metricsDTO
	 */
	MetricsDTO getAllJobStats();
	
	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardDataException
	 */
	long getEmployerCount() throws JobBoardDataException;

	/**
	 * @param facilityId
	 * @return
	 */
	FacilityDTO getFacilityByFacilityId(int facilityId);

	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardDataException;

	/**
	 * This method is used to get the user id of the corresponding facility based on th facility id
	 * @param int userId
	 * @return UserDTO
	 * @throws JobBoardDataException
	 */
	int getfacilityUserId(int facilityId);
	
	List<SchedulerDTO> getAllFacilityList();
	
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
