/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the vst_search_result_new database table.
 * 
 */
@Embeddable
public class VstSearchResultNewPK implements Serializable {
	// Default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search date. */
	@Column(name = "search_dt")
	@Temporal(TemporalType.DATE)
	private Date searchDate;

	// This column contains the job_id
	/** The result. */
	@Column(name = "result")
	private int result;

	/**
	 * Gets the search date.
	 *
	 * @return the search date
	 */
	public Date getSearchDate() {
		return searchDate;
	}

	/**
	 * Sets the search date.
	 *
	 * @param searchDate the new search date
	 */
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.result;
		result = prime * result
				+ ((searchDate == null) ? 0 : searchDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VstSearchResultNewPK other = (VstSearchResultNewPK) obj;
		if (result != other.result)
			return false;
		if (searchDate == null) {
			if (other.searchDate != null)
				return false;
		} else if (!searchDate.equals(other.searchDate))
			return false;
		return true;
	}

}