package com.advanceweb.afc.jb.webapp.web.forms.search;

import java.util.Date;

/**
 * <code>applyJobForm</code> is a form bean to apply job
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 12 July 2012
 * 
 */

public class applyJobForm {

	private String userId;
	private String useremail;
	private Date createdDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
