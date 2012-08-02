package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="jp_job_addon")
public class JpJobAddon implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JpJobAddonPK id;

	private byte active;

	@Column(name="create_dt")
	private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	//bi-directional many-to-one association to JpJob
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id", insertable=false, updatable=false) 
	private JpJob jpJob;

	//bi-directional many-to-one association to JpAddon
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addon_id", insertable=false, updatable=false)
	private JpAddon jpAddon;

    public JpJobAddon() {
    }

	public JpJobAddonPK getId() {
		return this.id;
	}

	public void setId(JpJobAddonPK id) {
		this.id = id;
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

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public JpJob getJpJob() {
		return this.jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
	public JpAddon getJpAddon() {
		return this.jpAddon;
	}

	public void setJpAddon(JpAddon jpAddon) {
		this.jpAddon = jpAddon;
	}
	
}