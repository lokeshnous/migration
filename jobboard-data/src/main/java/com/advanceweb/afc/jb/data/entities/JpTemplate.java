package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the jp_template database table.
 * 
 */
@Entity
@Table(name="jp_template")
public class JpTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="template_id")
	private int templateId;

	@Column(name="color_palette")
	private String colorPalette;

    @Lob()
	@Column(name="company_overview")
	private String companyOverview;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

//    MUST be changed to JoinColumn
    	
//    @ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="create_user_id")
//    private MerUser merUser;
    
	@Column(name="create_user_id")
	private int createUserId;

	@Column(name="logo_path")
	private String logoPath;

	@Column(name="main_image_path")
	private String mainImagePath;

	@Column(name="template_name")
	private String templateName;

	//bi-directional many-to-one association to JpJob
//	@OneToMany(mappedBy="jpTemplate")
//	private List<JpJob> jpJobs;

	//bi-directional many-to-one association to AdmFacility
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	//bi-directional many-to-one association to JpTemplateMedia
	@OneToMany(mappedBy="jpTemplate")
	private List<JpTemplateMedia> jpTemplateMedias;

	//bi-directional many-to-one association to JpTemplateTestimonial
	@OneToMany(mappedBy="jpTemplate")
	private List<JpTemplateTestimonial> jpTemplateTestimonials;

//    public JpTemplate() {
//    }

	public int getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getColorPalette() {
		return this.colorPalette;
	}

	public void setColorPalette(String colorPalette) {
		this.colorPalette = colorPalette;
	}

	public String getCompanyOverview() {
		return this.companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public int getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

//	public MerUser getMerUser() {
//		return merUser;
//	}
//
//	public void setMerUser(MerUser merUser) {
//		this.merUser = merUser;
//	}

	public String getLogoPath() {
		return this.logoPath;
	}

	
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getMainImagePath() {
		return this.mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

//	public List<JpJob> getJpJobs() {
//		return this.jpJobs;
//	}
//
//	public void setJpJobs(List<JpJob> jpJobs) {
//		this.jpJobs = jpJobs;
//	}
	
	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
	
	public List<JpTemplateMedia> getJpTemplateMedias() {
		return this.jpTemplateMedias;
	}

	public void setJpTemplateMedias(List<JpTemplateMedia> jpTemplateMedias) {
		this.jpTemplateMedias = jpTemplateMedias;
	}
	
	public List<JpTemplateTestimonial> getJpTemplateTestimonials() {
		return this.jpTemplateTestimonials;
	}

	public void setJpTemplateTestimonials(List<JpTemplateTestimonial> jpTemplateTestimonials) {
		this.jpTemplateTestimonials = jpTemplateTestimonials;
	}
	
}