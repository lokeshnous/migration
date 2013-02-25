/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: 02 Aug, 2012
 * @Purpose: This class is the DTO for the attributes of the jp_location table.
 */
public class LocationDTO {

	/** The id. */
	private int id;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The postcode. */
	private String postcode;

	/** The latitude. */
	private Float latitude;

	/** The longitude. */
	private Float longitude;

	/** The country. */
	private String country;

	/** The area. */
	private String area;

	/** The city alias. */
	private String cityAlias;

	/** The state fullname. */
	private String stateFullname;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
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
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
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
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
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
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public Float getLatitude() {
		return latitude;
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
		return longitude;
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
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
	 * Gets the area.
	 *
	 * @return the area
	 */
	public String getArea() {
		return area;
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
	 * Gets the city alias.
	 *
	 * @return the city alias
	 */
	public String getCityAlias() {
		return cityAlias;
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
	 * Gets the state full name.
	 *
	 * @return the state full name
	 */
	public String getStateFullName() {
		return stateFullname;
	}

	/**
	 * Sets the state full name.
	 *
	 * @param stateFullname the new state full name
	 */
	public void setStateFullName(String stateFullname) {
		this.stateFullname = stateFullname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

}
