package com.advanceweb.afc.jb.data.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the mer_user database table.
 * 
 */
@Entity
@Table(name = "mer_utility")
public class MerUtility {
	private static final long serialVersionUID = 1L;


	@Column(name = "utility_name")
	private String utilityName;

	@Column(name = "utility_category")
	private String utilityCategory;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "utility_id", insertable = false, updatable = false)
	private int utilityId;
	
	@Column(name = "utility_description")
	private String utilityDescription;
	
	@Column(name = "utility_status")
	private boolean utilityStaus;


	public String getUtilityName() {
		return utilityName;
	}

	public void setUtilityName(String utilityName) {
		this.utilityName = utilityName;
	}

	public String getUtilityCategory() {
		return utilityCategory;
	}

	public void setUtilityCategory(String utilityCategory) {
		this.utilityCategory = utilityCategory;
	}

	public int getUtilityId() {
		return utilityId;
	}

	public void setUtilityId(int utilityId) {
		this.utilityId = utilityId;
	}

	public String getUtilityDescription() {
		return utilityDescription;
	}

	public void setUtilityDescription(String utilityDescription) {
		this.utilityDescription = utilityDescription;
	}

	public boolean isUtilityStaus() {
		return utilityStaus;
	}

}
