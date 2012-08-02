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
public class MerUserAlerts implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_id")
	private int alertId;

    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="created_date")
	private Date created_date;

    @Column(name="alert_value")
	private String alertvalue;
    
    @Column(name="user_id")
	private int userid;
    
    @Column(name="lookup_id")
	private int lookupid;
    

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getAlertvalue() {
		return alertvalue;
	}

	public void setAlertvalue(String alertvalue) {
		this.alertvalue = alertvalue;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getLookupid() {
		return lookupid;
	}

	public void setLookupid(int lookupid) {
		this.lookupid = lookupid;
	}
    
    
}