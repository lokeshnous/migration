package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the res_resume_attrib database table.
 * 
 */
@Entity
@Table(name="res_resume_attrib")
public class ResResumeAttrib implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="resume_attrib_id")
	private int resumeAttribId;

	@Column(name="create_dt")
	private Timestamp createDt;

	private String description;

	@Column(name="form_type")
	private String formType;

	private String name;

	//bi-directional many-to-one association to ResResumeAttribList
	@OneToMany(mappedBy="resResumeAttrib",fetch=FetchType.EAGER)
	private List<ResResumeAttribList> resResumeAttribLists;

	//bi-directional many-to-one association to ResResumeProfile
	@OneToMany(mappedBy="resResumeAttrib")
	private List<ResResumeProfile> resResumeProfiles;

	public int getResumeAttribId() {
		return this.resumeAttribId;
	}

	public void setResumeAttribId(int resumeAttribId) {
		this.resumeAttribId = resumeAttribId;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormType() {
		return this.formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResResumeAttribList> getResResumeAttribLists() {
		return this.resResumeAttribLists;
	}

	public void setResResumeAttribLists(List<ResResumeAttribList> resResumeAttribLists) {
		this.resResumeAttribLists = resResumeAttribLists;
	}
	
	public List<ResResumeProfile> getResResumeProfiles() {
		return this.resResumeProfiles;
	}

	public void setResResumeProfiles(List<ResResumeProfile> resResumeProfiles) {
		this.resResumeProfiles = resResumeProfiles;
	}
	
}