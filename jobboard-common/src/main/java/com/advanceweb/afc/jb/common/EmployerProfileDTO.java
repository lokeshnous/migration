package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class EmployerProfileDTO extends BaseProfileDTO {

	private static final long serialVersionUID = 1L;
	private long employerId;
	private PackageInformationDTO packageInformationDTO;
	private MetricsDTO metricsDTO;
	private UserDTO merUserDTO;
	private CompanyProfileDTO compProfileDTO;
	private AddressDTO addDTO;
	private String profileType;
	private List<ProfileAttribDTO> attribList;
	private int rollId;

	/**
	 * @return the attribList
	 */
	public List<ProfileAttribDTO> getAttribList() {
		return attribList;
	}

	/**
	 * @param attribList the attribList to set
	 */
	public void setAttribList(List<ProfileAttribDTO> attribList) {
		this.attribList = attribList;
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

	public UserDTO getMerUserDTO() {
		return merUserDTO;
	}

	public void setMerUserDTO(UserDTO merUserDTO) {
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

	/**
	 * @return the rollId
	 */
	public int getRollId() {
		return rollId;
	}

	/**
	 * @param rollId the rollId to set
	 */
	public void setRollId(int rollId) {
		this.rollId = rollId;
	}
	
}