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

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_item_id")
	private int orderItemId;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "qty_ordered")
	private int qtyOrdered;

	@Column(name = "price")
	private float price;
	
	@Column(name = "order_status")
	private String orderStatus;

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
