/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;


/**
 * @author deviprasadm
 * @version 1.0
 */
public class ResumeViewedDTO {
   
	private int resViewedId;
	private int resumeId;
	private int userId;
	private Date createDt;

	private Date deleteDt;

	
	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * @return the resViewedId
	 */
	public int getResViewedId() {
		return resViewedId;
	}

	/**
	 * @param resViewedId the resViewedId to set
	 */
	public void setResViewedId(int resViewedId) {
		this.resViewedId = resViewedId;
	}

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

}
