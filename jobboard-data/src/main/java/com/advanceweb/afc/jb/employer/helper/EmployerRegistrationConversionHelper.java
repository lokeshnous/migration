package com.advanceweb.afc.jb.employer.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.MerUser;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Repository("empHelper")
public class EmployerRegistrationConversionHelper {
	
	/**
	 * Converting MerUserDTO to MerUser
	 * 
	 * @param dto
	 * @return MerUser
	 */
	public MerUser transformMerUserDTOToMerUser(MerUserDTO dto){
		MerUser merUser = new MerUser();
		merUser.setFirstName(dto.getFirstName());
		merUser.setLastName(dto.getLastName());
		merUser.setMiddleName(dto.getMiddleName());
		merUser.setEmail(dto.getEmailId());
		merUser.setPassword(dto.getPassword());
		merUser.setUserId(dto.getUserId());
		
		return merUser;
		
	}
	/**
	 * 
	 * @param dto
	 * @return
	 */
	public AdmFacility transformMerCompanyProfileDTOToAdmFacility(CompanyProfileDTO companyProfileDTO){
		AdmFacility admFacility = new AdmFacility();
		admFacility.setName(companyProfileDTO.getCompanyName());
		admFacility.setCompanyNews(companyProfileDTO.getCompanyNews());
		admFacility.setCompanyOverview(companyProfileDTO.getCompanyOverview());
		admFacility.setLogoPath(companyProfileDTO.getLogoPath());
		admFacility.setPromoMediaPath(companyProfileDTO.getPositionTitle());
		admFacility.setUrlDisplay(companyProfileDTO.getCompanyWebsite());
		admFacility.setEmailDisplay(companyProfileDTO.getCompanyEmail());
		admFacility.setColorPalette(companyProfileDTO.getPrimaryColor());
		
		return admFacility;
		
	}
	
}
