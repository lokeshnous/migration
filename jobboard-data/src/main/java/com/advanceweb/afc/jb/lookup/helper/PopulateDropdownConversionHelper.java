package com.advanceweb.afc.jb.lookup.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.ResumeAttribListDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.data.entities.MerPublication;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.ResDegreeEdu;
import com.advanceweb.afc.jb.data.entities.ResPrivacy;
import com.advanceweb.afc.jb.data.entities.ResResumeAttribList;

@Repository("dropdownHelper")
public class PopulateDropdownConversionHelper {

	public List<DropDownDTO> convertMerLookupToLookUpDTO(
			List<JpAttribList> merLookupList) {

		DropDownDTO lookUpDTO = null;
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();
		for (JpAttribList merUtility : merLookupList) {

			lookUpDTO = new DropDownDTO();

			lookUpDTO.setOptionId(String.valueOf(merUtility.getAttribListId()));
			lookUpDTO.setOptionName(merUtility.getAttribValue());

			list.add(lookUpDTO);
		}
		return list;

	}

	public List<CountryDTO> convertMerUtilityToCountryDTO(
			List<Object> merUtilityList) {

		CountryDTO countryDTO = null;
		List<CountryDTO> list = new ArrayList<CountryDTO>();

		for (Object merUtility : merUtilityList) {
			countryDTO = new CountryDTO();
			countryDTO.setCountryId((String) merUtility);
			countryDTO.setCountryValue((String) merUtility);
			list.add(countryDTO);
		}
		return list;
	}

	public List<EmploymentInfoDTO> convertMerUtilityToEmploymentInfoDTO(
			List<JpAttribList> merUtilityList) {

		EmploymentInfoDTO employmentInfoDTO = null;
		List<EmploymentInfoDTO> list = new ArrayList<EmploymentInfoDTO>();

		for (JpAttribList merUtility : merUtilityList) {

			employmentInfoDTO = new EmploymentInfoDTO();

			employmentInfoDTO.setInfoId(String.valueOf(merUtility
					.getAttribListId()));
			employmentInfoDTO.setInfoName(merUtility.getAttribValue());

			list.add(employmentInfoDTO);
		}
		return list;
	}

	public List<SubscriptionsDTO> convertMerUtilityToSubscriptionsDTO(
			List<JpAttribList> merUtilityList) {

		SubscriptionsDTO subscriptionsDTO = null;
		List<SubscriptionsDTO> list = new ArrayList<SubscriptionsDTO>();

		for (JpAttribList merUtility : merUtilityList) {

			subscriptionsDTO = new SubscriptionsDTO();

			subscriptionsDTO.setSubscriptionId(String.valueOf(merUtility
					.getAttribListId()));
			subscriptionsDTO.setSubscriptionName(merUtility.getAttribValue());

			list.add(subscriptionsDTO);
		}
		return list;
	}

	/*
	 * public List<CountryDTO> convertMerLookupToDTO(List<JpAttribList>
	 * MerLookupList){ ======= public List<CountryDTO>
	 * convertMerUtilityToDTO(List<JpAttribList> merUtilityList){ >>>>>>> .r56
	 * CountryDTO countryDTO = null; List<CountryDTO> list = new ArrayList();
	 * <<<<<<< .mine for(JpAttribList JpAttribList : MerLookupList){ =======
	 * for(JpAttribList merUtility : merUtilityList){ >>>>>>> .r56 countryDTO =
	 * new CountryDTO(); <<<<<<< .mine
	 * countryDTO.setCountryId(String.valueOf(JpAttribList.getAttribListId()));
	 * countryDTO.setCountryId(JpAttribList.getAttribValue()); =======
	 * countryDTO.setCountryId(String.valueOf(merUtility.getAttribListId()));
	 * countryDTO.setCountryId(merUtility.getAttribValue()); >>>>>>> .r56
	 * list.add(countryDTO); } return list; } <<<<<<< .mine
	 */

	public List<GenderDTO> convertMerUtilityToGenderDTO(
			List<JpAttribList> merUtilityList) {

		GenderDTO genderDTO = null;
		List<GenderDTO> list = new ArrayList<GenderDTO>();

		for (JpAttribList merUtility : merUtilityList) {

			genderDTO = new GenderDTO();

			genderDTO.setGenderId(String.valueOf(merUtility.getAttribListId()));
			genderDTO.setGenderName(merUtility.getAttribValue());

			list.add(genderDTO);
		}
		return list;
	}

	public List<EthenticityDTO> convertMerUtilityToEthenticityDTO(
			List<JpAttribList> merUtilityList) {

		EthenticityDTO ethenticityDTO = null;
		List<EthenticityDTO> list = new ArrayList<EthenticityDTO>();

		for (JpAttribList merUtility : merUtilityList) {

			ethenticityDTO = new EthenticityDTO();

			ethenticityDTO.setEthencityId(String.valueOf(merUtility
					.getAttribListId()));
			ethenticityDTO.setEthencityValue(merUtility.getAttribValue());

			list.add(ethenticityDTO);
		}
		return list;
	}

