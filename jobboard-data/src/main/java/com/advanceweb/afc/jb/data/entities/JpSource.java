package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the jp_source database table.
 * 
 */
@Entity
@Table(name="jp_source")
public class JpSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="source_id")
	private int sourceId;

	private byte active;

	@Column(name="create_dt")
	private Timestamp createDt;

	private String name;

	//bi-directional many-to-one association to JpJob
	@OneToMany(mappedBy="jpSource")
	private List<JpJob> jpJobs;

	//bi-directional many-to-one association to JpJobTemp
	@OneToMany(mappedBy="jpSource")
	private List<JpJobTemp> jpJobTemps;

	public int getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JpJob> getJpJobs() {
		return this.jpJobs;
	}

	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}
	
	public List<JpJobTemp> getJpJobTemps() {
		return this.jpJobTemps;
	}

	public void setJpJobTemps(List<JpJobTemp> jpJobTemps) {
		this.jpJobTemps = jpJobTemps;
	}
	
}