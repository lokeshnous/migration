/**
 * 
 */
package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ManageFacilityDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;


/**
 * @author deviprasadm
 *
 */
public interface ManageFacilityService {
	/**
	 * 
	 * @param facilityId
	 * @param userId
	 * @return
	 */
	public ManageFacilityDTO getFacilityList(int facilityId,boolean isGroup) throws JobBoardServiceException;
	/**
	 * 
	 * @param facilityId
	 * @param userId
	 * @return
	 */
	public List<FacilityDTO> getFacilityListByGroup(int facilityId) throws JobBoardServiceException;
	
	/**
	 * 
	 * @param facilityDTO
	 * @param facilityIdParent
	 * @throws JobBoardServiceException
	 */
	public void createFacility(ManageFacilityDTO manageFacilityDTO,int facilityIdParent) throws JobBoardServiceException;
	/**
	 * 
	 * @param facilityId
	 * @throws JobBoardServiceException
	 */
	public void deleteFacility(int facilityId) throws JobBoardServiceException;
	

}
