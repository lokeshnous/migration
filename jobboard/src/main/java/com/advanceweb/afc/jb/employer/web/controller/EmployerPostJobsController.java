package com.advanceweb.afc.jb.employer.web.controller;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * @author muralikc
 * 
 */
@Controller
@RequestMapping("/employerPostJobs")
public class EmployerPostJobsController {

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Value("${jobseekerJobApplicationBody}")
	private String jobseekerJobApplicationBody;

	@Autowired
	private MMEmailService emailService;

	@Value("${jobseekerJobApplicationSub}")
	private String jobseekerJobApplicationSub;

	@RequestMapping(value = "/sendEmailForGold")
	public ModelAndView sendEmailForGold(HttpSession session,
			@RequestParam("package") String packageId) {
		// Send confirmation mail to job seeker regarding job application
		EmailDTO jobSeekerEmailDTO = new EmailDTO();
		ModelAndView model = new ModelAndView();
		int userId = 0;
		String userName = "";
		String userEmail = "";
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			userName = (String) session
					.getAttribute(MMJBCommonConstants.USER_NAME);
			userEmail = (String) session
					.getAttribute(MMJBCommonConstants.USER_EMAIL);
		}
		try {
			if (packageId.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_SILVER)
					|| packageId
							.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_STJOBPOSTING)
					|| packageId
							.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_JBPOSTSLOT)) {
				model.setViewName("");
				return model;

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_ESPOST)) {
				jobSeekerEmailDTO.setFromAddress(userEmail);

				InternetAddress[] employerToAddress = new InternetAddress[1];

				// employerToAddress[0] = new InternetAddress(userEmail);
				// TODO: Remove hard codes of mails
				employerToAddress[0] = new InternetAddress(
						"muralikc@nousinfo.com");
				jobSeekerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = jobseekerJobApplicationSub
				// .replace(
				// "?companyname", searchedJobDTO.getCompanyName())
				;
				jobSeekerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = jobseekerJobApplicationBody
				// .replace(
				// "?jsdashboardLink", jonseekerloginUrl)
				;
				// jobseekerMailBody = jobseekerMailBody.replace("?companyname",
				// searchedJobDTO.getCompanyName());
				jobSeekerEmailDTO.setBody(jobseekerMailBody);
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_GOLD)) {
				jobSeekerEmailDTO.setFromAddress(userEmail);

				InternetAddress[] employerToAddress = new InternetAddress[1];

				// employerToAddress[0] = new InternetAddress(userEmail);
				// TODO: Remove hard codes of mails
				employerToAddress[0] = new InternetAddress(
						"muralikc@nousinfo.com");
				jobSeekerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = jobseekerJobApplicationSub
				// .replace(
				// "?companyname", searchedJobDTO.getCompanyName())
				;
				jobSeekerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = jobseekerJobApplicationBody
				// .replace(
				// "?jsdashboardLink", jonseekerloginUrl)
				;
				// jobseekerMailBody = jobseekerMailBody.replace("?companyname",
				// searchedJobDTO.getCompanyName());
				jobSeekerEmailDTO.setBody(jobseekerMailBody);
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_PLATINUM)) {
				jobSeekerEmailDTO.setFromAddress(userEmail);

				InternetAddress[] employerToAddress = new InternetAddress[1];

				// employerToAddress[0] = new InternetAddress(userEmail);
				// TODO: Remove hard codes of mails
				employerToAddress[0] = new InternetAddress(
						"muralikc@nousinfo.com");
				jobSeekerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = jobseekerJobApplicationSub
				// .replace(
				// "?companyname", searchedJobDTO.getCompanyName())
				;
				jobSeekerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = jobseekerJobApplicationBody
				// .replace(
				// "?jsdashboardLink", jonseekerloginUrl)
				;
				// jobseekerMailBody = jobseekerMailBody.replace("?companyname",
				// searchedJobDTO.getCompanyName());
				jobSeekerEmailDTO.setBody(jobseekerMailBody);
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			System.out.println("Error");
		}
		return null;
	}

}
