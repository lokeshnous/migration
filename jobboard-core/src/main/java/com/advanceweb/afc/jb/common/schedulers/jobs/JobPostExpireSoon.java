/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobPostExpireSoon.class);
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	/** The employer job post dao. */
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;
	
	/** The alert service. */
	@Autowired
	private UserAlertService alertService;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The facility service. */
	@Autowired
	private FacilityService facilityService;
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#executeJob()
	 */
	@Override
	public void executeJob() {
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.retreiveActiveJobsExpireSoon();
		//send the mails here 
		EmailDTO emailDTO = new EmailDTO();
//		InternetAddress[] jsToAddress = new InternetAddress[1];
		
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.expiresoon.email.subject").trim());
		
		for (SchedulerDTO schedulerDTO : schedulerDTOList) {
			if (schedulerDTO.getFacilityId() > 0) {
				FacilityDTO mainFacilityDTO = facilityService
						.getParentFacility(schedulerDTO.getFacilityId());
				FacilityDTO facilityDTO = facilityService
						.getFacilityByFacilityId(schedulerDTO.getFacilityId());
				List<FacilityDTO> admUserFacilities = facilityService
						.getUserFacilityDetails(mainFacilityDTO.getFacilityId());
				if (null != admUserFacilities && admUserFacilities.size() > 0) {
					for (FacilityDTO admUserFacility : admUserFacilities) {
						// check for job is posted by Job owner and send mail on
						// interest

						List<UserAlertDTO> alertDTOs = alertService
								.viewAlerts(admUserFacility.getUserId());
						UserDTO mainuserDto = userService
								.getUserByUserId(admUserFacility.getUserId());
						InternetAddress[] jsToAddress = new InternetAddress[1];
						try {
							jsToAddress[0] = new InternetAddress(
									mainuserDto.getEmailId());
						} catch (AddressException jbex) {
							LOGGER.error(
									"Error occured while geting InternetAddress reference",
									jbex);
						}
						if (null != alertDTOs && alertDTOs.size() > 0) {
							for (UserAlertDTO alertDTO : alertDTOs) {
								if (alertDTO.getAlertId() > 0
										&& alertDTO.getAlertId() == MMJBCommonConstants.JOB_POSTING_EXPIRING_SOON) {
									sendExpiringSoonMail(emailDTO, jsToAddress,
											schedulerDTO);
								}
							}
						} else {
							sendExpiringSoonMail(emailDTO, jsToAddress,
									schedulerDTO);
						}
					}
				}
				// if Mail not sent to the main Facility,send mail on interest
				if (!mainFacilityDTO.getFacilityId().equals(facilityDTO
						.getFacilityId()) && schedulerDTO.getFacilityId() > 0) {
					List<FacilityDTO> admUserFacilityList = facilityService
							.getUserFacilityDetails(facilityDTO.getFacilityId());
					if (null != admUserFacilityList
							&& admUserFacilityList.size() > 0) {
						for (FacilityDTO admUserFacility : admUserFacilityList) {

							List<UserAlertDTO> alertDTOs = alertService
									.viewAlerts(admUserFacility.getUserId());
							UserDTO userDto = userService
									.getUserByUserId(admUserFacility
											.getUserId());
							InternetAddress[] jsToAddress = new InternetAddress[1];
							try {
								jsToAddress[0] = new InternetAddress(
										userDto.getEmailId());
							} catch (AddressException jbex) {
								LOGGER.error(
										"Error occured while geting InternetAddress reference",
										jbex);
							}
							if (null != alertDTOs && alertDTOs.size() > 0) {
								for (UserAlertDTO alertDTO : alertDTOs) {
									if (alertDTO.getAlertId() > 0
											&& alertDTO.getAlertId() == MMJBCommonConstants.JOB_POSTING_EXPIRING_SOON) {
										sendExpiringSoonMail(emailDTO,
												jsToAddress, schedulerDTO);
									}
								}
							} else {
								sendExpiringSoonMail(emailDTO, jsToAddress,
										schedulerDTO);
							}
						}
					}
				}

			}
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
		LOGGER.debug("Expiring soon job mail send to :" +emailDTO.getToAddress());
		emailService.sendEmail(emailDTO);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#getJobName()
	 */
	@Override
	public String getJobName() {
		return null;
	}

}
