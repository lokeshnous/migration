/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.web.controller.JobApplicationForm;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
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

	/** The job search service. */
	@Autowired
	private JobSearchService jobSearchService;

	/** The email service. */
	@Autowired
	private  MMEmailService emailService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("JobApplicationController.class");

	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private  String advanceWebAddress;

	/** The emp job app sub. */
	@Value("${employeJobApplicationSub}")
	private  String empJobAppSub;

	/** The emp job app body. */
	@Value("${employeJobApplicationBody}")
	private  String empJobAppBody;

	/** The navigation path. */
	@Value("${navigationPath}")
	private  String navigationPath;

	/** The dothtml extention. */
	@Value("${dothtmlExtention}")
	private  String dothtmlExtention;
	
	/** The employer page extention. */
	@Value("${employerPageExtention}")
	private String employerPageExtention;
	
	/** The job app sub. */
	@Value("${jobseekerJobApplicationSub}")
	private String jobAppSub;

	/** The job app body. */
	@Value("${anonymousJobApplicationBody}")
	private  String jobAppBody;

	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	 //TODO:this will we used for saving the anonymous user record
	 //@Autowired private TransformAnonymousUserJobApply
	 //transformAnonymousUserJobApply;
	 

	/**
 	 * Show ano user form.
 	 *
 	 * @param model the model
 	 * @return the model and view
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
	
	@RequestMapping(value = "/saveAnonymousUserJobapply", method = RequestMethod.POST)
	public ModelAndView applyJob(
			@ModelAttribute("jobApplicationForm") JobApplicationForm form,
			BindingResult result,
			HttpServletRequest request, HttpSession session) {
		ModelAndView model=new ModelAndView();

		StringBuffer mailBody = new StringBuffer();
		//try {
			// send mail to employer email id which is given while posting the
			 // job and attach the anonymous job seeker resume as
			 // attachment,subject will be job title, body will contain short
			 // description
			 
			int jobId = (Integer) session.getAttribute("jobId");
			JobDTO jobDTO = jobSearchService
					.viewJobDetails(jobId);
			JobApplyTypeDTO jobApplyTypeDTO = jobSearchService
					.applyJobDetails(jobId);
			if (jobDTO.getEmail() == null) {
				jobDTO.setEmail(jobApplyTypeDTO
						.getApplyLink());
			}
			// Adding path for
			String loginPath = navigationPath.substring(2);
			String employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + employerPageExtention;
			EmailDTO toEmployer = new EmailDTO();
			InternetAddress[] employerToAddress = new InternetAddress[1];
			 try {
			employerToAddress[0] = new InternetAddress(
					jobDTO.getEmail());
			toEmployer.setFromAddress(advanceWebAddress);
			toEmployer.setToAddress(employerToAddress);
			String employerMailSub = empJobAppSub.replace(
					"?jobseekername", form.getUserName());
			toEmployer.setSubject(employerMailSub);
			String employerMailBody = empJobAppBody.replace(
					"?empDashboardLink", employerloginUrl);
			employerMailBody = employerMailBody.replace("?jobseekername",
					form.getUserName());
			mailBody.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			mailBody.append(employerMailBody);
			mailBody.append(emailConfiguration.getProperty("email.footer")
					.trim());
			toEmployer.setBody(mailBody.toString());
			toEmployer.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();
                MultipartFile file = form.getFileContent();
                File upLoadedfile = new File(file.getOriginalFilename());
                upLoadedfile.createNewFile();
                FileOutputStream fos = new FileOutputStream(upLoadedfile);
                fos.write(file.getBytes());
                fos.close(); 
                upLoadedfile.deleteOnExit();
				attachmentpaths.add(upLoadedfile.getAbsolutePath());
				toEmployer.setAttachmentPaths(attachmentpaths);
			} catch (Exception e) {
				LOGGER.error("Resume not found",e);
				// TODO:Exception Handling
			}
			
			emailService.sendEmail(toEmployer);
			// LOGGER.info("Mail sent to employer");

			 // send mail to anonymous job seeker email id which is given while
			 // applying the job, subject will be job title, body will contain
			 // short description
			EmailDTO toJobSeeker = new EmailDTO();
			InternetAddress[] jsToAddress = new InternetAddress[1];
			try{
			sendMailForAnonmousJobSeeker(form, session, model, jobDTO,
					toJobSeeker, jsToAddress);
		} catch (Exception e) {
			session.removeAttribute("jobId");
			model.setViewName("redirect:/search/findJobPage.html");
			return model;
			// TODO:Exception Handling
		}
		return model;
	}

	/**
	 * @param form
	 * @param session
	 * @param model
	 * @param jobDTO
	 * @param toJobSeeker
	 * @param jsToAddress
	 * @throws AddressException
	 */
	private void sendMailForAnonmousJobSeeker(JobApplicationForm form,
			HttpSession session, ModelAndView model, JobDTO jobDTO,
			EmailDTO toJobSeeker, InternetAddress[] jsToAddress)
			throws AddressException {

		StringBuffer mailBody = new StringBuffer();
		jsToAddress[0] = new InternetAddress(form.getUserEmail());
		toJobSeeker.setToAddress(jsToAddress);
		toJobSeeker.setFromAddress(advanceWebAddress);
		String jobseekerMailSub = "";
		if (jobDTO.getCompanyNameDisp() == null) {
			jobseekerMailSub = jobAppSub.replace("to ?companyname", "");
		} else {
			jobseekerMailSub = jobAppSub.replace("?companyname",
					jobDTO.getCompanyNameDisp());
		}
		toJobSeeker.setSubject(jobseekerMailSub);
		String jobseekerMailBody = jobAppBody;
		if (jobDTO.getCompanyNameDisp() == null) {
			jobseekerMailBody = jobseekerMailBody
					.replace("to ?companyname", "");
		} else {
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",
					jobDTO.getCompanyNameDisp());
		}
		mailBody.append(emailConfiguration.getProperty("employer.email.header")
				.trim());
		mailBody.append(jobseekerMailBody);
		mailBody.append(emailConfiguration.getProperty("email.footer").trim());
		toJobSeeker.setBody(mailBody.toString());
		toJobSeeker.setHtmlFormat(true);

		emailService.sendEmail(toJobSeeker);
		session.removeAttribute("jobId");
		model.setViewName("redirect:/search/findJobPage.html");
		// LOGGER.info("Mail has sent to Anonymous User");
	}

}
