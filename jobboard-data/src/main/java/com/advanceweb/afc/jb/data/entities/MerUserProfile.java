package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the mer_user_profile database table.
 * 
 */
@Entity
@Table(name="mer_user_profile")
public class MerUserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MerUserProfilePK profilePK;

	@Column(name="attrib_value")
	private String attribValue;

	@Column(name="create_dt")
	private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	//bi-directional many-to-one association to MerUser
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private MerUser merUser;

	//bi-directional many-to-one association to MerProfileAttrib
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_attrib_id", insertable=false, updatable=false)
	private MerProfileAttrib merProfileAttrib;

	public String getAttribValue() {
		return this.attribValue;
	}

	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
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

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public MerUser getMerUser() {
		return this.merUser;
	}

	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}
	
	public MerProfileAttrib getMerProfileAttrib() {
		return this.merProfileAttrib;
	}

	public void setMerProfileAttrib(MerProfileAttrib merProfileAttrib) {
		this.merProfileAttrib = merProfileAttrib;
	}

	/**
	 * @return the profilePK
	 */
	public MerUserProfilePK getProfilePK() {
		return profilePK;
	}

	/**
	 * @param profilePK the profilePK to set
	 */
	public void setProfilePK(MerUserProfilePK profilePK) {
		this.profilePK = profilePK;
	}
	
}