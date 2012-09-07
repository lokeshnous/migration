package com.advanceweb.afc.jb.common.schedulers.jobs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.employer.dao.JobPostDAO;

/**
 * @author muraliananthr
 * 
 */
@Service
@Scope("prototype")
@Qualifier("activeJobsJobWorker")
public class ActiveJobsJobWorker implements JobWorker {

	private static final Logger LOGGER = Logger.getLogger(ActiveJobsJobWorker.class);
	private final static String JOB_NAME = "ACTIVE_JOBS";


	@Autowired
	private JobPostDAO employerJobPostDAO;


	@Override
	public void executeJob() {
		LOGGER.info("ActiveJobsJobWorker.-> Execute Job.....");		
		employerJobPostDAO.executeActiveJobWorker();				
		LOGGER.info("ActiveJobsJobWorker.-> Executed Job Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
