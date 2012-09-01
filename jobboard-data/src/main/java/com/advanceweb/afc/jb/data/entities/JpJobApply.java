package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jp_job_apply database table.
 * 
 */
@Entity
@Table(name="jp_job_apply")
public class JpJobApply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_apply_id")
	private int jobApplyId;

	private int active;

	@Column(name="apply_link")
	private String applyLink;

	@Column(name="apply_method")
	private String applyMethod;

	//bi-directional many-to-one association to JpJob
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="job_id")
	private JpJob jpJob;

	public int getJobApplyId() {
		return this.jobApplyId;
	}

	public void setJobApplyId(int jobApplyId) {
		this.jobApplyId = jobApplyId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getApplyLink() {
		return this.applyLink;
	}

	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}

	public String getApplyMethod() {
		return this.applyMethod;
	}

	public void setApplyMethod(String applyMethod) {
		this.applyMethod = applyMethod;
	}

	public JpJob getJpJob() {
		return this.jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
}