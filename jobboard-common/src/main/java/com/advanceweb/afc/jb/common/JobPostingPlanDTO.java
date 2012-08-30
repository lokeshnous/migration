package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * anilm
 * @version 1.0
 * @created Aug 24, 2012
 */
public class JobPostingPlanDTO {
	
	String jobPostPlanId;
	String jobPostPlanName;
	String jobPostPlanDescr;
	String jobPostPlanCretitAmt;
	List<AddOnDTO> addOnDTOList;
	
	public String getJobPostPlanId() {
		return jobPostPlanId;
	}
	public void setJobPostPlanId(String jobPostPlanId) {
		this.jobPostPlanId = jobPostPlanId;
	}
	public String getJobPostPlanName() {
		return jobPostPlanName;
	}
	public void setJobPostPlanName(String jobPostPlanName) {
		this.jobPostPlanName = jobPostPlanName;
	}
	public String getJobPostPlanDescr() {
		return jobPostPlanDescr;
	}
	public void setJobPostPlanDescr(String jobPostPlanDescr) {
		this.jobPostPlanDescr = jobPostPlanDescr;
	}
	public String getJobPostPlanCretitAmt() {
		return jobPostPlanCretitAmt;
	}
	public void setJobPostPlanCretitAmt(String jobPostPlanCretitAmt) {
		this.jobPostPlanCretitAmt = jobPostPlanCretitAmt;
	}
	public List<AddOnDTO> getAddOnDTOList() {
		return addOnDTOList;
	}
	public void setAddOnDTOList(List<AddOnDTO> addOnDTOList) {
		this.addOnDTOList = addOnDTOList;
	}
}
