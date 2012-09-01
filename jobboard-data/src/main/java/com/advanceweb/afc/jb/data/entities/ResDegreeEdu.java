package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the res_degree_edu database table.
 * 
 */
@Entity
@Table(name="res_degree_edu")
public class ResDegreeEdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="degree_edu_id")
	private int degreeEduId;

	private String description;

	private String name;

	//bi-directional many-to-one association to ResBuilderEdu
	@OneToMany(mappedBy="resDegreeEdu")
	private List<ResBuilderEdu> resBuilderEdus;

	public int getDegreeEduId() {
		return this.degreeEduId;
	}

	public void setDegreeEduId(int degreeEduId) {
		this.degreeEduId = degreeEduId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResBuilderEdu> getResBuilderEdus() {
		return this.resBuilderEdus;
	}

	public void setResBuilderEdus(List<ResBuilderEdu> resBuilderEdus) {
		this.resBuilderEdus = resBuilderEdus;
	}
	
}