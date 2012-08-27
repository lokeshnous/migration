package com.advanceweb.afc.jb.common;


/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: 02 Aug, 2012
 * @Purpose: This class is the DTO for the attributes of the jp_location table.
 */
public class LocationDTO {
	
	
	private String city;
	
	private String state;
	
	private String postcode;
	
	private float latitude;
	
	private float longitude;
	
	private String country;
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


}
