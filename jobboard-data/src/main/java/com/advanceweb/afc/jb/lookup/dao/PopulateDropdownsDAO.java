/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.lookup.dao;

/**
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.ResumeAttribListDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;

public interface PopulateDropdownsDAO {

	/**
	 * To populate Country Names
	 * 
	 * @return
	 */
	List<CountryDTO> getCountryList();

	/**
	 * To populate employment informations
	 * 
	 * @return
	 */
	List<EmploymentInfoDTO> getEmployementInfoList();

	/**
	 * To populate employment informations
	 * 
	 * @return
	 */
	List<JobAlertsDTO> getJobAlertsList();

	/**
	 * To populate employment informations
	 * 
	 * @return
	 */
	List<MagazinesDTO> getMagazinesList();

	/**
	 * To populate available subscriptions
	 * 
	 * @return
	 */
	List<DropDownDTO> getSubscriptionsList();

	/**
	 * To populate genders
	 * 
	 * @return
	 */
	List<GenderDTO> getGenderList();

	/**
	 * To populate veteran status
	 * 
	 * @return
	 */
	List<VeteranStatusDTO> getVeteranStatusList();

	/**
	 * To populate Ethnicity
	 * 
	 * @return
	 */
	List<EthenticityDTO> getEthenticityList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of RadiusDTO for the job seeker's advance search
	 * @Created:Jul 10, 2012
	 * @Return :List of RadiusDTO
	 * 
	 */
	List<RadiusDTO> getRadiusList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of ExcludeFromDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Return :List of ExcludeFromDTO
	 * 
	 */
	List<ExcludeFromDTO> getExcludeFromList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of FromZipcodeDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Return :List of FromZipcodeDTO
	 * 
	 */
	List<FromZipcodeDTO> getFromZipcodeList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of StateDTO for the job seeker's advance search
	 * @Created:Jul 10, 2012
	 * @Return :List of StateDTO
	 * 
	 */
	List<StateDTO> getStateList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of MetroAreaDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Return :List of MetroAreaDTO
	 * 
	 */
	List<MetroAreaDTO> getMetroAreaList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of EmploymentTypeDTO for the job seeker's
	 *             advance search
	 * @Created:Jul 10, 2012
	 * @Return :List of EmploymentTypeDTO
	 * 
	 */
	List<EmploymentTypeDTO> getEmploymentTypeList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of JobPostedDateDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Return :List of JobPostedDateDTO
	 * 
	 */
	List<JobPostedDateDTO> getJobPostedDateList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of ResumeVisibilityDTO
	 * @Created:Jul 10, 2012
	 * @Return :List of ResumeVisibilityDTO
	 * 
	 */
	List<ResumeVisibilityDTO> getResumeVisibilityList();

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the dropDown values
	 * @Created:Jul 10, 2012
	 * @Return :List of list of drop down
	 * 
	 */
	List<DropDownDTO> populateDropdown(String dropDownName);

	/**
	 * Populate resume dropdown.
	 *
	 * @param dropdownName the dropdown name
	 * @return the list
	 */
	List<ResumeAttribListDTO> populateResumeDropdown(String dropdownName);

	/**
	 * Populate resume builder dropdowns.
	 *
	 * @param dropdownName the dropdown name
	 * @return the list
	 */
	List<DropDownDTO> populateResumeBuilderDropdowns(String dropdownName);

	/**
	 * Populate education degrees dropdowns.
	 *
	 * @return the list
	 */
	List<DropDownDTO> populateEducationDegreesDropdowns();

	/**
	 * Populate job owners dropdown.
	 *
	 * @param facilityId the facility id
	 * @return the list
	 */
	List<DropDownDTO> populateJobOwnersDropdown(int facilityId);

	/**
	 * Populate branding template dropdown.
	 *
	 * @param facilityId the facility id
	 * @param userId the user id
	 * @return the list
	 */
	List<DropDownDTO> populateBrandingTemplateDropdown(int facilityId,
			int userId);

	/**
	 * Populate job posting type dropdowns.
	 *
	 * @param facilityId the facility id
	 * @return the list
	 */
	List<DropDownDTO> populateJobPostingTypeDropdowns(int facilityId);

	/**
	 * Populate job posting type dropdown.
	 *
	 * @param facilityId the facility id
	 * @param jobPostType the job post type
	 * @return the list
	 */
	public List<DropDownDTO> populateJobPostingTypeDropdown(int facilityId,
			int jobPostType);

	/**
	 * Populate city auto complete.
	 *
	 * @param city the city
	 * @return the list
	 */
	List<String> populateCityAutoComplete(String city);

	/**
	 * Populate state auto complete.
	 *
	 * @param city the city
	 * @return the string
	 */
	String populateStateAutoComplete(String city);

	/**
	 * Populate postal code auto complete.
	 *
	 * @param postalCode the postal code
	 * @return the list
	 */
	List<String> populatePostalCodeAutoComplete(String postalCode);

	/**
	 * The method help to get the list of companies in dropdown and highlight the main facility
	 * 
	 * @param facilityId
	 * @param isHighlightFacility
	 * @return
	 */
	List<DropDownDTO> populateCompanyNames(int facilityId, boolean isHighlightFacility);

	/**
	 * Populate template auto complete.
	 *
	 * @param company the company
	 * @return the list
	 */
	List<DropDownDTO> populateTemplateAutoComplete(String company);

	/**
	 * Gets the postal code.
	 *
	 * @param city the city
	 * @param state the state
	 * @return the postal code
	 */
	String getPostalCode(String city, String state);

	/**
	 * Gets the country.
	 *
	 * @param city the city
	 * @param state the state
	 * @param postalCode the postal code
	 * @return the country
	 */
	String getCountry(String city, String state, String postalCode);

	/**
	 * Populate location.
	 *
	 * @param postalCode the postal code
	 * @return the location dto
	 */
	LocationDTO populateLocation(String postalCode);

	/**
	 * Gets the job status list.
	 *
	 * @return the job status list
	 */
	Map<String, String> getJobStatusList();

	/**
	 * Method to get the subscription list for facility
	 * 
	 * @return
	 */
	List<DropDownDTO> getFacilitySubList();

	List<DropDownDTO> getBlockedCompanyList(int resumeId);

}
