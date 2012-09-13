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
	
	
	private List<SalesItemDTO> salesItemDTOList;
	private String paymentMethod;
	private String cardType;
	private String ccName;
	private String ccNumber;
	private String ccExpiredate;
	private String securiyCode;
	private String ccZipcode;
	private String ccStreet;
	
	
	public List<SalesItemDTO> getSalesItemDTOList() {
		return salesItemDTOList;
	}
	public void setSalesItemDTOList(List<SalesItemDTO> salesItemDTOList) {
		this.salesItemDTOList = salesItemDTOList;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCcName() {
		return ccName;
	}
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getCcExpiredate() {
		return ccExpiredate;
	}
	public void setCcExpiredate(String ccExpiredate) {
		this.ccExpiredate = ccExpiredate;
	}
	public String getCcZipcode() {
		return ccZipcode;
	}
	public void setCcZipcode(String ccZipcode) {
		this.ccZipcode = ccZipcode;
	}
	public String getCcStreet() {
		return ccStreet;
	}
	public void setCcStreet(String ccStreet) {
		this.ccStreet = ccStreet;
	}
	public String getSecuriyCode() {
		return securiyCode;
	}
	public void setSecuriyCode(String securiyCode) {
		this.securiyCode = securiyCode;
	}
}
