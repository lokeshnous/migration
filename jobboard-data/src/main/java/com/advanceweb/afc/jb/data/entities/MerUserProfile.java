package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the mer_user_profile database table.
 * 
 */
@Entity
@Table(name="mer_user_profile")
public class MerUserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MerUserProfilePK id;

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
    @ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private MerUser merUser;

	//bi-directional many-to-one association to MerProfileAttrib
    @ManyToOne
	@JoinColumn(name="profile_attrib_id", insertable = false, updatable = false)
	private MerProfileAttrib merProfileAttrib;

    public MerUserProfile() {
    }

	public MerUserProfilePK getId() {
		return this.id;
	}

	public void setId(MerUserProfilePK id) {
		this.id = id;
	}
	
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
	
}