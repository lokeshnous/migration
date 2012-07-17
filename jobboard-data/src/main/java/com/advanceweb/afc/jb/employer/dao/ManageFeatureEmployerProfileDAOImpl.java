package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;

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

	/**
	 * Saving Manage Featured Employer Profile
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveEmployerProfile(CompanyProfileDTO companyProfileDTO) {

		AdmFacility facility = new AdmFacility();
		facility.setName(companyProfileDTO.getCompanyName());
		// facility.setFacilityId(162);
		// facility.setAccountNumber("347OSC002");
		// facility.setAdminUserId(0);

		try {
			if (companyProfileDTO != null) {
				sessionFactory.getCurrentSession().saveOrUpdate(facility);
				System.out.println("data submitted");
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public CompanyProfileDTO getEmployerDetails(long employerId) {
		CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();

		try {
			if (employerId != 0) {
				Session session = sessionFactory.openSession();
				AdmFacility facility = (AdmFacility) session.get(AdmFacility.class,
						new Long(employerId).intValue());
				System.out.println(facility);

			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return companyProfileDTO;
	}

}
