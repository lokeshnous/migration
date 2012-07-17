package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerActivityConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@Repository("activityDAO")
public class JobSeekerActivityDAOImpl implements JobSeekerActivityDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JobSeekerActivityConversionHelper jobSeekerActivityConversionHelper;

	public JobSeekerActivityDAOImpl() {

	}

	@Override
	public void finalize() throws Throwable {

	}
/**
 * deleting selected applied job
 */
	@Override
	public boolean deleteAppliedJobs(long appliedJobId) {
		JpJob jpJob = getById(appliedJobId);
		sessionFactory.getCurrentSession().delete(jpJob);
		return true;
	}

	/**
	 * implementation of get applied jobs
	 */

	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getAppliedJobs(long jobId) {
		List<AppliedJobDTO> appliedJobDTOList = new ArrayList<AppliedJobDTO>();

		try {
			if (jobId != 0) {
				Session session = sessionFactory.openSession();
				JpJob jpJob = (JpJob) session.get(JpJob.class,
						new Long(jobId).intValue());
				AppliedJobDTO appliedJobDTO = jobSeekerActivityConversionHelper
						.transformJpJobToApplidJobDTO(jpJob);
				appliedJobDTOList.add(appliedJobDTO);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return appliedJobDTOList;

	}

	/**
	 * deleting selected saved job
	 */
	@Override
	public boolean deleteSavedJobs(long savedJobId) {
		JpJob a = getById(savedJobId);
		sessionFactory.getCurrentSession().delete(a);
		return true;
	}

	public JpJob getById(long id) {
		return (JpJob) sessionFactory.getCurrentSession().get(JpJob.class, new Long(
				id).intValue());
	}

	/**
	 * implementation of get saved jobs
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SavedJobDTO> getSavedJobs(long jobSeekerId) {
		List<SavedJobDTO> savedJobDTOList = new ArrayList<SavedJobDTO>();

		try {
			if (jobSeekerId != 0) {
				Session session = sessionFactory.openSession();
				JpJob jpJob = (JpJob) session.get(JpJob.class, new Long(
						jobSeekerId).intValue());
				SavedJobDTO savedJobDTO = jobSeekerActivityConversionHelper
						.transformJpJobToSavedJobDTO(jpJob);
				savedJobDTOList.add(savedJobDTO);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return savedJobDTOList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public JobSeekerActivityConversionHelper getJobSeekerActivityConversionHelper() {
		return jobSeekerActivityConversionHelper;
	}

	public void setJobSeekerActivityConversionHelper(
			JobSeekerActivityConversionHelper jobSeekerActivityConversionHelper) {
		this.jobSeekerActivityConversionHelper = jobSeekerActivityConversionHelper;
	}

}
