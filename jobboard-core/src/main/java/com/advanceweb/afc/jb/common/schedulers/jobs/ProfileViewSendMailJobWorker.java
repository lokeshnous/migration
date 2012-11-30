package com.advanceweb.afc.jb.common.schedulers.jobs;

import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * The class is used to send email to the job seekers containing information on
 * Employers who viewed their profile.
 * 
 */

@Service
@Qualifier("profileViewSendMailJobWorker")
public class ProfileViewSendMailJobWorker implements JobWorker {
	private static final Logger LOGGER = Logger
			.getLogger(ProfileViewSendMailJobWorker.class);
	private static final String JOB_NAME = "SEND_MAIL";
	@Autowired
	private JobSeekerJobDetailService jobSeekerJobDetailService;
	@Autowired
	private UserDao userDAO;
	@Autowired
	private MMEmailService emailService;
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;

	@Override
	public void executeJob() {
		List<SchedulerDTO> jobSeekerList = userDAO.getAllJobSeekerList();
		for (SchedulerDTO dto : jobSeekerList) {
			List<Integer> view = jobSeekerJobDetailService.getEmployerViews(dto
					.getUserId());

			StringBuffer stringBuffer = new StringBuffer();
			InternetAddress[] toAddress = new InternetAddress[1];
			try {
				toAddress[0] = new InternetAddress(dto.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error(
						"Error occured while geting InternetAddress reference",
						jbex);
			}
			EmailDTO emailDTO = new EmailDTO();
			emailDTO.setToAddress(toAddress);
			emailDTO.setFromAddress(advanceWebAddress);
			emailDTO.setSubject(emailConfiguration.getProperty(
					"employerViewedYourProfile.subject").trim());

			StringBuffer mailBody = new StringBuffer(emailConfiguration
					.getProperty("employer.view.your.profile.mail.body").trim());
			int start, end;
			start = mailBody.indexOf("?jobSeekerFirstName");
			end = start + "?jobSeekerFirstName".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, dto.getFirstName());
			}
			start = mailBody.toString().indexOf("?profileViews");
			end = start + "?profileViews".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, view.get(0).toString());
			}
			start = mailBody.toString().indexOf("?searchResultAppearances");
			end = start + "?searchResultAppearances".length();
			if (start > 0 && end > 0) {
				mailBody.replace(start, end, view.get(1).toString());
			}
			start = mailBody.toString().indexOf("jobSeekerdashBoardLink");
			end = start + "jobSeekerdashBoardLink".length();
			if (start > 0 && end > 0) {
				mailBody.replace(
						start,
						end,
						emailConfiguration.getProperty(
								"jobseeker.dashboard.url").trim());
			}
			stringBuffer.append(emailConfiguration.getProperty(
					"jobseeker.email.header").trim());
			stringBuffer.append(mailBody);
			stringBuffer.append(emailConfiguration.getProperty("email.footer")
					.trim());
			emailDTO.setBody(stringBuffer.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		}
		LOGGER.info("ProfileViewSendMailJobWorker.-> Executed Job Successfully.....");
	}

	@Override
	public String getJobName() {
		return JOB_NAME;
	}

}
