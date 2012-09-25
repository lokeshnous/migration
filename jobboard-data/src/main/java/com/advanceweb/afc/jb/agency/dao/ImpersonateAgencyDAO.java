package com.advanceweb.afc.jb.agency.dao;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;

public interface ImpersonateAgencyDAO {
	//boolean saveEmployerDetails(AccountProfileDTO dto);

	boolean deleteAssocEmployer(String facilityId, int userId);

	List<FacilityDTO> getAssocEmployerNames(int userId, int agencyFacilityId);

	boolean addEmployer(AccountProfileDTO accountDto, int agencyFacilityId,
			int userId);
	

	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */
	
	List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID);
	
	/**
	 * @Author :Srikanth K
	 * @Purpose:Get complete Employers list
	 * @Created:Jul 10, 2012
	 * @Return :List of employers in DB
	 * 
	 */
	List<FacilityDTO> getEmployerNamesList(String employerName);

	Map<String, Object>  getEmployerDetails(int facilityId);
}
