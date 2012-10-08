package com.advanceweb.afc.jb.employer.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAuditPK;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpJobTypeCombo;
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
	private static final String FIND_EXPIRED_JOBS_FOR_RENEWAL = "from JpJob job where job.active='0'and job.autoRenew='1' and date_format(job.endDt, '%Y-%m-%d') = ?";
	private static final String FIND_SCHEDULED_JOBS = "from JpJob job where date_format(job.startDt, '%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') and job.active='0'";
	private static final String FIND_INVENTORY_DETAILS="select dtl from AdmFacilityInventory inv inner join inv.admInventoryDetail dtl where dtl.availableqty != 0 " +
			"and dtl.productId=? and inv.admFacility in(from AdmFacility fac where fac.facilityId=?) order by dtl.availableqty ";
	private static final String FIND_INVENTORY_DETAILS_BY_INV_ID="from AdmInventoryDetail inv  where inv.invDetailId=?)";
	private static final long MILLIS_IN_DAY = 24 * 60 * 60 * 1000;
	
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
				employerInfoDTO.setCustomerNo(String.valueOf(facility.getNsCustomerID()));
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
			
			if(!(dto.getJobId()>0) &&(MMJBCommonConstants.POST_NEW_JOB.equals(dto.getJobStatus()) && (!dto.isXmlStartEndDateEnabled())
					&& !validateAndDecreaseAvailableCredits(Integer.valueOf(dto.getJobPostingType()) ,dto.getFacilityId()))){
				return false;
			}				
			
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
			JpTemplate template = hibernateTemplate.get(JpTemplate.class,
					Integer.valueOf(dto.getBrandTemplate()));
			
			JpJobType jobType = getJobTypeDetails(dto);
			AdmFacility admFacility = hibernateTemplate.load(AdmFacility.class,
					Integer.valueOf(dto.getFacilityId()));
			
			JpJob jpJob = jobPostConversionHelper.transformJobDtoToJpJob(dto,
					template, admFacility);
			jpJob.setJpJobType(jobType);
			hibernateTemplate.saveOrUpdate(jpJob);
			
			AdmFacilityJpAudit audit = new AdmFacilityJpAudit();
			AdmFacilityJpAuditPK pKey = new AdmFacilityJpAuditPK();
			pKey.setFacilityId(dto.getFacilityId());
			pKey.setJobId(jpJob.getJobId());
			pKey.setUserId(dto.getUserId());
			pKey.setInventoryDetailId(Integer.valueOf(dto.getJobPostingType()));
			audit.setCreateDt(new Date());
			audit.setId(pKey);
			hibernateTemplate.saveOrUpdate(audit);
			
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
	 * @param dto
	 * @return
	 */
	private JpJobType getJobTypeDetails(JobPostDTO dto) {
		List<AdmInventoryDetail> invList = hibernateTemplate.find(
				FIND_INVENTORY_DETAILS_BY_INV_ID, Integer.valueOf(dto.getJobPostingType()));
		JpJobType jobType=new JpJobType();
		if (!invList.isEmpty()) {
			AdmInventoryDetail admInventoryDetail = invList.get(0);
			if (MMJBCommonConstants.JOB_TYPE_COMBO
					.equals(admInventoryDetail.getProductType())) {
				List<JpJobTypeCombo> comboList = hibernateTemplate.find(
						"from JpJobTypeCombo combo where combo.comboId=?",
						admInventoryDetail.getProductId());
				if (!comboList.isEmpty()) {
					JpJobTypeCombo combo = comboList.get(0);
					if (null != combo.getJobType()
							&& combo.getJobType()
									.equalsIgnoreCase(
											MMJBCommonConstants.STANDARD_JOB_POSTING)) {
						jobType = hibernateTemplate
								.load(JpJobType.class,
										MMJBCommonConstants.JOB_POST_TYPE_POSTING_ID);
					} else if (null != combo.getJobType()
							&& combo.getJobType()
									.equalsIgnoreCase(
											MMJBCommonConstants.JOB_POSTING_SLOT)) {
						jobType = hibernateTemplate.load(JpJobType.class,
								MMJBCommonConstants.JOB_POST_TYPE_SLOT_ID);
					}
				}
			} else {
				List<JpJobType> jpTypleList = hibernateTemplate.find(
						"from JpJobType type where type.jobTypeId=?",
						admInventoryDetail.getProductId());
				if (!jpTypleList.isEmpty()) {
					jobType = jpTypleList.get(0);
				}
			}

		}
		return jobType;
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
	public JobPostDTO editJob(int jobId, int jobPostType) {
		JobPostDTO dto = new JobPostDTO();
		try {
			JpJob job = hibernateTemplate.get(JpJob.class, jobId);

			if (job != null) {

				dto = jobPostConversionHelper.transformJpJobToJobPostDTO(job);
				int nsCustomerID = 0;
				List<FacilityDTO> admFacilityDTOList = getNSCustomerIDFromAdmFacility(Integer.valueOf(dto.getFacilityId()));
				nsCustomerID = admFacilityDTOList.get(0).getNsCustomerID();
				dto.setCustomerNo(String.valueOf(nsCustomerID));
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
				Date startDt=new Date(job.getStartDt().getTime());
				long startDateAsTimestamp = startDt.getTime();
				long currentTimestamp = new Date().getTime();
				long endtDateAsTimestamp = job.getEndDt().getTime();
				if((startDateAsTimestamp<=currentTimestamp && endtDateAsTimestamp>currentTimestamp)){
					job.setActive(MMJBCommonConstants.INACTIVE);
					hibernateTemplate.save(job);
					bDeactivate = true;
				}
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
			// Check credit detail for the specified job- starts
			if (null != job.getEndDt() && null != job.getStartDt()) {
				// Check credit detail for the specified job- starts
				if (MMJBCommonConstants.POST_NEW_JOB.equals(job.getJobStatus())
						&& !validateAndDecreaseAvailableCredits(
								Integer.valueOf(job.getJpJobType().getName()),
								job.getAdmFacility().getFacilityId())) {
					return false;
				}
				// Check credit detail for the specified job- Ends
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
			} else if (job.getActive() == MMJBCommonConstants.INACTIVE) {
				// Check credit detail for the specified job- starts
				if (MMJBCommonConstants.POST_NEW_JOB.equals(job.getJobStatus())
						&& !validateAndDecreaseAvailableCredits(
								Integer.valueOf(job.getJpJobType().getName()),
								job.getAdmFacility().getFacilityId())) {
					return false;
				} else {
					Calendar now = Calendar.getInstance();
					job.setStartDt(now.getTime());
					now.add(Calendar.DAY_OF_MONTH, 30);
					job.setActive(MMJBCommonConstants.ACTIVE);
					job.setEndDt(now.getTime());
					hibernateTemplate.save(job);
					bRepost = true;
				}
				// Check credit detail for the specified job- Ends
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
				// TODO Need to check the end date condition once the Package and plan functionality finalized
				query = hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and a.active = 1 and DATE_FORMAT(a.startDt, '%Y-%m-%d') <= CURRENT_DATE  and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and a.active = 1 and (DATE_FORMAT(a.startDt, '%Y-%m-%d') <= CURRENT_DATE and DATE_FORMAT(a.endDt, '%Y-%m-%d') >= CURRENT_DATE)  and a.deleteDt is NULL")
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
										+ " and (a.active = 0 and a.startDt is NULL and a.endDt is NULL) and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 0 and a.startDt is NULL and a.endDt is NULL) and a.deleteDt is NULL")
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
										+ " and (a.active = 1 and DATE_FORMAT(a.endDt, '%Y-%m-%d') < CURRENT_DATE) and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 1 and DATE_FORMAT(a.endDt, '%Y-%m-%d') < CURRENT_DATE) and a.deleteDt is NULL")
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
										+ " and (a.active = 0 and DATE_FORMAT(a.startDt, '%Y-%m-%d') > CURRENT_DATE) and a.deleteDt is NULL");
				jobCount = (Long) hibernateTemplate
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"SELECT count(a) from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
										+ userId
										+ " and (a.active = 0 and DATE_FORMAT(a.startDt, '%Y-%m-%d') > CURRENT_DATE) and a.deleteDt is NULL")
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

	/**
	 * This method is called to retreive all the scheduled jobs
	 */
	public List<JobPostDTO> retreiveAllScheduledJobs(){
		//Schedule Jobs			
		try {
			List<JpJob> scheduledJobs = hibernateTemplate.find(FIND_SCHEDULED_JOBS);
			List<JobPostDTO> scheduledJobDTOs = jobPostConversionHelper.transformJpJobListToJobPostDTOList(scheduledJobs);
			return scheduledJobDTOs;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}			
		return null;		
	}
	
	
	/**
	 * This method is called to retreive all the expired jobs
	 */
	public List<JobPostDTO> retreiveAllExpiredJobs(){
		//Schedule Jobs			
		try {
			List<JpJob> scheduledJobs = hibernateTemplate.find(FIND_EXPIRED_JOBS_FOR_RENEWAL, getOneDayBeforeDate());
			List<JobPostDTO> scheduledJobDTOs = jobPostConversionHelper.transformJpJobListToJobPostDTOList(scheduledJobs);
			return scheduledJobDTOs;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}			
		return null;		
	}
	
	@Override
	public boolean executeActiveJobWorker(List<JobPostDTO> jobsList) {
		LOGGER.info("Executing -> executeActiveJobWorker()");
		//Update Jobs as expired
		try {
			
			//Identify the expired jobs
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
			
			//Identify the scheduled jobs
			List<JpJob> scheduledJobs = hibernateTemplate.find(FIND_SCHEDULED_JOBS);
			
			for(JpJob job : scheduledJobs){						
				
				//Validating with net suite data to check whether the employer is featured or not 
				//And to know, whether the employer is applicable for free job posting
				JobPostDTO dto = validateJobPost(job, jobsList);					
				
				//Retreiving job posting type based on the job id
				List<AdmFacilityJpAudit> jpAuditList = hibernateTemplate.find("from AdmFacilityJpAudit audit where audit.id.jobId=?", job.getJobId());		
				
				if(!jpAuditList.isEmpty()){
					AdmFacilityJpAudit audit = jpAuditList.get(0);
					
					//Checking for available credits
					if(!dto.isXmlStartEndDateEnabled() 
							&& !validateAndDecreaseAvailableCredits(audit.getId().getInventoryDetailId(), job.getAdmFacility().getFacilityId())){
						LOGGER.error(job.getName()+" Doesn't have sufficient credits to post the job " +job.getJobId());
					}else{
						try {
							job.setFeatured((byte)(dto.isbFeatured()?1:0));
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

				}else{
					LOGGER.info("There is no job type with the given Job Id: " +job.getJobId());
				}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}	
		LOGGER.info("Executed -> executeActiveJobWorker()");
		return false;
	}

	@Override
	public boolean executeAutoRenewalJobWorker(List<JobPostDTO> jobsList) {
		LOGGER.info("Executing -> executeAutoRenewalJobWorker()");
		//Schedule Jobs
		try {

			List<JpJob> autoRenewJobs = hibernateTemplate.find(FIND_EXPIRED_JOBS_FOR_RENEWAL, getOneDayBeforeDate());
			
			for(JpJob job : autoRenewJobs){		
				
				List<AdmFacilityJpAudit> jpAuditList = hibernateTemplate.find("from AdmFacilityJpAudit audit where audit.id.jobId=?", job.getJobId());
				
				JobPostDTO dto = validateJobPost(job, jobsList);	
				
				if(!jpAuditList.isEmpty()){
					AdmFacilityJpAudit audit = jpAuditList.get(0);
					//Checking for available credits
					if(!dto.isXmlStartEndDateEnabled() 
							&& !validateAndDecreaseAvailableCredits(audit.getId().getInventoryDetailId(), job.getAdmFacility().getFacilityId())){
						LOGGER.error(job.getName()+" Doesn't have sufficient credits to post the job " +job.getJobId());
					}else{
						try {
							job.setFeatured((byte)(dto.isbFeatured()?1:0));
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

				}else{
					LOGGER.info("There is no job type with the given Job Id: " +job.getJobId());
				}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		LOGGER.info("Executed -> executeActiveJobWorker()");
		return false;
	}
	
	/**
	 * This method is called to validate available credits against the job id
	 * if the credits are not available it will return false otherwise it will 
	 * post the job/renewal the job and will decrease the credits.
	 * 
	 * @param invDtlId
	 * @param facilityId
	 * @return boolean	
	 */
	public boolean validateAndDecreaseAvailableCredits(int invDtlId, int facilityId){
		
		LOGGER.info("Executing -> decreaseAvailableCredits()");
		//Schedule Jobs
		try {			
			//Based on inventory detail id, we are retreiving product id
			//For example if the product id is having multiple purchases, we are going to dedut form old purchases
			List<AdmInventoryDetail> invDtlList = hibernateTemplate.find("from AdmInventoryDetail dtl where dtl.invDetailId=?", invDtlId);			
			if(!invDtlList.isEmpty()){				
				AdmInventoryDetail invDtl = invDtlList.get(0);
				Object[] inputs = {invDtl.getProductId(), facilityId};
				List<AdmInventoryDetail> invDtls = hibernateTemplate.find(FIND_INVENTORY_DETAILS, inputs);				
				if(!invDtls.isEmpty()){					
					try {
						//Deducting available quantity
						AdmInventoryDetail dtl = invDtls.get(0);
						dtl.setAvailableqty(dtl.getAvailableqty()-1);
						hibernateTemplate.saveOrUpdate(dtl);
					} catch (Exception e) {
						LOGGER.error(" Exception has been occured while the posting the job for Inventory Detail Id: " +invDtlId);
						e.printStackTrace();
					}	
					return true;
				}else{
					LOGGER.info("There is no job type with the given inventory Detail Id: " +invDtlId);
					return false;
				}	
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		LOGGER.info("Executed -> decreaseAvailableCredits()");
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

	/**
	 * @Author kartikm
	 * @Purpose:This method is called to retrieve all posted job 
	 * by Advanced search job id
	 * @Created:10sept, 2012
	 * @Param :advSearchId
	 * @Return :List<JobPostDTO>
	 * 
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobPostByADvSearch(int advSearchId) {

		List<JpJob> jobs = new ArrayList<JpJob>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT a from JpJob a where a.jobId='"
									+ advSearchId + "'");

			jobs = query.list();
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return jobPostConversionHelper.transformJpJobListToJobPostDTOList(jobs);

	}
	
	
	public boolean validateAvailableCredits(int invDtlId, int facilityId){
		
		LOGGER.info("Executing -> decreaseAvailableCredits()");		
		try {			
			//Based on inventory detail id, we are retreiving combo id 
			List<AdmInventoryDetail> invDtlList = hibernateTemplate.find("from AdmInventoryDetail dtl where dtl.invDetailId=?", invDtlId);			
			if(!invDtlList.isEmpty()){				
				AdmInventoryDetail invDtl = invDtlList.get(0);
				Object[] inputs = {invDtl.getProductId(), facilityId};
				List<AdmInventoryDetail> invDtls = hibernateTemplate.find(FIND_INVENTORY_DETAILS, inputs);				
				if(!invDtls.isEmpty()){					
					LOGGER.info("Having sufficient credits to post the job");
					return true;
				}else{
					LOGGER.info("Do not have sufficient credits to post the job for the given inventory Detail Id: " +invDtlId);
					return false;
				}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		LOGGER.info("Executed -> decreaseAvailableCredits()");
		return false;
	}
	

	/**
	 * @Author kartikm
	 * @Purpose:This method is called to save posted job by admin when admin
	 *               search some Advance job id and change some status by date
	 *               wise then this function is active.
	 * @Created:12sept, 2012
	 * @param apd
	 *            as list of End date
	 * @param jobId
	 *            jobId
	 * @return true
	 */
	public boolean jobSaveByAdmin(JobPostDTO apd, int jobId) {
		boolean isUpdate = false;
		try {
			JpJob mer = hibernateTemplate.get(JpJob.class, jobId);
			Date endDateValue = null;
			// Date startDateValue=null;
			Date todayDate = null;
			String enddateValue = apd.getEndDt() + " 00:00:00";
			// String startdateValue=apd.getStartDt()+" 00:00:00";

			endDateValue = dateConveter(enddateValue);
			// startDateValue=dateConveter(startdateValue);
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"MM/dd/yyyy HH:mm:ss");
			try {
				String dateNow = formatter.format(currentDate.getTime());
				todayDate = dateConveter(dateNow);
			} catch (Exception e) {
				LOGGER.info("Info data error date conversion");
			}

			int numberOfDays = twoDateDifferentValue(todayDate, endDateValue);
			// int
			// numberOfStartDate=twoDateDifferentValue(todayDate,startDateValue);
			if (numberOfDays >= 0) {
				mer.setJobStatus(MMJBCommonConstants.STATUS_ACTIVE);
				mer.setActive((byte) 1);
			} else if (numberOfDays < 0) {
				mer.setJobStatus(MMJBCommonConstants.STATUS_INACTIVE);
				mer.setActive((byte) 0);
			}
			mer.setEndDt(endDateValue);
			hibernateTemplate.update(mer);
			isUpdate = true;
			LOGGER.info("Job Status Save is done by Admin");
		} catch (DataAccessException e) {
			LOGGER.error("Job Status Save is not done by Admin");

		}
		return isUpdate;

	}

	/**
	 * @author kartikm 
	 * @Purpose:Date Conversion method name dateConveter that is for
	 *         Converting from MM/dd/yyyy HH:mm:ss to yyyy-MM-dd HH:mm:ss
	 * @Created:Sept 12, 2012
	 * @param date
	 * @return dateValue
	 */
	public Date dateConveter(String date) {
		Date dateValue = null;
		final String OLD_FORMAT = "MM/dd/yyyy HH:mm:ss";
		final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
		String newDateString;
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		try {
			Date d = sdf.parse(date);
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(d);
			SimpleDateFormat sdfSource = new SimpleDateFormat(NEW_FORMAT);
			dateValue = sdfSource.parse(newDateString);
		} catch (ParseException e) {
			LOGGER.info("Date parsing in Job save by admin wrong");
		}

		return dateValue;
	}

	/**
	 * @author kartikm 
	 * @Purpose: Two date Different method name is twoDateDifferentValue
	 *         that compare number of day start date and end date
	 * @Created:Sept 12, 2012        
	 * @param todayDate
	 * @param endDateValue
	 * @return numberOfDays
	 */
	public int twoDateDifferentValue(Date todayDate, Date endDateValue) {
		Calendar cal = Calendar.getInstance();
		long startDay = 0;
		long endDay = 0;
		int numberOfDays = 0;
		try {
			cal.setTime(todayDate);
			startDay = cal.getTimeInMillis() / MILLIS_IN_DAY;
			cal.setTime(endDateValue);
			endDay = cal.getTimeInMillis() / MILLIS_IN_DAY;
			// Two date different value calculate
			numberOfDays = (int) (endDay - startDay + 1);
		} catch (Exception e) {
			LOGGER.info("Info data error for date conversion");
		}
		return numberOfDays;
	}	
	
	/**
	 * 
	 * @param job
	 * @param jobsList
	 * @return
	 */
	private JobPostDTO validateJobPost(JpJob job, List<JobPostDTO> jobsList){
		for(JobPostDTO dto : jobsList){
			if(dto.getJobId() == job.getJobId()){
				return dto;
			}
		}
		return new JobPostDTO();		
	}

	@Override
	public int getinvDetIdByJobId(int jobId, int facilityId, int userId) {
		int invDetailId = 0;
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT a from AdmFacilityJpAudit a where a.id.facilityId="
									+ facilityId + " and a.id.userId = "
									+ userId + "and a.id.jobId=" + jobId);

			List<AdmFacilityJpAudit> admFacilityJpAuditList = (List<AdmFacilityJpAudit>) query
					.list();
			if (null != admFacilityJpAuditList
					&& admFacilityJpAuditList.size() > 0) {
				invDetailId = admFacilityJpAuditList.get(0).getId()
						.getInventoryDetailId();
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return invDetailId;
	}
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */
	
	public List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID) {

		List<FacilityDTO> admFacilityDTOList = new ArrayList<FacilityDTO>();
		try {
			List<AdmFacility> admFacilityList = hibernateTemplate
					.find(" from  AdmFacility WHERE  facilityId  = '" + admFacilityID + "'");

			if (admFacilityList != null) {
				for (AdmFacility admFacilityObj : admFacilityList) {
					FacilityDTO admFacilityDTO = new FacilityDTO();
					admFacilityDTO.setNsCustomerID(admFacilityObj.getNsCustomerID());
					admFacilityDTOList.add(admFacilityDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
		}
		return admFacilityDTOList;
	}
	
}
