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
	private Date createdDate;
    
    @Column(name="alert_value")
	private String alertValue;
    
    @Column(name="user_id")
	private int userId;
    
    @Column(name="lookup_id")
	private int lookupid;
    

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date created_date) {
		this.createdDate = created_date;
	}

	public String getAlertvalue() {
		return alertValue;
	}

	public void setAlertValue(String alertvalue) {
		this.alertValue = alertvalue;
	}

	public int getUserid() {
		return userId;
	}

	public void setUserid(int userid) {
		this.userId = userid;
	}

	public int getLookupid() {
		return lookupid;
	}

	public void setLookupid(int lookupid) {
		this.lookupid = lookupid;
	}
    
    
}