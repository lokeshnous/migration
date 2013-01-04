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
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.jb.test.ServiceTestBase;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

public class JobPostServiceTest extends ServiceTestBase{
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
	private JobPostDAO employerJobPostDAO;
	
	@Autowired
	private MMEmailService emailService;
	
	private static final String Q_USERNAME = "?userName";
	private static final String Q_JOBID = "?jobId";
	private static final String Q_COMPANYNAME = "?companyName";
	
	
	@Test
	public void testPostNewJob(){
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
	public void testEditJob(){
		JobPostDTO dto = new JobPostDTO();
		dto = employerJobPost.retrieveJobById(1606);
		Assert.assertEquals(dto.getJobId(), 1606);
	}
	@Test
	public void testRetrieveAllJobPost(){
		List <JobPostDTO> dto = new ArrayList<JobPostDTO>();
//		@TODO Verify the scheduler functionality after adding dropdownDTO
		List<DropDownDTO> dropdownDTO = new ArrayList<DropDownDTO>();
		dto = employerJobPost.retrieveAllJobPost(dropdownDTO,1,20,"asc");
		Assert.assertTrue("Total Record Found", dto.isEmpty()?false:true);
	}
	@Test
	public void testRetrieveAllJobPostByStatus(){
		List <JobPostDTO> dto = new ArrayList<JobPostDTO>();
//		@TODO Verify the scheduler functionality after adding dropdownDTO
		List<DropDownDTO> dropdownDTO = new ArrayList<DropDownDTO>();
		dto = employerJobPost.retrieveAllJobByStatus("Active",dropdownDTO,1,20);
		Assert.assertTrue("Total Record Found", dto.isEmpty()?false:true);
	}
	
	@Test
	public void testDeleteJobs(){
		
		boolean deleted = employerJobPost.deleteJob(13101, 1606);
		Assert.assertTrue("Data Deleted Successfully", deleted);
	}
	@Test
	public void testUpdateJobs(){
		
		boolean updated = employerJobPost.updateManageJob(false, 2, 13101, 1606);
		Assert.assertTrue("Data Updated Successfully", updated);
	}
	@Test
	public void testDeactivateJobs(){
		
		boolean deactivated = employerJobPost.deactivateJob(13101, 1606);
		Assert.assertTrue("Deactivated Successfully", deactivated);
	}
	
	@Test
	public void testRepostJobs(){
		
		boolean repost = employerJobPost.repostJob(13101, 30);
		Assert.assertTrue("Job Reposted Successfully", repost);
	}
	
	@Test
	public void testGetJobPostingPlansTest(){
		List<JobPostingPlanDTO> jobPostingPlanDTOList = employerJobPost.getJobPostingPlans();
		Assert.assertNotNull(jobPostingPlanDTOList);
	}
	
	@Test
	public void testJobsExpiringSoon(){
		
		List<SchedulerDTO> schedulerDTOList = employerJobPostDAO.retreiveActiveJobsExpireSoon();
		//send the mails here 
		EmailDTO emailDTO = new EmailDTO();
		StringBuffer jobExpireSoon = null;
		StringBuffer mailBody  = null;
		int start,end;
		InternetAddress[] jsToAddress = new InternetAddress[1];
		
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("employer.jobpost.expiresoon.email.subject").trim());
		
		for(SchedulerDTO schedulerDTO: schedulerDTOList){
			
			try {
				jsToAddress[0] = new InternetAddress("anilm@nousinfo.com");
			} catch (AddressException jbex) {
				LOGGER.error("Error occured while geting InternetAddress reference",jbex);
			}
			
			emailDTO.setToAddress(jsToAddress);
			
			jobExpireSoon  = new StringBuffer();
			
			mailBody  = new StringBuffer(emailConfiguration.getProperty("employer.jobpost.expiresoon.email.body").trim());
			
			//set the expire date in email body
			start = mailBody.toString()
					.indexOf("?expireDate");
			end = start + "?expireDate".length();
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
			
			//set the user name in table
			start = mailBody.toString()
					.indexOf("?expireDateTb");
			end = start + "?expireDateTb".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						schedulerDTO.getExpireDate());
			}
						
			jobExpireSoon.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			jobExpireSoon.append(mailBody);
			jobExpireSoon.append(emailConfiguration.getProperty("email.footer").trim());
			
			emailDTO.setBody(jobExpireSoon.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}
		LOGGER.info("Scheduler : Job Expires Soon Scheduler completed .......");
	}
	
	@Test
	public void testExpireEligibleJobs(){
		
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
				jsToAddress[0] = new InternetAddress("anilm@nousinfo.com");
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
			start = mailBody.toString()
					.indexOf("?empdashboardLink");
			end = start + "?empdashboardLink".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end,
						emailConfiguration.getProperty("employerer.dashboard.url").trim());
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
			
			expireJobPost.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			expireJobPost.append(mailBody);
			expireJobPost.append(emailConfiguration.getProperty("email.footer").trim());
			
			emailDTO.setBody(expireJobPost.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}
		LOGGER.info("Scheduler : Expire Eligible Job Posts scheduler completed .......");
	}
	
	
	@Test
	public void testAutoRenewJob(){
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
		
		LOGGER.info("ActiveJobsJobWorker-> Executed Job Successfully.....");
	}
	
	@Test 
	public void noActiveJobPosting(){
		
		List<SchedulerDTO> schedulerDTO=facilityService.getAllFacilityList();
		List<SchedulerDTO> sendMailList=new ArrayList<SchedulerDTO>();
//		@TODO Verify the scheduler functionality after adding dropdownDTO
		List<DropDownDTO> dropdownDTO = new ArrayList<DropDownDTO>();
		for(SchedulerDTO dto:schedulerDTO){
			List<JobPostDTO>jobPostDTOList=employerJobPost.retrieveAllJobByStatus(MMJBCommonConstants.POST_NEW_JOB, dropdownDTO, 0, 10);
		if(jobPostDTOList!=null){
			sendMailList.add(dto);
		}
		}
		for(SchedulerDTO dto:sendMailList){
		StringBuffer stringBuffer = new StringBuffer();
		InternetAddress[] toAddress = new InternetAddress[1];
		InternetAddress[] ccAddress = new InternetAddress[1];
		try {
			toAddress[0] = new InternetAddress("anilm@nousinfo.com");
			ccAddress[0]=new InternetAddress("anilm@nousinfo.com");
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		EmailDTO emailDTO = new EmailDTO();
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
		break;
		}
		LOGGER.info("NoActiveJobPostingJobWorker.-> Executed Job Successfully.....");
	}
	
}
