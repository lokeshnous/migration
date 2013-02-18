/**
 * 
 */
package com.advanceweb.afc.jb.common;

/**
 * @author anilm
 *
 */
public class SalesItemDTO {
    
	private String item;
	
	private String quantity;
	
	private String purchaseOrderNumber;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	
}
