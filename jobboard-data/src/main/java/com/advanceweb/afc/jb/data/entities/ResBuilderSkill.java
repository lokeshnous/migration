package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the res_builder_skill database table.
 * 
 */
@Entity
@Table(name="res_builder_skill")
public class ResBuilderSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_skill_id")
	private int builderSkillId;

	@Column(name="skill_name")
	private String skillName;

	//bi-directional many-to-one association to ResBuilderResume
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	public int getBuilderSkillId() {
		return this.builderSkillId;
	}

	public void setBuilderSkillId(int builderSkillId) {
		this.builderSkillId = builderSkillId;
	}

	public String getSkillName() {
		return this.skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}