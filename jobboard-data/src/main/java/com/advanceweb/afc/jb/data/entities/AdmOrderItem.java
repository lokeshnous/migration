/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the adm_order_payment database table.
 * 
 */
@Entity
@Table(name = "adm_order_item")
public class AdmOrderItem implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The order item id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_item_id")
	private int orderItemId;

	/** The product type. */
	@Column(name = "product_type")
	private String productType;

	/** The product id. */
	@Column(name = "product_id")
	private int productId;

	/** The product name. */
	@Column(name = "product_name")
	private String productName;

	/** The qty ordered. */
	@Column(name = "qty_ordered")
	private int qtyOrdered;

	/** The price. */
	@Column(name = "price")
	private float price;
	
	/** The order status. */
	@Column(name = "order_status")
	private String orderStatus;
	
	/** The item number. */
	@Column(name = "item_number")
	private int itemNumber;

	/** The adm order header. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private AdmOrderHeader admOrderHeader;

	/**
	 * @return the orderItemId
	 */
	public int getOrderItemId() {
		return orderItemId;
	}

	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the qtyOrdered
	 */
	public int getQtyOrdered() {
		return qtyOrdered;
	}

	/**
	 * @param qtyOrdered the qtyOrdered to set
	 */
	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * Gets the item number.
	 *
	 * @return the item number
	 */
	public int getItemNumber() {
		return itemNumber;
	}

	/**
	 * Sets the item number.
	 *
	 * @param itemNumber the new item number
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the admOrderHeader
	 */
	public AdmOrderHeader getAdmOrderHeader() {
		return admOrderHeader;
	}

	/**
	 * @param admOrderHeader the admOrderHeader to set
	 */
	public void setAdmOrderHeader(AdmOrderHeader admOrderHeader) {
		this.admOrderHeader = admOrderHeader;
	}

}
