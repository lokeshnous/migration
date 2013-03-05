/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;

public class CreateResume {
	
	/** The upload resume id. */
	private String uploadResumeId;
	
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
	
	/** The resume text. */
	private String resumeText;
	
	/** The resume text1. */
	private String resumeText1;
	
	/** The filename. */
	private String filename;
	
	/** The file path. */
	private String filePath;
	
	/** The b hide back button. */
	private boolean bHideBackButton;
	
	/** The file data. */
	private CommonsMultipartFile fileData;
	
	//Creating Resume
	/** The available date. */
	private String availableDate;
	
	/** The objective. */
	private String objective;
	
	/** The skills. */
	private String skills;
	
	/** The builder skills id. */
	private int builderSkillsId;
	
	/** The awards. */
	private String awards;
	
	/** The memberships. */
	private String memberships;
	
	/** The other details. */
	private String otherDetails;
	
	/** The list cert form. */
	private List<CertificationsForm> listCertForm;
	
	/** The list edu form. */
	private List<EducationForm> listEduForm;
	
	/** The list lang form. */
	private List<LanguageForm> listLangForm;
	
	/** The list ref form. */
	private List<ReferenceForm> listRefForm;
	
	/** The list work exp form. */
	private List<WorkExpForm> listWorkExpForm;
	
	/** The list phone dtl form. */
	private List<PhoneDetailForm> listPhoneDtlForm;
	
	/** The contact info form. */
	private ContactInfoForm contactInfoForm;
	
	/** The builder resume id. */
	private int builderResumeId;
	
	/** The user id. */
	private int userId;
	
	/** The total progress. */
	private Long totalProgress = 0L;
	
	/** The virus found. */
	private boolean virusFound=false;
	
	/** The description. */
	private String description;
	private List<String> companyName;
	private String searchComapnyName;
	private List<Integer> selectedList;
	private List<String> availableList;
	private List<DropDownDTO> selectedLst;
	//Creating Resume	
	
	/**
	 * Gets the upload resume id.
	 *
	 * @return the upload resume id
	 */
	public String getUploadResumeId() {
		return uploadResumeId;
	}
	
