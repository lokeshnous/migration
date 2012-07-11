
package com.advanceweb.afc.jb.common;

import java.io.Serializable;
/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act as a DTO for the Dropdown of Radius field in Jobseekers Advance Search
 */
public class RadiusDTO implements Serializable{
	private String radiusId;
	private String radiusValue;
	
	
	/**
	 * @return the radiusId
	 */
	public String getRadiusId() {
		return radiusId;
	}
	/**
	 * @param radiusId the radiusId to set
	 */
	public void setRadiusId(String radiusId) {
		this.radiusId = radiusId;
	}
	/**
	 * @return the radiusValue
	 */
	public String getRadiusValue() {
		return radiusValue;
	}
	/**
	 * @param radiusValue the radiusValue to set
	 */
	public void setRadiusValue(String radiusValue) {
		this.radiusValue = radiusValue;
	}
	
	
}
