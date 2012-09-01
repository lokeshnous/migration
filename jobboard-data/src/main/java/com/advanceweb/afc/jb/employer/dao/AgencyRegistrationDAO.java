package com.advanceweb.afc.jb.employer.dao;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

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
	MerUserDTO createNewAgency(AgencyProfileDTO agency);

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

}