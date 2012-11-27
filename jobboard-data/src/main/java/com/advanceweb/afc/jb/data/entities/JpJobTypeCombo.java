package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * The persistent class for the jp_jobtype_combo database table.
 * 
 */
@Entity
@Table(name="jp_jobtype_combo")
public class JpJobTypeCombo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OrderBy
	@Column(name="combo_id")
	private int comboId;
	
	@Column(name="job_type")
	private String jobType;
	
	@Column(name="addons")
	private String addons;
	
	@Column(name="netsuite_id")
	private int netSuiteId;
	
	@Column(name="base_price")
	private float basePrice;
	
	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getAddons() {
		return addons;
	}

	public void setAddons(String addons) {
		this.addons = addons;
	}
	
	public int getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(int netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
