package com.advanceweb.afc.jb.job.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
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
@SuppressWarnings("unchecked")
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
			LOGGER.info("ex-ERROR " + ex);
		}
		return jobDetail;
	}

	/**
	 * This method will fetch the last five job details based on posted date for
	 * the selected employer.
	 * 
	 * @param jobId
	 * @return List<SearchedJobDTO> object
	 */

	public List<JobPostDTO> getRecentJobsPostedByEmployer(long facilityID,
			long jobID) {

		List<JobPostDTO> srchJobList = new ArrayList<JobPostDTO>();

		try {
			hibernateTemplate.setMaxResults(5);
			List<JpJob> jpJobList = hibernateTemplate
					.find(" from  JpJob WHERE  admFacility=" + facilityID
							+ " and jobId not in (" + jobID
							+ ") ORDER BY  createDt DESC");

			if (jpJobList != null) {
				for (JpJob jpJob : jpJobList) {
					if (jpJob.getJobId() != facilityID) {
						JobPostDTO jobPostDTO = new JobPostDTO();
						jobPostDTO.setJobTitle(jpJob.getJobtitle());
						// jobPostDTO.setJobCity(jpJob.get);
						// jobPostDTO.setJobCountry(jobCountry);
						// jobPostDTO.setJobState();
						jobPostDTO.setJobId(jpJob.getJobId());
						jobPostDTO.setJobDesc(jpJob.getAdtext());
						srchJobList.add(jobPostDTO);
					}
				}
			}

		} catch (HibernateException e) {
			LOGGER.info("HibernateException occurred while getting recent jobs posted by the Employer"
					+ e);
		} catch (Exception ex) {
			LOGGER.info("Error occurred while getting recent jobs posted by the Employer "
					+ ex);
		}
		return srchJobList;
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
	@Override
	public JobApplyTypeDTO applyJobDetails(int jobId) {
		JobApplyTypeDTO jobApplyTypeDTO = null;
		try {
			JpJob jpJob = new JpJob();
			jpJob.setJobId(jobId);
			// Removed the active column as per the need
			// List<JpJobApply> jpJobApply = hibernateTemplate.find(
			// "from JpJobApply where jpJob = ? and active = 1", jpJob);
			List<JpJobApply> jpJobApply = hibernateTemplate.find(
					"from JpJobApply where jpJob = ?", jpJob);
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
	 * 
	 * @return long
	 */

	public long getTotalActiveJobs() {
		long totalNoOfActiveJobs = 0L;
		try {
			totalNoOfActiveJobs = DataAccessUtils.intResult(hibernateTemplate
					.find("select count(*) from JpJob where active=1"));
			LOGGER.info("Total number of Active Job is " + totalNoOfActiveJobs);
		} catch (HibernateException he) {
			LOGGER.info("Error occured while getting the Total Active Jobs from Database"
					+ he);
		}
		return totalNoOfActiveJobs;
	}

	/**
	 * This method is used to get the browse jobs by title
	 * 
	 * @return List<SearchedJobDTO> object
	 */
	@Override
	public List<SearchedJobDTO> getJobsByTitle() {
		List<SearchedJobDTO> jobDTOs = new ArrayList<SearchedJobDTO>();
		List<JpJob> jpJbList = new ArrayList<JpJob>();
		try {
			jpJbList = hibernateTemplate
					.find("SELECT distinct j.jobtitle,count(j.jobtitle)  from JpJob j where j.active=1 group by j.jobtitle");
			Iterator<?> iterator = jpJbList.iterator();
			while (iterator.hasNext()) {
				SearchedJobDTO dto = new SearchedJobDTO();
				Object[] row = (Object[]) iterator.next();
				Long count = (Long) row[1];
				dto.setJobTitle((String) row[0]);
				dto.setCount(count.intValue());
				jobDTOs.add(dto);
			}
		} catch (HibernateException e) {
			LOGGER.info("Error occured while getting the job title list from Database"
					+ e);
		}
		return jobDTOs;
	}

	/**
	 * This method is used to get the browse jobs list by Employer
	 * 
	 * @return List of employerDTOs
	 */
	public List<SearchedJobDTO> getJobsByEmployer() {
		List<SearchedJobDTO> employerDTOs = new ArrayList<SearchedJobDTO>();
		List<JpJob> jpJbList = new ArrayList<JpJob>();
		try {
			jpJbList = hibernateTemplate
					.find("SELECT distinct j.facility,count(j.facility)  from JpJob j where j.active=1 group by j.facility");
			Iterator<?> iterator = jpJbList.iterator();
			while (iterator.hasNext()) {
				SearchedJobDTO dto = new SearchedJobDTO();
				Object[] row = (Object[]) iterator.next();
				Long count = (Long) row[1];
				dto.setCompanyName((String) row[0]);
				dto.setCount(count.intValue());
				employerDTOs.add(dto);
			}
		} catch (HibernateException e) {
			LOGGER.info("Error occured while getting the job employers list from Database"
					+ e);
		}
		return employerDTOs;
	}

	/**
	 * This method is used to get the browse jobs list by location
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SearchedJobDTO> getJobsByLocation() {
		Query getLocationData = hibernateTemplate.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(" { call GetLocationData() }");
		List<?> locationDeatils = getLocationData.list();
		Iterator<?> iterator = locationDeatils.iterator();
		List<SearchedJobDTO> locationDTOs = new ArrayList<SearchedJobDTO>();
		while (iterator.hasNext()) {
			SearchedJobDTO dto = new SearchedJobDTO();
			Object[] row = (Object[]) iterator.next();
			BigInteger count = (BigInteger) row[1];
			dto.setStateFullName((String) row[0]);
			dto.setCount(count.intValue());
			locationDTOs.add(dto);
		}
		return locationDTOs;
	}
}
