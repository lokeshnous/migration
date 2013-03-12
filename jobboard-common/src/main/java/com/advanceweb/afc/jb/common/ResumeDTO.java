/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2200423309708244707L;
	
	/** The upload resume id. */
	private int uploadResumeId;
	
	/** The builder resume id. */
	private int builderResumeId;
	
	/** The user id. */
	private int userId;
	
	/** The resume text. */
	private String resumeText;
	
	/** The resume name. */
	private String resumeName;
	
	/** The desired job title. */
	private String desiredJobTitle;
	
	/** The desired employment type. */
	private String desiredEmploymentType;
	
	private List<String> employmentTypes;
	
	/** The work authorization us. */
	private String workAuthorizationUS;
	
	/** The willing to relocate. */
	private String willingToRelocate;
	
	/** The resume visibility. */
	private String resumeVisibility;
	
	/** The resume type. */
	private String resumeType;
	
	/** The employment type. */
	private String employmentType;
	
	/** The file data. */
	private CommonsMultipartFile fileData;
	
	/** The file server. */
	private String fileServer;
	
	/** The file path. */
	private String filePath;
	
	/** The file name. */
	private String fileName;
	
	/** The is published. */
	private String isPublished;
	
	/** The available date. */
	private String availableDate;
	
	/** The update dt. */
	private String updateDt;
	
	/** The builder skills id. */
	private int builderSkillsId;
	
	/** The folder resume id. */
	private int folderResumeId;
	//Creating Resume
	/** The objective. */
	private String objective;
	
	/** The skills. */
	private String skills;
	
	/** The awards. */
	private String awards;
	
	/** The memberships. */
	private String memberships;
	
	/** The other details. */
	private String otherDetails;
	
	/** The list cert dto. */
	private List<CertificationDTO> listCertDTO;
	
	/** The list edu dto. */
	private List<EducationDTO> listEduDTO;
	
	/** The list lang dto. */
	private List<LanguageDTO> listLangDTO;
	
	/** The list ref dto. */
	private List<ReferenceDTO> listRefDTO;
	
	/** The list work exp dto. */
	private List<WorkExpDTO> listWorkExpDTO;
	
	/** The resume attrib list. */
	private List<ProfileAttribDTO> resumeAttribList;
	
	/** The list phone dtl. */
	private List<PhoneDetailDTO> listPhoneDtl;
	
	/** The contact info dto. */
	private ContactInformationDTO contactInfoDTO;
	
	//Added new fields
	
	/** The city. */
	private String city;
	
	/** The full name. */
	private String fullName;
	
	/** The html resume text. */
	private String htmlResumeText;
	
	/** The post dt. */
	private Date postDt;
	
	/** The state. */
	private String state;
	
	/** The experience. */
	private int experience;
	
	/** The publish resume id. */
	private int publishResumeId;
	
	/** The publish resume id. */
	private boolean resumeViewed;
	
	/**	The Selected List. */
	private List<Integer> selectedList;

	/**
	 * Gets the publish resume id.
	 *
	 * @return the publish resume id
	 */
	public int getPublishResumeId() {
		return publishResumeId;
	}


	/**
	 * Sets the publish resume id.
	 *
	 * @param publishResumeId the new publish resume id
	 */
	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}


	/**
	 * Gets the experience.
	 *
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}


	/**
	 * Sets the experience.
	 *
	 * @param experience the new experience
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}


	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}


	/**
	 * @return the folderResumeId
	 */
	public int getFolderResumeId() {
		return folderResumeId;
	}


	/**
	 * @param folderResumeId the folderResumeId to set
	 */
	public void setFolderResumeId(int folderResumeId) {
		this.folderResumeId = folderResumeId;
	}


	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	/**
	 * Gets the html resume text.
	 *
	 * @return the html resume text
	 */
	public String getHtmlResumeText() {
		return htmlResumeText;
	}


	/**
	 * Sets the html resume text.
	 *
	 * @param htmlResumeText the new html resume text
	 */
	public void setHtmlResumeText(String htmlResumeText) {
		this.htmlResumeText = htmlResumeText;
	}


	/**
	 * Gets the post dt.
	 *
	 * @return the post dt
	 */
	public Date getPostDt() {
		return postDt;
	}


	/**
	 * Sets the post dt.
	 *
	 * @param postDt the new post dt
	 */
	public void setPostDt(Date postDt) {
		this.postDt = postDt;
	}


	/**
	 * Gets the upload resume id.
	 *
	 * @return the upload resume id
	 */
	public int getUploadResumeId() {
		return uploadResumeId;
	}


	/**
	 * Sets the upload resume id.
	 *
	 * @param uploadResumeId the new upload resume id
	 */
	public void setUploadResumeId(int uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
	}


	/**
	 * Gets the builder resume id.
	 *
	 * @return the builder resume id
	 */
	public int getBuilderResumeId() {
		return builderResumeId;
	}


	/**
	 * Sets the builder resume id.
	 *
	 * @param builderResumeId the new builder resume id
	 */
	public void setBuilderResumeId(int builderResumeId) {
		this.builderResumeId = builderResumeId;
	}


	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * Gets the resume text.
	 *
	 * @return the resume text
	 */
	public String getResumeText() {
		return resumeText;
	}


	/**
	 * Sets the resume text.
	 *
	 * @param resumeText the new resume text
	 */
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}


	/**
	 * Gets the resume name.
	 *
	 * @return the resume name
	 */
	public String getResumeName() {
		return resumeName;
	}


	/**
	 * Sets the resume name.
	 *
	 * @param resumeName the new resume name
	 */
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}


	/**
	 * Gets the desired job title.
	 *
	 * @return the desired job title
	 */
	public String getDesiredJobTitle() {
		return desiredJobTitle;
	}


	/**
	 * Sets the desired job title.
	 *
	 * @param desiredJobTitle the new desired job title
	 */
	public void setDesiredJobTitle(String desiredJobTitle) {
		this.desiredJobTitle = desiredJobTitle;
	}


	/**
	 * Gets the desired employment type.
	 *
	 * @return the desired employment type
	 */
	public String getDesiredEmploymentType() {
		return desiredEmploymentType;
	}


	/**
	 * Sets the desired employment type.
	 *
	 * @param desiredEmploymentType the new desired employment type
	 */
	public void setDesiredEmploymentType(String desiredEmploymentType) {
		this.desiredEmploymentType = desiredEmploymentType;
	}


	/**
	 * Gets the work authorization us.
	 *
	 * @return the work authorization us
	 */
	public String getWorkAuthorizationUS() {
		return workAuthorizationUS;
	}


	/**
	 * Sets the work authorization us.
	 *
	 * @param workAuthorizationUS the new work authorization us
	 */
	public void setWorkAuthorizationUS(String workAuthorizationUS) {
		this.workAuthorizationUS = workAuthorizationUS;
	}


	/**
	 * Gets the willing to relocate.
	 *
	 * @return the willing to relocate
	 */
	public String getWillingToRelocate() {
		return willingToRelocate;
	}


	/**
	 * Sets the willing to relocate.
	 *
	 * @param willingToRelocate the new willing to relocate
	 */
	public void setWillingToRelocate(String willingToRelocate) {
		this.willingToRelocate = willingToRelocate;
	}


	/**
	 * Gets the resume visibility.
	 *
	 * @return the resume visibility
	 */
	public String getResumeVisibility() {
		return resumeVisibility;
	}


	/**
	 * Sets the resume visibility.
	 *
	 * @param resumeVisibility the new resume visibility
	 */
	public void setResumeVisibility(String resumeVisibility) {
		this.resumeVisibility = resumeVisibility;
	}


	/**
	 * Gets the resume type.
	 *
	 * @return the resume type
	 */
	public String getResumeType() {
		return resumeType;
	}


	/**
	 * Sets the resume type.
	 *
	 * @param resumeType the new resume type
	 */
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}


	/**
	 * Gets the employment type.
	 *
	 * @return the employment type
	 */
	public String getEmploymentType() {
		return employmentType;
	}


	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the new employment type
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}


	/**
	 * Gets the file data.
	 *
	 * @return the file data
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}


	/**
	 * Sets the file data.
	 *
	 * @param fileData the new file data
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}


	/**
	 * Gets the file server.
	 *
	 * @return the file server
	 */
	public String getFileServer() {
		return fileServer;
	}


	/**
	 * Sets the file server.
	 *
	 * @param fileServer the new file server
	 */
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}


	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * Sets the file path.
	 *
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the checks if is published.
	 *
	 * @return the checks if is published
	 */
	public String getIsPublished() {
		return isPublished;
	}


	/**
	 * Sets the checks if is published.
	 *
	 * @param isPublished the new checks if is published
	 */
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}


	/**
	 * Gets the update dt.
	 *
	 * @return the update dt
	 */
	public String getUpdateDt() {
		return updateDt;
	}


	/**
	 * Sets the update dt.
	 *
	 * @param updateDt the new update dt
	 */
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}


	/**
	 * Gets the objective.
	 *
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}


	/**
	 * Sets the objective.
	 *
	 * @param objective the new objective
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}


	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public String getSkills() {
		return skills;
	}


	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}


	/**
	 * Gets the awards.
	 *
	 * @return the awards
	 */
	public String getAwards() {
		return awards;
	}


	/**
	 * Sets the awards.
	 *
	 * @param awards the new awards
	 */
	public void setAwards(String awards) {
		this.awards = awards;
	}


	/**
	 * Gets the memberships.
	 *
	 * @return the memberships
	 */
	public String getMemberships() {
		return memberships;
	}


	/**
	 * Sets the memberships.
	 *
	 * @param memberships the new memberships
	 */
	public void setMemberships(String memberships) {
		this.memberships = memberships;
	}


	/**
	 * Gets the other details.
	 *
	 * @return the other details
	 */
	public String getOtherDetails() {
		return otherDetails;
	}


	/**
	 * Sets the other details.
	 *
	 * @param otherDetails the new other details
	 */
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}


	/**
	 * Gets the list cert dto.
	 *
	 * @return the list cert dto
	 */
	public List<CertificationDTO> getListCertDTO() {
		return listCertDTO;
	}


	/**
	 * Sets the list cert dto.
	 *
	 * @param listCertDTO the new list cert dto
	 */
	public void setListCertDTO(List<CertificationDTO> listCertDTO) {
		this.listCertDTO = listCertDTO;
	}


	/**
	 * Gets the list edu dto.
	 *
	 * @return the list edu dto
	 */
	public List<EducationDTO> getListEduDTO() {
		return listEduDTO;
	}


	/**
	 * Sets the list edu dto.
	 *
	 * @param listEduDTO the new list edu dto
	 */
	public void setListEduDTO(List<EducationDTO> listEduDTO) {
		this.listEduDTO = listEduDTO;
	}


	/**
	 * Gets the list lang dto.
	 *
	 * @return the list lang dto
	 */
	public List<LanguageDTO> getListLangDTO() {
		return listLangDTO;
	}


	/**
	 * Sets the list lang dto.
	 *
	 * @param listLangDTO the new list lang dto
	 */
	public void setListLangDTO(List<LanguageDTO> listLangDTO) {
		this.listLangDTO = listLangDTO;
	}


	/**
	 * Gets the list ref dto.
	 *
	 * @return the list ref dto
	 */
	public List<ReferenceDTO> getListRefDTO() {
		return listRefDTO;
	}


	/**
	 * Sets the list ref dto.
	 *
	 * @param listRefDTO the new list ref dto
	 */
	public void setListRefDTO(List<ReferenceDTO> listRefDTO) {
		this.listRefDTO = listRefDTO;
	}


	/**
	 * Gets the list work exp dto.
	 *
	 * @return the list work exp dto
	 */
	public List<WorkExpDTO> getListWorkExpDTO() {
		return listWorkExpDTO;
	}


	/**
	 * Sets the list work exp dto.
	 *
	 * @param listWorkExpDTO the new list work exp dto
	 */
	public void setListWorkExpDTO(List<WorkExpDTO> listWorkExpDTO) {
		this.listWorkExpDTO = listWorkExpDTO;
	}


	/**
	 * Gets the contact info dto.
	 *
	 * @return the contact info dto
	 */
	public ContactInformationDTO getContactInfoDTO() {
		return contactInfoDTO;
	}


	/**
	 * Sets the contact info dto.
	 *
	 * @param contactInfoDTO the new contact info dto
	 */
	public void setContactInfoDTO(ContactInformationDTO contactInfoDTO) {
		this.contactInfoDTO = contactInfoDTO;
	}


	/**
	 * Gets the resume attrib list.
	 *
	 * @return the resume attrib list
	 */
	public List<ProfileAttribDTO> getResumeAttribList() {
		return resumeAttribList;
	}


	/**
	 * Sets the resume attrib list.
	 *
	 * @param resumeAttribList the new resume attrib list
	 */
	public void setResumeAttribList(List<ProfileAttribDTO> resumeAttribList) {
		this.resumeAttribList = resumeAttribList;
	}


	/**
	 * Gets the list phone dtl.
	 *
	 * @return the list phone dtl
	 */
	public List<PhoneDetailDTO> getListPhoneDtl() {
		return listPhoneDtl;
	}


	/**
	 * Sets the list phone dtl.
	 *
	 * @param listPhoneDtl the new list phone dtl
	 */
	public void setListPhoneDtl(List<PhoneDetailDTO> listPhoneDtl) {
		this.listPhoneDtl = listPhoneDtl;
	}


	/**
	 * Gets the builder skills id.
	 *
	 * @return the builder skills id
	 */
	public int getBuilderSkillsId() {
		return builderSkillsId;
	}


	/**
	 * Sets the builder skills id.
	 *
	 * @param builderSkillsId the new builder skills id
	 */
	public void setBuilderSkillsId(int builderSkillsId) {
		this.builderSkillsId = builderSkillsId;
	}


	/**
	 * Gets the available date.
	 *
	 * @return the available date
	 */
	public String getAvailableDate() {
		return availableDate;
	}


	/**
	 * Sets the available date.
	 *
	 * @param availableDate the new available date
	 */
	public void setAvailableDate(String availableDate) {
		this.availableDate = availableDate;
	}


	public List<Integer> getSelectedList() {
		return selectedList;
	}


	public void setSelectedList(List<Integer> selectedList) {
		this.selectedList = selectedList;
	}


	/**
	 * @return the employmentTypes
	 */
	public List<String> getEmploymentTypes() {
		return employmentTypes;
	}


	/**
	 * @param employmentTypes the employmentTypes to set
	 */
	public void setEmploymentTypes(List<String> employmentTypes) {
		this.employmentTypes = employmentTypes;
	}
	/**
	 * @return the resumeViewed
	 */
	public boolean isResumeViewed() {
		return resumeViewed;
	}


	/**
	 * @param resumeViewed the resumeViewed to set
	 */
	public void setResumeViewed(boolean resumeViewed) {
		this.resumeViewed = resumeViewed;
	}
	
}
