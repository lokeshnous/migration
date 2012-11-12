package com.advanceweb.afc.jb.common.email;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;

import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.jb.test.ServiceTestBase;

/**
 * <code>MMEmailServiceTest</code> is a test class for email service
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 18 July 2012
 * 
 */
public class MMEmailServiceTest extends ServiceTestBase {

	@Autowired
	private MMEmailService emailService;

	/**
	 * The method test the sending of mail.
	 * 
	 */
	@Test
	public void testSendEmail() {
		Boolean status = Boolean.FALSE;
		try {
			EmailDTO testemailDTO = new EmailDTO();
			testemailDTO.setFromAddress("merion@nousinfosystems.com");
			InternetAddress[] testccAddress = new InternetAddress[1];
			testccAddress[0] = new InternetAddress("mmnousinfo@gmail.com");
			testemailDTO.setCcAddress(testccAddress);
			InternetAddress[] testbccAddress = new InternetAddress[1];
			testbccAddress[0] = new InternetAddress("pramodap@nousinfo.com");
			testemailDTO.setBccAddress(testbccAddress);
			InternetAddress[] testtoAddress = new InternetAddress[1];
			testtoAddress[0] = new InternetAddress("anilm@nousinfo.com");
			testemailDTO.setToAddress(testtoAddress);
			testemailDTO
					.setSubject("Please ignore this mail:Test mail Subject");
			testemailDTO.setBody("Please ignore this mail:Test mail body");
			testemailDTO.setHtmlFormat(true);
			List<String> testAttachmentpaths = new ArrayList<String>();
			testAttachmentpaths.add("C:\\testResume.txt");
			testemailDTO.setAttachmentPaths(testAttachmentpaths);
			emailService.sendEmail(testemailDTO);
			status = Boolean.TRUE;
		} catch (MessagingException e) {
			status = Boolean.FALSE;
			throw new MailParseException(e);
		}
		assertTrue("Mail info", status);
	}

}