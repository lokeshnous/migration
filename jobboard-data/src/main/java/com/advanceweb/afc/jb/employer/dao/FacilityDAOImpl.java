/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 3rd Oct 2012
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityContactDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmRole;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.helper.EmpConversionHelper;
import com.advanceweb.afc.jb.employer.helper.FacilityConversionHelper;
import com.advanceweb.afc.jb.user.dao.UserDao;

@Transactional
@Repository("facilityDAO")
public class FacilityDAOImpl implements FacilityDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(FacilityDAOImpl.class);

	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;

	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;
	
	/** The conversion helper. */
	@Autowired
	private EmpConversionHelper conversionHelper;
	
	/** The facility conversion helper. */
	@Autowired
	private FacilityConversionHelper facilityConversionHelper;
	
	/** The user dao. */
	@Autowired
	private UserDao userDAO;
	
	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactoryMerionTracker the session factory merion tracker
	 * @param sessionFactory the session factory
	 */
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
	}
	
	/**
	 * This method is to get the facility id and role for a particular user
	 * 
	 * @param userId
	 * @return EmployerInfoDTO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EmployerInfoDTO facilityDetails(int userId) {
		EmployerInfoDTO employerInfoDTO = null;
		AdmUserFacility user = null;

		List<AdmUserFacility> userList = hibernateTemplate
				.find("from AdmUserFacility e where e.id.userId = " + userId);
		if (userList != null && !userList.isEmpty()) {
			user = userList.get(0);
			employerInfoDTO = new EmployerInfoDTO();

			employerInfoDTO
					.setFacilityId(user.getAdmFacility().getFacilityId());
			employerInfoDTO.setRoleId(user.getAdmRole().getRoleId());
			employerInfoDTO.setCustomerNamel(user.getAdmFacility().getName());
		}
		return employerInfoDTO;
	}

	/**
	 * This method is get the metrics details for logged in employer
	 * 
	 * @param facilityId
	 * @return metricsDTO
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MetricsDTO getJobPostTotal(int facilityId) {

		long views = 0;
		long clicks = 0;
		long applies = 0;
		MetricsDTO metricsDto = new MetricsDTO();
		try {
			Query getMetricsViews = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getViews(?) }");
			getMetricsViews.setInteger(0, facilityId);
			List<BigDecimal> viewsList = getMetricsViews.list();
			if (null != viewsList && !viewsList.isEmpty() && !viewsList.contains(null)) {
				views = viewsList.get(0).longValue();
			}

			Query getMetricsClicks = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getClicks(?) }");
			getMetricsClicks.setInteger(0, facilityId);
			List<BigDecimal> clicksList = getMetricsClicks.list();
			if (null != clicksList && !clicksList.isEmpty() && !clicksList.contains(null)) {
				clicks = clicksList.get(0).longValue();
			}

			Query getMetricsApplies = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getApplies(?) }");
			getMetricsApplies.setInteger(0, facilityId);
			List<BigDecimal> appliesList = getMetricsApplies.list();
			if (null != appliesList && !appliesList.isEmpty() && !appliesList.contains(null)) {
				applies = appliesList.get(0).longValue();
			}

			metricsDto.setViews(views);
			metricsDto.setClicks(clicks);
			metricsDto.setApplies(applies);
		} catch (Exception e) {
			LOGGER.error(e);
			return metricsDto;
		}
		return metricsDto;
	}
	
	/**
	 * This method returns total number of active jobs posted by the employer
	 * 
	 * @param facilityId
	 * @return long
	 */
	@Override
	@SuppressWarnings("unchecked")
	public long getJobsByFacility(int facilityId) {
		long jobCount = 0;
		List<Long> jobCountList = hibernateTemplate
				.find("select count(*) from JpJob where admFacility.facilityId=? and active=1 and deleteDt is NULL",
						facilityId);
		if (null != jobCountList && !jobCountList.isEmpty()) {
			jobCount = jobCountList.get(0).longValue();
		}
		return jobCount;
	}

	/**
	 * This method is used to get all jobs stats for Site – wide average per job posting
	 * 
	 * @return metricsDTO
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MetricsDTO getAllJobStats() {
		long sumViews = 0;
		long sumClicks = 0;
		long sumApplies = 0;
		MetricsDTO metricsDTO = new MetricsDTO();
		try {
			List<Long> sumViewsList = hibernateTemplateTracker
					.find("select sum(resultCount) from VstSearchResultNew");
			if (null != sumViewsList && !sumViewsList.isEmpty()) {
				sumViews = sumViewsList.get(0).longValue();
			}

			List<Long> sumClicksList = hibernateTemplateTracker
					.find("select sum(clickCount) from VstClickthroughNew where clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (1,2,3,6,9,10)");
			if (null != sumClicksList && !sumClicksList.isEmpty()) {
				sumClicks = sumClicksList.get(0).longValue();
			}

			List<Long> sumAppliesList = hibernateTemplateTracker
					.find("select sum(clickCount) from VstClickthroughNew where clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (4,5,7,8)");
			if (null != sumAppliesList && !sumAppliesList.isEmpty()) {
				sumApplies = sumAppliesList.get(0).longValue();
			}

			metricsDTO.setViews(sumViews);
			metricsDTO.setClicks(sumClicks);
			metricsDTO.setApplies(sumApplies);
		} catch (Exception e) {
			LOGGER.error(e);
			return metricsDTO;
		}
		return metricsDTO;
	}

	/**
	 * This method is used to get date wise jobs stats for Site – wide average
	 * per job posting
	 * 
	 * @param startDate
	 * @param endDate
	 * 
	 * @return metricsDTO
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MetricsDTO getDateJobStats(Date startDate, Date endDate) {
		long sumViews = 0;
		long sumClicks = 0;
		long sumApplies = 0;
		MetricsDTO metricsDTO = new MetricsDTO();
		try {
			List<Long> sumViewsList = hibernateTemplateTracker
					.find("select sum(resultCount) from VstSearchResultNew where searchResultNewPK.searchDate >= ? and searchResultNewPK.searchDate <= ?",
							startDate, endDate);
			if (null != sumViewsList && !sumViewsList.isEmpty()) {
				sumViews = sumViewsList.get(0).longValue();
			}

			List<Long> sumClicksList = hibernateTemplateTracker
					.find("select sum(clickCount) from VstClickthroughNew where clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (1,2,3,6,9,10) and clickthroughNewPK.clickthroughDt >= ? and clickthroughNewPK.clickthroughDt <= ?",
							startDate, endDate);
			if (null != sumClicksList && !sumClicksList.isEmpty()) {
				sumClicks = sumClicksList.get(0).longValue();
			}

			List<Long> sumAppliesList = hibernateTemplateTracker
					.find("select sum(clickCount) from VstClickthroughNew where clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (4,5,7,8) and clickthroughNewPK.clickthroughDt >= ? and clickthroughNewPK.clickthroughDt <= ?",
							startDate, endDate);
			if (null != sumAppliesList && !sumAppliesList.isEmpty()) {
				sumApplies = sumAppliesList.get(0).longValue();
			}

			metricsDTO.setViews(sumViews);
			metricsDTO.setClicks(sumClicks);
			metricsDTO.setApplies(sumApplies);
		} catch (Exception e) {
			LOGGER.error(e);
			return metricsDTO;
		}
		return metricsDTO;
	}
	
	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardDataException
	 */
	public long getEmployerCount() throws JobBoardDataException {
		long count = 0;
		try {
			String hql = "select count(*) from AdmUserRole where admRole = 3";
			Query query = hibernateTemplate.getSessionFactory()
					.getCurrentSession().createQuery(hql);
			count = ((Number) query.uniqueResult()).intValue();
		} catch (HibernateException he) {
			throw new JobBoardDataException(
					"Error occured while getting the Result from Database" + he);
		}
		return count;
	}

	/**
	 * Get the Date range specific data
	 * 
	 * @param startFrom
	 * @param endFrom
	 * @param selEmployerId
	 * 
	 * @return MetricsDTO
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MetricsDTO employerDateMetrics(Date startFrom, Date endFrom,
			int selEmployerId) {
		long views = 0;
		long clicks = 0;
		long applies = 0;
		MetricsDTO metricsDTO = new MetricsDTO();
		try {
			Query getMetricsDateViews = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getViewsDate(?,?,?) }");
			getMetricsDateViews.setDate(0, startFrom);
			getMetricsDateViews.setDate(1, endFrom);
			getMetricsDateViews.setInteger(2, selEmployerId);
			List<BigDecimal> viewsList = getMetricsDateViews.list();
			if (null != viewsList && !viewsList.isEmpty() && !viewsList.contains(null)) {
				views = viewsList.get(0).longValue();
			}

			Query getMetricsDateClicks = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getClicksDate(?,?,?) }");
			getMetricsDateClicks.setDate(0, startFrom);
			getMetricsDateClicks.setDate(1, endFrom);
			getMetricsDateClicks.setInteger(2, selEmployerId);
			List<BigDecimal> clicksList = getMetricsDateClicks.list();
			if (null != clicksList && !clicksList.isEmpty() && !clicksList.contains(null)) {
				clicks = clicksList.get(0).longValue();
			}

			Query getMetricsDateApplies = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getAppliesDate(?,?,?) }");
			getMetricsDateApplies.setDate(0, startFrom);
			getMetricsDateApplies.setDate(1, endFrom);
			getMetricsDateApplies.setInteger(2, selEmployerId);
			List<BigDecimal> appliesList = getMetricsDateApplies.list();
			if (null != appliesList && !appliesList.isEmpty() && !appliesList.contains(null)) {
				applies = appliesList.get(0).longValue();
			}

			metricsDTO.setViews(views);
			metricsDTO.setClicks(clicks);
			metricsDTO.setApplies(applies);

		} catch (Exception e) {
			LOGGER.error(e);
			return metricsDTO;
		}

		return metricsDTO;
	}
	/**
	 * This method is to get the parent id of logged in facility
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public FacilityDTO getFacilityByFacilityId(int facilityId) {
//		AdmFacility facility = (AdmFacility) DataAccessUtils
//				.uniqueResult(hibernateTemplate
//						.find("from AdmFacility facility where facility.facilityId=?",
//								facilityId));
		AdmFacility facility = hibernateTemplate.get(AdmFacility.class,
				facilityId);

		FacilityDTO dto = new FacilityDTO();
		dto.setFacilityId(facility.getFacilityId());
		dto.setName(facility.getName());
		dto.setRoleId((null == facility.getAdmUserFacilities() || facility
				.getAdmUserFacilities().isEmpty()) ? 0 : facility
				.getAdmUserFacilities().get(0).getFacilityPK().getRoleId());
		dto.setFacilityParentId(facility.getFacilityParentId());
		dto.setLogoPath(facility.getLogoPath());
		dto.setNsCustomerID(facility.getNsCustomerID());
		dto.setFacilityType(facility.getFacilityType());
		dto.setUserId((null == facility.getAdmUserFacilities() || facility
				.getAdmUserFacilities().isEmpty()) ? 0 : facility
				.getAdmUserFacilities().get(0).getFacilityPK().getUserId());
		return dto;
	}

	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardDataException {

		AdmFacility admFacility = (AdmFacility) hibernateTemplate.find(
				"from AdmFacility e where e.facilityId=?", facilityId).get(0);

		List<AdmFacility> facilityList = new ArrayList<AdmFacility>();

		if (admFacility.getFacilityType().equals(
				MMJBCommonConstants.FACILITY_GROUP)) {
			try {
				List<AdmFacility> admFacilityList = hibernateTemplate
						.find("from AdmFacility e where e.facilityParentId=? and e.deleteDt is Null",
								facilityId);
				for (AdmFacility facility : admFacilityList) {
					// Add the facilities only by avoiding the job owners
					if (facility.getAdmUserFacilities() == null
							|| facility.getAdmUserFacilities().isEmpty()) {
						facilityList.add(facility);
					}
				}
			} catch (HibernateException e) {
				throw new JobBoardDataException(
						"Error occured while getting the facility details from Database"
								+ e);
			}
		}
		return conversionHelper.transformFacilityToDropDownDTO(facilityList,
				admFacility);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.FacilityDAO#getfacilityUserId(int)
	 */
	@SuppressWarnings("unchecked")
	public int getfacilityUserId(int facilityId) {
		FacilityDTO facilityDto = getParentFacility(facilityId);
		if (null != facilityDto) {
			facilityId = facilityDto.getFacilityId();
		}
		AdmRole role = (AdmRole) DataAccessUtils.uniqueResult(hibernateTemplate
				.find("from AdmRole role where role.name=?",
						MMJBCommonConstants.FACILITY_ADMIN));
		List<AdmUserFacility> facility = hibernateTemplate
				.find("from AdmUserFacility af where af.facilityPK.roleId=? and af.facilityPK.facilityId=?",
						role.getRoleId(), facilityId);
		if (null != facility && facility.size()>0) {
			return facility.get(0).getFacilityPK().getUserId();
		} else {
			return 0;
		}
	}
	
	/**
	 * This method is used to get all facility list 
	 * @return List<SchedulerDTO>
	 */
	@Override
	public List<SchedulerDTO> getAllFacilityList() {
		List<AdmUserFacility> facility;
		List<SchedulerDTO> schedulerDTOList=new ArrayList<SchedulerDTO>();
		try{
			facility=(List<AdmUserFacility>)hibernateTemplate.find("from AdmUserFacility userFacility where userFacility.facilityPK.roleId=? and userFacility.deleteDt=NULL",MMJBCommonConstants.EMPLOYER_ROLE_ID);
		for(AdmUserFacility admUserFacility:facility){
			if(admUserFacility.getAdmFacility().getFacilityType().equals(MMJBCommonConstants.FACILITY)|| admUserFacility.getAdmFacility().getFacilityType().equals(MMJBCommonConstants.FACILITY_GROUP)){
				SchedulerDTO schedulerDTO=new SchedulerDTO();
				UserDTO userDTO=userDAO.getUserByUserId(admUserFacility.getFacilityPK().getUserId());
				if(userDTO!=null){
					schedulerDTO.setCompanyName(admUserFacility.getAdmFacility().getName());
					schedulerDTO.setUserId(userDTO.getUserId());
//					schedulerDTO.setCreateUserId(userDTO.getUserId());
					schedulerDTO.setFirstName(userDTO.getFirstName());
					schedulerDTO.setLastName(userDTO.getLastName());
					schedulerDTO.setEmailId(userDTO.getEmailId());
					schedulerDTO.setFacilityId(admUserFacility.getFacilityPK().getFacilityId());
					schedulerDTOList.add(schedulerDTO);
				}
			}
		}
		}catch(Exception e){
			LOGGER.error("Exception occur while getting all facility list for scheduler job"+e.getMessage());
		}
		return schedulerDTOList;
	}
	
	/**
	 * The method helps to get main facility. If job owner login then method
	 * retrieves the main facility group. 
	 * 
	 * @param currentFacilityId
	 * @return
	 */
	@Override
	public FacilityDTO getParentFacility(int currentFacilityId) {
		AdmFacility admFacility = (AdmFacility) hibernateTemplate.find(
				"from AdmFacility e where e.facilityId=?", currentFacilityId).get(0);
		int facilityParentId = admFacility.getFacilityParentId();
		
		if (facilityParentId > 0) {
			AdmFacility parentAdmFacility = hibernateTemplate.get(
					AdmFacility.class, facilityParentId);
			if (parentAdmFacility.getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY_GROUP) || parentAdmFacility.getFacilityType().equalsIgnoreCase(
							MMJBCommonConstants.FACILITY)) {
				// For job owner login
				admFacility = parentAdmFacility;
			}
		}
		FacilityDTO facilityDTO = null;
		if(admFacility != null){
			List<AdmFacility> admFacilities = new ArrayList<AdmFacility>();
			admFacilities.add(admFacility);
			facilityDTO = facilityConversionHelper.transformToFacilityDTO(admFacilities).get(0);
		}		
		return facilityDTO;
	}
	
	/**
	 * The method returns true if application logged in by job owner otherwise false 
	 * 
	 * @param facilityId
	 * @return
	 */
	@Override
	public boolean isJobOwner(int facilityId){
		boolean isJobOwner = false;
		AdmFacility admFacility = hibernateTemplate.get(AdmFacility.class, facilityId);
		int facilityParentId = admFacility.getFacilityParentId();
		if (MMJBCommonConstants.ZERO_INT < facilityParentId) {
			AdmFacility parentAdmFacility = hibernateTemplate.get(
					AdmFacility.class, facilityParentId);
			if (parentAdmFacility.getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY_GROUP)
					|| parentAdmFacility.getFacilityType()
							.equalsIgnoreCase(MMJBCommonConstants.FACILITY)) {
				isJobOwner = true;
			}
		}
		return isJobOwner;
	}
	
	/**
	 * This method provides facility contact details
	 * @param facilityId
	 * @return
	 */
	public FacilityContactDTO getFacilityContactDetails(int facilityId){
		AdmFacility facility = hibernateTemplate.get(AdmFacility.class, facilityId);
		return facilityConversionHelper.transformToFacilityContactDTO(facility);
	}
	/**
	 * This method provides user facility details
	 * @param facilityId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FacilityDTO> getUserFacilityDetails(int facilityId) {
		List<FacilityDTO> facilityDTOs = new ArrayList<FacilityDTO>();
		List<AdmUserFacility> userFacilityList = hibernateTemplate
				.find("from AdmUserFacility e where e.id.facilityId = "
						+ facilityId);
		if (null != userFacilityList && userFacilityList.size() > 0) {
			for (AdmUserFacility admUserFacility : userFacilityList) {
				FacilityDTO facilityDTO = new FacilityDTO();
				facilityDTO.setUserId(admUserFacility.getFacilityPK()
						.getUserId());
				facilityDTOs.add(facilityDTO);
			}
		}
		return facilityDTOs;
	}
}
