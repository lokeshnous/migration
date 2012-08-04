package com.advanceweb.afc.jb.common;

import java.util.List;

public class JobSeekerRegistrationDTO extends ProfileDTO {

	private AddressDTO addressDTO;
	private JobSeekerProfileDTO jobSeekerProfileDTO;
	private MerUserDTO merUserDTO;
	private List<MerProfileAttribDTO> attribList;

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public JobSeekerProfileDTO getJobSeekerProfileDTO() {
		return jobSeekerProfileDTO;
	}

	public MerUserDTO getMerUserDTO() {
		return merUserDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public void setJobSeekerProfileDTO(JobSeekerProfileDTO jobSeekerProfileDTO) {
		this.jobSeekerProfileDTO = jobSeekerProfileDTO;
	}

	public void setMerUserDTO(MerUserDTO merUserDTO) {
		this.merUserDTO = merUserDTO;
	}

	public List<MerProfileAttribDTO> getAttribList() {
		return attribList;
	}

	public void setAttribList(List<MerProfileAttribDTO> attribList) {
		this.attribList = attribList;
	}

}
