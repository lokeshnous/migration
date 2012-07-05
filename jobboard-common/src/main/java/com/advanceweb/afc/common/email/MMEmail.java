package com.advanceweb.afc.common.email;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:21 PM
 */
public interface MMEmail {

	public EmailDTO m_EmailDTO=null;

	/**
	 * 
	 * @param emailDTO
	 */
	public void sendEmail(EmailDTO emailDTO);

}