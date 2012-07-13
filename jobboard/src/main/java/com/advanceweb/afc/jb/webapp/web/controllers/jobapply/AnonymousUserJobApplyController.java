package com.advanceweb.afc.jb.webapp.web.controllers.jobapply;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.jobsearch.JobSearchActivity;
import com.advanceweb.afc.jb.webapp.web.forms.jobapply.AnonymousUserJobApplyForm;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a controller for the Anonymous User to apply for a Job
 */

@Controller
@RequestMapping("/anonymoususerjobapply")
public class AnonymousUserJobApplyController {

	
	@Autowired
	private JobSearchActivity jobSearchActivity;

	@Autowired
	private MMEmailService mailSender;
	
	/*@Autowired
	private TransformAnonymousUserJobApply transformAnonymousUserJobApply;
	*/
	public AnonymousUserJobApplyController() {
		super();
	}
	
	
	@RequestMapping(value="/saveAnonymousUserJobapply",method = RequestMethod.POST)
	public ModelAndView saveJobSeekerRegistration(@Valid AnonymousUserJobApplyForm form,
			BindingResult result,@RequestParam("id") Long jobId) {

		try {
			
				if (result.hasErrors()) {
					return new ModelAndView("anonymoususerjobapply");
				}

				/**
				 * send mail to employer email id which is given while posting the job and attach the anonymous 
				 * job seeker resume as attachment,subject will be job title, body will contain short description
				 * 
				 */
				SearchedJobDTO searchedJobDTO = jobSearchActivity.viewJobDetails(jobId);				
				EmailDTO toEmployer = new EmailDTO();
				InternetAddress[] employerToAddress = new InternetAddress[1];
				employerToAddress[0] = new InternetAddress(searchedJobDTO.getEmployerEmailAddress());
				toEmployer.setToAddress(employerToAddress);
				toEmployer.setSubject(searchedJobDTO.getJobTitle());
				toEmployer.setBody(searchedJobDTO.getJobDesc());
				toEmployer.setHtmlFormat(true);
				List<String> attachmentpaths = new ArrayList<String>();
				attachmentpaths.add(form.getFilePath());
				toEmployer.setAttachmentPaths(attachmentpaths);
				mailSender.sendEmail(toEmployer);

				/**
				 * send mail to anonymous job seeker  email id which is given while applying the job, 
				 * subject will be job title, body will contain short description
				 */
				
				EmailDTO toJobSeeker = new EmailDTO();
				InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
				jobSeekerToAddress[0] = new InternetAddress(form.getUserEmail());
				toJobSeeker.setToAddress(jobSeekerToAddress);
				toJobSeeker.setSubject(searchedJobDTO.getJobTitle());
				toJobSeeker.setBody(searchedJobDTO.getJobDesc());
				toJobSeeker.setHtmlFormat(true);
				mailSender.sendEmail(toJobSeeker);

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("anouserjobapplyssuccess");
	}
	
	
	

}
