package com.advanceweb.afc.jb.employer.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.employer.helper.JobPostConversionHelper;

/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 18, 2012
 * @Purpose: This class implements all the DAO operations related to post new
 *           job
 */
@Transactional
@Repository("employerJobPostDAO")
@SuppressWarnings("unchecked")
public class JobPostDAOImpl implements JobPostDAO {

	private static final String FIND_ADM_USER_FACILITY = "select facility from AdmUserFacility facility, AdmRole role where role.roleId=facility.id.roleId and facility.id.userId=? and role.name=?";
	private static final String FIND_EXPIRED_JOBS = "from JpJob job where job.active='1' and date_format(job.endDt, '%Y-%m-%d') = ?";
	private static final String FIND_EXPIRED_JOBS_FOR_RENEWAL = "from JpJob job where job.active='1'and job.autoRenew='1' and date_format(job.endDt, '%Y-%m-%d') = ?";
	private static final String FIND_SCHEDULED_JOBS = "from JpJob job where date_format(job.startDt, '%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') and job.active='0'";


	private static final Logger LOGGER = Logger.getLogger(JobPostDAOImpl.class);

	private HibernateTemplate hibernateTemplate;
	private int numberOfJobRecordsByStatus;
	@Autowired
	private JobPostConversionHelper jobPostConversionHelper;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/*
	 * @Autowired public void setHibernateTemplate(SessionFactory
	 * sessionFactory) { this.hibernateTemplateTracker = new
	 * HibernateTemplate(sessionFactory); }
	 */
	/**
	 * @Author :Prince Mathew
	 * @Purpose:To get the records of the Employer by userId from the DB
	 * @Created:Jul 19, 2012
	 * @Param :userId
	 * @Return :EmployerInfoDTO containing the information of corresponding
	 *         Employer
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getEmployerInfo(int)
	 */
	@Override
	public EmployerInfoDTO getEmployerInfo(int userId, String roleName) {

		EmployerInfoDTO employerInfoDTO = new EmployerInfoDTO();

		Object[] inputs = { userId, roleName };

		List<AdmUserFacility> facilityList = hibernateTemplate.find(
				FIND_ADM_USER_FACILITY, inputs);

		if (null != facilityList && !facilityList.isEmpty()) {
			AdmUserFacility userFacility = facilityList.get(0);
			AdmFacility facility = userFacility.getAdmFacility();
			if (null != facility) {
				employerInfoDTO.setCustomerNamel(facility.getName());
				employerInfoDTO.setUserId(userFacility.getFacilityPK()
						.getUserId());
				employerInfoDTO.setFacilityId(userFacility.getFacilityPK()
						.getFacilityId());
				employerInfoDTO.setRoleId(userFacility.getFacilityPK()
						.getRoleId());
			}
		}

		return employerInfoDTO;
	}

