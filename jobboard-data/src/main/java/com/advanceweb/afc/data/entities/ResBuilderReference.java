package com.advanceweb.afc.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the res_builder_references database table.
 * 
 */
@Entity
@Table(name = "res_builder_references")
public class ResBuilderReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "builder_reference_id", insertable = false, updatable = false)
	private int builderReferenceId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "contact_name")
	private String contactName;

	private String email;

	@Column(name = "is_available")
	private short isAvailable;

	@Column(name = "job_title")
	private String jobTitle;

	// bi-directional many-to-one association to ResBuilderResume
	@ManyToOne
	@JoinColumn(name = "builder_resume_id")
	private ResBuilderResume resBuilderResume;

	@Column(name = "work_phone")
	private String workPhone;

	public ResBuilderReference() {
	}

	public int getBuilderReferenceId() {
		return builderReferenceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public String getEmail() {
		return email;
	}

	public short getIsAvailable() {
		return isAvailable;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public ResBuilderResume getResBuilderResume() {
		return resBuilderResume;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setBuilderReferenceId(int builderReferenceId) {
		this.builderReferenceId = builderReferenceId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsAvailable(short isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

}