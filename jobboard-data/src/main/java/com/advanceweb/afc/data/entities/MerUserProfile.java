package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "mer_user_profile")
public class MerUserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "attrib_value")
	private String attribValue;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@EmbeddedId
	private MerUserProfilePK id;

	// bi-directional many-to-one association to MerProfileAttrib
	@ManyToOne
	@JoinColumn(name = "profile_attrib_id", insertable = false, updatable = false)
	private MerProfileAttrib merProfileAttrib;

	// bi-directional many-to-one association to MerUser
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private MerUser merUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt")
	private Date updateDt;

	public MerUserProfile() {
	}

	public String getAttribValue() {
		return attribValue;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public MerUserProfilePK getId() {
		return id;
	}

	public MerProfileAttrib getMerProfileAttrib() {
		return merProfileAttrib;
	}

	public MerUser getMerUser() {
		return merUser;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setId(MerUserProfilePK id) {
		this.id = id;
	}

	public void setMerProfileAttrib(MerProfileAttrib merProfileAttrib) {
		this.merProfileAttrib = merProfileAttrib;
	}

	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

}