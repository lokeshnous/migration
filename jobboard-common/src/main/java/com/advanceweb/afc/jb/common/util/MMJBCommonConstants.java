package com.advanceweb.afc.jb.common.util;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public interface MMJBCommonConstants {
	public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z]).{8,20})";
	//public static final String MOBILE_PATTERN = "((?=.*\\d).{10})";
	public static final String MOBILE_PATTERN = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String MMDDYYYY_PATTERN="^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
	public static final String NUMERICS_PATTERN = "[\\p{Digit}&&[123456789]]+";
	public static final String RESUME_TYPE_RESUME_BUILDER = "ADVANCE Resume Builder";
	public static final String RESUME_TYPE_UPLOAD = "Upload Existing Resume";
	public static final String RESUME_TYPE_COPY_PASTE = "Copy and Paste";
	// For DropDowns
	public static final String RESUME_TYPE = "ResumeType";	
	public static final String DESIRED_JOB_TITLE = "JobTitle";
	public static final String EMPLOYMENT_TYPE = "EmploymentType";
	public static final String PHONE_TYPE = "PhoneType";
	public static final String CAREER_LEVEL = "CareerLevel";
	public static final String ANNUAL_SALARY = "AnnualSalary";
	public static final String LANGUAGE_TYPE = "Language";
	public static final String LANGUAGE_PROFICIENCY_TYPE = "LanguageProficiencyLevel";
	public static final String WORK_AUTH_US = "WorkAuthorization";
	public static final String RELOCATE = "Relocate";
	public static final String RESUME_VISIBILITY = "ResumeVisibility" ;
	public static final String VISIBILITY = "Visibility" ;
	
	//Relocate
	public static final String RELOCATE_YES = "Yes" ;
	public static final String RELOCATE_NO = "No" ;
	
	//Resume Visibility
	public static final String VISIBILITY_PUBLIC = "1" ;
	public static final String VISIBILITY_PRIVATE = "0";
	
	public static final long RESUME_MAX_SIZE = 50; // 750KB
	
	public static final String FILE_TYPE_DOC = "doc";
	public static final String FILE_TYPE_DOCX = "docx";
	public static final String FILE_TYPE_PDF = "pdf";

	// For SOLR search
	public static final int ZERO_INT = 0;
	public static final String NULL_STR = "null";
	public static final String TEMP_SESSION_ID = "JS0011";
	public static final String SPACE_OPN_BRCKT = " (";
	public static final String CLSG_BRCKT = ")";
	public static final String EMPTY = "";
	public static final String SLASH = "/";
	public static final String PLUS = "+";
	public static final String SLASH_SELECT_SLASH = "/select/?";
	public static final String AMP = "&";
	public static final String QUESTION_MARK = "?";
	public static final String CLSD_BRACES = "}";
	public static final String SPACE = " ";
	public static final String EQUAL_TO = "=";
	public static final String USER = "admin";
	public static final String SELECT_SLASH_QUESTIONMARK = "select/?";
	public static final String COMMA = ",";
	
	public static final String KEYWORDS = "keywords";
	public static final String CITY_STATE = "cityState";
	public static final String RADIUS = "radius";
	public static final String AUTOLOAD = "autoload";
	public static final String SAVE_SEARCH_ID = "saveSearchId";
	public static final String SESSION_ID = "sessionid";
	
	public static final String SEARCH_SEQ = "search_seq";
	public static final String SEARCH_NAME = "searchName";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COMPANY = "company";
	public static final String POSTED_DT = "posted_dt";
	
	public static final String B = ":b";
	public static final String B_01 = ":b01";
	public static final String B_02 = ":b02";
	public static final String B_03 = ":b03";
	public static final String B_04 = ":b04";
	public static final String B_05 = ":b05";
	public static final String B_06 = ":b06";
	
	public static final String AD_TEXT = "AdText";
	public static final String CAP_COMPANY = "Company";
	public static final String JOB_TITLE = "JobTitle";
	public static final String CAP_CITY = "City";
	public static final String POSTED_DATE = "PostedDate";
	public static final String APPLY_ONLINE = "ApplyOnline";
	public static final String BLIND_AD = "BlindAd";
	public static final String FACILITY_NAME = "FacilityName";
	public static final String EMAIL_DISPLAY = "EmailDisplay";
	public static final String EMAIL = "Email";
	public static final String IS_INTERNATIONAL = "IsInternational";
	public static final String IS_NATIONAL = "IsNational";
	public static final String IS_FEATURED = "IsFeatured";
	public static final String JOB_COUNT = "JobCount";
	public static final String JOB_ID = "JobId";
	public static final String JOB_NUMBER = "Job Number";
	public static final String JOB_GEO = "Job Geo";
	public static final String JOB_POSITION = "JobPosition";
	public static final String JOB_GEO_0_LATLON = "jobGeo0LatLon";
	public static final String JOB_GEO_1_LATLON = "jobGeo1LatLon";
	public static final String URL_DISPLAY = "URLDisplay";
	public static final String URL = "url";
	
	public static final String TOTAL_NO_RECORDS = "TotalNoRecords";
	public static final String JSON_ROWS = "jsonRows";
	
	public static final String DEF_TYPE = "defType";
	public static final String SELECT = "select";
	public static final String SO_TIMEOUT = "sotimeout";
	public static final String CONNECTION_TIMEOUT = "connectiontimeout";
	public static final String MAX_CONNECTION_HOST = "maxconnectionperhost";
	public static final String MAX_TOTAL_CONNECTION = "maxtotalconnection";
	public static final String FOLLOW_REDIRECTS = "followredirects";
	public static final String ALLOW_COMPRESSION = "allowcompression";
	public static final String MAX_RETRIES = "maxretries";
	
	public static final String QF = "qf";
	public static final String PF = "pf";
	public static final String PS = "ps";
	public static final String MM = "mm";
	public static final String BQ = "bq";
	public static final String BF = "bf";
	public static final String SORT = "sort";
	public static final String Q_ALT = "q.alt";
	public static final String ROWS = "rows";
	public static final String START = "start";
	public static final String Q = "q";
	public static final String FQ = "fq";
	
	public static final String LOCATION = "LOCATION";
	public static final String KEYWORD = "KEYWORD";

	//Solr Date pattern
	
	public static final String SOLR_DATE_PATTERN = "E MMM dd hh:mm:ss Z yyyy";
	public static final String REQ_SOLR_DATE_PATTERN = "MMM-dd-yyyy";
	//For Resume
	
	public static final String POST_RESUME = "postresume";

	// Date Patterns
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DISP_DATE_PATTERN = "MM/dd/yyyy";
	public static final String NEWDATE_PATTERN = "EEE MMM d HH:mm:ss z yyyy";
	public static final String SQL_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss"; 

	public static final String ERROR_STRING = "error";
	public static final String OK_STRING = "ok";
	
	public static final String CLICKTYPE_VIEW = "view";
	public static final String CLICKTYPE_APPLY = "apply";
	public static final String CLICKTYPE_CLICK = "click";
	public static final String DROP_DOWN = "Dropdown";
	public static final String CHECK_BOX = "CheckBox";
	public static final String RADIO_BUTTON = "Radio";
	public static final String LABEL_COUNTRY = "Country";
	public static final String LABEL_STATE = "State / Province";
	public static final String LABEL_SUSBSCRIPTION = "Subscriptions";
	//If you change the following value, you should change in mer_profile_attrib as well.
	//Colum name Screen Name
	public static final String JOB_SEEKER_REGISTRATION="JobSeeker Registration";
	public static final String JOB_SEEKER_PROFILE_SETTINGS="JobSeeker Edit Profile Settings";
	public static final String FIRST_NAME="First Name";
	public static final String LAST_NAME="Last Name";
	public static final String MIDDLE_NAME="Middle Name";
	public static final String EMAIL_ADDRESS="E-Mail Address";
	public static final String PHONE_NUMBER="Phone Number";
	public static final String ZERO = "0";
	//for employer 
	public static final String position_title = "Position Title";
	public static final String street_add = "Street Address";
	public static final String zip_code = "Zip Code";
	public static final String primary_phone = "Primary Phone";
	public static final String secondary_phone = "Secondary Phone";
	
	
	//For Save this 
	public static final String SEARCH_TYPE = "searchtype";
	public static final String SEMICOLON = ";";
	public static final String LANGUAGE_ENGLISH="English";
	public static final String REFERENCE_TYPE_PERSONAL="Personal";
	public static final String BASIC_SEARCH_TYPE = "basic";
	public static final String SAVE_SEARCH_NAME = "saveSearchName";
	

	//For User Roles
	public static final String MERION_ADMIN ="merion_admin";
	public static final String JOBSEEKER="jobseeker";
	public static final String JOB_SEEKER="jobSeeker";
	public static final String EMPLOYER="employer";
	public static final String AGENCY="agency";
	public static final String FACILITY_ADMIN="facility_admin";
	public static final String FACILITY_USER="facility_user";
	public static final String ROLE_MERION_ADMIN="ROLE_MERION_ADMIN";
	public static final String ROLE_JOB_SEEKER="ROLE_JOB_SEEKER";
	public static final String ROLE_FACILITY_ADMIN="ROLE_FACILITY_ADMIN";
	public static final String ROLE_FACILITY_USER="ROLE_FACILITY_USER";
	public static final String FACILITY="FACILITY";
	public static final String FACILITY_GROUP="FACILITY_GROUP";
	public static final String FACILITY_SYSTEM="FACILITY_SYSTEM";
	public static final String ROLE_FACILITY="ROLE_FACILITY";
	public static final String ROLE_FACILITY_GROUP="ROLE_FACILITY_GROUP";
	public static final String ROLE_FACILITY_SYSTEM="ROLE_FACILITY_SYSTEM";
	
	
	
	
	//For Login Failure URL
	public static final String JOBSEEKER_LOGIN_FAILURE_URL="/commonLogin/login.html?error=true&page=jobSeeker";
	public static final String EMPLOYER_LOGIN_FAILURE_URL="/commonLogin/login.html?error=true&page=employer";
	public static final String AGENCY_LOGIN_FAILURE_URL="/commonLogin/login.html?error=true&page=agency";
	
	//For Logout URL
		public static final String JOBSEEKER_LOGOUT_URL="/commonLogin/login.html?page=jobSeeker";
		public static final String EMPLOYER_LOGOUT_URL="/commonLogin/login.html?page=employer";
		public static final String AGENCY_LOGOUT_URL="/commonLogin/login.html?page=agency";
	

	// Payment Gateway
	public static final String CREDIT_CARD = "creditCard";
	public static final String INVOICE = "invoice";

	//For Successful Login
	public static final String USER_ID = "userId";
	public static final String USER_EMAIL="userEmail";
	public static final String USER_NAME="userName";
	public static final String PAGE_VALUE="pageValue";
	
	//For Duplicate Submission
	public static final String LAST_PLACE_KEY="LAST_PLACE_KEY";
	
	public static final String PERFORM_SAVED_SEARCH = "performSearch";
	
	//For Account Setting 
	public static final String PRIMARY="PRIMARY";	
	public static final String BILLING = "BILLING";
	
}
