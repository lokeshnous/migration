package com.advanceweb.afc.jb.job.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.jobseeker.helper.JobSearchConversionHelper;
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerJobDetailConversionHelper;

/**
 * <code> JobSearchDAOImpl </code> is a DAO implementation class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Repository("jobSearchDAO")
public class JobSearchDAOImpl implements JobSearchDAO {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	private static final Logger LOGGER = Logger
			.getLogger(JobSearchDAOImpl.class);

	@Autowired
	private JobSearchConversionHelper jobSearchConversionHelper;

	@Autowired
	private JobSeekerJobDetailConversionHelper jobSeekerJobDetailConversionHelper;

	/**
	 * implementation of viewJobDetails
	 */
	@Override
	@Transactional(readOnly = true)
	public SearchedJobDTO viewJobDetails(long jobId) {
		SearchedJobDTO jobDetail = null;

		try {
			if (jobId != 0) {
				JpJob jpJob = (JpJob) hibernateTemplate.get(JpJob.class,
						(int) jobId);
				SearchedJobDTO searchedJobDTO = jobSearchConversionHelper
						.transformJpJobToSearchedJobDTO(jpJob);
				jobDetail = searchedJobDTO;
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("viewJobDetails ERROR");
		} catch (Exception ex) {
			// logger call
			LOGGER.info("ex-ERROR "+ex);
		}
		return jobDetail;
	}

	/**
	 * implementation of viewJobDetails
	 */
	@Override
	@Transactional(readOnly = true)
	public AppliedJobDTO fetchSavedOrAppliedJob(SearchedJobDTO searchedJobDTO,
			int userId) {
		List<AppliedJobDTO> jobDetail = null;
		int jobId = searchedJobDTO.getJobID();
		try {
			if (userId != 0) {
				@SuppressWarnings("unchecked")
				List<AdmSaveJob> admSaveJobs = hibernateTemplate
						.find("from AdmSaveJob where jpJob.jobId = ? and userId = ? and deleteDt is null",
								jobId, userId);
				jobDetail = jobSeekerJobDetailConversionHelper
						.transformToApplidJobDTO(admSaveJobs);
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("ERROR");
		} catch (Exception ex) {
			// logger call
			LOGGER.info("ex-ERROR");
		}
		AppliedJobDTO appliedJobDTO = null;
		if (jobDetail != null && !jobDetail.isEmpty()) {
			appliedJobDTO = jobDetail.get(0);
		}
		return appliedJobDTO;
	}

	/**
	 * implementation of create save or apply the job for logged in user
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean saveOrApplyJob(AppliedJobDTO jobDTO) {
		boolean status = false;
		try {
			// save the job in DB
			AdmSaveJob admSaveJob = jobSearchConversionHelper
					.transformJobDTOToAdmSaveJob(jobDTO);
			hibernateTemplate.save(admSaveJob);
			status = true;
		} catch (HibernateException e) {
			status = false;
			// logger call
			LOGGER.info("ERROR");
		}
		return status;
	}

	/**
	 * implementation of update save or apply the job for logged in user
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean updateSaveOrApplyJob(AppliedJobDTO jobDTO) {
		boolean status = false;
		try {
			// save the job in DB
			AdmSaveJob admSaveJob = (AdmSaveJob) hibernateTemplate.load(
					AdmSaveJob.class, jobDTO.getSaveJobId());

			admSaveJob.setAppliedDt(new java.util.Date());
			hibernateTemplate.update(admSaveJob);
			status = true;
		} catch (HibernateException e) {
			status = false;
			// logger call
			LOGGER.info("ERROR");
		}
		return status;
	}

	// To Save the save searched job details to DB
	@Override
	public void saveTheJob(SearchedJobDTO searchedJobDTO) {
		// Transforming the saveSearchedJobsDTO to Save Search Entity
		AdmSaveJob jpSaveJob = jobSearchConversionHelper
				.transformSearchedJobDTOtoJpSaveJob(searchedJobDTO);
		hibernateTemplate.saveOrUpdate(jpSaveJob);
	}

	/**
	 * Fetch the apply type of job
	 * 
	 * @param jobId
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JobApplyTypeDTO applyJobDetails(int jobId) {
		JobApplyTypeDTO jobApplyTypeDTO = null;
		try {
			JpJob jpJob = new JpJob();
			jpJob.setJobId(jobId);
			List<JpJobApply> jpJobApply = hibernateTemplate.find(
					"from JpJobApply where jpJob = ? and active = 1", jpJob);
			List<JobApplyTypeDTO> jobApplyTypeDTOs = jobSearchConversionHelper
					.transformJpJobApplytoJobApplyTypeDTO(jpJobApply);
			jobApplyTypeDTO = jobApplyTypeDTOs.get(0);
		} catch (Exception e) {
			LOGGER.info("applyJobDetails : ERROR");
		}
		return jobApplyTypeDTO;
	}
	
	
	/**
	 * This method is used to get the total number of Active jobs.
	 * @return long
	 */
	
	public long getTotalActiveJobs(){
		long totalNoOfActiveJobs = 0L;
		try {
			totalNoOfActiveJobs = DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from JpJob where active=1"));
			LOGGER.info("Total number of Active Job is "+totalNoOfActiveJobs);
		} catch (HibernateException he) {
			LOGGER.info("Error occured while getting the Total Active Jobs from Database" + he);
		}
		return totalNoOfActiveJobs;
	}

}
