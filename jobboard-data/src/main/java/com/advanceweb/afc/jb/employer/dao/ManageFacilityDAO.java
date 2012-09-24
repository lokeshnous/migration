/**
 * 
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ManageFacilityDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;


/**
 * @author deviprasadm
 *
 */
public interface ManageFacilityDAO {

	/**
	 * 
	 * @param facilityId
	 * @param isGroup 
	 * @param userId
	 * @return
	 */
	public ManageFacilityDTO getFacilityList(int facilityId, boolean isGroup)
			throws JobBoardDataException;

	/**
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardDataException 
	 */
	public List<FacilityDTO> getFacilityListByGroup(int facilityId) throws JobBoardDataException;
	
	/**
	 * 
	 * @param facilityDTO
	 * @param facilityIdParent
	 * @throws JobBoardServiceException
	 */
	public void createFacility(ManageFacilityDTO manageFacilityDTO,int facilityIdParent) throws JobBoardDataException;
	/**
	 * 
	 * @param facilityId
	 * @throws JobBoardDataException
	 */
	public void deleteFacility(int facilityId) throws JobBoardDataException;
}
