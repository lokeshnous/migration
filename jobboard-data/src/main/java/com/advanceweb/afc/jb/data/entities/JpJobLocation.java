package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the jp_job_location database table.
 * 
 */
@Entity
@Table(name="jp_job_location")
public class JpJobLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JpJobLocationPK locationPK;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="hide_city")
	private Integer hideCity;

	@Column(name="hide_country")
	private Integer hideCountry;

	@Column(name="hide_postcode")
	private Integer hidePostcode;

	@Column(name="hide_state")
	private Integer hideState;

	//bi-directional many-to-one association to JpJob
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id", insertable=false, updatable=false)
	private JpJob jpJob;

	//bi-directional many-to-one association to JpLocation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id", insertable=false, updatable=false)
	private JpLocation jpLocation;

	public JpJobLocationPK getLocationPK() {
		return locationPK;
	}

	public void setLocationPK(JpJobLocationPK locationPK) {
		this.locationPK = locationPK;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Integer getHideCity() {
		return hideCity;
	}

	public void setHideCity(Integer hideCity) {
		this.hideCity = hideCity;
	}

	public Integer getHideCountry() {
		return hideCountry;
	}

	public void setHideCountry(Integer hideCountry) {
		this.hideCountry = hideCountry;
	}

	public Integer getHidePostcode() {
		return hidePostcode;
	}

	public void setHidePostcode(Integer hidePostcode) {
		this.hidePostcode = hidePostcode;
	}

	public Integer getHideState() {
		return hideState;
	}

	public void setHideState(Integer hideState) {
		this.hideState = hideState;
	}

	public JpJob getJpJob() {
		return jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}

	public JpLocation getJpLocation() {
		return jpLocation;
	}

	public void setJpLocation(JpLocation jpLocation) {
		this.jpLocation = jpLocation;
	}

	
	
}