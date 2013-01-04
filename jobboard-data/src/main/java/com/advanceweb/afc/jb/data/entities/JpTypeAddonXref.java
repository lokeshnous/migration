package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the jp_type_addon_xref database table.
 * 
 */
@Entity
@Table(name = "jp_type_addon_xref")
public class JpTypeAddonXref implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "type_addon_id")
	private int typeAddonId;

	@Column(name = "job_type_id")
	private int jobTypeId;

	@Column(name = "addon_id")
	private int addonId;

	@Column(name = "combo_id")
	private int comboId;

	public int getTypeAddonId() {
		return typeAddonId;
	}

	public void setTypeAddonId(int typeAddonId) {
		this.typeAddonId = typeAddonId;
	}

	public int getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public int getAddonId() {
		return addonId;
	}

	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

}