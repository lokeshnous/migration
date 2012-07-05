package com.advanceweb.afc.data.registration;


import com.advanceweb.afc.common.AgencyProfileDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:52 PM
 */
public class AgencyRegistrationDAOImpl implements AgencyRegistrationDAO {

	public AgencyRegistrationDAOImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param agencyDTO
	 */
	public boolean createNewAgency(AgencyProfileDTO agencyDTO){
		return false;
	}

	/**
	 * 
	 * @param agencyId
	 */
	public boolean deleteAgency(long agencyId){
		return false;
	}

	/**
	 * 
	 * @param agencyId
	 */
	public AgencyProfileDTO getAgencyDetails(long agencyId){
		return null;
	}

	/**
	 * 
	 * @param agencyDomain
	 */
	public boolean updateAgencyDetails(AgencyProfileDTO agencyDomain){
		return false;
	}

}