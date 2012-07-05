package com.advanceweb.afc.data.entities;

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
@Table(name = "res_degree_edu")
public class ResDegreeEdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "degree_edu_id", insertable = false, updatable = false)
	private int degreeEduId;

	private String description;

	private String name;

	// bi-directional many-to-one association to ResBuilderEdu
	@OneToMany(mappedBy = "resDegreeEdu")
	private List<ResBuilderEdu> resBuilderEdus;

	public ResDegreeEdu() {
	}

	public int getDegreeEduId() {
		return degreeEduId;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public List<ResBuilderEdu> getResBuilderEdus() {
		return resBuilderEdus;
	}

	public void setDegreeEduId(int degreeEduId) {
		this.degreeEduId = degreeEduId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResBuilderEdus(List<ResBuilderEdu> resBuilderEdus) {
		this.resBuilderEdus = resBuilderEdus;
	}

}