package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.jb.test.ServiceTest;

/**
 * <code> JobSearchServiceTest </code> is a Test class for JobSearchService
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public class JobSearchServiceTest extends ServiceTest {

	private static final String NOT_READY_FOR_TEST = "Not Ready For Test";
	@Autowired
	private JobSearchService jobSearchService;

	/**
	 * validating job for save/apply.
	 */
//	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void fetchSavedOrAppliedJob() {
		int userId = 5;
		int jobId = 13100;
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		jobSearchService.fetchSavedOrAppliedJob(jobDTO, userId);

	}

	/**
	 * The method helps to test the retrieving of job details by JobId
	 * 
	 */
	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void testViewJobDetails() {
		int jobId = 13100;
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		assertNotNull("View SearchedJob", jobDTO);
	}

	/**
	 * create save or apply the job for logged in user
	 */
	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void saveOrApplyJob() {
		int jobId = 13100;
		Date currentDate = new Date();
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		AppliedJobDTO appliedJobDTO = new AppliedJobDTO();
		JobPostDTO jpJob = new JobPostDTO();
		jpJob.setJobId(jobId);
		appliedJobDTO.setJpJob(jpJob);
		appliedJobDTO.setUserId(5);
		appliedJobDTO.setJobTitle(jobDTO.getJobTitle());
		appliedJobDTO.setFacilityName(jobDTO.getCompanyNameDisp());
		appliedJobDTO.setCreateDt(currentDate.toString());
		appliedJobDTO.setAppliedDt(currentDate.toString());
		appliedJobDTO.setDeleteDt(null);
		assertTrue("saveOrApplyJob :", jobSearchService.saveOrApplyJob(appliedJobDTO));
	}

	/**
	 * update save or apply the job for logged in user
	 */
	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void updateSaveOrApplyJob() {
		int jobId = 13100;
		int userId = 5;
		Date currentDate = new Date();
		AppliedJobDTO appliedJobDTO = new AppliedJobDTO();
		JobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
		AppliedJobDTO jobDTO2 = jobSearchService.fetchSavedOrAppliedJob(
				jobDTO, userId);
		appliedJobDTO = jobDTO2;
		appliedJobDTO.setAppliedDt(currentDate.toString());
		assertTrue("updateSaveOrApplyJob :",
				jobSearchService.updateSaveOrApplyJob(appliedJobDTO));
	}

	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 */
	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void saveJob() {
		int jobId = 13100;
		boolean status = true;
		try {
			JobDTO jobDTO = jobSearchService
					.viewJobDetails(jobId);
			jobSearchService.saveJob(jobDTO);
		} catch (Exception e) {
			status = false;
		}
		assertTrue("saveJob :", status);
	}

	/**
	 * Fetch the apply type of job
	 * 
	 */
	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void applyJobDetails() {
		int jobId = 13100;
		JobApplyTypeDTO jobApplyTypeDTO = jobSearchService
				.applyJobDetails(jobId);
		assertNotNull("applyJobDetails", jobApplyTypeDTO);
	}

}
