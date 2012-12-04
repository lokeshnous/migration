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

import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;

@Service
@Qualifier("jobPostExpireSoon")
public class JobPostExpireSoon implements JobWorker{
	private static final Logger LOGGER = Logger
			.getLogger(JobPostExpireSoon.class);
	
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Autowired
	private MMEmailService emailService;
	
	@Override
	public void executeJob() {
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.retreiveActiveJobsExpireSoon();
		//send the mails here 
		EmailDTO emailDTO = new EmailDTO();
		StringBuffer jobPostExpiresSoon = null;
		StringBuffer mailBody  = null;
		int start,end;
		InternetAddress[] jsToAddress = new InternetAddress[1];
		
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.expiresoon.email.subject").trim());
		
		for(SchedulerDTO schedulerDTO: schedulerDTOList){
			
			try {
				jsToAddress[0] = new InternetAddress(schedulerDTO.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error("Error occured while geting InternetAddress reference",jbex);
			}
			
			emailDTO.setToAddress(jsToAddress);
			
			jobPostExpiresSoon  = new StringBuffer();
			
			mailBody  = new StringBuffer(emailConfiguration.getProperty("employer.jobpost.expiresoon.email.body").trim());
			
			//set the expire date in email body
			start = mailBody.toString()
					.indexOf("?expireDate");
			end = start + "?expireDate".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getExpireDate());
			}
			
			//set the company name in table
			start = mailBody.toString()
					.indexOf("?userName");
			end = start + "?userName".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getFirstName()+" "+schedulerDTO.getLastName());
			}
			
			//set the company name in table
			start = mailBody.toString()
					.indexOf("?jobId");
			end = start + "?jobId".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						String.valueOf(schedulerDTO.getJobId()));
			}
			
			//set the expire date in table
			start = mailBody.toString()
					.indexOf("?companyName");
			end = start + "?companyName".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getCompanyName());
			}
			
			//set the user name in table
			start = mailBody.toString()
					.indexOf("?expireDateTb");
			end = start + "?expireDateTb".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getExpireDate());
			}
						
			jobPostExpiresSoon.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			jobPostExpiresSoon.append(mailBody);
			jobPostExpiresSoon.append(emailConfiguration.getProperty("email.footer").trim());
			
			emailDTO.setBody(jobPostExpiresSoon.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}
		LOGGER.info("Scheduler : Job Expires Soon Scheduler completed .......");
	}

	@Override
	public String getJobName() {
		return null;
	}

}
