package com.advanceweb.afc.jb.data.common.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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
import com.advanceweb.afc.jb.data.entities.MerLookup;

@Repository("dropdownHelper")
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
	

	/*public List<CountryDTO> convertMerLookupToDTO(List<MerLookup> MerLookupList){
=======
	public List<CountryDTO> convertMerUtilityToDTO(List<MerLookup> merUtilityList){
>>>>>>> .r56
		CountryDTO countryDTO = null;
		List<CountryDTO> list = new ArrayList();
<<<<<<< .mine
		for(MerLookup merLookup : MerLookupList){
=======
		for(MerLookup merUtility : merUtilityList){
>>>>>>> .r56
			countryDTO = new CountryDTO();
<<<<<<< .mine
			countryDTO.setCountryId(String.valueOf(merLookup.getLookupId()));
			countryDTO.setCountryId(merLookup.getLookupName());
=======
			countryDTO.setCountryId(String.valueOf(merUtility.getLookupId()));
			countryDTO.setCountryId(merUtility.getLookupName());
>>>>>>> .r56
			list.add(countryDTO);
		}		
		return list;		
	}
<<<<<<< .mine
	*/

	
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

	
	
	/**@Author :Prince Mathew
	   @Purpose:TO convert the List of MerLookup to the List of RadiusDTO
	   @Created:Jul 10, 2012
	   @Param  :List of MerLookup
	   @Return :List of RadiusDTO
	  */
	public List<RadiusDTO> convertMerLookupToRadiusListDTO(List<MerLookup> merLookupList){
		
		RadiusDTO radiusDTO=null;
		List<RadiusDTO> list = new ArrayList<RadiusDTO>();
		for(MerLookup merLookup : merLookupList){
			radiusDTO = new RadiusDTO();
			radiusDTO.setRadiusId(String.valueOf(merLookup.getLookupId()));
			radiusDTO.setRadiusValue(merLookup.getLookupName());
			list.add(radiusDTO);
		}
		return list;
		} 
	
	
	/**
	   @Author :Prince Mathew
	   @Purpose:TO convert the List of MerLookup to the List of ExcludeFromDTO
	   @Created:Jul 10, 2012
	   @Param  :List of MerLookup
	   @Return :List of ExcludeFromDTO
	 * 
	 */
	public List<ExcludeFromDTO> convertMerLookupToExcludeFromListDTO(List<MerLookup> merLookupList){
		ExcludeFromDTO excludeFromDTO=null;
		List<ExcludeFromDTO> list = new ArrayList<ExcludeFromDTO>();
		for(MerLookup merLookup : merLookupList){
			excludeFromDTO = new ExcludeFromDTO();
			excludeFromDTO.setExcludeFromId(String.valueOf(merLookup.getLookupId()));
			excludeFromDTO.setExcludeFromValue(merLookup.getLookupName());
			list.add(excludeFromDTO);
		}
		return list;
		
	}
	
	
	/**
	   @Author :Prince Mathew
	   @Purpose:TO convert the List of MerLookup to the List of FromZipcodeDTO
	   @Created:Jul 10, 2012
	   @Param  :List of MerLookup
	   @Return :List of FromZipcodeDTO
	 * 
	 */
	public List<FromZipcodeDTO> convertMerLookupToFromZipcodeListDTO(List<MerLookup> merLookupList){
		FromZipcodeDTO fromZipcodeDTO=null;
		List<FromZipcodeDTO> list = new ArrayList<FromZipcodeDTO>();
		
		for(MerLookup merLookup : merLookupList){
			fromZipcodeDTO = new FromZipcodeDTO();
			fromZipcodeDTO.setFromZipcodeId(String.valueOf(merLookup.getLookupId()));
			fromZipcodeDTO.setFromZipcodeValue(merLookup.getLookupName());
			list.add(fromZipcodeDTO);
		}
		return list;
		
	}
	
	
	/**
	   @Author :Prince Mathew
	   @Purpose:TO convert the List of MerLookup to the List of StateDTO
	   @Created:Jul 10, 2012
	   @Param  :List of MerLookup
	   @Return :List of StateDTO
	 * 
	 */
	public List<StateDTO> convertMerLookupToStateListDTO(List<MerLookup> merLookupList){
		StateDTO stateDTO=null;
		List<StateDTO> list = new ArrayList<StateDTO>();
		
		for(MerLookup merLookup : merLookupList){
			stateDTO = new StateDTO();
			stateDTO.setStateId(String.valueOf(merLookup.getLookupId()));
			stateDTO.setStateValue(merLookup.getLookupName());
			list.add(stateDTO);
		}
		return list;}
	
	
	/**
	   @Author :Prince Mathew
	   @Purpose:TO convert the List of MerLookup to the List of MetroAreaDTO
	   @Created:Jul 10, 2012
	   @Param  :List of MerLookup
	   @Return :List of MetroAreaDTO
	 * 
	 */
	public List<MetroAreaDTO> convertMerLookupToMetroAreaListDTO(List<MerLookup> merLookupList){
		MetroAreaDTO metroAreaDTO=null;
		List<MetroAreaDTO> list = new ArrayList<MetroAreaDTO>();
		
		for(MerLookup merLookup : merLookupList){
			metroAreaDTO = new MetroAreaDTO();
			metroAreaDTO.setMetroAreaId(String.valueOf(merLookup.getLookupId()));
			metroAreaDTO.setMetroAreaValue(merLookup.getLookupName());
			list.add(metroAreaDTO);
		}
		return list;}
	
	
	/**
	   @Author :Prince Mathew
	   @Purpose:TO convert the List of MerLookup to the List of EmploymentTypeDTO
	   @Created:Jul 10, 2012
	   @Param  :List of MerLookup
	   @Return :List of EmploymentTypeDTO
	 * 
	 */
	public List<EmploymentTypeDTO> convertMerLookupToEmploymentTypeListDTO(List<MerLookup> merLookupList){
		
		EmploymentTypeDTO employmentTypeDTO=null;
		List<EmploymentTypeDTO> list = new ArrayList<EmploymentTypeDTO>();
		
		for(MerLookup merLookup : merLookupList){
			employmentTypeDTO = new EmploymentTypeDTO();
			employmentTypeDTO.setEmploymentTypeId(String.valueOf(merLookup.getLookupId()));
			employmentTypeDTO.setEmploymentTypeValue(merLookup.getLookupName());
			list.add(employmentTypeDTO);
		}
		return list;}
	
	
	/**
	   @Author :Prince Mathew
	   @Purpose:TO convert the List of MerLookup to the List of JobPostedDateDTO
	   @Created:Jul 10, 2012
	   @Param  :List of MerLookup
	   @Return :List of JobPostedDateDTO
	 * 
	 */
	public List<JobPostedDateDTO> convertMerLookupToJobPostedDateListDTO(List<MerLookup> merLookupList){
		JobPostedDateDTO jobPostedDateDTO=null;
		List<JobPostedDateDTO> list = new ArrayList<JobPostedDateDTO>();
		
		for(MerLookup merLookup : merLookupList){
			jobPostedDateDTO = new JobPostedDateDTO();
			jobPostedDateDTO.setJobPostedDateId(String.valueOf(merLookup.getLookupId()));
			jobPostedDateDTO.setJobPostedDateValue(merLookup.getLookupName());
			list.add(jobPostedDateDTO);
		}
		return list;
	}
	
	/**
	   @Author :Sasibhushan
	   @Purpose:TO convert the List of MerLookup to the List of JobAlertsDTO
	   @Created:Jul 12, 2012
	   @Param  :List of MerLookup
	   @Return :List of JobAlertsDTO
	 * 
	 */
	public List<JobAlertsDTO> convertMerLookupToJobAlertsDTO(List<MerLookup> merLookupList){
	
		List<JobAlertsDTO> list = new ArrayList<JobAlertsDTO>();
		
		for(MerLookup merLookup : merLookupList){
			JobAlertsDTO jobAlertsDTO=new JobAlertsDTO();
			jobAlertsDTO.setAlertId(String.valueOf(merLookup.getLookupId()));
			jobAlertsDTO.setAlertName(merLookup.getLookupName());
			list.add(jobAlertsDTO);
		}
		return list;
	}
	
	/**
	   @Author :Sasibhushan
	   @Purpose:TO convert the List of MerLookup to the List of Magazines DTO
	   @Created:Jul 12, 2012
	   @Param  :List of MerLookup
	   @Return :List of MagazinesDTO
	 * 
	 */
	public List<MagazinesDTO> convertMerLookupToMagazinesDTO(List<MerLookup> merLookupList){
	
		List<MagazinesDTO> list = new ArrayList<MagazinesDTO>();
		
		for(MerLookup merLookup : merLookupList){
			MagazinesDTO magazinesDTO=new MagazinesDTO();
			magazinesDTO.setMagazineId(String.valueOf(merLookup.getLookupId()));
			magazinesDTO.setMagazineName(merLookup.getLookupName());
			list.add(magazinesDTO);
		}
		return list;
	}
	
}
