package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


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

	//bi-directional many-to-one association to ResPublishResumePriv
	@OneToMany(mappedBy="resPrivacy")
	private List<ResPublishResumePriv> resPublishResumePrivs;

    public ResPrivacy() {
    }

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

	public List<ResPublishResumePriv> getResPublishResumePrivs() {
		return this.resPublishResumePrivs;
	}

	public void setResPublishResumePrivs(List<ResPublishResumePriv> resPublishResumePrivs) {
		this.resPublishResumePrivs = resPublishResumePrivs;
	}
	
}