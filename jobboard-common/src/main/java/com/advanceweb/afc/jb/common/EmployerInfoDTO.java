package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 19, 2012
   @Purpose: This class will act as a DTO for getting the information of the Employer 
 */
public class EmployerInfoDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int userId;
	private int facilityId;
	private int roleId;
	private String customerNo;
	private String customerName;
	
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the customerNo
	 */
	public String getCustomerNo() {
		return customerNo;
	}
	/**
	 * @param customerNo the customerNo to set
	 */
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	/**
	 * @return the customerNamel
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerNamel the customerNamel to set
	 */
	public void setCustomerNamel(String customerName) {
		this.customerName = customerName;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
