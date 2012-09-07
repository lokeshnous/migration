package com.advanceweb.afc.jb.common.schedulers.jobs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.employer.dao.JobPostDAO;

/**
 * @author muraliananthr
 * 
 */
@Service
@Scope("prototype")
public class AutoRenewalJobWorker implements JobWorker {

	private final static String JOB_NAME = "AUTORENEAL_JOB";

	private static final Logger LOGGER = Logger.getLogger(AutoRenewalJobWorker.class);
	
	@Autowired
	private JobPostDAO employerJobPostDAO;

	@Override
	public void executeJob() {
		LOGGER.info("AutoRenewalJobWorker-> Execute Job.....");
		employerJobPostDAO.executeAutoRenewalJobWorker();
		LOGGER.info("ActiveJobsJobWorker-> Executed Job Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
