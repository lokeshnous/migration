package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.Date;
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
	private int builderSkillsId;
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
	private List<ProfileAttribDTO> resumeAttribList;
	private List<PhoneDetailDTO> listPhoneDtl;
	private ContactInformationDTO contactInfoDTO;
	
	//Added new fields
	
	private String city;
	
	private String fullName;
	
	private String htmlResumeText;
	
	private Date postDt;
	
	private String state;
	
	private int experience;
	
	

	public int getExperience() {
		return experience;
	}


	public void setExperience(int experience) {
		this.experience = experience;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getHtmlResumeText() {
		return htmlResumeText;
	}


	public void setHtmlResumeText(String htmlResumeText) {
		this.htmlResumeText = htmlResumeText;
	}


	public Date getPostDt() {
		return postDt;
	}


	public void setPostDt(Date postDt) {
		this.postDt = postDt;
	}


	public int getUploadResumeId() {
		return uploadResumeId;
	}


	public void setUploadResumeId(int uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
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


	public String getResumeText() {
		return resumeText;
	}


	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}


	public String getResumeName() {
		return resumeName;
	}


	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}


	public String getDesiredJobTitle() {
		return desiredJobTitle;
	}


	public void setDesiredJobTitle(String desiredJobTitle) {
		this.desiredJobTitle = desiredJobTitle;
	}


	public String getDesiredEmploymentType() {
		return desiredEmploymentType;
	}


	public void setDesiredEmploymentType(String desiredEmploymentType) {
		this.desiredEmploymentType = desiredEmploymentType;
	}


	public String getWorkAuthorizationUS() {
		return workAuthorizationUS;
	}


	public void setWorkAuthorizationUS(String workAuthorizationUS) {
		this.workAuthorizationUS = workAuthorizationUS;
	}


	public String getWillingToRelocate() {
		return willingToRelocate;
	}


	public void setWillingToRelocate(String willingToRelocate) {
		this.willingToRelocate = willingToRelocate;
	}


	public String getResumeVisibility() {
		return resumeVisibility;
	}


	public void setResumeVisibility(String resumeVisibility) {
		this.resumeVisibility = resumeVisibility;
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


	public List<ProfileAttribDTO> getResumeAttribList() {
		return resumeAttribList;
	}


	public void setResumeAttribList(List<ProfileAttribDTO> resumeAttribList) {
		this.resumeAttribList = resumeAttribList;
	}


	public List<PhoneDetailDTO> getListPhoneDtl() {
		return listPhoneDtl;
	}


	public void setListPhoneDtl(List<PhoneDetailDTO> listPhoneDtl) {
		this.listPhoneDtl = listPhoneDtl;
	}


	public int getBuilderSkillsId() {
		return builderSkillsId;
	}


	public void setBuilderSkillsId(int builderSkillsId) {
		this.builderSkillsId = builderSkillsId;
	}
	
}
