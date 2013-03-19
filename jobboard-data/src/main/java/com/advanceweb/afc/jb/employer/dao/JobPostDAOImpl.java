/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAuditPK;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobAddon;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpJobTypeCombo;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpSource;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.data.entities.JpTypeAddonXref;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.helper.FacilityConversionHelper;
import com.advanceweb.afc.jb.employer.helper.JobPostConversionHelper;
import com.advanceweb.afc.jb.user.dao.UserDao;

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

	/** The Constant FIND_ADM_USER_FACILITY. */
	private static final String FIND_ADM_USER_FACILITY = "select facility from AdmUserFacility facility, AdmRole role where role.roleId=facility.id.roleId and facility.id.userId=? and role.name=?";
	
	/** The Constant FIND_EXPIRED_JOBS. */
	private static final String FIND_EXPIRED_JOBS = "from JpJob job where job.active='1' and job.autoRenew='0' and date_format(job.endDt, '%Y-%m-%d') = ?";
	
	/** The Constant FIND_EXPIRED_JOBS_FOR_RENEWAL. */
	private static final String FIND_EXPIRED_JOBS_FOR_RENEWAL = "from JpJob job where job.active='1'and job.autoRenew='1' and date_format(job.endDt, '%Y-%m-%d') = ?";
	
	/** The Constant FIND_SCHEDULED_JOBS. */
	private static final String FIND_SCHEDULED_JOBS = "from JpJob job where date_format(job.startDt, '%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') and job.active='0'";
	
	/** The Constant FIND_INVENTORY_DETAILS. */
	private static final String FIND_INVENTORY_DETAILS = "select dtl from AdmFacilityInventory inv inner join inv.admInventoryDetail dtl where dtl.availableqty != 0 "
			+ "and dtl.productId=? and inv.admFacility in(from AdmFacility fac where fac.facilityId=?) order by dtl.availableqty ";
	
	/** The Constant FIND_INVENTORY_DETAILS_OFFLINE. */
	private static final String FIND_INVENTORY_DETAILS_OFFLINE = "select dtl from AdmFacilityInventory inv inner join inv.admInventoryDetail dtl where dtl.availableqty != 0 and inv.orderId = 0 "
			+ "and dtl.productId=? and inv.admFacility in(from AdmFacility fac where fac.facilityId=?) order by dtl.availableqty ";
	
	/** The Constant FIND_INVENTORY_DETAILS_BY_INV_ID. */
	private static final String FIND_INVENTORY_DETAILS_BY_INV_ID = "from AdmInventoryDetail inv  where inv.invDetailId=?)";
	
	/** The Constant FIND_JP_JOB. */
	private static final String FIND_JP_JOB = "SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId=:userId and a.deleteDt is NULL ";
	
	/** The Constant FIND_EMP_JP_JOB. */
	private static final String FIND_EMP_JP_JOB = "SELECT a from JpJob a where a.admFacility.facilityId in (:facilityList) and a.deleteDt is NULL ";
	
	/** The Constant COUNT_EMP_JP_JOB. */
	private static final String COUNT_EMP_JP_JOB = "SELECT count(a) from JpJob a where a.admFacility.facilityId in (:facilityList) and a.deleteDt is NULL ";
	
	/** The Constant FIND_JP_JOBS. */
	private static final String FIND_JP_JOBS = "SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId=:userId and a.deleteDt is NULL ORDER BY a.jobId DESC ";
	
	
	
	
	/*private static final String FIND_ACTIVE_JOBS_EXPIRE_SOON = "select job.jobId,job.admFacility.facilityId,userFacility.facilityPK.userId," +
			"facility.name,job.endDt as expiteDate from JpJob job INNER JOIN AdmUserFacility userFacility INNER JOIN AdmFacility facility " +
			"where job.admFacility.facilityId=userFacility.admFacility.facilityId and job.admFacility.facilityId = facility.facilityId and job.active = 1 " +
			"and date_format(job.startDt, '%Y-%m-%d') <= CURRENT_DATE and date_format(job.endDt, '%Y-%m-%d') >= CURRENT_DATE " +
			"and (job.deleteDt is null) and DATEDIFF(date_format(job.endDt, '%Y-%m-%d'),CURRENT_DATE) <= 2";*/
	
	/** The Constant FIND_ACTIVE_JOBS_EXPIRE_SOON. */
	private static final String FIND_ACTIVE_JOBS_EXPIRE_SOON = "select job.job_id,job.facility_id,facility.user_id,fec.name,job.end_dt,job.create_user_id from jp_job job join adm_user_facility facility join adm_facility fec where job.facility_id=facility.facility_id and job.facility_id=fec.facility_id and job.active = 1 and date_format(job.start_dt, '%Y-%m-%d') <= CURRENT_DATE and date_format(job.end_dt, '%Y-%m-%d') >= CURRENT_DATE and (job.delete_dt is null) and DATEDIFF(date_format(job.end_dt, '%Y-%m-%d'),CURRENT_DATE) <= 3";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JobPostDAOImpl.class);
	
	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;
	
	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;
	
	/** The job post conversion helper. */
	@Autowired
	private JobPostConversionHelper<?> jobPostConversionHelper;
	
	/** The facility conversion helper. */
	@Autowired
	private FacilityConversionHelper facilityConversionHelper;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactoryMerionTracker the session factory merion tracker
	 * @param sessionFactory the session factory
	 */
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
	}

	/** The user dao. */
	@Autowired
	private UserDao userDAO;
	
	/** The facility dao. */
	@Autowired
	private FacilityDAO facilityDAO;
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
				employerInfoDTO.setCustomerNo(String.valueOf(facility
						.getNsCustomerID()));
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

			if (!(dto.getJobId() > 0)
					&& (MMJBCommonConstants.POST_NEW_JOB.equals(dto
							.getJobStatus())
							&& (!dto.isXmlStartEndDateEnabled()) && !validateAndDecreaseAvailableCredits(
								Integer.valueOf(dto.getJobPostingType()),
								dto.getMainFacilityId()))) {
				return false;
			}
			if ((dto.getJobId() > 0 && dto.isbActive())
					&& ((MMJBCommonConstants.POST_JOB_SCHEDULED.equals(dto
							.getJobStatus()) || (MMJBCommonConstants.POST_JOB_DRAFT
							.equals(dto.getJobStatus())))
							&& (!dto.isXmlStartEndDateEnabled()) && !validateAndDecreaseAvailableCredits(
								Integer.valueOf(dto.getJobPostingType()),
								dto.getMainFacilityId()))) {
				return false;
			}

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
			JpTemplate template = hibernateTemplate.get(JpTemplate.class,
					Integer.valueOf(dto.getBrandTemplate()));

			JpJobType jobType = getJobTypeDetails(dto);
			AdmFacility admFacility = hibernateTemplate.get(AdmFacility.class,
					Integer.valueOf(dto.getFacilityId()));
			JpJob job;
			if(dto.getJobId()>0){
             job = hibernateTemplate.get(JpJob.class, dto.getJobId());
             hibernateTemplate.deleteAll(hibernateTemplate.find("from JpJobLocation jp where jp.locationPK.jobId=?",dto.getJobId()));
			}else{
				job=new JpJob();
			}
			JpJob jpJob = jobPostConversionHelper.transformJobDtoToJpJob(job,dto,
					template, admFacility);
			jpJob.setJpJobType(jobType);

			if (dto.getSourceId() != MMJBCommonConstants.SOURCE_ID_ONE) {
				JpSource jpSource = hibernateTemplate.get(JpSource.class, 2);
				if (null != jpSource) {
					jpJob.setJpSource(jpSource);
				}
			}
			
			hibernateTemplate.saveOrUpdate(jpJob);
			
			if (dto.getSourceId() != MMJBCommonConstants.SOURCE_ID_ONE) {
				// Update the JpJobAddon entity
				List<AdmInventoryDetail> invList = hibernateTemplate.find(
						FIND_INVENTORY_DETAILS_BY_INV_ID,
						Integer.valueOf(dto.getJobPostingType()));
				if (null != invList && !invList.isEmpty()) {
					AdmInventoryDetail admInventoryDetail = invList.get(0);

					List<JpTypeAddonXref> xrefList = hibernateTemplate.find(
							"from JpTypeAddonXref xrf where xrf.comboId=?",
							admInventoryDetail.getProductId());
					List<JpJobAddon> jpJobAddonList = jobPostConversionHelper
							.transformXrefToJpJob(xrefList, jpJob, dto);
					hibernateTemplate.saveOrUpdateAll(jpJobAddonList);
				}

				AdmFacilityJpAudit audit = new AdmFacilityJpAudit();
				AdmFacilityJpAuditPK pKey = new AdmFacilityJpAuditPK();
				pKey.setFacilityId(dto.getFacilityId());
				pKey.setJobId(jpJob.getJobId());
				pKey.setUserId(dto.getUserId());
				pKey.setInventoryDetailId(Integer.valueOf(dto
						.getJobPostingType()));
				audit.setCreateDt(new Date());
				audit.setId(pKey);

				AdmFacilityJpAudit auditObj = (AdmFacilityJpAudit) DataAccessUtils
						.uniqueResult(hibernateTemplate
								.find("from AdmFacilityJpAudit admFacilityAudit where admFacilityAudit.id.facilityId = ? and admFacilityAudit.id.userId = ? and admFacilityAudit.id.jobId = ?",
										audit.getId().getFacilityId(), audit
												.getId().getUserId(), audit
												.getId().getJobId()));

				if (null != auditObj) {
					audit.setCreateDt(auditObj.getCreateDt());
					hibernateTemplate.delete(auditObj);
					hibernateTemplate.save(audit);
					LOGGER.debug("Job post updated successfully");
				} else {
					hibernateTemplate.save(audit);
				}
			}
			List<JpJobApply> applyJobList = jobPostConversionHelper
					.transformJobPostDTOToJpJobApply(dto, jpJob);
			hibernateTemplate.merge(applyJobList.get(0));
			if (null != location) {
				List<JpJobLocation> locList = jobPostConversionHelper
						.transformJobPostDTOToJpJbLocation(dto, jpJob, location);
				hibernateTemplate.merge(locList.get(0));
			}
			
		
			
		} catch (DataAccessException e) {
			LOGGER.error(e);
			return false;
		}

		return true;
	}

	/**
	 * @param dto
	 * @return
	 */
	private JpJobType getJobTypeDetails(JobPostDTO dto) {
		List<AdmInventoryDetail> invList = hibernateTemplate.find(
				FIND_INVENTORY_DETAILS_BY_INV_ID,
				Integer.valueOf(dto.getJobPostingType()));
		JpJobType jobType = new JpJobType();
		if (!invList.isEmpty()) {
			AdmInventoryDetail admInventoryDetail = invList.get(0);
			if (MMJBCommonConstants.JOB_TYPE_COMBO.equals(admInventoryDetail
					.getProductType())) {
				List<JpJobTypeCombo> comboList = hibernateTemplate.find(
						"from JpJobTypeCombo combo where combo.comboId=?",
						admInventoryDetail.getProductId());
				if (!comboList.isEmpty()) {
					JpJobTypeCombo combo = comboList.get(0);
					if (null != combo.getJobType()
							&& combo.getJobType().equalsIgnoreCase(
									MMJBCommonConstants.STANDARD_JOB_POSTING)) {
						jobType = hibernateTemplate.load(JpJobType.class,
								MMJBCommonConstants.JOB_POST_TYPE_POSTING_ID);
					} else if (null != combo.getJobType()
							&& combo.getJobType().equalsIgnoreCase(
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

		} else if (dto.getSourceId() == MMJBCommonConstants.SOURCE_ID_ONE) {

			jobType = hibernateTemplate.get(JpJobType.class,
					MMJBCommonConstants.SOURCE_ID_ONE);
		}
		return jobType;
	}

	/**
	 * @Author :devi mishra
	 * @Purpose:Method to retrieve all job Posted by the employer and sub facilities
	 * @Created:Aug 29, 2012
	 * @Param :companyList
	 * @Param :noOfRecords
	 * @Param :sortBy
	 * @Param :mainFacilityId
	 * @Return :List<JobPostDTO>
	 * 
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobPost(List<DropDownDTO> companyList,
			int offset, int noOfRecords, String sortBy) {

		List<JpJob> jobs = new ArrayList<JpJob>();
		List<JobPostDTO> listJobPostDto = new ArrayList<JobPostDTO>();
		List<JobPostDTO> modListJobPostDto = new ArrayList<JobPostDTO>();
		try {
			Query query = hibernateTemplate.getSessionFactory()
					.getCurrentSession().createQuery(FIND_EMP_JP_JOB + sortBy);
			// get the facility id list 
			List<Integer> facilityList = new ArrayList<Integer>();
			for (DropDownDTO facility : companyList) {
				facilityList.add(Integer.parseInt(facility.getOptionId()));
			}			
			query.setParameterList("facilityList", facilityList);
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			jobs = query.list();
			listJobPostDto = jobPostConversionHelper
					.transformJpJobListToJobPostDTOList(jobs);

			for (JobPostDTO jobPostDto : listJobPostDto) {
				List<Long> viewsList = hibernateTemplateTracker
						.find("select sum(resultCount) as views from VstSearchResultNew srn where srn.searchResultNewPK.result = ?",
								jobPostDto.getJobId());
				if (null != viewsList && !viewsList.isEmpty() && !viewsList.contains(null)) {
					jobPostDto.setViews(viewsList.get(0).longValue());
				}
				List<Long> clicksList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (1,2,3,6,9,10)",
								jobPostDto.getJobId());
				if (null != clicksList && !clicksList.isEmpty() && !clicksList.contains(null)) {
					jobPostDto.setClicks(clicksList.get(0).longValue());
				}
				List<Long> appliesList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (4,5,7,8)",
								jobPostDto.getJobId());
				if (null != appliesList && !appliesList.isEmpty() && !appliesList.contains(null)) {
					jobPostDto.setApplies(appliesList.get(0).longValue());
				}
				modListJobPostDto.add(jobPostDto);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage(), e);
			return modListJobPostDto;
		}
		return modListJobPostDto;

	}

	/**
	 * @Author :devi mishra
	 * @Purpose:This method is called when user click the edit job to retrieve
	 *               the detail as per the selected job id
	 * @Created:Aug 29, 2012
	 * @Param :jobid
	 * @Return :JobPostDTO
	 * 
	 */
	@Override
	public JobPostDTO retrieveJobById(int jobId) {
		JobPostDTO dto = new JobPostDTO();
		try {
			JpJob job = hibernateTemplate.get(JpJob.class, jobId);

			if (job != null) {

				dto = jobPostConversionHelper.transformJpJobToJobPostDTO(job);
				int nsCustomerID = 0;
				List<FacilityDTO> admFacilityDTOList = getNSCustomerIDFromAdmFacility(Integer
						.valueOf(dto.getFacilityId()));
				nsCustomerID = admFacilityDTOList.get(0).getNsCustomerID();
				dto.setCustomerNo(String.valueOf(nsCustomerID));
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return dto;
	}

	/**
	 * This method is called fetch all the job type & respective addons
	 * 
	 * @param
	 * @return JobPostingPlanDTO list
	 */
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
				Date startDt = job.getStartDt();
				Date endtDt = job.getEndDt();
				Date currentDt = new Date();

				if ((job.getActive() == 1 && endtDt.before(currentDt))
						|| (job.getActive() == MMJBCommonConstants.INACTIVE && (startDt
								.before(currentDt) || startDt.equals(currentDt)))) {
					// System deletes the job postings which are in "inactive"
					// or "Expired" status
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
	public boolean updateManageJob(boolean autoRenew, int brandTemplate,
			int jobId, int userId) {
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
		try {
			// System deletes the job postings which are in "Expired" status
			job.setUpdateDt(new Timestamp(new Date().getTime()));
			job.setAutoRenew(autoRenew ? 1 : 0);
			JpTemplate template = null;
			if (brandTemplate > 0) {
				template = hibernateTemplate.load(JpTemplate.class,
						brandTemplate);
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
				// "Active"
				// status
				Date startDt = job.getStartDt();
				Date endtDt = job.getEndDt();
				Date currentDt = new Date();

				if ((startDt.before(currentDt) || startDt.equals(currentDt)) && endtDt.after(currentDt)) {
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
	public boolean repostJob(int jobId, int extendDays) {
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
		Date startDt = job.getStartDt();
		Date endtDt = job.getEndDt();
		Date currentDt = new Date();
		boolean bRepost = false;
		try {
						
			if (null != startDt
					&& (job.getActive() == MMJBCommonConstants.INACTIVE
							&& (startDt.before(currentDt)) || (startDt
								.equals(currentDt)))) {
				job.setActive(MMJBCommonConstants.ACTIVE);
				bRepost=true;
				// if User has reposted a
				// inactive job no need to deduct any credit and the start date
				// and end date will remain same
			} else if (job.getActive() == MMJBCommonConstants.ACTIVE
					&& endtDt.before(currentDt)) {
				job.setActive(MMJBCommonConstants.ACTIVE);
				// if User has reposted a
				// Expired Job deduct the credit and change the start date to
				// current date
				// and end date to current date + 30days
				Integer facilityId=getParentFacility(job
						.getAdmFacility().getFacilityId()).getFacilityId();
				
				List<AdmFacilityJpAudit>jpAudList=hibernateTemplate.find("from AdmFacilityJpAudit jpAudit where id.jobId=?",job.getJobId());
			/*	if (!validateAndDecreaseAvailableCredits(Integer.valueOf(job
						.getJpJobType().getJobTypeId()), job.getAdmFacility()
						.getFacilityId())) {*/
				if (jpAudList!=null &&!validateAndDecreaseAvailableCredits(jpAudList.get(0).getId().getInventoryDetailId(), facilityId)) {
					return false;
				}
				job.setStartDt(Calendar.getInstance().getTime());
				job.setEndDt(calculateEndDt(job.getStartDt(), extendDays));
				bRepost=true;
			}
			hibernateTemplate.save(job);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return bRepost;
	}

	/**
	 * @Author :devi mishra
	 * @Purpose:This method is called to retrieve all posted job by Status
	 * @Created:Aug 29, 2012
	 * @Param :jobStatus
	 * @Param :companyList
	 * @Param :offset
	 * @Param :noOfRecords
	 * @Return :List<JobPostDTO>
	 * 
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobByStatus(String jobStatus,
			List<DropDownDTO> companyList, int offset, int noOfRecords) {
		List<JpJob> jobs = new ArrayList<JpJob>();
		List<JobPostDTO> listJobPostDto = new ArrayList<JobPostDTO>();
		List<JobPostDTO> modListJobPostDto = new ArrayList<JobPostDTO>();
		Query query = null;

		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		try {
			if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_NEW_JOB)) {
				// Need to check the end date condition once the Package
				// and plan functionality finalized
				query = session
						.createQuery(FIND_EMP_JP_JOB
								+ " and a.active = 1 and (DATE_FORMAT(a.startDt, '%Y-%m-%d') <= CURRENT_DATE and DATE_FORMAT(a.endDt, '%Y-%m-%d') >= CURRENT_DATE)");

			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_INACTIVE)) {
				query = session
						.createQuery(FIND_EMP_JP_JOB
								+ " and (a.active = 0 and DATE_FORMAT(a.startDt, '%Y-%m-%d') <= CURRENT_DATE)");

			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_DRAFT)) {
				query = session
						.createQuery(FIND_EMP_JP_JOB
								+ " and (a.active = 0 and a.startDt is NULL and a.endDt is NULL)");

			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_EXPIRED)) {
				query = session
						.createQuery(FIND_EMP_JP_JOB
								+ " and (a.active = 1 and DATE_FORMAT(a.endDt, '%Y-%m-%d') < CURRENT_DATE)");

			} else if (null != jobStatus
					&& jobStatus
							.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_SCHEDULED)) {
				query = session
						.createQuery(FIND_EMP_JP_JOB
								+ " and (a.active = 0 and DATE_FORMAT(a.startDt, '%Y-%m-%d') > CURRENT_DATE)");

			}
			// get the facility id list 
			List<Integer> facilityList = new ArrayList<Integer>();
			for (DropDownDTO facility : companyList) {
				facilityList.add(Integer.parseInt(facility.getOptionId()));
			}			
			query.setParameterList("facilityList", facilityList);
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			jobs = query.list();
			listJobPostDto = jobPostConversionHelper
					.transformJpJobListToJobPostDTOList(jobs);

			for (JobPostDTO jobPostDto : listJobPostDto) {
				List<Long> viewsList = hibernateTemplateTracker
						.find("select sum(resultCount) as views from VstSearchResultNew srn where srn.searchResultNewPK.result = ?",
								jobPostDto.getJobId());
				if (null != viewsList && !viewsList.isEmpty() && !viewsList.contains(null)) {
					jobPostDto.setViews(viewsList.get(0).longValue());
				}
				List<Long> clicksList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (1,2,3,6,9,10)",
								jobPostDto.getJobId());
				if (null != clicksList && !clicksList.isEmpty() && !clicksList.contains(null)) {
					jobPostDto.setClicks(clicksList.get(0).longValue());
				}
				List<Long> appliesList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (4,5,7,8)",
								jobPostDto.getJobId());
				if (null != appliesList && !appliesList.isEmpty() && !appliesList.contains(null)) {
					jobPostDto.setApplies(appliesList.get(0).longValue());
				}
				modListJobPostDto.add(jobPostDto);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
			return modListJobPostDto;
		}
		return modListJobPostDto;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getEmpJobsCount(java.util.List)
	 */
	@Override
	public int getEmpJobsCount(List<DropDownDTO> companyList) {
		int jobsCount = 0;
		try {
			Query query = hibernateTemplate.getSessionFactory()
					.getCurrentSession().createQuery(COUNT_EMP_JP_JOB);
			// get the facility id list
			List<Integer> facilityList = new ArrayList<Integer>();
			for (DropDownDTO facility : companyList) {
				facilityList.add(Integer.parseInt(facility.getOptionId()));
			}
			query.setParameterList("facilityList", facilityList);
			jobsCount = ((Long) query.uniqueResult()).intValue();
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return jobsCount;
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getEmpJobsCountByStatus(java.lang.String, java.util.List)
	 */
	@Override
	public int getEmpJobsCountByStatus(String jobStatus,
			List<DropDownDTO> companyList){
	Query query = null;
	int jobsCount = 0 ;
	Session session = hibernateTemplate.getSessionFactory()
			.getCurrentSession();
	try {
		if (null != jobStatus
				&& jobStatus
						.equalsIgnoreCase(MMJBCommonConstants.POST_NEW_JOB)) {
			// Need to check the end date condition once the Package
			// and plan functionality finalized
			query = session
					.createQuery(COUNT_EMP_JP_JOB
							+ " and a.active = 1 and (DATE_FORMAT(a.startDt, '%Y-%m-%d') <= CURRENT_DATE and DATE_FORMAT(a.endDt, '%Y-%m-%d') >= CURRENT_DATE)");

		} else if (null != jobStatus
				&& jobStatus
						.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_INACTIVE)) {
			query = session
					.createQuery(COUNT_EMP_JP_JOB
							+ " and (a.active = 0 and DATE_FORMAT(a.startDt, '%Y-%m-%d') <= CURRENT_DATE)");

		} else if (null != jobStatus
				&& jobStatus
						.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_DRAFT)) {
			query = session
					.createQuery(COUNT_EMP_JP_JOB
							+ " and (a.active = 0 and a.startDt is NULL and a.endDt is NULL)");

		} else if (null != jobStatus
				&& jobStatus
						.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_EXPIRED)) {
			query = session
					.createQuery(COUNT_EMP_JP_JOB
							+ " and (a.active = 1 and DATE_FORMAT(a.endDt, '%Y-%m-%d') < CURRENT_DATE)");

		} else if (null != jobStatus
				&& jobStatus
						.equalsIgnoreCase(MMJBCommonConstants.POST_JOB_SCHEDULED)) {
			query = session
					.createQuery(COUNT_EMP_JP_JOB
							+ " and (a.active = 0 and DATE_FORMAT(a.startDt, '%Y-%m-%d') > CURRENT_DATE)");

		}
		// get the facility id list 
		List<Integer> facilityList = new ArrayList<Integer>();
		for (DropDownDTO facility : companyList) {
			facilityList.add(Integer.parseInt(facility.getOptionId()));
		}			
		query.setParameterList("facilityList", facilityList);
		jobsCount = ((Long) query.uniqueResult()).intValue();
	} catch (DataAccessException e) {
		LOGGER.error(e.getMessage(), e);
	}
		return jobsCount;
	}

	/**
	 * This method is called to retreive all the scheduled jobs
	 */
	public List<JobPostDTO> retreiveAllScheduledJobs() {
		List<JobPostDTO> listJobPostDto = new ArrayList<JobPostDTO>();
		List<JobPostDTO> modListJobPostDto = new ArrayList<JobPostDTO>();
		// Schedule Jobs
		try {
			List<JpJob> scheduledJobs = hibernateTemplate
					.find(FIND_SCHEDULED_JOBS);
			listJobPostDto = jobPostConversionHelper
					.transformJpJobListToJobPostDTOList(scheduledJobs);

			for (JobPostDTO jobPostDto : listJobPostDto) {
				List<Long> viewsList = hibernateTemplateTracker
						.find("select sum(resultCount) as views from VstSearchResultNew srn where srn.searchResultNewPK.result = ?",
								jobPostDto.getJobId());
				if (null != viewsList && !viewsList.isEmpty() && !viewsList.contains(null)) {
					jobPostDto.setViews(viewsList.get(0).longValue());
				}
				List<Long> clicksList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (1,2,3,6,9,10)",
								jobPostDto.getJobId());
				if (null != clicksList && !clicksList.isEmpty() && !clicksList.contains(null)) {
					jobPostDto.setClicks(clicksList.get(0).longValue());
				}
				List<Long> appliesList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (4,5,7,8)",
								jobPostDto.getJobId());
				if (null != appliesList && !appliesList.isEmpty() && !appliesList.contains(null)) {
					jobPostDto.setApplies(appliesList.get(0).longValue());
				}
				modListJobPostDto.add(jobPostDto);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
			return modListJobPostDto;
		}
		return modListJobPostDto;
	}

	/**
	 * This method is called to retreive all the expired jobs
	 */
	public List<JobPostDTO> retreiveAllExpiredJobs() {
		List<JobPostDTO> listJobPostDto = new ArrayList<JobPostDTO>();
		List<JobPostDTO> modListJobPostDto = new ArrayList<JobPostDTO>();
		// Schedule Jobs
		try {
			List<JpJob> scheduledJobs = hibernateTemplate.find(
					FIND_EXPIRED_JOBS_FOR_RENEWAL, getOneDayBeforeDate());
			
			listJobPostDto = jobPostConversionHelper
					.transformJpJobListToJobPostDTOList(scheduledJobs);

			for (JobPostDTO jobPostDto : listJobPostDto) {
				List<Long> viewsList = hibernateTemplateTracker
						.find("select sum(resultCount) as views from VstSearchResultNew srn where srn.searchResultNewPK.result = ?",
								jobPostDto.getJobId());
				if (null != viewsList && !viewsList.isEmpty() && !viewsList.contains(null)) {
					jobPostDto.setViews(viewsList.get(0).longValue());
				}
				List<Long> clicksList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (1,2,3,6,9,10)",
								jobPostDto.getJobId());
				if (null != clicksList && !clicksList.isEmpty() && !clicksList.contains(null)) {
					jobPostDto.setClicks(clicksList.get(0).longValue());
				}
				List<Long> appliesList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (4,5,7,8)",
								jobPostDto.getJobId());
				if (null != appliesList && !appliesList.isEmpty() && !appliesList.contains(null)) {
					jobPostDto.setApplies(appliesList.get(0).longValue());
				}
				modListJobPostDto.add(jobPostDto);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
			return modListJobPostDto;
		}
		return modListJobPostDto;
	}
	
	/**
	 * This method is called to retreive all the active jobs which expire in 2 days
	 */
	public List<SchedulerDTO> retreiveActiveJobsExpireSoon() {
		//list the  Active Jobs Expiring Soon
		List<SchedulerDTO> schedulerDTOList = new ArrayList<SchedulerDTO>();
		
		SchedulerDTO schedulerDTO = null;
		try {
			Query query = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(FIND_ACTIVE_JOBS_EXPIRE_SOON);
			List<Object[]> jobs = query.list();
			for( Object[] obj : jobs){
				
				schedulerDTO = new SchedulerDTO();
				schedulerDTO.setJobId(Integer.parseInt(String.valueOf(obj[0])));
				schedulerDTO.setFacilityId(Integer.parseInt(String.valueOf(obj[1])));
				schedulerDTO.setUserId(Integer.parseInt(String.valueOf(obj[2])));
				schedulerDTO.setCompanyName(String.valueOf(obj[3]));
				schedulerDTO.setExpireDate(String.valueOf(obj[4]));
				schedulerDTO.setCreateUserId(Integer.parseInt(String.valueOf(obj[5])));
				
				//get the user details from the mer_user table using the user id with which job has been posted
				UserDTO userDTO = userDAO.getUserByUserId(schedulerDTO.getUserId());
				
				schedulerDTO.setFirstName(userDTO.getFirstName());
				schedulerDTO.setLastName(userDTO.getLastName());
				schedulerDTO.setEmailId(userDTO.getEmailId());
				schedulerDTOList.add(schedulerDTO);
			}
			
			
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return schedulerDTOList;
	}
	
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#executeExpireJobs()
	 */
	@Override
	public List<SchedulerDTO> executeExpireJobs() {
		LOGGER.info("Executing -> executeActiveJobWorker()");
		
		List<SchedulerDTO> schedulerDTOList = new ArrayList<SchedulerDTO>();
		
		// Update Jobs as expired
		try {
			// Identify jobs eligible for expire
			List<JpJob> expiredJobs = hibernateTemplate.find(FIND_EXPIRED_JOBS,
					getOneDayBeforeDate());

			for (JpJob job : expiredJobs) {
				try {
					//job.setActive((byte) 0);
					//hibernateTemplate.saveOrUpdate(job);
					//get the user details to send the mail
					getUserDetails(schedulerDTOList, job);
					
				} catch (Exception e) {
					LOGGER.error("Failed to mark job as expired for job Id  "
							+ job.getJobId());
					LOGGER.error(e);
				}
				LOGGER.info("ActiveJobsJobWorker-> Marked Job as expired successfully....."+ job.getJobId());
			}

		} catch (DataAccessException e) {
			LOGGER.error("Failed to retreive expired jobs  ");
			LOGGER.error(e);
		}
		return schedulerDTOList;
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#executeActiveJobWorker(java.util.List)
	 */
	@Override
	public List<SchedulerDTO> executeActiveJobWorker(List<JobPostDTO> jobsList) {
		LOGGER.info("Executing -> executeActiveJobWorker()");
		
		List<SchedulerDTO> schedulerDTOList = new ArrayList<SchedulerDTO>();
		
		// Schedule Jobs
		try {
			
			// Identify the scheduled jobs
			List<JpJob> scheduledJobs = hibernateTemplate
					.find(FIND_SCHEDULED_JOBS);

			for (JpJob job : scheduledJobs) {

				// Validating with net suite data to check whether the employer
				// is featured or not
				// And to know, whether the employer is applicable for free job
				// posting
				JobPostDTO dto = validateJobPost(job, jobsList);

				// Retreiving job posting type based on the job id
				List<AdmFacilityJpAudit> jpAuditList = hibernateTemplate.find(
						"from AdmFacilityJpAudit audit where audit.id.jobId=?",
						job.getJobId());

				if (!jpAuditList.isEmpty()) {
					AdmFacilityJpAudit audit = jpAuditList.get(0);

					// Checking for available credits
					Integer facilityId=getParentFacility(job
							.getAdmFacility().getFacilityId()).getFacilityId();
					
					if (!dto.isXmlStartEndDateEnabled()
							&& !validateAndDecreaseAvailableCredits(audit
									.getId().getInventoryDetailId(), facilityId)) {
						LOGGER.error(job.getName()
								+ " Doesn't have sufficient credits to post the job "
								+ job.getJobId());
					} else {
						try {
							job.setFeatured((byte) (dto.isbFeatured() ? 1 : 0));
							job.setStartDt(new Date());
							job.setEndDt(addDaysToCurrentDate());
							job.setActive((byte) 1);
							hibernateTemplate.saveOrUpdate(job);
						} catch (Exception e) {
							LOGGER.error("Failed to renew the job as Active "+ job.getJobId());
							//get the user details to send the mail
							getUserDetails(schedulerDTOList, job);
							LOGGER.error(e);
						}
						LOGGER.info("ActiveJobsJobWorker-> Renewal of job is done successfully....."
								+ job.getJobId());
					}

				} else {
					LOGGER.info("There is no job type with the given Job Id: "
							+ job.getJobId());
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		LOGGER.info("Executed -> executeActiveJobWorker()");
		return schedulerDTOList;
	}

	/**
	 * This method will get the user details to send the mail.
	 * @param schedulerDTOList
	 * @param job
	 */
	private void getUserDetails(List<SchedulerDTO> schedulerDTOList, JpJob job) {
		SchedulerDTO schedulerDTO;
		UserDTO userDTO;
		userDTO = userDAO.getUserByUserId(facilityDAO.getfacilityUserId(job
				.getAdmFacility().getFacilityId()));
		if (null != userDTO) {
			schedulerDTO = new SchedulerDTO();
			schedulerDTO.setUserId(userDTO.getUserId());
			schedulerDTO.setCreateUserId(job.getCreateUserId());
			schedulerDTO.setJobId(job.getJobId());
			schedulerDTO.setFirstName(userDTO.getFirstName());
			schedulerDTO.setLastName(userDTO.getLastName());
			schedulerDTO.setEmailId(userDTO.getEmailId());
			schedulerDTO.setCompanyName(job.getAdmFacility().getName());
			schedulerDTO.setExpireDate(String.valueOf(job.getEndDt()));
			schedulerDTO.setFacilityId(job
					.getAdmFacility().getFacilityId());
			schedulerDTOList.add(schedulerDTO);
		}
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#executeAutoRenewalJobWorker(java.util.List)
	 */
	@Override
	public List<SchedulerDTO> executeAutoRenewalJobWorker(List<JobPostDTO> jobsList) {
		LOGGER.info("Executing -> executeAutoRenewalJobWorker()");
		List<SchedulerDTO> schedulerDTOList = new ArrayList<SchedulerDTO>();
		// Schedule Jobs
		try {

			List<JpJob> autoRenewJobs = hibernateTemplate.find(
					FIND_EXPIRED_JOBS_FOR_RENEWAL, getOneDayBeforeDate());

			for (JpJob job : autoRenewJobs) {

				List<AdmFacilityJpAudit> jpAuditList = hibernateTemplate.find(
						"from AdmFacilityJpAudit audit where audit.id.jobId=?",
						job.getJobId());

				JobPostDTO dto = validateJobPost(job, jobsList);

				if (!jpAuditList.isEmpty()) {
					AdmFacilityJpAudit audit = jpAuditList.get(0);
					// Checking for available credits
					Integer facilityId=getParentFacility(job
								.getAdmFacility().getFacilityId()).getFacilityId();
				
					if (!dto.isXmlStartEndDateEnabled()
							&& !validateAndDecreaseAvailableCredits(audit
									.getId().getInventoryDetailId(), facilityId)) {
						LOGGER.error(job.getName()
								+ " Doesn't have sufficient credits to post the job "
								+ job.getJobId());
						getUserDetails(schedulerDTOList, job);
					} else {
						try {
							job.setFeatured((byte) (dto.isbFeatured() ? 1 : 0));
							job.setStartDt(new Date());
							job.setEndDt(addDaysToCurrentDate());
							job.setActive((byte) 1);
							hibernateTemplate.saveOrUpdate(job);
						} catch (Exception e) {
							LOGGER.error("Failed to renew the job as Active "+ job.getJobId());
							getUserDetails(schedulerDTOList, job);
							LOGGER.error(e);
						}
						LOGGER.info("ActiveJobsJobWorker-> Renewal of job is done successfully....."
								+ job.getJobId());
					}

				} else {
					LOGGER.info("There is no job type with the given Job Id: "
							+ job.getJobId());
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		LOGGER.info("Executed -> executeActiveJobWorker()");
		return schedulerDTOList;
	}

	/**
	 * This method is called to validate available credits against the job id if
	 * the credits are not available it will return false otherwise it will post
	 * the job/renewal the job and will decrease the credits.
	 * 
	 * @param invDtlId
	 * @param facilityId
	 * @return boolean
	 */
	public boolean validateAndDecreaseAvailableCredits(int invDtlId,
			int facilityId) {

		LOGGER.debug("Executing -> validateAndDecreaseAvailableCredits()");
		// Schedule Jobs
		try {
			// Based on inventory detail id, we are retreiving product id
			// For example if the product id is having multiple purchases, we
			// are going to dedut form old purchases
			List<AdmInventoryDetail> invDtlList = hibernateTemplate.find(
					"from AdmInventoryDetail dtl where dtl.invDetailId=?",
					invDtlId);
			if (!invDtlList.isEmpty()) {
				AdmInventoryDetail invDtl = invDtlList.get(0);
				Object[] inputs = { invDtl.getProductId(), facilityId };
			/*	Object[] inputs = { 11, 4544 };*/
				
				//check if the job type purchased is offline. If so then use order id = 0
				List<AdmInventoryDetail> invDtls = null; 
						
				invDtls = hibernateTemplate.find(FIND_INVENTORY_DETAILS_OFFLINE, inputs);
				
				if(invDtls.isEmpty()){
					invDtls = hibernateTemplate.find(FIND_INVENTORY_DETAILS, inputs);
				}
				
				if (!invDtls.isEmpty()) {
					try {
						// Deducting available quantity
						AdmInventoryDetail dtl = invDtls.get(0);
						dtl.setAvailableqty(dtl.getAvailableqty() - 1);
						hibernateTemplate.saveOrUpdate(dtl);
					} catch (Exception e) {
						LOGGER.error(" Exception has been occured while the posting the job for Inventory Detail Id: "
								+ invDtlId);

					}
					return true;
				} else {
					LOGGER.info("There is no job type with the given inventory Detail Id: "
							+ invDtlId);
					return false;
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		LOGGER.debug("Executed -> validateAndDecreaseAvailableCredits()");
		return false;
	}

	/**
	 * Gets the one day before date.
	 *
	 * @return the one day before date
	 */
	private String getOneDayBeforeDate() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.US);
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * Adds the days to current date.
	 *
	 * @return the date
	 */
	private Date addDaysToCurrentDate() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, MMJBCommonConstants.AUTO_RENEWAL_DAYS);
		return cal.getTime();
	}

	/**
	 * @Author kartikm
	 * @Purpose:This method is called to retrieve all posted job by Advanced
	 *               search job id
	 * @Created:10sept, 2012
	 * @Param :advSearchId
	 * @Return :List<JobPostDTO>
	 * 
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobPostByADvSearch(int advSearchId) {

		List<JpJob> jobs = new ArrayList<JpJob>();
		List<JobPostDTO> listJobPostDto = new ArrayList<JobPostDTO>();
		List<JobPostDTO> modListJobPostDto = new ArrayList<JobPostDTO>();
		try {
			jobs=hibernateTemplate.find("from JpJob a where a.jobId=?",advSearchId);
			
			listJobPostDto = jobPostConversionHelper
					.transformJpJobListToJobPostDTOList(jobs);

			for (JobPostDTO jobPostDto : listJobPostDto) {
				List<Long> viewsList = hibernateTemplateTracker
						.find("select sum(resultCount) as views from VstSearchResultNew srn where srn.searchResultNewPK.result = ?",
								jobPostDto.getJobId());
				if (null != viewsList && !viewsList.isEmpty() && !viewsList.contains(null)) {
					jobPostDto.setViews(viewsList.get(0).longValue());
				}
				List<Long> clicksList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (1,2,3,6,9,10)",
								jobPostDto.getJobId());
				if (null != clicksList && !clicksList.isEmpty() && !clicksList.contains(null)) {
					jobPostDto.setClicks(clicksList.get(0).longValue());
				}
				List<Long> appliesList = hibernateTemplateTracker
						.find("select sum(clickCount) as clicks from VstClickthroughNew ctn where ctn.clickthroughNewPK.keyId=? " +
								"and ctn.clickthroughNewPK.vstClickthroughType.clickthroughTypeId in (4,5,7,8)",
								jobPostDto.getJobId());
				if (null != appliesList && !appliesList.isEmpty() && !appliesList.contains(null)) {
					jobPostDto.setApplies(appliesList.get(0).longValue());
				}
				modListJobPostDto.add(jobPostDto);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
			return modListJobPostDto;
		}
/*if(jobs.get(0).getEndDt()){}*/
		return modListJobPostDto;

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#validateAvailableCredits(int, int)
	 */
	public boolean validateAvailableCredits(int invDtlId, int facilityId) {

		LOGGER.debug("Executing -> decreaseAvailableCredits()");
		try {
			// Based on inventory detail id, we are retreiving combo id
			List<AdmInventoryDetail> invDtlList = hibernateTemplate.find(
					"from AdmInventoryDetail dtl where dtl.invDetailId=?",
					invDtlId);
			if (!invDtlList.isEmpty()) {
				AdmInventoryDetail invDtl = invDtlList.get(0);
				Object[] inputs = { invDtl.getProductId(), facilityId };
				List<AdmInventoryDetail> invDtls = hibernateTemplate.find(
						FIND_INVENTORY_DETAILS, inputs);
				if (!invDtls.isEmpty()) {
					LOGGER.debug("Having sufficient credits to post the job");
					return true;
				} else {
					LOGGER.debug("Do not have sufficient credits to post the job for the given inventory Detail Id: "
							+ invDtlId);
					return false;
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		LOGGER.debug("Executed -> decreaseAvailableCredits()");
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
			
			Date endDate = CommonUtil.convertToDate(apd.getEndDt());
			
			Calendar currentDate = Calendar.getInstance();
			Calendar endDt = Calendar.getInstance();
			//endDt.setTime(apd.getEndDt());
			endDt.setTime(endDate);
			endDt.set(Calendar.HOUR_OF_DAY, 23);
			endDt.set(Calendar.MINUTE, 59);
			endDt.set(Calendar.SECOND, 59);
			if (endDt.getTime().after(currentDate.getTime()) || endDt.getTime().equals(currentDate.getTime())) {
				// mer.setJobStatus(MMJBCommonConstants.STATUS_ACTIVE);
				mer.setActive((byte) 1);
			} else {
				// mer.setJobStatus(MMJBCommonConstants.STATUS_EXPIRED);
			}
			mer.setEndDt(endDt.getTime());
			hibernateTemplate.update(mer);
			isUpdate = true;
			LOGGER.debug("Job Status Save is done by Admin");
		} catch (DataAccessException e) {
			LOGGER.error("Job Status Save is not done by Admin",e);

		}
		return isUpdate;

	}

	/**
	 * 
	 * @param job
	 * @param jobsList
	 * @return
	 */
	private JobPostDTO validateJobPost(JpJob job, List<JobPostDTO> jobsList) {
		for (JobPostDTO dto : jobsList) {
			if (dto.getJobId() == job.getJobId()) {
				return dto;
			}
		}
		return new JobPostDTO();
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getinvDetIdByJobId(int, int, int)
	 */
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
					&& !admFacilityJpAuditList.isEmpty()) {
				invDetailId = admFacilityJpAuditList.get(0).getId()
						.getInventoryDetailId();
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return invDetailId;
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getinvDtlByJobId(int)
	 */
	@Override
	public AdmFacilityJpAudit getinvDtlByJobId(int jobId) {
		AdmFacilityJpAudit invDetail = new AdmFacilityJpAudit();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT a from AdmFacilityJpAudit a where a.id.jobId=" + jobId);

			List<AdmFacilityJpAudit> admFacilityJpAuditList = (List<AdmFacilityJpAudit>) query
					.list();
			if (null != admFacilityJpAuditList
					&& !admFacilityJpAuditList.isEmpty()) {
				invDetail = admFacilityJpAuditList.get(0);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return invDetail;
	}

	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */

	public List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID) {

		List<FacilityDTO> admFacilityDTOList = new ArrayList<FacilityDTO>();
		try {
			List<AdmFacility> admFacilityList = hibernateTemplate
					.find(" from  AdmFacility WHERE  facilityId  = '"
							+ admFacilityID + "'");

			if (admFacilityList != null) {
				for (AdmFacility admFacilityObj : admFacilityList) {
					FacilityDTO admFacilityDTO = new FacilityDTO();
					admFacilityDTO.setNsCustomerID(admFacilityObj
							.getNsCustomerID());
					admFacilityDTOList.add(admFacilityDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
		}
		return admFacilityDTOList;
	}

	/**
	 * This method extends the current end date by the number of extendDays
	 * passed
	 * 
	 * @param currentEndDt
	 * @param extendDays
	 * @return Date
	 */
	private Date calculateEndDt(Date currentEndDt, int extendDays) {
		Calendar now = Calendar.getInstance(); 
		now.setTime(currentEndDt);
		now.add(Calendar.DAY_OF_MONTH, extendDays);
		return now.getTime();
	}

/* (non-Javadoc)
 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#checkDraftAndSchedule(int)
 */
public boolean checkDraftAndSchedule(int avdSearchId){
	boolean result=false;
	List<JpJob>scheduleJobs=hibernateTemplate.find("from JpJob a where a.jobId=? and a.active = 0 and DATE_FORMAT(a.startDt, '%Y-%m-%d') > CURRENT_DATE",avdSearchId);
	List<JpJob>draftJobs=hibernateTemplate.find("from JpJob a where a.jobId=? and a.active = 0 and a.startDt is NULL and a.endDt is NULL",avdSearchId);
	if(!scheduleJobs.isEmpty() || !draftJobs.isEmpty()){
		result=true;
	}
	return result;
}

	/**
	 * The method helps to get main facility. If job owner login then method
	 * retrieves the main facility group.
	 * 
	 * @param currentFacilityId
	 * @return
	 */

	public FacilityDTO getParentFacility(int currentFacilityId) {
		AdmFacility admFacility = (AdmFacility) hibernateTemplate.find(
				"from AdmFacility e where e.facilityId=?", currentFacilityId)
				.get(0);
		int facilityParentId = admFacility.getFacilityParentId();

		if (facilityParentId > 0) {
			AdmFacility parentAdmFacility = hibernateTemplate.get(
					AdmFacility.class, facilityParentId);
			if (parentAdmFacility.getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY_GROUP)
					|| parentAdmFacility.getFacilityType().equalsIgnoreCase(
							MMJBCommonConstants.FACILITY)) {
				// For job owner login
				admFacility = parentAdmFacility;
			}
		}
		FacilityDTO facilityDTO = null;
		if (admFacility != null) {
			List<AdmFacility> admFacilities = new ArrayList<AdmFacility>();
			admFacilities.add(admFacility);
			facilityDTO = facilityConversionHelper.transformToFacilityDTO(
					admFacilities).get(0);
		}
		return facilityDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#validateCityStateZip(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateCityStateZip(String city, String state, String zipCode,
			String country) throws JobBoardDataException {
		boolean valied=false;
		try {
			
			Object[] inputs = { city,state,zipCode,country};
			List<JpLocation> jpLocationList = hibernateTemplate
					.find("from JpLocation loc where loc.city=? and loc.state=? and  loc.postcode=? and loc.country=?",
							inputs);
			if(jpLocationList.size()>0){
				valied=true;
			}
		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException(
					"Error while fetching the latitude and longitude By CityState from the Database..."
							+ e);
		}
		return valied;
	}
}
