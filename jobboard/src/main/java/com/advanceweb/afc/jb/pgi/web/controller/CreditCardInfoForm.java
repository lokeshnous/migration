/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.web.controller;

/**
 * @author muralikc
 * 
 */
public class CreditCardInfoForm {

	// Credit card information
	/** The name. */
	private String name;
	
	/** The credit card no. */
	private String creditCardNo;
	
	/** The card type. */
	private String cardType;
	
	/** The exp month. */
	private String expMonth;
	
	/** The exp year. */
	private String expYear;
	
	/** The securiy code. */
	private String securiyCode;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the creditCardNo
	 */
	public String getCreditCardNo() {
		return creditCardNo;
	}

	/**
	 * @param creditCardNo
	 *            the creditCardNo to set
	 */
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the expMonth
	 */
	public String getExpMonth() {
		return expMonth;
	}

	/**
	 * @param expMonth
	 *            the expMonth to set
	 */
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	/**
	 * @return the expYear
	 */
	public String getExpYear() {
		return expYear;
	}

	/**
	 * @param expYear
	 *            the expYear to set
	 */
	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	/**
	 * @return the securiyCode
	 */
	public String getSecuriyCode() {
		return securiyCode;
	}

	/**
	 * @param securiyCode
	 *            the securiyCode to set
	 */
	public void setSecuriyCode(String securiyCode) {
		this.securiyCode = securiyCode;
	}

}
