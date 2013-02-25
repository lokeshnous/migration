/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.admin.dao.AdminDAO;
import com.advanceweb.afc.jb.admin.service.AdminService;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.ProfileRegistration;

@Service("adminService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class AdminServiceImpl implements ProfileRegistration,
		AdminService {

	/** The admin dao. */
	@Autowired
	private AdminDAO adminDAO;
	

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#validateEmail(java.lang.String)
	 */
	@Override
	public boolean validateEmail(String email) {
		return adminDAO.validateEmail(email);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#impersonateUser(com.advanceweb.afc.jb.common.AdminDTO)
	 */
	@Override
	public boolean impersonateUser(AdminDTO adminDTO) {
		return adminDAO.impersonateUser(adminDTO);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#validateNetSuitId(int)
	 */
	@Override
	public boolean validateNetSuitId(int nsId) {
		return adminDAO.validateNetSuitId(nsId);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#validateCompName(java.lang.String)
	 */
	@Override
	public EmpSearchDTO validateCompName(String empList) {
		return adminDAO.validateCompName(empList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#getUserIdAndFacilityId(int)
	 */
	@Override
	public EmpSearchDTO getUserIdAndFacilityId(int nsId) {
		return adminDAO.getUserIdAndFacilityId(nsId);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#saveModifiedData(java.util.List)
	 */
	@Override
	public boolean saveModifiedData(
			List<JobPostingInventoryDTO> searchedJobsDTOs) {
		return adminDAO.saveModifiedData(searchedJobsDTOs);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#getEmpdataByNetSuiteId(int)
	 */
	@Override
	public List<EmpSearchDTO> getEmpdataByNetSuiteId(int nsId) {
		return adminDAO.getEmpdataByNetSuiteId(nsId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#saveEditFacilityGroup(com.advanceweb.afc.jb.common.EmpSearchDTO)
	 */
	public boolean saveEditFacilityGroup(EmpSearchDTO dto){
		return adminDAO.saveEditFacilityGroup(dto);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#deleteProfile(int)
	 */
	@Override
	public boolean deleteProfile(int profileId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#modifyProfile(com.advanceweb.afc.jb.common.ProfileDTO)
	 */
	@Override
	public boolean modifyProfile(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#viewProfile(int)
	 */
	@Override
	public ProfileDTO viewProfile(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#changePassword(com.advanceweb.afc.jb.common.ProfileDTO)
	 */
	@Override
	public boolean changePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#validatePassword(com.advanceweb.afc.jb.common.ProfileDTO)
	 */
	@Override
	public boolean validatePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#getProfileAttributes()
	 */
	@Override
	public ProfileDTO getProfileAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#validateProfileAttributes(int)
	 */
	@Override
	public boolean validateProfileAttributes(int jobseekerId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#validateAdminCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateAdminCredentials(String email, String password) {
		return adminDAO.validateAdminCredentials(email, password);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#createUser(com.advanceweb.afc.jb.common.ProfileDTO)
	 */
	@Override
	public UserDTO createUser(ProfileDTO profileDTO) {
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#listJobPostings()
	 */
	@Override
	public List<DropDownDTO> listJobPostings() {
	
		return adminDAO.listJobPostings();
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.service.AdminService#updateJobPostInventory(int, int, int)
	 */
	@Override
	public boolean updateJobPostInventory(int facilityId, int jobTypeId, int quantity) {
		return adminDAO.updateJobPostInventory(facilityId, jobTypeId, quantity);
	}
	
	/**
	 * This method is used to get the list of the Facility whose name is
	 * matching with the given facility name
	 * 
	 * @param String
	 *            facilityName
	 * @return List<FacilityDTO>
	 */
	@Override
	public List<FacilityDTO> getFacilityNames(String employerName)
			throws JobBoardServiceException {
		try {
			return adminDAO.getFacilityNames(employerName);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(e);
		}
	}

	/**
	 * This method is used to get the details of the Facility depending on the
	 * facilityId
	 * 
	 * @param int facilityId
	 * @return List<FacilityDTO>
	 */
	@Override
	public FacilityDTO getLinkedFacilityDetails(int facilityId)
			throws JobBoardServiceException {
		try {
			return adminDAO.getLinkedFacilityDetails(facilityId);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(e);
		}
	}
}
