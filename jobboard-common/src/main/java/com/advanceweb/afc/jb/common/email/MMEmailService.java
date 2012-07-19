package com.advanceweb.afc.jb.common.email;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:22 PM
 */
//@Service("emailService")
public class MMEmailService implements MMEmail {
	
	@Autowired
	private JavaMailSender mailSender;
	 
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
			if (ccAddress != null && ccAddress.length > 0) {
				helper.setCc(emailDTO.getCcAddress());
			}
			InternetAddress[] bccAddress = emailDTO.getBccAddress();
			if (bccAddress != null && bccAddress.length > 0) {
				helper.setBcc(emailDTO.getBccAddress());
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
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}

}