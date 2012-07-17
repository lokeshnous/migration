package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class EmployerProfileDTO extends BaseProfileDTO {

	private long employerId;
	private PackageInformationDTO packageInformationDTO;
	private MetricsDTO metricsDTO;
	private MerUserDTO merUserDTO;
	private CompanyProfileDTO compProfileDTO;
	private AddressDTO addDTO;
	private String profileType;

	public EmployerProfileDTO(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}

	public PackageInformationDTO getPackageInformationDTO() {
		return packageInformationDTO;
	}

	public void setPackageInformationDTO(PackageInformationDTO packageInformationDTO) {
		this.packageInformationDTO = packageInformationDTO;
	}

	public MetricsDTO getMetricsDTO() {
		return metricsDTO;
	}

	public void setMetricsDTO(MetricsDTO metricsDTO) {
		this.metricsDTO = metricsDTO;
	}

	public MerUserDTO getMerUserDTO() {
		return merUserDTO;
	}

	public void setMerUserDTO(MerUserDTO merUserDTO) {
		this.merUserDTO = merUserDTO;
	}

	public CompanyProfileDTO getCompProfileDTO() {
		return compProfileDTO;
	}

	public void setCompProfileDTO(CompanyProfileDTO compProfileDTO) {
		this.compProfileDTO = compProfileDTO;
	}

	public AddressDTO getAddDTO() {
		return addDTO;
	}

	public void setAddDTO(AddressDTO addDTO) {
		this.addDTO = addDTO;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	
}