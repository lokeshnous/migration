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

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
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
		dto = employerJobPost.retrieveAllJobPost(1606,1,20,"asc");
		Assert.assertTrue("Total Record Found", dto.isEmpty()?false:true);
	}
	@Test
	public void testRetrieveAllJobPostByStatus(){
		List <JobPostDTO> dto = new ArrayList<JobPostDTO>();
		dto = employerJobPost.retrieveAllJobByStatus("Active", 1606,1,20);
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
}
