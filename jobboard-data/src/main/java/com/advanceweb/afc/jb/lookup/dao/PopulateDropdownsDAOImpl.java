/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
import org.springframework.dao.support.DataAccessUtils;
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
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.JpJobTypeCombo;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.ResBlockedCompanies;
import com.advanceweb.afc.jb.data.entities.ResDegreeEdu;
import com.advanceweb.afc.jb.data.entities.ResPrivacy;
import com.advanceweb.afc.jb.data.entities.ResResumeAttrib;
import com.advanceweb.afc.jb.data.entities.ResResumeAttribList;
import com.advanceweb.afc.jb.lookup.helper.PopulateDropdownConversionHelper;

@Repository("populateDropdownsDAO")
public class PopulateDropdownsDAOImpl implements PopulateDropdownsDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(PopulateDropdownsDAOImpl.class);

	/** The Constant FIND_USER_SUBSCRIPTIONS. */
	private static final String FIND_USER_SUBSCRIPTIONS = "from AdmSubscription sub where sub.subscriptionType=?";
	
	/** The Constant FIND_RESBUILDER_DROPDOWNS. */
	private static final String FIND_RESBUILDER_DROPDOWNS = "from ResResumeAttrib attrib where attrib.name=?";
	
	/** The Constant FIND_EDU_DEGREES. */
	private static final String FIND_EDU_DEGREES = "from ResDegreeEdu edu";
	
	/** The Constant FIND_INVENTORY_DETAILS. */
	private static final String FIND_INVENTORY_DETAILS = "select dtl from AdmFacilityInventory inv inner join inv.admInventoryDetail dtl where dtl.availableqty != 0 and inv.admFacility in(from AdmFacility fac where fac.facilityId=?) group by dtl.productType,dtl.productId";
	
	/** The Constant FIND_JOB_TYPE_COMBO. */
	private static final String FIND_JOB_TYPE_COMBO = "select dtl from AdmFacilityInventory inv inner join inv.admInventoryDetail dtl where dtl.invDetailId = ? and inv.admFacility in(from AdmFacility fac where fac.facilityId=?)";
	private static final String FIND_BLOCKED_BOMPANIES = "from ResBlockedCompanies rbc where rbc.resumeId=?";
	// private static final String
	// FIND_JOB_OWNERS="from AdmFacility adm where adm.admFacility=?";

	/** The dropdown helper. */
	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;

	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;
	
	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactory the new hibernate template
	 */
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * Sets the hibernate template tracker.
	 *
	 * @param sessionFactoryMerionTracker the new hibernate template tracker
	 */
	@Autowired
	public void setHibernateTemplateTracker(
			SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getCountryList()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getEmployementInfoList()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getSubscriptionsList()
	 */
	@Override
	public List<DropDownDTO> getSubscriptionsList() {

		try {
			List<AdmSubscription> subsList = hibernateTemplate.find(
					FIND_USER_SUBSCRIPTIONS, "USER");
			return dropdownHelper.convertAdmSubscriptionToDropDownDTO(subsList);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return null;

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getGenderList()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getVeteranStatusList()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getEthenticityList()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getJobAlertsList()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getMagazinesList()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateResumeDropdown(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateResumeBuilderDropdowns(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateEducationDegreesDropdowns()
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateJobOwnersDropdown(int)
	 */
	@Override
	public List<DropDownDTO> populateJobOwnersDropdown(int facilityId) {
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		try {

			AdmFacility admFacility = hibernateTemplate.get(AdmFacility.class,
					facilityId);
			int facilityParentId = admFacility.getFacilityParentId();
			if (MMJBCommonConstants.ZERO_INT < facilityParentId) {
				AdmFacility parentAdmFacility = hibernateTemplate.get(
						AdmFacility.class, facilityParentId);
				if (parentAdmFacility.getFacilityType().equalsIgnoreCase(
						MMJBCommonConstants.FACILITY_GROUP)
						|| parentAdmFacility.getFacilityType()
								.equalsIgnoreCase(MMJBCommonConstants.FACILITY)) {
					facilityId = facilityParentId;
				}
			}

			// Add employer name also in job owners list
			List<AdmUserFacility> userFacilities = hibernateTemplate.find(
					"from AdmUserFacility m where m.facilityPK.facilityId=?",
					facilityId);
			AdmUserFacility facility = new AdmUserFacility();
			if (null != userFacilities && !userFacilities.isEmpty()) {
				DropDownDTO dropdownDTO = new DropDownDTO();
				facility = userFacilities.get(0);
				int userId = facility.getFacilityPK().getUserId();
				MerUser merUser = hibernateTemplateTracker.get(MerUser.class,
						userId);
				dropdownDTO.setOptionId(String.valueOf(userId));
				dropdownDTO.setOptionName(merUser.getFirstName() + " "
						+ merUser.getLastName());
				dropdownList.add(dropdownDTO);

			}
			
			// Get the list of job owners of employee
			List<AdmFacility> admFacilityList = hibernateTemplate.find(
					"from AdmFacility adm where adm.facilityParentId=?",
					facilityId);
			for (AdmFacility jobOwner : admFacilityList) {
				if (jobOwner.getAdmUserFacilities() != null
						&& !jobOwner.getAdmUserFacilities().isEmpty()) {
					int facilityRoleId = jobOwner.getAdmUserFacilities().get(0)
							.getAdmRole().getRoleId();
					if (facilityRoleId == 5 || facilityRoleId == 6) {
						int jobownerUserId = jobOwner.getAdmUserFacilities()
								.get(0).getFacilityPK().getUserId();
						// Get the job owner details
						MerUser merUser = hibernateTemplateTracker.get(
								MerUser.class, jobownerUserId);
						if (merUser != null && merUser.getDeleteDt() == null) {
							DropDownDTO dropdownDTO = new DropDownDTO();
							dropdownDTO.setOptionId(String
									.valueOf(jobownerUserId));

							// Modified based on new changes
							/*
							 * dropdownDTO.setOptionName(merUser.getLastName() +
							 * " " + merUser.getFirstName());
							 */
							dropdownDTO.setOptionName(merUser.getFirstName()
									+ " " + merUser.getLastName());
							dropdownList.add(dropdownDTO);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return dropdownList;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateBrandingTemplateDropdown(int, int)
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateJobPostingTypeDropdown(int, int)
	 */
	@Override
	public List<DropDownDTO> populateJobPostingTypeDropdown(int facilityId,
			int jobPostType) {
		try {
			List<DropDownDTO> jbPostings = new ArrayList<DropDownDTO>();
			List<AdmInventoryDetail> invList = hibernateTemplate.find(
					FIND_JOB_TYPE_COMBO, jobPostType, facilityId);
			getJobPostingComboList(jbPostings, invList);
			return jbPostings;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateJobPostingTypeDropdowns(int)
	 */
	@Override
	public List<DropDownDTO> populateJobPostingTypeDropdowns(int facilityId) {

		try {
			List<DropDownDTO> jbPostings = new ArrayList<DropDownDTO>();
			List<AdmInventoryDetail> invList = hibernateTemplate.find(
					FIND_INVENTORY_DETAILS, facilityId);
			getJobPostingComboList(jbPostings, invList);
			return jbPostings;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * Gets the job posting combo list.
	 *
	 * @param jbPostings the jb postings
	 * @param invList the inv list
	 * @return the job posting combo list
	 */
	private void getJobPostingComboList(List<DropDownDTO> jbPostings,
			List<AdmInventoryDetail> invList) {
		DropDownDTO dto = null;
		if (!invList.isEmpty()) {
			for (AdmInventoryDetail inv : invList) {
				dto = new DropDownDTO();

				JpJobTypeCombo combo = (JpJobTypeCombo) DataAccessUtils
						.uniqueResult(hibernateTemplate
								.find("from JpJobTypeCombo combo where combo.comboId=?",
										inv.getProductId()));
				if (null != combo) {
					dto.setOptionId(String.valueOf(inv.getInvDetailId()));
					dto.setOptionName(combo.getAddons());
					dto.setNetSuiteId(String.valueOf(combo.getNetSuiteId()));
					jbPostings.add(dto);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateCityAutoComplete(java.lang.String)
	 */
	@Override
	public List<String> populateCityAutoComplete(String city) {

		List<LocationDTO> locationDtoList = new ArrayList<LocationDTO>();
		List<String> locationStringList = new ArrayList<String>();

		try {
			List<JpLocation> jpLocationList = hibernateTemplate
					.find("select distinct jloc.city, jloc.state from  JpLocation jloc WHERE  jloc.city like '"
							+ city + "%' ORDER BY  jloc.city, jloc.state ASC");

			if (jpLocationList != null) {
				Iterator<?> itr = jpLocationList.iterator();
				while (itr.hasNext()) {
					Object[] locObj = (Object[]) itr.next();
					LocationDTO locDTO = new LocationDTO();
					locDTO.setCity(String.valueOf(locObj[0]));
					locDTO.setState(String.valueOf(locObj[1]));
					locationDtoList.add(locDTO);
				}
				locationStringList = MMUtils.convertToCityStateStringList(locationDtoList);
			}
		} catch (DataAccessException e) {

			LOGGER.error(e);
		}
		return locationStringList;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateStateAutoComplete(java.lang.String)
	 */
	@Override
	public String populateStateAutoComplete(String cityState) {
		String state = MMJBCommonConstants.EMPTY;
		if (cityState.contains(MMJBCommonConstants.COMMASPACE)) {
			state = cityState.substring(cityState.lastIndexOf(MMJBCommonConstants.COMMASPACE)+2);
		}

		return state;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populatePostalCodeAutoComplete(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateCompanyNames(int, boolean)
	 */
	@Override
	public List<DropDownDTO> populateCompanyNames(int facilityId,
			boolean isHighlightFacility) {
		boolean isEmployer = false;
		boolean isEmployerGroup = false;
		int mainFacilityId = facilityId;
		int facilityParentId = -1;
		List<DropDownDTO> companyNames = new ArrayList<DropDownDTO>();
		try {
			AdmFacility admFacility = hibernateTemplate.load(AdmFacility.class,
					facilityId);

			facilityParentId = admFacility.getFacilityParentId();
			// Check for employer facility and facility group
			if (admFacility.getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY)) {
				isEmployer = true;
			} else if (admFacility.getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY_GROUP)) {
				isEmployerGroup = true;
			}

			// For job owner login
			if (MMJBCommonConstants.ZERO_INT < facilityParentId) {
				AdmFacility parentAdmFacility = hibernateTemplate.get(
						AdmFacility.class, facilityParentId);
				if (parentAdmFacility.getFacilityType().equalsIgnoreCase(
						MMJBCommonConstants.FACILITY_GROUP)) {
					isEmployer = false;
					isEmployerGroup = true;
					mainFacilityId = facilityParentId;
				} else if (parentAdmFacility.getFacilityType()
						.equalsIgnoreCase(MMJBCommonConstants.FACILITY)) {
					admFacility = parentAdmFacility;
				}
			}

			// Add the facilities for employer
			if (isEmployer) {
				DropDownDTO dto = new DropDownDTO();
				dto.setOptionId(String.valueOf(admFacility.getFacilityId()));
				dto.setOptionName(admFacility.getName());
				companyNames.add(dto);
			} else if (isEmployerGroup) {
				List<AdmFacility> subAdmFacility = hibernateTemplate
						.find("from  AdmFacility fac WHERE fac.facilityParentId=? and fac.deleteDt is null order by fac.createDt asc",
								mainFacilityId);
				for (AdmFacility facility : subAdmFacility) {
					// Do not display job owners
					if (facility.getAdmUserFacilities() == null
							|| facility.getAdmUserFacilities().isEmpty()) {
						DropDownDTO dto = new DropDownDTO();
						dto.setOptionId(String.valueOf(facility.getFacilityId()));
						dto.setOptionName(facility.getName());
						companyNames.add(dto);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		// Highlight the main facility for matrics
		if (isHighlightFacility && !companyNames.isEmpty()) {
			companyNames.get(0).setOptionName(
					"* " + companyNames.get(0).getOptionName());
		}
		return companyNames;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateTemplateAutoComplete(java.lang.String)
	 */
	@Override
	public List<DropDownDTO> populateTemplateAutoComplete(String company) {
		int facilityId = 0;
		int templateId = 0;
		List<DropDownDTO> templateList = new ArrayList<DropDownDTO>();
		try {

			facilityId = Integer.parseInt(company);

			List<AdmFacility> admFacilityList = hibernateTemplate.find(
					"from AdmFacility adm where adm.facilityId=?", facilityId);

			if (admFacilityList != null && !admFacilityList.isEmpty()) {
				AdmFacility facility = admFacilityList.get(0);
				templateId = facility.getTemplateId();
				int parentId = facility.getFacilityParentId(); 
				if(-1 != parentId){
					//Chk for agency in parent Id
					List<AdmFacility> admFacilityList1 = hibernateTemplate.find(
							"from AdmFacility adm where adm.facilityId=?", parentId);
					if (null != admFacilityList1 && !admFacilityList1.isEmpty()
							&& admFacilityList1
									.get(0)
									.getFacilityType()
									.equalsIgnoreCase(
											MMJBCommonConstants.FACILITY_SYSTEM)) {
						parentId = -1;
					}
				}
				if (-1 == templateId && -1 == parentId) {
					return populateBrandingTemplateDropdown(facilityId, 0);
				}
			}

			if (-1 == templateId) {
				return templateList;
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getPostalCode(java.lang.String, java.lang.String)
	 */
	@Override
	public String getPostalCode(String city, String state) {

		try {
			city = city.replaceAll("'", "''");
			List<Object> jpLocationList = hibernateTemplate
					.find("select distinct jloc.postcode from  JpLocation jloc WHERE  jloc.state='"
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getCountry(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getCountry(String city, String state, String postalCode) {

		try {
			city = city.replaceAll("'", "''");
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#populateLocation(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO#getJobStatusList()
	 */
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

	/**
	 * Method to get the subscription list for facility
	 * 
	 * @return
	 */
	@Override
	public List<DropDownDTO> getFacilitySubList() {

		try {
			List<AdmSubscription> subsList = hibernateTemplate.find(
					FIND_USER_SUBSCRIPTIONS, "FACILITY");
			// Map<String, String> facilityMap = new HashMap<String, String>();
			return dropdownHelper.convertAdmSubscriptionToDropDownDTO(subsList);
		} catch (DataAccessException e) {
			LOGGER.error("Error occurred while getting data for subscriptions"
					+ e);
		}

		return null;
	}

	@Override
	public List<DropDownDTO> getBlockedCompanyList(int resumeId) {
		try {
			List<ResBlockedCompanies> blockedCompanies = hibernateTemplate
					.find(FIND_BLOCKED_BOMPANIES, resumeId);
			DropDownDTO dropDownDTO = null;
			List<DropDownDTO> list = new ArrayList<DropDownDTO>();

			for (ResBlockedCompanies company : blockedCompanies) {
				dropDownDTO = new DropDownDTO();
				AdmFacility admFacility = hibernateTemplate.get(
						AdmFacility.class, company.getCompanyId());
				dropDownDTO.setOptionId(String.valueOf(company.getCompanyId()));
				dropDownDTO.setOptionName(admFacility.getName());
				list.add(dropDownDTO);
			}
			return list;
		} catch (DataAccessException e) {
			LOGGER.error("Error occurred while getting data for subscriptions"
					+ e);
		}
		return null;
	}
}
