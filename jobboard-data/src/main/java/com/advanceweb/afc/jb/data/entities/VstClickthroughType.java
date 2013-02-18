package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "clickthrough_type_id")
	private int clickthroughTypeId;

	private String description;

	private String name;

	public int getClickthroughTypeId() {
		return this.clickthroughTypeId;
	}

	public void setClickthroughTypeId(int clickthroughTypeId) {
		this.clickthroughTypeId = clickthroughTypeId;
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

}