package com.advanceweb.afc.jb.common;
import java.io.Serializable;

/**
 * 
 * @author kartikm
 * @version 1.0.0.1
 *
 */
public class AccountProfileDTO implements Serializable{

	/**
	 * serial version id is long.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * name name.
	 */
	private String firstName;
	/**
	 * companyName companyName.
	 */
	private String companyName;
	/**
	 * street street.
	 */
	private String street;
	/**
	 * city city.
	 */
	private String city;
	/**
	 * zipCode zipCode.
	 */
	private String zipCode;	
	/**
	 * email email.
	 */
	private String email;
	/**
	 * country country.
	 */
	private String phone;
	/**
	 * state state
	 */
	private String state;
	/**
	 * country country
	 */
	private String country;
	/**
	 * contactType is BILLING or PRIMARY
	 */
	private String contactType;
	/**
	 * facilityId facilityId 111,112,etc.
	 */
	private int facilityId;
	/**
	 * facility contact id 1,2 ,3....
	 */	
	private int facilityContactId;
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public int getFacilityContactId() {
		return facilityContactId;
	}
	public void setFacilityContactId(int facilityContactId) {
		this.facilityContactId = facilityContactId;
	}
	
	
	
}
