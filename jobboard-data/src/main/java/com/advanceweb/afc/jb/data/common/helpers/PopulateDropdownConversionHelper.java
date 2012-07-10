package com.advanceweb.afc.jb.data.common.helpers;

import java.util.ArrayList;
import java.util.List;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.data.entities.MerLookup;

public class PopulateDropdownConversionHelper {
	
	public List<CountryDTO> convertMerUtilityToCountryDTO(List<MerLookup> merUtilityList){
		CountryDTO countryDTO = null;
		List<CountryDTO> list = new ArrayList();
		for(MerLookup merUtility : merUtilityList){
			countryDTO = new CountryDTO();
			countryDTO.setCountryId(String.valueOf(merUtility.getLookupId()));
			countryDTO.setCountryValue(merUtility.getLookupName());
			list.add(countryDTO);
		}		
		return list;		
	}
	
	
	public List<EmploymentInfoDTO> convertMerUtilityToEmploymentInfoDTO(List<MerLookup> merUtilityList){
		EmploymentInfoDTO employmentInfoDTO = null;
		List<EmploymentInfoDTO> list = new ArrayList();
		for(MerLookup merUtility : merUtilityList){
			employmentInfoDTO = new EmploymentInfoDTO();
			employmentInfoDTO.setInfoId(String.valueOf(merUtility.getLookupId()));
			employmentInfoDTO.setInfoName(merUtility.getLookupName());
			list.add(employmentInfoDTO);
		}		
		return list;		
	}
	
	public List<SubscriptionsDTO> convertMerUtilityToSubscriptionsDTO(List<MerLookup> merUtilityList){
		SubscriptionsDTO subscriptionsDTO = null;
		List<SubscriptionsDTO> list = new ArrayList();
		for(MerLookup merUtility : merUtilityList){
			subscriptionsDTO = new SubscriptionsDTO();
			subscriptionsDTO.setSubscriptionId(String.valueOf(merUtility.getLookupId()));
			subscriptionsDTO.setSubscriptionName(merUtility.getLookupName());
			list.add(subscriptionsDTO);
		}		
		return list;		
	}
	
	public List<CountryDTO> convertMerUtilityToDTO(List<MerLookup> merUtilityList){
		CountryDTO countryDTO = null;
		List<CountryDTO> list = new ArrayList();
		for(MerLookup merUtility : merUtilityList){
			countryDTO = new CountryDTO();
			countryDTO.setCountryId(String.valueOf(merUtility.getLookupId()));
			countryDTO.setCountryId(merUtility.getLookupName());
			list.add(countryDTO);
		}		
		return list;		
	}
	
	public List<GenderDTO> convertMerUtilityToGenderDTO(List<MerLookup> merUtilityList){
		GenderDTO genderDTO = null;
		List<GenderDTO> list = new ArrayList();
		for(MerLookup merUtility : merUtilityList){
			genderDTO = new GenderDTO();
			genderDTO.setGenderId(String.valueOf(merUtility.getLookupId()));
			genderDTO.setGenderName(merUtility.getLookupName());
			list.add(genderDTO);
		}		
		return list;		
	}
	
	public List<EthenticityDTO> convertMerUtilityToEthenticityDTO(List<MerLookup> merUtilityList){
		EthenticityDTO ethenticityDTO = null;
		List<EthenticityDTO> list = new ArrayList();
		for(MerLookup merUtility : merUtilityList){
			ethenticityDTO = new EthenticityDTO();
			ethenticityDTO.setEthencityId(String.valueOf(merUtility.getLookupId()));
			ethenticityDTO.setEthencityValue(merUtility.getLookupName());
			list.add(ethenticityDTO);
		}		
		return list;		
	}
	
	public List<VeteranStatusDTO> convertMerUtilityToVeteranStatusDTO(List<MerLookup> merUtilityList){
		VeteranStatusDTO veteranStatusDTO = null;
		List<VeteranStatusDTO> list = new ArrayList();
		for(MerLookup merUtility : merUtilityList){
			veteranStatusDTO = new VeteranStatusDTO();
			veteranStatusDTO.setStatusId(String.valueOf(merUtility.getLookupId()));
			veteranStatusDTO.setStatusValue(merUtility.getLookupName());
			list.add(veteranStatusDTO);
		}		
		return list;		
	}

}
