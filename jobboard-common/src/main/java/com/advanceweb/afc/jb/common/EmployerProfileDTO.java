package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class EmployerProfileDTO extends BaseProfileDTO {

	private long employerId;
	private PackageInformationDTO packageInformationDTO;
	private CompanyProfileDTO comapnyProfileDTO;
	public MetricsDTO m_MetricsDTO;
	public PackageInformationDTO m_PackageInformationDTO;
	public CompanyProfileDTO m_CompanyProfileDTO;

	public EmployerProfileDTO(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}