package com.advanceweb.afc.jb.employer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.dao.FacilityDAO;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserDao;

@Service("facilityService")
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private FacilityDAO facilityDAO;
	@Autowired
	private UserDao userDao;

	/**
	 * This method is to get the facilityId for logged in user
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public EmployerInfoDTO facilityDetails(int userId) {
		return facilityDAO.facilityDetails(userId);
	}

	@Override
	public FacilityDTO getFacilityByFacilityId(int facilityId) {
		return facilityDAO.getFacilityByFacilityId(facilityId);
	}

	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	public List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardServiceException {
		List<DropDownDTO> downDTOs = new ArrayList<DropDownDTO>();
		try {
			downDTOs = facilityDAO.getFacilityGroup(facilityId);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(
					"Error occured while getting facility group from Database"
							+ e);
		}
		return downDTOs;
	}

	/**
	 * This method is get the metrics details for logged in employer
	 * 
	 * @param facilityId
	 * @return metricsDTO
	 */
	public List<MetricsDTO> getJobPostTotal(int facilityId) {
		return facilityDAO.getJobPostTotal(facilityId);
	}

	/**
	 * This method is used to get all jobs stats for Site – wide average per job posting.
	 * 
	 * @return metricsDTO
	 */
	public MetricsDTO getAllJobStats() {
		return facilityDAO.getAllJobStats();
	}

	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardServiceException
	 */
	public long getEmployerCount() throws JobBoardServiceException {
		long returnVal = 0;
		try {
			returnVal = facilityDAO.getEmployerCount();
		} catch (JobBoardDataException jde) {
			throw new JobBoardServiceException(
					"Error occured while getting the Result from Database"
							+ jde);
		}
		return returnVal;
	}
	@Override
	public int getfacilityUserId(int facilityId) {
		return facilityDAO.getfacilityUserId(facilityId);
	}
	
	@Override
	public List<SchedulerDTO> getAllFacilityList() {
		return facilityDAO.getAllFacilityList();
	}
	@Override
	public UserDTO getUser(String email) {
		return userDao.getUser(email);
	}
	
	/**
	 * The method helps to get main facility. If job owner login then method
	 * retrieves the main facility group. 
	 * 
	 * @param currentFacilityId
	 * @return
	 */
	@Override
	public AdmFacility getParentFacility(int currentFacilityId) {
		return facilityDAO.getParentFacility(currentFacilityId);
	}

	/**
	 * The method returns true if application logged in by job owner otherwise false 
	 * 
	 * @param facilityId
	 * @return
	 */
	public boolean isJobOwner(int facilityId) {
		return facilityDAO.isJobOwner(facilityId);
	}
}
