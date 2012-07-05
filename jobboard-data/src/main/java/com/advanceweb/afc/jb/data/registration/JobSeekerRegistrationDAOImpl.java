package com.advanceweb.afc.jb.data.registration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.common.helpers.RegistrationConversionHelper;
import com.advanceweb.afc.jb.data.domain.JobSeeker;
import com.advanceweb.afc.jb.data.entities.MerUser;

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
	public boolean createNewJobSeeker(JobSeekerRegistrationDTO jobSeekerRegistrationDTO) {
		
		MerUser merUser = registrationConversionHelper
				.transformMerUserDTOToMerUser(jobSeekerRegistrationDTO
						.getMerUserDTO());
		try {
			if (merUser != null) {
				Session session = sessionFactory.getCurrentSession();
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
	public JobSeekerRegistrationDTO getJobSeekerDetails(long jobSeekerId) {
		JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
		try {
			if (jobSeekerId != 0) {
				Session session= sessionFactory.openSession();
				MerUser merUser = (MerUser) session.load(MerUser.class, new Long(jobSeekerId).intValue());
				MerUserDTO merUserDTO = registrationConversionHelper.transformMerUserToMerUserDTO(merUser);
				jsRegistrationDTO.setMerUserDTO(merUserDTO);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return jsRegistrationDTO;
	}

	/**
	 * 
	 * @param jobSeeker
	 */
	@Override
	public boolean updateJobSeekerDetails(JobSeekerRegistrationDTO jobSeekerRegistrationDTO) {
		
		MerUser merUser = registrationConversionHelper
				.transformMerUserDTOToMerUser(jobSeekerRegistrationDTO
						.getMerUserDTO());
		try {
			if (merUser != null) {
				Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(merUser);

			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

}