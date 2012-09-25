package com.advanceweb.afc.jb.agency.service;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;

public interface ImpersonateAgencyService {
	
	Map<String, Object> getEmployerDetails(int facilityId);
	List<FacilityDTO> getEmployerNamesList(String employerName);
	int getfacility(int facilityId);
	UserDTO getUserByUserId(int userId);
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	
	int getNSCustomerIDFromAdmFacility(int admFacilityID);
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return Object of UserDTO
	 */
	
	UserDTO getNSCustomerDetails(int nsCustomerID);
	
	
	boolean deleteAssocEmployer(String facilityId, int userId);

	boolean addEmployer(AccountProfileDTO accountDto, int agencyFacilityId,
			int userId);

	List<FacilityDTO> getAssocEmployerNames(int userId, int agencyFacilityId);
}
