package com.advanceweb.afc.jb.employer.web.controller;

/**
 * 
 * @author HarshaV
 * @Version 1.0
 * @Since 29th Aug, 2012
 */

public class TestimonyForm {
	
	private String testimony;

	private int testimonyId;
	private int itemId;
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getTestimonyId() {
		return testimonyId;
	}

	public void setTestimonyId(int testimonyId) {
		this.testimonyId = testimonyId;
	}

	public String getTestimony() {
		return testimony;
	}

	public void setTestimony(String testimony) {
		this.testimony = testimony;
	}
	

}
