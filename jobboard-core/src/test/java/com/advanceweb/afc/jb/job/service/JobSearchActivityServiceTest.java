package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.ApplyJobDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.resume.ResumeService;

/**
 * <code> JobSearchActivityServiceTest </code> is a Test class for JobSearchActivity
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public class JobSearchActivityServiceTest extends ServiceTest {

	@Autowired
	public JobSearchActivity jobSearchActivity;
	
	@Autowired
	private MMEmailService emailService;
	
	@Autowired
	private ResumeService resumeService;
	
	/**
	 * The method helps to test the retrieving of job details by JobId  
	 * 	 
	 */
	@Test
	public void testViewJobDetails() {
			Long jobId = 13100L;
			SearchedJobDTO searchedJobDTO =jobSearchActivity.viewJobDetails(jobId);
			assertNotNull("View SearchedJob", searchedJobDTO);
	}
	
	/**
	 * The method helps to test the saving of job after applying.  
	 * 	 
	 */
	@Test
	public void testApplyJob() {
		try {
			Long jobId = 13100L;
			SearchedJobDTO searchedJobDTO = jobSearchActivity
					.viewJobDetails(jobId);
			/**
			 * Sending mail to employer
			 */
			EmailDTO employerEmailDTO = new EmailDTO();
			employerEmailDTO.setFromAddress("carrer@carrer.com");
			employerEmailDTO.setCcAddress(null);
			employerEmailDTO.setBccAddress(null);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress("jobseeker@carrer.com");
			employerEmailDTO.setToAddress(employerToAddress);
			employerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			employerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			employerEmailDTO.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();
			ResumeDTO resumeDTO = resumeService.fetchPublicResumeByUserId(2);
			assertNotNull("Public visibility Resume", resumeDTO);
			attachmentpaths.add(resumeDTO.getFilePath());
			employerEmailDTO.setAttachmentPaths(attachmentpaths);
			emailService.sendEmail(employerEmailDTO);

			/**
			 * Sending mail to job seeker
			 */
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			jobSeekerEmailDTO.setFromAddress("carrer@carrer.com");
			jobSeekerEmailDTO.setCcAddress(null);
			jobSeekerEmailDTO.setBccAddress(null);
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress("employer@carrer.com");
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			jobSeekerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			jobSeekerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			jobSeekerEmailDTO.setHtmlFormat(true);
			emailService.sendEmail(jobSeekerEmailDTO);

			/**
			 * saving the job in applied job in job seeker table
			 */
			int userId = 1;
			Date currentDate = new Date();
			byte isApplied = 1;
			ApplyJobDTO applyJobDTO = new ApplyJobDTO();
			applyJobDTO.setJobId(jobId.intValue());
			applyJobDTO.setUserId(userId);
			applyJobDTO.setCreateDate(currentDate);
			applyJobDTO.setAppliedDate(currentDate);
			applyJobDTO.setIsApplied(isApplied);
			jobSearchActivity.applyJob(applyJobDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
