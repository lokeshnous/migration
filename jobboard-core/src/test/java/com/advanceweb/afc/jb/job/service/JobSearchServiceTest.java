package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;

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
		SearchedJobDTO searchedJobDTO = jobSearchService.viewJobDetails(jobId);
		jobSearchService.fetchSavedOrAppliedJob(searchedJobDTO, userId);

	}

	/**
	 * The method helps to test the retrieving of job details by JobId
	 * 
	 */
	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void testViewJobDetails() {
		int jobId = 13100;
		SearchedJobDTO searchedJobDTO = jobSearchService.viewJobDetails(jobId);
		assertNotNull("View SearchedJob", searchedJobDTO);
	}

	/**
	 * create save or apply the job for logged in user
	 */
	@Ignore(NOT_READY_FOR_TEST)
	@Test
	public void saveOrApplyJob() {
		int jobId = 13100;
		Date currentDate = new Date();
		SearchedJobDTO searchedJobDTO = jobSearchService.viewJobDetails(jobId);
		AppliedJobDTO jobDTO = new AppliedJobDTO();
		JobPostDTO jpJob = new JobPostDTO();
		jpJob.setJobId(jobId);
		jobDTO.setJpJob(jpJob);
		jobDTO.setUserId(5);
		jobDTO.setJobTitle(searchedJobDTO.getJobTitle());
		jobDTO.setFacilityName(searchedJobDTO.getCompanyName());
		jobDTO.setCreateDt(currentDate.toString());
		jobDTO.setAppliedDt(currentDate.toString());
		jobDTO.setDeleteDt(null);
		assertTrue("saveOrApplyJob :", jobSearchService.saveOrApplyJob(jobDTO));
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
		AppliedJobDTO jobDTO = new AppliedJobDTO();
		SearchedJobDTO searchedJobDTO = jobSearchService.viewJobDetails(jobId);
		AppliedJobDTO appliedJobDTO = jobSearchService.fetchSavedOrAppliedJob(
				searchedJobDTO, userId);
		jobDTO = appliedJobDTO;
		jobDTO.setAppliedDt(currentDate.toString());
		assertTrue("updateSaveOrApplyJob :",
				jobSearchService.updateSaveOrApplyJob(jobDTO));
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
			SearchedJobDTO searchedJobDTO = jobSearchService
					.viewJobDetails(jobId);
			jobSearchService.saveJob(searchedJobDTO);
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
