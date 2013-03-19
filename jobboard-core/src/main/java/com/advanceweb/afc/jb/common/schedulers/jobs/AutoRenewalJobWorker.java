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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.UserAlertService;
import com.advanceweb.afc.jb.user.UserService;

/**
 * @author muraliananthr
 * 
 */
@Service
public class AutoRenewalJobWorker implements JobWorker {

	/** The Constant JOB_NAME. */
	private final static String JOB_NAME = "AUTORENEAL_JOB";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(AutoRenewalJobWorker.class);
	
	/** The employer job post dao. */
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;
	
	/** The facility service. */
	@Autowired
	private FacilityService facilityService;
	
	/** The manage featured employer profile. */
	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;

	/** The alert service. */
	@Autowired
	private UserAlertService alertService;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The NetSuite package internal ID. */
	@Value("${FEATURE_30}")
	private String FEATURE_30;

	/** The NetSuite package internal ID. */
	@Value("${FEATURE_90}")
	private String FEATURE_90;
	
	/** The NetSuite package internal ID. */
	@Value("${FEATURE_180}")
	private String FEATURE_180;
	
	/** The NetSuite package internal ID. */
	@Value("${FEATURE_365}")
	private String FEATURE_365;
	
	/** The NetSuite package internal ID. */
	@Value("${XML_90}")
	private String XML_90;
	
	/** The NetSuite package internal ID. */
	@Value("${XML_180}")
	private String XML_180;
	
	/** The NetSuite package internal ID. */
	@Value("${XML_365}")
	private String XML_365;
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#executeJob()
	 */
	@Override
	public void executeJob() {
		LOGGER.info("AutoRenewalJobWorker-> Execute Job.....");
		//Retreive all the expireds jobs to validate with net suite data 
		List<JobPostDTO> jobsList = employerJobPostDAO.retreiveAllExpiredJobs();
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
					.contains(FEATURE_30)
					|| purchasedPackages
							.contains(FEATURE_90)
					|| purchasedPackages
							.contains(FEATURE_180)
					|| purchasedPackages
							.contains(FEATURE_365));
			// Verify the employer is applicable for free posting or not
			dto.setXmlStartEndDateEnabled(purchasedPackages
					.contains(XML_90)
					|| purchasedPackages.contains(XML_180)
					|| purchasedPackages.contains(XML_365));
		}
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.executeAutoRenewalJobWorker(jobsList);
		//send the mails here 
				EmailDTO emailDTO = new EmailDTO();

				emailDTO.setFromAddress(advanceWebAddress);
				emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.autorenew.failed.email.subject").trim());
				
		for (SchedulerDTO schedulerDTO : schedulerDTOList) {
			if (schedulerDTO.getFacilityId() > 0) {
				FacilityDTO mainFacilityDTO = facilityService
						.getParentFacility(schedulerDTO.getFacilityId());
				FacilityDTO facilityDTO = facilityService
						.getFacilityByFacilityId(schedulerDTO.getFacilityId());
				// Get all associated user for the above facility id
				List<FacilityDTO> admUserFacilities = facilityService
						.getUserFacilityDetails(mainFacilityDTO.getFacilityId());
				if (null != admUserFacilities && admUserFacilities.size() > 0) {
					// iterate each associated user for the above facility id
					// and
					// send mail
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
										&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_LISTING_FAILED_TO_AUTO_RENEW) {
									autoRenewFailMail(emailDTO, schedulerDTO,
											jsToAddress);
								}
							}
						} else {
							autoRenewFailMail(emailDTO, schedulerDTO,
									jsToAddress);
						}
					}
				}
				// if Mail not sent to the main Facility,send mail on interest
				if (!mainFacilityDTO.getFacilityId().equals(facilityDTO
						.getFacilityId())&& schedulerDTO.getFacilityId() > 0) {
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
											&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_LISTING_FAILED_TO_AUTO_RENEW) {
										autoRenewFailMail(emailDTO,
												schedulerDTO, jsToAddress);
									}
								}
							} else {
								autoRenewFailMail(emailDTO, schedulerDTO,
										jsToAddress);
							}
						}
					}
				}

			}

			LOGGER.info("ActiveJobsJobWorker-> Executed Job Successfully.....");
		}
	}

	/**
	 * Method helps to send mail for auto renew fail
	 * 
	 * @param emailDTO
	 * @param schedulerDTO
	 * @param jsToAddress
	 */
	private void autoRenewFailMail(EmailDTO emailDTO, SchedulerDTO schedulerDTO, InternetAddress[] jsToAddress) {
		StringBuffer autoRenewFailed;
		StringBuffer mailBody;
		int start;
		int end;
		
		emailDTO.setToAddress(jsToAddress);
		
		autoRenewFailed  = new StringBuffer();
		
		mailBody  = new StringBuffer(emailConfiguration.getProperty("employer.jobpost.autorenew.failed.email.body").trim());
		
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
		LOGGER.debug("Auto renewal fail mail send to :" +emailDTO.getToAddress());
		emailService.sendEmail(emailDTO);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#getJobName()
	 */
	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
