package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the res_publish_resume_stats database table.
 * 
 */
@Entity
@Table(name="res_publish_resume_stats")
public class ResPublishResumeStat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="publish_resume_id")
	private int publishResumeId;

	@Column(name="employer_impressions")
	private int employerImpressions;

	@Column(name="employer_views")
	private int employerViews;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="stats_dt")
	private Date statsDt;

	//bi-directional one-to-one association to ResPublishResume
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publish_resume_id")
	private ResPublishResume resPublishResume;

	public int getPublishResumeId() {
		return this.publishResumeId;
	}

	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	public int getEmployerImpressions() {
		return this.employerImpressions;
	}

	public void setEmployerImpressions(int employerImpressions) {
		this.employerImpressions = employerImpressions;
	}

	public int getEmployerViews() {
		return this.employerViews;
	}

	public void setEmployerViews(int employerViews) {
		this.employerViews = employerViews;
	}

	public Date getStatsDt() {
		return this.statsDt;
	}

	public void setStatsDt(Date statsDt) {
		this.statsDt = statsDt;
	}

	public ResPublishResume getResPublishResume() {
		return this.resPublishResume;
	}

	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}
	
}