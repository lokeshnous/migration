package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the res_builder_certification database table.
 * 
 */
@Entity
@Table(name="res_builder_certification")
public class ResBuilderCertification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_certification_id")
	private int builderCertificationId;

	@Column(name="certification_name")
	private String certificationName;

	private String description;

    @Temporal( TemporalType.DATE)
	@Column(name="earned_dt")
	private Date earnedDt;

    @Temporal( TemporalType.DATE)
	@Column(name="expire_dt")
	private Date expireDt;

	@Column(name="institution_name")
	private String institutionName;

	//bi-directional many-to-one association to ResBuilderResume
    @ManyToOne
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

    public ResBuilderCertification() {
    }

	public int getBuilderCertificationId() {
		return this.builderCertificationId;
	}

	public void setBuilderCertificationId(int builderCertificationId) {
		this.builderCertificationId = builderCertificationId;
	}

	public String getCertificationName() {
		return this.certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEarnedDt() {
		return this.earnedDt;
	}

	public void setEarnedDt(Date earnedDt) {
		this.earnedDt = earnedDt;
	}

	public Date getExpireDt() {
		return this.expireDt;
	}

	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}