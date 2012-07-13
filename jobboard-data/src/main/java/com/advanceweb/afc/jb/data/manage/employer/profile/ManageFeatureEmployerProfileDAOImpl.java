package com.advanceweb.afc.jb.data.manage.employer.profile;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;

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
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