	public List<VeteranStatusDTO> convertMerUtilityToVeteranStatusDTO(
			List<JpAttribList> merUtilityList) {

		VeteranStatusDTO veteranStatusDTO = null;
		List<VeteranStatusDTO> list = new ArrayList<VeteranStatusDTO>();

		for (JpAttribList merUtility : merUtilityList) {

			veteranStatusDTO = new VeteranStatusDTO();

			veteranStatusDTO.setStatusId(String.valueOf(merUtility
					.getAttribListId()));
			veteranStatusDTO.setStatusValue(merUtility.getAttribValue());

			list.add(veteranStatusDTO);
		}
		return list;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:TO convert the List of JpAttribList to the List of RadiusDTO
	 * @Created:Jul 10, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of RadiusDTO
	 */
	public List<RadiusDTO> convertMerLookupToRadiusListDTO(
			List<JpAttribList> merLookupList) {

		RadiusDTO radiusDTO = null;
		List<RadiusDTO> list = new ArrayList<RadiusDTO>();
		for (JpAttribList JpAttribList : merLookupList) {
			radiusDTO = new RadiusDTO();
			radiusDTO
					.setRadiusId(String.valueOf(JpAttribList.getAttribListId()));
			radiusDTO.setRadiusValue(JpAttribList.getAttribValue());
			list.add(radiusDTO);
		}
		return list;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:TO convert the List of JpAttribList to the List of
	 *             ExcludeFromDTO
	 * @Created:Jul 10, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of ExcludeFromDTO
	 * 
	 */
	public List<ExcludeFromDTO> convertMerLookupToExcludeFromListDTO(
			List<JpAttribList> merLookupList) {
		ExcludeFromDTO excludeFromDTO = null;
		List<ExcludeFromDTO> list = new ArrayList<ExcludeFromDTO>();
		for (JpAttribList JpAttribList : merLookupList) {
			excludeFromDTO = new ExcludeFromDTO();
			excludeFromDTO.setExcludeFromId(String.valueOf(JpAttribList
					.getAttribListId()));
			excludeFromDTO.setExcludeFromValue(JpAttribList.getAttribValue());
			list.add(excludeFromDTO);
		}
		return list;

	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:TO convert the List of JpAttribList to the List of
	 *             FromZipcodeDTO
	 * @Created:Jul 10, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of FromZipcodeDTO
	 * 
	 */
	public List<FromZipcodeDTO> convertMerLookupToFromZipcodeListDTO(
			List<Object> merLookupList) {
		FromZipcodeDTO zipCodeDTO = null;
		List<FromZipcodeDTO> list = new ArrayList<FromZipcodeDTO>();

		for (Object object : merLookupList) {
			zipCodeDTO = new FromZipcodeDTO();
			zipCodeDTO.setFromZipcodeId((String) object);
			zipCodeDTO.setFromZipcodeValue((String) object);
			list.add(zipCodeDTO);
		}

		return list;

	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:TO convert the List of JpAttribList to the List of StateDTO
	 * @Created:Jul 10, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of StateDTO
	 * 
	 */
	public List<StateDTO> convertMerLookupToStateListDTO(
			List<Object> merLookupList) {
		StateDTO stateDTO = null;
		List<StateDTO> list = new ArrayList<StateDTO>();

		for (Object object : merLookupList) {
			stateDTO = new StateDTO();
			// stateDTO.setStateId(JpAttribList.getLocationId());
			stateDTO.setStateKey((String) object);
			stateDTO.setStateValue((String) object);
			list.add(stateDTO);
		}
		return list;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:TO convert the List of JpAttribList to the List of MetroAreaDTO
	 * @Created:Jul 10, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of MetroAreaDTO
	 * 
	 */
	public List<MetroAreaDTO> convertMerLookupToMetroAreaListDTO(
			List<JpAttribList> merLookupList) {
		MetroAreaDTO metroAreaDTO = null;
		List<MetroAreaDTO> list = new ArrayList<MetroAreaDTO>();

		for (JpAttribList JpAttribList : merLookupList) {
			metroAreaDTO = new MetroAreaDTO();
			metroAreaDTO.setMetroAreaId(String.valueOf(JpAttribList
					.getAttribListId()));
			metroAreaDTO.setMetroAreaValue(JpAttribList.getAttribValue());
			list.add(metroAreaDTO);
		}
		return list;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:TO convert the List of JpAttribList to the List of
	 *             EmploymentTypeDTO
	 * @Created:Jul 10, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of EmploymentTypeDTO
	 * 
	 */
	public List<EmploymentTypeDTO> convertMerLookupToEmploymentTypeListDTO(
			List<JpAttribList> merLookupList) {

		EmploymentTypeDTO employmentTypeDTO = null;
		List<EmploymentTypeDTO> list = new ArrayList<EmploymentTypeDTO>();

		for (JpAttribList JpAttribList : merLookupList) {
			if (!MMJBCommonConstants.SELECT.equalsIgnoreCase(JpAttribList
					.getAttribValue())) {
				employmentTypeDTO = new EmploymentTypeDTO();
				employmentTypeDTO.setEmploymentTypeId(JpAttribList
						.getAttribListId());
				employmentTypeDTO.setEmploymentTypeValue(JpAttribList
						.getAttribValue());
				list.add(employmentTypeDTO);
			}
		}
		return list;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:TO convert the List of JpAttribList to the List of
	 *             JobPostedDateDTO
	 * @Created:Jul 10, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of JobPostedDateDTO
	 * 
	 */
	public List<JobPostedDateDTO> convertMerLookupToJobPostedDateListDTO(
			List<JpAttribList> merLookupList) {
		JobPostedDateDTO jobPostedDateDTO = null;
		List<JobPostedDateDTO> list = new ArrayList<JobPostedDateDTO>();

		for (JpAttribList JpAttribList : merLookupList) {
			jobPostedDateDTO = new JobPostedDateDTO();
			jobPostedDateDTO.setJobPostedDateId(String.valueOf(JpAttribList
					.getAttribListId()));
			jobPostedDateDTO.setJobPostedDateValue(JpAttribList
					.getAttribValue());
			list.add(jobPostedDateDTO);
		}
		return list;
	}

	/**
	 * @Author :Sasibhushan
	 * @Purpose:TO convert the List of JpAttribList to the List of JobAlertsDTO
	 * @Created:Jul 12, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of JobAlertsDTO
	 * 
	 */
	public List<JobAlertsDTO> convertMerLookupToJobAlertsDTO(
			List<JpAttribList> merLookupList) {

		List<JobAlertsDTO> list = new ArrayList<JobAlertsDTO>();

		for (JpAttribList JpAttribList : merLookupList) {
			JobAlertsDTO jobAlertsDTO = new JobAlertsDTO();
			jobAlertsDTO.setAlertId(String.valueOf(JpAttribList
					.getAttribListId()));
			jobAlertsDTO.setAlertName(JpAttribList.getAttribValue());
			list.add(jobAlertsDTO);
		}
		return list;
	}

	/**
	 * @Author :Sasibhushan
	 * @Purpose:TO convert the List of JpAttribList to the List of Magazines DTO
	 * @Created:Jul 12, 2012
	 * @Param :List of JpAttribList
	 * @Return :List of MagazinesDTO
	 * 
	 */
	public List<MagazinesDTO> convertMerLookupToMagazinesDTO(
			List<JpAttribList> merLookupList) {

		List<MagazinesDTO> list = new ArrayList<MagazinesDTO>();

		for (JpAttribList JpAttribList : merLookupList) {
			MagazinesDTO magazinesDTO = new MagazinesDTO();
			magazinesDTO.setMagazineId(String.valueOf(JpAttribList
					.getAttribListId()));
			magazinesDTO.setMagazineName(JpAttribList.getAttribValue());
			list.add(magazinesDTO);
		}
		return list;
	}

	/**
	 * @Author :Sasibhushan
	 * @Purpose:TO convert the List of ResPrivacy to the List of VisibilityDTO
	 * @Created:Jul 12, 2012
	 * @Param :List of resPrivacyList
	 * @Return :List of visibilityDTO
	 * 
	 */
	public List<ResumeVisibilityDTO> transformResPrivacyToVisibilityDTO(
			List<ResPrivacy> resPrivacyList) {

		List<ResumeVisibilityDTO> visibilityDTOList = new ArrayList<ResumeVisibilityDTO>();

		for (ResPrivacy resPrivacy : resPrivacyList) {
			ResumeVisibilityDTO visibilityDTO = new ResumeVisibilityDTO();
			visibilityDTO.setVisibilityId(String.valueOf(resPrivacy
					.getDescription()));
			visibilityDTO.setVisibilityName(resPrivacy.getName());
			visibilityDTOList.add(visibilityDTO);
		}
		return visibilityDTOList;
	}

	public List<ResumeAttribListDTO> transformResumeAttribListToDTO(
			List<ResResumeAttribList> resResumeAttribList) {
		List<ResumeAttribListDTO> ResumeAttribListDTOList = new ArrayList<ResumeAttribListDTO>();
		for (ResResumeAttribList resResumeAttrib : resResumeAttribList) {
			ResumeAttribListDTO resumeAttribListDTO = new ResumeAttribListDTO();

			resumeAttribListDTO.setOptionId(String.valueOf(resResumeAttrib
					.getResumeAttribListId()));
			resumeAttribListDTO.setOptionValue(resResumeAttrib.getListValue());
			resumeAttribListDTO.setPosition(String.valueOf(resResumeAttrib
					.getPosition()));
			resumeAttribListDTO.setActive(String.valueOf(resResumeAttrib
					.getActive()));
			resumeAttribListDTO.setResResumeAttribId(String
					.valueOf(resResumeAttrib.getResResumeAttrib()));
			ResumeAttribListDTOList.add(resumeAttribListDTO);
		}
		return ResumeAttribListDTOList;

	}

	public List<DropDownDTO> convertAdmSubscriptionToDropDownDTO(
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
	 * Conversion from MerPublication entity to drop down DTO
	 * 
	 * @param subsList
	 * @return DropDownDTO List
	 */
	public List<DropDownDTO> convertMerPublicationToDropDownDTO(
			List<MerPublication> subsList) {

		DropDownDTO dropDownDTO = null;
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();

		for (MerPublication subs : subsList) {
			dropDownDTO = new DropDownDTO();
			dropDownDTO.setOptionId(String.valueOf(subs.getPublicationId()));
			dropDownDTO.setOptionName(subs.getPublicationName());
			list.add(dropDownDTO);
		}
		return list;
	}

	public List<DropDownDTO> transformResumeAttribListToDropDownDTO(
			List<ResResumeAttribList> resResumeAttribList) {
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		for (ResResumeAttribList resResumeAttrib : resResumeAttribList) {
			if (!MMJBCommonConstants.SELECT.equalsIgnoreCase(resResumeAttrib
					.getListValue())) {
				DropDownDTO dropdownDTO = new DropDownDTO();
				dropdownDTO.setOptionId(String.valueOf(resResumeAttrib
						.getResumeAttribListId()));
				dropdownDTO.setOptionName(resResumeAttrib.getListValue());

				dropdownList.add(dropdownDTO);
			}
		}
		return dropdownList;

	}

	public List<DropDownDTO> transformResDegreeEduToDropDownDTO(
			List<ResDegreeEdu> resEduDegreeList) {
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		for (ResDegreeEdu resDegree : resEduDegreeList) {
			DropDownDTO dropdownDTO = new DropDownDTO();
			dropdownDTO.setOptionId(String.valueOf(resDegree.getDegreeEduId()));
			dropdownDTO.setOptionName(resDegree.getName());

			dropdownList.add(dropdownDTO);
		}
		return dropdownList;

	}

	public List<DropDownDTO> transformAdmFacilityToDropDownDTO(
			List<MerUser> merUsers) {
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		for (MerUser merUser : merUsers) {
			DropDownDTO dropdownDTO = new DropDownDTO();
			dropdownDTO.setOptionId(String.valueOf(merUser.getUserId()));
			dropdownDTO.setOptionName(merUser.getLastName() + " "
					+ merUser.getFirstName());

			dropdownList.add(dropdownDTO);
		}
		return dropdownList;

	}

	public List<DropDownDTO> transformJpTemplateToDropDownDTO(
			List<JpTemplate> templateList) {
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		for (JpTemplate template : templateList) {
			if (null == template.getDeleteDt()) {
				DropDownDTO dropdownDTO = new DropDownDTO();
				dropdownDTO
						.setOptionId(String.valueOf(template.getTemplateId()));
				dropdownDTO.setOptionName(template.getTemplateName());

				dropdownList.add(dropdownDTO);
			}
		}
		return dropdownList;

	}

	public List<DropDownDTO> transformJpJobTypeToDropDownDTO(
			List<JpJobType> jpJobTypeList) {
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		if (null != jpJobTypeList) {
			for (JpJobType jobType : jpJobTypeList) {
				DropDownDTO dropdownDTO = new DropDownDTO();
				dropdownDTO.setOptionId(String.valueOf(jobType.getJobTypeId()));
				dropdownDTO.setOptionName(jobType.getDescription());
				dropdownList.add(dropdownDTO);
			}
		}
		return dropdownList;

	}

	public List<UserSubscriptionsDTO> convertMerUserProfileToUserDTO(
			List<MerUserProfile> userProfiles) {
		List<UserSubscriptionsDTO> dtos = new ArrayList<UserSubscriptionsDTO>();
		if (null != userProfiles) {
			for (MerUserProfile profile : userProfiles) {
				UserSubscriptionsDTO subDTO = new UserSubscriptionsDTO();
				if (MMJBCommonConstants.LABEL_SUSBSCRIPTION
						.equalsIgnoreCase(profile.getMerProfileAttrib()
								.getName())) {
					subDTO.setSubscriptionId(Integer.parseInt(profile
							.getAttribValue()));
					dtos.add(subDTO);
				}
			}
		}
		return dtos;
	}

}
