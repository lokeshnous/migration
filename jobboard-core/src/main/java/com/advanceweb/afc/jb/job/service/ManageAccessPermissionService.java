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
	public UserDTO createJobOwner(EmployerProfileDTO profileDTO,int facilityIdParent,int userId) throws JobBoardServiceException;

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteJobOwner(int profileId) throws JobBoardServiceException;

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean updateJobOwner(List<ManageAccessPermissionDTO> accessPermissionDTO) throws JobBoardServiceException;
	/**
	 * 
	 * @param facilityId
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public List<ManageAccessPermissionDTO> getJobOwnerList(int facilityId, int userId) throws JobBoardServiceException;
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws JobBoardServiceException
	 */
	public MerUser getUserListByEmail(String email) throws JobBoardServiceException;
	
	

}
