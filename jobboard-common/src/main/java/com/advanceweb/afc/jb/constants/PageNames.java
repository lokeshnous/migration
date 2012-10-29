package com.advanceweb.afc.jb.constants;

/**
 * This interface is used to define the name of the application pages.
 * Application pages are part of the Context information passed by the client
 * applications to various services related to content, ad, SEO etc. This is not
 * a comprehensive list of pages in the application and are only a handy way to
 * identify the pages. For this reason, this is not an enumeration. Only those
 * pages that has meaning on a context is included in this list. While clients
 * may pass any string to identify the page names, but the consumers are not
 * guaranteed to recognize them. So it is important to define the page names in
 * this interface and access them through the constants.
 * 
 * @author sukeshnambiar
 * 
 */
public interface PageNames {
	/**
	 * The jobboard application home page.
	 */
	String HOME = "home";
	/**
	 * Page for displaying the job details
	 */
	String JOB_VIEW = "jobview";

	/**
	 * The login page for job seeker
	 */
	String JOBSEEKER_lOGIN = "login_jobseeker";

	/**
	 * The Registration page for job seeker
	 */
	String JOBSEEKER_REGISTRATION = "registration_jobseeker";

	/**
	 * The Jobseeker dashboard
	 */
	String JOBSEEKER_DASHBOARD = "dashboard_jobseeker";

	/**
	 * The Job seeker resume view page
	 */
	String JOBSEEKER_RESUME_VIEW = "jobseeker_resume_view";

	/**
	 * The Login page for Employer
	 */
	String EMPLOYER_LOGIN = "login_employer";

	/**
	 * The Registration page for Employer
	 */
	String EMPLOYER_REGISTRATION = "registration_employer";

	/**
	 * The Employer dashboard
	 */
	String EMPLOYER_DASHBOARD = "dashboard_employer";
	
	/**
	 * The Employer Job post
	 */
	String EMPLOYER_JOBPOST = "employer_jobpost";

	/**
	 * The Login page for Agency
	 */
	String AGENCY_LOGIN = "login_agency";

	/**
	 * The Registration page for Agency
	 */
	String AGENCY_REGISTRATION = "registration_agency";

	/**
	 * The Agency dashboard
	 */
	String AGENCY_DASHBOARD = "dashboard_agency";

}
