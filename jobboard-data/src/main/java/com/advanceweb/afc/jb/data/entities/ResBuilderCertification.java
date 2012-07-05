package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the res_builder_certification database table.
 * 
 */
@Entity
@Table(name = "res_builder_certification")
public class ResBuilderCertification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "builder_certification_id", insertable = false, updatable = false)
	private int builderCertificationId;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "earned_dt")
	private Date earnedDt;

	@Temporal(TemporalType.DATE)
	@Column(name = "expire_dt")
	private Date expireDt;

	// bi-directional many-to-one association to ResBuilderResume
	@ManyToOne
	@JoinColumn(name = "builder_resume_id")
	private ResBuilderResume resBuilderResume;

	public ResBuilderCertification() {
	}

	public int getBuilderCertificationId() {
		return builderCertificationId;
	}

	public String getDescription() {
		return description;
	}

	public Date getEarnedDt() {
		return earnedDt;
	}

	public Date getExpireDt() {
		return expireDt;
	}

	public ResBuilderResume getResBuilderResume() {
		return resBuilderResume;
	}

	public void setBuilderCertificationId(int builderCertificationId) {
		this.builderCertificationId = builderCertificationId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEarnedDt(Date earnedDt) {
		this.earnedDt = earnedDt;
	}

	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}

}