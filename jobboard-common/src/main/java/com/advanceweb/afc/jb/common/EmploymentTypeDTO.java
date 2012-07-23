package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act as a DTO for the Dropdown of Employment Type field in Jobseekers Advance Search
 */
public class EmploymentTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int employmentTypeId;
	private String employmentTypeValue;
	
	
	/**
	 * @return the employmentTypeId
	 */
	public int getEmploymentTypeId() {
		return employmentTypeId;
	}
	/**
	 * @param employmentTypeId the employmentTypeId to set
	 */
	public void setEmploymentTypeId(int employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
	}
	/**
	 * @return the employmentTypeValue
	 */
	public String getEmploymentTypeValue() {
		return employmentTypeValue;
	}
	/**
	 * @param employmentTypeValue the employmentTypeValue to set
	 */
	public void setEmploymentTypeValue(String employmentTypeValue) {
		this.employmentTypeValue = employmentTypeValue;
	}
	
	
	
	
}
