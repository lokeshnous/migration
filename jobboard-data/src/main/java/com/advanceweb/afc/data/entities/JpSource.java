package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the jp_source database table.
 * 
 */
@Entity
@Table(name = "jp_source")
public class JpSource implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte active;

	@Column(name = "create_dt")
	private Timestamp createDt;

	// bi-directional many-to-one association to JpJob
	@OneToMany(mappedBy = "jpSource")
	private List<JpJob> jpJobs;

	// bi-directional many-to-one association to JpJobTemp
	@OneToMany(mappedBy = "jpSource")
	private List<JpJobTemp> jpJobTemps;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "source_id", insertable = false, updatable = false)
	private int sourceId;

	public JpSource() {
	}

	public byte getActive() {
		return active;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public List<JpJob> getJpJobs() {
		return jpJobs;
	}

	public List<JpJobTemp> getJpJobTemps() {
		return jpJobTemps;
	}

	public String getName() {
		return name;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}

	public void setJpJobTemps(List<JpJobTemp> jpJobTemps) {
		this.jpJobTemps = jpJobTemps;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

}