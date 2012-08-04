package com.advanceweb.afc.jb.user.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerProfileAttribList;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;

@Repository("registrationConversionHelper")
public class RegistrationConversionHelper {

	/**
	 * Transform MerUserDTO to entity MerUser	 
	 * @param dto
	 * @return
	 */
	public MerUser transformMerUserDTOToMerUser(JobSeekerRegistrationDTO dto) {

		MerUserDTO userDTO = dto.getMerUserDTO();
		AddressDTO addDTO = dto.getAddressDTO();
		
		List<MerUserProfile> listProfiles = new ArrayList<MerUserProfile>();
		MerUserProfile profile = new MerUserProfile();
		MerUser entity = new MerUser();
		
		JobSeekerProfileDTO profileDTO = dto.getJobSeekerProfileDTO();
		if (userDTO != null) {
			entity.setFirstName(userDTO.getFirstName());
			entity.setMiddleName(userDTO.getMiddleName());
			entity.setEmail(userDTO.getEmailId());
			entity.setPassword(userDTO.getPassword());
			entity.setLastName(userDTO.getLastName());
			
			if(userDTO.getUserId() != 0){
				entity.setUserId(userDTO.getUserId());
			}
		}
	
/*		if(addDTO != null){
			profile.setCity(addDTO.getCity());
			profile.setCountry(addDTO.getCountry());
			profile.setState(addDTO.getState());
			profile.setStreetAddress2(addDTO.getAddress2());
			profile.setStreetAddress1(addDTO.getAddress1());
			profile.setPhone(addDTO.getPhone());
			profile.setIndustry(userDTO.getIndustry());
			profile.setJobTitle(userDTO.getJobTitle());
			profile.setProfession(userDTO.getProfession());
			profile.setSpeciality(userDTO.getSpeciality());	
			profile.setZip(addDTO.getZipCode());
		}
		
		if(profileDTO != null){
			profile.setEthinicity(profileDTO.getEthinicity());
			profile.setVeteranStatus(profileDTO.getVeteranStatus());	
			profile.setGender(profileDTO.getGender());
			if(profileDTO.getProfileId() != 0){
				profile.setUserProfileId(userDTO.getUserId());
			}

		}*/
		listProfiles.add(profile);
		profile.setMerUser(entity);
		entity.setMerUserProfiles(listProfiles);
				
		return entity;

	}
	
	/**
	 * Transform MerUserDTO to entity MerUser	 
	 * @param dto
	 * @return
	 */
	public MerUserProfile transformMerUserDTOToMerUserProfile(JobSeekerRegistrationDTO dto) {
		MerUserProfile entity = new MerUserProfile();
		MerUserDTO userDTO = dto.getMerUserDTO();
		AddressDTO addDTO = dto.getAddressDTO();
		JobSeekerProfileDTO profileDTO = dto.getJobSeekerProfileDTO();
		
/*		if(addDTO != null){
			entity.setCity(addDTO.getCity());
			entity.setCountry(addDTO.getCountry());
			entity.setState(addDTO.getState());
			entity.setStreetAddress2(addDTO.getAddress2());
			entity.setStreetAddress1(addDTO.getAddress1());
			entity.setPhone(addDTO.getPhone());
			entity.setIndustry(userDTO.getIndustry());
			entity.setJobTitle(userDTO.getJobTitle());
			entity.setProfession(userDTO.getProfession());
			entity.setSpeciality(userDTO.getSpeciality());	
		}
		
		if(profileDTO != null){
			entity.setEthinicity(profileDTO.getEthinicity());
			entity.setVeteranStatus(profileDTO.getVeteranStatus());

		}*/
		
		return entity;

	}
	
	
	/**
	 * Transform MerUser to MerUserDTO
	 * @param dto
	 * @return
	 */
	public MerUserDTO transformMerUserToMerUserDTO(MerUser entity) {
		MerUserDTO dto = new MerUserDTO();
		if (entity != null) {
			dto.setEmailId(entity.getEmail());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setMiddleName(entity.getMiddleName());
			dto.setPassword(entity.getPassword());
//			dto.setJobTitle(entity.getJobTitle());
//			dto.setProfession(entity.getProfession());
//			dto.setSpeciality(entity.getSpeciality());
//			dto.setIndustry(entity.getIndustry());
		}
		return dto;

	}
	
