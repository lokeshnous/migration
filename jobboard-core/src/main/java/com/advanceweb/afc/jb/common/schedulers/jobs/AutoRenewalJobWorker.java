package com.advanceweb.afc.jb.common.schedulers.jobs;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.employer.service.ManageFeatureEmployerProfile;

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
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;

	@Override
	public void executeJob() {
		LOGGER.info("AutoRenewalJobWorker-> Execute Job.....");
		List<JobPostDTO> jobsList =employerJobPostDAO.retreiveAllExpiredJobs();
		for(JobPostDTO dto : jobsList){
			int nsCustomerID = manageFeatureEmployerProfile.getNSCustomerIDFromAdmFacility(dto.getFacilityId());			
			UserDTO userDTO = manageFeatureEmployerProfile.getNSCustomerDetails(nsCustomerID);
			dto.setbFeatured(userDTO.isFeatured());
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
