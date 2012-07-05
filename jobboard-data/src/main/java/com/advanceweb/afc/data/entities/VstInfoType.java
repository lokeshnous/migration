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
 * The persistent class for the vst_info_type database table.
 * 
 */
@Entity
@Table(name = "vst_info_type")
public class VstInfoType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "info_type_id", insertable = false, updatable = false)
	private int infoTypeId;

	private String name;

	// bi-directional many-to-one association to VstSessionEventInfo
	@OneToMany(mappedBy = "vstInfoType")
	private List<VstSessionEventInfo> vstSessionEventInfos;

	public VstInfoType() {
	}

	public String getDescription() {
		return description;
	}

	public int getInfoTypeId() {
		return infoTypeId;
	}

	public String getName() {
		return name;
	}

	public List<VstSessionEventInfo> getVstSessionEventInfos() {
		return vstSessionEventInfos;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInfoTypeId(int infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVstSessionEventInfos(
			List<VstSessionEventInfo> vstSessionEventInfos) {
		this.vstSessionEventInfos = vstSessionEventInfos;
	}

}