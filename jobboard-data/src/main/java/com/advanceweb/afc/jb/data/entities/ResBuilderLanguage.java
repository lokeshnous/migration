package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the res_builder_language database table.
 * 
 */
@Entity
@Table(name="res_builder_language")
public class ResBuilderLanguage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_language_id")
	private int builderLanguageId;

	@Column(name="exp_level")
	private String expLevel;

	@Column(name="language_name")
	private String languageName;

	//bi-directional many-to-one association to ResBuilderResume
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

    public ResBuilderLanguage() {
    }

	public int getBuilderLanguageId() {
		return this.builderLanguageId;
	}

	public void setBuilderLanguageId(int builderLanguageId) {
		this.builderLanguageId = builderLanguageId;
	}

	public String getExpLevel() {
		return this.expLevel;
	}

	public void setExpLevel(String expLevel) {
		this.expLevel = expLevel;
	}

	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}