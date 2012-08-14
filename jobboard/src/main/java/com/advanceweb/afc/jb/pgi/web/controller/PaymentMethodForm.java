package com.advanceweb.afc.jb.pgi.web.controller;

/**
 * @author muralikc
 * 
 */

public class PaymentMethodForm {

	private String paymentMethod;
	
	//For Account address
	public AccountAddressForm accountAddressForm;
	//For Billing address
	public BillingAddressForm billingAddressForm;
	//For Invoice for
	public InvoiceForm invoiceForm;
	//For Credit Card
	public CreditCardInfoForm creditCardInfoForm;

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
}
