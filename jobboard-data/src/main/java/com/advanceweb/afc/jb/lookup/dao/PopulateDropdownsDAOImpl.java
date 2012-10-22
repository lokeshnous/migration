package com.advanceweb.afc.jb.lookup.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
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
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.ResumeAttribListDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpJobTypeCombo;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.ResDegreeEdu;
import com.advanceweb.afc.jb.data.entities.ResPrivacy;
import com.advanceweb.afc.jb.data.entities.ResResumeAttrib;
import com.advanceweb.afc.jb.data.entities.ResResumeAttribList;
import com.advanceweb.afc.jb.lookup.helper.PopulateDropdownConversionHelper;

@Repository("populateDropdownsDAO")
@SuppressWarnings("unchecked")
public class PopulateDropdownsDAOImpl implements PopulateDropdownsDAO {

	private static final Logger LOGGER = Logger
			.getLogger(PopulateDropdownsDAOImpl.class);

	private static final String FIND_JOBSEEKER_SUBSCRIPTIONS = "from AdmSubscription sub where sub.subscriptionType=?";
	private static final String FIND_RESBUILDER_DROPDOWNS = "from ResResumeAttrib attrib where attrib.name=?";
	private static final String FIND_EDU_DEGREES = "from ResDegreeEdu edu";
	private static final String FIND_INVENTORY_DETAILS="select dtl from AdmFacilityInventory inv inner join inv.admInventoryDetail dtl where dtl.availableqty != 0 and inv.admFacility in(from AdmFacility fac where fac.facilityId=?) group by dtl.productType,dtl.productId";
	private static final String FIND_JOB_TYPE_COMBO="select dtl from AdmFacilityInventory inv inner join inv.admInventoryDetail dtl where dtl.invDetailId = ? and inv.admFacility in(from AdmFacility fac where fac.facilityId=?)";
	// private static final String
	// FIND_JOB_OWNERS="from AdmFacility adm where adm.admFacility=?";

	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;

	private HibernateTemplate hibernateTemplate;
	private HibernateTemplate hibernateTemplateTracker;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Autowired
	public void setHibernateTemplateTracker(
			SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
	}

