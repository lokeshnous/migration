package com.advanceweb.afc.jb.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.admin.dao.AdminDAO;
import com.advanceweb.afc.jb.admin.service.ImpersonateUserService;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.user.ProfileRegistration;

@Service("adminService")
public class ImpesfonateUserServiceImpl implements ProfileRegistration,
		ImpersonateUserService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public boolean validateEmail(String email) {
		return adminDAO.validateEmail(email);
	}

	@Override
	public boolean impersonateUser(AdminDTO adminDTO) {
		return adminDAO.impersonateUser(adminDTO);
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
	public boolean addEmployer(AccountProfileDTO accountDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AdmFacility> getAssocEmployerNames(int userId) {
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

}
