package com.advanceweb.afc.common;

public class JobSeekerRegistrationDTO extends ProfileDTO {

	private AddressDTO addressDTO;
	private JobSeekerProfileDTO jobSeekerProfileDTO;
	private MerUserDTO merUserDTO;

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

}
