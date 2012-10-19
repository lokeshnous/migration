package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
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
@Repository("manageFeatureEmployerProfileDAO")
public class ManageFeatureEmployerProfileDAOImpl implements
		ManageFeatureEmployerProfileDAO {

	private static final Logger LOGGER = Logger
			.getLogger(ManageFeatureEmployerProfileDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private EmployerRegistrationConversionHelper employerRegistrationConversionHelper;

	private HibernateTemplate hibernateTemplateCareers;

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
	public boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO) {

		AdmFacility facility = hibernateTemplateCareers.load(AdmFacility.class,
				Long.valueOf(companyProfileDTO.getFacilityid()).intValue());
		facility = employerRegistrationConversionHelper
				.transformMerCompanyProfileDTOToAdmFacility(companyProfileDTO,
						facility);
		int nsCustomerID = 0;
		List<FacilityDTO> admFacilityDTOList = getNSCustomerIDFromAdmFacility(Integer
				.valueOf(companyProfileDTO.getFacilityid()));
		nsCustomerID = admFacilityDTOList.get(0).getNsCustomerID();
		facility.setNsCustomerID(nsCustomerID);
		try {
			if (companyProfileDTO != null) {
				hibernateTemplateCareers.saveOrUpdate(facility);
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

			// modified to bring all facility groups in futured employer list.
			List<?> admFacilityList = session.createQuery(
					"from AdmFacility where facilityParentId = 0").list();

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

	@Override
	public List<CompanyProfileDTO> getEmployerList(int startRow, int endRow) {
		List<CompanyProfileDTO> companyProfileDTOList = new ArrayList<CompanyProfileDTO>();

		Session session = sessionFactory.openSession();
		Query query = null;
		try {

			// modified to bring all facility groups in futured employer list.
			query = session.createQuery(
					"from AdmFacility where facilityParentId = 0");
			query.setFirstResult(startRow);
			query.setMaxResults(endRow);
			List<?> admFacilityList = query.list();
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

	@Override
	public Long getEmployerListCount() {
		Long employerListCount = 0L;
		try {

			// modified to bring all facility groups in futured employer list.
			employerListCount =(Long) hibernateTemplateCareers
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT count(a) AdmFacility a where a.facilityParentId = 0")
					.uniqueResult(); 
					
		} catch (HibernateException e) {
			LOGGER.error(e);
		}

		return employerListCount;
	}

}
