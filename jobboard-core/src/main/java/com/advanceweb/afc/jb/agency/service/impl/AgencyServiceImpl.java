package com.advanceweb.afc.jb.agency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.agency.dao.AgencyDAO;
import com.advanceweb.afc.jb.agency.service.AgencyDelegate;
import com.advanceweb.afc.jb.agency.service.AgencyService;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserDao;

@Service("impersonateAgencyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private UserDao userDAO;

	@Autowired
	private AgencyDelegate agencyDelegate;
	@Autowired
	private AgencyDAO agencyDAO;

	@Override
	public int getfacility(int facilityId) {
		return userDAO.getfacility(facilityId);
	}

	@Override
	public UserDTO getUserByUserId(int userId) {
		return userDAO.getUserByUserId(userId);
	}

	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * 
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */

	public UserDTO getNSCustomerDetails(int nsCustomerID) {
		return agencyDelegate.getNSCustomerDetails(nsCustomerID);
	}

	/**
	 * This method is used to link the Facility to the corresponding Agency
	 * based on the parentFacilityId
	 * 
	 * @param AccountProfileDTO
	 *            accountDto
	 * @param int agencyFacilityId
	 * @return true or false
	 */
	@Override
	public boolean linkFacility(AccountProfileDTO accountDto,
			int agencyFacilityId) throws JobBoardServiceException {
		try {
			return agencyDAO.linkFacility(accountDto, agencyFacilityId);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(e);
		}
	}

	/**
	 * This method is used to get all the linked Facility to the corresponding
	 * Agency based on the Agency facilityId
	 * 
	 * @param int agencyFacilityId
	 * @return List<FacilityDTO>
	 */
	@Override
	public List<FacilityDTO> getLinkedFacilityNames(int agencyFacilityId)
			throws JobBoardServiceException {
		try {
			return agencyDAO.getLinkedFacilityNames(agencyFacilityId);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(e);
		}
	}

	/**
	 * This method is used to unlink the Facility from the corresponding Agency
	 * 
	 * @param int facilityId
	 * @return true or false
	 */
	@Override
	public boolean deleteAssocEmployer(int facilityId)
			throws JobBoardServiceException {
		try {
			return agencyDAO.deleteAssocEmployer(facilityId);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(e);
		}
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
			return agencyDAO.getFacilityNames(employerName);
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
			return agencyDAO.getLinkedFacilityDetails(facilityId);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(e);
		}
	}

}
