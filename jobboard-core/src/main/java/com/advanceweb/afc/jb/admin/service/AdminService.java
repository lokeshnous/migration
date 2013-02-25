/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.admin.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @author muralikc
 *
 */
public interface AdminService {
	
	/**
	 * @param adminDTO
	 * @return
	 */
	boolean impersonateUser(AdminDTO adminDTO);
	
	/**
	 * @param email
	 * @param password
	 * @return
	 */
	boolean validateAdminCredentials(String email, String password);
	
	/**
	 * @param nsId
	 * @return
	 */
	boolean validateNetSuitId(int nsId);
	
	/**
	 * @param nsId
	 * @return
	 */
	EmpSearchDTO getUserIdAndFacilityId(int nsId);
	
	/**
	 * @param searchedJobsDTOs
	 * @return
	 */
	boolean saveModifiedData(List<JobPostingInventoryDTO> searchedJobsDTOs);
	
	/**
	 * @param empList
	 * @return
	 */
	EmpSearchDTO validateCompName(String empList);
	
	/**
	 * @param nsId
	 * @return
	 */
	List<EmpSearchDTO> getEmpdataByNetSuiteId(int nsId);
	
	/**
	 * @param userId
	 * @param facilityId
	 * @return
	 */
	List<JobPostingInventoryDTO> getInventoryDetails(int userId,
			int facilityId);
	
	/**
	 * @param nsId
	 * @return
	 */
	boolean saveEditFacilityGroup(EmpSearchDTO dto);
	
	/**
	 * This method will list all the job posting combo which can purchased online 
	 * @param 
	 * @return
	 */
	List<DropDownDTO> listJobPostings();
	
	/**
	 * This method is used to update the job posting inventory of the facility by the admin 
	 * @param facilityId
	 * @param jobTypeId
	 * @param quantity
	 * @return
	 */
	boolean updateJobPostInventory(int facilityId, int jobTypeId, int quantity);
	/**
	 * This method is used to get all the linked Facility to the corresponding
	 * Agency based on the Agency facilityId
	 * 
	 * @param int agencyFacilityId
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getFacilityNames(String employerName)
			throws JobBoardServiceException;
	/**
	 * This method is used to get the details of the Facility depending on the
	 * facilityId
	 * 
	 * @param int facilityId
	 * @return FacilityDTO
	 */
	FacilityDTO getLinkedFacilityDetails(int facilityId)
			throws JobBoardServiceException;

	
}
