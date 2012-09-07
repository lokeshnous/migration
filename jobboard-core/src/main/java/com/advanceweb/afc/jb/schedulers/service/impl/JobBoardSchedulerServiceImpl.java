package com.advanceweb.afc.jb.schedulers.service.impl;

/**
 * 
 */


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker;
import com.advanceweb.afc.jb.schedulers.service.JobBoardSchedulerService;



/**
 * Class will hold all the jobs to be run and will be triggered by the Quartz
 * from the spring configuration.
 * 
 * @author muraliananthr
 * 
 */
@Service
public class JobBoardSchedulerServiceImpl implements JobBoardSchedulerService {

	/**
	 * 
	 */
	private static final Logger LOGGER = Logger
			.getLogger(JobBoardSchedulerServiceImpl.class);

	/**
	 * 
	 */
	private List<JobWorker> jobWorkers;

	@Override
	public void execute() {
		LOGGER.info("Scheduler started....");
		for (JobWorker jobWorker : jobWorkers) {
			LOGGER.info("Starting Job....");
			LOGGER.info("Job Name::" + jobWorker.getJobName());
			jobWorker.executeJob();
		}
		LOGGER.info("Scheduler tasks are completed....");
	}

	/**
	 * @return the jobWorkers
	 */
	public List<JobWorker> getJobWorkers() {
		return jobWorkers;
	}

	/**
	 * @param jobWorkers
	 *            the jobWorkers to set
	 */
	public void setJobWorkers(List<JobWorker> jobWorkers) {
		this.jobWorkers = jobWorkers;
	}

}
