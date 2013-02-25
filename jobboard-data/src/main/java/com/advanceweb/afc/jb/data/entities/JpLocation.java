/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The location id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="location_id")
	private int locationId;

	/** The area. */
	private String area;

	/** The city. */
	private String city;

	/** The city alias. */
	@Column(name="city_alias")
	private String cityAlias;

	/** The country. */
	private String country;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The latitude. */
	private Float latitude;

	/** The longitude. */
	private Float longitude;

	/** The postcode. */
	private String postcode;

	/** The region. */
	private String region;

	/** The state. */
	private String state;

	/** The state fullname. */
	@Column(name="state_fullname")
	private String stateFullname;

	//bi-directional many-to-one association to JpJobLocation
	/** The jp job locations. */
	@OneToMany(mappedBy="jpLocation")
	private List<JpJobLocation> jpJobLocations;

	/**
	 * Gets the location id.
	 *
	 * @return the location id
	 */
	public int getLocationId() {
		return this.locationId;
	}

	/**
	 * Sets the location id.
	 *
	 * @param locationId the new location id
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public String getArea() {
		return this.area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the city alias.
	 *
	 * @return the city alias
	 */
	public String getCityAlias() {
		return this.cityAlias;
	}

	/**
	 * Sets the city alias.
	 *
	 * @param cityAlias the new city alias
	 */
	public void setCityAlias(String cityAlias) {
		this.cityAlias = cityAlias;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public Float getLatitude() {
		return this.latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public Float getLongitude() {
		return this.longitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postcode the new postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return this.region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the state fullname.
	 *
	 * @return the state fullname
	 */
	public String getStateFullname() {
		return this.stateFullname;
	}

	/**
	 * Sets the state fullname.
	 *
	 * @param stateFullname the new state fullname
	 */
	public void setStateFullname(String stateFullname) {
		this.stateFullname = stateFullname;
	}

	/**
	 * Gets the jp job locations.
	 *
	 * @return the jp job locations
	 */
	public List<JpJobLocation> getJpJobLocations() {
		return this.jpJobLocations;
	}

	/**
	 * Sets the jp job locations.
	 *
	 * @param jpJobLocations the new jp job locations
	 */
	public void setJpJobLocations(List<JpJobLocation> jpJobLocations) {
		this.jpJobLocations = jpJobLocations;
	}
	
}