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

	@Column(name="certifying_authority")
	private String certifyingAuthority;

	private String description;

    @Temporal( TemporalType.DATE)
	@Column(name="earned_dt")
	private Date earnedDt;

    @Temporal( TemporalType.DATE)
	@Column(name="expire_dt")
	private Date expireDt;

	//bi-directional many-to-one association to ResBuilderResume
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

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

	public String getCertifyingAuthority() {
		return this.certifyingAuthority;
	}

	public void setCertifyingAuthority(String certifyingAuthority) {
		this.certifyingAuthority = certifyingAuthority;
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

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}