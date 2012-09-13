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
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the adm_order_payment database table.
 *
 */
@Entity
@Table(name="adm_order_payment")
public class AdmOrderPayment implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_payment_id")
	private int orderPaymentId;
	
	@Column(name = "method")
	private int method;
	
	@Column(name = "payment_number")
	private int paymentNumber;
	
	@Column(name = "paid_amt")
	private float paidAmt;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "transaction_response")
	private String tranResponse;
	
	@Column(name = "transaction_dt")
	private Date transDate;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	private AdmOrderHeader admOrderHeader;

	
	/**
	 * @return the orderPaymentId
	 */
	public int getOrderPaymentId() {
		return orderPaymentId;
	}

	/**
	 * @param orderPaymentId the orderPaymentId to set
	 */
	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}

	/**
	 * @return the method
	 */
	public int getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(int method) {
		this.method = method;
	}

	/**
	 * @return the paymentNumber
	 */
	public int getPaymentNumber() {
		return paymentNumber;
	}

	/**
	 * @param paymentNumber the paymentNumber to set
	 */
	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	/**
	 * @return the paidAmt
	 */
	public float getPaidAmt() {
		return paidAmt;
	}

	/**
	 * @param paidAmt the paidAmt to set
	 */
	public void setPaidAmt(float paidAmt) {
		this.paidAmt = paidAmt;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the tranResponse
	 */
	public String getTranResponse() {
		return tranResponse;
	}

	/**
	 * @param tranResponse the tranResponse to set
	 */
	public void setTranResponse(String tranResponse) {
		this.tranResponse = tranResponse;
	}

	/**
	 * @return the transDate
	 */
	public Date getTransDate() {
		return transDate;
	}

	/**
	 * @param transDate the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
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
