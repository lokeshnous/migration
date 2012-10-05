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
@Table(name="jp_location")
public class JpLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="location_id")
	private int locationId;

	private String area;

	private String city;

	@Column(name="city_alias")
	private String cityAlias;

	private String country;

	@Column(name="create_dt")
	private Timestamp createDt;

	private float latitude;

	private float longitude;

	private String postcode;

	private String region;

	private String state;

	@Column(name="state_fullname")
	private String stateFullname;

	//bi-directional many-to-one association to JpJobLocation
	@OneToMany(mappedBy="jpLocation")
	private List<JpJobLocation> jpJobLocations;

	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityAlias() {
		return this.cityAlias;
	}

	public void setCityAlias(String cityAlias) {
		this.cityAlias = cityAlias;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateFullname() {
		return this.stateFullname;
	}

	public void setStateFullname(String stateFullname) {
		this.stateFullname = stateFullname;
	}

	public List<JpJobLocation> getJpJobLocations() {
		return this.jpJobLocations;
	}

	public void setJpJobLocations(List<JpJobLocation> jpJobLocations) {
		this.jpJobLocations = jpJobLocations;
	}
	
}