package com.advanceweb.afc.common;

import java.util.List;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class AgencyProfileDTO extends BaseProfileDTO {

	private long agencyId;
	private PackageInformationDTO packageInformationDTO;
	private List<EmployerProfileDTO> employerList;
	private CompanyProfileDTO comapnyProfileDTO;
	public CompanyProfileDTO m_CompanyProfileDTO;

	public AgencyProfileDTO(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}