	@Override
	public List<CountryDTO> getCountryList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("country")));
			List<Object> merUtilityList = hibernateTemplateTracker
					.findByCriteria(criteria);
			return dropdownHelper.convertMerUtilityToCountryDTO(merUtilityList);

		} catch (HibernateException e) {

			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<EmploymentInfoDTO> getEmployementInfoList() {

		try {
			List<JpAttribList> merUtilityList = hibernateTemplate
					.find("from JpAttribList e where e.attribType='EmploymentInformation' order by e.position");
			return dropdownHelper
					.convertMerUtilityToEmploymentInfoDTO(merUtilityList);

		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<DropDownDTO> getSubscriptionsList() {

		try {
			List<AdmSubscription> subsList = hibernateTemplate.find(
					FIND_JOBSEEKER_SUBSCRIPTIONS, "jobseeker");
			return dropdownHelper.convertAdmSubscriptionToDropDownDTO(subsList);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return null;

	}

	@Override
	public List<GenderDTO> getGenderList() {

		try {
			List<JpAttribList> merUtilityList = hibernateTemplate
					.find("from JpAttribList e where e.attribType='Gender' order by e.position");
			return dropdownHelper.convertMerUtilityToGenderDTO(merUtilityList);

		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<VeteranStatusDTO> getVeteranStatusList() {

		try {

			List<JpAttribList> merUtilityList = hibernateTemplate
					.find("from JpAttribList e where e.attribType='VeteranStatus' order by e.position");

			return dropdownHelper
					.convertMerUtilityToVeteranStatusDTO(merUtilityList);

		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<EthenticityDTO> getEthenticityList() {

		try {
			List<JpAttribList> merUtilityList = hibernateTemplate
					.find("from JpAttribList e where e.attribType='Ethnicity' order by e.position");

			return dropdownHelper
					.convertMerUtilityToEthenticityDTO(merUtilityList);

		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of RadiusDTO for the job seeker's advance search
	 * @Created:Jul 10, 2012
	 * @Param :not required
	 * @Return :List of RadiusDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getRadiusList()
	 */
	@Override
	public List<RadiusDTO> getRadiusList() {
		try {

			List<JpAttribList> merLookupList = hibernateTemplate
					.find("from JpAttribList e where e.lookupCategory='Radius' and e.lookupStatus='1'");
			return dropdownHelper
					.convertMerLookupToRadiusListDTO(merLookupList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of ExcludeFromDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Param :not required
	 * @Return :List of ExcludeFromDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getExcludeFromList()
	 */
	@Override
	public List<ExcludeFromDTO> getExcludeFromList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate
					.find("from JpAttribList e where e.lookupCategory='ExcludeFrom' and e.lookupStatus='1'");
			return dropdownHelper
					.convertMerLookupToExcludeFromListDTO(merLookupList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of FromZipcodeDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Param :not required
	 * @Return :List of FromZipcodeDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getFromZipcodeList()
	 */
	@Override
	public List<FromZipcodeDTO> getFromZipcodeList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("postcode")));
			criteria.addOrder(Order.asc("postcode"));
			List<Object> merUtilityList = hibernateTemplateTracker
					.findByCriteria(criteria);
			return dropdownHelper
					.convertMerLookupToFromZipcodeListDTO(merUtilityList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of StateDTO for the job seeker's advance search
	 * @Created:Jul 10, 2012
	 * @Param :not required
	 * @Return :List of StateDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getStateList()
	 */
	@Override
	public List<StateDTO> getStateList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("state")));
			criteria.addOrder(Order.asc("state"));
			List<Object> merUtilityList = hibernateTemplateTracker
					.findByCriteria(criteria);
			return dropdownHelper
					.convertMerLookupToStateListDTO(merUtilityList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of MetroAreaDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Param :not required
	 * @Return :List of MetroAreaDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getMetroAreaList()
	 */
	@Override
	public List<MetroAreaDTO> getMetroAreaList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate
					.find("from JpAttribList e where e.lookupCategory='MetroArea' and e.lookupStatus='1'");
			return dropdownHelper
					.convertMerLookupToMetroAreaListDTO(merLookupList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of EmploymentTypeDTO for the job seeker's
	 *             advance search
	 * @Created:Jul 10, 2012
	 * @Param :not required
	 * @Return :List of EmploymentTypeDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getEmploymentTypeList()
	 */
	@Override
	public List<EmploymentTypeDTO> getEmploymentTypeList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate
					.find("from JpAttribList e where e.attribType='EmploymentType' order by e.position");
			return dropdownHelper
					.convertMerLookupToEmploymentTypeListDTO(merLookupList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the list of JobPostedDateDTO for the job seeker's advance
	 *             search
	 * @Created:Jul 10, 2012
	 * @Param :not required
	 * @Return :List of JobPostedDateDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getJobPostedDateList()
	 */
	@Override
	public List<JobPostedDateDTO> getJobPostedDateList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate
					.find("from JpAttribList e where e.lookupCategory='JobPostedDate' and e.lookupStatus='1'");
			return dropdownHelper
					.convertMerLookupToJobPostedDateListDTO(merLookupList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<JobAlertsDTO> getJobAlertsList() {

		try {
			List<JpAttribList> merLookupList = hibernateTemplate
					.find("from JpAttribList e where e.lookupCategory='JobAlerts' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToJobAlertsDTO(merLookupList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<MagazinesDTO> getMagazinesList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate
					.find("from JpAttribList e where e.lookupCategory='Magazines' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToMagazinesDTO(merLookupList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * @Author :Anil Malali
	 * @Purpose:To get the dropdown
	 * @Created:Jul 24, 2012
	 * @Param :not required
	 * @Return :List of dropdown values
	 */
	@Override
	public List<DropDownDTO> populateDropdown(String dropDownName) {
		List<JpAttribList> merLookupList = hibernateTemplate.find(
				"from JpAttribList e where e.attribType=?", dropDownName);
		return dropdownHelper.convertMerLookupToLookUpDTO(merLookupList);
	}

	@Override
	public List<ResumeAttribListDTO> populateResumeDropdown(String dropdownName) {
		List<ResumeAttribListDTO> resumeAttribListDTOList = new ArrayList<ResumeAttribListDTO>();
		try {
			List<ResResumeAttrib> resResumeAttrib = hibernateTemplate
					.find("from ResResumeAttrib where name='" + dropdownName
							+ "'");
			if (!resResumeAttrib.isEmpty()) {
				List<ResResumeAttribList> resResumeAttribList = resResumeAttrib
						.get(0).getResResumeAttribLists();
				resumeAttribListDTOList = dropdownHelper
						.transformResumeAttribListToDTO(resResumeAttribList);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return resumeAttribListDTOList;
	}

	/**
	 * @Author :Anil Malali
	 * @Purpose:To get the list of ResumeVisibilityDTO for resume
	 * @Created:Jul 24, 2012
	 * @Param :not required
	 * @Return :List of ResumeVisibilityDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getResumeVisibilityList()
	 */
	@Override
	public List<ResumeVisibilityDTO> getResumeVisibilityList() {
		List<ResPrivacy> resPrivacyList = hibernateTemplate
				.find("from ResPrivacy");
		return dropdownHelper
				.transformResPrivacyToVisibilityDTO(resPrivacyList);
	}

	@Override
	public List<DropDownDTO> populateResumeBuilderDropdowns(String dropdownName) {

		try {
			List<ResResumeAttrib> resResumeAttrib = hibernateTemplate.find(
					FIND_RESBUILDER_DROPDOWNS, dropdownName);
			if (!resResumeAttrib.isEmpty()) {
				List<ResResumeAttribList> resResumeAttribList = resResumeAttrib
						.get(0).getResResumeAttribLists();
				return dropdownHelper
						.transformResumeAttribListToDropDownDTO(resResumeAttribList);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<DropDownDTO> populateEducationDegreesDropdowns() {

		try {
			List<ResDegreeEdu> resEduDegreeList = hibernateTemplate
					.find(FIND_EDU_DEGREES);
			return dropdownHelper
					.transformResDegreeEduToDropDownDTO(resEduDegreeList);

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<DropDownDTO> populateJobOwnersDropdown(int facilityId,
			int userId, int roleId) {

		try {
			List<MerUser> merUsers = new ArrayList<MerUser>();
			List<AdmFacility> facilityList = new ArrayList<AdmFacility>();
			// List<AdmUserFacility> userFacilityList =
			// hibernateTemplate.find("from AdmUserFacility facility where facility.id.userId=?",userId);
			// for(AdmUserFacility userFacility : userFacilityList){
			List<AdmFacility> admFacilityList = hibernateTemplate.find(
					"from AdmFacility adm where adm.facilityParentId=?",
					facilityId);
			facilityList.addAll(admFacilityList);
			// }

			for (AdmFacility facility : admFacilityList) {
				Object[] inputs = { facility.getFacilityId(), 5, 6 };
				List<AdmUserFacility> admUsersList = hibernateTemplate
						.find("from AdmUserFacility admFacility where admFacility.id.facilityId=? and (admFacility.id.roleId=? or admFacility.id.roleId=?)",
								inputs);
				if (null != admUsersList && !admUsersList.isEmpty()) {
					AdmUserFacility admUserFacility = admUsersList.get(0);
					List<MerUser> merUserList = hibernateTemplateTracker.find(
							"from MerUser user where user.userId=? and user.deleteDt is null",
							admUserFacility.getFacilityPK().getUserId());
					merUsers.addAll(merUserList);
				}

			}
			return dropdownHelper.transformAdmFacilityToDropDownDTO(merUsers);

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}


	@Override
	public List<DropDownDTO> populateBrandingTemplateDropdown(int facilityId,
			int userId) {
		try {
			List<AdmFacility> facilityList = hibernateTemplate.find(
					"from AdmFacility adm where adm.facilityId=?", facilityId);
			if (null != facilityList && !facilityList.isEmpty()) {
				AdmFacility facility = facilityList.get(0);
				List<JpTemplate> templateList = facility.getJpTemplates();
				return dropdownHelper
						.transformJpTemplateToDropDownDTO(templateList);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	
	@Override
	public List<DropDownDTO> populateJobPostingTypeDropdown(int facilityId,	int jobPostType) {
		try {
			List<DropDownDTO> jbPostings = new ArrayList<DropDownDTO>();
			List<AdmInventoryDetail> invList = hibernateTemplate.find(FIND_JOB_TYPE_COMBO, jobPostType, facilityId);
			getJobPostingComboList(jbPostings, invList);
			return jbPostings;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	@Override
	public List<DropDownDTO> populateJobPostingTypeDropdowns(int facilityId) {

		try {
			List<DropDownDTO> jbPostings = new ArrayList<DropDownDTO>();
			List<AdmInventoryDetail> invList = hibernateTemplate.find(FIND_INVENTORY_DETAILS, facilityId);
			getJobPostingComboList(jbPostings, invList);
			return jbPostings;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	private void getJobPostingComboList(List<DropDownDTO> jbPostings,
		List<AdmInventoryDetail> invList) {
		if (!invList.isEmpty()) {
			for (AdmInventoryDetail inv : invList) {					
				DropDownDTO dto = new DropDownDTO();
				dto.setOptionId(String.valueOf(inv.getInvDetailId()));
				if(MMJBCommonConstants.JOB_TYPE_COMBO.equals(inv.getProductType())){
					List<JpJobTypeCombo> comboList = hibernateTemplate.find("from JpJobTypeCombo combo where combo.comboId=?", inv.getProductId());
					if(!comboList.isEmpty()){
						
						JpJobTypeCombo combo = comboList.get(0);
						if(combo.getJobType().contains(MMJBCommonConstants.STANDARD_JOB_POSTING)){
							dto.setOptionName(MMJBCommonConstants.STANDARD_POSTING + " + "
									+ combo.getAddons().trim());
						}
						else if(combo.getJobType().contains(MMJBCommonConstants.JOB_POSTING_SLOT)){
							dto.setOptionName(MMJBCommonConstants.SLOT_POSTING + " + "
									+ combo.getAddons().trim());
						}
						if(combo.getAddons().contains("Job Posting")){
							combo.setAddons(combo.getAddons().replace("Job Posting",""));
							if(combo.getAddons().contains("Upgrade".trim())){
								combo.setAddons(combo.getAddons().replace("Upgrade",""));
							}
						}
						jbPostings.add(dto);
					}
				}else{
					List<JpJobType> jpTypleList = hibernateTemplate.find("from JpJobType type where type.jobTypeId=?", inv.getProductId());
					if(!jpTypleList.isEmpty()){
						JpJobType type = jpTypleList.get(0);
						
						if(type.getName().contains(MMJBCommonConstants.STANDARD_JOB_POSTING)){
							dto.setOptionName(MMJBCommonConstants.STANDARD_POSTING);
						}
						else if(type.getName().contains(MMJBCommonConstants.JOB_POSTING_SLOT)){
							dto.setOptionName(MMJBCommonConstants.SLOT_POSTING);
						}
						//dto.setOptionName(MMJBCommonConstants.STANDARD_POSTING);
						jbPostings.add(dto);
					}
				}
			}
		}
	}

	@Override
	public List<String> populateCityAutoComplete(String city) {

		List<String> locationList = new ArrayList<String>();

		try {
			List<Object> jpLocationList = hibernateTemplate
					.find("select distinct jloc.city from  JpLocation jloc WHERE  jloc.city like '"
							+ city + "%' ORDER BY  jloc.city ASC");

			if (jpLocationList != null) {
				for (Object obj : jpLocationList) {
					locationList.add((String) obj);
				}
			}
		} catch (DataAccessException e) {

			LOGGER.error(e);
		}
		return locationList;
	}

	@Override
	public String populateStateAutoComplete(String city) {

		try {
			List<Object> jpLocationList = hibernateTemplate
					.find("select distinct jloc.state from  JpLocation jloc WHERE  jloc.city='"
							+ city + "' ORDER BY  jloc.state ASC");

			if (jpLocationList != null && !jpLocationList.isEmpty()) {
				return (String) jpLocationList.get(0);
			}
		} catch (DataAccessException e) {

			LOGGER.error(e);
		}

		return null;
	}

	@Override
	public List<String> populatePostalCodeAutoComplete(String postalCode) {
		List<String> postalCodeList = new ArrayList<String>();
		try {
			List<Object> jpLocationList = hibernateTemplate
					.find("select distinct jloc.postcode from  JpLocation jloc WHERE  jloc.postcode like'"
							+ postalCode + "%' ORDER BY  jloc.postcode ASC");

			if (jpLocationList != null) {
				for (Object obj : jpLocationList) {
					postalCodeList.add((String) obj);
				}
			}
			return postalCodeList;

		} catch (DataAccessException e) {

			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public List<DropDownDTO> populateCompanyNames(int facilityId, int facilityParentId) {
		List<DropDownDTO> companyNames = new ArrayList<DropDownDTO>();
		try {
			if (MMJBCommonConstants.ZERO_INT == facilityParentId) {
				populateGroupCompany(facilityId, companyNames);
			} else {
				List<AdmFacility> listAdmFacility = hibernateTemplate.find(
						"from  AdmFacility fac WHERE fac.facilityParentId=? and fac.deleteDt is null",
						facilityParentId);
				for (AdmFacility facility : listAdmFacility) {
					// Do not display job owners
					Object[] inputs = { facility.getFacilityId(), 5, 6 };
					List<AdmUserFacility> admUsersList = hibernateTemplate
							.find("from AdmUserFacility admFacility where admFacility.id.facilityId=? and (admFacility.id.roleId=? or admFacility.id.roleId=?)",
									inputs);
					if (null == admUsersList || admUsersList.isEmpty()) {
						DropDownDTO dto = new DropDownDTO();
						dto.setOptionId(String.valueOf(facility.getFacilityId()));
						dto.setOptionName(facility.getName());
						companyNames.add(dto);
					}
				}
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return companyNames;
	}

	/**
	 * This method populates the Company Group name
	 * 
	 * @param facilityId
	 * @param companyNames
	 */
	private void populateGroupCompany(int facilityId,
			List<DropDownDTO> companyNames) {
		List<AdmFacility> listAdmFacility = hibernateTemplate.find(
				"from  AdmFacility fac WHERE fac.facilityParentId=? and fac.deleteDt is null",
				facilityId );

		if (null == listAdmFacility || listAdmFacility.isEmpty()) {
			listAdmFacility = hibernateTemplate.find(
					"from  AdmFacility fac WHERE fac.facilityId=? and fac.deleteDt is null",
					facilityId);

		}
		for (AdmFacility facility : listAdmFacility) {
			// Do not display job owners
			Object[] inputs = { facility.getFacilityId(), 5, 6 };
			List<AdmUserFacility> admUsersList = hibernateTemplate
					.find("from AdmUserFacility admFacility where admFacility.id.facilityId=? and (admFacility.id.roleId=? or admFacility.id.roleId=?)",
							inputs);
			if (null == admUsersList || admUsersList.isEmpty()) {
				DropDownDTO dto = new DropDownDTO();
				dto.setOptionId(String.valueOf(facility.getFacilityId()));
				dto.setOptionName(facility.getName());
				companyNames.add(dto);
			}
		}
	}
	
	@Override
	public List<DropDownDTO> populateTemplateAutoComplete(String company) {
		int facilityId=0;
		int templateId=0;
		List<DropDownDTO> templateList = new ArrayList<DropDownDTO>();
		try {
			
			facilityId = Integer.parseInt(company);
			
			List<Object> admFacilityList = hibernateTemplate
					.find("select fac.templateId from  AdmFacility fac WHERE fac.facilityId=?",
							facilityId);
			
			if (admFacilityList != null && !admFacilityList.isEmpty()) {
				templateId = (Integer)admFacilityList.get(0);
			}
			
			List<Object> jpTemplateList = hibernateTemplate
					.find("select distinct jtem.templateName from  JpTemplate jtem WHERE jtem.deleteDt is null and jtem.templateId=?",
							templateId);

			if (jpTemplateList != null && !jpTemplateList.isEmpty()) {
				DropDownDTO dto = new DropDownDTO();
				dto.setOptionId(String.valueOf(templateId));
				dto.setOptionName((String) jpTemplateList.get(0));
				templateList.add(dto);
				return templateList;
			}
		} catch (DataAccessException e) {

			LOGGER.error(e);
			return templateList;
		}

		return templateList;
	}
	
	@Override
	public String getPostalCode(String city, String state) {

		try {
			List<Object> jpLocationList = hibernateTemplate
					.find("select distinct jloc.postcode from  JpLocation jloc WHERE  jloc.state='"
							+ state
							+ "' and jloc.city='"
							+ city
							+ "' ORDER BY  jloc.postcode ASC");

			if (jpLocationList != null && jpLocationList.size() != 0) {
				return (String) jpLocationList.get(0);
			}

		} catch (DataAccessException e) {

			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public String getCountry(String city, String state, String postalCode) {

		try {
			List<Object> jpLocationList = hibernateTemplate
					.find("select distinct jloc.country from  JpLocation jloc WHERE jloc.state='"
							+ state
							+ "' and jloc.city='"
							+ city
							+ "' ORDER BY  jloc.postcode ASC");

			if (jpLocationList != null && !jpLocationList.isEmpty()) {
				return (String) jpLocationList.get(0);
			}

		} catch (DataAccessException e) {

			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public LocationDTO populateLocation(String postalCode) {

		try {
			List<Object> jpLocationList = hibernateTemplate
					.find("select jloc.country,jloc.state,jloc.city from  JpLocation jloc WHERE jloc.postcode='"
							+ postalCode + "' ORDER BY  jloc.postcode ASC");

			if (jpLocationList != null && !jpLocationList.isEmpty()) {
				LocationDTO dto = new LocationDTO();
				Object[] obj = (Object[]) jpLocationList.get(0);
				dto.setCountry(String.valueOf(obj[0]));
				dto.setState(String.valueOf(obj[1]));
				dto.setCity(String.valueOf(obj[2]));

				return dto;
			}

		} catch (DataAccessException e) {

			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public Map<String, String> getJobStatusList() {
		try {
			Map<String, String> resultMap = new HashMap<String, String>();
			List<?> dataList = hibernateTemplate
					.find("select jpa.attribValue from JpAttribList jpa where jpa.attribType='JobStatus' order by jpa.position");
			resultMap = new LinkedHashMap<String, String>(dataList.size());
			if (dataList != null && !dataList.isEmpty()) {

				Iterator<?> itr = dataList.iterator();
				while (itr.hasNext()) {
					String key = String.valueOf(itr.next());
					resultMap.put(key, key);
				}
			} else {
				resultMap = new LinkedHashMap<String, String>(1);
			}
			return resultMap;

		} catch (DataAccessException e) {

			LOGGER.error(e);
		}
		return null;

	}
	
}
