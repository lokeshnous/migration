/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;

/**
 * <code> ManageFeatureEmployerProfileDAOImpl </code> is a DaoIMPL class.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 13 July 2012
 * 
 * 
 */
@Repository("manageFeaturedEmployerProfileDAO")
public class ManageFeaturedEmployerProfileDAOImpl implements
		ManageFeaturedEmployerProfileDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ManageFeaturedEmployerProfileDAOImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/** The employer registration conversion helper. */
	@Autowired
	private EmployerRegistrationConversionHelper employerRegistrationConversionHelper;

	/** The hibernate template careers. */
	private HibernateTemplate hibernateTemplateCareers;

	/**
	 * Sets the hibernate template.
	 * 
	 * @param sessionFactoryMerionTracker
	 *            the session factory merion tracker
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

	}

	/**
	 * Saving Manage Featured Employer Profile
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	public boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO) {

		AdmFacility facility = hibernateTemplateCareers.get(AdmFacility.class,
				Integer.parseInt(companyProfileDTO.getFacilityid()));
		facility = employerRegistrationConversionHelper
				.transformMerCompanyProfileDTOToAdmFacility(companyProfileDTO,
						facility);
		try {
			if (companyProfileDTO != null) {
				hibernateTemplateCareers.saveOrUpdate(facility);
				if (null != facility
						&& facility.getFacilityType().equals(
								MMJBCommonConstants.FACILITY_GROUP)) {
					List<AdmFacility> admFacilityList = hibernateTemplateCareers
							.find("from AdmFacility where facilityParentId=?",
									facility.getFacilityId());
					for (AdmFacility fac : admFacilityList) {
						fac.setFeStartDt(facility.getFeStartDt());
						fac.setFeEndDt(facility.getFeEndDt());
						hibernateTemplateCareers.saveOrUpdate(fac);
					}
				}
			}

		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return true;
	}

	/**
	 * Getting Featured Employer list
	 */
	@Override
	public List<CompanyProfileDTO> getEmployerList() {
		List<CompanyProfileDTO> companyProfileDTOList = new ArrayList<CompanyProfileDTO>();

		Session session = sessionFactory.openSession();

		try {

			@SuppressWarnings("unchecked")
			List<AdmFacility> admFacilityList = session
					.createCriteria(AdmFacility.class)
					.add(Restrictions.le("feStartDt", new Date()))
					.add(Restrictions.ge("feEndDt", new Date()))
					.add(Restrictions.eq("featuredEmp", (byte) 1)).list();

			for (Iterator<?> iterator = admFacilityList.iterator(); iterator
					.hasNext();) {

				CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();
				AdmFacility admFacility = (AdmFacility) iterator.next();
				companyProfileDTO.setFacilityid(String.valueOf(admFacility
						.getFacilityId()));
				companyProfileDTO.setCompanyName(admFacility.getName());
				companyProfileDTO.setCompanyNews(admFacility.getCompanyNews());
				companyProfileDTO.setCompanyOverview(admFacility
						.getCompanyOverview());

				// companyProfileDTO.setCompanyOverview("Please Modify me as soon as possible, im in ManageFeatureEmployerProfileDAOImpl");
				companyProfileDTO.setCompanyWebsite(admFacility.getUrl());
				companyProfileDTO.setCompanyEmail(admFacility.getEmail());

				// companyProfileDTO.setPositionTitle(facility.get);
				companyProfileDTO.setLogoPath(admFacility.getLogoPath());
				companyProfileDTOList.add(companyProfileDTO);

			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}

		return companyProfileDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.advanceweb.afc.jb.employer.dao.ManageFeaturedEmployerProfileDAO#
	 * getEmployerDetails(long)
	 */
	@Override
	public CompanyProfileDTO getEmployerDetails(long employerId) {
		CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();

		try {
			if (employerId != 0) {

				AdmFacility admFacility = (AdmFacility) hibernateTemplateCareers
						.get(AdmFacility.class, Long.valueOf(employerId)
								.intValue());
				companyProfileDTO.setFacilityid(String.valueOf(admFacility
						.getFacilityId()));
				companyProfileDTO.setCompanyName(admFacility.getName());
				companyProfileDTO.setCompanyNews(admFacility.getCompanyNews());
				companyProfileDTO.setCompanyOverview(admFacility
						.getCompanyOverview());

				// List<JpTemplate> jbTemplateList=admFacility.getJpTemplates();
				// if(null != jbTemplateList){
				// for(JpTemplate JpTemplate:jbTemplateList){
				// companyProfileDTO.setCompanyOverview(JpTemplate.getCompanyOverview());
				// }
				// }
				// companyProfileDTO.setCompanyOverview("Please Modify me as soon as possible, im in ManageFeatureEmployerProfileDAOImpl");
				companyProfileDTO
						.setCompanyWebsite(admFacility.getUrlDisplay());
				companyProfileDTO
						.setCompanyEmail(admFacility.getEmailDisplay());
				// companyProfileDTO.setPositionTitle(facility.get);
				companyProfileDTO.setLogoPath(admFacility.getLogoPath());
				companyProfileDTO
						.setPrimaryColor(admFacility.getColorPalette());
				companyProfileDTO.setPositionalMedia(admFacility
						.getPromoMediaPath());

			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return companyProfileDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.advanceweb.afc.jb.employer.dao.ManageFeaturedEmployerProfileDAO#
	 * getEmployerAccountDetails(long)
	 */
	@Override
	public List<EmployerProfileDTO> getEmployerAccountDetails(long employerId) {
		List<EmployerProfileDTO> employerProfileDTOs = new ArrayList<EmployerProfileDTO>();
		try {

			if (employerId != 0) {

				// Session session = sessionFactory.openSession();
				// AdmFacility facility = (AdmFacility)
				// session.get(AdmFacility.class,
				// Long.valueOf(employerId).intValue());

				EmployerProfileDTO employerProfileDTO = new EmployerProfileDTO();
				employerProfileDTOs.add(employerProfileDTO);

			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return employerProfileDTOs;
	}

	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */

	public List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID) {

		List<FacilityDTO> admFacilityDTOList = new ArrayList<FacilityDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<AdmFacility> admFacilityList = hibernateTemplateCareers
					.find(" from  AdmFacility WHERE  facilityId  = '"
							+ admFacilityID + "'");

			if (admFacilityList != null) {
				for (AdmFacility admFacilityObj : admFacilityList) {
					FacilityDTO admFacilityDTO = new FacilityDTO();
					admFacilityDTO.setNsCustomerID(admFacilityObj
							.getNsCustomerID());
					admFacilityDTOList.add(admFacilityDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
		}
		return admFacilityDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.advanceweb.afc.jb.employer.dao.ManageFeaturedEmployerProfileDAO#
	 * getEmployerList(int, int)
	 */
	@Override
	public List<CompanyProfileDTO> getEmployerList(int startRow, int endRow) {
		List<CompanyProfileDTO> companyProfileDTOList = new ArrayList<CompanyProfileDTO>();

		Session session = sessionFactory.openSession();
		try {
			@SuppressWarnings("unchecked")
			List<AdmFacility> admFacilityList = session
					.createCriteria(AdmFacility.class)
					.add(Restrictions.le("feStartDt", new Date()))
					.add(Restrictions.ge("feEndDt", new Date()))
					.add(Restrictions.eq("featuredEmp", (byte) 1))
					.setFirstResult(startRow).setMaxResults(endRow).list();

			for (Iterator<?> iterator = admFacilityList.iterator(); iterator
					.hasNext();) {

				CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();
				AdmFacility admFacility = (AdmFacility) iterator.next();
				companyProfileDTO.setFacilityid(String.valueOf(admFacility
						.getFacilityId()));
				companyProfileDTO.setCompanyName(admFacility.getName());
				companyProfileDTO.setCompanyNews(admFacility.getCompanyNews());
				companyProfileDTO.setCompanyOverview(admFacility
						.getCompanyOverview());

				// companyProfileDTO.setCompanyOverview("Please Modify me as soon as possible, im in ManageFeatureEmployerProfileDAOImpl");
				companyProfileDTO.setCompanyWebsite(admFacility.getUrl());
				companyProfileDTO.setCompanyEmail(admFacility.getEmail());

				// companyProfileDTO.setPositionTitle(facility.get);
				companyProfileDTO.setLogoPath(admFacility.getLogoPath());
				companyProfileDTOList.add(companyProfileDTO);

			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}

		return companyProfileDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.advanceweb.afc.jb.employer.dao.ManageFeaturedEmployerProfileDAO#
	 * getEmployerListCount()
	 */
	@Override
	public Long getEmployerListCount() {
		Long employerListCount = 0L;
		try {
			Session session = sessionFactory.openSession();
			// modified to bring all facility groups in futured employer list.
			/*
			 * employerListCount =(Long) hibernateTemplateCareers
			 * .getSessionFactory() .getCurrentSession() .createQuery(
			 * "SELECT count(a) from AdmFacility a where a.facilityParentId = -1"
			 * ) .uniqueResult();
			 */
			employerListCount = (Long) session
					.createCriteria(AdmFacility.class)
					.add(Restrictions.le("feStartDt", new Date()))
					.add(Restrictions.eq("featuredEmp", (byte) 1))
					.add(Restrictions.ge("feEndDt", new Date()))
					.setProjection(Projections.rowCount()).uniqueResult();

		} catch (HibernateException e) {
			LOGGER.error(e);
		}

		return employerListCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.advanceweb.afc.jb.employer.dao.ManageFeaturedEmployerProfileDAO#
	 * getParentId(int)
	 */
	@Override
	public int getParentId(int facilityId) {
		int roleId = 0;
		List<AdmUserFacility> userFacility = new ArrayList<AdmUserFacility>();
		List<AdmFacility> facilities = new ArrayList<AdmFacility>();
		AdmUserFacility facility = new AdmUserFacility();
		AdmFacility admFacility = new AdmFacility();
		try {
			userFacility = hibernateTemplateCareers.find(
					"from AdmUserFacility a where a.facilityPK.facilityId=?",
					facilityId);
			facility = userFacility.get(0);
			roleId = facility.getFacilityPK().getRoleId();
			if (roleId == 5 || roleId == 6) {
				facilities = hibernateTemplateCareers.find(
						"from AdmFacility a where a.facilityId=?", facilityId);
				admFacility = facilities.get(0);
				facilityId = admFacility.getFacilityParentId();
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return facilityId;
	}

	/**
	 * This method returns the facilityId of FACILITY_GROUP if the facility
	 * belongs to FACILITY_GROUP
	 * 
	 * @param facilityId
	 * @return facilityId
	 */
	@Override
	public int getParentGroup(int facilityId) {
		int facilityModId = facilityId;
		AdmFacility facility;
		try {
			facility = hibernateTemplateCareers.get(AdmFacility.class,
					facilityModId);
			if (null != facility
					&& facility.getFacilityType().equals(
							MMJBCommonConstants.FACILITY)
					&& facility.getFacilityParentId() > 0) {
				facility = hibernateTemplateCareers.get(AdmFacility.class,
						facility.getFacilityParentId());
				if (null != facility
						&& facility.getFacilityType().equals(
								MMJBCommonConstants.FACILITY_GROUP)) {
					facilityModId = facility.getFacilityId();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
			return facilityModId;
		}
		return facilityModId;
	}

	/**
	 * This method is to get the facilityId of job
	 * 
	 * @param jobId
	 * @return
	 */
	@Override
	public int getFaciliyId(int jobId) {
		int facilityId = 0;
		try {
			JpJob job = hibernateTemplateCareers.get(JpJob.class, jobId);
			facilityId = job.getAdmFacility().getFacilityId();
		} catch (DataAccessException e) {
			LOGGER.error("Error occured while getting facilityId for job", e);
		}
		return facilityId;
	}

	/**
	 * The method helps to validate featured employer start and end dates if
	 * expires then method return false otherwise true.
	 */
	/*
	 * public boolean validateFeaturedEmp(int facilityId){ boolean status =
	 * false; Session session = sessionFactory.openSession();
	 * 
	 * try {
	 * 
	 * @SuppressWarnings("unchecked") List<AdmFacility> admFacilityList =
	 * session .createCriteria(AdmFacility.class)
	 * .add(Restrictions.le("feStartDt", new Date()))
	 * .add(Restrictions.ge("feEndDt", new Date())) .add(Restrictions.eq("",
	 * facilityId)).list();
	 * 
	 * if(!admFacilityList.isEmpty()){ status = true; } } catch
	 * (HibernateException e) { LOGGER.error(e.getMessage(), e); }
	 * 
	 * return status; }
	 */

}
