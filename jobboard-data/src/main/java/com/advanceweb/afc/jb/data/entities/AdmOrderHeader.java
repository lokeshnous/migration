package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_order_header database table.
 * 
 */
@Entity
@Table(name = "adm_order_header")
public class AdmOrderHeader implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private int orderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "order_total")
	private float orderTotal;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "facility_id")
	private AdmFacility admFacility;

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the orderTotal
	 */
	public float getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal
	 *            the orderTotal to set
	 */
	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the admFacility
	 */
	public AdmFacility getAdmFacility() {
		return admFacility;
	}

	/**
	 * @param admFacility
	 *            the admFacility to set
	 */
	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
}
