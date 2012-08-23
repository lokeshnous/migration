package com.advanceweb.afc.jb.employer.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Repository;


import com.advanceweb.afc.jb.common.AccountProfileDTO;

import com.advanceweb.afc.jb.common.AddressDTO;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerProfileAttribList;
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
	public MerUser transformMerUserDTOToMerUser(EmployerProfileDTO dto,
			MerUser entity) {
		MerUserDTO userDTO = dto.getMerUserDTO();

		if (null != userDTO && null == entity) {
			entity = new MerUser();
			entity.setPassword(userDTO.getPassword());
			entity.setEmail(userDTO.getEmailId());
		}

		if (null != dto.getAttribList()) {
			return createMerUser(entity, dto, userDTO);
		}

		return entity;

	}

	private MerUser createMerUser(MerUser entity, EmployerProfileDTO dto,
			MerUserDTO userDTO) {

		for (MerProfileAttribDTO attribDTO : dto.getAttribList()) {

			MerUserProfilePK pk = new MerUserProfilePK();
			MerUserProfile profile = new MerUserProfile();

			profile.setAttribValue(attribDTO.getStrLabelValue());

			if (attribDTO.getStrLabelName().equals(
					MMJBCommonConstants.FIRST_NAME)) {
				entity.setFirstName(attribDTO.getStrLabelValue());
			}

			if (attribDTO.getStrLabelName().equals(
					MMJBCommonConstants.MIDDLE_NAME)) {
				entity.setMiddleName(attribDTO.getStrLabelValue());
			}

			if (attribDTO.getStrLabelName().equals(
					MMJBCommonConstants.LAST_NAME)) {
				entity.setLastName(attribDTO.getStrLabelValue());
			}

			if (attribDTO.getStrLabelName().equals(
					MMJBCommonConstants.EMAIL_ADDRESS)
					&& null != attribDTO.getStrLabelValue()
					&& !attribDTO.getStrLabelValue().isEmpty()) {
				entity.setEmail(attribDTO.getStrLabelValue());
			}

			if (null != attribDTO.getStrProfileAttribId()
					&& !attribDTO.getStrProfileAttribId().isEmpty()) {
				pk.setProfileAttribId(Integer.valueOf(attribDTO
						.getStrProfileAttribId()));
			}

			if (userDTO.getUserId() != 0) {
				entity.setUserId(userDTO.getUserId());
			}

		}
		return entity;
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
	
	/**
	 * 
	 * @param listProfAttrib
	 * @param countryList
	 * @param stateList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public EmployerProfileDTO transformProfileAttrib(List<MerProfileAttrib> listProfAttrib,
			List<DropDownDTO> countryList, List<DropDownDTO> stateList) {
		Properties entries = null;
		Set set = null;
		List<String> labels = new ArrayList<String>();
		try {
			entries = PropertiesLoaderUtils
					.loadAllProperties("entries.properties");
			set = entries.keySet();
			Iterator itr = set.iterator();
			while (itr.hasNext()) {
				labels.add(entries.getProperty((String) itr.next()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Properties profileAttributes = null;
		List<String> profileAttribs = new ArrayList<String>();
		try {
				profileAttributes = PropertiesLoaderUtils.loadAllProperties("employerProfile.properties");
				set = profileAttributes.keySet(); 
				Iterator itr = set.iterator(); 
				while(itr.hasNext()) { 
					profileAttribs.add(profileAttributes.getProperty((String) itr.next())); 
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		EmployerProfileDTO registerDTO = new EmployerProfileDTO();
		List<MerProfileAttribDTO> listDTO = new ArrayList<MerProfileAttribDTO>();
		if (null != listProfAttrib) {
			for (MerProfileAttrib entity : listProfAttrib) {
				if(profileAttribs.contains(entity.getName())){
					MerProfileAttribDTO dto = new MerProfileAttribDTO();
					dto.setStrAttribType(entity.getFormType());
					dto.setStrLabelName(entity.getName());
					dto.setStrProfileAttribId(String.valueOf(entity
							.getProfileAttribId()));
					dto.setbRequired((labels.contains(dto.getStrLabelName()) ? 1
							: 0));
					if (dto.getStrAttribType()
							.equals(MMJBCommonConstants.DROP_DOWN)
							|| dto.getStrAttribType().equals(
									MMJBCommonConstants.CHECK_BOX)) {
						// populating countries
						if (dto.getStrLabelName().equals(
								MMJBCommonConstants.LABEL_COUNTRY)) {
							dto.setDropdown(countryList);
	
						} else if (dto.getStrLabelName().equals(
								MMJBCommonConstants.LABEL_STATE)) {
							dto.setDropdown(stateList); // populating states
	
						} else {
							List<MerProfileAttribList> dropdownVals = entity
									.getMerProfileAttribLists();
							dto.setDropdown(transformToDropDownDTO(dropdownVals));
						}
					}
					listDTO.add(dto);
				}	
			}
		}
		registerDTO.setAttribList(listDTO);
		return registerDTO;
	}
	
	/**
	 * Transform MerUser to MerUserDTO
	 * @param dto
	 * @return
	 */
	public EmployerProfileDTO transformMerUserProfilesToDTO(MerUser user, EmployerProfileDTO jsDTO, 
			List<MerUserProfile> profiles) {
		
		if (null !=jsDTO.getAttribList()) {
			
			for(MerProfileAttribDTO attribDTO : jsDTO.getAttribList()){
				if(MMJBCommonConstants.FIRST_NAME.equalsIgnoreCase(attribDTO.getStrLabelName())){
					attribDTO.setStrLabelValue(user.getFirstName());
				}else if(MMJBCommonConstants.LAST_NAME.equalsIgnoreCase(attribDTO.getStrLabelName())){
					attribDTO.setStrLabelValue(user.getLastName());
				}else if(MMJBCommonConstants.MIDDLE_NAME.equalsIgnoreCase(attribDTO.getStrLabelName())){
					attribDTO.setStrLabelValue(user.getMiddleName());
				}else if(MMJBCommonConstants.EMAIL_ADDRESS.equalsIgnoreCase(attribDTO.getStrLabelName())){
					attribDTO.setStrLabelValue(user.getEmail());
				}else{
					attribDTO.setStrLabelValue(retrieveLabelValue(attribDTO, profiles));
				}
			}
		}

		return jsDTO;
	}
	
	
	/**
	 * 
	 * @param attribDTO
	 * @param profiles
	 * @return
	 */
	private String retrieveLabelValue(MerProfileAttribDTO attribDTO, List<MerUserProfile> profiles){
		
		if(null != attribDTO){
			for(MerUserProfile profile : profiles){
				if(attribDTO.getStrProfileAttribId().equalsIgnoreCase(String.valueOf(profile.getId().getProfileAttribId()))){
					return profile.getAttribValue();
				}
			}
		}
		
		return null;		
	}

	public MerUserDTO transformMerUserToUserDTO(MerUser merUser) {
		MerUserDTO userDTO = new MerUserDTO();
		if(null != merUser){
			userDTO.setEmailId(merUser.getEmail());
			userDTO.setFirstName(merUser.getFirstName());
			userDTO.setLastName(merUser.getLastName());
			userDTO.setUserId(merUser.getUserId());
			userDTO.setMiddleName(merUser.getMiddleName());
			userDTO.setPassword(merUser.getPassword());
		}
		
		return userDTO;		
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
