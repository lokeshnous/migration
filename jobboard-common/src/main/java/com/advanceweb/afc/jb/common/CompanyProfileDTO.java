package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:27 PM
 */
public class CompanyProfileDTO {

	private String companyName;
	private String companyOverview;
	private String companyWebsite;
	private String companyEmail;
	private String companyNews;
	private String positionTitle;

	public CompanyProfileDTO() {

	}

	public void finalize() throws Throwable {

	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyOverview() {
		return companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyNews() {
		return companyNews;
	}

	public void setCompanyNews(String companyNews) {
		this.companyNews = companyNews;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	
}