	/**
	 * Transform MerUser to AddressDTO
	 * @param dto
	 * @return
	 */
	public AddressDTO transformMerUserToAddDTO(MerUser entity) {
		AddressDTO dto = new AddressDTO();
		if (entity != null) {
//			dto.setCity(entity.);
//			dto.setAddress1(address1);
//			dto.setAddress2(address2);
//			dto.setCountry(entity.getCountryLocationId());
//			dto.setPhone(entity.getMobileNo());		
//			dto.setState(entity.g);
//			dto.setStreet(entity.get);
//			dto.setZipCode(entity.getZipCodeLocationId());
		}
		return dto;

	}
	
	/**
	 * Transform MerUser to MerUserDTO
	 * @param dto
	 * @return
	 */
	public JobSeekerProfileDTO transformMerUserToProfileDTO(MerUser entity) {
		JobSeekerProfileDTO dto = new JobSeekerProfileDTO();
		if (entity != null) {
//			dto.setEmploymentInformation(String.valueOf(entity.getEmpinfoLookupId()));
//			dto.setEthinicity(String.valueOf(entity.getEthinicityLookupId()));
//			dto.setGender(String.valueOf(entity.getGender()));
//			dto.setVeteranStatus(String.valueOf(entity.getVeteranLookupId()));
		}
		return dto;

	}
	
	/**
	 * 
	 * @param listProfAttrib
	 * @param countryList
	 * @param stateList
	 * @return
	 */
	public ResumeDTO transformProfileAttrib(List<MerProfileAttrib> listProfAttrib,List<DropDownDTO> countryList, List<DropDownDTO> stateList){
		
		ResumeDTO resumeDTO = new ResumeDTO();
		List<MerProfileAttribDTO> listDTO = new ArrayList<MerProfileAttribDTO>();
		if(null != listProfAttrib){
			for(MerProfileAttrib entity : listProfAttrib){
				MerProfileAttribDTO dto = new MerProfileAttribDTO();
				dto.setStrAttribType(entity.getFormType());
				dto.setStrLabelName(entity.getName());
				dto.setStrProfileAttribId(String.valueOf(entity.getProfileAttribId()));
				dto.setStrScreenName(entity.getScreenName());
				dto.setStrSectionName(entity.getSectionName());
				
				if(dto.getStrAttribType().equals(MMJBCommonConstants.DROP_DOWN) || dto.getStrAttribType().equals(MMJBCommonConstants.CHECK_BOX)){
					//populating countries
					if(dto.getStrLabelName().equals(MMJBCommonConstants.LABEL_COUNTRY)){
						dto.setDropdown(countryList);
						
					}else if(dto.getStrLabelName().equals(MMJBCommonConstants.LABEL_STATE)){
						dto.setDropdown(stateList);	//populating states
						
					}else{
						List<MerProfileAttribList> dropdownVals = entity.getMerProfileAttribLists();
						dto.setDropdown(transformToDropDownDTO(dropdownVals));
					}
				}							
				listDTO.add(dto);
			}
		}
		resumeDTO.setAttribList(listDTO);
		return resumeDTO;		
	}
	
	/**
	 * Converting list of MerProfileAttribList to list of DropDownDTO's
	 */
	public List<DropDownDTO> transformToDropDownDTO(List<MerProfileAttribList> dropdownVals){
		
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		if(null != dropdownVals){
			for(MerProfileAttribList attrib : dropdownVals){
				DropDownDTO dto = new DropDownDTO();
				dto.setOptionId(String.valueOf(attrib.getProfileAttribListId()));
				dto.setOptionName(attrib.getListValue());
				dropdownList.add(dto);
			}
		}
		return dropdownList;
	}
	
	public List<DropDownDTO> convertMerUtilityToDropDownDTO(List<Object> merUtilityList){

		DropDownDTO dropDownDTO = null;
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();

		for(Object merUtility : merUtilityList){
			dropDownDTO = new DropDownDTO();
			dropDownDTO.setOptionId((String)merUtility);
			dropDownDTO.setOptionName((String)merUtility);
			list.add(dropDownDTO);
		}		
		return list;		
	}
	
}