	/**
	 * @Author :Prince Mathew
	 * @Purpose:This method is called to post the new job
	 * @Created:Jul 20, 2012
	 * @Param :EmployerInfoDTO object
	 * @Return :boolean result
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#savePostJob(com.advanceweb.afc.jb.common.EmployerInfoDTO)
	 */
	@Override
	public boolean savePostJob(JobPostDTO dto) {

		try {
			JpLocation location = null;
			// Retrieving location Id based on the data selection while posting
			// the new job
			Object[] inputs = { dto.getJobCountry(), dto.getJobState(),
					dto.getJobCity(), dto.getJobZip() };
			List<JpLocation> locationList = hibernateTemplate
					.find("from JpLocation loc where loc.country=? and loc.state=? and loc.city=? and loc.postcode=?",
							inputs);
			if (null != locationList && !locationList.isEmpty()) {
				location = locationList.get(0);
			}

			// Retrieving the template object selected based on the template id
			// selected by the user
			JpTemplate template = hibernateTemplate.load(JpTemplate.class,
					Integer.valueOf(dto.getBrandTemplate()));

			JpJobType jobType = hibernateTemplate.load(JpJobType.class,
					Integer.valueOf(dto.getJobPostingType()));

			AdmFacility admFacility = hibernateTemplate.load(AdmFacility.class,
					Integer.valueOf(dto.getFacilityId()));

			JpJob jpJob = jobPostConversionHelper.transformJobDtoToJpJob(dto,
					template, jobType, admFacility);
			hibernateTemplate.save(jpJob);
			List<JpJobApply> applyJobList = jobPostConversionHelper
					.transformJobPostDTOToJpJobApply(dto, jpJob);
			hibernateTemplate.saveOrUpdateAll(applyJobList);
			List<JpJobLocation> locList = jobPostConversionHelper
					.transformJobPostDTOToJpJbLocation(dto, jpJob, location);
			hibernateTemplate.saveOrUpdateAll(locList);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return true;
	}

	/**
	 * @Author :devi mishra
	 * @Purpose:This method is called to retrieve all posted job
	 * @Created:Aug 29, 2012
	 * @Param :employerId
	 * @Return :List<JobPostDTO>
	 * 
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobPost(int employerId, int offset,
			int noOfRecords) {

		List<JpJob> jobs = new ArrayList<JpJob>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
									+ employerId + "and a.deleteDt is NULL ");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			jobs = query.list();
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return jobPostConversionHelper.transformJpJobListToJobPostDTOList(jobs);

	}

	/**
	 * @Author :devi mishra
	 * @Purpose:This method is called to retrieve job as per the selected job id
	 * @Created:Aug 29, 2012
	 * @Param :jobid
	 * @Return :JobPostDTO
	 * 
	 */
	@Override
	public JobPostDTO editJob(int jobId) {
		JobPostDTO dto = new JobPostDTO();
		try {
			JpJob job = hibernateTemplate.get(JpJob.class, jobId);

			if (job != null) {

				dto = jobPostConversionHelper.transformJpJobToJobPostDTO(job);

			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return dto;
	}

	@Override
	public List<JobPostingPlanDTO> getJobPostingPlans() {
		List<JpJobType> jobTypeList = hibernateTemplate.find("from JpJobType");
		return jobPostConversionHelper
				.transformToJobPostingPlanDTOList(jobTypeList);
	}

	/**
	 * This method is called to delete the Job
	 * 
	 * @param jobId
	 * @param userId
	 * @return delete status
	 */
	@Override
	public boolean deleteJob(int jobId, int userId) {
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
		boolean bDelete = false;
		try {
			if (null != job.getEndDt() && null != job.getStartDt()) {
				int compareEndDate = job.getEndDt().compareTo(new Date());
				if ((job.getActive() == 1 && compareEndDate < 0)
						|| (job.getActive() == MMJBCommonConstants.INACTIVE)) {
					// System deletes the job postings which are in "inactive"
					// or
					// “Expired” status
					job.setDeleteDt(new Timestamp(new Date().getTime()));
					hibernateTemplate.save(job);
					bDelete = true;
				} else {
					bDelete = false;
				}
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return bDelete;
	}

	/**
	 * This method is called to update the Job
	 * 
	 * @param jobId
	 * @param userId
	 * @return delete status
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateManageJob(boolean autoRenew, String brandTemplate,
			int jobId, int userId) {
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
		try {
			// System deletes the job postings which are in “Expired” status
			job.setUpdateDt(new Timestamp(new Date().getTime()));
			job.setAutoRenew(autoRenew ? 1 : 0);
			JpTemplate template = null;
			if (Integer.valueOf(brandTemplate) > 0) {
				template = hibernateTemplate.load(JpTemplate.class,
						Integer.valueOf(brandTemplate));
			}

			job.setJpTemplate(template);
			hibernateTemplate.saveOrUpdate(job);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return true;
	}

	/**
	 * This method is called to Deactivate the Job
	 * 
	 * @param jobId
	 * @param userId
	 * @return delete status
	 */
	@Override
	public boolean deactivateJob(int jobId, int userId) {
		boolean bDeactivate = false;
		try {
			JpJob job = hibernateTemplate.get(JpJob.class, jobId);

			if (job.getActive() == MMJBCommonConstants.ACTIVE) {
				// System should deactivate the job posting which are in
				// “Active”
				// status
				job.setActive(MMJBCommonConstants.INACTIVE);
				hibernateTemplate.save(job);
				bDeactivate = true;
			} else {
				bDeactivate = false;
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return bDeactivate;
	}

	/**
	 * This method is called to repost the Job
	 * 
	 * @param jobId
	 * @param userId
	 * @return delete status
	 */
	@Override
	public boolean repostJob(int jobId, int userId) {
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
		boolean bRepost = false;
		try {
			if (null != job.getEndDt() && null != job.getStartDt()) {
				int compareEndDate = job.getEndDt().compareTo(new Date());
				if ((job.getActive() == 1 && compareEndDate < 0)
						|| (job.getActive() == MMJBCommonConstants.INACTIVE)) {
					// Repost the inactive and expired job and extend the end
					// date
					// to one month
					Calendar now = Calendar.getInstance();
					now.setTime(job.getEndDt());
					now.add(Calendar.DAY_OF_MONTH, 30);
					job.setActive(MMJBCommonConstants.ACTIVE);
					job.setEndDt(now.getTime());
					hibernateTemplate.save(job);
					bRepost = true;
				} else {
					bRepost = false;
				}
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return bRepost;
	}

	/**
	 * @Author :devi mishra
	 * @Purpose:This method is called to retrieve all posted job by Status
	 * @Created:Aug 29, 2012
	 * @Param :employerId
	 * @Return :List<JobPostDTO>
	 * 
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobByStatus(String jobStatus,
			int userId, int offset, int noOfRecords) {
		List<JpJob> jobs = new ArrayList<JpJob>();
		Long jobCount = 0L;
		Query query = null;
		try {
			if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_NEW_JOB)) {
				query = hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and a.active = 1 and (a.startDt <= CURRENT_DATE and a.endDt >= CURRENT_DATE)  and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and a.active = 1 and (a.startDt <= CURRENT_DATE and a.endDt >= CURRENT_DATE)  and a.deleteDt is NULL")
						.uniqueResult();

			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_INACTIVE)) {
				query = hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and a.active = 0 and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and a.active = 0 and a.deleteDt is NULL")
						.uniqueResult();
			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_DRAFT)) {
				query = hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 1 and a.startDt > CURRENT_DATE) and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 1 and a.startDt > CURRENT_DATE) and a.deleteDt is NULL")
						.uniqueResult();
			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_EXPIRED)) {
				query = hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 1 and a.endDt < CURRENT_DATE) and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 1 and a.endDt < CURRENT_DATE) and a.deleteDt is NULL")
						.uniqueResult();
			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_SCHEDULED)) {
				query = hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 0 and a.startDt > CURRENT_DATE) and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 0 and a.startDt > CURRENT_DATE) and a.deleteDt is NULL")
						.uniqueResult();
			}

			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			jobs = query.list();
			setNumberOfJobRecordsByStatus(jobCount.intValue());
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return jobPostConversionHelper.transformJpJobListToJobPostDTOList(jobs);
	}

	@Override
	public int getTotalNumberOfJobRecords(int employerId) {
		try {
			Long jobCount = (Long) hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
									+ employerId + "and a.deleteDt is NULL")
					.uniqueResult();
			return jobCount.intValue();
		} catch (DataAccessException e) {
			LOGGER.error(e);
			return 0;
		}

	}

	/**
	 * @return the totalNumberOfJobRecordsByStatus
	 */
	public int getNumberOfJobRecordsByStatus() {
		return numberOfJobRecordsByStatus;
	}

	/**
	 * @param totalNumberOfJobRecordsByStatus
	 *            the totalNumberOfJobRecordsByStatus to set
	 */
	public void setNumberOfJobRecordsByStatus(int numberOfJobRecordsByStatus) {
		this.numberOfJobRecordsByStatus = numberOfJobRecordsByStatus;
	}

	@Override
	public int getTotalNumberOfJobRecordsByStatus() {
		return this.numberOfJobRecordsByStatus;
	}

	@Override
	public boolean executeActiveJobWorker() {
		LOGGER.info("Executing -> executeActiveJobWorker()");
		//Update Jobs as expired
		try {
			
			List<JpJob> expiredJobs = hibernateTemplate.find(FIND_EXPIRED_JOBS, getOneDayBeforeDate());
			
			for(JpJob job : expiredJobs){
				try {
					job.setActive((byte)0);
					hibernateTemplate.saveOrUpdate(job);
				} catch (Exception e) {
					LOGGER.error("Failed to mark job as expired for job Id  " +job.getJobId());
					LOGGER.error(e);
				}
				LOGGER.info("ActiveJobsJobWorker-> Marked Job as expired successfully....." +job.getJobId());
			}
			
		} catch (DataAccessException e) {
			LOGGER.error("Failed to retreive expired jobs  ");
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		//Schedule Jobs
		try {
			
			List<JpJob> scheduledJobs = hibernateTemplate.find(FIND_SCHEDULED_JOBS);
			
			for(JpJob job : scheduledJobs){		
				//TODO
				//Check for available credits
				int credits=1;
				if(credits == 0){
					LOGGER.error(job.getName()+" Doesn't have sufficient credits to post the job " +job.getJobId());
				}else{				
					try {
						job.setActive((byte)1);
						hibernateTemplate.saveOrUpdate(job);
					} catch (Exception e) {
						LOGGER.error("Failed to post a job as Active " +job.getJobId());
						LOGGER.error(e);
					}
					LOGGER.info("ActiveJobsJobWorker-> Job is posted successfully....." +job.getJobId());
				}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}	
		LOGGER.info("Executed -> executeActiveJobWorker()");
		return false;
	}

	@Override
	public boolean executeAutoRenewalJobWorker() {
		LOGGER.info("Executing -> executeAutoRenewalJobWorker()");
		//Schedule Jobs
		try {

			List<JpJob> autoRenewJobs = hibernateTemplate.find(FIND_EXPIRED_JOBS_FOR_RENEWAL, getOneDayBeforeDate());
			
			for(JpJob job : autoRenewJobs){		
				//TODO
				//Check for available credits
				int credits=1;
				if(credits == 0){
					LOGGER.error(job.getName()+" Doesn't have sufficient credits to post the job " +job.getJobId());
				}else{				
					try {
						job.setStartDt(new Date());
						job.setEndDt(addDaysToCurrentDate());
						job.setActive((byte)1);
						hibernateTemplate.saveOrUpdate(job);
					} catch (Exception e) {
						LOGGER.error("Failed to renew the job as Active " +job.getJobId());
						LOGGER.error(e);
					}
					LOGGER.info("ActiveJobsJobWorker-> Renewal of job is done successfully....." +job.getJobId());
				}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		LOGGER.info("Executed -> executeActiveJobWorker()");
		return false;
	}
	
	private String getOneDayBeforeDate(){
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);	
		return dateFormat.format(cal.getTime());
	}
	
	private Date addDaysToCurrentDate(){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, MMJBCommonConstants.AUTO_RENEWAL_DAYS);	
		return cal.getTime();
	}

}
