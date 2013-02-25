package com.advanceweb.afc.jb.employer.postjobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.UserAlertService;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.jb.test.ServiceTestBase;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */

public class JobPostServiceTest extends ServiceTestBase {
	private static final Logger LOGGER = Logger
			.getLogger(JobPostServiceTest.class);

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;

	@Autowired
	private JobPostService employerJobPost;

	@Autowired
	private JobPostService jobPostService;
	
	@Autowired
	private JobPostDAO employerJobPostDAO;
	@Autowired
	private UserAlertService alertService;

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	private static final String Q_USERNAME = "?userName";
	private static final String Q_JOBID = "?jobId";
	private static final String Q_COMPANYNAME = "?companyName";

	@Test
	public void testPostNewJob() {
		JobPostDTO dto = new JobPostDTO();
		dto.setApplicationMethod("ApplyToEMail");
		dto.setApplyEmail("www.google.com");
		dto.setAutoRenew(true);
		dto.setbHideCity(true);
		dto.setbHideCompName(true);
		dto.setbHideCountry(true);
		dto.setbHideZipCode(true);
		dto.setBrandTemplate(2);
		dto.setCompanyName("Nous");
		dto.setCustomerNo("1234");
		dto.setDisCompanyName("Nous");
		dto.setEmploymentType("");
		dto.setJobCity("ABBEVILLE");
		dto.setJobCountry("US");
		dto.setJobDesc("Job Description");
		dto.setJobId(0);
		dto.setJobNumber("NT112262");
		dto.setJobOwner("Job Owner");
		dto.setJobPostingType("2");
		dto.setJobState("SC");
		dto.setJobTitle("Job Title");
		dto.setJobZip("29620");
		dto.setReqSkills("Java/J2ee");
		dto.setTrackPixel("Tracking Pixel");

		boolean bSaved = employerJobPost.savePostJob(dto);
		Assert.assertTrue("Data Saved Successfully", bSaved);

	}

	@Test
	public void testEditJob() {
		JobPostDTO dto = new JobPostDTO();
		dto = employerJobPost.retrieveJobById(1606);
		Assert.assertEquals(dto.getJobId(), 1606);
	}

	@Test
	public void testRetrieveAllJobPost() {
		List<JobPostDTO> dto = new ArrayList<JobPostDTO>();
		// @TODO Verify the scheduler functionality after adding dropdownDTO
		List<DropDownDTO> dropdownDTO = new ArrayList<DropDownDTO>();
		dto = employerJobPost.retrieveAllJobPost(dropdownDTO, 1, 20, "asc");
		Assert.assertTrue("Total Record Found", dto.isEmpty() ? false : true);
	}

	@Test
	public void testRetrieveAllJobPostByStatus() {
		List<JobPostDTO> dto = new ArrayList<JobPostDTO>();
		// @TODO Verify the scheduler functionality after adding dropdownDTO
		List<DropDownDTO> dropdownDTO = new ArrayList<DropDownDTO>();
		dto = employerJobPost.retrieveAllJobByStatus("Active", dropdownDTO, 1,
				20);
		Assert.assertTrue("Total Record Found", dto.isEmpty() ? false : true);
	}

	@Test
	public void testDeleteJobs() {

		boolean deleted = employerJobPost.deleteJob(13101, 1606);
		Assert.assertTrue("Data Deleted Successfully", deleted);
	}

	@Test
	public void testUpdateJobs() {

		boolean updated = employerJobPost
				.updateManageJob(false, 2, 13101, 1606);
		Assert.assertTrue("Data Updated Successfully", updated);
	}

	@Test
	public void testDeactivateJobs() {

		boolean deactivated = employerJobPost.deactivateJob(13101, 1606);
		Assert.assertTrue("Deactivated Successfully", deactivated);
	}

	@Test
	public void testRepostJobs() {

		boolean repost = employerJobPost.repostJob(13101, 30);
		Assert.assertTrue("Job Reposted Successfully", repost);
	}

