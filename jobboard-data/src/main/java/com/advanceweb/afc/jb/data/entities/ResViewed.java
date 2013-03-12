package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_user_role database table.
 * 
 */
@Entity
@Table(name="res_viewed")
public class ResViewed implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "res_viewed_id")
	private int resViewedId;
	@Column(name = "Resume_id")
	private int resumeId;
	@Column(name = "user_Id")
	private int userId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_date")
	private Date deleteDt;

	
	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
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

	/**
	 * @return the resViewedId
	 */
	public int getResViewedId() {
		return resViewedId;
	}

	/**
	 * @param resViewedId the resViewedId to set
	 */
	public void setResViewedId(int resViewedId) {
		this.resViewedId = resViewedId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}