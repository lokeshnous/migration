package com.advanceweb.afc.jb.employer.postjobs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.jb.test.ServiceTest;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

public class JobPostServiceTest extends ServiceTest{
	
	@Autowired
	private JobPostService employerJobPost;
	
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
		dto = employerJobPost.retrieveAllJobPost(1606,1,20);
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
		
		boolean repost = employerJobPost.repostJob(13101);
		Assert.assertTrue("Job Reposted Successfully", repost);
	}
	
	@Test
	public void testGetJobPostingPlansTest(){
		List<JobPostingPlanDTO> jobPostingPlanDTOList = employerJobPost.getJobPostingPlans();
		Assert.assertNotNull(jobPostingPlanDTOList);
	}
}
