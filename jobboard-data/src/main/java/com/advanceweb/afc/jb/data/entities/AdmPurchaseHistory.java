package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the adm_purchase_history database table.
 * 
 */
@Entity
@Table(name="adm_purchase_history")
public class AdmPurchaseHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="purchase_history_id")
	private int purchaseHistoryId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="expire_dt")
	private Date expireDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="purchase_dt")
	private Date purchaseDt;

	@Column(name="purchased_credits")
	private BigDecimal purchasedCredits;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to AdmFacility
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	public int getPurchaseHistoryId() {
		return this.purchaseHistoryId;
	}

	public void setPurchaseHistoryId(int purchaseHistoryId) {
		this.purchaseHistoryId = purchaseHistoryId;
	}

	public Date getExpireDt() {
		return this.expireDt;
	}

	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
	}

	public Date getPurchaseDt() {
		return this.purchaseDt;
	}

	public void setPurchaseDt(Date purchaseDt) {
		this.purchaseDt = purchaseDt;
	}

	public BigDecimal getPurchasedCredits() {
		return this.purchasedCredits;
	}

	public void setPurchasedCredits(BigDecimal purchasedCredits) {
		this.purchasedCredits = purchasedCredits;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
	
}