package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the vst_clickthrough_new database table.
 * 
 */
@Entity
@Table(name = "vst_clickthrough_new")
public class VstClickthroughNew implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VstClickthroughNewPK clickthroughNewPK;

	@Column(name = "clickthrough_id")
	private int clickthroughId;

	@Column(name = "clickcount")
	private int clickCount;

	public int getClickthroughId() {
		return clickthroughId;
	}

	public void setClickthroughId(int clickthroughId) {
		this.clickthroughId = clickthroughId;
	}

	public VstClickthroughNewPK getClickthroughNewPK() {
		return clickthroughNewPK;
	}

	public void setClickthroughNewPK(VstClickthroughNewPK clickthroughNewPK) {
		this.clickthroughNewPK = clickthroughNewPK;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

}