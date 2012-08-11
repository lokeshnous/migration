package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.job.service.JobSearchActivity;
import com.advanceweb.afc.jb.job.web.controller.JobApplicationForm;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a controller for the Anonymous User to apply for a Job
 */
@Controller
@RequestMapping("/anonymoususerjobapply")
public class JobApplicationController {

	
	@Autowired
	private JobSearchActivity jobSearchActivity;

	@Autowired
	private MMEmailService emailService;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	/*@Autowired
	private TransformAnonymousUserJobApply transformAnonymousUserJobApply;
	*/
	
	@RequestMapping(value="/anonymousUser",method = RequestMethod.GET)
	public ModelAndView showAnoUserForm( Map model) {
		
	        model.put("jobApplicationForm", new JobApplicationForm());
	        return new ModelAndView("jobseekerguestuserformpopup");
	    
	}
	
	
	@RequestMapping(value="/saveAnonymousUserJobapply",method = RequestMethod.GET)
	public ModelAndView saveJobSeekerRegistration(@Valid JobApplicationForm form,
			BindingResult result,Map model) {
		//,@RequestParam("id") Long jobId
		try {
			
				if (result.hasErrors()) {
					return new ModelAndView("jobseekerguestuserformpopup");
				}

				/**
				 * send mail to employer email id which is given while posting the job and attach the anonymous 
				 * job seeker resume as attachment,subject will be job title, body will contain short description
				 * 
				 */
				SearchedJobDTO searchedJobDTO = jobSearchActivity.viewJobDetails(13100);	
				
				EmailDTO toEmployer = new EmailDTO();
				InternetAddress[] employerToAddress = new InternetAddress[1];
				//employerToAddress[0] = new InternetAddress(searchedJobDTO.getEmployerEmailAddress());
				employerToAddress[0] = new InternetAddress("pramodap@nousinfo.com");
				toEmployer.setFromAddress(advanceWebAddress);
				toEmployer.setToAddress(employerToAddress);
				toEmployer.setSubject(searchedJobDTO.getJobTitle());
				toEmployer.setBody(searchedJobDTO.getJobDesc());
				toEmployer.setHtmlFormat(true);
				List<String> attachmentpaths = new ArrayList<String>();
				attachmentpaths.add(form.getFilePath());
				//attachmentpaths.add("D:\\testresume.txt");
				toEmployer.setAttachmentPaths(attachmentpaths);
				
				emailService.sendEmail(toEmployer);

				/**
				 * send mail to anonymous job seeker  email id which is given while applying the job, 
				 * subject will be job title, body will contain short description
				 */
				
				EmailDTO toJobSeeker = new EmailDTO();
				InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
				jobSeekerToAddress[0] = new InternetAddress(form.getUserEmail());
				toJobSeeker.setToAddress(jobSeekerToAddress);
				toJobSeeker.setFromAddress(advanceWebAddress);
				toJobSeeker.setSubject(searchedJobDTO.getJobTitle());
				toJobSeeker.setBody(searchedJobDTO.getJobDesc());
				toJobSeeker.setHtmlFormat(true);
				emailService.sendEmail(toJobSeeker);
				System.out.println("Mail sent");
		
		} catch (Exception e) {

			// waiting for Exception
			
		}
		JobSearchResultForm jobSearchResultForm=new JobSearchResultForm();
		model.put("jobSearchResultForm", jobSearchResultForm);
		return new ModelAndView("jobboardsearchresults");
	}
	
	
	

}
