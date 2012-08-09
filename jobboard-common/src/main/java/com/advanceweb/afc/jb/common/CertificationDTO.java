package com.advanceweb.afc.jb.common;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class CertificationDTO {
	private String certificationName;
	private String dateOfReceipt;
	private String instituteName;
	private String summary;
	private String certifyingAuthority;
	private int builderCertId;
	
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
