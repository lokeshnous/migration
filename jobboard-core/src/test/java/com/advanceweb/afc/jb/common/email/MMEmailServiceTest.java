package com.advanceweb.afc.jb.common.email;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

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
	private JavaMailSender mailSender;
	 

	/**
	 * The method test the sending of mail.
	 */
	@Test
	public void testSendEmail() {
		Boolean status = Boolean.FALSE;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			
			EmailDTO testemailDTO = new EmailDTO();
			testemailDTO.setFromAddress("from@gmail.com");
			InternetAddress[] testccAddress = new InternetAddress[1];
			testccAddress[0] = new InternetAddress("cc1@gmail.com");
			testemailDTO.setCcAddress(testccAddress);
			InternetAddress[] testbccAddress = new InternetAddress[1];
			testbccAddress[0] = new InternetAddress("bcc1@gmail.com");
			testemailDTO.setBccAddress(testbccAddress);
			InternetAddress[] testtoAddress = new InternetAddress[1];
			testtoAddress[0] = new InternetAddress("to@gmail.com");
			testemailDTO.setToAddress(testtoAddress);
			testemailDTO.setSubject("Test mail Subject");
			testemailDTO.setBody("Test mail body");
			testemailDTO.setHtmlFormat(true);
			List<String> testAttachmentpaths = new ArrayList<String>();
			testAttachmentpaths.add("C:\\testResume.txt");
			testemailDTO.setAttachmentPaths(testAttachmentpaths);
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(testemailDTO.getFromAddress());
			InternetAddress[] ccAddress = testemailDTO.getCcAddress();
			if (ccAddress != null && ccAddress.length > 0) {
				helper.setCc(testemailDTO.getCcAddress());
			}
			InternetAddress[] bccAddress = testemailDTO.getBccAddress();
			if (bccAddress != null && bccAddress.length > 0) {
				helper.setBcc(testemailDTO.getBccAddress());
			}
			helper.setTo(testemailDTO.getToAddress());
			helper.setSubject(testemailDTO.getSubject());
			helper.setText(testemailDTO.getBody(), testemailDTO.isHtmlFormat());
			List<String> attachmentPaths = testemailDTO.getAttachmentPaths();
			if (attachmentPaths != null && attachmentPaths.isEmpty()) {
				FileSystemResource file = null;
				for (String path : attachmentPaths) {
					file = new FileSystemResource(path);
					if (file.exists()) {
						helper.addAttachment(file.getFilename(), file);
					}
				}
			}
			mailSender.send(message);
			status = Boolean.TRUE;
		} catch (MessagingException e) {
			status = Boolean.FALSE;
			throw new MailParseException(e);
		}
		assertTrue("Mail info",status);		
	}

}