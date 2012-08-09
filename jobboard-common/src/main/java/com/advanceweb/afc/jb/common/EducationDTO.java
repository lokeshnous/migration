package com.advanceweb.afc.jb.common;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class EducationDTO {
	private String degreeLvl;
	private String fieldOfStudy;
	private String startDate;
	private String endDate;
	private String instituteName;
	private String degrees;
	private String certifications;
	private String language;
	private int bGraduated;
	private int builderEduId;
	private EduDegreeDTO eduDegreeDTO;
	
	public String getDegreeLvl() {
		return degreeLvl;
	}
	public void setDegreeLvl(String degreeLvl) {
		this.degreeLvl = degreeLvl;
	}
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getDegrees() {
		return degrees;
	}
	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getBuilderEduId() {
		return builderEduId;
	}
	public void setBuilderEduId(int builderEduId) {
		this.builderEduId = builderEduId;
	}
	public EduDegreeDTO getEduDegreeDTO() {
		return eduDegreeDTO;
	}
	public void setEduDegreeDTO(EduDegreeDTO eduDegreeDTO) {
		this.eduDegreeDTO = eduDegreeDTO;
	}
	public String getCertifications() {
		return certifications;
	}
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	public int getbGraduated() {
		return bGraduated;
	}
	public void setbGraduated(int bGraduated) {
		this.bGraduated = bGraduated;
	}
		
}
