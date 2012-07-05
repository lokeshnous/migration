package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the mer_location database table.
 * 
 */
@Entity
@Table(name = "mer_location")
public class MerLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	private String city;

	private String country;

	@Column(name = "create_dt")
	private Timestamp createDt;

	private float latitude;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "location_id", insertable = false, updatable = false)
	private int locationId;

	private float longitude;

	private String postcode;

	private String state;

	public MerLocation() {
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public float getLatitude() {
		return latitude;
	}

	public int getLocationId() {
		return locationId;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getState() {
		return state;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setState(String state) {
		this.state = state;
	}

}