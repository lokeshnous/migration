package com.advanceweb.afc.jb.mail.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:22 PM
 */
@Service("emailService")
public class MMEmailService implements MMEmail {

	private static final Logger LOGGER = Logger
			.getLogger("MMEmailService.class");
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private UserDao userDAO;
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	@Autowired
	private JobSearchService jobSearchService;
	/**
	 * The method is to send mail.
	 * 
	 * @param emailDTO
	 */
	@Override
	public void sendEmail(EmailDTO emailDTO) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(emailDTO.getFromAddress());
			InternetAddress[] ccAddress = emailDTO.getCcAddress();
			setCCAddress(helper, ccAddress);
			InternetAddress[] bccAddress = emailDTO.getBccAddress();
			if (bccAddress != null && bccAddress.length > 0) {
				helper.setBcc(bccAddress);
			}
			helper.setTo(emailDTO.getToAddress());

			helper.setSubject(emailDTO.getSubject());
			helper.setText(emailDTO.getBody(), emailDTO.isHtmlFormat());
			List<String> attachmentPaths = emailDTO.getAttachmentPaths();
			if (attachmentPaths != null && !attachmentPaths.isEmpty()) {
				FileSystemResource file = null;
				for (String path : attachmentPaths) {
					file = new FileSystemResource(path);
					if (file.exists()) {
						helper.addAttachment(file.getFilename(), file);
					}
				}
			}
			mailSender.send(message);
			LOGGER.info("Mail has been sent.");
		} catch (MessagingException e) {
			LOGGER.error("sendEmail Exception : "+e);
			//throw new MailParseException(e);
		}
	}

	/**
	 * Helps to set the CC address for the mail.
	 * 
	 * @param helper
	 * @param ccAddress
	 */
	private void setCCAddress(MimeMessageHelper helper,
			InternetAddress[] ccAddress) {
		try {
			if (ccAddress != null && ccAddress.length > 0) {
				helper.setCc(ccAddress);
			}
		} catch (MessagingException e) {
			new Exception();
		}
	}
	/**
	 * service method to iterate and send mail to each saved search if any new job mathches
	 * @param scheduleDay
	 * @param inputParams
	 * @param saveSearchedJobsDTO
	 * @param stringNew
	 */
	public void sendMailBySavedSearch(String scheduleDay,
			Map<String, String> inputParams,
			SaveSearchedJobsDTO saveSearchedJobsDTO, StringTokenizer stringNew) {
		String keyWord;
		while (stringNew.hasMoreElements()) {
			String stringObject = (String) stringNew.nextElement();
			StringTokenizer stringAlter = new StringTokenizer(
					stringObject, "=");
			if ((boolean) stringAlter.nextElement().equals(
					"keywords")) {
				keyWord = (String) stringAlter.nextElement();
				LOGGER.info("Keyword=" + keyWord);
				inputParams.put(SearchParamDTO.KEYWORDS, keyWord);
				inputParams.put(SearchParamDTO.SEARCH_NAME,
						MMJBCommonConstants.KEYWORD_SEARCH);
				inputParams.put(SearchParamDTO.SEARCH_SEQ, "1");
				inputParams.put(SearchParamDTO.SESSION_ID,
						"278BAC559D47ABF5BD956B27A61650A7");
				inputParams.put(MMJBCommonConstants.SORT_PARAM,
						"posted_dt");
				inputParams.put(MMJBCommonConstants.FIRST_FQ_PARAM,
						"");
				inputParams.put(
						MMJBCommonConstants.SECOND_FQ_PARAM, "");
				inputParams.put(MMJBCommonConstants.THIRD_FQ_PARAM,
						"");
				inputParams.put(
						MMJBCommonConstants.FOURTH_FQ_PARAM, "");
				inputParams.put(MMJBCommonConstants.SORT_ORDER,
						"desc");
				inputParams.put(MMJBCommonConstants.SCHEDULER_DAY,
						scheduleDay);
				try {
					JobSearchResultDTO jobSearchResultDTO = jobSearchService
							.jobSearch(inputParams, 0, 20);
					if (null != jobSearchResultDTO
							&& null != jobSearchResultDTO
									.getResultList()) {
						LOGGER.info("Total Count:"
								+ jobSearchResultDTO
										.getResultCount());
						saveSearchedJobsDTO.setKeywords(keyWord);
						sendNewJobDetalEmail(saveSearchedJobsDTO,
								jobSearchResultDTO);

					}
				} catch (JobBoardServiceException jbex) {
					LOGGER.error(
							"Error occured while running sendMailBySavedSearch method",
							jbex);
				}
			}

		}
	}
	
	/**
	 * Method to send email with all new job opportunities that match saved searches
	 * @param userDTO
	 * @param jobDTO
	 */
	public void sendNewJobDetalEmail(SaveSearchedJobsDTO saveSearchedJobsDTO,
			JobSearchResultDTO jobSearchResultDTO) {
		if (!jobSearchResultDTO.getResultList().isEmpty()) {
			InternetAddress[] jsToAddress = new InternetAddress[1];
			UserDTO merUserdto = new UserDTO();
			StringBuffer stringBuffer = new StringBuffer();

			EmailDTO emailDTO = new EmailDTO();
			String jobseekerNewJobEmailBody = emailConfiguration.getProperty(
					"jobseeker.new.job.matches").trim();

			merUserdto = userDAO.getUserByUserId(saveSearchedJobsDTO
					.getUserID());

			emailDTO.setFromAddress(advanceWebAddress);
			emailDTO.setSubject(emailConfiguration.getProperty(
					"jobseeker.new.job.matches.subject").trim());

			jobseekerNewJobEmailBody = jobseekerNewJobEmailBody.replace(
					"?jobSeekerFirstName", merUserdto.getFirstName());

			String empName;

			for (JobDTO searchDTO : jobSearchResultDTO.getResultList()) {
				if (null == searchDTO.getFacilityName()) {
					empName = "";
				} else {
					empName = searchDTO.getFacilityName();
				}
				try {
					jsToAddress[0] = new InternetAddress(merUserdto.getEmailId());
					emailDTO.setToAddress(jsToAddress);
				} catch (AddressException jbex) {
					LOGGER.error(
							"Error occured while running scheduler job",
							jbex);
				}
				LOGGER.info("sent email to" + merUserdto.getEmailId()
						+ "with below details");
				LOGGER.info("Employer Details for KeyWord:....."+saveSearchedJobsDTO.getKeywords());
				LOGGER.info("Comapny Name:" + searchDTO.getCompany());
				LOGGER.info("Job Title:" + searchDTO.getJobTitle());
				if (null != saveSearchedJobsDTO.getKeywords()) {
					jobseekerNewJobEmailBody = jobseekerNewJobEmailBody
							+ emailConfiguration
									.getProperty(
											"jobseeker.new.job.matches.content")
									.trim()
									.replace("?keyword",
											saveSearchedJobsDTO.getKeywords());
				}
				jobseekerNewJobEmailBody = jobseekerNewJobEmailBody
								.replace("?jobTitle", searchDTO.getJobTitle());
				if (empName.isEmpty()) {
					empName = searchDTO.getCompany();
				}
				jobseekerNewJobEmailBody = jobseekerNewJobEmailBody
								.trim().replace("?employerName", empName);

			}
			jobseekerNewJobEmailBody = jobseekerNewJobEmailBody
					+ emailConfiguration.getProperty(
							"jobseeker.new.job.matches.content1").trim().replace("?jsdashboardLink", "#");
			stringBuffer.append(emailConfiguration.getProperty(
					"jobseeker.email.header").trim());
			stringBuffer.append(jobseekerNewJobEmailBody);
			stringBuffer.append(emailConfiguration.getProperty("email.footer")
					.trim());
			emailDTO.setBody(stringBuffer.toString());
			emailDTO.setHtmlFormat(true);
			sendEmail(emailDTO);
		}

	}
}