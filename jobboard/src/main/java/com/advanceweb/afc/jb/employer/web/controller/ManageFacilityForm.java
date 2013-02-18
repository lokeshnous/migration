/**
 * 
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import com.advanceweb.afc.jb.common.FacilityDTO;

/**
 * @author deviprasadm
 * @Created: Sep 22, 2012
 * @Purpose: This class will act as a Form Bean for the Manage Access Facility
 */
public class ManageFacilityForm {
	private int facilityId;
	private String facilityName;
	private String facilityType;
	private List<FacilityDTO> facilityDTOList;
	private String facilityStreet;
	private String facilityCity;
	private String facilityState;
	private String zipCode;
	private String facilityCountry;
	private String phoneNumber;
	private String templateId;
	private boolean readOnly=false;
	/**
	 * @return the facilityType
	 */
	public String getFacilityType() {
		return facilityType;
	}
	/**
	 * @param facilityType the facilityType to set
	 */
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}
	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}
	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	/**
	 * @return the facilityDTOList
	 */
	public List<FacilityDTO> getFacilityDTOList() {
		return facilityDTOList;
	}
	/**
	 * @param facilityDTOList the facilityDTOList to set
	 */
	public void setFacilityDTOList(List<FacilityDTO> facilityDTOList) {
		this.facilityDTOList = facilityDTOList;
	}
	/**
	 * @return the facilityStreet
	 */
	public String getFacilityStreet() {
		return facilityStreet;
	}
	/**
	 * @param facilityStreet the facilityStreet to set
	 */
	public void setFacilityStreet(String facilityStreet) {
		this.facilityStreet = facilityStreet;
	}
	/**
	 * @return the facilityCity
	 */
	public String getFacilityCity() {
		return facilityCity;
	}
	/**
	 * @param facilityCity the facilityCity to set
	 */
	public void setFacilityCity(String facilityCity) {
		this.facilityCity = facilityCity;
	}
	/**
	 * @return the facilityState
	 */
	public String getFacilityState() {
		return facilityState;
	}
	/**
	 * @param facilityState the facilityState to set
	 */
	public void setFacilityState(String facilityState) {
		this.facilityState = facilityState;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the facilityCountry
	 */
	public String getFacilityCountry() {
		return facilityCountry;
	}
	/**
	 * @param facilityCountry the facilityCountry to set
	 */
	public void setFacilityCountry(String facilityCountry) {
		this.facilityCountry = facilityCountry;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}
	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
