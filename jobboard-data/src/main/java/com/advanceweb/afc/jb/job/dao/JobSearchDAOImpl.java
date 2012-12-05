package com.advanceweb.afc.jb.job.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.MerApplication;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.VstSessioninfo;
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
	private HibernateTemplate hibernateTemplateTracker;
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory,
			SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
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
	public JobDTO viewJobDetails(long jobId) {
		JobDTO jobDetail = null;

		try {
			if (jobId != 0) {
				JpJob jpJob = (JpJob) hibernateTemplate.get(JpJob.class,
						(int) jobId);
				JobDTO jobDTO = jobSearchConversionHelper
						.transformJpJobToJobDTO(jpJob);
				jobDetail = jobDTO;
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.error("HibernateException Exception on geting job details", e);
		} catch (Exception ex) {
			// logger call
			LOGGER.error("Exception on geting job details", ex);
		}
		return jobDetail;
	}

	/**
	 * This method will fetch the last five job details based on posted date for
	 * the selected employer.
	 * 
	 * @param jobId
	 * @return List<jobDTO> object
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
			LOGGER.error("HibernateException occurred while getting recent jobs posted by the Employer"
					, e);
		} catch (Exception ex) {
			LOGGER.error("Error occurred while getting recent jobs posted by the Employer " ,
					 ex);
		}
		return srchJobList;
	}

	/**
	 * implementation of viewJobDetails
	 */
	@Override
	@Transactional(readOnly = true)
	public AppliedJobDTO fetchSavedOrAppliedJob(JobDTO jobDTO,
			int userId) {
		List<AppliedJobDTO> jobDetail = null;
		int jobId = jobDTO.getJobId();
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
			LOGGER.error("HibernateException Exception while fetching Saved or applied job ", e);
		} catch (Exception ex) {
			// logger call
			LOGGER.error("Exception while fetching Saved or applied job ", ex);
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
			LOGGER.error("Exception while Saving job ", e);
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
			LOGGER.error("Exception while updating job :", e);
		}
		return status;
	}

	// To Save the save searched job details to DB
	@Override
	public void saveTheJob(JobDTO jobDTO) {
		// Transforming the saveSearchedJobsDTO to Save Search Entity
		AdmSaveJob jpSaveJob = jobSearchConversionHelper
				.transformJobDTOtoJpSaveJob(jobDTO);
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
	 * This method is used to remove the data in database
	 * 
	 * @return
	 */
	@Override
	public void clearRecentSearches(int userId) {

		List<AdmSaveSearch> admsearches = hibernateTemplate.find(" from AdmSaveSearch where userId =? and searchName = ''",userId);

		hibernateTemplate.deleteAll(admsearches);

	}
	
	/**
	 * This method is used to check existing session in application
	 * 
	 * @return
	 */
	@Override
	public void inserSessinfo(String session_id, int userId) {

		

		VstSessioninfo vssinfo = new VstSessioninfo();
		vssinfo.setSessionId(session_id);
		MerApplication merapp = new MerApplication();
		merapp.setApplicationId(1);

		vssinfo.setMerApplication(merapp);
		vssinfo.setDeviceId("");
		MerUser ms = new MerUser();
		ms.setUserId(userId);

		vssinfo.setMerUser(ms);
		vssinfo.setStartDt(MMUtils.getCurrentDateAndTime());
		vssinfo.setIpAddress("");
		vssinfo.setReferringUrl("");
		vssinfo.setLastSessionId("");
		vssinfo.setLatitude(11);
		vssinfo.setLongitude(11);
		vssinfo.setUserAgent("");

		vssinfo.setCreateDt(new Timestamp(new Date().getTime()));

		List<VstSessioninfo> vstSessioninfos = hibernateTemplateTracker.find("from VstSessioninfo where user_id =? and session_id =?", userId,session_id);

		StringBuffer buffer = new StringBuffer();

		for (VstSessioninfo sessioninfo : vstSessioninfos) {
			buffer.append(sessioninfo.getSessionId());
		}

		boolean isSeesionAvailabe = ((buffer.toString().equals(session_id)) ? true
				: false);

		if (!isSeesionAvailabe) {
			hibernateTemplateTracker.saveOrUpdate(vssinfo);
		}

	}

	
	//here implementation work after descsion
	/*@Override
	public List<VstSessioninfo> getSessionId(String newSession_id) {
		// TODO Auto-generated method stub
		List<VstSessioninfo> admHistoryList = hibernateTemplateTracker.find("from VstSessioninfo where session_id =?",newSession_id);
		
		return admHistoryList;
	}

	@Override
	public void insertSessionId(Integer sessioninfo_id) {
		// TODO Auto-generated method stub
		
		VstSearch vstList = new VstSearch();
		VstSessioninfo vssessinfo=new VstSessioninfo();
		vssessinfo.setSessioninfoId(sessioninfo_id);
		vstList.setVstSessioninfo(vssessinfo);
		
		vstList.setSearchDt(MMUtils.getCurrentDateAndTime());
		
		
		List<VstSearch> vstSearchs = hibernateTemplateTracker.find("from VstSearch where sessioninfo_id =?", sessioninfo_id);
		
		List<Integer> searchSeq = new ArrayList<Integer>();

		for (VstSearch search : vstSearchs) {
			searchSeq.add(search.getSearchSeq());
		}
		
		int length = searchSeq.size();
		int lastSeq = 0;
		
		if(length > 0){
			lastSeq = searchSeq.get(0);
		}
		lastSeq=lastSeq+1;
		vstList.setSearchSeq(lastSeq);
		
		hibernateTemplateTracker.saveOrUpdate(vstList);
	}

	*/
		
}
