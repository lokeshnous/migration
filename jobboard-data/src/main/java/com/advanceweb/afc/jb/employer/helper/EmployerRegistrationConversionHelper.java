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
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerProfileAttribList;
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
		/**
		 * Introduced a new variable "merUSer" to resolve PMD issue.
		 */
		MerUser merUser = entity;

		UserDTO userDTO = dto.getMerUserDTO();

		if (null != userDTO && null == merUser) {
			merUser = new MerUser();
			merUser.setPassword(userDTO.getPassword());
			merUser.setEmail(userDTO.getEmailId());
		}

		if (null != dto.getAttribList()) {
			return createMerUser(merUser, dto, userDTO);
		}

		return merUser;

	}

	private MerUser createMerUser(MerUser entity, EmployerProfileDTO dto,
			UserDTO userDTO) {

		for (ProfileAttribDTO attribDTO : dto.getAttribList()) {

			MerUserProfilePK merUserProfilePK = new MerUserProfilePK();
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
				merUserProfilePK.setProfileAttribId(Integer.valueOf(attribDTO
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
		admFacility.setFacilityId(Long.valueOf(companyProfileDTO.getFacilityid()).intValue());
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
	public AdmFacilityContact transformEmpAccDTOToAdmAccEntity(
			AccountProfileDTO apd) {
		AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		AdmFacility admFacility = new AdmFacility();
		admFacility.setAdminUserId(apd.getFacilityId());
		// admFacilityContact.setAdmFacility(apd.getFacilityId());
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
		MerUserProfilePK merUserProfilePK = null;

		if (null != dto.getAttribList()) {

			for (ProfileAttribDTO attribDTO : dto.getAttribList()) {

				if (!MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(attribDTO
						.getStrLabelName())
						&& !MMJBCommonConstants.FIRST_NAME.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.MIDDLE_NAME.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.LAST_NAME.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.STREET_ADD.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.CITY_EMP.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.STATE_PROVINCE.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.ZIP_CODE.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.COUNTRY.equals(attribDTO
								.getStrLabelName())
						&& !MMJBCommonConstants.COMPANY_EMP.equals(attribDTO
								.getStrLabelName())) {

					merUserProfilePK = new MerUserProfilePK();
					MerUserProfile profile = new MerUserProfile();

					profile.setAttribValue(attribDTO.getStrLabelValue());

					if (null != attribDTO.getStrProfileAttribId()
							&& !attribDTO.getStrProfileAttribId().isEmpty()) {
						merUserProfilePK.setProfileAttribId(Integer
								.valueOf(attribDTO.getStrProfileAttribId()));
					}

					if (user.getUserId() != 0) {
						merUserProfilePK.setUserId(user.getUserId());
					}

					profile.setProfilePK(merUserProfilePK);
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
	public AdmFacility transformEmpDTOToAdmFAcility(EmployerProfileDTO dto) {


		AdmFacility admFacility= new AdmFacility();
		
		if (null != dto.getAttribList()) {
			for (ProfileAttribDTO attribDTO : dto.getAttribList()) {
				if (MMJBCommonConstants.EMAIL_ADDRESS.equals(attribDTO
						.getStrLabelName())) {
					admFacility.setEmail(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.STREET_ADD.equals(attribDTO
						.getStrLabelName())) {
					admFacility.setStreet(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.CITY_EMP.equals(attribDTO
						.getStrLabelName())) {
					admFacility.setCity(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.STATE_PROVINCE.equals(attribDTO
						.getStrLabelName())) {
					admFacility.setState(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.ZIP_CODE.equals(attribDTO
						.getStrLabelName())) {
					admFacility.setPostcode(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.COUNTRY.equals(attribDTO
						.getStrLabelName())) {
					admFacility.setCountry(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.COMPANY_EMP.equals(attribDTO
						.getStrLabelName())) {
					admFacility.setName(attribDTO.getStrLabelValue());
				} 

			}
		}

		return admFacility;

	}

	/**
	 * Transform MerUserDTO to entity MerUser
	 * 
	 * @param dto
	 * @return
	 */
	public AdmFacilityContact transformEmpDTOToAdmFacilityContact(
			EmployerProfileDTO dto, AdmFacility facility) {

		AdmFacilityContact facilityContact = new AdmFacilityContact();

		if (null != dto.getAttribList()) {
			for (ProfileAttribDTO attribDTO : dto.getAttribList()) {
				if (MMJBCommonConstants.EMAIL_ADDRESS.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setEmail(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.STREET_ADD.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setStreet(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.CITY_EMP.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setCity(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.STATE_PROVINCE.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setState(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.ZIP_CODE.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setPostcode(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.COUNTRY.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setCountry(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.FIRST_NAME.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setFirstName(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.LAST_NAME.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setLastName(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.MIDDLE_NAME.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setMiddleName(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.COMPANY_EMP.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setCompany(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.POSITION_TITLE.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setJobTitle(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.PRIMARY_PHONE.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setPhone(attribDTO.getStrLabelValue());
				} else if (MMJBCommonConstants.SECONDARY_PHONE.equals(attribDTO
						.getStrLabelName())) {
					facilityContact.setPhone2(attribDTO.getStrLabelValue());
				}
				AdmFacility admFacility = new AdmFacility();
				admFacility.setFacilityId(facility.getFacilityId());
				facilityContact.setAdmFacility(admFacility);
			}
		}

		return facilityContact;

	}

	/**
	 * Converting list of MerProfileAttribList to list of DropDownDTO's
	 */
	public List<DropDownDTO> transformToDropDownDTO(
			List<MerProfileAttribList> dropdownVals) {

		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		if (null != dropdownVals) {
			for (MerProfileAttribList attrib : dropdownVals) {
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
	public EmployerProfileDTO transformProfileAttrib(
			List<MerProfileAttrib> listProfAttrib,
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
			profileAttributes = PropertiesLoaderUtils
					.loadAllProperties("employerProfile.properties");
			set = profileAttributes.keySet();
			Iterator itr = set.iterator();
			while (itr.hasNext()) {
				profileAttribs.add(profileAttributes.getProperty((String) itr
						.next()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		EmployerProfileDTO registerDTO = new EmployerProfileDTO();
		List<ProfileAttribDTO> listDTO = new ArrayList<ProfileAttribDTO>();
		if (null != listProfAttrib) {
			for (MerProfileAttrib entity : listProfAttrib) {
				if (profileAttribs.contains(entity.getName())) {
					ProfileAttribDTO dto = new ProfileAttribDTO();
					dto.setStrAttribType(entity.getFormType());
					dto.setStrLabelName(entity.getName());
					dto.setStrProfileAttribId(String.valueOf(entity
							.getProfileAttribId()));
					dto.setbRequired((labels.contains(dto.getStrLabelName()) ? 1
							: 0));
					if (dto.getStrAttribType().equals(
							MMJBCommonConstants.DROP_DOWN)
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
	 * 
	 * @param dto
	 * @return
	 */
	public EmployerProfileDTO transformMerUserProfilesToDTO(MerUser user,
			EmployerProfileDTO jsDTO, List<MerUserProfile> profiles) {

		if (null != jsDTO.getAttribList()) {

			for (ProfileAttribDTO attribDTO : jsDTO.getAttribList()) {
				if (MMJBCommonConstants.FIRST_NAME.equalsIgnoreCase(attribDTO
						.getStrLabelName())) {
					attribDTO.setStrLabelValue(user.getFirstName());
				} else if (MMJBCommonConstants.LAST_NAME
						.equalsIgnoreCase(attribDTO.getStrLabelName())) {
					attribDTO.setStrLabelValue(user.getLastName());
				} else if (MMJBCommonConstants.MIDDLE_NAME
						.equalsIgnoreCase(attribDTO.getStrLabelName())) {
					attribDTO.setStrLabelValue(user.getMiddleName());
				} else if (MMJBCommonConstants.EMAIL_ADDRESS
						.equalsIgnoreCase(attribDTO.getStrLabelName())) {
					attribDTO.setStrLabelValue(user.getEmail());
				} else {
					attribDTO.setStrLabelValue(retrieveLabelValue(attribDTO,
							profiles));
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
	private String retrieveLabelValue(ProfileAttribDTO attribDTO,
			List<MerUserProfile> profiles) {

		if (null != attribDTO) {
			for (MerUserProfile profile : profiles) {
				if (attribDTO.getStrProfileAttribId().equalsIgnoreCase(
						String.valueOf(profile.getProfilePK()
								.getProfileAttribId()))) {
					return profile.getAttribValue();
				}
			}
		}

		return null;
	}

	public UserDTO transformMerUserToUserDTO(MerUser merUser) {
		UserDTO userDTO = new UserDTO();
		if (null != merUser) {
			userDTO.setEmailId(merUser.getEmail());
			userDTO.setFirstName(merUser.getFirstName());
			userDTO.setLastName(merUser.getLastName());
			userDTO.setUserId(merUser.getUserId());
			userDTO.setMiddleName(merUser.getMiddleName());
			userDTO.setPassword(merUser.getPassword());
		}

		return userDTO;
	}

	public AdmFacilityContact transformEmpAccDTOToAdmAccEntityData(
			AdmFacilityContact afc, AccountProfileDTO apd) {
		// AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		// AdmFacility admFacility = new AdmFacility();
		// admFacility.setAdminUserId(apd.getFacilityId());
		// admFacilityContact.setAdmFacility(admFacility);
		// admFacilityContact.setContactType(apd.getContactType());
		afc.setFirstName(apd.getFirstName());
		afc.setCompany(apd.getCompanyName());
		afc.setStreet(apd.getStreet());
		afc.setCity(apd.getCity());
		afc.setState(apd.getState());
		afc.setPostcode(apd.getZipCode());
		afc.setCountry(apd.getCountry());
		afc.setEmail(apd.getEmail());
		afc.setPhone(apd.getPhone());

		return afc;
	}

	public AdmFacilityContactDTO transformListToDTOList(
			List<AdmFacilityContact> adm) {

		AdmFacilityContactDTO afc = new AdmFacilityContactDTO();
		int count = 0;
		if (adm != null) {

			for (AdmFacilityContact facility : adm) {
				afc.setCity(facility.getCity());
				afc.setFirstName(facility.getFirstName());
				afc.setLastName(facility.getLastName());
				afc.setCompanyName(facility.getCompany());
				afc.setPhone(facility.getPhone());
				afc.setZipCode(facility.getPostcode());
				afc.setState(facility.getState());
				afc.setCountry(facility.getCountry());
				afc.setStreet(facility.getStreet());
				afc.setEmail(facility.getEmail());
				afc.setFacilityContactId(facility.getFacilityContactId());
				count++;
			}
			// afc.setCount(count);
		}
		afc.setCount(count);
		return afc;
	}

	/**
	 * Converting MerUserDTO to MerUser for Manage Access Permission
	 * 
	 * @param dto
	 * @return MerUser
	 */
	public MerUser transformMerUserDTOToMerUser(EmployerProfileDTO dto) {

		UserDTO userDTO = dto.getMerUserDTO();
		MerUser merUser = null;
		if (null != userDTO) {
			merUser = new MerUser();
			merUser.setFirstName(userDTO.getFirstName());
			merUser.setLastName(userDTO.getLastName());
			merUser.setPassword(userDTO.getPassword());
			merUser.setEmail(userDTO.getEmailId());
		}

		if (null != dto.getAttribList()) {
			return createMerUser(merUser, dto, userDTO);
		}

		return merUser;

	}

	public List<ManageAccessPermissionDTO> transformAdmFacilityToManageAccessPermissionDTO(
			List<MerUser> merUsers, List<Integer> roleId) {
		List<ManageAccessPermissionDTO> manageAccessPermissionDTOList = new ArrayList<ManageAccessPermissionDTO>();
		int i = 0;
		for (MerUser merUser : merUsers) {
			ManageAccessPermissionDTO manageAccessPermissionDTO = new ManageAccessPermissionDTO();
			manageAccessPermissionDTO.setOwnerId(merUser.getUserId());
			manageAccessPermissionDTO.setOwnerName(merUser.getLastName() + " "
					+ merUser.getFirstName());
			if (roleId.size() > i && null != roleId.get(i)) {
				manageAccessPermissionDTO.setTypeOfAccess(roleId.get(i));
			}
			manageAccessPermissionDTOList.add(manageAccessPermissionDTO);
			i = i + 1;
		}
		return manageAccessPermissionDTOList;

	}
}
