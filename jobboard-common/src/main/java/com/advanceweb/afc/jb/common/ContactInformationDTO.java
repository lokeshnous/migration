package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:25 PM
 */
public class ContactInformationDTO {

	private String firstName;
	private String middleNameInitial;
	private String lastName;
	private AddressDTO addressDTO;
	private String email;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleNameInitial() {
		return middleNameInitial;
	}

	public void setMiddleNameInitial(String middleNameInitial) {
		this.middleNameInitial = middleNameInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}