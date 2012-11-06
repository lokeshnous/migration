package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

	/**
	 * 
	 * @author deviprasadm
	 * @Purpose: This Interface will act as a DAO interface for the Manage Access Permission
	 */
	public interface ManageAccessPermissionDAO {
	/**
	 * 
	 * @param profileDTO
	 * @param facilityIdP
	 * @param userIdp
	 * @return
	 */
	UserDTO createJobOwner(EmployerProfileDTO profileDTO,
			int facilityIdP, int userIdp) throws JobBoardDataException;

	/**
	 * 
	 * @param jobOwnerId
	 * @return
	 */
	boolean deleteJobOwner(int jobOwnerId)throws JobBoardDataException;

	/**
	 * 
	 * @param accessPermissionDTO
	 * @return
	 */
	boolean updateJobOwner(
			List<ManageAccessPermissionDTO> accessPermissionDTO)throws JobBoardDataException;

	/**
	 * 
	 * @param facilityId
	 * @param userId
	 * @param roleId
	 * @return
	 */
	List<ManageAccessPermissionDTO> getJobOwnerList(int facilityId,
			int userId)throws JobBoardDataException;
	/**
	 * 
	 * @param email
	 * @return
	 */
	UserDTO getUserListByEmail(String email)throws JobBoardDataException;

}
