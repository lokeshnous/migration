package com.advanceweb.afc.jb.dropdowns;

import java.util.List;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;

public interface PopulateDropdownsDAO {
	
	public List<CountryDTO> getCountryList();
	
	public List<EmploymentInfoDTO> getEmployementInfoList();
	
	public List<SubscriptionsDTO> getSubscriptionsList();
	
	public List<GenderDTO> getGenderList();
	
	public List<VeteranStatusDTO> getVeteranStatusList();
	
	public List<EthenticityDTO> getEthenticityList();

}
