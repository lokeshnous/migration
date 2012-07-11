package com.advanceweb.afc.jb.dropdowns;

import java.util.List;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;

public interface PopulateDropdowns {
	
	public List<CountryDTO> getCountryList();
	
	public List<EmploymentInfoDTO> getEmployementInfoList();
	
	public List<SubscriptionsDTO> getSubscriptionsList();
	
	public List<GenderDTO> getGenderList();
	
	public List<VeteranStatusDTO> getVeteranStatusList();
	
	public List<EthenticityDTO> getEthenticityList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of RadiusDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of RadiusDTO
	 * 
	 */
	public List<RadiusDTO> getRadiusList(); 
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of ExcludeFromDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of ExcludeFromDTO
	 * 
	 */
	public List<ExcludeFromDTO> getExcludeFromList(); 
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of FromZipcodeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of FromZipcodeDTO
	 *
	 */
	public List<FromZipcodeDTO> getFromZipcodeList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of StateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of StateDTO
	 *
	 */
	public List<StateDTO> getStateList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of MetroAreaDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of MetroAreaDTO
	 * 
	 */
	public List<MetroAreaDTO> getMetroAreaList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of EmploymentTypeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of EmploymentTypeDTO
	 * 
	 */
	public List<EmploymentTypeDTO> getEmploymentTypeList();
	
	/**@Author :Prince Mathew
	   @Purpose:To get the list of JobPostedDateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Return :List of JobPostedDateDTO
	 * 
	 */
	public List<JobPostedDateDTO> getJobPostedDateList();
	
	
	
}
