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

	private final static String JOB_NAME = "AUTORENEAL_JOB";

	private static final Logger LOGGER = Logger.getLogger(AutoRenewalJobWorker.class);
	
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Autowired
	private MMEmailService emailService;
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;

	@Autowired
	private UserAlertService alertService;
	
	@Autowired
	private UserService userService;
	
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
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.executeAutoRenewalJobWorker(jobsList);
		//send the mails here 
				EmailDTO emailDTO = new EmailDTO();

				emailDTO.setFromAddress(advanceWebAddress);
				emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.autorenew.failed.email.subject").trim());
				
				for(SchedulerDTO schedulerDTO: schedulerDTOList){
					
					FacilityDTO mainFacilityDTO = facilityService.getParentFacility(schedulerDTO.getFacilityId());
//			        UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
//							.getUserId());
			        
			        // check for job is posted by Job owner and send mail on interest
					 if(schedulerDTO.getCreateUserId() !=  mainFacilityDTO.getUserId()){
			        	List<UserAlertDTO> alertDTOs = alertService.viewAlerts(schedulerDTO
								.getCreateUserId());
			        	 UserDTO mainuserDto = userService.getUserByUserId(schedulerDTO
									.getCreateUserId());
			        	InternetAddress[] jsToAddress = new InternetAddress[1];
			    		try {
			    			jsToAddress[0] = new InternetAddress(mainuserDto.getEmailId());
			    		} catch (AddressException jbex) {
			    			LOGGER.error("Error occured while geting InternetAddress reference",jbex);
			    		}
						if (null != alertDTOs && alertDTOs.size() > 0) {
							for (UserAlertDTO alertDTO : alertDTOs) {
								if (alertDTO.getAlertId() > 0
										&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_LISTING_FAILED_TO_AUTO_RENEW) {
									autoRenewFailMail(emailDTO,schedulerDTO, jsToAddress);
								}
							}
						} else {
							autoRenewFailMail(emailDTO,schedulerDTO, jsToAddress);
						}
			        }
					
			        // send mail to employer on interest
					List<UserAlertDTO> alertDTOs = alertService
							.viewAlerts(mainFacilityDTO.getUserId());
					 UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
								.getUserId());
					 InternetAddress[] jsToAddress = new InternetAddress[1];
			    		try {
			    			jsToAddress[0] = new InternetAddress(mainuserDto.getEmailId());
			    		} catch (AddressException jbex) {
			    			LOGGER.error("Error occured while geting InternetAddress reference",jbex);
			    		}
					if (null != alertDTOs && alertDTOs.size() > 0) {
						for (UserAlertDTO alertDTO : alertDTOs) {
							if (alertDTO.getAlertId() > 0
									&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_LISTING_FAILED_TO_AUTO_RENEW) {
								autoRenewFailMail(emailDTO,schedulerDTO, jsToAddress);
							}
						}
					} else {
						autoRenewFailMail(emailDTO,schedulerDTO, jsToAddress);
					}
					LOGGER.info("Scheduler : Job Expires Soon Scheduler completed .......");
				}
		
		LOGGER.info("ActiveJobsJobWorker-> Executed Job Successfully.....");
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
		emailService.sendEmail(emailDTO);
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
