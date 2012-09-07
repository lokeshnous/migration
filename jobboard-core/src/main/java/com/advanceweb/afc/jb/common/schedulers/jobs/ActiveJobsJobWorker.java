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
@SuppressWarnings("unchecked")
public class ActiveJobsJobWorker implements JobWorker {

	protected static Logger LOGGER = Logger.getLogger(ActiveJobsJobWorker.class);
	private final static String JOB_NAME = "ACTIVE_JOBS";

//	private static final String FIND_EXPIRED_JOBS = "from JpJob job where job.active=1 and date_format(job.endDt, '%m-%d-%Y') = DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY),'%m-%d-%Y')";
//	private static final String FIND_SCHEDULED_JOBS = "from JpJob job where job.active=0 and date_format(job.startDt, '%m-%d-%Y') = DATE_FORMAT(NOW(),'%m-%d-%Y')";
	
	@Autowired
	private JobPostDAO employerJobPostDAO;


	@Override
	public void executeJob() {
		LOGGER.info("ActiveJobsJobWorker.-> Execute Job.....");
		
		boolean bExecuted = employerJobPostDAO.executeActiveJobWorker();
		
/*		//Update Jobs as expired
		try {

			List<JpJob> expiredJobs = hibernateTemplate.find(FIND_EXPIRED_JOBS);
			
			for(JpJob job : expiredJobs){
				try {
					job.setActive((byte)0);
					hibernateTemplate.saveOrUpdate(job);
				} catch (Exception e) {
					LOGGER.error("Failed to mark job as expired for job Id  " +job.getJobId());
					LOGGER.error(e);
				}
				LOGGER.info("ActiveJobsJobWorker-> Executed Job Successfully.....");
			}
			
		} catch (DataAccessException e) {
			LOGGER.error("Failed to retreive expired jobs  ");
			LOGGER.error(e);
		}
		
		//Schedule Jobs
		try {
			
			List<JpJob> scheduledJobs = hibernateTemplate.find(FIND_SCHEDULED_JOBS);
			
			for(JpJob job : scheduledJobs){		
				//TODO
				//Check for available credits
				int credits=1;
				if(credits == 0){
					LOGGER.error(job.getName()+" Doesn't have sufficient credits to post the job " +job.getJobId());
				}else{				
					try {
						job.setActive((byte)1);
						hibernateTemplate.saveOrUpdate(job);
					} catch (Exception e) {
						LOGGER.error("Failed to post a job as Active " +job.getJobId());
						LOGGER.error(e);
					}
				}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}	*/	
		
		LOGGER.info("ActiveJobsJobWorker.-> Executed Job Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
