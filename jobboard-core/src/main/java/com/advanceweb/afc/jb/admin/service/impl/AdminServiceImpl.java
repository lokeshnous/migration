package com.advanceweb.afc.jb.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.admin.dao.AdminDAO;
import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.job.dao.JobPostInventoryDAO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

@Service("adminService")
public class AdminServiceImpl implements ProfileRegistration,
		AdminService {

	@Autowired
	private AdminDAO adminDAO;
	

	@Override
	public boolean validateEmail(String email) {
		return adminDAO.validateEmail(email);
	}

	@Override
	public boolean impersonateUser(AdminDTO adminDTO) {
		return adminDAO.impersonateUser(adminDTO);
	}
	
	@Override
	public boolean validateNetSuitId(int nsId) {
		return adminDAO.validateNetSuitId(nsId);
	}
	
	@Override
	public EmpSearchDTO validateCompName(String empList) {
		return adminDAO.validateCompName(empList);
	}

	@Override
	public EmpSearchDTO getUserIdAndFacilityId(int nsId) {
		return adminDAO.getUserIdAndFacilityId(nsId);
	}
	
	@Override
	public boolean saveModifiedData(
			List<JobPostingInventoryDTO> searchedJobsDTOs) {
		return adminDAO.saveModifiedData(searchedJobsDTOs);
	}
	
	@Override
	public List<EmpSearchDTO> getEmpdataByNetSuiteId(int nsId) {
		return adminDAO.getEmpdataByNetSuiteId(nsId);
	}

	@Override
	public boolean deleteProfile(int profileId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyProfile(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProfileDTO viewProfile(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProfileDTO getProfileAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateProfileAttributes(int jobseekerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateAdminCredentials(String email, String password) {
		return adminDAO.validateAdminCredentials(email, password);
	}

	@Override
	public UserDTO createUser(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEmployerDetails(AccountProfileDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAssocEmployer(String facilityId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEmployer(AccountProfileDTO accountDto,
			int agencyFacilityId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AdmFacility> getAssocEmployerNames(int userId,
			int agencyFacilityId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	public List<JobPostingInventoryDTO> getInventoryDetails(int userId,
			int facilityId) {
		return adminDAO.getInventoryDetails(userId, facilityId);
	}
}
