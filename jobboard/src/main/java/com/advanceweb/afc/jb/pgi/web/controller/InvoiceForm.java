package com.advanceweb.afc.jb.pgi.web.controller;

/**
 * This class is added to hold the values of whether the customer is invoice enabled and 
 * purchase order number
 * @author muralikc
 * @version 1.0
 * @created Aug 22, 2012
 */
public class InvoiceForm {

	private boolean invoiceEnabled;
	
	// For invoice information
	private String purchaseOrderNo;
	
	public boolean isInvoiceEnabled() {
		return invoiceEnabled;
	}

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
