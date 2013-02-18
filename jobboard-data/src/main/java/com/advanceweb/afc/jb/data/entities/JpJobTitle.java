package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the jp_job_title database table.
 * 
 */
@Entity
@Table(name="jp_job_title")
public class JpJobTitle{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_title_id")
	private int jobTitleId;
	
	@Column(name = "job_title")
	private String jobtitle;

	public int getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(int jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
}