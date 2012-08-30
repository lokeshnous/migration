package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class AgencyProfileDTO extends BaseProfileDTO {

	private static final long serialVersionUID = 1L;
	private long agencyId;
	private PackageInformationDTO packageInformationDTO;
	private MetricsDTO metricsDTO;
	private MerUserDTO merUserDTO;
	private CompanyProfileDTO compProfileDTO;
	private AddressDTO addDTO;
	private String profileType;
	private List<MerProfileAttribDTO> attribList;

	/**
	 * @return the attribList
	 */
	public List<MerProfileAttribDTO> getAttribList() {
		return attribList;
	}

	/**
	 * @param attribList
	 *            the attribList to set
	 */
	public void setAttribList(List<MerProfileAttribDTO> attribList) {
		this.attribList = attribList;
	}

	public AgencyProfileDTO() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * @return the agencyId
	 */
	public long getAgencyId() {
		return agencyId;
	}

	/**
	 * @param agencyId
	 *            the agencyId to set
	 */
	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

	public PackageInformationDTO getPackageInformationDTO() {
		return packageInformationDTO;
	}

	public void setPackageInformationDTO(
			PackageInformationDTO packageInformationDTO) {
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