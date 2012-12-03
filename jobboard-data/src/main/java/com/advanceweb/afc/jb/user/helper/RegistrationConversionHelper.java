package com.advanceweb.afc.jb.user.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscriptionPK;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerProfileAttribList;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.MerUserProfilePK;

@Repository("registrationConversionHelper")
public class RegistrationConversionHelper {

	private static final Logger LOGGER = Logger
			.getLogger(RegistrationConversionHelper.class);

	public MerUser transformMerUserDTOToMerUser(JobSeekerRegistrationDTO dto,
			MerUser entity) {
		/**
		 * Introduced a new variable "merUser" to resolve PMD issue.
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

	private MerUser createMerUser(MerUser entity, JobSeekerRegistrationDTO dto,
			UserDTO userDTO) {

		for (ProfileAttribDTO attribDTO : dto.getAttribList()) {

			MerUserProfilePK profilePK = new MerUserProfilePK();
			MerUserProfile profile = new MerUserProfile();

			profile.setAttribValue(attribDTO.getStrLabelValue());

			if (MMJBCommonConstants.FIRST_NAME.equals(attribDTO
					.getStrLabelName())) {
				entity.setFirstName(attribDTO.getStrLabelValue());
			}

			if (MMJBCommonConstants.MIDDLE_NAME.equals(attribDTO
					.getStrLabelName())) {
				entity.setMiddleName(attribDTO.getStrLabelValue());
			}

			if (MMJBCommonConstants.LAST_NAME.equals(attribDTO
					.getStrLabelName())) {
				entity.setLastName(attribDTO.getStrLabelValue());
			}

			if (MMJBCommonConstants.EMAIL_ADDRESS.equals(attribDTO
					.getStrLabelName())
					&& null != attribDTO.getStrLabelValue()
					&& !attribDTO.getStrLabelValue().isEmpty()) {
				entity.setEmail(attribDTO.getStrLabelValue());
			}

			if (null != attribDTO.getStrProfileAttribId()
					&& !attribDTO.getStrProfileAttribId().isEmpty()) {
				profilePK.setProfileAttribId(Integer.valueOf(attribDTO
						.getStrProfileAttribId()));
			}

			if (userDTO.getUserId() != 0) {
				entity.setUserId(userDTO.getUserId());
			}

		}
		return entity;
	}

	/**
	 * Transform MerUserDTO to entity MerUser
	 * 
	 * @param dto
	 * @return
	 */
	public List<MerUserProfile> transformMerUserDTOToMerUserProfiles(
			JobSeekerRegistrationDTO dto, MerUser user) {

		List<MerUserProfile> listProfiles = new ArrayList<MerUserProfile>();
		MerUserProfilePK pk = null;

		if (null != dto.getAttribList()) {

			for (ProfileAttribDTO attribDTO : dto.getAttribList()) {

				/*!MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(attribDTO
						.getStrLabelName())
						&& */
				if (!MMJBCommonConstants.FIRST_NAME.equals(attribDTO
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

					profile.setProfilePK(pk);
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
	public List<AdmUserSubscription> transformMerUserDTOToAdmUserSubs(
			JobSeekerRegistrationDTO dto, MerUser user) {

		List<AdmUserSubscription> subsList = new ArrayList<AdmUserSubscription>();
		AdmUserSubscriptionPK pkey = null;

		if (null != dto.getAttribList()) {

			for (ProfileAttribDTO attribDTO : dto.getAttribList()) {

				if (MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(attribDTO
						.getStrLabelName())
						&& null != attribDTO.getStrLabelValue()
						&& !attribDTO.getStrLabelValue().isEmpty()) {
					List<String> sellItems = Arrays.asList(attribDTO
							.getStrLabelValue().split(","));
					for (String idVal : sellItems) {
						pkey = new AdmUserSubscriptionPK();
						AdmUserSubscription sub = new AdmUserSubscription();
						pkey.setSubscriptionId(Integer.valueOf(idVal));
						pkey.setUserId(user.getUserId());
						sub.setSubscriptionPK(pkey);
						subsList.add(sub);
					}
				}
			}
		}

		return subsList;

	}

	/**
	 * Transform MerUser to MerUserDTO
	 * 
	 * @param dto
	 * @return
	 */
	public JobSeekerRegistrationDTO transformMerUserProfilesToDTO(MerUser user,
			JobSeekerRegistrationDTO jsDTO, List<MerUserProfile> profiles) {

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
					jsDTO.setEmailId(user.getEmail());
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

	/**
	 * 
	 * @param listProfAttrib
	 * @param countryList
	 * @param stateList
	 * @return
	 */
	public JobSeekerRegistrationDTO transformProfileAttrib(
			List<MerProfileAttrib> listProfAttrib,
			List<DropDownDTO> countryList, List<DropDownDTO> stateList,
			List<DropDownDTO> subsList) {
		List<String> labels = getLablesByProperties("entries.properties");
		List<String> profileAttribs = getLablesByProperties("jobSeekerProfile.properties");
		JobSeekerRegistrationDTO registerDTO = new JobSeekerRegistrationDTO();
		List<ProfileAttribDTO> listDTO = new ArrayList<ProfileAttribDTO>();
		if (null != listProfAttrib) {
			for (MerProfileAttrib entity : listProfAttrib) {
				if (profileAttribs.contains(entity.getName())) {
					ProfileAttribDTO dto = new ProfileAttribDTO();
					dto.setStrAttribType(entity.getFormType());
					dto.setStrLabelName(entity.getName());
					dto.setStrProfileAttribId(String.valueOf(entity
							.getProfileAttribId()));
					dto.setRequired((labels.contains(dto.getStrLabelName()) ? 1
							: 0));
					if (MMJBCommonConstants.DROP_DOWN.equals(dto
							.getStrAttribType())
							|| MMJBCommonConstants.CHECK_BOX.equals(dto
									.getStrAttribType())) {
						// populating countries
						if (MMJBCommonConstants.LABEL_COUNTRY.equals(dto
								.getStrLabelName())) {
							dto.setDropdown(countryList);

						} else if (MMJBCommonConstants.LABEL_STATE.equals(dto
								.getStrLabelName())) {
							dto.setDropdown(stateList); // populating states

						} else if (MMJBCommonConstants.LABEL_SUSBSCRIPTION
								.equals(dto.getStrLabelName())) {
							dto.setDropdown(subsList); // populating states
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
	 * Get the list of labels from the given properties name
	 * 
	 * @param string
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<String> getLablesByProperties(String string) {
		List<String> labels = new ArrayList<String>();
		Set set = null;
		Properties entries = null;
		try {
			entries = PropertiesLoaderUtils.loadAllProperties(string);
			set = entries.keySet();
			Iterator itr = set.iterator();
			while (itr.hasNext()) {
				labels.add(entries.getProperty((String) itr.next()));
			}
		} catch (IOException e) {
			LOGGER.info("ERROR in JobSeekerRegistrationDTO", e);
		}
		return labels;
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
	 * Converting list of States/Countries to DropDownDTO
	 * 
	 * @param merUtilityList
	 * @return
	 */
	public List<DropDownDTO> transformMerUtilityToDropDownDTO(
			List<Object> merUtilityList) {

		DropDownDTO dropDownDTO = null;
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();

		for (Object merUtility : merUtilityList) {
			dropDownDTO = new DropDownDTO();
			dropDownDTO.setOptionId((String) merUtility);
			dropDownDTO.setOptionName((String) merUtility);
			list.add(dropDownDTO);
		}
		return list;
	}

	/**
	 * Transforming List of AdmSubscriptions to List of DropDownDTO
	 * 
	 * @param subsList
	 * @return
	 */
	public List<DropDownDTO> transformAdmSubscriptionToDropDownDTO(
			List<AdmSubscription> subsList) {

		DropDownDTO dropDownDTO = null;
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();

		for (AdmSubscription subs : subsList) {
			dropDownDTO = new DropDownDTO();
			dropDownDTO.setOptionId(String.valueOf(subs.getSubscriptionId()));
			dropDownDTO.setOptionName(subs.getName());
			list.add(dropDownDTO);
		}
		return list;
	}

	/**
	 * Transforming MerUser to MerUserDTO
	 * 
	 * @param user
	 * @return
	 */
	public UserDTO transformMerUserToUserDTO(MerUser user) {

		UserDTO userDTO = new UserDTO();
		if (null != user) {
			userDTO.setEmailId(user.getEmail());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setUserId(user.getUserId());
			userDTO.setMiddleName(user.getMiddleName());
			userDTO.setPassword(user.getPassword());
		}

		return userDTO;
	}

}
