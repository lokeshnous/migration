package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * The persistent class for the jp_job_type database table.
 * 
 */
@Entity
@Table(name="jp_job_type")
public class JpJobType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_type_id")
	private int jobTypeId;

	@Column(name="credit_amt")
	private int creditAmt;

	private String description;

	private String name;
	
	@Column(name="netsuite_id")
	private int netSuiteId;

	//bi-directional many-to-one association to JpJob
	@OneToMany(mappedBy="jpJobType")
	private List<JpJob> jpJobs;
	


	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "jp_type_addon_xref",joinColumns = { 
			@JoinColumn(name = "job_type_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "addon_id", 
					nullable = false, updatable = false) })
	private Set<JpAddon> jpAddons = new TreeSet<JpAddon>();

	public int getJobTypeId() {
		return this.jobTypeId;
	}

	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public int getCreditAmt() {
		return this.creditAmt;
	}

	public void setCreditAmt(int creditAmt) {
		this.creditAmt = creditAmt;
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

	public int getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(int netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public List<JpJob> getJpJobs() {
		return this.jpJobs;
	}

	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}


	public Set<JpAddon> getJpAddons() {
		return jpAddons;
	}

	public void setJpAddons(Set<JpAddon> jpAddons) {
		this.jpAddons = jpAddons;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}