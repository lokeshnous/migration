package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
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
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardDataException
	 */
	long getEmployerCount() throws JobBoardDataException;

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
	 * This method is to get facility parent id
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	int getFacilityParent(int facilityId) throws JobBoardDataException;
}
