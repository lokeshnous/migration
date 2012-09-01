package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the jp_job_stats database table.
 * 
 */
@Entity
@Table(name="jp_job_stats")
public class JpJobStat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_id")
	private int jobId;

	private int applies;

	private int clicks;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="stats_dt")
	private Date statsDt;

	private int views;

	//bi-directional one-to-one association to JpJob
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	private JpJob jpJob;

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getApplies() {
		return this.applies;
	}

	public void setApplies(int applies) {
		this.applies = applies;
	}

	public int getClicks() {
		return this.clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public Date getStatsDt() {
		return this.statsDt;
	}

	public void setStatsDt(Date statsDt) {
		this.statsDt = statsDt;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public JpJob getJpJob() {
		return this.jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
}