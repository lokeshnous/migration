package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the mer_profile_attrib database table.
 * 
 */
@Entity
@Table(name="mer_profile_attrib")
public class MerProfileAttrib implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_attrib_id")
	private int profileAttribId;

	@Column(name="create_dt")
	private Timestamp createDt;

	private String description;

	@Column(name="form_type")
	private String formType;

	private String name;
	

	//bi-directional many-to-one association to MerProfileAttribList
	@OneToMany(mappedBy="merProfileAttrib")
	private List<MerProfileAttribList> merProfileAttribLists;

	//bi-directional many-to-one association to MerUserProfile
	@OneToMany(mappedBy="merProfileAttrib")
	private List<MerUserProfile> merUserProfiles;

	public int getProfileAttribId() {
		return this.profileAttribId;
	}

	public void setProfileAttribId(int profileAttribId) {
		this.profileAttribId = profileAttribId;
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

	public List<MerProfileAttribList> getMerProfileAttribLists() {
		return this.merProfileAttribLists;
	}

	public void setMerProfileAttribLists(List<MerProfileAttribList> merProfileAttribLists) {
		this.merProfileAttribLists = merProfileAttribLists;
	}
	
	public List<MerUserProfile> getMerUserProfiles() {
		return this.merUserProfiles;
	}

	public void setMerUserProfiles(List<MerUserProfile> merUserProfiles) {
		this.merUserProfiles = merUserProfiles;
	}
	
}