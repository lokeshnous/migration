package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserDTO;
/**
 * 
 * @author deviprasadm
 *
 */
public interface ManageAccessPermissionService {
	/**
	 * 
	 * @param profileDTO
	 */
	public UserDTO createJobOwner(EmployerProfileDTO profileDTO,int facilityIdParent,int userId);

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteJobOwner(int profileId);

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean updateJobOwner(List<ManageAccessPermissionDTO> accessPermissionDTO);
	/**
	 * 
	 * @param facilityId
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public List<ManageAccessPermissionDTO> getJobOwnerList(int facilityId, int userId);
	
	

}
