package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.search.service.JobSearchService;

/**
 * <code> JobSearchServiceTest </code> is a Test class for
 * JobSearchService
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public class JobSearchServiceTest extends ServiceTest {

	@Autowired
	private JobSearchService jobSearchService;

	/*
	 * @Autowired private MMEmailService emailService;
	 */

	private static final Logger LOGGER = Logger
			.getLogger(JobSearchServiceTest.class);

	@Autowired
	private ResumeService resumeService;

	/**
	 * The method helps to test the retrieving of job details by JobId
	 * 
	 */
	@Test
	public void testViewJobDetails() {
		Long jobId = 13100L;
		SearchedJobDTO searchedJobDTO = jobSearchService.viewJobDetails(jobId);
		assertNotNull("View SearchedJob", searchedJobDTO);
	}

	/**
	 * The method helps to test the saving of job after applying.
	 * 
	 */
	@Ignore("Not Ready to Run")
	@Test
	public void testApplyJob() {
		try {
			int jobId = 13100;
			int userId = 30;
			SearchedJobDTO searchedJobDTO = jobSearchService
					.viewJobDetails(jobId);
			/**
			 * Sending mail to employer
			 */
			EmailDTO employerEmailDTO = new EmailDTO();
			employerEmailDTO.setFromAddress("merion@nousinfosystems.com");
			employerEmailDTO.setCcAddress(null);
			employerEmailDTO.setBccAddress(null);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress("mmnousinfo@gmail.com");
			employerEmailDTO.setToAddress(employerToAddress);
			employerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			employerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			employerEmailDTO.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();
			// TODO: Exception if resume not found
			ResumeDTO resumeDTO = resumeService
					.fetchPublicResumeByUserId(userId);
			assertNotNull("Public visibility Resume", resumeDTO);
			attachmentpaths.add(resumeDTO.getFilePath());
			employerEmailDTO.setAttachmentPaths(attachmentpaths);
			// emailService.sendEmail(employerEmailDTO);

			/**
			 * Sending mail to job seeker
			 */
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			jobSeekerEmailDTO.setFromAddress("merion@nousinfosystems.com");
			jobSeekerEmailDTO.setCcAddress(null);
			jobSeekerEmailDTO.setBccAddress(null);
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress("mmnousinfo@gmail.com");
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			jobSeekerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			jobSeekerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			jobSeekerEmailDTO.setHtmlFormat(true);
			// emailService.sendEmail(jobSeekerEmailDTO);

			/**
			 * saving the job in applied job in job seeker table
			 */
			Date currentDate = new Date();
			AppliedJobDTO applyJobDTO = new AppliedJobDTO();
			JobPostDTO jpJob = new JobPostDTO();
			jpJob.setJobId(jobId);
			applyJobDTO.setJpJob(jpJob);
			applyJobDTO.setUserId(userId);
			applyJobDTO.setCreateDt(currentDate.toString());
			applyJobDTO.setAppliedDt(currentDate.toString());
			applyJobDTO.setDeleteDt(null);
			jobSearchService.saveOrApplyJob(applyJobDTO);
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.info("testApplyJob Exception");
		}
	}

	/**
	 * Added for save the job task
	 * 
	 */
	// @Ignore("Not Reaady to test")
	@Test
	public void testSaveJob() {
		try {
			Boolean status = Boolean.TRUE;
			SearchedJobDTO searchedJobDTO = new SearchedJobDTO();
			searchedJobDTO.setUserID(8);
			searchedJobDTO.setJobID(10);
			searchedJobDTO.setCreatedDate(new Date());
			searchedJobDTO.setJobTitle("Project Manager");
			searchedJobDTO.setCompanyName("XYZ");
			jobSearchService.saveJob(searchedJobDTO);
			assertTrue("Test to save the job", status);
		} catch (Exception e) {
			LOGGER.info("testSaveJob Exception");
		}
	}

}