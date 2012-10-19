package com.advanceweb.afc.jb.agency.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface AgencyService {

	/**
	 * This method is used to get the details of the Facility depending on the
	 * facilityId
	 * 
	 * @param int facilityId
	 * @return FacilityDTO
	 */
	FacilityDTO getLinkedFacilityDetails(int facilityId)
			throws JobBoardServiceException;

	/**
	 * This method is used to get all the linked Facility to the corresponding
	 * Agency based on the Agency facilityId
	 * 
	 * @param int agencyFacilityId
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getFacilityNames(String employerName)
			throws JobBoardServiceException;

	UserDTO getUserByUserId(int userId);

	/**
	 * This method is used to get the net suite customer details based on
	 * nsCustomerId.
	 * 
	 * @param int nsCustomerID
	 * @return UserDTO
	 */

	UserDTO getNSCustomerDetails(int nsCustomerID);

	/**
	 * This method is used to unlink the Facility from the corresponding Agency
	 * 
	 * @param int facilityId
	 * @return true or false
	 */
	boolean deleteAssocEmployer(int facilityId) throws JobBoardServiceException;

	/**
	 * This method is used to link the Facility to the corresponding Agency
	 * based on the parentFacilityId
	 * 
	 * @param AccountProfileDTO
	 *            accountDto
	 * @param int agencyFacilityId
	 * @return true or false
	 */
	boolean linkFacility(AccountProfileDTO accountDto, int agencyFacilityId)
			throws JobBoardServiceException;

	/**
	 * This method is used to get all the Facility linked to the corresponding
	 * Agency based on the Agency facilityId
	 * 
	 * @param int agencyFacilityId
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getLinkedFacilityNames(int agencyFacilityId)
			throws JobBoardServiceException;
}
