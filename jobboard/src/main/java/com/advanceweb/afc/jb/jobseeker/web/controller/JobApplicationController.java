package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

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
import com.advanceweb.afc.jb.job.service.JobSearchActivity;
import com.advanceweb.afc.jb.job.web.controller.JobApplicationForm;
import com.sun.xml.bind.v2.TODO;

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
	private JobSearchActivity jobSearchActivity;

	@Autowired
	private MMEmailService emailService;

	private static final Logger LOGGER = Logger
			.getLogger("JobApplicationController.class");

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Value("${employeJobApplicationSub}")
	private String employeJobApplicationSub;

	@Value("${employeJobApplicationBody}")
	private String employeJobApplicationBody;

	@Value("${navigationPath}")
	private String navigationPath;

	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${jobseekerJobApplicationSub}")
	private String jobseekerJobApplicationSub;

	@Value("${jobseekerJobApplicationBody}")
	private String jobseekerJobApplicationBody;

	/*
	 * @Autowired private TransformAnonymousUserJobApply
	 * transformAnonymousUserJobApply;
	 */

	@RequestMapping(value = "/anonymousUser", method = RequestMethod.GET)
	public ModelAndView showAnoUserForm(Map model) {

		model.put("jobApplicationForm", new JobApplicationForm());
		return new ModelAndView("jobseekerguestuserformpopup");

	}
	
	
	

	@ResponseBody
	  
	  @RequestMapping(value = "/saveAnonymousUserJobapply",method=RequestMethod.POST) 
	public String appylJob(@ModelAttribute("jobApplicationForm")
	  JobApplicationForm form, BindingResult result,@RequestParam("filePath")
	  String filepath,HttpServletRequest request) {
		// ,@RequestParam("id") Long jobId
		try {

			/**
			 * send mail to employer email id which is given while posting the
			 * job and attach the anonymous job seeker resume as
			 * attachment,subject will be job title, body will contain short
			 * description
			 * 
			 */
		//TODO:waiting for the completion fo apply job functionality for anonymous,currently using hard code jobId
			SearchedJobDTO searchedJobDTO = jobSearchActivity.viewJobDetails(13100);
			// Adding path for
			String loginPath = navigationPath.substring(2);
			String jonseekerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention;
			String employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention;

			EmailDTO toEmployer = new EmailDTO();
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress(
					searchedJobDTO.getEmployerEmailAddress());
			toEmployer.setFromAddress(advanceWebAddress);
			toEmployer.setToAddress(employerToAddress);
			String employerMailSub = employeJobApplicationSub.replace("?jobseekername", form.getUserName());
			toEmployer.setSubject(employerMailSub);

			String employerMailBody = employeJobApplicationBody.replace(
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
				//LOGGER.info("Resume not found");
			}
			 emailService.sendEmail(toEmployer);
			//LOGGER.info("Mail sent to employer");

			/**
			 * send mail to anonymous job seeker email id which is given while
			 * applying the job, subject will be job title, body will contain
			 * short description
			 */

			EmailDTO toJobSeeker = new EmailDTO();
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress(form.getUserEmail());
			toJobSeeker.setToAddress(jobSeekerToAddress);
			toJobSeeker.setFromAddress(advanceWebAddress);
			String jobseekerMailSub = jobseekerJobApplicationSub.replace("?companyname", searchedJobDTO.getCompanyName());
			toJobSeeker.setSubject(jobseekerMailSub);
			String jobseekerMailBody = jobseekerJobApplicationBody.replace("?jsdashboardLink", jonseekerloginUrl);
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",searchedJobDTO.getCompanyName());
			toJobSeeker.setBody(jobseekerMailBody);
			toJobSeeker.setHtmlFormat(true);
			emailService.sendEmail(toJobSeeker);
			//LOGGER.info("Mail has sent to Anonymous User");
		} catch (Exception e) {

			// waiting for Exception

		}
		/*
		 * JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		 * model.put("jobSearchResultForm", jobSearchResultForm);
		 */
		return "";
	}

}