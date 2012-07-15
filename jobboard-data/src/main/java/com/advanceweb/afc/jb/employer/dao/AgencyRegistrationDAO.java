package com.advanceweb.afc.jb.employer.dao;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.data.domain.Agency;

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
	public boolean createNewAgency(AgencyProfileDTO agency);

	/**
	 * 
	 * @param agencyId
	 */
	public boolean deleteAgency(long agencyId);

	/**
	 * 
	 * @param agencyId
	 */
	public AgencyProfileDTO getAgencyDetails(long agencyId);

	/**
	 * 
	 * @param agency
	 */
	public boolean updateAgencyDetails(AgencyProfileDTO agency);

}