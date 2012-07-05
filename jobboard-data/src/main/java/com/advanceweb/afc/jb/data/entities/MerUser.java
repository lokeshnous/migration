package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	// bi-directional many-to-one association to MerUserApplication
	@OneToMany(mappedBy = "merUser")
	private List<MerUserApplication> merUserApplications;

	// bi-directional many-to-one association to MerUserProfile
	@OneToMany(mappedBy = "merUser")
	private List<MerUserProfile> merUserProfiles;

	private String password;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", insertable = false, updatable = false)
	private int userId;

	// bi-directional many-to-one association to VstSessioninfo
	@OneToMany(mappedBy = "merUser")
	private List<VstSessioninfo> vstSessioninfos;

	public MerUser() {
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<MerUserApplication> getMerUserApplications() {
		return merUserApplications;
	}

	public List<MerUserProfile> getMerUserProfiles() {
		return merUserProfiles;
	}

	public String getPassword() {
		return password;
	}

	public int getUserId() {
		return userId;
	}

	public List<VstSessioninfo> getVstSessioninfos() {
		return vstSessioninfos;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMerUserApplications(
			List<MerUserApplication> merUserApplications) {
		this.merUserApplications = merUserApplications;
	}

	public void setMerUserProfiles(List<MerUserProfile> merUserProfiles) {
		this.merUserProfiles = merUserProfiles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setVstSessioninfos(List<VstSessioninfo> vstSessioninfos) {
		this.vstSessioninfos = vstSessioninfos;
	}

}