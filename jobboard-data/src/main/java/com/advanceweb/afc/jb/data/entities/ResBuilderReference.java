package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the res_builder_references database table.
 * 
 */
@Entity
@Table(name="res_builder_references")
public class ResBuilderReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_reference_id")
	private int builderReferenceId;

	@Column(name="company_name")
	private String companyName;

	@Column(name="contact_name")
	private String contactName;

	private String email;

	@Column(name="is_available")
	private short isAvailable;

	@Column(name="job_title")
	private String jobTitle;

	@Column(name="work_phone")
	private String workPhone;

	//bi-directional many-to-one association to ResBuilderResume
    @ManyToOne
	@JoinColumn(name="builder_resume_id", insertable = false, updatable = false)
	private ResBuilderResume resBuilderResume;

    public ResBuilderReference() {
    }

	public int getBuilderReferenceId() {
		return this.builderReferenceId;
	}

	public void setBuilderReferenceId(int builderReferenceId) {
		this.builderReferenceId = builderReferenceId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(short isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}