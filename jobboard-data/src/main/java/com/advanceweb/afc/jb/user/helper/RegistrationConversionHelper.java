package com.advanceweb.afc.jb.user.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
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

	/**
	 * Transform MerUserDTO to entity MerUser	 
	 * @param dto
	 * @return
	 */
	public MerUser transformMerUserDTOToMerUser(JobSeekerRegistrationDTO dto, MerUser entity) {

		MerUserDTO userDTO = dto.getMerUserDTO();		
			
		
		if (null != userDTO && null == entity) {
			entity = new MerUser();
			entity.setPassword(userDTO.getPassword());
			entity.setEmail(userDTO.getEmailId());
		}
	
		if(null != dto.getAttribList()){
			
			for(MerProfileAttribDTO attribDTO : dto.getAttribList()){
				
				MerUserProfilePK pk = new MerUserProfilePK();
				MerUserProfile profile = new MerUserProfile();
				
				profile.setAttribValue(attribDTO.getStrLabelValue());
				
				if(attribDTO.getStrLabelName().equals(MMJBCommonConstants.FIRST_NAME)){
					entity.setFirstName(attribDTO.getStrLabelValue());
				}
				
				if(attribDTO.getStrLabelName().equals(MMJBCommonConstants.MIDDLE_NAME)){
					entity.setMiddleName(attribDTO.getStrLabelValue());
				}
				
				if(attribDTO.getStrLabelName().equals(MMJBCommonConstants.LAST_NAME)){
					entity.setLastName(attribDTO.getStrLabelValue());
				}
				
				if(attribDTO.getStrLabelName().equals(MMJBCommonConstants.EMAIL_ADDRESS)
						&& null != attribDTO.getStrLabelValue() && !attribDTO.getStrLabelValue().isEmpty()){
					entity.setEmail(attribDTO.getStrLabelValue());
				}
				
				if(null != attribDTO.getStrProfileAttribId() && !attribDTO.getStrProfileAttribId().isEmpty()){
					pk.setProfileAttribId(Integer.valueOf(attribDTO.getStrProfileAttribId()));
				}
				
				if(userDTO.getUserId() != 0){
					entity.setUserId(userDTO.getUserId());
				}
				
			}						
		}		

				
		return entity;

	}
	
	
	/**
	 * Transform MerUserDTO to entity MerUser	 
	 * @param dto
	 * @return
	 */
	public List<MerUserProfile> transformMerUserDTOToMerUserProfiles(JobSeekerRegistrationDTO dto, MerUser user) {
	
		List<MerUserProfile> listProfiles = new ArrayList<MerUserProfile>();		
		MerUserProfilePK pk = null;
			
		if(null != dto.getAttribList()){
			
			for(MerProfileAttribDTO attribDTO : dto.getAttribList()){
				
				if(!MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(attribDTO.getStrLabelName()) 
						&& !MMJBCommonConstants.FIRST_NAME.equals(attribDTO.getStrLabelName())
						&& !MMJBCommonConstants.MIDDLE_NAME.equals(attribDTO.getStrLabelName())
						&& !MMJBCommonConstants.LAST_NAME.equals(attribDTO.getStrLabelName())
						&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(attribDTO.getStrLabelName())){
					
					pk = new MerUserProfilePK();
					MerUserProfile profile = new MerUserProfile();
					
					profile.setAttribValue(attribDTO.getStrLabelValue());

					if(null != attribDTO.getStrProfileAttribId() && !attribDTO.getStrProfileAttribId().isEmpty()){
						pk.setProfileAttribId(Integer.valueOf(attribDTO.getStrProfileAttribId()));
					}

					if(user.getUserId() != 0){
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
	 * @param dto
	 * @return
	 */
	public List<AdmUserSubscription> transformMerUserDTOToAdmUserSubs(JobSeekerRegistrationDTO dto, MerUser user) {
	
		List<AdmUserSubscription> subsList = new ArrayList<AdmUserSubscription>();		
		AdmUserSubscriptionPK pk = null;
			
		if(null != dto.getAttribList()){
			
			for(MerProfileAttribDTO attribDTO : dto.getAttribList()){
				
				if(MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(attribDTO.getStrLabelName())
						&& null != attribDTO.getStrLabelValue() && !attribDTO.getStrLabelValue().isEmpty()){					
					List<String> sellItems = Arrays.asList(attribDTO.getStrLabelValue().split(","));
					for(String idVal : sellItems){
						pk = new AdmUserSubscriptionPK();
						AdmUserSubscription sub = new AdmUserSubscription();
							pk.setSubscriptionId(Integer.valueOf(idVal));
							pk.setUserId(user.getUserId());
						sub.setId(pk);
						subsList.add(sub);						
					}
				}
			}						
		}		
				
		return subsList;

	}
	
	
	/**
	 * Transform MerUser to MerUserDTO
	 * @param dto
	 * @return
	 */
	public JobSeekerRegistrationDTO transformMerUserProfilesToDTO(MerUser user, JobSeekerRegistrationDTO jsDTO, 
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
	
	/**
	 * 
	 * @param listProfAttrib
	 * @param countryList
	 * @param stateList
	 * @return
	 */
	public JobSeekerRegistrationDTO transformProfileAttrib(List<MerProfileAttrib> listProfAttrib,
			List<DropDownDTO> countryList, List<DropDownDTO> stateList, List<DropDownDTO> subsList){
		
		JobSeekerRegistrationDTO registerDTO = new JobSeekerRegistrationDTO();
		List<MerProfileAttribDTO> listDTO = new ArrayList<MerProfileAttribDTO>();
		if(null != listProfAttrib){
			for(MerProfileAttrib entity : listProfAttrib){
				MerProfileAttribDTO dto = new MerProfileAttribDTO();
				dto.setStrAttribType(entity.getFormType());
				dto.setStrLabelName(entity.getName());
				dto.setStrProfileAttribId(String.valueOf(entity.getProfileAttribId()));
				dto.setStrSectionName(entity.getSectionName());
				dto.setbRequired(entity.getRequired());
				dto.setStrToolTip(entity.getToolTip());
				if(dto.getStrAttribType().equals(MMJBCommonConstants.DROP_DOWN) || dto.getStrAttribType().equals(MMJBCommonConstants.CHECK_BOX)){
					//populating countries
					if(dto.getStrLabelName().equals(MMJBCommonConstants.LABEL_COUNTRY)){
						dto.setDropdown(countryList);
						
					}else if(dto.getStrLabelName().equals(MMJBCommonConstants.LABEL_STATE)){
						dto.setDropdown(stateList);	//populating states
						
					}else if(dto.getStrLabelName().equals(MMJBCommonConstants.LABEL_SUSBSCRIPTION)){
						dto.setDropdown(subsList);	//populating states
					}else{
						List<MerProfileAttribList> dropdownVals = entity.getMerProfileAttribLists();
						dto.setDropdown(transformToDropDownDTO(dropdownVals));
					}
				}							
				listDTO.add(dto);
			}
		}
		registerDTO.setAttribList(listDTO);
		return registerDTO;		
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
	 * Converting list of States/Countries to dropdown dto
	 * 
	 * @param merUtilityList
	 * @return
	 */
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
	
	public List<DropDownDTO> convertAdmSubscriptionToDropDownDTO(List<AdmSubscription> subsList){

		DropDownDTO dropDownDTO = null;
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();

		for(AdmSubscription subs : subsList){
			dropDownDTO = new DropDownDTO();
			dropDownDTO.setOptionId(String.valueOf(subs.getSubscriptionId()));
			dropDownDTO.setOptionName(subs.getName());
			list.add(dropDownDTO);
		}		
		return list;		
	}
	
}
