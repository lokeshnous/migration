package com.advanceweb.afc.jb.admin.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;

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

}
