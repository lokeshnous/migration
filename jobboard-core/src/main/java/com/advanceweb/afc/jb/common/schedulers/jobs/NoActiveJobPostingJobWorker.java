package com.advanceweb.afc.jb.common.schedulers.jobs;

import java.util.ArrayList;
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
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;

/**
 * The class is used to send email to the employer containing information that they have no active job posting.
 * 
 */

@Service
@Qualifier("noActiveJobPostingJobWorker")
public class NoActiveJobPostingJobWorker implements JobWorker {

	private static final Logger LOGGER = Logger
			.getLogger(NoActiveJobPostingJobWorker.class);
	private static final String JOB_NAME = "SEND_MAIL";
	@Autowired
	private JobPostService jobPostService;
	@Autowired
	private MMEmailService emailService;
	
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Autowired
	private FacilityService facilityService;

	@Override
	public void executeJob() {
		List<SchedulerDTO> schedulerDTO=facilityService.getAllFacilityList();
		List<SchedulerDTO> sendMailList=new ArrayList<SchedulerDTO>();
		for(SchedulerDTO dto:schedulerDTO){
			List<JobPostDTO>jobPostDTOList=jobPostService.retrieveAllJobByStatus(MMJBCommonConstants.POST_NEW_JOB, dto.getUserId(), 0, 10);
		if(jobPostDTOList!=null){
			sendMailList.add(dto);
		}
		}
		for(SchedulerDTO dto:sendMailList){
		StringBuffer stringBuffer = new StringBuffer();
		InternetAddress[] toAddress = new InternetAddress[1];
		try {
			toAddress[0] = new InternetAddress(dto.getEmailId());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setToAddress(toAddress);
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("noActiveJobPostings.subject").trim());
		
		StringBuffer mailBody=new StringBuffer(emailConfiguration.getProperty("no.active.job.posting.mail.body").trim());
		int start, end;
		start = mailBody.indexOf("?user_name");
		end = start + "?user_name".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					dto.getFirstName()+" "+dto.getLastName());
		}
		start = mailBody.toString()
				.indexOf("?company_name");
		end = start + "?company_name".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					dto.getCompanyName());
		}
		start = mailBody.toString()
				.indexOf("?empdashboardLink");
		end = start + "?empdashboardLink".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					emailConfiguration.getProperty("employerer.dashboard.url").trim());
		}
		stringBuffer.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		stringBuffer.append(mailBody);
		stringBuffer.append(emailConfiguration.getProperty("email.footer").trim());
		emailDTO.setBody(stringBuffer.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
		}
		LOGGER.info("NoActiveJobPostingJobWorker.-> Executed Job Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
