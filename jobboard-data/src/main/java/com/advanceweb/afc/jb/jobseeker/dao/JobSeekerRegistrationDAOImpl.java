package com.advanceweb.afc.jb.jobseeker.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:56 PM
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("jobSeekerRegistrationDAO")
public class JobSeekerRegistrationDAOImpl implements JobSeekerRegistrationDAO {

	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setHibernateTemplate(final SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	/**
	 * This method is called to save Job seeker registration information into
	 * Database
	 * 
	 * @param jobSeekerRegistrationDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public boolean createNewJobSeeker(JobSeekerRegistrationDTO jsDTO) {
		
		MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jsDTO);
		try {
			if (merUser != null) {
				hibernateTemplate.saveOrUpdate(merUser);
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
	public boolean deleteJobSeeker(int jobSeekerId) {
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
	public JobSeekerRegistrationDTO getJobSeekerDetails(int jobSeekerId) {
		JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
		try {
			if (jobSeekerId != 0) {
				MerUser merUser = hibernateTemplate.load(MerUser.class, jobSeekerId);
				MerUserDTO merUserDTO = registrationConversionHelper.transformMerUserToMerUserDTO(merUser);
				AddressDTO addDTO = registrationConversionHelper.transformMerUserToAddDTO(merUser);
				JobSeekerProfileDTO profileDTO = registrationConversionHelper.transformMerUserToProfileDTO(merUser);
				jsRegistrationDTO.setMerUserDTO(merUserDTO);
				jsRegistrationDTO.setAddressDTO(addDTO);
				jsRegistrationDTO.setJobSeekerProfileDTO(profileDTO);
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
		
		MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jobSeekerRegistrationDTO);
		try {
			if (merUser != null) {
				hibernateTemplate.saveOrUpdate(merUser);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}



	@Override
	public boolean jsChangePassword(
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO) {
		MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jobSeekerRegistrationDTO);
		try {
			if (merUser != null) {
				hibernateTemplate.saveOrUpdate(merUser);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}