package com.advanceweb.afc.jb.admin.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;

/**
 * @author muralikc
 *
 */
public interface ImpersonateUserService {
	
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
	
	boolean validateNetSuitId(int nsId);
	
	EmpSearchDTO getUserIdAndFacilityId(int nsId);
	
	boolean saveModifiedData(List<JobPostingInventoryDTO> searchedJobsDTOs);
	
	EmpSearchDTO validateCompName(String empList);

}
