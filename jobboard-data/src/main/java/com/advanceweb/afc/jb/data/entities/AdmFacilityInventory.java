/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_facility_inventory database table.
 * 
 */
@Entity
@Table(name="adm_facility_inventory")
public class AdmFacilityInventory {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The inventory id. */
	@Id
	@OrderBy
	@Column(name="inventory_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int inventoryId;
	

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    /** The expire dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="expire_dt")
	private Date expireDt;
    
	/** The order id. */
	@Column(name="order_id")
	private int orderId;
	
	/** The facility id. */
	@Column(name="facility_id")
	private int facilityId;
	//bi-directional many-to-one association to AdmFacility
	/** The adm facility. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id",insertable=false ,updatable=false)
	private AdmFacility admFacility;
	
	//bi-directional many-to-one association to MerProfileAttribList
	/** The adm inventory detail. */
	@OneToOne(fetch=FetchType.LAZY,mappedBy="admFacilityInventory",cascade=CascadeType.ALL)
	private AdmInventoryDetail admInventoryDetail;

	

	/**
	 * Gets the inventory id.
	 *
	 * @return the inventory id
	 */
	public int getInventoryId() {
		return inventoryId;
	}

	/**
	 * Sets the inventory id.
	 *
	 * @param inventoryId the new inventory id
	 */
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the expire dt.
	 *
	 * @return the expire dt
	 */
	public Date getExpireDt() {
		return expireDt;
	}

	/**
	 * Sets the expire dt.
	 *
	 * @param expireDt the new expire dt
	 */
	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
	}

	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the adm facility.
	 *
	 * @return the adm facility
	 */
	public AdmFacility getAdmFacility() {
		return admFacility;
	}

	/**
	 * Sets the adm facility.
	 *
	 * @param admFacility the new adm facility
	 */
	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}

	/**
	 * Gets the adm inventory detail.
	 *
	 * @return the adm inventory detail
	 */
	public AdmInventoryDetail getAdmInventoryDetail() {
		return admInventoryDetail;
	}

	/**
	 * Sets the adm inventory detail.
	 *
	 * @param admInventoryDetail the new adm inventory detail
	 */
	public void setAdmInventoryDetail(AdmInventoryDetail admInventoryDetail) {
		this.admInventoryDetail = admInventoryDetail;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}


	
	
	
}
