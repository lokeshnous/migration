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
	private String city;
	private String state;
	private String label;
	private Float latitude;
	private Float longitude;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	@Override
	public int hashCode() {
		if (label == null) {
			return super.hashCode();
		} else {
			return label.hashCode();
		}
	}
}
