/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common.schedulers.jobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.mail.service.MMEmailService;

/**
 * The class is used to send email daily to the job seekers containing 
 * new job opportunities that match the saved searches
 * 
 */

@Service
@Qualifier("NewJobsUpdationMailWorkerDaily")
public class NewJobsUpdationMailWorkerDaily implements JobWorker {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(NewJobsUpdationMailWorkerDaily.class);
	
	/** The Constant JOB_NAME. */
	private static final String JOB_NAME = "SEND_MAIL";
	
	/** The save search service. */
	@Autowired
	private SaveSearchService saveSearchService;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#executeJob()
	 */
	@Override
	public void executeJob() {
		LOGGER.info("New Jobs Updation Mail Worker.-> Execution Of  Daily Job Scheduler Started.....");
		List<SaveSearchedJobsDTO> savedSearch = saveSearchService
				.getsavedSearches();
		String searchUrl = null;
		int emailFrequency;
		Map<String, String> inputParams = new HashMap<String, String>();
		for (SaveSearchedJobsDTO saveSearchedJobsDTO : savedSearch) {
			searchUrl = saveSearchedJobsDTO.getUrl();

			StringTokenizer stringNew = new StringTokenizer(searchUrl, ";");
			if (null == saveSearchedJobsDTO.getEmailFrequency()) {
				saveSearchedJobsDTO
						.setEmailFrequency(MMJBCommonConstants.DAILY_ALERT);
			}
			emailFrequency = Integer.valueOf(saveSearchedJobsDTO
					.getEmailFrequency());
			if (emailFrequency > 0
					&& emailFrequency == MMJBCommonConstants.DAILY_SCHEDULER) {
				emailService.sendMailBySavedSearch(
						MMJBCommonConstants.SCHEDULE_DAILY, inputParams,
						saveSearchedJobsDTO, stringNew);
			}

		}
		LOGGER.info("New Jobs Updation Mail Worker.-> Execution Of  Daily Job Scheduler Executed Successfully.....");
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#getJobName()
	 */
	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
