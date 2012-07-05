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
 * The persistent class for the vst_clickthrough_type database table.
 * 
 */
@Entity
@Table(name = "vst_clickthrough_type")
public class VstClickthroughType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clickthrough_type_id", insertable = false, updatable = false)
	private int clickthroughTypeId;

	private String description;

	private String name;

	// bi-directional many-to-one association to VstClickthrough
	@OneToMany(mappedBy = "vstClickthroughType")
	private List<VstClickthrough> vstClickthroughs;

	public VstClickthroughType() {
	}

	public int getClickthroughTypeId() {
		return clickthroughTypeId;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public List<VstClickthrough> getVstClickthroughs() {
		return vstClickthroughs;
	}

	public void setClickthroughTypeId(int clickthroughTypeId) {
		this.clickthroughTypeId = clickthroughTypeId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVstClickthroughs(List<VstClickthrough> vstClickthroughs) {
		this.vstClickthroughs = vstClickthroughs;
	}

}