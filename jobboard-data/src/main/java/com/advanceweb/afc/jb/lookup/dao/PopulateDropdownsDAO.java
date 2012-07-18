package com.advanceweb.afc.jb.lookup.dao;

/**
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

import java.util.List;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;

public interface PopulateDropdownsDAO {
	
	/**
	 * To populate Country Names
	 * @return
	 */
	public List<CountryDTO> getCountryList();
	
	/**
	 * To populate employment informations
	 * @return
	 */
	public List<EmploymentInfoDTO> getEmployementInfoList();
	
	/**
	 * To populate employment informations
	 * @return
	 */
	public List<JobAlertsDTO> getJobAlertsList();
	
	/**
	 * To populate employment informations
	 * @return
	 */
	public List<MagazinesDTO> getMagazinesList();
	
	/**
	 * To populate available subscriptions
	 * @return
	 */
	public List<SubscriptionsDTO> getSubscriptionsList();
	
	/**
	 * To populate genders
	 * @return
	 */
	public List<GenderDTO> getGenderList();
	
	/**
	 * To populate veteran status
	 * @return
	 */
	public List<VeteranStatusDTO> getVeteranStatusList();
	
	/**
	 * To populate Ethnicity
	 * @return
	 */
	public List<EthenticityDTO> getEthenticityList();

	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of RadiusDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of RadiusDTO
	 * 
	 */
	List<RadiusDTO> getRadiusList(); 
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of ExcludeFromDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of ExcludeFromDTO
	 * 
	 */
	List<ExcludeFromDTO> getExcludeFromList(); 
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of FromZipcodeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of FromZipcodeDTO
	 *
	 */
	List<FromZipcodeDTO> getFromZipcodeList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of StateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of StateDTO
	 *
	 */
	List<StateDTO> getStateList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of MetroAreaDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of MetroAreaDTO
	 * 
	 */
	List<MetroAreaDTO> getMetroAreaList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of EmploymentTypeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of EmploymentTypeDTO
	 * 
	 */
	List<EmploymentTypeDTO> getEmploymentTypeList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of JobPostedDateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of JobPostedDateDTO
	 * 
	 */
	List<JobPostedDateDTO> getJobPostedDateList();
}
