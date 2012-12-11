package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.ResPublishResumeStat;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerJobDetailConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@Repository("jobSeekerJobDetailDAO")
public class JobSeekerJobDetailDAOImpl implements JobSeekerJobDetailDAO {

	private HibernateTemplate hibernateTemplate;
	@Autowired
	private JobSeekerJobDetailConversionHelper jobSeekerJobDetailConversionHelper;
	private static final Logger LOGGER = Logger.getLogger(JobSeekerJobDetailDAOImpl.class);
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}


	/**
	 * @Purpose:This method is used to update the delete data of the applied or saved depending on the appliedJobId , which is the PK of the AdmSaveJob table
	 * @Created:Jul 26, 2012
	 * @Param :appliedJobId
	 * @Return :true or false
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerJobDetailDAO#updateAppliedSavedJobs(int)
	 */
	@Override
	public boolean updateAppliedSavedJobs(int appliedJobId)throws JobBoardDataException  {
		boolean result=false;
		try {

			AdmSaveJob job = hibernateTemplate.load(AdmSaveJob.class, appliedJobId);
			job.setDeleteDt(new Date());
			hibernateTemplate.saveOrUpdate(job);
			result= true;
		} catch (Exception e) {
			LOGGER.debug("Error while updating the delete date of the corresponding applied or saved job");
			throw new JobBoardDataException(
					"Error while updating the delete date of the corresponding applied or saved job"
							+ e);
		}
		return result;
	}

	/**
	 * @Purpose:This method is used to get the list of all job applied by
	 *               the corresponding job seeker
	 * @Created:Jul 26, 2012
	 * @Param :jobSeekerId
	 * @Return :List of the AppliedJobDTO
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerJobDetailDAO#getAppliedJobs(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getAppliedJobs(int jobSeekerId)throws JobBoardDataException   {
		List<AppliedJobDTO> appliedJobDTOList = null;

		try {
			if (jobSeekerId != 0) {
				appliedJobDTOList = new ArrayList<AppliedJobDTO>();
				List<AdmSaveJob> jobList = new ArrayList<AdmSaveJob>();
				Query query = hibernateTemplate.getSessionFactory()
						.getCurrentSession()
						.createQuery("from AdmSaveJob asj where asj.userId=:userId and asj.appliedDt is not NULL and asj.deleteDt is NULL order by asj.saveJobId desc");
				query.setParameter("userId", jobSeekerId);
				query.setFirstResult(0);
				query.setMaxResults(30);
				jobList = query.list();
				appliedJobDTOList = jobSeekerJobDetailConversionHelper
						.transformToApplidJobDTO(jobList);
			}
		} catch (Exception e) {
			LOGGER.debug("Error while fetching the applied jobs of the corresponding job seeker");
			throw new JobBoardDataException(
					"Error while fetching the applied jobs of the corresponding job seeker"
							+ e);
		}

		return appliedJobDTOList;

	}

	/**
	 * @Purpose:This method is used to get the list of all job saved by
	 *               the corresponding job seeker
	 * @Created:Jul 26, 2012
	 * @Param :jobSeekerId
	 * @Return :List of the AppliedJobDTO
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerJobDetailDAO#getAppliedJobs(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getSavedJobs(int jobSeekerId)throws JobBoardDataException {
		List<AppliedJobDTO> appliedJobDTOList = null;

		try {
			if (jobSeekerId != 0) {
				appliedJobDTOList = new ArrayList<AppliedJobDTO>();
				List<AdmSaveJob> jobs = new ArrayList<AdmSaveJob>();
				Query query = hibernateTemplate.getSessionFactory()
						.getCurrentSession()
						.createQuery("from AdmSaveJob asj where asj.userId=:userId and asj.appliedDt is NULL and asj.deleteDt is NULL order by asj.saveJobId desc");
				query.setParameter("userId", jobSeekerId);
				query.setFirstResult(0);
				query.setMaxResults(30);
				jobs = query.list();
				appliedJobDTOList = jobSeekerJobDetailConversionHelper
						.transformToDTOForSavedJob(jobs);

			}
		} catch (HibernateException e) {
			LOGGER.debug("Error while fetching the saved jobs of the corresponding job seeker");
			throw new JobBoardDataException(
					"Error while fetching the applied jobs of the corresponding job seeker"
							+ e);
		}

		return appliedJobDTOList;
	}

	/**
	 * This method gets the number of times the resume was viewed by an
	 * Employer and also the number of time the resume appeared in Search
	 * 
	 * @param jobSeekerId
	 * @return List<Integer>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getEmployerViews(int jobSeekerId) {
		List<Integer> empViews = new ArrayList<Integer>();
		empViews.add(0);
		empViews.add(0);
		int publishResumeId = 0;
		try {
			List<ResUploadResume> uploadResumeList = (List<ResUploadResume>) hibernateTemplate
					.find("from ResUploadResume where isPublished=1 and userId=?",
							jobSeekerId);
			if (null != uploadResumeList && !uploadResumeList.isEmpty()
					&& null != uploadResumeList.get(0).getResPublishResume()) {
				publishResumeId = uploadResumeList.get(0).getResPublishResume()
						.getPublishResumeId();
			}

			ResPublishResumeStat resumeStat = hibernateTemplate.get(
					ResPublishResumeStat.class, publishResumeId);
			if (null != resumeStat) {
				empViews.set(0, resumeStat.getEmployerViews());
				empViews.set(1, resumeStat.getEmployerImpressions());
			} 
		} catch (Exception e) {
			LOGGER.error(e);
			return empViews;
		}

		return empViews;

	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<AppliedJobDTO> getAppliedJobsByCriteria(int jobSeekerId,String startDate,String endDate)throws JobBoardDataException   {
		List<AdmSaveJob> jobList = null;
		List<AppliedJobDTO> appliedJobDTOList = null;
		try {
			LOGGER.info("Login Date Time: "+startDate+"  LogOut Date Time : "+endDate);
			if (jobSeekerId != 0) {
				 jobList = (List<AdmSaveJob>) hibernateTemplate
						.find("from AdmSaveJob asj where asj.userId=? and  (asj.appliedDt > DATE_FORMAT('"+startDate+"', '%Y-%m-%d %T') and asj.appliedDt < DATE_FORMAT('"+endDate+"', '%Y-%m-%d %T')) and asj.deleteDt is NULL",
								jobSeekerId);
				 appliedJobDTOList = jobSeekerJobDetailConversionHelper
							.transformToDTOForSavedJob(jobList);
			}
		} catch (Exception e) {
			LOGGER.debug("Error while fetching the applied jobs of the corresponding job seeker");
			throw new JobBoardDataException(
					"Error while fetching the applied jobs of the corresponding job seeker"
							+ e);
		}

		return appliedJobDTOList;

	}
}