	/**
	 * Sets the upload resume id.
	 *
	 * @param uploadResumeId the new upload resume id
	 */
	public void setUploadResumeId(String uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
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
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
	 * Gets the resume text1.
	 *
	 * @return the resume text1
	 */
	public String getResumeText1() {
		return resumeText1;
	}
	
	/**
	 * Sets the resume text1.
	 *
	 * @param resumeText1 the new resume text1
	 */
	public void setResumeText1(String resumeText1) {
		this.resumeText1 = resumeText1;
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
	 * Gets the workauth us.
	 *
	 * @return the workauth us
	 */
	public String getWorkauthUS() {
		return workauthUS;
	}
	
	/**
	 * Sets the workauth us.
	 *
	 * @param workauthUS the new workauth us
	 */
	public void setWorkauthUS(String workauthUS) {
		this.workauthUS = workauthUS;
	}
	
	/** The workauth us. */
	private String workauthUS;

	
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
	 * Gets the contact info form.
	 *
	 * @return the contact info form
	 */
	public ContactInfoForm getContactInfoForm() {
		return contactInfoForm;
	}
	
	/**
	 * Sets the contact info form.
	 *
	 * @param contactInfoForm the new contact info form
	 */
	public void setContactInfoForm(ContactInfoForm contactInfoForm) {
		this.contactInfoForm = contactInfoForm;
	}
	
	/**
	 * Gets the list cert form.
	 *
	 * @return the list cert form
	 */
	public List<CertificationsForm> getListCertForm() {
		return listCertForm;
	}
	
	/**
	 * Sets the list cert form.
	 *
	 * @param listCertForm the new list cert form
	 */
	public void setListCertForm(List<CertificationsForm> listCertForm) {
		this.listCertForm = listCertForm;
	}
	
	/**
	 * Gets the list edu form.
	 *
	 * @return the list edu form
	 */
	public List<EducationForm> getListEduForm() {
		return listEduForm;
	}
	
	/**
	 * Sets the list edu form.
	 *
	 * @param listEduForm the new list edu form
	 */
	public void setListEduForm(List<EducationForm> listEduForm) {
		this.listEduForm = listEduForm;
	}
	
	/**
	 * Gets the list lang form.
	 *
	 * @return the list lang form
	 */
	public List<LanguageForm> getListLangForm() {
		return listLangForm;
	}
	
	/**
	 * Sets the list lang form.
	 *
	 * @param listLangForm the new list lang form
	 */
	public void setListLangForm(List<LanguageForm> listLangForm) {
		this.listLangForm = listLangForm;
	}
	
	/**
	 * Gets the list ref form.
	 *
	 * @return the list ref form
	 */
	public List<ReferenceForm> getListRefForm() {
		return listRefForm;
	}
	
	/**
	 * Sets the list ref form.
	 *
	 * @param listRefForm the new list ref form
	 */
	public void setListRefForm(List<ReferenceForm> listRefForm) {
		this.listRefForm = listRefForm;
	}
	
	/**
	 * Gets the list work exp form.
	 *
	 * @return the list work exp form
	 */
	public List<WorkExpForm> getListWorkExpForm() {
		return listWorkExpForm;
	}
	
	/**
	 * Sets the list work exp form.
	 *
	 * @param listWorkExpForm the new list work exp form
	 */
	public void setListWorkExpForm(List<WorkExpForm> listWorkExpForm) {
		this.listWorkExpForm = listWorkExpForm;
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
	 * Gets the list phone dtl form.
	 *
	 * @return the list phone dtl form
	 */
	public List<PhoneDetailForm> getListPhoneDtlForm() {
		return listPhoneDtlForm;
	}
	
	/**
	 * Sets the list phone dtl form.
	 *
	 * @param listPhoneDtlForm the new list phone dtl form
	 */
	public void setListPhoneDtlForm(List<PhoneDetailForm> listPhoneDtlForm) {
		this.listPhoneDtlForm = listPhoneDtlForm;
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
	 * Gets the total progress.
	 *
	 * @return the total progress
	 */
	public Long getTotalProgress() {
		return totalProgress;
	}
	
	/**
	 * Sets the total progress.
	 *
	 * @param totalProgress the new total progress
	 */
	public void setTotalProgress(Long totalProgress) {
		this.totalProgress = totalProgress;
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
	 * Checks if is b hide back button.
	 *
	 * @return true, if is b hide back button
	 */
	public boolean isbHideBackButton() {
		return bHideBackButton;
	}
	
	/**
	 * Sets the b hide back button.
	 *
	 * @param bHideBackButton the new b hide back button
	 */
	public void setbHideBackButton(boolean bHideBackButton) {
		this.bHideBackButton = bHideBackButton;
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
	/**
	 * @return the virusFound
	 */
	public boolean isVirusFound() {
		return virusFound;
	}
	/**
	 * @param virusFound the virusFound to set
	 */
	public void setVirusFound(boolean virusFound) {
		this.virusFound = virusFound;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getCompanyName() {
		return companyName;
	}
	public void setCompanyName(List<String> companyName) {
		this.companyName = companyName;
	}
	public String getSearchComapnyName() {
		return searchComapnyName;
	}
	public void setSearchComapnyName(String searchComapnyName) {
		this.searchComapnyName = searchComapnyName;
	}
	public List<Integer> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<Integer> selectedList) {
		this.selectedList = selectedList;
	}
	public List<String> getAvailableList() {
		return availableList;
	}
	public void setAvailableList(List<String> availableList) {
		this.availableList = availableList;
	}
	/**
	 * @return the selectedLst
	 */
	public List<DropDownDTO> getSelectedLst() {
		return selectedLst;
	}
	/**
	 * @param selectedLst the selectedLst to set
	 */
	public void setSelectedLst(List<DropDownDTO> selectedLst) {
		this.selectedLst = selectedLst;
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
}
