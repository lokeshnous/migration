/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.UserAlertService;
import com.advanceweb.afc.jb.user.UserService;

/**
 * The class is used to send email to the employer containing information that they have no active job posting.
 * 
 */

@Service
@Qualifier("noActiveJobPostingJobWorker")
public class NoActiveJobPostingJobWorker implements JobWorker {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(NoActiveJobPostingJobWorker.class);
	
	/** The Constant JOB_NAME. */
	private static final String JOB_NAME = "SEND_MAIL";
	
	/** The job post service. */
	@Autowired
	private JobPostService jobPostService;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/** The facility service. */
	@Autowired
	private FacilityService facilityService;

	/** The alert service. */
	@Autowired
	private UserAlertService alertService;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#executeJob()
	 */
	@Override
	public void executeJob() {
		List<SchedulerDTO> schedulerDTO=facilityService.getAllFacilityList();
		List<SchedulerDTO> sendMailList=new ArrayList<SchedulerDTO>();
		List<DropDownDTO> companyList = new ArrayList<DropDownDTO>();
		
		for (SchedulerDTO dto : schedulerDTO) {
			companyList = populateDropdownsService.populateCompanyNames(
					dto.getFacilityId(), false);
			try{
			List<JobPostDTO> jobPostDTOList = jobPostService
					.retrieveAllJobByStatus(MMJBCommonConstants.POST_NEW_JOB,
							companyList, 0, 10);
			if (jobPostDTOList != null) {
				sendMailList.add(dto);
			}
			}catch(Exception e){
				LOGGER.error(e.getMessage(), e);
			}
		}
		for (SchedulerDTO dto : sendMailList) {
			
//			FacilityDTO mainFacilityDTO = facilityService.getParentFacility(dto.getFacilityId());
//	        UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
//					.getUserId());
	        
	        // check for job is posted by Job owner and send mail on interest
			/* if(dto.getUserId() !=  mainFacilityDTO.getUserId()){
	        	List<UserAlertDTO> alertDTOs = alertService.viewAlerts(dto
						.getUserId());
	        	UserDTO mainuserDto = userService.getUserByUserId(dto
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
								&& alertDTO.getAlertId() == MMJBCommonConstants.NO_ACTIVE_POSTINGS_ON_ADVANCE) {
							noActivePostingSendMail(dto, toAddress);
						}
					}
				} else {
					noActivePostingSendMail(dto,  toAddress);
				}
	        }*/
	        // send mail to employer on interest
			List<UserAlertDTO> alertDTOs = alertService
					.viewAlerts(dto.getUserId());
			UserDTO mainuserDto = userService.getUserByUserId(dto
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
							&& alertDTO.getAlertId() == MMJBCommonConstants.NO_ACTIVE_POSTINGS_ON_ADVANCE) {
						noActivePostingSendMail(dto, toAddress);
					}
				}
			} else {
				noActivePostingSendMail(dto, toAddress);
			}
		}


		LOGGER.info("NoActiveJobPostingJobWorker.-> Executed Job Successfully.....");
	}

	/**
	 * Method helps to send mail for n oActive Posting facilities
	 * 
	 * @param dto
	 * @param toAddress
	 */
	private void noActivePostingSendMail(SchedulerDTO dto, InternetAddress[] toAddress) {
		StringBuffer stringBuffer = new StringBuffer();
		
		EmailDTO emailDTO = new EmailDTO();
		InternetAddress[] ccAddress = new InternetAddress[1];
		try {
			ccAddress[0]=new InternetAddress(emailConfiguration.getProperty("rep.email.address").trim());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		emailDTO.setToAddress(toAddress);
		emailDTO.setCcAddress(ccAddress);
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.common.schedulers.jobs.JobWorker#getJobName()
	 */
	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
