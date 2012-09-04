/**
 * This for Account Setting of Employer
 */
package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;

/**
 * @author kartikm
 * @version 1.0
 *
 */
public interface EmloyerRegistartionService {

	
	/**
	 * 
	 * @param userId
	 * @return List
	 */
	List<AdmFacilityContact> getEmployeeData(int userId,
			String contactType);

	/**
	 * 
	 * @param userId
	 * @return userid
	 */
	AdmFacilityContactDTO getEmployeePrimaryKey(int userId,
			String contactType);

	/**
	 * 
	 * @param apd
	 *            apd.
	 * @param admfacilityid
	 *            admfacilityid.
	 */
	void editEmployeeAccount(AccountProfileDTO apd, int admfacilityid,
			int userId, String billing);
	
}
