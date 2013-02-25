/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;

/**
 * Created EmployerJobPostingDAO
 * 
 * @author sharadk
 * 
 */
public interface ManageFeaturedEmployerProfileDAO {

	/**
	 * Saving Manage Featured Employer Profile
	 * 
	 * @param companyProfileDTO
	 */
	boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO);

	/**
	 * Gets the employer details.
	 * 
	 * @param employerId
	 *            the employer id
	 * @return the employer details
	 */
	CompanyProfileDTO getEmployerDetails(long employerId);

	/**
	 * Gets the employer account details.
	 * 
	 * @param employerId
	 *            the employer id
	 * @return the employer account details
	 */
	List<EmployerProfileDTO> getEmployerAccountDetails(long employerId);

	/**
	 * Gets the employer list.
	 * 
	 * @return the employer list
	 */
	List<CompanyProfileDTO> getEmployerList();

	/**
	 * Gets the employer list.
	 * 
	 * @param startRow
	 *            the start row
	 * @param endRow
	 *            the end row
	 * @return the employer list
	 */
	List<CompanyProfileDTO> getEmployerList(int startRow, int endRow);

	/**
	 * Gets the employer list count.
	 * 
	 * @return the employer list count
	 */
	Long getEmployerListCount();

	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID);

	/**
	 * Gets the parent id.
	 * 
	 * @param facilityId
	 *            the facility id
	 * @return the parent id
	 */
	int getParentId(int facilityId);

	/**
	 * This method returns the facilityId of FACILITY_GROUP if the facility
	 * belongs to FACILITY_GROUP
	 * 
	 * @param facilityId
	 * @return facilityId
	 */
	int getParentGroup(int facilityId);

	/**
	 * This method is to get the facilityId of job
	 * 
	 * @param jobId
	 * @return
	 */
	int getFaciliyId(int jobId);

}
