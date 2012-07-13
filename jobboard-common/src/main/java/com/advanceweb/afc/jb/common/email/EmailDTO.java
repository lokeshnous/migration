package com.advanceweb.afc.jb.common.email;

import java.util.List;

import javax.mail.internet.InternetAddress;



/**
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
	
	public EmailDTO(){

	}

	public void finalize() throws Throwable {

	}

	public InternetAddress[] getToAddress() {
		return toAddress;
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
		return ccAddress;
	}

	public void setCcAddress(InternetAddress[] ccAddress) {
		this.ccAddress = ccAddress;
	}

	public InternetAddress[] getBccAddress() {
		return bccAddress;
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