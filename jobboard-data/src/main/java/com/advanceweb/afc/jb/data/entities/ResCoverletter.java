package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the res_coverletter database table.
 * 
 */
@Entity
@Table(name="res_coverletter")
public class ResCoverletter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="coverletter_id")
	private int coverletterId;

	private int active;

    @Lob()
	@Column(name="coverletter_text")
	private String coverletterText;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	private String name;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to ResCoverletterPriv
	@OneToMany(mappedBy="resCoverletter")
	private List<ResCoverletterPriv> resCoverletterPrivs;

	public int getCoverletterId() {
		return this.coverletterId;
	}

	public void setCoverletterId(int coverletterId) {
		this.coverletterId = coverletterId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getCoverletterText() {
		return this.coverletterText;
	}

	public void setCoverletterText(String coverletterText) {
		this.coverletterText = coverletterText;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<ResCoverletterPriv> getResCoverletterPrivs() {
		return this.resCoverletterPrivs;
	}

	public void setResCoverletterPrivs(List<ResCoverletterPriv> resCoverletterPrivs) {
		this.resCoverletterPrivs = resCoverletterPrivs;
	}
	
}