/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the res_degree_edu database table.
 * 
 */
@Entity
@Table(name="res_degree_edu")
public class ResDegreeEdu implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The degree edu id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="degree_edu_id")
	private int degreeEduId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to ResBuilderEdu
	/** The res builder edus. */
	@OneToMany(mappedBy="resDegreeEdu")
	private List<ResBuilderEdu> resBuilderEdus;

	/**
	 * Gets the degree edu id.
	 *
	 * @return the degree edu id
	 */
	public int getDegreeEduId() {
		return this.degreeEduId;
	}

	/**
	 * Sets the degree edu id.
	 *
	 * @param degreeEduId the new degree edu id
	 */
	public void setDegreeEduId(int degreeEduId) {
		this.degreeEduId = degreeEduId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the res builder edus.
	 *
	 * @return the res builder edus
	 */
	public List<ResBuilderEdu> getResBuilderEdus() {
		return this.resBuilderEdus;
	}

	/**
	 * Sets the res builder edus.
	 *
	 * @param resBuilderEdus the new res builder edus
	 */
	public void setResBuilderEdus(List<ResBuilderEdu> resBuilderEdus) {
		this.resBuilderEdus = resBuilderEdus;
	}
	
}