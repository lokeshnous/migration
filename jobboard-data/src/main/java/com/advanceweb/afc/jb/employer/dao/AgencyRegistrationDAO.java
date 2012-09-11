package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public interface AgencyRegistrationDAO {

	/**
	 * 
	 * @param agency
	 */
	UserDTO createUser(AgencyProfileDTO agency);

	/**
	 * 
	 * @param agencyId
	 */
	boolean deleteAgency(long agencyId);

	/**
	 * 
	 * @param agencyId
	 */
	AgencyProfileDTO getAgencyDetails(long agencyId);

	/**
	 * 
	 * @param agency
	 */
	boolean updateAgencyDetails(AgencyProfileDTO agency);

	/**
	 * @return
	 */
	AgencyProfileDTO getProfileAttributes();

	boolean validateProfileAttributes(int jobseekerId);		

	boolean saveEmployerDetails(AccountProfileDTO dto);

	boolean deleteAssocEmployer(String facilityId, int userId);

	List<AdmFacility> getAssocEmployerNames(int userId, int agencyFacilityId);

	boolean addEmployer(AccountProfileDTO accountDto, int agencyFacilityId,
			int userId);

}