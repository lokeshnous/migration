package com.advanceweb.afc.jb.resume.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class WorkExpForm {
	private String jobTitle;
	private String employerName;
	private String employmentType;
	private String startDate;
	private String endDate;
	private String yrsAtPostion;
	private String currentCareerLvl;
	private String annualSalary;
	private String hrlyPayRate;
	private String description;
	private boolean bPresent;
	private boolean bCurrentCareerLevel;
	private int builderEmpId;
	private int itemId;
	private boolean editMode;
	
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
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
	public String getYrsAtPostion() {
		return yrsAtPostion;
	}
	public void setYrsAtPostion(String yrsAtPostion) {
		this.yrsAtPostion = yrsAtPostion;
	}
	public String getCurrentCareerLvl() {
		return currentCareerLvl;
	}
	public void setCurrentCareerLvl(String currentCareerLvl) {
		this.currentCareerLvl = currentCareerLvl;
	}
	public String getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}
	public String getHrlyPayRate() {
		return hrlyPayRate;
	}
	public void setHrlyPayRate(String hrlyPayRate) {
		this.hrlyPayRate = hrlyPayRate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBuilderEmpId() {
		return builderEmpId;
	}
	public void setBuilderEmpId(int builderEmpId) {
		this.builderEmpId = builderEmpId;
	}	
	public boolean isbPresent() {
		return bPresent;
	}
	public void setbPresent(boolean bPresent) {
		this.bPresent = bPresent;
	}
	public boolean isbCurrentCareerLevel() {
		return bCurrentCareerLevel;
	}
	public void setbCurrentCareerLevel(boolean bCurrentCareerLevel) {
		this.bCurrentCareerLevel = bCurrentCareerLevel;
	}	
}
