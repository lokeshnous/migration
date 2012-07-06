package com.advanceweb.afc.jb.dropdowns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;

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
	public List<SubscriptionsDTO> getSubscriptionsList() {
		
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

}
