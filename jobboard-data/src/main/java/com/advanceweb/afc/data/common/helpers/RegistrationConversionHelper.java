package com.advanceweb.afc.data.common.helpers;

import com.advanceweb.afc.common.MerUserDTO;
import com.advanceweb.afc.data.entities.MerUser;

public class RegistrationConversionHelper {

	/**
	 * This method is called to convert MerUserDTO to entity MerUser
	 * 
	 * @param dto
	 * @return
	 */
	public MerUser transformToMerUserFromDTo(MerUserDTO dto) {
		MerUser merUser = new MerUser();
		if (dto != null) {
			merUser.setFirstName(dto.getFirstName());
			merUser.setEmail(dto.getEmailId());
			merUser.setPassword(dto.getPassword());
			merUser.setLastName(dto.getLastName());
	
			// merUser.setUserId(userId);
		}
		return merUser;

	}

	/*
	 * public Address transformToMerUserFromDTo(MerUserDTO dto) { MerUser
	 * merUser = new MerUser(); merUser.setFirstName(dto.getFirstName());
	 * merUser.setEmail(dto.getEmailId());
	 * merUser.setPassword(dto.getPassword()); // merUser.setUserId(userId);
	 * return merUser;
	 * 
	 * }
	 */

}
