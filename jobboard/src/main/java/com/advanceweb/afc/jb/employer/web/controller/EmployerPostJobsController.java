package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;

/**
 * @author muralikc
 * 
 */
@Controller
@RequestMapping("/employerPostJobs")
public class EmployerPostJobsController {
	
	private static final Logger LOGGER = Logger
			.getLogger(EmployerPostJobsController.class);

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Value("${goldOrPlatinumPricingReqBody}")
	private String goldOrPlatinumPricingReqBody;

	@Value("${goldOrPlatinumPricingReqSub}")
	private String goldOrPlatinumPricingReqSub;

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private PaymentGatewayService fetchAdmFacilityConatact;

	@RequestMapping(value = "/sendEmailForGold")
	public ModelAndView sendEmailForGold(HttpSession session,
			@RequestParam("package") String packageId) {
		// Send confirmation mail to job seeker regarding job application
		EmailDTO employerEmailDTO = new EmailDTO();
		ModelAndView model = new ModelAndView();
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		// Fetching the Account address from the database
		AccountAddressDTO accountAddressDTO = fetchAdmFacilityConatact
				.getConatactByFacilityId(facilityId);
		String companyName = accountAddressDTO.getCompany();
		String phoneNo = accountAddressDTO.getPhone();

		String userName = "";
		String userEmail = "";
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			userName = (String) session
					.getAttribute(MMJBCommonConstants.USER_NAME);
			userEmail = (String) session
					.getAttribute(MMJBCommonConstants.USER_EMAIL);
		}
		try {
			employerEmailDTO.setFromAddress(advanceWebAddress);
			if (packageId.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_SILVER)
					|| packageId
							.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_STJOBPOSTING)
					|| packageId
							.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_JBPOSTSLOT)) {
				model.setViewName("");
				return model;

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_ESPOST)) {

				InternetAddress[] employerToAddress = new InternetAddress[1];

				employerToAddress[0] = new InternetAddress("muralikc@nousinfo.com");
				employerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = goldOrPlatinumPricingReqSub.replace(
						"?Companyname", companyName);
				employerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = goldOrPlatinumPricingReqBody
						.replace("?Date", new Date().toString())
						.replace("?Employername", userName)
						.replace("?Companyname", companyName)
						.replace("?Emailaddress", userEmail)
						.replace("?Phonenumber", phoneNo)
						.replace("?Mailingaddress", userEmail)
						.replace("?Packagetype",
								MMJBCommonConstants.PACKAGE_ESPOST);
				employerEmailDTO.setBody(jobseekerMailBody);
				employerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(employerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_GOLD)) {

				InternetAddress[] employerToAddress = new InternetAddress[1];

				employerToAddress[0] = new InternetAddress("muralikc@nousinfo.com");
				employerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = goldOrPlatinumPricingReqSub.replace(
						"?Companyname", companyName);
				employerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = goldOrPlatinumPricingReqBody
						.replace("?Date", new Date().toString())
						.replace("?Employername", userName)
						.replace("?Companyname", companyName)
						.replace("?Emailaddress", userEmail)
						.replace("?Phonenumber", phoneNo)
						.replace("?Mailingaddress", userEmail)
						.replace("?Packagetype",
								MMJBCommonConstants.PACKAGE_GOLD);
				employerEmailDTO.setBody(jobseekerMailBody);
				employerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(employerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_PLATINUM)) {

				InternetAddress[] employerToAddress = new InternetAddress[1];

				employerToAddress[0] = new InternetAddress(
						"muralikc@nousinfo.com");
				employerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = goldOrPlatinumPricingReqSub.replace(
						"?Companyname", companyName);
				employerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = goldOrPlatinumPricingReqBody
						.replace("?Date", new java.util.Date().toString())
						.replace("?Employername", userName)
						.replace("?Companyname", companyName)
						.replace("?Emailaddress", userEmail)
						.replace("?Phonenumber", phoneNo)
						.replace("?Mailingaddress", userEmail)
						.replace("?Packagetype",
								MMJBCommonConstants.PACKAGE_PLATINUM);
				// jobseekerMailBody = jobseekerMailBody.replace("?companyname",
				// searchedJobDTO.getCompanyName());
				employerEmailDTO.setBody(jobseekerMailBody);
				employerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(employerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			}
		} catch (Exception e) {
			LOGGER.info("Error while sending email for gold",e);;
		}
		model.setViewName("redirect:/employer/employerDashBoard.html");
		return model;
	}

}
