/**
 * 
 */
package com.advanceweb.afc.jb.common;

/**
 * @author anilm
 *
 */
public class SalesItemDTO {
    
	/** The item. */
	private String item;
	
	/** The quantity. */
	private String quantity;
	
	/** The purchase order number. */
	private String purchaseOrderNumber;

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