	@Test
	public void testGetJobPostingPlansTest() {
		List<JobPostingPlanDTO> jobPostingPlanDTOList = employerJobPost
				.getJobPostingPlans();
		Assert.assertNotNull(jobPostingPlanDTOList);
	}

	@Test
	public void testScheduleJobsExpireSoon() {
		// send the mails here
		SchedulerDTO schedulerDTO = new SchedulerDTO();
		schedulerDTO.setFirstName("FN");
		schedulerDTO.setLastName("LN");
		schedulerDTO.setCompanyName("CompN");
		schedulerDTO.setJobId(153737);
		schedulerDTO.setFacilityId(2656);
		schedulerDTO.setUserId(157504);
		schedulerDTO.setExpireDate("2013-02-02 18:12:19");
		EmailDTO emailDTO = new EmailDTO();

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"employer.jobpost.expiresoon.email.subject").trim());
		FacilityDTO mainFacilityDTO = facilityService
				.getParentFacility(schedulerDTO.getFacilityId());
		// UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
		// .getUserId());

		// check for job is posted by Job owner and send mail on interest
		if (schedulerDTO.getUserId() != mainFacilityDTO.getUserId()) {
			List<UserAlertDTO> alertDTOs = alertService.viewAlerts(schedulerDTO
					.getUserId());
			UserDTO mainuserDto = userService.getUserByUserId(schedulerDTO
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
		}
		// send mail to employer on interest
		List<UserAlertDTO> alertDTOs = alertService.viewAlerts(mainFacilityDTO
				.getUserId());
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

		LOGGER.info("Scheduler : Job Expires Soon Scheduler completed .......");
	}

	private void sendExpiringSoonMail(EmailDTO emailDTO,
			InternetAddress[] jsToAddress, SchedulerDTO schedulerDTO) {
		StringBuffer jobPostExpiresSoon;
		StringBuffer mailBody;
		int start;
		int end;
		// try {
		// jsToAddress[0] = new InternetAddress(schedulerDTO.getEmailId());
		// // TODO: remove the hard code values
		// //jsToAddress[0] = new InternetAddress("pramod1356@gmail.com");
		// } catch (AddressException jbex) {
		// LOGGER.error("Error occured while geting InternetAddress reference",jbex);
		// }
		emailDTO.setToAddress(jsToAddress);

		jobPostExpiresSoon = new StringBuffer();

		mailBody = new StringBuffer(emailConfiguration.getProperty(
				"employer.jobpost.expiresoon.email.body").trim());

		// set the expire date in email body
		start = mailBody.toString().indexOf("?expireDate");
		end = start + "?expireDate".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getExpireDate());
		}

		// set the company name in table
		start = mailBody.toString().indexOf("?userName");
		end = start + "?userName".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getFirstName() + " "
					+ schedulerDTO.getLastName());
		}

		// set the company name in table
		start = mailBody.toString().indexOf("?jobId");
		end = start + "?jobId".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					String.valueOf(schedulerDTO.getJobId()));
		}

		// set the expire date in table
		start = mailBody.toString().indexOf("?companyName");
		end = start + "?companyName".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getCompanyName());
		}

		// set the user name in table
		start = mailBody.toString().indexOf("?expireDateTb");
		end = start + "?expireDateTb".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getExpireDate());
		}
		start = mailBody.toString().indexOf("?empdashboardLink");
		end = start + "?empdashboardLink".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					emailConfiguration.getProperty("employerer.dashboard.url")
							.trim());
		}
		jobPostExpiresSoon.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		jobPostExpiresSoon.append(mailBody);
		jobPostExpiresSoon.append(emailConfiguration
				.getProperty("email.footer").trim());

		emailDTO.setBody(jobPostExpiresSoon.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}
	
	@Test
	public void testScheduleNoactiveJobposting() {

		List<SchedulerDTO> schedulerDTO=facilityService.getAllFacilityList();
		List<SchedulerDTO> sendMailList=new ArrayList<SchedulerDTO>();
		List<DropDownDTO> companyList = new ArrayList<DropDownDTO>();
		
		for (SchedulerDTO dto : schedulerDTO) {
			try{				
			companyList = populateDropdownsService.populateCompanyNames(
					dto.getFacilityId(), false);
			if(dto.getFacilityId() == 4665){
				List<JobPostDTO> jobPostDTOList = jobPostService
						.retrieveAllJobByStatus(MMJBCommonConstants.POST_NEW_JOB,
								companyList, 0, 10);
				if (jobPostDTOList != null) {
					sendMailList.add(dto);
				}
			}
			}catch (Exception e) {
				LOGGER.debug(e.getMessage(), e);
			}
		}
		for (SchedulerDTO dto : sendMailList) {
			
//			FacilityDTO mainFacilityDTO = facilityService.getParentFacility(dto.getFacilityId());
//	        UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
//					.getUserId());
	        
	        // check for job is posted by Job owner and send mail on interest
			 /*if(dto.getUserId() !=  mainFacilityDTO.getUserId()){
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
//						noActivePostingSendMail(dto, toAddress);
					}
				}
			} else {
//				noActivePostingSendMail(dto, toAddress);
			}
		}


		LOGGER.info("NoActiveJobPostingJobWorker.-> Executed Job Successfully.....");
	
	}
	
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

	@Test
	public void testScheduleautoRenewJobsExpire() {
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
		SchedulerDTO schedulerDTO = new SchedulerDTO();
		for (SchedulerDTO dto : schedulerDTOList) {
			if(dto.getJobId() == 97545){
				schedulerDTO = dto;
			}
		}
		EmailDTO emailDTO = new EmailDTO();

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"employer.jobpost.autorenew.failed.email.subject").trim());
//		SchedulerDTO schedulerDTO = new SchedulerDTO();
//		schedulerDTO.setFirstName("FN");
//		schedulerDTO.setLastName("LN");
//		schedulerDTO.setCompanyName("CompN");
//		schedulerDTO.setJobId(97545);
//		schedulerDTO.setFacilityId(4665);
//		schedulerDTO.setUserId(141431);
		// schedulerDTO.setExpireDate("2013-01-25 18:12:19");

		FacilityDTO mainFacilityDTO = facilityService
				.getParentFacility(schedulerDTO.getFacilityId());
//		 UserDTO userDto = userService.getUserByUserId(mainFacilityDTO
//		 .getUserId());

		// check for job is posted by Job owner and send mail on interest
		if (schedulerDTO.getCreateUserId() != mainFacilityDTO.getUserId()) {
			List<UserAlertDTO> alertDTOs = alertService.viewAlerts(schedulerDTO
					.getCreateUserId());
			UserDTO mainuserDto = userService.getUserByUserId(schedulerDTO
					.getCreateUserId());
			InternetAddress[] jsToAddress = new InternetAddress[1];
			try {
				jsToAddress[0] = new InternetAddress(mainuserDto.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error(
						"Error occured while geting InternetAddress reference",
						jbex);
			}
			if (null != alertDTOs && alertDTOs.size() > 0) {
				for (UserAlertDTO alertDTO : alertDTOs) {
					if (alertDTO.getAlertId() > 0
							&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_LISTING_FAILED_TO_AUTO_RENEW) {
//						autoRenewFailMail(emailDTO, schedulerDTO, jsToAddress);
					}
				}
			} else {
//				autoRenewFailMail(emailDTO, schedulerDTO, jsToAddress);
			}
		}

		// send mail to employer on interest
		List<UserAlertDTO> alertDTOs = alertService.viewAlerts(mainFacilityDTO
				.getUserId());
		UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
				.getUserId());
		InternetAddress[] jsToAddress = new InternetAddress[1];
		try {
			jsToAddress[0] = new InternetAddress(mainuserDto.getEmailId());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		if (null != alertDTOs && alertDTOs.size() > 0) {
			for (UserAlertDTO alertDTO : alertDTOs) {
				if (alertDTO.getAlertId() > 0
						&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_LISTING_FAILED_TO_AUTO_RENEW) {
//					autoRenewFailMail(emailDTO, schedulerDTO, jsToAddress);
				}
			}
		} else {
//			autoRenewFailMail(emailDTO, schedulerDTO, jsToAddress);
		}
		LOGGER.info("Scheduler : Job Expires Soon Scheduler completed .......");

	}

	@Test
	public void testScheduleJobsExpire() {
		int facilityId = 4665;
		int userId = 141431;
		SchedulerDTO schedulerDTO = new SchedulerDTO();
		schedulerDTO.setFirstName("FN");
		schedulerDTO.setLastName("LN");
		schedulerDTO.setCompanyName("CompN");
		schedulerDTO.setJobId(97545);

		EmailDTO emailDTO = new EmailDTO();

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"employer.jobpost.expired.email.subject").trim());
		FacilityDTO mainFacilityDTO = facilityService
				.getParentFacility(facilityId);
		// UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
		// .getUserId());

		// check for job is posted by Job owner and send mail on interest
		if (userId != mainFacilityDTO.getUserId()) {
			List<UserAlertDTO> alertDTOs = alertService.viewAlerts(userId);
			UserDTO mainuserDto = userService.getUserByUserId(userId);
			InternetAddress[] jsToAddress = new InternetAddress[1];
			try {
				jsToAddress[0] = new InternetAddress(mainuserDto.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error(
						"Error occured while geting InternetAddress reference",
						jbex);
			}
			if (null != alertDTOs && alertDTOs.size() > 0) {
				for (UserAlertDTO alertDTO : alertDTOs) {
					if (alertDTO.getAlertId() > 0
							&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_HAS_EXPIRED) {
//						expireJobSendMail(emailDTO, schedulerDTO, jsToAddress);
					}
				}
			} else {
//				expireJobSendMail(emailDTO, schedulerDTO, jsToAddress);
			}
		}
		// send mail to employer on interest
		List<UserAlertDTO> alertDTOs = alertService.viewAlerts(mainFacilityDTO
				.getUserId());
		UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
				.getUserId());
		InternetAddress[] jsToAddress = new InternetAddress[1];
		try {
			jsToAddress[0] = new InternetAddress(mainuserDto.getEmailId());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		if (null != alertDTOs && alertDTOs.size() > 0) {
			for (UserAlertDTO alertDTO : alertDTOs) {
				if (alertDTO.getAlertId() > 0
						&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_HAS_EXPIRED) {
//					expireJobSendMail(emailDTO, schedulerDTO, jsToAddress);
				}
			}
		} else {
//			expireJobSendMail(emailDTO, schedulerDTO, jsToAddress);
		}
	}

	private void autoRenewFailMail(EmailDTO emailDTO,
			SchedulerDTO schedulerDTO, InternetAddress[] jsToAddress) {
		StringBuffer autoRenewFailed;
		StringBuffer mailBody;
		int start;
		int end;

		emailDTO.setToAddress(jsToAddress);

		autoRenewFailed = new StringBuffer();

		mailBody = new StringBuffer(emailConfiguration.getProperty(
				"employer.jobpost.autorenew.failed.email.body").trim());

		// set the company name in table
		start = mailBody.toString().indexOf("?userName");
		end = start + "?userName".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getFirstName() + " "
					+ schedulerDTO.getLastName());
		}

		// set the company name in table
		start = mailBody.toString().indexOf("?jobId");
		end = start + "?jobId".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					String.valueOf(schedulerDTO.getJobId()));
		}

		// set the expire date in table
		start = mailBody.toString().indexOf("?companyName");
		end = start + "?companyName".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getCompanyName());
		}
		start = mailBody.toString().indexOf("?empdashboardLink");
		end = start + "?empdashboardLink".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					emailConfiguration.getProperty("employerer.dashboard.url")
							.trim());
		}
		autoRenewFailed.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		autoRenewFailed.append(mailBody);
		autoRenewFailed.append(emailConfiguration.getProperty("email.footer")
				.trim());

		emailDTO.setBody(autoRenewFailed.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}

	private void expireJobSendMail(EmailDTO emailDTO,
			SchedulerDTO schedulerDTO, InternetAddress[] jsToAddress) {

		StringBuffer expireJobPost;
		StringBuffer mailBody;
		int start;
		int end;
		emailDTO.setToAddress(jsToAddress);

		expireJobPost = new StringBuffer();

		mailBody = new StringBuffer(emailConfiguration.getProperty(
				"employer.jobpost.expired.email.body").trim());

		// set the company name in table
		start = mailBody.toString().indexOf(Q_USERNAME);
		end = start + Q_USERNAME.length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getFirstName() + " "
					+ schedulerDTO.getLastName());
		}

		// set the company name in table
		start = mailBody.toString().indexOf(Q_JOBID);
		end = start + Q_JOBID.length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					String.valueOf(schedulerDTO.getJobId()));
		}

		// set the expire date in table
		start = mailBody.toString().indexOf(Q_COMPANYNAME);
		end = start + Q_COMPANYNAME.length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getCompanyName());
		}
		start = mailBody.toString().indexOf("?empdashboardLink");
		end = start + "?empdashboardLink".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					emailConfiguration.getProperty("employerer.dashboard.url")
							.trim());
		}
		expireJobPost.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		expireJobPost.append(mailBody);
		expireJobPost.append(emailConfiguration.getProperty("email.footer")
				.trim());

		emailDTO.setBody(expireJobPost.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}

	@Test
	public void testJobsExpiringSoon() {
		// expire the jobs if eligible to expire
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO
				.executeExpireJobs();
		// send the mails here
		EmailDTO emailDTO = new EmailDTO();

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"employer.jobpost.expired.email.subject").trim());

		for (SchedulerDTO schedulerDTO : schedulerDTOList) {
			List<UserAlertDTO> alertDTOs = alertService.viewAlerts(schedulerDTO
					.getUserId());
			if (null != alertDTOs && alertDTOs.size() > 0) {
				for (UserAlertDTO alertDTO : alertDTOs) {
					if (alertDTO.getAlertId() > 0
							&& alertDTO.getAlertId() == MMJBCommonConstants.YOUR_JOB_HAS_EXPIRED) {
//						expireJobSendMail(emailDTO, schedulerDTO);
					}
				}
			} else {
//				expireJobSendMail(emailDTO, schedulerDTO);
			}
		}
	}

	private void expireJobSendMail(EmailDTO emailDTO, SchedulerDTO schedulerDTO) {

		InternetAddress[] jsToAddress = new InternetAddress[1];
		StringBuffer expireJobPost;
		StringBuffer mailBody;
		int start;
		int end;
		try {
			jsToAddress[0] = new InternetAddress(schedulerDTO.getEmailId());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}

		emailDTO.setToAddress(jsToAddress);

		expireJobPost = new StringBuffer();

		mailBody = new StringBuffer(emailConfiguration.getProperty(
				"employer.jobpost.expired.email.body").trim());

		// set the company name in table
		start = mailBody.toString().indexOf(Q_USERNAME);
		end = start + Q_USERNAME.length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getFirstName() + " "
					+ schedulerDTO.getLastName());
		}

		// set the company name in table
		start = mailBody.toString().indexOf(Q_JOBID);
		end = start + Q_JOBID.length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					String.valueOf(schedulerDTO.getJobId()));
		}

		// set the expire date in table
		start = mailBody.toString().indexOf(Q_COMPANYNAME);
		end = start + Q_COMPANYNAME.length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end, schedulerDTO.getCompanyName());
		}
		start = mailBody.toString().indexOf("?empdashboardLink");
		end = start + "?empdashboardLink".length();
		if (start > 0 && end > 0) {
			mailBody.replace(start, end,
					emailConfiguration.getProperty("employerer.dashboard.url")
							.trim());
		}
		expireJobPost.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		expireJobPost.append(mailBody);
		expireJobPost.append(emailConfiguration.getProperty("email.footer")
				.trim());

		emailDTO.setBody(expireJobPost.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}

	@Test
	public void testExpireEligibleJobs() {

		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO
				.executeExpireJobs();
		// send the mails here
		EmailDTO emailDTO = new EmailDTO();
		StringBuffer expireJobPost = null;
		StringBuffer mailBody = null;
		int start, end;
		InternetAddress[] jsToAddress = new InternetAddress[1];

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"employer.jobpost.expired.email.subject").trim());

		for (SchedulerDTO schedulerDTO : schedulerDTOList) {

			try {
				jsToAddress[0] = new InternetAddress("anilm@nousinfo.com");
			} catch (AddressException jbex) {
				LOGGER.error(
						"Error occured while geting InternetAddress reference",
						jbex);
			}

			emailDTO.setToAddress(jsToAddress);

			expireJobPost = new StringBuffer();

			mailBody = new StringBuffer(emailConfiguration.getProperty(
					"employer.jobpost.expired.email.body").trim());

			// set the company name in table
			start = mailBody.toString().indexOf(Q_USERNAME);
			end = start + Q_USERNAME.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, schedulerDTO.getFirstName() + " "
						+ schedulerDTO.getLastName());
			}
			start = mailBody.toString().indexOf("?empdashboardLink");
			end = start + "?empdashboardLink".length();
			if (start > 0 && end > 0) {
				mailBody.replace(
						start,
						end,
						emailConfiguration.getProperty(
								"employerer.dashboard.url").trim());
			}
			// set the company name in table
			start = mailBody.toString().indexOf(Q_JOBID);
			end = start + Q_JOBID.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						String.valueOf(schedulerDTO.getJobId()));
			}

			// set the expire date in table
			start = mailBody.toString().indexOf(Q_COMPANYNAME);
			end = start + Q_COMPANYNAME.length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, schedulerDTO.getCompanyName());
			}

			expireJobPost.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			expireJobPost.append(mailBody);
			expireJobPost.append(emailConfiguration.getProperty("email.footer")
					.trim());

			emailDTO.setBody(expireJobPost.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}
		LOGGER.info("Scheduler : Expire Eligible Job Posts scheduler completed .......");
	}

	@Test
	public void testAutoRenewJob() {
		LOGGER.info("AutoRenewalJobWorker-> Execute Job.....");
		// Retreive all the expireds jobs to validate with net suite data
		List<JobPostDTO> jobsList = employerJobPostDAO.retreiveAllExpiredJobs();
		// Calling net suite to check whether the employer is featured or not
		// And to know, whether the employer is applicable for free job posting
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
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO
				.executeAutoRenewalJobWorker(jobsList);
		// send the mails here
		EmailDTO emailDTO = new EmailDTO();
		StringBuffer autoRenewFailed = null;
		StringBuffer mailBody = null;
		int start, end;
		InternetAddress[] jsToAddress = new InternetAddress[1];

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"employer.jobpost.autorenew.failed.email.subject").trim());

		for (SchedulerDTO schedulerDTO : schedulerDTOList) {

			try {
				jsToAddress[0] = new InternetAddress(schedulerDTO.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error(
						"Error occured while geting InternetAddress reference",
						jbex);
			}

			emailDTO.setToAddress(jsToAddress);

			autoRenewFailed = new StringBuffer();

			mailBody = new StringBuffer(emailConfiguration.getProperty(
					"employer.jobpost.autorenew.failed.email.body").trim());

			// set the company name in table
			start = mailBody.toString().indexOf("?userName");
			end = start + "?userName".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, schedulerDTO.getFirstName() + " "
						+ schedulerDTO.getLastName());
			}

			// set the company name in table
			start = mailBody.toString().indexOf("?jobId");
			end = start + "?jobId".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						String.valueOf(schedulerDTO.getJobId()));
			}

			// set the expire date in table
			start = mailBody.toString().indexOf("?companyName");
			end = start + "?companyName".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, schedulerDTO.getCompanyName());
			}
			start = mailBody.toString().indexOf("?empdashboardLink");
			end = start + "?empdashboardLink".length();
			if (start > 0 && end > 0) {
				mailBody.replace(
						start,
						end,
						emailConfiguration.getProperty(
								"employerer.dashboard.url").trim());
			}
			autoRenewFailed.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			autoRenewFailed.append(mailBody);
			autoRenewFailed.append(emailConfiguration.getProperty(
					"email.footer").trim());

			emailDTO.setBody(autoRenewFailed.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}

		LOGGER.info("ActiveJobsJobWorker-> Executed Job Successfully.....");
	}

	@Test
	public void noActiveJobPosting() {

		List<SchedulerDTO> schedulerDTO = facilityService.getAllFacilityList();
		List<SchedulerDTO> sendMailList = new ArrayList<SchedulerDTO>();
		// @TODO Verify the scheduler functionality after adding dropdownDTO
		List<DropDownDTO> dropdownDTO = new ArrayList<DropDownDTO>();
		for (SchedulerDTO dto : schedulerDTO) {
			List<JobPostDTO> jobPostDTOList = employerJobPost
					.retrieveAllJobByStatus(MMJBCommonConstants.POST_NEW_JOB,
							dropdownDTO, 0, 10);
			if (jobPostDTOList != null) {
				sendMailList.add(dto);
			}
		}
		for (SchedulerDTO dto : sendMailList) {
			StringBuffer stringBuffer = new StringBuffer();
			InternetAddress[] toAddress = new InternetAddress[1];
			InternetAddress[] ccAddress = new InternetAddress[1];
			try {
				toAddress[0] = new InternetAddress("anilm@nousinfo.com");
				ccAddress[0] = new InternetAddress("anilm@nousinfo.com");
			} catch (AddressException jbex) {
				LOGGER.error(
						"Error occured while geting InternetAddress reference",
						jbex);
			}
			EmailDTO emailDTO = new EmailDTO();
			emailDTO.setToAddress(toAddress);
			emailDTO.setCcAddress(ccAddress);
			emailDTO.setFromAddress(advanceWebAddress);
			emailDTO.setSubject(emailConfiguration.getProperty(
					"noActiveJobPostings.subject").trim());

			StringBuffer mailBody = new StringBuffer(emailConfiguration
					.getProperty("no.active.job.posting.mail.body").trim());
			int start, end;
			start = mailBody.indexOf("?user_name");
			end = start + "?user_name".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						dto.getFirstName() + " " + dto.getLastName());
			}
			start = mailBody.toString().indexOf("?company_name");
			end = start + "?company_name".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, dto.getCompanyName());
			}
			start = mailBody.toString().indexOf("?empdashboardLink");
			end = start + "?empdashboardLink".length();
			if (start > 0 && end > 0) {
				mailBody.replace(
						start,
						end,
						emailConfiguration.getProperty(
								"employerer.dashboard.url").trim());
			}
			stringBuffer.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			stringBuffer.append(mailBody);
			stringBuffer.append(emailConfiguration.getProperty("email.footer")
					.trim());
			emailDTO.setBody(stringBuffer.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
			break;
		}
		LOGGER.info("NoActiveJobPostingJobWorker.-> Executed Job Successfully.....");
	}

}
