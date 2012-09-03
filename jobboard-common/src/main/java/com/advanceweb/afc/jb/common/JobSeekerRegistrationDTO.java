package com.advanceweb.afc.jb.common;

import java.util.List;

public class JobSeekerRegistrationDTO extends ProfileDTO {

	private AddressDTO addressDTO;
	private JobSeekerProfileDTO jobSeekerProfileDTO;
	private UserDTO merUserDTO;
	private List<ProfileAttribDTO> attribList;
	private String emailId;
	
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public JobSeekerProfileDTO getJobSeekerProfileDTO() {
		return jobSeekerProfileDTO;
	}

	public UserDTO getMerUserDTO() {
		return merUserDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public void setJobSeekerProfileDTO(JobSeekerProfileDTO jobSeekerProfileDTO) {
		this.jobSeekerProfileDTO = jobSeekerProfileDTO;
	}

	public void setMerUserDTO(UserDTO merUserDTO) {
		this.merUserDTO = merUserDTO;
	}

	public List<ProfileAttribDTO> getAttribList() {
		return attribList;
	}

	public void setAttribList(List<ProfileAttribDTO> attribList) {
		this.attribList = attribList;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
