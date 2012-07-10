package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mer_lookup database table.
 * 
 */
@Entity
@Table(name="mer_lookup")
public class MerLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LOOKUP_ID")
	private int lookupId;

	@Column(name="LOOKUP_CATEGORY")
	private String lookupCategory;

	@Column(name="LOOKUP_DESCRIPTION")
	private String lookupDescription;

	@Column(name="LOOKUP_NAME")
	private String lookupName;

	@Column(name="LOOKUP_STATUS")
	private String lookupStatus;

    public MerLookup() {
    }

	public int getLookupId() {
		return this.lookupId;
	}

	public void setLookupId(int lookupId) {
		this.lookupId = lookupId;
	}

	public String getLookupCategory() {
		return this.lookupCategory;
	}

	public void setLookupCategory(String lookupCategory) {
		this.lookupCategory = lookupCategory;
	}

	public String getLookupDescription() {
		return this.lookupDescription;
	}

	public void setLookupDescription(String lookupDescription) {
		this.lookupDescription = lookupDescription;
	}

	public String getLookupName() {
		return this.lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getLookupStatus() {
		return this.lookupStatus;
	}

	public void setLookupStatus(String lookupStatus) {
		this.lookupStatus = lookupStatus;
	}

}