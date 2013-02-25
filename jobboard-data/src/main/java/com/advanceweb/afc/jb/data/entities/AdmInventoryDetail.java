/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * The persistent class for the adm_facility_detail database table.
 * 
 */
@Entity
@Table(name="adm_inventory_detail")
public class AdmInventoryDetail {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The inv detail id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OrderBy
	@Column(name="inventory_detail_id")
	private int invDetailId;
	
	/** The product type. */
	@Column(name="product_type")
	private String productType;
	
	/** The product id. */
	@Column(name="product_id")
	private int productId;	
	
	
	/** The availableqty. */
	@Column(name="available_qty")
	private int availableqty;
	
	/** The order qty. */
	@Column(name="order_qty")
	private int orderQty;
	
	//bi-directional many-to-one association to MerProfileAttrib
	/** The adm facility inventory. */
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="inventory_id")
	private AdmFacilityInventory admFacilityInventory;
	
	/**
	 * Gets the inv detail id.
	 *
	 * @return the inv detail id
	 */
	public int getInvDetailId() {
		return invDetailId;
	}

	/**
	 * Sets the inv detail id.
	 *
	 * @param invDetailId the new inv detail id
	 */
	public void setInvDetailId(int invDetailId) {
		this.invDetailId = invDetailId;
	}

	/**
	 * Gets the product type.
	 *
	 * @return the product type
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * Sets the product type.
	 *
	 * @param productType the new product type
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Gets the availableqty.
	 *
	 * @return the availableqty
	 */
	public int getAvailableqty() {
		return availableqty;
	}

	/**
	 * Sets the availableqty.
	 *
	 * @param availableqty the new availableqty
	 */
	public void setAvailableqty(int availableqty) {
		this.availableqty = availableqty;
	}

	/**
	 * Gets the adm facility inventory.
	 *
	 * @return the adm facility inventory
	 */
	public AdmFacilityInventory getAdmFacilityInventory() {
		return admFacilityInventory;
	}

	/**
	 * Sets the adm facility inventory.
	 *
	 * @param admFacilityInventory the new adm facility inventory
	 */
	public void setAdmFacilityInventory(AdmFacilityInventory admFacilityInventory) {
		this.admFacilityInventory = admFacilityInventory;
	}

	/**
	 * Gets the order qty.
	 *
	 * @return the order qty
	 */
	public int getOrderQty() {
		return orderQty;
	}

	/**
	 * Sets the order qty.
	 *
	 * @param orderQty the new order qty
	 */
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}	
	
}
