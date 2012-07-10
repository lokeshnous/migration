package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mer_user_alerts database table.
 * 
 */
@Entity
@Table(name="mer_user_alerts")
public class MerUserAlert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="alert_id")
	private int alertId;

	@Column(name="alert_status")
	private String alertStatus;

    @Temporal( TemporalType.TIMESTAMP)
	private Date created_date;

	private String email_id;

	private String mobile_no;

    @Temporal( TemporalType.TIMESTAMP)
	private Date remove_date;

	private int userid;

    public MerUserAlert() {
    }

	public int getAlertId() {
		return this.alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public String getAlertStatus() {
		return this.alertStatus;
	}

	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

	public Date getCreated_date() {
		return this.created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getEmail_id() {
		return this.email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getMobile_no() {
		return this.mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Date getRemove_date() {
		return this.remove_date;
	}

	public void setRemove_date(Date remove_date) {
		this.remove_date = remove_date;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}