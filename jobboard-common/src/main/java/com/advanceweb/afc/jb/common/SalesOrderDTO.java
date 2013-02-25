/**
 * 
 */
package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * @author anilm
 *
 */
public class SalesOrderDTO {
	
	
	/** The sales item dto list. */
	private List<SalesItemDTO> salesItemDTOList;
	
	/** The payment method. */
	private String paymentMethod;
	
	/** The card type. */
	private String cardType;
	
	/** The cc name. */
	private String ccName;
	
	/** The cc number. */
	private String ccNumber;
	
	/** The cc expiredate. */
	private String ccExpiredate;
	
	/** The securiy code. */
	private String securiyCode;
	
	/** The cc zipcode. */
	private String ccZipcode;
	
	/** The cc street. */
	private String ccStreet;
	
	/** The purchase order number. */
	private String purchaseOrderNumber;
	
	
	/**
	 * Gets the sales item dto list.
	 *
	 * @return the sales item dto list
	 */
	public List<SalesItemDTO> getSalesItemDTOList() {
		return salesItemDTOList;
	}
	
	/**
	 * Sets the sales item dto list.
	 *
	 * @param salesItemDTOList the new sales item dto list
	 */
	public void setSalesItemDTOList(List<SalesItemDTO> salesItemDTOList) {
		this.salesItemDTOList = salesItemDTOList;
	}
	
	/**
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * Sets the payment method.
	 *
	 * @param paymentMethod the new payment method
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * Gets the card type.
	 *
	 * @return the card type
	 */
	public String getCardType() {
		return cardType;
	}
	
	/**
	 * Sets the card type.
	 *
	 * @param cardType the new card type
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	/**
	 * Gets the cc name.
	 *
	 * @return the cc name
	 */
	public String getCcName() {
		return ccName;
	}
	
	/**
	 * Sets the cc name.
	 *
	 * @param ccName the new cc name
	 */
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	
	/**
	 * Gets the cc number.
	 *
	 * @return the cc number
	 */
	public String getCcNumber() {
		return ccNumber;
	}
	
	/**
	 * Sets the cc number.
	 *
	 * @param ccNumber the new cc number
	 */
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	
	/**
	 * Gets the cc expiredate.
	 *
	 * @return the cc expiredate
	 */
	public String getCcExpiredate() {
		return ccExpiredate;
	}
	
	/**
	 * Sets the cc expiredate.
	 *
	 * @param ccExpiredate the new cc expiredate
	 */
	public void setCcExpiredate(String ccExpiredate) {
		this.ccExpiredate = ccExpiredate;
	}
	
	/**
	 * Gets the cc zipcode.
	 *
	 * @return the cc zipcode
	 */
	public String getCcZipcode() {
		return ccZipcode;
	}
	
	/**
	 * Sets the cc zipcode.
	 *
	 * @param ccZipcode the new cc zipcode
	 */
	public void setCcZipcode(String ccZipcode) {
		this.ccZipcode = ccZipcode;
	}
	
	/**
	 * Gets the cc street.
	 *
	 * @return the cc street
	 */
	public String getCcStreet() {
		return ccStreet;
	}
	
	/**
	 * Sets the cc street.
	 *
	 * @param ccStreet the new cc street
	 */
	public void setCcStreet(String ccStreet) {
		this.ccStreet = ccStreet;
	}
	
	/**
	 * Gets the securiy code.
	 *
	 * @return the securiy code
	 */
	public String getSecuriyCode() {
		return securiyCode;
	}
	
	/**
	 * Sets the securiy code.
	 *
	 * @param securiyCode the new securiy code
	 */
	public void setSecuriyCode(String securiyCode) {
		this.securiyCode = securiyCode;
	}
	
	/**
	 * Gets the purchase order number.
	 *
	 * @return the purchase order number
	 */
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	
	/**
	 * Sets the purchase order number.
	 *
	 * @param purchaseOrderNumber the new purchase order number
	 */
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
}
