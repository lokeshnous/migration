package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the mer_publication_profession database table.
 * 
 */
@Entity
@Table(name = "mer_publication_profession")
public class MerPublicationProfession {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "publication_profession_id")
	private int pubProfessionId;
	
	@Column(name = "publication_id")
	private int publicationId;

	@Column(name = "subscription_subtype")
	private String subSubType;

	@Column(name = "profession")
	private String profession;

	public int getPubProfessionId() {
		return pubProfessionId;
	}

	public void setPubProfessionId(int pubProfessionId) {
		this.pubProfessionId = pubProfessionId;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public String getSubSubType() {
		return subSubType;
	}

	public void setSubSubType(String subSubType) {
		this.subSubType = subSubType;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
}
