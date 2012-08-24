package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.job.web.controller.JobApplicationForm;
import com.advanceweb.afc.jb.search.service.JobSearchService;

/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 12, 2012
 * @Purpose: This class will act as a controller for the Anonymous User to apply
 *           for a Job
 */
@Controller
@RequestMapping("/anonymoususerjobapply")
public class JobApplicationController {

	@Autowired
	private JobSearchService jobSearchService;

	@Autowired
	private  MMEmailService emailService;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger("JobApplicationController.class");

	@Value("${advanceWebAddress}")
	private  String advanceWebAddress;

	@Value("${employeJobApplicationSub}")
	private  String empJobAppSub;

	@Value("${employeJobApplicationBody}")
	private  String empJobAppBody;

	@Value("${navigationPath}")
	private  String navigationPath;

	@Value("${dothtmlExtention}")
	private  String dothtmlExtention;
	
	@Value("${employerPageExtention}")
	private String employerPageExtention;
	
	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;

	@Value("${jobseekerJobApplicationSub}")
	private String jobAppSub;

	@Value("${jobseekerJobApplicationBody}")
	private  String jobAppBody;

	/*
	 * @Autowired private TransformAnonymousUserJobApply
	 * transformAnonymousUserJobApply;
	 */

	@RequestMapping(value = "/anonymousUser", method = RequestMethod.GET)
	public ModelAndView showAnoUserForm(Map<String, JobApplicationForm> model) {

		model.put("jobApplicationForm", new JobApplicationForm());
		return new ModelAndView("jobseekerguestuserformpopup");

	}

	/**
	 * This method is used to save the Anonymous User record - yet to implement
	 * along with saving need to send email for both employer and Anonymous user
	 * 
	 * @param form
	 * @param result
	 * @param filepath
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveAnonymousUserJobapply", method = RequestMethod.POST)
	public String appylJob(
			@ModelAttribute("jobApplicationForm") JobApplicationForm form,
			BindingResult result, @RequestParam("filePath") String filepath,
			HttpServletRequest request, HttpSession session) {
		// ,@RequestParam("id") Long jobId
		try {

			// send mail to employer email id which is given while posting the
			 // job and attach the anonymous job seeker resume as
			 // attachment,subject will be job title, body will contain short
			 // description
			 
			// TODO:waiting for the completion for apply job functionality for
			// anonymous,currently using hard code jobId
			int jobId = (Integer) session.getAttribute("jobId");
			SearchedJobDTO searchedJobDTO = jobSearchService
					.viewJobDetails(jobId);
			
			// Adding path for
			String loginPath = navigationPath.substring(2);
			String jonseekerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + jobseekerPageExtention;
			String employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + employerPageExtention;

			EmailDTO toEmployer = new EmailDTO();
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress(
					searchedJobDTO.getEmployerEmailAddress());
			toEmployer.setFromAddress(advanceWebAddress);
			toEmployer.setToAddress(employerToAddress);
			String employerMailSub = empJobAppSub.replace(
					"?jobseekername", form.getUserName());
			toEmployer.setSubject(employerMailSub);

			String employerMailBody = empJobAppBody.replace(
					"?empDashboardLink", employerloginUrl);
			employerMailBody = employerMailBody.replace("?jobseekername",
					form.getUserName());
			toEmployer.setBody(employerMailBody);
			toEmployer.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();
			try {

				attachmentpaths.add(filepath);
				toEmployer.setAttachmentPaths(attachmentpaths);
			} catch (Exception e) {
				// LOGGER.info("Resume not found");
				// TODO:Exception handeling
			}
			emailService.sendEmail(toEmployer);
			// LOGGER.info("Mail sent to employer");

			/**
			 * send mail to anonymous job seeker email id which is given while
			 * applying the job, subject will be job title, body will contain
			 * short description
			 */

			EmailDTO toJobSeeker = new EmailDTO();
			InternetAddress[] jsToAddress = new InternetAddress[1];
			jsToAddress[0] = new InternetAddress(form.getUserEmail());
			toJobSeeker.setToAddress(jsToAddress);
			toJobSeeker.setFromAddress(advanceWebAddress);
			String jobseekerMailSub = jobAppSub.replace(
					"?companyname", searchedJobDTO.getCompanyName());
			toJobSeeker.setSubject(jobseekerMailSub);
			String jobseekerMailBody = jobAppBody.replace(
					"?jsdashboardLink", jonseekerloginUrl);
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",
					searchedJobDTO.getCompanyName());
			toJobSeeker.setBody(jobseekerMailBody);
			toJobSeeker.setHtmlFormat(true);
			emailService.sendEmail(toJobSeeker);
			// LOGGER.info("Mail has sent to Anonymous User");
		} catch (Exception e) {

			// TODO:Exception handeling

		}
		return "";
	}

}