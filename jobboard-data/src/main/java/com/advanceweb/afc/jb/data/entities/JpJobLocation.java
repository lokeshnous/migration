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
	private JpJobLocationPK locationPK;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="hide_city")
	private int hideCity;

	@Column(name="hide_country")
	private int hideCountry;

	@Column(name="hide_postcode")
	private int hidePostcode;

	@Column(name="hide_state")
	private int hideState;

	//bi-directional many-to-one association to JpJob
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id", insertable=false, updatable=false)
	private JpJob jpJob;

	//bi-directional many-to-one association to JpLocation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id", insertable=false, updatable=false)
	private JpLocation jpLocation;

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public int getHideCity() {
		return this.hideCity;
	}

	public void setHideCity(int hideCity) {
		this.hideCity = hideCity;
	}

	public int getHideCountry() {
		return this.hideCountry;
	}

	public void setHideCountry(int hideCountry) {
		this.hideCountry = hideCountry;
	}

	public int getHidePostcode() {
		return this.hidePostcode;
	}

	public void setHidePostcode(int hidePostcode) {
		this.hidePostcode = hidePostcode;
	}

	public int getHideState() {
		return this.hideState;
	}

	public void setHideState(int hideState) {
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

	/**
	 * @return the locationPK
	 */
	public JpJobLocationPK getLocationPK() {
		return locationPK;
	}

	/**
	 * @param locationPK the locationPK to set
	 */
	public void setLocationPK(JpJobLocationPK locationPK) {
		this.locationPK = locationPK;
	}
	
}