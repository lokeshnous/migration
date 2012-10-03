package com.advanceweb.afc.jb.agency.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface AgencyDAO {

	/**
	 * This method is used to unlink the Facility from the corresponding Agency
	 * 
	 * @param int facilityId
	 * @return true or false
	 */
	boolean deleteAssocEmployer(int facilityId) throws JobBoardDataException;

	/**
	 * This method is used to get all the linked Facility to the corresponding
	 * Agency based on the Agency facilityId
	 * 
	 * @param int agencyFacilityId
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getLinkedFacilityNames(int agencyFacilityId)
			throws JobBoardDataException;

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
			throws JobBoardDataException;

	/**
	 * This method is used to get the list of the Facility whose name is
	 * matching with the given facility name
	 * 
	 * @param String
	 *            facilityName
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getFacilityNames(String facilityName)
			throws JobBoardDataException;

	/**
	 * This method is used to get the details of the Facility depending on the
	 * facilityId
	 * 
	 * @param int facilityId
	 * @return List<FacilityDTO>
	 */
	FacilityDTO getLinkedFacilityDetails(int facilityId)
			throws JobBoardDataException;
}
