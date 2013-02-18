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
	private static final long serialVersionUID = 1L;

	@Column(name = "search_dt")
	@Temporal(TemporalType.DATE)
	private Date searchDate;

	// This column contains the job_id
	@Column(name = "result")
	private int result;

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.result;
		result = prime * result
				+ ((searchDate == null) ? 0 : searchDate.hashCode());
		return result;
	}

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