package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.advanceweb.afc.jb.common.AccountProfileDTO;

import com.advanceweb.afc.jb.common.AddressDTO;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.MerUserProfilePK;

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
	public MerUser transformMerUserDTOToMerUser(MerUserDTO dto) {
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
	public AdmFacility transformMerCompanyProfileDTOToAdmFacility(
			CompanyProfileDTO companyProfileDTO) {
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

	/**
	 * 
	 * @param apd
	 * @return admFacilityContact.
	 */
	public AdmFacilityContact transformEmpAccDTOToAdmAccEntity(AccountProfileDTO apd){
		AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		AdmFacility admFacility = new AdmFacility();
		admFacility.setAdminUserId(apd.getFacilityId());		
		//admFacilityContact.setAdmFacility(apd.getFacilityId());
		admFacilityContact.setContactType(apd.getContactType());
		admFacilityContact.setFirstName(apd.getFirstName());
		admFacilityContact.setCompany(apd.getCompanyName());
		admFacilityContact.setStreet(apd.getStreet());
		admFacilityContact.setCity(apd.getCity());
		admFacilityContact.setState(apd.getState());
		admFacilityContact.setPostcode(apd.getZipCode());
		admFacilityContact.setCountry(apd.getCountry());
		admFacilityContact.setEmail(apd.getEmail());
		admFacilityContact.setPhone(apd.getPhone());
		
		
		return admFacilityContact;
		
	}
	


	/**
	 * Transform MerUserDTO to entity MerUser
	 * 
	 * @param dto
	 * @return
	 */
	public List<MerUserProfile> transformMerUserDTOToMerUserProfiles(
			EmployerProfileDTO dto, MerUser user) {

		List<MerUserProfile> listProfiles = new ArrayList<MerUserProfile>();
		MerUserProfilePK pk = null;

		if (null != dto.getAttribList()) {

			for (MerProfileAttribDTO attribDTO : dto.getAttribList()) {

				if (!MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(attribDTO
						.getStrLabelName())
						&& !MMJBCommonConstants.FIRST_NAME.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.MIDDLE_NAME.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.LAST_NAME.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(attribDTO
								.getStrLabelName())) {

					pk = new MerUserProfilePK();
					MerUserProfile profile = new MerUserProfile();

					profile.setAttribValue(attribDTO.getStrLabelValue());

					if (null != attribDTO.getStrProfileAttribId()
							&& !attribDTO.getStrProfileAttribId().isEmpty()) {
						pk.setProfileAttribId(Integer.valueOf(attribDTO
								.getStrProfileAttribId()));
					}

					if (user.getUserId() != 0) {
						pk.setUserId(user.getUserId());
					}

					profile.setId(pk);
					listProfiles.add(profile);
				}
			}
		}

		return listProfiles;

	}

	/**
	 * Transform MerUserDTO to entity MerUser
	 * 
	 * @param dto
	 * @return
	 */
	public List<MerUserProfile> transformMerUserDTOToAdmFacility(
			EmployerProfileDTO dto, MerUser user) {

		List<MerUserProfile> listProfiles = new ArrayList<MerUserProfile>();
		MerUserProfilePK pk = null;

		if (null != dto.getAttribList()) {

			for (MerProfileAttribDTO attribDTO : dto.getAttribList()) {

				MerUserProfile profile = new MerUserProfile();
				if (MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(attribDTO
						.getStrLabelName())) {
					pk = new MerUserProfilePK();
					profile.setAttribValue(attribDTO.getStrLabelValue());
				}
				if (MMJBCommonConstants.CITY
						.equals(attribDTO.getStrLabelName())) {
					profile.setAttribValue(attribDTO.getStrLabelValue());
				}
				if (MMJBCommonConstants.STATE.equals(attribDTO
						.getStrLabelName())) {
					profile.setAttribValue(attribDTO.getStrLabelValue());
				}
				if (MMJBCommonConstants.LABEL_COUNTRY.equals(attribDTO
						.getStrLabelName())) {
					profile.setAttribValue(attribDTO.getStrLabelValue());
				}
				if (MMJBCommonConstants.EMAIL.equals(attribDTO
						.getStrLabelName())) {
					profile.setAttribValue(attribDTO.getStrLabelValue());
				}

			}
		}

		return listProfiles;

	}


	
	public AdmFacilityContact transformEmpAccDTOToAdmAccEntityData(AdmFacilityContact admFacilityContact,AccountProfileDTO apd){
		//AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		//AdmFacility admFacility = new AdmFacility();
		//admFacility.setAdminUserId(apd.getFacilityId());		
		//admFacilityContact.setAdmFacility(admFacility);
		//admFacilityContact.setContactType(apd.getContactType());
		admFacilityContact.setFirstName(apd.getFirstName());
		admFacilityContact.setCompany(apd.getCompanyName());
		admFacilityContact.setStreet(apd.getStreet());
		admFacilityContact.setCity(apd.getCity());
		admFacilityContact.setState(apd.getState());
		admFacilityContact.setPostcode(apd.getZipCode());
		admFacilityContact.setCountry(apd.getCountry());
		admFacilityContact.setEmail(apd.getEmail());
		admFacilityContact.setPhone(apd.getPhone());
		
		
		return admFacilityContact;
	}
	
}
