package com.advanceweb.afc.data.entities;

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
@Table(name = "mer_profile_attrib")
public class MerProfileAttrib implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_dt")
	private Timestamp createDt;

	private String description;

	// bi-directional many-to-one association to MerUserProfile
	@OneToMany(mappedBy = "merProfileAttrib")
	private List<MerUserProfile> merUserProfiles;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_attrib_id", insertable = false, updatable = false)
	private int profileAttribId;

	public MerProfileAttrib() {
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public String getDescription() {
		return description;
	}

	public List<MerUserProfile> getMerUserProfiles() {
		return merUserProfiles;
	}

	public String getName() {
		return name;
	}

	public int getProfileAttribId() {
		return profileAttribId;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMerUserProfiles(List<MerUserProfile> merUserProfiles) {
		this.merUserProfiles = merUserProfiles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProfileAttribId(int profileAttribId) {
		this.profileAttribId = profileAttribId;
	}

}