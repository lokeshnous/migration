package com.advanceweb.afc.jb.data.entities;

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
 * The persistent class for the jp_location database table.
 * 
 */
@Entity
@Table(name = "jp_location")
public class JpLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	private String area;

	private String city;

	@Column(name = "city_alias")
	private String cityAlias;

	private String country;

	@Column(name = "create_dt")
	private Timestamp createDt;

	// bi-directional many-to-one association to JpJobLocation
	@OneToMany(mappedBy = "jpLocation")
	private List<JpJobLocation> jpJobLocations;

	private float latitude;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "location_id", insertable = false, updatable = false)
	private int locationId;

	private float longitude;

	private String postcode;

	private String region;

	private String state;

	@Column(name = "state_fullname")
	private String stateFullname;

	public JpLocation() {
	}

	public String getArea() {
		return area;
	}

	public String getCity() {
		return city;
	}

	public String getCityAlias() {
		return cityAlias;
	}

	public String getCountry() {
		return country;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public List<JpJobLocation> getJpJobLocations() {
		return jpJobLocations;
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

	public String getRegion() {
		return region;
	}

	public String getState() {
		return state;
	}

	public String getStateFullname() {
		return stateFullname;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCityAlias(String cityAlias) {
		this.cityAlias = cityAlias;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setJpJobLocations(List<JpJobLocation> jpJobLocations) {
		this.jpJobLocations = jpJobLocations;
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

	public void setRegion(String region) {
		this.region = region;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStateFullname(String stateFullname) {
		this.stateFullname = stateFullname;
	}

}