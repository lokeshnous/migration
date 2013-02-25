/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.web.controller;

import com.advanceweb.afc.jb.employer.web.controller.PurchaseJobPostForm;
import com.advanceweb.afc.jb.employer.web.controller.PurchaseResumeSearchForm;

/**
 * This class has been created to hold the values of customer id, Account address, Billing address, Invoice info
 * credit card info, purchase job posting info, payment method. 
 * @author muralikc
 * @version 1.0
 * @created Aug 27, 2012
 */
public class PaymentGatewayForm {

	/** The ns customer id. */
	private int nsCustomerId;
	
	/** The payment method. */
	private String paymentMethod;
	
	//For Account address
	/** The account address form. */
	public AccountAddressForm accountAddressForm;
	//For Billing address
	/** The billing address form. */
	public BillingAddressForm billingAddressForm;
	//For Invoice for
	/** The invoice form. */
	public InvoiceForm invoiceForm;
	//For Credit Card
	/** The credit card info form. */
	public CreditCardInfoForm creditCardInfoForm;
	
	/** The purchase type. */
	private String purchaseType;
	
	/** The purchase job post form. */
	private PurchaseJobPostForm purchaseJobPostForm;

	/** The purchase resume search form. */
	private PurchaseResumeSearchForm purchaseResumeSearchForm;
	
	/**
	 * Gets the ns customer id.
	 *
	 * @return the ns customer id
	 */
	public int getNsCustomerId() {
		return nsCustomerId;
	}

	/**
	 * Sets the ns customer id.
	 *
	 * @param nsCustomerId the new ns customer id
	 */
	public void setNsCustomerId(int nsCustomerId) {
		this.nsCustomerId = nsCustomerId;
	}
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod
	 *            the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the accountAddressForm
	 */
	public AccountAddressForm getAccountAddressForm() {
		return accountAddressForm;
	}

	/**
	 * @param accountAddressForm
	 *            the accountAddressForm to set
	 */
	public void setAccountAddressForm(AccountAddressForm accountAddressForm) {
		this.accountAddressForm = accountAddressForm;
	}

	/**
	 * @return the billingAddressForm
	 */
	public BillingAddressForm getBillingAddressForm() {
		return billingAddressForm;
	}

	/**
	 * @param billingAddressForm
	 *            the billingAddressForm to set
	 */
	public void setBillingAddressForm(BillingAddressForm billingAddressForm) {
		this.billingAddressForm = billingAddressForm;
	}

	/**
	 * @return the invoiceForm
	 */
	public InvoiceForm getInvoiceForm() {
		return invoiceForm;
	}

	/**
	 * @param invoiceForm
	 *            the invoiceForm to set
	 */
	public void setInvoiceForm(InvoiceForm invoiceForm) {
		this.invoiceForm = invoiceForm;
	}

	/**
	 * @return the creditCardInfoForm
	 */
	public CreditCardInfoForm getCreditCardInfoForm() {
		return creditCardInfoForm;
	}

	/**
	 * @param creditCardInfoForm
	 *            the creditCardInfoForm to set
	 */
	public void setCreditCardInfoForm(CreditCardInfoForm creditCardInfoForm) {
		this.creditCardInfoForm = creditCardInfoForm;
	}

	/**
	 * @return
	 */
	public PurchaseJobPostForm getPurchaseJobPostForm() {
		return purchaseJobPostForm;
	}

	/**
	 * @param purchaseJobPostForm
	 */
	public void setPurchaseJobPostForm(PurchaseJobPostForm purchaseJobPostForm) {
		this.purchaseJobPostForm = purchaseJobPostForm;
	}

	/**
	 * Gets the purchase resume search form.
	 *
	 * @return the purchase resume search form
	 */
	public PurchaseResumeSearchForm getPurchaseResumeSearchForm() {
		return purchaseResumeSearchForm;
	}

	/**
	 * Sets the purchase resume search form.
	 *
	 * @param purchaseResumeSearchForm the new purchase resume search form
	 */
	public void setPurchaseResumeSearchForm(
			PurchaseResumeSearchForm purchaseResumeSearchForm) {
		this.purchaseResumeSearchForm = purchaseResumeSearchForm;
	}

	/**
	 * Gets the purchase type.
	 *
	 * @return the purchase type
	 */
	public String getPurchaseType() {
		return purchaseType;
	}

	/**
	 * Sets the purchase type.
	 *
	 * @param purchaseType the new purchase type
	 */
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
}
