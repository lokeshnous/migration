package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adm_package")
public class AdmPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "package_id")
	private int packageId;

	@Column(name = "package_type")
	private String packageType;
	
	@Column(name = "package_name")
	private String packageName;
	
	@Column(name = "netsuite_id")
	private int netsuiteId;
	
	@Column(name = "Duration")
	private int duration;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "price_amt")
	private float priceAmt;

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
	
}
