package com.advanceweb.afc.jb.job.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpSaveJob;
import com.advanceweb.afc.jb.jobseeker.helper.JobSearchActivityConversionHelper;
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerActivityConversionHelper;

/**
 * <code> JobSearchActivityDAOImpl </code> is a DAO implementation class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Repository("jobSearchActivityDAO")
public class JobSearchActivityDAOImpl implements JobSearchActivityDAO {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	private static final Logger LOGGER = Logger
			.getLogger(JobSearchActivityDAOImpl.class);

	@Autowired
	private JobSearchActivityConversionHelper jobSearchActivityConversionHelper;

	@Autowired
	private JobSeekerActivityConversionHelper jobSeekerActivityConversionHelper;

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
				SearchedJobDTO searchedJobDTO = jobSearchActivityConversionHelper
						.transformJpJobToSearchedJobDTO(jpJob);
				jobDetail = searchedJobDTO;
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("viewJobDetails ERROR");
		} catch (Exception ex) {
			// logger call
			LOGGER.info("ex-ERROR");
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
						.find("from AdmSaveJob where jpJob.jobId = ? and userId = ? and deleteDt = null",
								jobId, userId);
				jobDetail = jobSeekerActivityConversionHelper
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
		if(jobDetail != null && !jobDetail.isEmpty()){
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
			/**
			 * save the job in DB
			 * 
			 */
			AdmSaveJob admSaveJob = jobSearchActivityConversionHelper
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
			/**
			 * save the job in DB
			 * 
			 */
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
		JpSaveJob jpSaveJob = jobSearchActivityConversionHelper
				.transformSearchedJobDTOtoJpSaveJob(searchedJobDTO);
		hibernateTemplate.saveOrUpdate(jpSaveJob);
	}

}
