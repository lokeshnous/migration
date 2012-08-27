package com.advanceweb.afc.jb.employer.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.employer.helper.JobPostConversionHelper;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class implements all the DAO operations related to post new job 
 */
@Transactional 
@Repository("employerJobPostDAO")
@SuppressWarnings("unchecked")
public class JobPostDAOImpl implements JobPostDAO {

	private static final String FIND_ADM_USER_FACILITY="select facility from AdmUserFacility facility, AdmRole role where role.roleId=facility.id.roleId and facility.id.userId=? and role.name=?";
	
	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;
	

	
	@Autowired
	private JobPostConversionHelper jobPostConversionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
/*	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactory);
	}*/
	/**
	   @Author :Prince Mathew
	   @Purpose:To get the records of the Employer by userId from the DB
	   @Created:Jul 19, 2012
	   @Param  :userId
	   @Return :EmployerInfoDTO containing the information of corresponding Employer
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getEmployerInfo(int)
	 */
	@Override
	public EmployerInfoDTO getEmployerInfo(int userId, String roleName) {
		
		EmployerInfoDTO employerInfoDTO=new EmployerInfoDTO();
		
		Object[] inputs= {userId, roleName};

		List<AdmUserFacility> facilityList = hibernateTemplate.find(FIND_ADM_USER_FACILITY, inputs);
		
		if(null != facilityList && facilityList.size() != 0){
			AdmUserFacility userFacility = facilityList.get(0);
			AdmFacility facility = userFacility.getAdmFacility();
			if(null != facility){
				employerInfoDTO.setCustomerNamel(facility.getName());
				employerInfoDTO.setUserId(userFacility.getId().getUserId());
				employerInfoDTO.setFacilityId(userFacility.getId().getFacilityId());
				employerInfoDTO.setRoleId(userFacility.getId().getRoleId());
			}					
		}		
		
		return employerInfoDTO;
	}
	
	/**
	   @Author :Prince Mathew
	   @Purpose:to get the List of the State for the drop down of state for Post New Job Screen 
	   @Created:Jul 19, 2012
	   @Param  :not required
	   @Return :List of StateDTO
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getStateList()
	 */
	public List<StateDTO> getStateList() {
		
		List<JpAttribList> merLookupList =  hibernateTemplateTracker.find("from JpAttribList e where e.lookupCategory='State' and e.lookupStatus='1'");	

		return null;
	}
	
	/**
	   @Author :Prince Mathew
	   @Purpose:This method is called to post the new job
	   @Created:Jul 20, 2012
	   @Param  :EmployerInfoDTO object
	   @Return :boolean result
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#savePostJob(com.advanceweb.afc.jb.common.EmployerInfoDTO)
	 */
	@Override
	public boolean savePostJob(JobPostDTO dto) {
		
		try {			
				JpLocation location = null;			
				//Retrieving location Id based on the data selection while posting the new job
				Object[] inputs = {dto.getJobCountry(), dto.getJobState(), dto.getJobCity(),dto.getJobZip()};
				List<JpLocation> locationList = hibernateTemplate.find("from JpLocation loc where loc.country=? and loc.state=? and loc.city=? and loc.postcode=?",inputs);
				if(null != locationList && locationList.size() != 0){
					location = locationList.get(0);
				}
				
				//Retrieving the template object selected based on the template id selected by the user
				JpTemplate template = hibernateTemplate.load(JpTemplate.class,Integer.valueOf(dto.getBrandTemplate()));
				
				JpJobType jobType = hibernateTemplate.load(JpJobType.class, Integer.valueOf(dto.getJobPostingType()));
				
				AdmFacility admFacility = hibernateTemplate.load(AdmFacility.class, Integer.valueOf(dto.getFacilityId()));
				
				JpJob jpJob=jobPostConversionHelper.transformJobDtoToJpJob(dto, template, jobType, admFacility);
				hibernateTemplate.save(jpJob);
				List<JpJobApply> applyJobList = jobPostConversionHelper.transformJobPostDTOToJpJobApply(dto, jpJob);
				hibernateTemplate.saveOrUpdateAll(applyJobList);
				List<JpJobLocation> locList = jobPostConversionHelper.transformJobPostDTOToJpJbLocation(dto, jpJob, location);
				hibernateTemplate.saveOrUpdateAll(locList);				
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public List<JobPostDTO> retrieveAllJobPost(int employerId) {
		List<JpJob> jobs = hibernateTemplate
				.find("SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
						+ employerId + "and a.deleteDt is NULL");
		return jobPostConversionHelper.transformJpJobListToJobPostDTOList(jobs);

	}

	@Override
	public JobPostDTO editJob(int jobId) {
		JobPostDTO dto = new JobPostDTO();
		JpJob job =  hibernateTemplate.get(JpJob.class,
				jobId);

		if (job != null) {

			dto =  jobPostConversionHelper
					.transformJpJobToJobPostDTO(job);

		}

		return dto;
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
		if (null != job.getJobStatus() && job.getJobStatus().equalsIgnoreCase(MMJBCommonConstants.POST_JOB_EXPIRED)) {
			// System deletes the job postings which are in “Expired” status
			job.setDeleteDt(new Timestamp(new Date().getTime()));
			hibernateTemplate.save(job);
		}
		return true;
	}
	/**
	 * This method is called to update the Job
	 * 
	 * @param jobId
	 * @param userId
	 * @return delete status
	 */
	@Override
	public boolean updateManageJob(boolean autoRenew, String brandTemplate,
			int jobId, int userId) {
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
			// System deletes the job postings which are in “Expired” status
			job.setUpdateDt(new Timestamp(new Date().getTime()));
			job.setAutoRenew(autoRenew?1:0);
			JpTemplate template = hibernateTemplate.load(JpTemplate.class,Integer.valueOf(brandTemplate));
			job.setJpTemplate(template);
			hibernateTemplate.save(job);
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
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
		if (null != job.getJobStatus() && job.getJobStatus().equalsIgnoreCase(MMJBCommonConstants.POST_NEW_JOB)) {
			// System should deactivate the job posting which are in “Active” status
			job.setJobStatus(MMJBCommonConstants.POST_JOB_INACTIVE);
			hibernateTemplate.save(job);
		}
		return true;
	}
	/**
	 * This method is called to delete the Job
	 * 
	 * @param jobId
	 * @param userId
	 * @return delete status
	 */
	@Override
	public boolean repostJob(int jobId, int userId) {
		JpJob job = hibernateTemplate.get(JpJob.class, jobId);
		if (null != job.getJobStatus() && job.getJobStatus().equalsIgnoreCase(MMJBCommonConstants.POST_JOB_INACTIVE)) {
			// Repost the inactive job and extend the end date to one month
			Calendar now = Calendar.getInstance();
			now.setTime(job.getEndDt());
			now.add(Calendar.DAY_OF_MONTH,30);
			job.setJobStatus(MMJBCommonConstants.POST_NEW_JOB);
			job.setEndDt(now.getTime());
			hibernateTemplate.save(job);
		}
		return true;
	}

	@Override
	public List<JobPostDTO> retrieveAllJobByStatus(String jobStatus,
			int userId) {List<JpJob> jobs = hibernateTemplate
			.find("SELECT a from JpJob a,AdmUserFacility b where a.admFacility.facilityId=b.admFacility.facilityId and b.id.userId="
					+ userId +" and a.jobStatus = '"+jobStatus+ "' and a.deleteDt is NULL");
	return jobPostConversionHelper.transformJpJobListToJobPostDTOList(jobs);
	}
	
}
