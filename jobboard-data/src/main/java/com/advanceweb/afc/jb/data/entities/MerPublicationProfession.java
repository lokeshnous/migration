/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The pub profession id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "publication_profession_id")
	private int pubProfessionId;
	
	/** The publication id. */
	@Column(name = "publication_id")
	private int publicationId;

	/** The sub sub type. */
	@Column(name = "subscription_subtype")
	private String subSubType;

	/** The profession. */
	@Column(name = "profession")
	private String profession;

	/**
	 * Gets the pub profession id.
	 *
	 * @return the pub profession id
	 */
	public int getPubProfessionId() {
		return pubProfessionId;
	}

	/**
	 * Sets the pub profession id.
	 *
	 * @param pubProfessionId the new pub profession id
	 */
	public void setPubProfessionId(int pubProfessionId) {
		this.pubProfessionId = pubProfessionId;
	}

	/**
	 * Gets the publication id.
	 *
	 * @return the publication id
	 */
	public int getPublicationId() {
		return publicationId;
	}

	/**
	 * Sets the publication id.
	 *
	 * @param publicationId the new publication id
	 */
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	/**
	 * Gets the sub sub type.
	 *
	 * @return the sub sub type
	 */
	public String getSubSubType() {
		return subSubType;
	}

	/**
	 * Sets the sub sub type.
	 *
	 * @param subSubType the new sub sub type
	 */
	public void setSubSubType(String subSubType) {
		this.subSubType = subSubType;
	}

	/**
	 * Gets the profession.
	 *
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * Sets the profession.
	 *
	 * @param profession the new profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
}
