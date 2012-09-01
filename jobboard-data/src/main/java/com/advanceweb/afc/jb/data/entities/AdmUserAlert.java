package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adm_user_alert database table.
 * 
 */
@Entity
@Table(name="adm_user_alert")
public class AdmUserAlert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_alert_id")
	private int userAlertId;

	@Column(name="alert_value")
	private String alertValue;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to AdmAlert
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="alert_id")
	private AdmAlert admAlert;

	public int getUserAlertId() {
		return this.userAlertId;
	}

	public void setUserAlertId(int userAlertId) {
		this.userAlertId = userAlertId;
	}

	public String getAlertValue() {
		return this.alertValue;
	}

	public void setAlertValue(String alertValue) {
		this.alertValue = alertValue;
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

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public AdmAlert getAdmAlert() {
		return this.admAlert;
	}

	public void setAdmAlert(AdmAlert admAlert) {
		this.admAlert = admAlert;
	}
	
}