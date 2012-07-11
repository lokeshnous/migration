package com.advanceweb.afc.jb.webapp.web.forms.resume;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.advanceweb.afc.jb.webapp.web.forms.registration.ContactInfoForm;

public class CreateResume {
	private String resume_name;
	private String desired_job_title;
	private String desired_employment_type;
	private String work_authorization_US;
	private String willing_to_relocate;
	private String resume_visibility;
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
	private ContactInfoForm contactInfoForm;
	private int builderResumeId;
	//Creating Resume	
	
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

	public String getResume_name() {
		return resume_name;
	}
	public String getResumeType() {
		return resumeType;
	}
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
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
	
		
}
