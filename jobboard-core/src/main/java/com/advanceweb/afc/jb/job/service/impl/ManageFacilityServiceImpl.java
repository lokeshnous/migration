/**
 * 
 */
package com.advanceweb.afc.jb.job.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ManageFacilityDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.dao.ManageFacilityDAO;
import com.advanceweb.afc.jb.job.service.ManageFacilityService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @author deviprasadm
 *
 */
@Service("ManageFacilityService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class ManageFacilityServiceImpl implements ManageFacilityService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ManageFacilityServiceImpl.class);
	
	/** The manage facility dao. */
	@Autowired
	public ManageFacilityDAO manageFacilityDAO;
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageFacilityService#getFacilityList(int, int)
	 */
	@Override
	public ManageFacilityDTO getFacilityList(int facilityId,boolean isGroup)
			throws JobBoardServiceException {
		ManageFacilityDTO manageFacilityDTO = new ManageFacilityDTO();
		try {
			manageFacilityDTO = manageFacilityDAO.getFacilityList(
					facilityId, isGroup);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while fetching the facility Group..." + jdex);
		}
		return manageFacilityDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageFacilityService#getFacilityListByGroup(int)
	 */
	@Override
	public List<FacilityDTO> getFacilityListByGroup(int facilityId)
			throws JobBoardServiceException {
		List<FacilityDTO> manageFacilityDTO = new ArrayList<FacilityDTO>();
		try {
			manageFacilityDTO = manageFacilityDAO.getFacilityListByGroup(
					facilityId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while fetching the facility..." + jdex);
		}
		return manageFacilityDTO;
	}
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageFacilityService#createFacility(com.advanceweb.afc.jb.common.ManageFacilityDTO, int)
	 */
	@Override
	public void createFacility(ManageFacilityDTO manageFacilityDTO,
			int facilityIdParent) throws JobBoardServiceException {
		try {
			 manageFacilityDAO.createFacility(manageFacilityDTO, facilityIdParent);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while Saving the Facility..." + jdex);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageFacilityService#deleteFacility(int)
	 */
	@Override
	public void deleteFacility(int facilityId) throws JobBoardServiceException {
		try {
			 manageFacilityDAO.deleteFacility(facilityId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while Saving the Facility..." + jdex);
		}
		
	}
}
