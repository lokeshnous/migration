package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the jp_addon database table.
 * 
 */
@Entity
@Table(name="jp_addon")
public class JpAddon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="addon_id")
	private int addonId;

	@Column(name="credit_amt")
	private int creditAmt;

	private String description;

	private String name;

	//bi-directional many-to-one association to JpJobAddon
	@OneToMany(mappedBy="jpAddon")
	private List<JpJobAddon> jpJobAddons;
	
	@ElementCollection
	private Set<JpJobType> jpJobType = new TreeSet<JpJobType>();

	public int getAddonId() {
		return this.addonId;
	}

	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	public int getCreditAmt() {
		return this.creditAmt;
	}

	public void setCreditAmt(int creditAmt) {
		this.creditAmt = creditAmt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JpJobAddon> getJpJobAddons() {
		return this.jpJobAddons;
	}

	public void setJpJobAddons(List<JpJobAddon> jpJobAddons) {
		this.jpJobAddons = jpJobAddons;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "JpAddons")
	public Set<JpJobType> getJpJobType() {
		return jpJobType;
	}

	public void setJpJobType(Set<JpJobType> jpJobType) {
		this.jpJobType = jpJobType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}