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

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.UserAlertService;
import com.advanceweb.afc.jb.user.UserService;

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
	@Autowired
	private UserAlertService alertService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FacilityService facilityService;
	
	@Override
	public void executeJob() {
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.retreiveActiveJobsExpireSoon();
		//send the mails here 
		EmailDTO emailDTO = new EmailDTO();
//		InternetAddress[] jsToAddress = new InternetAddress[1];
		
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.expiresoon.email.subject").trim());
		
		for (SchedulerDTO schedulerDTO : schedulerDTOList) {
			
			FacilityDTO mainFacilityDTO = facilityService.getParentFacility(schedulerDTO.getFacilityId());
//	        UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
//					.getUserId());
	        
	        // check for job is posted by Job owner and send mail on interest
			 if(schedulerDTO.getCreateUserId() !=  mainFacilityDTO.getUserId()){
	        	List<UserAlertDTO> alertDTOs = alertService.viewAlerts(schedulerDTO
						.getCreateUserId());
	        	UserDTO mainuserDto = userService.getUserByUserId(schedulerDTO
						.getCreateUserId());
	        	InternetAddress[] toAddress = new InternetAddress[1];
				try {
					toAddress[0] = new InternetAddress(mainuserDto.getEmailId());
				} catch (AddressException jbex) {
					LOGGER.error(
							"Error occured while geting InternetAddress reference",
							jbex);
				}
				if (null != alertDTOs && alertDTOs.size() > 0) {
					for (UserAlertDTO alertDTO : alertDTOs) {
						if (alertDTO.getAlertId() > 0
								&& alertDTO.getAlertId() == MMJBCommonConstants.JOB_POSTING_EXPIRING_SOON) {
							sendExpiringSoonMail(emailDTO, toAddress, schedulerDTO);
						}
					}
				} else {
					sendExpiringSoonMail(emailDTO, toAddress, schedulerDTO);
				}
	        }
	        // send mail to employer on interest
			List<UserAlertDTO> alertDTOs = alertService
					.viewAlerts(mainFacilityDTO.getUserId());
			UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
					.getUserId());
			InternetAddress[] toAddress = new InternetAddress[1];
			try {
				toAddress[0] = new InternetAddress(mainuserDto.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error(
						"Error occured while geting InternetAddress reference",
						jbex);
			}
			if (null != alertDTOs && alertDTOs.size() > 0) {
				for (UserAlertDTO alertDTO : alertDTOs) {
					if (alertDTO.getAlertId() > 0
							&& alertDTO.getAlertId() == MMJBCommonConstants.JOB_POSTING_EXPIRING_SOON) {
						sendExpiringSoonMail(emailDTO, toAddress, schedulerDTO);
					}
				}
			} else {
				sendExpiringSoonMail(emailDTO, toAddress, schedulerDTO);
			}
		
			/*// if employer / agency has set alert starts
			List<UserAlertDTO> alertDTOs = alertService.viewAlerts(schedulerDTO
					.getUserId());
			if (null != alertDTOs && alertDTOs.size() > 0) {
				for (UserAlertDTO alertDTO : alertDTOs) {
					if (alertDTO.getAlertId() > 0
							&& alertDTO.getAlertId() == MMJBCommonConstants.JOB_POSTING_EXPIRING_SOON) {
						sendExpiringSoonMail(emailDTO, jsToAddress,
								schedulerDTO);
					}

				} // if employer / agency has set alert ends

			} else {// if employer / agency has not set any alert(Default)
				sendExpiringSoonMail(emailDTO, jsToAddress, schedulerDTO);
			}*/
			LOGGER.info("Scheduler : Job Expires Soon Scheduler completed .......");
		}
	}

	/**
	 * Method helps to send mail for expiring jobs 
	 * 
	 * @param emailDTO
	 * @param jsToAddress
	 * @param schedulerDTO
	 */
	private void sendExpiringSoonMail(EmailDTO emailDTO,
			InternetAddress[] jsToAddress, SchedulerDTO schedulerDTO) {
		StringBuffer jobPostExpiresSoon;
		StringBuffer mailBody;
		int start;
		int end;
//		try {
//			jsToAddress[0] = new InternetAddress(schedulerDTO.getEmailId());
//			// TODO: remove the hard code values
//			//jsToAddress[0] = new InternetAddress("pramod1356@gmail.com");
//		} catch (AddressException jbex) {
//			LOGGER.error("Error occured while geting InternetAddress reference",jbex);
//		}
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
		start = mailBody.toString()
				.indexOf("?empdashboardLink");
		end = start + "?empdashboardLink".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					emailConfiguration.getProperty("employerer.dashboard.url").trim());
		}		
		jobPostExpiresSoon.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		jobPostExpiresSoon.append(mailBody);
		jobPostExpiresSoon.append(emailConfiguration.getProperty("email.footer").trim());
		
		emailDTO.setBody(jobPostExpiresSoon.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}

	@Override
	public String getJobName() {
		return null;
	}

}
