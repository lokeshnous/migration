package com.advanceweb.afc.jb.common.schedulers.jobs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * The class is used to send email to the job seekers containing information on
 * Employers who viewed their profile.
 * 
 */

@Service
@Qualifier("profileViewSendMailJobWorker")
public class ProfileViewSendMailJobWorker implements JobWorker {

	private static final Logger LOGGER = Logger
			.getLogger(ProfileViewSendMailJobWorker.class);
	private static final String JOB_NAME = "SEND_MAIL";

	@Override
	public void executeJob() {

		LOGGER.info("ProfileViewSendMailJobWorker.-> Executed Job Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
