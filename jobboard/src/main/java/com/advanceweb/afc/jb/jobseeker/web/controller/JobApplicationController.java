package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;
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
	
	@Value("${jobseekerJobApplicationSub}")
	private String jobAppSub;

	@Value("${anonymousJobApplicationBody}")
	private  String jobAppBody;

	 //TODO:this will we used for saving the anonymous user record
	 //@Autowired private TransformAnonymousUserJobApply
	 //transformAnonymousUserJobApply;
	 

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
	public ModelAndView appylJob(
			@ModelAttribute("jobApplicationForm") JobApplicationForm form,
			BindingResult result,
			HttpServletRequest request, HttpSession session) {
		ModelAndView model=new ModelAndView();
		try {
			// send mail to employer email id which is given while posting the
			 // job and attach the anonymous job seeker resume as
			 // attachment,subject will be job title, body will contain short
			 // description
			 
			int jobId = (Integer) session.getAttribute("jobId");
			SearchedJobDTO searchedJobDTO = jobSearchService
					.viewJobDetails(jobId);
			// Adding path for
			String loginPath = navigationPath.substring(2);
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
			
                MultipartFile file = form.getFileContent();
                File upLoadedfile = new File(file.getOriginalFilename());
                upLoadedfile.createNewFile();
                FileOutputStream fos = new FileOutputStream(upLoadedfile);
                fos.write(file.getBytes());
                fos.close(); 
                upLoadedfile.deleteOnExit();
			try {
				attachmentpaths.add(upLoadedfile.getAbsolutePath());
				toEmployer.setAttachmentPaths(attachmentpaths);
			} catch (Exception e) {
				LOGGER.info("Resume not found");
				// TODO:Exception Handling
			}
			emailService.sendEmail(toEmployer);
			// LOGGER.info("Mail sent to employer");

			 // send mail to anonymous job seeker email id which is given while
			 // applying the job, subject will be job title, body will contain
			 // short description
			EmailDTO toJobSeeker = new EmailDTO();
			InternetAddress[] jsToAddress = new InternetAddress[1];
			jsToAddress[0] = new InternetAddress(form.getUserEmail());
			toJobSeeker.setToAddress(jsToAddress);
			toJobSeeker.setFromAddress(advanceWebAddress);
			String jobseekerMailSub = jobAppSub.replace(
					"?companyname", searchedJobDTO.getCompanyName());
			toJobSeeker.setSubject(jobseekerMailSub);
			String jobseekerMailBody = jobAppBody;
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",
					searchedJobDTO.getCompanyName());
			toJobSeeker.setBody(jobseekerMailBody);
			toJobSeeker.setHtmlFormat(true);
			emailService.sendEmail(toJobSeeker);
			session.removeAttribute("jobId");
			model.setViewName("redirect:/jobsearch/findJobPage.html");
			// LOGGER.info("Mail has sent to Anonymous User");
		} catch (Exception e) {
			session.removeAttribute("jobId");
			model.setViewName("redirect:/jobsearch/findJobPage.html");
			return model;
			// TODO:Exception Handling
		}
		return model;
	}

}
