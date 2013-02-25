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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the res_builder_skill database table.
 * 
 */
@Entity
@Table(name="res_builder_skill")
public class ResBuilderSkill implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder skill id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_skill_id")
	private int builderSkillId;

	/** The skill name. */
	@Column(name="skill_name")
	private String skillName;

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	/**
	 * Gets the builder skill id.
	 *
	 * @return the builder skill id
	 */
	public int getBuilderSkillId() {
		return this.builderSkillId;
	}

	/**
	 * Sets the builder skill id.
	 *
	 * @param builderSkillId the new builder skill id
	 */
	public void setBuilderSkillId(int builderSkillId) {
		this.builderSkillId = builderSkillId;
	}

	/**
	 * Gets the skill name.
	 *
	 * @return the skill name
	 */
	public String getSkillName() {
		return this.skillName;
	}

	/**
	 * Sets the skill name.
	 *
	 * @param skillName the new skill name
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	/**
	 * Gets the res builder resume.
	 *
	 * @return the res builder resume
	 */
	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	/**
	 * Sets the res builder resume.
	 *
	 * @param resBuilderResume the new res builder resume
	 */
	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}