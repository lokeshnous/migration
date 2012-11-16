package com.advanceweb.afc.jb.data.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_facility_inventory database table.
 * 
 */
@Entity
@Table(name="adm_facility_inventory")
public class AdmFacilityInventory {
	
	private static final long serialVersionUID = 1L;

	@Id
	@OrderBy
	@Column(name="inventory_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int inventoryId;
	

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="expire_dt")
	private Date expireDt;
    
	@Column(name="order_id")
	private int orderId;
	@Column(name="facility_id")
	private int facilityId;
	//bi-directional many-to-one association to AdmFacility
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id",insertable=false ,updatable=false)
	private AdmFacility admFacility;
	
	//bi-directional many-to-one association to MerProfileAttribList
	@OneToOne(fetch=FetchType.LAZY,mappedBy="admFacilityInventory",cascade=CascadeType.ALL)
	private AdmInventoryDetail admInventoryDetail;

	

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getExpireDt() {
		return expireDt;
	}

	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public AdmFacility getAdmFacility() {
		return admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}

	public AdmInventoryDetail getAdmInventoryDetail() {
		return admInventoryDetail;
	}

	public void setAdmInventoryDetail(AdmInventoryDetail admInventoryDetail) {
		this.admInventoryDetail = admInventoryDetail;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}


	
	
	
}
