package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
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

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private EmployerRegistrationConversionHelper employerRegistrationConversionHelper;
	
	/**
	 * Saving Manage Featured Employer Profile
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO) {
		Session session = sessionFactory.getCurrentSession();
		AdmFacility facility = employerRegistrationConversionHelper
				.transformMerCompanyProfileDTOToAdmFacility(companyProfileDTO);
		facility.setFacilityType("FACILITY");
		facility.setAdminUserId(1);
		facility.setCreateDt(new Date());

		try {
			if (companyProfileDTO != null) {
				session.saveOrUpdate(facility);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
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
			
			List<?> admFacilityList = session.createQuery("from AdmFacility").list();
			
			for (Iterator<?> iterator = admFacilityList.iterator(); iterator.hasNext();) {
				
				CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();
				AdmFacility admFacility = (AdmFacility) iterator.next();
				companyProfileDTO.setFacilityid(String.valueOf(admFacility.getFacilityId()));
				companyProfileDTO.setCompanyName(admFacility.getName());
				companyProfileDTO.setCompanyNews(admFacility.getCompanyNews());
				companyProfileDTO.setCompanyOverview(admFacility.getCompanyOverview());

				// companyProfileDTO.setCompanyOverview("Please Modify me as soon as possible, im in ManageFeatureEmployerProfileDAOImpl");
				companyProfileDTO.setCompanyWebsite(admFacility.getUrl());
				companyProfileDTO.setCompanyEmail(admFacility.getEmail());
				
				// companyProfileDTO.setPositionTitle(facility.get);
				companyProfileDTO.setLogoPath(admFacility.getLogoPath());
				companyProfileDTOList.add(companyProfileDTO);
				
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return companyProfileDTOList;
	}

	@Override
	public CompanyProfileDTO getEmployerDetails(long employerId) {
		CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();

		try {
			if (employerId != 0) {
				Session session = sessionFactory.openSession();

				AdmFacility admFacility = (AdmFacility) session.get(
						AdmFacility.class, Long.valueOf(employerId).intValue());
				// System.out.println(facility);
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
				companyProfileDTO.setCompanyWebsite(admFacility.getUrl());
				companyProfileDTO.setCompanyEmail(admFacility.getEmail());
				// companyProfileDTO.setPositionTitle(facility.get);
				companyProfileDTO.setLogoPath(admFacility.getLogoPath());

			}
		} catch (HibernateException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return employerProfileDTOs;
	}

}
