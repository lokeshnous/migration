package com.advanceweb.afc.jb.employer.dao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.data.domain.Employer;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:55 PM
 */
@Repository("employerRegistrationDAO")
public class EmployerRegistrationDAOImpl implements EmployerRegistrationDAO {

	private HibernateTemplate hibernateTemplateTracker;
	
	@Autowired
	private EmployerRegistrationConversionHelper empHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	public EmployerRegistrationDAOImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param employer
	 */
	public boolean createNewEmployer(EmployerProfileDTO empDTO){
			try {
				MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO.getMerUserDTO());
				hibernateTemplateTracker.saveOrUpdate(merUser);
				return true;
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		return false;
	}

	/**
	 * 
	 * @param employerId
	 */
	public boolean deleteEmployer(long employerId){
		return false;
	}

	/**
	 * 
	 * @param employerId
	 */
	public Employer getEmployerDetails(long employerId){
		return null;
	}

	/**
	 * 
	 * @param employer
	 */
	public boolean updateEmployerDetails(EmployerProfileDTO empDTO){
		return false;
	}

}