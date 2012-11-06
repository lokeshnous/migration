package com.advanceweb.afc.jb.common.schedulers.jobs;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;

/**
 * @author muraliananthr
 * 
 */
@Service
public class AutoRenewalJobWorker implements JobWorker {

	private final static String JOB_NAME = "AUTORENEAL_JOB";

	private static final Logger LOGGER = Logger.getLogger(AutoRenewalJobWorker.class);
	
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
	@Autowired
	private ManageFeaturedEmployerProfile manageFeatureEmployerProfile;

	@Override
	public void executeJob() {
		LOGGER.info("AutoRenewalJobWorker-> Execute Job.....");
		//Retreive all the expireds jobs to validate with net suite data 
		List<JobPostDTO> jobsList =employerJobPostDAO.retreiveAllExpiredJobs();
		//Calling net suite to check whether the employer is featured or not 
		//And to know, whether the employer is applicable for free job posting
		for(JobPostDTO dto : jobsList){
			int nsCustomerID = manageFeatureEmployerProfile.getNSCustomerIDFromAdmFacility(dto.getFacilityId());			
			UserDTO userDTO = manageFeatureEmployerProfile.getNSCustomerDetails(nsCustomerID);
			dto.setbFeatured(userDTO.isFeatured());
			//Verify the employer is applicable for free posting or not
			if(userDTO.isXmlFeedEnabled() && null != userDTO.getXmlFeedStartDate() && null != userDTO.getXmlFeedEndDate()){
				dto.setXmlStartEndDateEnabled(MMUtils.compareDateRangeWithCurrentDate(userDTO.getXmlFeedStartDate(), userDTO.getXmlFeedEndDate()));
			}
		}
		employerJobPostDAO.executeAutoRenewalJobWorker(jobsList);
		LOGGER.info("ActiveJobsJobWorker-> Executed Job Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
