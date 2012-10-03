package com.advanceweb.afc.jb.employer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.dao.FacilityDAO;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

@Service("facilityService")
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private FacilityDAO facilityDAO;

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
	 * This method is to get facility parent id
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	public int getFacilityParent(int facilityId)
			throws JobBoardServiceException {
		int facilityParentId = 0;
		try {
			facilityParentId = facilityDAO.getFacilityParent(facilityId);
		} catch (JobBoardDataException e) {
			// TODO Auto-generated catch block
		}
		return facilityParentId;
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
			// TODO Auto-generated catch block
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
}
