package com.advanceweb.afc.jb.common.email;

import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * <code>EmailDTO</code> is a DTO class for email.
 * 
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:23 PM
 */
public class EmailDTO {

	private String fromAddress;
	private InternetAddress[] toAddress;
	private InternetAddress[] ccAddress;
	private InternetAddress[] bccAddress;
	private String subject;
	private String body;
	private List<String> attachmentPaths;
	private boolean htmlFormat;
	
	public InternetAddress[] getToAddress() {
		// Since: PMD 2.2:Exposing internal arrays to the caller violates object
		// encapsulation
		return toAddress != null?toAddress.clone():toAddress;
	}

	public void setToAddress(InternetAddress[] toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public InternetAddress[] getCcAddress() {
		// Since: PMD 2.2:Exposing internal arrays to the caller violates object
		// encapsulation
		return ccAddress != null?ccAddress.clone():ccAddress;
	}

	public void setCcAddress(InternetAddress[] ccAddress) {
		this.ccAddress = ccAddress;
	}

	public InternetAddress[] getBccAddress() {
		// Since: PMD 2.2:Exposing internal arrays to the caller violates object
		// encapsulation
		return bccAddress != null?bccAddress.clone():bccAddress;
	}

	public void setBccAddress(InternetAddress[] bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getAttachmentPaths() {
		return attachmentPaths;
	}

	public void setAttachmentPaths(List<String> attachmentPaths) {
		this.attachmentPaths = attachmentPaths;
	}

	public boolean isHtmlFormat() {
		return htmlFormat;
	}

	public void setHtmlFormat(boolean htmlFormat) {
		this.htmlFormat = htmlFormat;
	}
	

}