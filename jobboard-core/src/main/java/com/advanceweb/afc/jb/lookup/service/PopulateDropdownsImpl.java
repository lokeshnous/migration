package com.advanceweb.afc.jb.lookup.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO;

@Service("populateDropdownsService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
public class PopulateDropdownsImpl implements PopulateDropdowns{

	@Autowired
	private PopulateDropdownsDAO populateDropdownsDAO;
	
	@Override
	public List<CountryDTO> getCountryList() {

		return populateDropdownsDAO.getCountryList();
	}

	@Override
	public List<EmploymentInfoDTO> getEmployementInfoList() {
		
		return populateDropdownsDAO.getEmployementInfoList();
	}

	@Override
	public List<DropDownDTO> getSubscriptionsList() {
		
		return populateDropdownsDAO.getSubscriptionsList();
	}

	@Override
	public List<GenderDTO> getGenderList() {

		return populateDropdownsDAO.getGenderList();
	}

	@Override
	public List<VeteranStatusDTO> getVeteranStatusList() {

		return populateDropdownsDAO.getVeteranStatusList();
	}

	@Override
	public List<EthenticityDTO> getEthenticityList() {

		return populateDropdownsDAO.getEthenticityList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of RadiusDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of RadiusDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getRadiusList()
	 */
	@Override
	public List<RadiusDTO> getRadiusList() {
		return populateDropdownsDAO.getRadiusList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of ExcludeFromDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of ExcludeFromDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getExcludeFromList()
	 */
	@Override
	public List<ExcludeFromDTO> getExcludeFromList() {
		return populateDropdownsDAO.getExcludeFromList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of FromZipcodeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of FromZipcodeDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getFromZipcodeList()
	 */
	@Override
	public List<FromZipcodeDTO> getFromZipcodeList() {
		return populateDropdownsDAO.getFromZipcodeList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of StateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of StateDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getStateList()
	 */
	@Override
	public List<StateDTO> getStateList() {
		return populateDropdownsDAO.getStateList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of MetroAreaDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of MetroAreaDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getMetroAreaList()
	 */
	@Override
	public List<MetroAreaDTO> getMetroAreaList() {
		return populateDropdownsDAO.getMetroAreaList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of EmploymentTypeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of EmploymentTypeDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getEmploymentTypeList()
	 */
	@Override
	public List<EmploymentTypeDTO> getEmploymentTypeList() {
		return populateDropdownsDAO.getEmploymentTypeList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of JobPostedDateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of JobPostedDateDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getJobPostedDateList()
	 */
	@Override
	public List<JobPostedDateDTO> getJobPostedDateList() {
		return populateDropdownsDAO.getJobPostedDateList();
	}

	@Override
	public List<JobAlertsDTO> getJobAlertsList() {
		return populateDropdownsDAO.getJobAlertsList();
	}

	@Override
	public List<MagazinesDTO> getMagazinesList() {
		return populateDropdownsDAO.getMagazinesList();
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of ResumeVisibilityDTO for the resume
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of ResumeVisibilityDTO
	 * @see com.advanceweb.afc.jb.lookup.service.PopulateDropdowns#getResumeVisibilityList()
	 */
	@Override
	public List<ResumeVisibilityDTO> getResumeVisibilityList() {
		return populateDropdownsDAO.getResumeVisibilityList();
	}

	@Override
	public List<DropDownDTO> populateDropdown(String dropDownName) {
		
		return populateDropdownsDAO.populateDropdown(dropDownName);
	}

	@Override
	public List<ResumeAttribListDTO> populateResumeDropdown(String dropdownName) {
		return populateDropdownsDAO.populateResumeDropdown(dropdownName);
	}

	@Override
	public List<DropDownDTO> populateResumeBuilderDropdowns(String dropdownName) {
		return populateDropdownsDAO.populateResumeBuilderDropdowns(dropdownName);
	}

	@Override
	public List<DropDownDTO> populateEducationDegreesDropdowns() {	
		return populateDropdownsDAO.populateEducationDegreesDropdowns();
	}

	@Override
	public List<DropDownDTO> populateJobOwnersDropdown(int facilityId, int userId, int roleId) {
		return populateDropdownsDAO.populateJobOwnersDropdown(facilityId, userId, roleId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<DropDownDTO> populateBrandingTemplateDropdown(int facilityId, int userId) {

		return populateDropdownsDAO.populateBrandingTemplateDropdown(facilityId, userId);
	}

	@Override
	public List<DropDownDTO> populateJobPostingTypeDropdowns(int facilityId) {
		
		return populateDropdownsDAO.populateJobPostingTypeDropdowns(facilityId);
	}

	@Override
	public List<String> populateCityAutoComplete(String city) {
		
		return populateDropdownsDAO.populateCityAutoComplete(city);
	}

	@Override
	public String populateStateAutoComplete(String city) {
		
		return populateDropdownsDAO.populateStateAutoComplete(city);
	}

	@Override
	public List<String> populatePostalCodeAutoComplete(String postalCode) {
		return populateDropdownsDAO.populatePostalCodeAutoComplete(postalCode);
	}

	@Override
	public String getPostalCode(String city, String state) {
		return populateDropdownsDAO.getPostalCode(city, state);
	}

	@Override
	public String getCountry(String city, String state, String postalCode) {
		
		return populateDropdownsDAO.getCountry(city, state, postalCode);
	}

	@Override
	public LocationDTO populateLocation(String postalCode) {
		return populateDropdownsDAO.populateLocation(postalCode);
	}	
	@Override
	public Map<String,String> getJobStatusList() {
		return populateDropdownsDAO.getJobStatusList();
	}	
}
