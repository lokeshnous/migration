package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the mer_user database table.
 * 
 */
@Entity
@Table(name = "mer_user")
public class MerUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	private String password;

	// bi-directional many-to-one association to MerUserApplication
	@OneToMany(mappedBy = "merUser")
	private List<MerUserApplication> merUserApplications;

	// bi-directional many-to-one association to MerUserProfile
	@OneToMany(mappedBy = "merUser", cascade = CascadeType.ALL)
	private List<MerUserProfile> merUserProfiles;

	// bi-directional many-to-one association to VstSessioninfo
	@OneToMany(mappedBy = "merUser")
	private List<VstSessioninfo> vstSessioninfos;

	// bi-directional many-to-one association to JpTemplate
	// @OneToMany(mappedBy="merUser")
	// private List<JpTemplate> jpTemplates;

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<MerUserApplication> getMerUserApplications() {
		return this.merUserApplications;
	}

	public void setMerUserApplications(
			List<MerUserApplication> merUserApplications) {
		this.merUserApplications = merUserApplications;
	}

	public List<MerUserProfile> getMerUserProfiles() {
		return this.merUserProfiles;
	}

	public void setMerUserProfiles(List<MerUserProfile> merUserProfiles) {
		this.merUserProfiles = merUserProfiles;
	}

	public List<VstSessioninfo> getVstSessioninfos() {
		return this.vstSessioninfos;
	}

	public void setVstSessioninfos(List<VstSessioninfo> vstSessioninfos) {
		this.vstSessioninfos = vstSessioninfos;
	}

	// public List<JpTemplate> getJpTemplates() {
	// return jpTemplates;
	// }
	//
	// public void setJpTemplates(List<JpTemplate> jpTemplates) {
	// this.jpTemplates = jpTemplates;
	// }

}