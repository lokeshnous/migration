/**
 * 
 */
package com.advanceweb.afc.jb.common;


/**
 * @author anilm
 *
 */
public class ResumePackageDTO {
	
	private int packageId;
	private String packageType;
	private String packageName;
	private int netsuiteId;
	private int duration;
	private int active;
	private float priceAmt;
	private float discount;
	private int quantity;
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getNetsuiteId() {
		return netsuiteId;
	}
	public void setNetsuiteId(int netsuiteId) {
		this.netsuiteId = netsuiteId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public float getPriceAmt() {
		return priceAmt;
	}
	public void setPriceAmt(float priceAmt) {
		this.priceAmt = priceAmt;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
