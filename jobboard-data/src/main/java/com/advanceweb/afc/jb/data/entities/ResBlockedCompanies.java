package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_user_role database table.
 * 
 */
@Entity
@Table(name="res_blocked_companies")
public class ResBlockedCompanies implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "blocked_company_id")
	private int blockedCompanyId;
	@Column(name = "Resume_id")
	private int resumeId;
	@Column(name = "Comapny_Id")
	private int companyId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDt;

	public int getBlockedCompanyId() {
		return blockedCompanyId;
	}

	public void setBlockedCompanyId(int blockedCompanyId) {
		this.blockedCompanyId = blockedCompanyId;
	}

	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getModifiedDt() {
		return this.modifiedDt;
	}

	public void setModifiedDt(Date modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

}