package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the jp_job_location database table.
 * 
 */
@Entity
@Table(name="jp_job_location")
public class JpJobLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JpJobLocationPK id;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="hide_city")
	private short hideCity;

	@Column(name="hide_country")
	private short hideCountry;

	@Column(name="hide_postcode")
	private short hidePostcode;

	@Column(name="hide_state")
	private short hideState;

	//bi-directional many-to-one association to JpJob
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id", insertable=false, updatable=false)
	private JpJob jpJob;

	//bi-directional many-to-one association to JpLocation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id", insertable=false, updatable=false)
	private JpLocation jpLocation;

    public JpJobLocation() {
    }

	public JpJobLocationPK getId() {
		return this.id;
	}

	public void setId(JpJobLocationPK id) {
		this.id = id;
	}
	
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public short getHideCity() {
		return this.hideCity;
	}

	public void setHideCity(short hideCity) {
		this.hideCity = hideCity;
	}

	public short getHideCountry() {
		return this.hideCountry;
	}

	public void setHideCountry(short hideCountry) {
		this.hideCountry = hideCountry;
	}

	public short getHidePostcode() {
		return this.hidePostcode;
	}

	public void setHidePostcode(short hidePostcode) {
		this.hidePostcode = hidePostcode;
	}

	public short getHideState() {
		return this.hideState;
	}

	public void setHideState(short hideState) {
		this.hideState = hideState;
	}

	public JpJob getJpJob() {
		return this.jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
	public JpLocation getJpLocation() {
		return this.jpLocation;
	}

	public void setJpLocation(JpLocation jpLocation) {
		this.jpLocation = jpLocation;
	}
	
}