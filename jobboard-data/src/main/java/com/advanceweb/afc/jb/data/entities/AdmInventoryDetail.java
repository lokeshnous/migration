package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * The persistent class for the adm_facility_detail database table.
 * 
 */
@Entity
@Table(name="adm_inventory_detail")
public class AdmInventoryDetail {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OrderBy
	@Column(name="inventory_detail_id")
	private int invDetailId;
	
	@Column(name="product_type")
	private String productType;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="package_id")
	private int packageId;
	
	@Column(name="Plan_id")
	private int planId;
	
	@Column(name="available_qty")
	private int availableqty;
	
	@Column(name="Status")
	private String status;
	
	private float qty;
	
	//bi-directional many-to-one association to MerProfileAttrib
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="inventory_id")
	private AdmFacilityInventory admFacilityInventory;

	
	
	public int getInvDetailId() {
		return invDetailId;
	}

	public void setInvDetailId(int invDetailId) {
		this.invDetailId = invDetailId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getAvailableqty() {
		return availableqty;
	}

	public void setAvailableqty(int availableqty) {
		this.availableqty = availableqty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public AdmFacilityInventory getAdmFacilityInventory() {
		return admFacilityInventory;
	}

	public void setAdmFacilityInventory(AdmFacilityInventory admFacilityInventory) {
		this.admFacilityInventory = admFacilityInventory;
	}
	
	
	
}
