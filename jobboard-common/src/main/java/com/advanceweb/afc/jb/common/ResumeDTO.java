package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author anilm
 * @version 1.0
 */
public class ResumeDTO implements Serializable{
	
	private static final long serialVersionUID = -2200423309708244707L;
	private int uploadResumeId;
	private String resumeText;
	private String resume_name;
	private String desired_job_title;
	private String desired_employment_type;
	private String work_authorization_US;
	private String willing_to_relocate;
	private String resume_visibility;
	private String resumeType;
	private String employmentType;
	private CommonsMultipartFile fileData;
	private String fileServer;
	private String filePath;
	private String fileName;
	private String isPublished;
	private Date updateDt;
	
	//Creating Resume
	private String objective;
	private String skills;
	private String awards;
	private String memberships;
	private String otherDetails;
	private List<CertificationDTO> listCertDTO;
	private List<EducationDTO> listEduDTO;
	private List<LanguageDTO> listLangDTO;
	private List<ReferenceDTO> listRefDTO;
	private List<WorkExpDTO> listWorkExpDTO;
	private AddressDTO addDTO;
	//Creating Resume	

	
	public int getUploadResumeId() {
		return uploadResumeId;
	}
	public void setUploadResumeId(int uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
	}
	public String getFileServer() {
		return fileServer;
	}
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public String getResume_name() {
		return resume_name;
	}
	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
	}
	public String getDesired_job_title() {
		return desired_job_title;
	}
	public void setDesired_job_title(String desired_job_title) {
		this.desired_job_title = desired_job_title;
	}
	public String getDesired_employment_type() {
		return desired_employment_type;
	}
	public void setDesired_employment_type(String desired_employment_type) {
		this.desired_employment_type = desired_employment_type;
	}
	public String getWork_authorization_US() {
		return work_authorization_US;
	}
	public void setWork_authorization_US(String work_authorization_US) {
		this.work_authorization_US = work_authorization_US;
	}
	public String getWilling_to_relocate() {
		return willing_to_relocate;
	}
	public void setWilling_to_relocate(String willing_to_relocate) {
		this.willing_to_relocate = willing_to_relocate;
	}
	public String getResume_visibility() {
		return resume_visibility;
	}
	public void setResume_visibility(String resume_visibility) {
		this.resume_visibility = resume_visibility;
	}
	public String getResumeType() {
		return resumeType;
	}
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getMemberships() {
		return memberships;
	}
	public void setMemberships(String memberships) {
		this.memberships = memberships;
	}
	public String getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}
	public List<CertificationDTO> getListCertDTO() {
		return listCertDTO;
	}
	public void setListCertDTO(List<CertificationDTO> listCertDTO) {
		this.listCertDTO = listCertDTO;
	}
	public List<EducationDTO> getListEduDTO() {
		return listEduDTO;
	}
	public void setListEduDTO(List<EducationDTO> listEduDTO) {
		this.listEduDTO = listEduDTO;
	}
	public List<LanguageDTO> getListLangDTO() {
		return listLangDTO;
	}
	public void setListLangDTO(List<LanguageDTO> listLangDTO) {
		this.listLangDTO = listLangDTO;
	}
	public List<ReferenceDTO> getListRefDTO() {
		return listRefDTO;
	}
	public void setListRefDTO(List<ReferenceDTO> listRefDTO) {
		this.listRefDTO = listRefDTO;
	}
	public List<WorkExpDTO> getListWorkExpDTO() {
		return listWorkExpDTO;
	}
	public void setListWorkExpDTO(List<WorkExpDTO> listWorkExpDTO) {
		this.listWorkExpDTO = listWorkExpDTO;
	}
	
	public AddressDTO getAddDTO() {
		return addDTO;
	}
	public void setAddDTO(AddressDTO addDTO) {
		this.addDTO = addDTO;
	}
	@Override
	public String toString() {
		return "ResumeDTO [uploadResumeId=" + uploadResumeId + ", resumeText="
				+ resumeText + ", resume_name=" + resume_name
				+ ", desired_job_title=" + desired_job_title
				+ ", desired_employment_type=" + desired_employment_type
				+ ", work_authorization_US=" + work_authorization_US
				+ ", willing_to_relocate=" + willing_to_relocate
				+ ", resume_visibility=" + resume_visibility + ", resumeType="
				+ resumeType + ", employmentType=" + employmentType
				+ ", fileData=" + fileData + ", fileServer=" + fileServer
				+ ", filePath=" + filePath + ", fileName=" + fileName
				+ ", isPublished=" + isPublished + ", updateDt=" + updateDt
				+ "]";
	}
	
}
