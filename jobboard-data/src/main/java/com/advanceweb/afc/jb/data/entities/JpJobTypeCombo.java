package com.advanceweb.afc.jb.data.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * The persistent class for the adm_facility_detail database table.
 * 
 */
@Entity
@Table(name="jp_jobtype_combo")
public class JpJobTypeCombo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OrderBy
	@Column(name="combo_id")
	private int comboId;
	
	@Column(name="job_type")
	private String jobType;
	
	@Column(name="addons")
	private String addons;
	
	//bi-directional many-to-one association to MerProfileAttribList
	@OneToOne(mappedBy="jpJobTypeCombo")
	private AdmInventoryDetail admInventoryDetail;

	
	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getAddons() {
		return addons;
	}

	public void setAddons(String addons) {
		this.addons = addons;
	}

	public AdmInventoryDetail getAdmInventoryDetail() {
		return admInventoryDetail;
	}

	public void setAdmInventoryDetail(AdmInventoryDetail admInventoryDetail) {
		this.admInventoryDetail = admInventoryDetail;
	}	
	
}
