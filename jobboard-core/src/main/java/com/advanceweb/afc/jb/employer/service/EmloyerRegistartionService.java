/**
 * This for Account Setting of Employer
 */
package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
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
    public List<AdmFacilityContact> getEmployeeData(int userId,String contactType);
    /**
     * 
     * @param userId
     * @return userid
     */
     public List<AdmFacilityContact> getEmployeePrimaryKey(int userId,String contactType);
    /**
     * 	
     * @param apd apd.
     * @param admfacilityid admfacilityid.
     */
    public void editEmployeeAccount(AccountProfileDTO apd,int admfacilityid);

	
	
}
