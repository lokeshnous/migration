package com.advanceweb.afc.jb.common;

/**
 * @author muralikc
 * 
 */
public class EmpSearchDTO {
	
	private String companyName;
	private int userId;
	private int facilityId;
	private int nsId;

	/**
	 * @return the nsId
	 */
	public int getNsId() {
		return nsId;
	}

	/**
	 * @param nsId the nsId to set
	 */
	public void setNsId(int nsId) {
		this.nsId = nsId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
