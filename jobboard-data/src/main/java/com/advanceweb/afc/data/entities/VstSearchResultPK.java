package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the vst_search_result database table.
 * 
 */
@Embeddable
public class VstSearchResultPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="search_id")
	private int searchId;

	@Column(name="result_seq")
	private int resultSeq;

    public VstSearchResultPK() {
    }
	public int getSearchId() {
		return this.searchId;
	}
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	public int getResultSeq() {
		return this.resultSeq;
	}
	public void setResultSeq(int resultSeq) {
		this.resultSeq = resultSeq;
	}

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
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.searchId;
		hash = hash * prime + this.resultSeq;
		
		return hash;
    }
}