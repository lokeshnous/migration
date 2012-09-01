package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vst_info_type database table.
 * 
 */
@Entity
@Table(name="vst_info_type")
public class VstInfoType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="info_type_id")
	private int infoTypeId;

	private String description;

	private String name;

	//bi-directional many-to-one association to VstSessionEventInfo
	@OneToMany(mappedBy="vstInfoType")
	private List<VstSessionEventInfo> vstSessionEventInfos;

	public int getInfoTypeId() {
		return this.infoTypeId;
	}

	public void setInfoTypeId(int infoTypeId) {
		this.infoTypeId = infoTypeId;
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

	public List<VstSessionEventInfo> getVstSessionEventInfos() {
		return this.vstSessionEventInfos;
	}

	public void setVstSessionEventInfos(List<VstSessionEventInfo> vstSessionEventInfos) {
		this.vstSessionEventInfos = vstSessionEventInfos;
	}
	
}