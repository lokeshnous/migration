/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.web.controller;

/**
 * This class is added to hold the values of whether the customer is invoice enabled and 
 * purchase order number
 * @author muralikc
 * @version 1.0
 * @created Aug 22, 2012
 */
public class InvoiceForm {

	/** The invoice enabled. */
	private boolean invoiceEnabled;
	
	// For invoice information
	/** The purchase order no. */
	private String purchaseOrderNo;
	
	/**
	 * Checks if is invoice enabled.
	 *
	 * @return true, if is invoice enabled
	 */
	public boolean isInvoiceEnabled() {
		return invoiceEnabled;
	}

	/**
	 * Sets the invoice enabled.
	 *
	 * @param invoiceEnabled the new invoice enabled
	 */
	public void setInvoiceEnabled(boolean invoiceEnabled) {
		this.invoiceEnabled = invoiceEnabled;
	}
	
	/**
	 * @return the purchaseOrderNo
	 */
	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	/**
	 * @param purchaseOrderNo
	 *            the purchaseOrderNo to set
	 */
	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}
}
