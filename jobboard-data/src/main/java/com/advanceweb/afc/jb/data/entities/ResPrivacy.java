package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the res_privacy database table.
 * 
 */
@Entity
@Table(name = "res_privacy")
public class ResPrivacy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_dt")
	private Timestamp createDt;

	private int description;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "privacy_id", insertable = false, updatable = false)
	private int privacyId;

	// bi-directional many-to-one association to ResPublishResumePriv
	@OneToMany(mappedBy = "resPrivacy")
	private List<ResPublishResumePriv> resPublishResumePrivs;

	public ResPrivacy() {
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public int getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public int getPrivacyId() {
		return privacyId;
	}

	public List<ResPublishResumePriv> getResPublishResumePrivs() {
		return resPublishResumePrivs;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDescription(int description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrivacyId(int privacyId) {
		this.privacyId = privacyId;
	}

	public void setResPublishResumePrivs(
			List<ResPublishResumePriv> resPublishResumePrivs) {
		this.resPublishResumePrivs = resPublishResumePrivs;
	}

}