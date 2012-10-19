package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
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
			// TODO: handle exception
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
				List<AdmSaveJob> jobList = (List<AdmSaveJob>) hibernateTemplate
						.find("from AdmSaveJob asj where asj.userId=? and asj.appliedDt is not NULL and asj.deleteDt is NULL",
								jobSeekerId);
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
				List<AdmSaveJob> jobList = (List<AdmSaveJob>) hibernateTemplate
						.find("from AdmSaveJob asj where asj.userId=? and asj.appliedDt is NULL and asj.deleteDt is NULL",
								jobSeekerId);
				appliedJobDTOList = jobSeekerJobDetailConversionHelper
						.transformToDTOForSavedJob(jobList);

			}
		} catch (HibernateException e) {
			LOGGER.debug("Error while fetching the saved jobs of the corresponding job seeker");
			throw new JobBoardDataException(
					"Error while fetching the applied jobs of the corresponding job seeker"
							+ e);
		}

		return appliedJobDTOList;
	}

}
