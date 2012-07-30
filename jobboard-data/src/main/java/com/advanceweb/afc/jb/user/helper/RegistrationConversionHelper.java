package com.advanceweb.afc.jb.user.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.MerUser;

@Repository("registrationConversionHelper")
public class RegistrationConversionHelper {

	/**
	 * Transform MerUserDTO to entity MerUser	 
	 * @param dto
	 * @return
	 */
	public MerUser transformMerUserDTOToMerUser(JobSeekerRegistrationDTO dto) {
		MerUser entity = new MerUser();
		MerUserDTO userDTO = dto.getMerUserDTO();
		AddressDTO addDTO = dto.getAddressDTO();
		JobSeekerProfileDTO profileDTO = dto.getJobSeekerProfileDTO();
		if (userDTO != null) {
			entity.setFirstName(userDTO.getFirstName());
			entity.setMiddleName(userDTO.getMiddleName());
			entity.setEmail(userDTO.getEmailId());
			entity.setPassword(userDTO.getPassword());
			entity.setLastName(userDTO.getLastName());
			entity.setIndustry(userDTO.getIndustry());
			entity.setProfession(userDTO.getProfession());
			entity.setSpeciality(userDTO.getSpeciality());
			entity.setJobTitle(userDTO.getJobTitle());
			// merUser.setUserId(userId);
		}
		
		if(addDTO != null){
			entity.setMobileNo(addDTO.getPhone());
			entity.setCountryLocationId(addDTO.getCountry());
			entity.setZipCodeLocationId(addDTO.getZipCode());		
		}
		
		if(profileDTO != null){
			entity.setGender(profileDTO.getGender());
			entity.setEthinicityLookupId(Integer.valueOf(profileDTO.getEthinicity()));
			entity.setEmpinfoLookupId(Integer.valueOf(profileDTO.getEmploymentInformation()));
			entity.setVeteranLookupId(Integer.valueOf(profileDTO.getVeteranStatus()));
		}
		
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
//			dto.setMiddleName(entity.get);
			dto.setPassword(entity.getPassword());
	
			// merUser.setUserId(userId);
		}
		return dto;

	}

}
