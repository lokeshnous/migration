package com.advanceweb.afc.jb.data.common.helpers;

import java.util.ArrayList;
import java.util.List;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.data.entities.MerUtility;

public class PopulateDropdownConversionHelper {
	
	public List<CountryDTO> convertMerUtilityToCountryDTO(List<MerUtility> merUtilityList){
		CountryDTO countryDTO = null;
		List<CountryDTO> list = new ArrayList();
		for(MerUtility merUtility : merUtilityList){
			countryDTO = new CountryDTO();
			countryDTO.setCountryId(String.valueOf(merUtility.getUtilityId()));
			countryDTO.setCountryValue(merUtility.getUtilityName());
			list.add(countryDTO);
		}		
		return list;		
	}
	
	
	public List<EmploymentInfoDTO> convertMerUtilityToEmploymentInfoDTO(List<MerUtility> merUtilityList){
		EmploymentInfoDTO employmentInfoDTO = null;
		List<EmploymentInfoDTO> list = new ArrayList();
		for(MerUtility merUtility : merUtilityList){
			employmentInfoDTO = new EmploymentInfoDTO();
			employmentInfoDTO.setInfoId(String.valueOf(merUtility.getUtilityId()));
			employmentInfoDTO.setInfoName(merUtility.getUtilityName());
			list.add(employmentInfoDTO);
		}		
		return list;		
	}
	
	public List<SubscriptionsDTO> convertMerUtilityToSubscriptionsDTO(List<MerUtility> merUtilityList){
		SubscriptionsDTO subscriptionsDTO = null;
		List<SubscriptionsDTO> list = new ArrayList();
		for(MerUtility merUtility : merUtilityList){
			subscriptionsDTO = new SubscriptionsDTO();
			subscriptionsDTO.setSubscriptionId(String.valueOf(merUtility.getUtilityId()));
			subscriptionsDTO.setSubscriptionName(merUtility.getUtilityName());
			list.add(subscriptionsDTO);
		}		
		return list;		
	}
	
	public List<CountryDTO> convertMerUtilityToDTO(List<MerUtility> merUtilityList){
		CountryDTO countryDTO = null;
		List<CountryDTO> list = new ArrayList();
		for(MerUtility merUtility : merUtilityList){
			countryDTO = new CountryDTO();
			countryDTO.setCountryId(String.valueOf(merUtility.getUtilityId()));
			countryDTO.setCountryId(merUtility.getUtilityName());
			list.add(countryDTO);
		}		
		return list;		
	}
	
	public List<GenderDTO> convertMerUtilityToGenderDTO(List<MerUtility> merUtilityList){
		GenderDTO genderDTO = null;
		List<GenderDTO> list = new ArrayList();
		for(MerUtility merUtility : merUtilityList){
			genderDTO = new GenderDTO();
			genderDTO.setGenderId(String.valueOf(merUtility.getUtilityId()));
			genderDTO.setGenderName(merUtility.getUtilityName());
			list.add(genderDTO);
		}		
		return list;		
	}
	
	public List<EthenticityDTO> convertMerUtilityToEthenticityDTO(List<MerUtility> merUtilityList){
		EthenticityDTO ethenticityDTO = null;
		List<EthenticityDTO> list = new ArrayList();
		for(MerUtility merUtility : merUtilityList){
			ethenticityDTO = new EthenticityDTO();
			ethenticityDTO.setEthencityId(String.valueOf(merUtility.getUtilityId()));
			ethenticityDTO.setEthencityValue(merUtility.getUtilityName());
			list.add(ethenticityDTO);
		}		
		return list;		
	}
	
	public List<VeteranStatusDTO> convertMerUtilityToVeteranStatusDTO(List<MerUtility> merUtilityList){
		VeteranStatusDTO veteranStatusDTO = null;
		List<VeteranStatusDTO> list = new ArrayList();
		for(MerUtility merUtility : merUtilityList){
			veteranStatusDTO = new VeteranStatusDTO();
			veteranStatusDTO.setStatusId(String.valueOf(merUtility.getUtilityId()));
			veteranStatusDTO.setStatusValue(merUtility.getUtilityName());
			list.add(veteranStatusDTO);
		}		
		return list;		
	}

}
