package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerActivityConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@Repository("activityDAO")
public class JobSeekerActivityDAOImpl implements JobSeekerActivityDAO {

	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JobSeekerActivityConversionHelper jobSeekerActivityConversionHelper;

		
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public JobSeekerActivityDAOImpl() {

	}

	@Override
	public void finalize() throws Throwable {

	}

	/**
	   @Author :Prince Mathew
	   @Purpose:This method is used to delete the job applied by the Job Seeker
	   @Created:Jul 26, 2012
	   @Param  :appliedJobId
	   @Return :boolean value depends on the result
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerActivityDAO#deleteAppliedJobs(int)
	 */
	@Override
	public boolean deleteAppliedJobs(int appliedJobId) {
try {
			
			AdmSaveJob job = hibernateTemplate.load(AdmSaveJob.class,appliedJobId);
			job.setDeleteDt(new Date());
			hibernateTemplate.saveOrUpdate(job);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return false;
	}

	
	/**
	   @Author :Prince Mathew
	   @Purpose:This method is used to get the list of the all job applied by the job seeker
	   @Created:Jul 26, 2012
	   @Param  :jobSeekerId
	   @Return :List of the AppliedJobDTO
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerActivityDAO#getAppliedJobs(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getAppliedJobs(int jobSeekerId) {
		List<AppliedJobDTO> appliedJobDTOList = null;

		try {
			if (jobSeekerId != 0) {
				appliedJobDTOList = new ArrayList<AppliedJobDTO>();
				List<AdmSaveJob> jobList=(List<AdmSaveJob>)hibernateTemplate.find("from AdmSaveJob asj where asj.userId=? and asj.appliedDt is not NULL and asj.deleteDt is NULL",jobSeekerId);
				appliedJobDTOList=jobSeekerActivityConversionHelper.transformToApplidJobDTO(jobList);
			}
		} catch (HibernateException e) {
			// waiting for exception
		}

		return appliedJobDTOList;

	}

	/**
	 * deleting selected saved job
	 */
	
	@Override
	public boolean deleteSavedJobs(int savedJobId) {
        try {
			AdmSaveJob job = hibernateTemplate.load(AdmSaveJob.class,savedJobId);
			job.setDeleteDt(new Date());
			hibernateTemplate.saveOrUpdate(job);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return false;
	}

/*	public JpJob getById(int id) {
		//return (JpJob) sessionFactory.getCurrentSession().get(JpJob.class,id);
	}
*/
	/**
	 * implementation of get saved jobs
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getSavedJobs(int jobSeekerId) {
		List<AppliedJobDTO> appliedJobDTOList = null;
       System.out.println("DDDDDDDDDDDDD"+jobSeekerId);
		try {
			if (jobSeekerId != 0) {
				appliedJobDTOList = new ArrayList<AppliedJobDTO>();
				List<AdmSaveJob> jobList=(List<AdmSaveJob>)hibernateTemplate.find("from AdmSaveJob asj where asj.userId=? and asj.appliedDt is NULL and asj.deleteDt is NULL",jobSeekerId);
				appliedJobDTOList=jobSeekerActivityConversionHelper.transformToDTOForSavedJob(jobList);
				
			}
		} catch (HibernateException e) {
			// waiting for exception
		}

		return appliedJobDTOList;
	}


/*	public JobSeekerActivityConversionHelper getJobSeekerActivityConversionHelper() {
		return jobSeekerActivityConversionHelper;
	}

	public void setJobSeekerActivityConversionHelper(
			JobSeekerActivityConversionHelper jobSeekerActivityConversionHelper) {
		this.jobSeekerActivityConversionHelper = jobSeekerActivityConversionHelper;
	}

*/
}
