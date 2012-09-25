package com.advanceweb.afc.jb.admin.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;

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
	public List<JobPostingInventoryDTO> getInventoryDetails(int userId,
			int facilityId);

}
