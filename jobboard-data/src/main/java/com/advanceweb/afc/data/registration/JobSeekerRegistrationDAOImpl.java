package com.advanceweb.afc.data.registration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.data.common.helpers.RegistrationConversionHelper;
import com.advanceweb.afc.data.domain.JobSeeker;
import com.advanceweb.afc.data.entities.MerUser;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:56 PM
 */
@SuppressWarnings("unchecked")
@Transactional
public class JobSeekerRegistrationDAOImpl implements JobSeekerRegistrationDAO {

	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;

	@Autowired
	private SessionFactory sessionFactory;

	public JobSeekerRegistrationDAOImpl() {

	}

	
	
	/**
	 * This method is called to save Job seeker registration information into
	 * Database
	 * 
	 * @param jobSeekerRegistrationDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public boolean createNewJobSeeker(
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO) {
		Session session = null;
		MerUser merUser = registrationConversionHelper
				.transformToMerUserFromDTo(jobSeekerRegistrationDTO
						.getMerUserDTO());
		try {
			if (merUser != null) {

				session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(merUser);

			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 
	 * @param jobSeekerId
	 */
	@Override
	public boolean deleteJobSeeker(long jobSeekerId) {
		return false;
	}

	@Override
	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param jobSeekerId
	 */
	@Override
	@Transactional(readOnly=true)
	public JobSeeker getJobSeekerDetails(long jobSeekerId) {
		Session session = null;
		try {
			if (jobSeekerId != 0) {
				
				session= sessionFactory.openSession();
//				session.load(arg0, jobSeekerId);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return null;
	}

	/**
	 * 
	 * @param jobSeeker
	 */
	@Override
	public boolean updateJobSeekerDetails(JobSeeker jobSeeker) {
		return false;
	}

}