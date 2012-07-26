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

	//bi-directional many-to-one association to JpJob
    @ManyToOne
	@JoinColumn(name="job_id", insertable = false, updatable = false)
	private JpJob jpJob;

	//bi-directional many-to-one association to JpLocation
    @ManyToOne
	@JoinColumn(name="location_id", insertable = false, updatable = false)
	private JpLocation jpLocation;
    
	@Column(name="hide_city")
	private int hideCity;
	
	@Column(name="hide_state")
	private int hideState;
	
	@Column(name="hide_postcode")
	private int hidePostCode;
	
	@Column(name="hide_country")
	private int hideCountry;

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

	public int getHideCity() {
		return hideCity;
	}

	public void setHideCity(int hideCity) {
		this.hideCity = hideCity;
	}

	public int getHideState() {
		return hideState;
	}

	public void setHideState(int hideState) {
		this.hideState = hideState;
	}

	public int getHidePostCode() {
		return hidePostCode;
	}

	public void setHidePostCode(int hidePostCode) {
		this.hidePostCode = hidePostCode;
	}

	public int getHideCountry() {
		return hideCountry;
	}

	public void setHideCountry(int hideCountry) {
		this.hideCountry = hideCountry;
	}
	
}