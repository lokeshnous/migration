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
	 * Page for displaying the premium job details
	 */
	String PREMIUM_JOB_VIEW = "premium_jobview";

	/**
	 * Page for displaying the featured employers
	 */
	String FEATURED_EMPS = "featured_employers";

	/**
	 * Page for displaying the featured employer detail
	 */
	String FEATURED_EMP = "featured_employer";

	/**
	 * The login page for job seeker
	 */
	String JOBSEEKER_LOGIN = "login_jobseeker";

	/**
	 * The second Registration page for job seeker 
	 */
	String JOBSEEKER_REGISTRATION_INFO = "registration_info_jobseeker";

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
	String JOBSEEKER_RESUME_VIEW = "resume_view_jobseeker";

	/**
	 * The Job seeker basic job search page
	 */
	String JOBSEEKER_JOB_SEARCH = "job_search_jobseeker";

	/**
	 * The Job seeker advance job search page
	 */
	String JOBSEEKER_ADVC_JOB_SEARCH = "advance_job_search_jobseeker";

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
	 * The Employer Job post page
	 */
	String EMPLOYER_JOBPOST = "jobpost_employer";
	
	/**
	 * The Employer manage Job post page
	 */
	String EMPLOYER_MANAGE_JOBPOST = "manage_jobpost_employer";
	
	/**
	 * The Employer manage featured employer page
	 */
	String EMPLOYER_MANAGE_FEATURED_EMP = "manage_featured_employer";
	
	/**
	 * The Employer manage Job seeker page
	 */
	String EMPLOYER_MANAGE_JOBSEEKER = "manage_jobseeker_employer";
	
	/**
	 * The Employer registration post job page
	 */
	String EMPLOYER_POSTJOB_REG = "registration_postjob_employer";

	/**
	 * The Payment Gateway conclusion page
	 */
	String EMPLOYER_PG_CONCLUSION = "paymentgateway_conclusion_employer";

	/**
	 * The Payment Gateway method page
	 */
	String EMPLOYER_PG_METHOD = "paymentgateway_method_employer";
	
	/**
	 * The Payment Gateway confirmation order page
	 */
	String EMPLOYER_PG_CONFIRMORDER = "paymentgateway_confirmorder_employer";
	
	/**
	 * The Payment Gateway billing page
	 */
	String EMPLOYER_PG_BILLING = "paymentgateway_billing_employer";

	/**
	 * The Branding Template page for employer
	 */
	String EMPLOYER_BTEMPLATE = "branding_template_employer";
	
	/**
	 * The advance resume search page
	 */
	String EMP_ADV_RESUME_SEARCH = "advance_resume_search_employer";
	
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
	
	/**
	 * The login page for admin
	 */
	String ADMIN_LOGIN = "login_admin";
	
	/**
	 * The browse category page for job seeker
	 */
	String JOBSEEKER_BROWSE_JOB_TITLES = "browse_jobtitle_jobseekers";
	
	/**
	 * The browse category page for job seeker
	 */
	String JOBSEEKER_SITE_MAP = "sitemap_jobseekers";

	/**
	 * The browse category page for job seeker
	 */
	String JOBSEEKER_BROWSE_JOBS = "browse_jobs_jobseekers";
	
}
