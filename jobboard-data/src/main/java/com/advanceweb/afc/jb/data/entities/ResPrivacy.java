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
@Table(name="res_privacy")
public class ResPrivacy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="privacy_id")
	private int privacyId;

	@Column(name="create_dt")
	private Timestamp createDt;

	private int description;

	private String name;

	//bi-directional many-to-one association to ResCoverletterPriv
	@OneToMany(mappedBy="resPrivacy")
	private List<ResCoverletterPriv> resCoverletterPrivs;

	//bi-directional many-to-one association to ResPublishResumePriv
	@OneToMany(mappedBy="resPrivacy")
	private List<ResPublishResumePriv> resPublishResumePrivs;

	public int getPrivacyId() {
		return this.privacyId;
	}

	public void setPrivacyId(int privacyId) {
		this.privacyId = privacyId;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public int getDescription() {
		return this.description;
	}

	public void setDescription(int description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResCoverletterPriv> getResCoverletterPrivs() {
		return this.resCoverletterPrivs;
	}

	public void setResCoverletterPrivs(List<ResCoverletterPriv> resCoverletterPrivs) {
		this.resCoverletterPrivs = resCoverletterPrivs;
	}
	
	public List<ResPublishResumePriv> getResPublishResumePrivs() {
		return this.resPublishResumePrivs;
	}

	public void setResPublishResumePrivs(List<ResPublishResumePriv> resPublishResumePrivs) {
		this.resPublishResumePrivs = resPublishResumePrivs;
	}
	
}