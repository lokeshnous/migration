package com.advanceweb.afc.jb.pgi.web.controller;

/**
 * @author muralikc
 * 
 */
public class InvoiceForm {

	private boolean invoiceEnabled;
	
	public boolean isInvoiceEnabled() {
		return invoiceEnabled;
	}

	public void setInvoiceEnabled(boolean invoiceEnabled) {
		this.invoiceEnabled = invoiceEnabled;
	}

	// For invoice information
	private String purchaseOrderNo;

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
