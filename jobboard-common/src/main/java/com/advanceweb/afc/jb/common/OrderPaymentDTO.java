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
	
	private String method;
	private String paymentNumber;
	private String paidAmount;
	private String transactionId;
	private String transactionResponse;
	private Date transactionDate;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionResponse() {
		return transactionResponse;
	}
	public void setTransactionResponse(String transactionResponse) {
		this.transactionResponse = transactionResponse;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
		
}
