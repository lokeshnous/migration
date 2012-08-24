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
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerJobDetailConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@Repository("jobSeekerJobDetailDAO")
public class JobSeekerJobDetailDAOImpl implements JobSeekerJobDetailDAO {

	@SuppressWarnings("unused")
	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private JobSeekerJobDetailConversionHelper jobSeekerJobDetailConversionHelper;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void finalize() throws Throwable {

	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:This method is used to delete the job applied by the Job Seeker
	 * @Created:Jul 26, 2012
	 * @Param :appliedJobId
	 * @Return :boolean value depends on the result
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerJobDetailDAO#updateAppliedSavedJobs(int)
	 */
	@Override
	public boolean updateAppliedSavedJobs(int jobId) {
		try {

			AdmSaveJob job = hibernateTemplate.load(AdmSaveJob.class, jobId);
			job.setDeleteDt(new Date());
			hibernateTemplate.saveOrUpdate(job);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:This method is used to get the list of the all job applied by
	 *               the job seeker
	 * @Created:Jul 26, 2012
	 * @Param :jobSeekerId
	 * @Return :List of the AppliedJobDTO
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerJobDetailDAO#getAppliedJobs(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getAppliedJobs(int jobSeekerId) {
		List<AppliedJobDTO> appliedJobDTOList = null;

		try {
			if (jobSeekerId != 0) {
				appliedJobDTOList = new ArrayList<AppliedJobDTO>();
				List<AdmSaveJob> jobList = (List<AdmSaveJob>) hibernateTemplate
						.find("from AdmSaveJob asj where asj.userId=? and asj.appliedDt is not NULL and asj.deleteDt is NULL",
								jobSeekerId);
				appliedJobDTOList = jobSeekerJobDetailConversionHelper
						.transformToApplidJobDTO(jobList);
			}
		} catch (HibernateException e) {
			// TODO: handle exception
		}

		return appliedJobDTOList;

	}

	/**
	 * implementation of get saved jobs
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getSavedJobs(int jobSeekerId) {
		List<AppliedJobDTO> appliedJobDTOList = null;

		try {
			if (jobSeekerId != 0) {
				appliedJobDTOList = new ArrayList<AppliedJobDTO>();
				List<AdmSaveJob> jobList = (List<AdmSaveJob>) hibernateTemplate
						.find("from AdmSaveJob asj where asj.userId=? and asj.appliedDt is NULL and asj.deleteDt is NULL",
								jobSeekerId);
				appliedJobDTOList = jobSeekerJobDetailConversionHelper
						.transformToDTOForSavedJob(jobList);

			}
		} catch (HibernateException e) {
			// TODO: handle exception
		}

		return appliedJobDTOList;
	}

}
