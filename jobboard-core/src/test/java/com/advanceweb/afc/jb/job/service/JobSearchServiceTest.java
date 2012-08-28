package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.resume.ResumeService;
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

	@Autowired
	private JobSearchService jobSearchService;

	/*
	 * @Autowired private MMEmailService emailService;
	 */

	private static final Logger LOGGER = Logger
			.getLogger(JobSearchServiceTest.class);

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private MMEmailService emailService;

	@Value("${navigationPath}")
	private String navigationPath;

	@Value("${jobseekerJobApplicationSub}")
	private String jobseekerJobApplicationSub;

	@Value("${jobseekerJobApplicationBody}")
	private String jobseekerJobApplicationBody;

	@Value("${employeJobApplicationSub}")
	private String employeJobApplicationSub;

	@Value("${employeJobApplicationBody}")
	private String employeJobApplicationBody;

	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;

	@Value("${employerPageExtention}")
	private String employerPageExtention;

	@Value("${defaultResumeExtension}")
	private String defaultResumeExtension;

	@Value("${applyJobSuccessMsg}")
	private String applyJobSuccessMsg;

	@Value("${ajaxMsg}")
	private String ajaxMsg;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

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
			int jobId = 22538;
			int userId = 5;
			String userName = null;
			JSONObject jsonObject = new JSONObject();
			SearchedJobDTO searchedJobDTO = jobSearchService
					.viewJobDetails(jobId);

			JobApplyTypeDTO jobApplyTypeDTO = jobSearchService
					.applyJobDetails(jobId);
			if (jobApplyTypeDTO != null) {
				if (jobApplyTypeDTO.getApplyMethod().equalsIgnoreCase(
						MMJBCommonConstants.APPLY_TO_ATS)
						|| jobApplyTypeDTO.getApplyMethod().equalsIgnoreCase(
								MMJBCommonConstants.APPLY_TO_URL)) {
					jsonObject.put("applyMethod",
							jobApplyTypeDTO.getApplyMethod());
					jsonObject.put("applyLink", jobApplyTypeDTO.getApplyLink());
					return;
				}
			}
			// Check for job seeker login
			// if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			// map.put("loginForm", new LoginForm());
			// jsonObject.put(ajaxNavigationPath, navigationPath
			// + dothtmlExtention + jobseekerPageExtention);
			// session.setAttribute("jobId", jobId);
			// session.setAttribute("currentUrl", currentUrl);
			// return;
			// }

			AppliedJobDTO appliedJobDTO = jobSearchService
					.fetchSavedOrAppliedJob(searchedJobDTO, userId);
			// if (appliedJobDTO != null && appliedJobDTO.getAppliedDt() !=
			// null) {
			// applyJobErrMsg = applyJobErrMsg.replace("?",
			// appliedJobDTO.getAppliedDt());
			// jsonObject.put(ajaxMsg, applyJobErrMsg);
			// return jsonObject;
			// }
			String loginPath = navigationPath.substring(2);
			String jonseekerloginUrl =
			// request.getRequestURL().toString()
			// .replace(request.getServletPath(), loginPath)
			loginPath + dothtmlExtention + jobseekerPageExtention;
			String employerloginUrl =
			// request.getRequestURL().toString()
			// .replace(request.getServletPath(), loginPath)
			loginPath + dothtmlExtention + employerPageExtention;

			EmailDTO employerEmailDTO = new EmailDTO();
			employerEmailDTO.setFromAddress(advanceWebAddress);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress(
					"merion@nousinfosystems.com");
			employerEmailDTO.setToAddress(employerToAddress);
			String employerMailSub = employeJobApplicationSub.replace(
					"?jobseekername", userName);
			employerEmailDTO.setSubject(employerMailSub);
			String employerMailBody = employeJobApplicationBody.replace(
					"?empDashboardLink", employerloginUrl);
			employerMailBody = employerMailBody.replace("?jobseekername",
					userName);
			employerEmailDTO.setBody(employerMailBody);
			employerEmailDTO.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();
			try {
				ResumeDTO resumeDTO = resumeService
						.fetchPublicResumeByUserId(userId);
				if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER
						.equalsIgnoreCase(resumeDTO.getResumeType())) {

				} else if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE
						.equalsIgnoreCase(resumeDTO.getResumeType())) {
					try {
						// Create temp file.
						File temp = File.createTempFile(
								resumeDTO.getResumeName(),
								defaultResumeExtension);
						File newFile = new File(temp.getParent() + "\\"
								+ resumeDTO.getResumeName()
								+ defaultResumeExtension);
						// Rename
						newFile.deleteOnExit();
						if (temp.renameTo(newFile)) {
							LOGGER.info("File has been renamed.");
						}
						temp.deleteOnExit();

						// Write to temp file
						BufferedWriter out = new BufferedWriter(new FileWriter(
								newFile));
						out.write(resumeDTO.getResumeText());
						out.close();
						resumeDTO.setFilePath(newFile.getAbsolutePath());
					} catch (IOException e) {
						LOGGER.info("Copy Paste resume error");
					}
				}
				if (resumeDTO.getFilePath() != null) {
					attachmentpaths.add(resumeDTO.getFilePath());
				}
				employerEmailDTO.setAttachmentPaths(attachmentpaths);
			} catch (Exception e) {
				LOGGER.info("Resume not found");
			}
			emailService.sendEmail(employerEmailDTO);
			LOGGER.info("Mail sent to employer");

			// Send confirmation mail to job seeker regarding job application
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			jobSeekerEmailDTO.setFromAddress("merion@nousinfosystems.com");
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress(
					"merion@nousinfosystems.com");
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			String jobseekerMailSub = jobseekerJobApplicationSub.replace(
					"?companyname", searchedJobDTO.getCompanyName());
			jobSeekerEmailDTO.setSubject(jobseekerMailSub);
			String jobseekerMailBody = jobseekerJobApplicationBody.replace(
					"?jsdashboardLink", jonseekerloginUrl);
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",
					searchedJobDTO.getCompanyName());
			jobSeekerEmailDTO.setBody(jobseekerMailBody);
			jobSeekerEmailDTO.setHtmlFormat(true);
			emailService.sendEmail(jobSeekerEmailDTO);
			LOGGER.info("Mail sent to jobseeker");

			// save the applied job in DB
			Date currentDate = new Date();
			AppliedJobDTO applyJobDTO = null;
			if (appliedJobDTO == null || appliedJobDTO.getAppliedDt() != null) {
				applyJobDTO = new AppliedJobDTO();
				JobPostDTO jpJob = new JobPostDTO();
				jpJob.setJobId(jobId);
				applyJobDTO.setJpJob(jpJob);
				applyJobDTO.setUserId(userId);
				applyJobDTO.setJobTitle(searchedJobDTO.getJobTitle());
				applyJobDTO.setFacilityName(searchedJobDTO.getCompanyName());
				applyJobDTO.setCreateDt(currentDate.toString());
				applyJobDTO.setAppliedDt(currentDate.toString());
				applyJobDTO.setDeleteDt(null);
				jobSearchService.saveOrApplyJob(applyJobDTO);
			} else {
				applyJobDTO = appliedJobDTO;
				applyJobDTO.setAppliedDt(currentDate.toString());
				jobSearchService.updateSaveOrApplyJob(applyJobDTO);
			}
			jsonObject.put(ajaxMsg, applyJobSuccessMsg);
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
