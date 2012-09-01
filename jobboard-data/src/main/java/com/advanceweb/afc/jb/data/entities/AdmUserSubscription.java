package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
 * The persistent class for the adm_user_subscription database table.
 * 
 */
@Entity
@Table(name="adm_user_subscription")
public class AdmUserSubscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdmUserSubscriptionPK subscriptionPK;

	private int active;

	@Column(name="create_dt")
    private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	private String info;

	//bi-directional many-to-one association to AdmSubscription
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subscription_id", insertable=false, updatable=false)
	private AdmSubscription admSubscription;

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public AdmSubscription getAdmSubscription() {
		return this.admSubscription;
	}

	public void setAdmSubscription(AdmSubscription admSubscription) {
		this.admSubscription = admSubscription;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}
	
	public Date getDeleteDt() {
		return deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	/**
	 * @return the subscriptionPK
	 */
	public AdmUserSubscriptionPK getSubscriptionPK() {
		return subscriptionPK;
	}

	/**
	 * @param subscriptionPK the subscriptionPK to set
	 */
	public void setSubscriptionPK(AdmUserSubscriptionPK subscriptionPK) {
		this.subscriptionPK = subscriptionPK;
	}
	
	
}