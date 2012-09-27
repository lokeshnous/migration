package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
/**
 * 
 * @author deviprasadm
 *
 */
public interface ManageAccessPermissionService {
	/**
	 * 
	 * @param profileDTO
	 * @throws JobBoardServiceException 
	 */
	UserDTO createJobOwner(EmployerProfileDTO profileDTO,int facilityIdParent,int userId) throws JobBoardServiceException;

	/**
	 * 
	 * @param profileId
	 */
	boolean deleteJobOwner(int profileId) throws JobBoardServiceException;

	/**
	 * 
	 * @param profileDTO
	 */
	boolean updateJobOwner(List<ManageAccessPermissionDTO> accessPermissionDTO) throws JobBoardServiceException;
	/**
	 * 
	 * @param facilityId
	 * @param userId
	 * @param roleId
	 * @return
	 */
	List<ManageAccessPermissionDTO> getJobOwnerList(int facilityId, int userId) throws JobBoardServiceException;
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws JobBoardServiceException
	 */
	MerUser getUserListByEmail(String email) throws JobBoardServiceException;
	
	

}
