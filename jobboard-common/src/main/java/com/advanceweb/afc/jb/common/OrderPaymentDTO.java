/**
 * 
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * @author anilm
 *
 */
public class OrderPaymentDTO {
	
	/** The method. */
	private String method;
	
	/** The payment number. */
	private String paymentNumber;
	
	/** The paid amount. */
	private String paidAmount;
	
	/** The transaction id. */
	private String transactionId;
	
	/** The transaction response. */
	private String transactionResponse;
	
	/** The transaction date. */
	private Date transactionDate;
	
	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * Gets the payment number.
	 *
	 * @return the payment number
	 */
	public String getPaymentNumber() {
		return paymentNumber;
	}
	
	/**
	 * Sets the payment number.
	 *
	 * @param paymentNumber the new payment number
	 */
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	
	/**
	 * Gets the paid amount.
	 *
	 * @return the paid amount
	 */
	public String getPaidAmount() {
		return paidAmount;
	}
	
	/**
	 * Sets the paid amount.
	 *
	 * @param paidAmount the new paid amount
	 */
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public String getTransactionId() {
		return transactionId;
	}
	
	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId the new transaction id
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	/**
	 * Gets the transaction response.
	 *
	 * @return the transaction response
	 */
	public String getTransactionResponse() {
		return transactionResponse;
	}
	
	/**
	 * Sets the transaction response.
	 *
	 * @param transactionResponse the new transaction response
	 */
	public void setTransactionResponse(String transactionResponse) {
		this.transactionResponse = transactionResponse;
	}
	
	/**
	 * Gets the transaction date.
	 *
	 * @return the transaction date
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	/**
	 * Sets the transaction date.
	 *
	 * @param transactionDate the new transaction date
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
		
}
