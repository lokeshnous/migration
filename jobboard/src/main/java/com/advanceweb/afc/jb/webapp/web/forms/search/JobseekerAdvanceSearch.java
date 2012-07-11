
package com.advanceweb.afc.jb.webapp.web.forms.search;

import java.util.List;

import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.StateDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will be used as a FormBean for the Advance Search
 */
public class JobseekerAdvanceSearch {
	

private String radius;
private String excludeFrom;
private String fromZipCode;
private String state;
private String metroArea;
private String employmentType;
private String jobpostedDate;
private String resultPerPage;

private List<RadiusDTO> radiusList; 
private List<ExcludeFromDTO> excludeFromList; 
private List<FromZipcodeDTO> fromZipcodeList;
private List<StateDTO> stateList;
private List<MetroAreaDTO> metroAreaList;
private List<EmploymentTypeDTO> employmentTypeList;
private List<JobPostedDateDTO> jobPostedDateList;


/**
 * @return the radiusList
 */

public List<RadiusDTO> getRadiusList() {
	return radiusList;
}

/**
 * @return the radius
 */
public String getRadius() {
	return radius;
}
/**
 * @param radius the radius to set
 */
public void setRadius(String radius) {
	this.radius = radius;
}
/**
 * @return the excludeFrom
 */
public String getExcludeFrom() {
	return excludeFrom;
}
/**
 * @param excludeFrom the excludeFrom to set
 */
public void setExcludeFrom(String excludeFrom) {
	this.excludeFrom = excludeFrom;
}
/**
 * @return the fromZipCode
 */
public String getFromZipCode() {
	return fromZipCode;
}
/**
 * @param fromZipCode the fromZipCode to set
 */
public void setFromZipCode(String fromZipCode) {
	this.fromZipCode = fromZipCode;
}
/**
 * @return the state
 */
public String getState() {
	return state;
}
/**
 * @param state the state to set
 */
public void setState(String state) {
	this.state = state;
}
/**
 * @return the metroArea
 */
public String getMetroArea() {
	return metroArea;
}
/**
 * @param metroArea the metroArea to set
 */
public void setMetroArea(String metroArea) {
	this.metroArea = metroArea;
}
/**
 * @return the employmentType
 */
public String getEmploymentType() {
	return employmentType;
}
/**
 * @param employmentType the employmentType to set
 */
public void setEmploymentType(String employmentType) {
	this.employmentType = employmentType;
}
/**
 * @return the jobpostedDate
 */
public String getJobpostedDate() {
	return jobpostedDate;
}
/**
 * @param jobpostedDate the jobpostedDate to set
 */
public void setJobpostedDate(String jobpostedDate) {
	this.jobpostedDate = jobpostedDate;
}
/**
 * @param radiusList the radiusList to set
 */
public void setRadiusList(List<RadiusDTO> radiusList) {
	radiusList = radiusList;
}
/**
 * @return the excludeFromList
 */
public List<ExcludeFromDTO> getExcludeFromList() {
	return excludeFromList;
}
/**
 * @param excludeFromList the excludeFromList to set
 */
public void setExcludeFromList(List<ExcludeFromDTO> excludeFromList) {
	excludeFromList = excludeFromList;
}
/**
 * @return the fromZipcodeList
 */
public List<FromZipcodeDTO> getFromZipcodeList() {
	return fromZipcodeList;
}
/**
 * @param fromZipcodeList the fromZipcodeList to set
 */
public void setFromZipcodeList(List<FromZipcodeDTO> fromZipcodeList) {
	fromZipcodeList = fromZipcodeList;
}
/**
 * @return the stateList
 */
public List<StateDTO> getStateList() {
	return stateList;
}
/**
 * @param stateList the stateList to set
 */
public void setStateList(List<StateDTO> stateList) {
	stateList = stateList;
}
/**
 * @return the metroAreaList
 */
public List<MetroAreaDTO> getMetroAreaList() {
	return metroAreaList;
}
/**
 * @param metroAreaList the metroAreaList to set
 */
public void setMetroAreaList(List<MetroAreaDTO> metroAreaList) {
	metroAreaList = metroAreaList;
}
/**
 * @return the employmentTypeList
 */
public List<EmploymentTypeDTO> getEmploymentTypeList() {
	return employmentTypeList;
}
/**
 * @param employmentTypeList the employmentTypeList to set
 */
public void setEmploymentTypeList(List<EmploymentTypeDTO> employmentTypeList) {
	employmentTypeList = employmentTypeList;
}
/**
 * @return the jobPostedDateList
 */
public List<JobPostedDateDTO> getJobPostedDateList() {
	return jobPostedDateList;
}
/**
 * @param jobPostedDateList the jobPostedDateList to set
 */
public void setJobPostedDateList(List<JobPostedDateDTO> jobPostedDateList) {
	jobPostedDateList = jobPostedDateList;
}

/**
 * @return the resultPerPage
 */
public String getResultPerPage() {
	return resultPerPage;
}

/**
 * @param resultPerPage the resultPerPage to set
 */
public void setResultPerPage(String resultPerPage) {
	this.resultPerPage = resultPerPage;
}

/**
 * @return the resultPerPageList
 */






}
