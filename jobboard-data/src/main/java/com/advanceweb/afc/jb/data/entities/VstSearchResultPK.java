/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the vst_search_result database table.
 * 
 */
@Embeddable
public class VstSearchResultPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search id. */
	@Column(name="search_id")
	private int searchId;

	/** The result seq. */
	@Column(name="result_seq")
	private int resultSeq;

	/**
	 * Gets the search id.
	 *
	 * @return the search id
	 */
	public int getSearchId() {
		return this.searchId;
	}
	
	/**
	 * Sets the search id.
	 *
	 * @param searchId the new search id
	 */
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	
	/**
	 * Gets the result seq.
	 *
	 * @return the result seq
	 */
	public int getResultSeq() {
		return this.resultSeq;
	}
	
	/**
	 * Sets the result seq.
	 *
	 * @param resultSeq the new result seq
	 */
	public void setResultSeq(int resultSeq) {
		this.resultSeq = resultSeq;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VstSearchResultPK)) {
			return false;
		}
		VstSearchResultPK castOther = (VstSearchResultPK)other;
		return 
			(this.searchId == castOther.searchId)
			&& (this.resultSeq == castOther.resultSeq);

    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.searchId;
		hash = hash * prime + this.resultSeq;
		
		return hash;
    }
}