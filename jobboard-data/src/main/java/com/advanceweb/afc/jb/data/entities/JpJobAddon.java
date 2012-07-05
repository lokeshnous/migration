package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the jp_job_addon database table.
 * 
 */
@Entity
@Table(name = "jp_job_addon")
public class JpJobAddon implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte active;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@EmbeddedId
	private JpJobAddonPK id;

	// bi-directional many-to-one association to JpAddon
	@ManyToOne
	@JoinColumn(name = "addon_id", insertable = false, updatable = false)
	private JpAddon jpAddon;

	// bi-directional many-to-one association to JpJob
	@ManyToOne
	@JoinColumn(name = "job_id", insertable = false, updatable = false)
	private JpJob jpJob;

	public JpJobAddon() {
	}

	public byte getActive() {
		return active;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public JpJobAddonPK getId() {
		return id;
	}

	public JpAddon getJpAddon() {
		return jpAddon;
	}

	public JpJob getJpJob() {
		return jpJob;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setId(JpJobAddonPK id) {
		this.id = id;
	}

	public void setJpAddon(JpAddon jpAddon) {
		this.jpAddon = jpAddon;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}

}