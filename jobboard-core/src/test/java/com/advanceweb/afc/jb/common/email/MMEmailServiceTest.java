package com.advanceweb.afc.jb.common.email;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;

import com.advanceweb.afc.jb.ServiceTest;

/**
 * <code>MMEmailServiceTest</code> is a test class for email service
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 18 July 2012
 * 
 */
public class MMEmailServiceTest extends ServiceTest  {
	
	@Autowired
	private MMEmailService emailService;
	 

	/**
	 * The method test the sending of mail.
	 *  
	 */
	@Ignore("Not Ready to test as port number is not given")
	@Test(expected = MailSendException.class)
	public void testSendEmail() {
		Boolean status = Boolean.FALSE;
		try {			
			EmailDTO testemailDTO = new EmailDTO();
			testemailDTO.setFromAddress("testfromAddress@testDomain.com");
			InternetAddress[] testccAddress = new InternetAddress[1];
			testccAddress[0] = new InternetAddress("testCcAddress@testDomain.com");
			testemailDTO.setCcAddress(testccAddress);
			InternetAddress[] testbccAddress = new InternetAddress[1];
			testbccAddress[0] = new InternetAddress("testBccAddress@testDomain.com");
			testemailDTO.setBccAddress(testbccAddress);
			InternetAddress[] testtoAddress = new InternetAddress[1];
			testtoAddress[0] = new InternetAddress("testToAddress@testDomain.com");
			testemailDTO.setToAddress(testtoAddress);
			testemailDTO.setSubject("Test mail Subject");
			testemailDTO.setBody("Test mail body");
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
		assertTrue("Mail info",status);		
	}

}