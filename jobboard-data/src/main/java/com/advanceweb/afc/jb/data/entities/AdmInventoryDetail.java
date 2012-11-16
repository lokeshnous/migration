package com.advanceweb.afc.jb.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@Column(name="inventory_id")
	private String inventoryId;
	
	@Column(name="product_id")
	private int productId;	
	
	
	@Column(name="available_qty")
	private int availableqty;
	
	@Column(name="order_qty")
	private int orderQty;
	
	@Column(name="product_type")
	private String productType;
	//bi-directional many-to-one association to MerProfileAttrib
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="inventory_id",insertable=false ,updatable=false)
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

	public int getAvailableqty() {
		return availableqty;
	}

	public void setAvailableqty(int availableqty) {
		this.availableqty = availableqty;
	}

	public AdmFacilityInventory getAdmFacilityInventory() {
		return admFacilityInventory;
	}

	public void setAdmFacilityInventory(AdmFacilityInventory admFacilityInventory) {
		this.admFacilityInventory = admFacilityInventory;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * @return the inventoryId
	 */
	public String getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}	
	
}
