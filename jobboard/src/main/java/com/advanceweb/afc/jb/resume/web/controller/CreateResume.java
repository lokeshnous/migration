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

import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;

public class CreateResume {
	private String uploadResumeId;
	private String resumeName;
	private String desiredJobTitle;
	private String desiredEmploymentType;
	private String workAuthorizationUS;
	private String willingToRelocate;
	private String resumeVisibility;
	private String resumeType;
	private String employmentType;
	private String resumeText;
	private String resumeText1;
	private String filename;
	private CommonsMultipartFile fileData;
	
	//Creating Resume
	private String objective;
	private String skills;
	private String awards;
	private String memberships;
	private String otherDetails;
	private List<CertificationsForm> listCertForm;
	private List<EducationForm> listEduForm;
	private List<LanguageForm> listLangForm;
	private List<ReferenceForm> listRefForm;
	private List<WorkExpForm> listWorkExpForm;
	private List<PhoneDetailForm> listPhoneDtlForm;
	private ContactInfoForm contactInfoForm;
	private int builderResumeId;
	private int userId;
	List<ResumeProfileAttribForm> resumeProfileAttribForm; 
	//Creating Resume	
	
	public String getUploadResumeId() {
		return uploadResumeId;
	}
	public void setUploadResumeId(String uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getResumeText1() {
		return resumeText1;
	}
	public void setResumeText1(String resumeText1) {
		this.resumeText1 = resumeText1;
	}
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public String getWorkauthUS() {
		return workauthUS;
	}
	public void setWorkauthUS(String workauthUS) {
		this.workauthUS = workauthUS;
	}
	private String workauthUS;

	
	public String getResumeType() {
		return resumeType;
	}
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
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
	public ContactInfoForm getContactInfoForm() {
		return contactInfoForm;
	}
	public void setContactInfoForm(ContactInfoForm contactInfoForm) {
		this.contactInfoForm = contactInfoForm;
	}
	public List<CertificationsForm> getListCertForm() {
		return listCertForm;
	}
	public void setListCertForm(List<CertificationsForm> listCertForm) {
		this.listCertForm = listCertForm;
	}
	public List<EducationForm> getListEduForm() {
		return listEduForm;
	}
	public void setListEduForm(List<EducationForm> listEduForm) {
		this.listEduForm = listEduForm;
	}
	public List<LanguageForm> getListLangForm() {
		return listLangForm;
	}
	public void setListLangForm(List<LanguageForm> listLangForm) {
		this.listLangForm = listLangForm;
	}
	public List<ReferenceForm> getListRefForm() {
		return listRefForm;
	}
	public void setListRefForm(List<ReferenceForm> listRefForm) {
		this.listRefForm = listRefForm;
	}
	public List<WorkExpForm> getListWorkExpForm() {
		return listWorkExpForm;
	}
	public void setListWorkExpForm(List<WorkExpForm> listWorkExpForm) {
		this.listWorkExpForm = listWorkExpForm;
	}
	public int getBuilderResumeId() {
		return builderResumeId;
	}
	public void setBuilderResumeId(int builderResumeId) {
		this.builderResumeId = builderResumeId;
	}
	public List<ResumeProfileAttribForm> getResumeProfileAttribForm() {
		return resumeProfileAttribForm;
	}
	public void setResumeProfileAttribForm(
			List<ResumeProfileAttribForm> resumeProfileAttribForm) {
		this.resumeProfileAttribForm = resumeProfileAttribForm;
	}
	public List<PhoneDetailForm> getListPhoneDtlForm() {
		return listPhoneDtlForm;
	}
	public void setListPhoneDtlForm(List<PhoneDetailForm> listPhoneDtlForm) {
		this.listPhoneDtlForm = listPhoneDtlForm;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
		
}
