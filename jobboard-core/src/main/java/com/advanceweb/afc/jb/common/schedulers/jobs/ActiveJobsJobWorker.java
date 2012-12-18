package com.advanceweb.afc.jb.common.schedulers.jobs;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;

/**
 * @author muraliananthr
 * 
 */
@Service
@Qualifier("activeJobsJobWorker")
public class ActiveJobsJobWorker implements JobWorker {

	private static final Logger LOGGER = Logger.getLogger(ActiveJobsJobWorker.class);
	private final static String JOB_NAME = "ACTIVE_JOBS";
	private final static String Q_USERNAME = "?userName";
	private final static String Q_JOBID = "?jobId";
	private final static String Q_COMPANYNAME = "?companyName";

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
	@Autowired
	private MMEmailService emailService;
	
	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;


	@Override
	public void executeJob() {
		
		LOGGER.info("ActiveJobsJobWorker.-> Execute Job.....");
		
		LOGGER.info("ActiveJobsJobWorker -> Expiring the eligible jobs started.....");	
		expireEligibleJobs();
		LOGGER.info("ActiveJobsJobWorker -> Expiring the eligible jobs completed.....");
		
		LOGGER.info("ActiveJobsJobWorker -> Auto renew the scheduled jobs started.....");
		autoRenewScheduledJobs();				
		LOGGER.info("ActiveJobsJobWorker -> Auto renew the scheduled jobs completed.....");
		
		LOGGER.info("ActiveJobsJobWorker.-> Executed Job Successfully.....");
	}

	private void autoRenewScheduledJobs() {
		//Retreive all the schedulded jobs to validate with net suite data 
		List<JobPostDTO> jobsList = employerJobPostDAO.retreiveAllScheduledJobs();	
		//Calling net suite to check whether the employer is featured or not 
		//And to know, whether the employer is applicable for free job posting
		for (JobPostDTO dto : jobsList) {
			int nsCustomerID = manageFeaturedEmployerProfile
					.getNSCustomerIDFromAdmFacility(dto.getFacilityId());
			// Get the list of valid packages purchased by customers from
			// NetSuite
			List<String> purchasedPackages = manageFeaturedEmployerProfile
					.getNSCustomerPackages(nsCustomerID);
			dto.setbFeatured(purchasedPackages
					.contains(MMJBCommonConstants.FEATURE_30)
					|| purchasedPackages
							.contains(MMJBCommonConstants.FEATURE_90)
					|| purchasedPackages
							.contains(MMJBCommonConstants.FEATURE_180)
					|| purchasedPackages
							.contains(MMJBCommonConstants.FEATURE_365));
			// Verify the employer is applicable for free posting or not
			dto.setXmlStartEndDateEnabled(purchasedPackages
					.contains(MMJBCommonConstants.XML_90)
					|| purchasedPackages.contains(MMJBCommonConstants.XML_180)
					|| purchasedPackages.contains(MMJBCommonConstants.XML_365));
		}
		//Executing the jobs
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.executeActiveJobWorker(jobsList);
		//send the mails here 
		EmailDTO emailDTO = new EmailDTO();
		StringBuffer autoRenewFailed = null;
		StringBuffer mailBody = null;
		int start, end;
		InternetAddress[] jsToAddress = new InternetAddress[1];

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.autorenew.failed.email.subject").trim());
		
		for(SchedulerDTO schedulerDTO: schedulerDTOList){
			
			try {
				jsToAddress[0] = new InternetAddress(schedulerDTO.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error("Error occured while geting InternetAddress reference",jbex);
			}
			
			emailDTO.setToAddress(jsToAddress);
			
			autoRenewFailed  = new StringBuffer();
			
			mailBody  = new StringBuffer(emailConfiguration.getProperty("employer.jobpost.autorenew.failed.email.body").trim());
			
			//set the company name in table
			start = mailBody.toString()
					.indexOf(Q_USERNAME);
			end = start + Q_USERNAME.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getFirstName()+" "+schedulerDTO.getLastName());
			}
			
			//set the company name in table
			start = mailBody.toString()
					.indexOf(Q_JOBID);
			end = start + Q_JOBID.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						String.valueOf(schedulerDTO.getJobId()));
			}
			
			//set the expire date in table
			start = mailBody.toString()
					.indexOf(Q_COMPANYNAME);
			end = start + Q_COMPANYNAME.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getCompanyName());
			}
			start = mailBody.toString()
					.indexOf("?empdashboardLink");
			end = start + "?empdashboardLink".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						emailConfiguration.getProperty("employerer.dashboard.url").trim());
			}
			autoRenewFailed.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			autoRenewFailed.append(mailBody);
			autoRenewFailed.append(emailConfiguration.getProperty("email.footer").trim());
			
			emailDTO.setBody(autoRenewFailed.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}
	}

	private boolean expireEligibleJobs(){
		//expire the jobs if eligible to expire
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.executeExpireJobs();
		//send the mails here 
		EmailDTO emailDTO = new EmailDTO();
		StringBuffer expireJobPost = null;
		StringBuffer mailBody  = null;
		int start,end;
		InternetAddress[] jsToAddress = new InternetAddress[1];
		
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.expired.email.subject").trim());
		
		for(SchedulerDTO schedulerDTO: schedulerDTOList){
			
			try {
				jsToAddress[0] = new InternetAddress(schedulerDTO.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error("Error occured while geting InternetAddress reference",jbex);
			}
			
			emailDTO.setToAddress(jsToAddress);
			
			expireJobPost  = new StringBuffer();
			
			mailBody  = new StringBuffer(emailConfiguration.getProperty("employer.jobpost.expired.email.body").trim());
			
			//set the company name in table
			start = mailBody.toString()
					.indexOf(Q_USERNAME);
			end = start + Q_USERNAME.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getFirstName()+" "+schedulerDTO.getLastName());
			}
			
			//set the company name in table
			start = mailBody.toString()
					.indexOf(Q_JOBID);
			end = start + Q_JOBID.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						String.valueOf(schedulerDTO.getJobId()));
			}
			
			//set the expire date in table
			start = mailBody.toString()
					.indexOf(Q_COMPANYNAME);
			end = start + Q_COMPANYNAME.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getCompanyName());
			}
			start = mailBody.toString()
					.indexOf("?empdashboardLink");
			end = start + "?empdashboardLink".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						emailConfiguration.getProperty("employerer.dashboard.url").trim());
			}
			expireJobPost.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			expireJobPost.append(mailBody);
			expireJobPost.append(emailConfiguration.getProperty("email.footer").trim());
			
			emailDTO.setBody(expireJobPost.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}	
		return true;
	}
	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
