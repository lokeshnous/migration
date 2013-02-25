/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads;

/**
 * Ad Location is used to manage the location where the advertisement is
 * targeted to. The target of an ad si represented by the country, state and
 * city where the ad is targeted to. The ad targeting keyword is represented by
 * the label of the AdLocation. For the OpneX server implementationa at Merion
 * matters, the Ad label is the city name followed by the state name separated
 * by hyphen.
 * 
 * While most of the processors will clear extra white spaces, it is recommended
 * not to put any white space before, after or around the hyphen in a label to
 * ensure the un fsiqueness
 * 
 * @author sukeshnambiar
 * 
 */
public class AdLocation {
	
	/** The city. */
	private String city;
	
	/** The state. */
	private String state;
	
	/** The label. */
	private String label;
	
	/** The latitude. */
	private Float latitude;
	
	/** The longitude. */
	private Float longitude;
	
	/** The country. */
	private String country;

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
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
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
		builder.append(",label=");
		builder.append(label);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj instanceof AdLocation) {
			AdLocation other = (AdLocation) obj;
			return (label != null && label.equals(other.label));
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (label == null) {
			return super.hashCode();
		} else {
			return label.hashCode();
		}
	}
}
