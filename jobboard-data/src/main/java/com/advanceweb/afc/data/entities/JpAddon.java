package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the jp_addon database table.
 * 
 */
@Entity
@Table(name = "jp_addon")
public class JpAddon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addon_id", insertable = false, updatable = false)
	private int addonId;

	@Column(name = "credit_amt")
	private int creditAmt;

	private String description;

	// bi-directional many-to-one association to JpJobAddon
	@OneToMany(mappedBy = "jpAddon")
	private List<JpJobAddon> jpJobAddons;

	private String name;

	public JpAddon() {
	}

	public int getAddonId() {
		return addonId;
	}

	public int getCreditAmt() {
		return creditAmt;
	}

	public String getDescription() {
		return description;
	}

	public List<JpJobAddon> getJpJobAddons() {
		return jpJobAddons;
	}

	public String getName() {
		return name;
	}

	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	public void setCreditAmt(int creditAmt) {
		this.creditAmt = creditAmt;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setJpJobAddons(List<JpJobAddon> jpJobAddons) {
		this.jpJobAddons = jpJobAddons;
	}

	public void setName(String name) {
		this.name = name;
	}

}