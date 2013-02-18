package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
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
	
	@Value("${salesWebAddress}")
	private String salesWebAddress;

	@Value("${goldOrPlatinumPricingReqBody}")
	private String goldOrPlatinumPricingReqBody;

	@Value("${goldOrPlatinumPricingReqSub}")
	private String goldOrPlatinumPricingReqSub;

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private PaymentGatewayService fetchAdmFacilityConatact;
	
	private static final String COMPANYNAME = "?Companyname";
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;

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

				employerToAddress[0] = new InternetAddress(salesWebAddress);
				employerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = goldOrPlatinumPricingReqSub.replace(
						COMPANYNAME, companyName);
				employerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = goldOrPlatinumPricingReqBody
						.replace("?Date", new Date().toString())
						.replace("?Employername", userName)
						.replace(COMPANYNAME, companyName)
						.replace("?Emailaddress", userEmail)
						.replace("?Phonenumber", phoneNo)
						.replace("?Mailingaddress", userEmail)
						.replace("?Packagetype",
								MMJBCommonConstants.PACKAGE_ESPOST);
				StringBuffer ezpostMail = new StringBuffer();
				ezpostMail.append(emailConfiguration.getProperty(
						"employer.email.header").trim());
				ezpostMail.append(jobseekerMailBody);
				ezpostMail.append(emailConfiguration.getProperty("email.footer")
						.trim());
				employerEmailDTO.setBody(ezpostMail.toString());
				employerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(employerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_GOLD)) {

				InternetAddress[] employerToAddress = new InternetAddress[1];

				employerToAddress[0] = new InternetAddress(salesWebAddress);
				employerEmailDTO.setToAddress(employerToAddress);
				String jobseekerMailSub = goldOrPlatinumPricingReqSub.replace(
						COMPANYNAME, companyName);
				employerEmailDTO.setSubject(jobseekerMailSub);
				String jobseekerMailBody = goldOrPlatinumPricingReqBody
						.replace("?Date", new Date().toString())
						.replace("?Employername", userName)
						.replace(COMPANYNAME, companyName)
						.replace("?Emailaddress", userEmail)
						.replace("?Phonenumber", phoneNo)
						.replace("?Mailingaddress", userEmail)
						.replace("?Packagetype",
								MMJBCommonConstants.PACKAGE_GOLD);
				StringBuffer goldPackageMail = new StringBuffer();
				goldPackageMail.append(emailConfiguration.getProperty(
						"employer.email.header").trim());
				goldPackageMail.append(jobseekerMailBody);
				goldPackageMail.append(emailConfiguration.getProperty("email.footer")
						.trim());
				employerEmailDTO.setBody(goldPackageMail.toString());
				employerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(employerEmailDTO);
				// LOGGER.info("Mail sent to jobseeker");

			} else if (packageId
					.equalsIgnoreCase(MMJBCommonConstants.PACKAGE_PLATINUM)) {

				sendEmailForPlatinum(employerEmailDTO, companyName, phoneNo,
						userName, userEmail);

			}
		} catch (Exception e) {
			LOGGER.error("Error while sending email for gold",e);
		}
		model.setViewName("redirect:/employer/employerDashBoard.html");
		return model;
	}

	private void sendEmailForPlatinum(EmailDTO employerEmailDTO,
			String companyName, String phoneNo, String userName,
			String userEmail) throws AddressException {
		InternetAddress[] employerToAddress = new InternetAddress[1];

		employerToAddress[0] = new InternetAddress(
				salesWebAddress);
		employerEmailDTO.setToAddress(employerToAddress);
		String jobseekerMailSub = goldOrPlatinumPricingReqSub.replace(
				COMPANYNAME, companyName);
		employerEmailDTO.setSubject(jobseekerMailSub);
		String jobseekerMailBody = goldOrPlatinumPricingReqBody
				.replace("?Date", new java.util.Date().toString())
				.replace("?Employername", userName)
				.replace(COMPANYNAME, companyName)
				.replace("?Emailaddress", userEmail)
				.replace("?Phonenumber", phoneNo)
				.replace("?Mailingaddress", userEmail)
				.replace("?Packagetype",
						MMJBCommonConstants.PACKAGE_PLATINUM);
		// jobseekerMailBody = jobseekerMailBody.replace("?companyname",
		// jobDTO.getCompanyName());
		StringBuffer platinumPackageMail = new StringBuffer();
		platinumPackageMail.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		platinumPackageMail.append(jobseekerMailBody);
		platinumPackageMail.append(emailConfiguration.getProperty("email.footer")
				.trim());
		employerEmailDTO.setBody(platinumPackageMail.toString());
		employerEmailDTO.setHtmlFormat(true);
		emailService.sendEmail(employerEmailDTO);
		// LOGGER.info("Mail sent to jobseeker");
	}

}
