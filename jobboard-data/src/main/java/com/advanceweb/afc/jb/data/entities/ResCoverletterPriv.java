package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the res_coverletter_priv database table.
 * 
 */
@Entity
@Table(name="res_coverletter_priv")
public class ResCoverletterPriv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="coverletter_priv_id")
	private int coverletterPrivId;

	private short active;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	@Column(name="create_user_id")
	private int createUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private int deleteUserId;

	//bi-directional many-to-one association to ResPrivacy
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="privacy_id")
	private ResPrivacy resPrivacy;

	//bi-directional many-to-one association to ResCoverletter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="coverletter_id")
	private ResCoverletter resCoverletter;

    public ResCoverletterPriv() {
    }

	public int getCoverletterPrivId() {
		return this.coverletterPrivId;
	}

	public void setCoverletterPrivId(int coverletterPrivId) {
		this.coverletterPrivId = coverletterPrivId;
	}

	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public int getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public ResPrivacy getResPrivacy() {
		return this.resPrivacy;
	}

	public void setResPrivacy(ResPrivacy resPrivacy) {
		this.resPrivacy = resPrivacy;
	}
	
	public ResCoverletter getResCoverletter() {
		return this.resCoverletter;
	}

	public void setResCoverletter(ResCoverletter resCoverletter) {
		this.resCoverletter = resCoverletter;
	}
	
}