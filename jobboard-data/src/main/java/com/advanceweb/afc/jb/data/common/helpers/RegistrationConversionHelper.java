package com.advanceweb.afc.jb.data.common.helpers;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.MerUser;

@Repository("registrationConversionHelper")
public class RegistrationConversionHelper {

	/**
	 * Transform MerUserDTO to entity MerUser	 
	 * @param dto
	 * @return
	 */
	public MerUser transformMerUserDTOToMerUser(MerUserDTO dto) {
		MerUser entity = new MerUser();
		if (dto != null) {
			entity.setFirstName(dto.getFirstName());
			entity.setEmail(dto.getEmailId());
			entity.setPassword(dto.getPassword());
			entity.setLastName(dto.getLastName());
	
			// merUser.setUserId(userId);
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
