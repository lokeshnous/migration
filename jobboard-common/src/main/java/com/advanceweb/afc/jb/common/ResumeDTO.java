package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author anilm
 * @version 1.0
 */
public class ResumeDTO extends ProfileDTO implements Serializable{
	
	private static final long serialVersionUID = -2200423309708244707L;
	
	private int uploadResumeId;
	private int builderResumeId;
	private int userId;
	private String resumeText;
	private String resumeName;
	private String desiredJobTitle;
	private String desiredEmploymentType;
	private String workAuthorizationUS;
	private String willingToRelocate;
	private String resumeVisibility;
	private String resumeType;
	private String employmentType;
	private CommonsMultipartFile fileData;
	private String fileServer;
	private String filePath;
	private String fileName;
	private String isPublished;
	private String updateDt;
	
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
	private ContactInformationDTO contactInfoDTO;


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
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resume_name) {
		this.resumeName = resume_name;
	}
	public String getDesiredJobTitle() {
		return desiredJobTitle;
	}
	public void setDesiredJobTitle(String desired_job_title) {
		this.desiredJobTitle = desired_job_title;
	}
	public String getDesiredEmploymentType() {
		return desiredEmploymentType;
	}
	public void setDesiredEmploymentType(String desired_employment_type) {
		this.desiredEmploymentType = desired_employment_type;
	}
	public String getWorkAuthorizationUS() {
		return workAuthorizationUS;
	}
	public void setWorkAuthorizationUS(String work_authorization_US) {
		this.workAuthorizationUS = work_authorization_US;
	}
	public String getWillingToRelocate() {
		return willingToRelocate;
	}
	public void setWillingToRelocate(String willing_to_relocate) {
		this.willingToRelocate = willing_to_relocate;
	}
	public String getResumeVisibility() {
		return resumeVisibility;
	}
	public void setResumeVisibility(String resume_visibility) {
		this.resumeVisibility = resume_visibility;
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
		
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
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
	public ContactInformationDTO getContactInfoDTO() {
		return contactInfoDTO;
	}
	public void setContactInfoDTO(ContactInformationDTO contactInfoDTO) {
		this.contactInfoDTO = contactInfoDTO;
	}	
	public int getBuilderResumeId() {
		return builderResumeId;
	}
	public void setBuilderResumeId(int builderResumeId) {
		this.builderResumeId = builderResumeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}	

	@Override
	public String toString() {
		return "ResumeDTO [uploadResumeId=" + uploadResumeId + ", resumeText="
				+ resumeText + ", resume_name=" + resumeName
				+ ", desired_job_title=" + desiredJobTitle
				+ ", desired_employment_type=" + desiredEmploymentType
				+ ", work_authorization_US=" + workAuthorizationUS
				+ ", willing_to_relocate=" + willingToRelocate
				+ ", resume_visibility=" + resumeVisibility + ", resumeType="
				+ resumeType + ", employmentType=" + employmentType
				+ ", fileData=" + fileData + ", fileServer=" + fileServer
				+ ", filePath=" + filePath + ", fileName=" + fileName
				+ ", isPublished=" + isPublished + ", updateDt=" + updateDt
				+ "]";
	}
	
}
