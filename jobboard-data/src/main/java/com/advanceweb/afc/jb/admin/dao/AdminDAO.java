/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.admin.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface AdminDAO {
	
	/**
	 * @param email
	 * @return
	 */
	boolean validateEmail(String email);
	
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
	 * @param facilityId
	 * @return
	 */
	List<EmpSearchDTO> getEmpdataByNetSuiteId(int nsId);
	
	
	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	List<JobPostingInventoryDTO> getInventoryDetails(int userId, int facilityId);
	
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
	 * This method is used to get the list of the Facility whose name is
	 * matching with the given facility name
	 * 
	 * @param String
	 *            facilityName
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getFacilityNames(String facilityName)
			throws JobBoardDataException;
	/**
	 * This method is used to get the details of the Facility depending on the
	 * facilityId
	 * 
	 * @param int facilityId
	 * @return List<FacilityDTO>
	 */
	FacilityDTO getLinkedFacilityDetails(int facilityId)
			throws JobBoardDataException;
}
