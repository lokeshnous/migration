package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the res_builder_edu database table.
 * 
 */
@Entity
@Table(name="res_builder_edu")
public class ResBuilderEdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_edu_id")
	private int builderEduId;

	private String city;

    @Temporal( TemporalType.DATE)
	@Column(name="completion_dt")
	private Date completionDt;

	private String country;

	@Column(name="course_of_study")
	private String courseOfStudy;

	@Column(name="`degree_edu-id`")
	private int degreeEdu_id;

    @Temporal( TemporalType.DATE)
	private Date end_Date;

	@Column(name="field_study")
	private String fieldStudy;

	@Column(name="inst_name_lookup_id")
	private int instNameLookupId;

	@Column(name="institution_name")
	private String institutionName;

	@Column(name="language_lookup_id")
	private int languageLookupId;

	@Column(name="not_graduated")
	private String notGraduated;

	private String skills;

    @Temporal( TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String state;

	//bi-directional many-to-one association to ResBuilderResume
    @ManyToOne
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	//bi-directional many-to-one association to ResDegreeEdu
    @ManyToOne
	@JoinColumn(name="degree_edu_id")
	private ResDegreeEdu resDegreeEdu;

    public ResBuilderEdu() {
    }

	public int getBuilderEduId() {
		return this.builderEduId;
	}

	public void setBuilderEduId(int builderEduId) {
		this.builderEduId = builderEduId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCompletionDt() {
		return this.completionDt;
	}

	public void setCompletionDt(Date completionDt) {
		this.completionDt = completionDt;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCourseOfStudy() {
		return this.courseOfStudy;
	}

	public void setCourseOfStudy(String courseOfStudy) {
		this.courseOfStudy = courseOfStudy;
	}

	public int getDegreeEdu_id() {
		return this.degreeEdu_id;
	}

	public void setDegreeEdu_id(int degreeEdu_id) {
		this.degreeEdu_id = degreeEdu_id;
	}

	public Date getEnd_Date() {
		return this.end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public String getFieldStudy() {
		return this.fieldStudy;
	}

	public void setFieldStudy(String fieldStudy) {
		this.fieldStudy = fieldStudy;
	}

	public int getInstNameLookupId() {
		return this.instNameLookupId;
	}

	public void setInstNameLookupId(int instNameLookupId) {
		this.instNameLookupId = instNameLookupId;
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public int getLanguageLookupId() {
		return this.languageLookupId;
	}

	public void setLanguageLookupId(int languageLookupId) {
		this.languageLookupId = languageLookupId;
	}

	public String getNotGraduated() {
		return this.notGraduated;
	}

	public void setNotGraduated(String notGraduated) {
		this.notGraduated = notGraduated;
	}

	public String getSkills() {
		return this.skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
	public ResDegreeEdu getResDegreeEdu() {
		return this.resDegreeEdu;
	}

	public void setResDegreeEdu(ResDegreeEdu resDegreeEdu) {
		this.resDegreeEdu = resDegreeEdu;
	}
	
}