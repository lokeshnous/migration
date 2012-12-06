package com.advanceweb.afc.jb.common;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: 02 Aug, 2012
 * @Purpose: This class is the DTO for the attributes of the jp_location table.
 */
public class LocationDTO {

	private int id;

	private String city;

	private String state;

	private String postcode;

	private Float latitude;

	private Float longitude;

	private String country;

	private String area;

	private String cityAlias;

	private String stateFullname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCityAlias() {
		return cityAlias;
	}

	public void setCityAlias(String cityAlias) {
		this.cityAlias = cityAlias;
	}

	public String getStateFullName() {
		return stateFullname;
	}

	public void setStateFullName(String stateFullname) {
		this.stateFullname = stateFullname;
	}

}
