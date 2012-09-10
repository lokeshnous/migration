package com.advanceweb.afc.jb.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.admin.dao.AdminDAO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

@Service("adminService")
public class AdminService implements ProfileRegistration{
	
	@Autowired 
	AdminDAO adminDAO;
	
	public boolean validateEmail(String email) {
		return adminDAO.validateEmail(email);
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
	public UserDTO createUser(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return null;
	}


}
