package com.advanceweb.afc.jb.resume.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class CertificationsForm {
	private String certificationName;
	private String dateOfReceipt;
	private String instituteName;
	private String summary;
	private String certifyingAuthority;
	private int builderCertId;
	private int itemId;
	private boolean editMode;
	
	
	
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getCertificationName() {
		return certificationName;
	}
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}
	public String getDateOfReceipt() {
		return dateOfReceipt;
	}
	public void setDateOfReceipt(String dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getBuilderCertId() {
		return builderCertId;
	}
	public void setBuilderCertId(int builderCertId) {
		this.builderCertId = builderCertId;
	}
	public String getCertifyingAuthority() {
		return certifyingAuthority;
	}
	public void setCertifyingAuthority(String certifyingAuthority) {
		this.certifyingAuthority = certifyingAuthority;
	}		
	
}
