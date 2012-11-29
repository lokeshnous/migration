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
@Qualifier("NewJobsUpdationMailWorkerBiweekly")
public class NewJobsUpdationMailWorkerBiweekly implements JobWorker {

	private static final Logger LOGGER = Logger
			.getLogger(NewJobsUpdationMailWorkerBiweekly.class);
	private static final String JOB_NAME = "SEND_MAIL";
	@Autowired
	private SaveSearchService saveSearchService;
	@Autowired
	private MMEmailService emailService;

	@Override
	public void executeJob() {
		LOGGER.info("New Jobs Updation Mail Worker.-> Execution Of  Biweekly Job Scheduler Started.....");

		List<SaveSearchedJobsDTO> savedSearch = saveSearchService
				.getsavedSearches();
		String searchUrl = null;
		int emailFrequency;
		Map<String, String> inputParams = new HashMap<String, String>();
		for (SaveSearchedJobsDTO saveSearchedJobsDTO : savedSearch) {
			searchUrl = saveSearchedJobsDTO.getUrl();

			StringTokenizer stringNew = new StringTokenizer(searchUrl, ";");
			if (null != saveSearchedJobsDTO.getEmailFrequency()) {
				emailFrequency = Integer.valueOf(saveSearchedJobsDTO
						.getEmailFrequency());
				if (emailFrequency > 0
						&& emailFrequency == MMJBCommonConstants.BIWEEKLY_SCHEDULER) {

					emailService.sendMailBySavedSearch(
							MMJBCommonConstants.SCHEDULE_BIWEEKLY, inputParams,
							saveSearchedJobsDTO, stringNew);

				}
			}

		}
		LOGGER.info("New Jobs Updation Mail Worker.-> Execution Of  Biweekly Job Scheduler Executed Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
