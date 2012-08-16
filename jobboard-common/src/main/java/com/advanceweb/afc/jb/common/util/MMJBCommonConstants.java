package com.advanceweb.afc.jb.common.util;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public interface MMJBCommonConstants {
	public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z]).{6,20})";
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
	int ZERO_INT = 0;
	String NULL_STR = "null";
	public static final String USER_ID = "userId";
	String TEMP_SESSION_ID = "JS0011";
	String SPACE_OPN_BRCKT = " (";
	String CLSG_BRCKT = ")";
	String EMPTY = "";
	String SLASH = "/";
	String PLUS = "+";
	String SLASH_SELECT_SLASH = "/select/?";
	String AMP = "&";
	String QUESTION_MARK = "?";
	String CLSD_BRACES = "}";
	String SPACE = " ";
	String EQUAL_TO = "=";
	String USER = "admin";
	String SELECT_SLASH_QUESTIONMARK = "select/?";
	String COMMA = ",";
	
	String KEYWORDS = "keywords";
	String CITY_STATE = "cityState";
	String RADIUS = "radius";
	public static final String AUTOLOAD = "autoload";
	String SESSION_ID = "sessionid";
	
	String SEARCH_SEQ = "search_seq";
	String QUERY_TYPE = "queryType";
	String CITY = "city";
	String STATE = "state";
	String COMPANY = "company";
	String POSTED_DT = "posted_dt";
	
	String B = ":b";
	String B_01 = ":b01";
	String B_02 = ":b02";
	String B_03 = ":b03";
	String B_04 = ":b04";
	String B_05 = ":b05";
	String B_06 = ":b06";
	
	String AD_TEXT = "AdText";
	String CAP_COMPANY = "Company";
	String JOB_TITLE = "JobTitle";
	String CAP_CITY = "City";
	String POSTED_DATE = "PostedDate";
	String APPLY_ONLINE = "ApplyOnline";
	String BLIND_AD = "BlindAd";
	String FACILITY_NAME = "FacilityName";
	String EMAIL_DISPLAY = "EmailDisplay";
	String EMAIL = "Email";
	String IS_INTERNATIONAL = "IsInternational";
	String IS_NATIONAL = "IsNational";
	String IS_FEATURED = "IsFeatured";
	String JOB_COUNT = "JobCount";
	String JOB_ID = "JobId";
	String JOB_NUMBER = "Job Number";
	String JOB_GEO = "Job Geo";
	String JOB_POSITION = "JobPosition";
	String JOB_GEO_0_LATLON = "jobGeo0LatLon";
	String JOB_GEO_1_LATLON = "jobGeo1LatLon";
	String URL_DISPLAY = "URLDisplay";
	String URL = "url";
	
	String TOTAL_NO_RECORDS = "TotalNoRecords";
	String JSON_ROWS = "jsonRows";
	
	String DEF_TYPE = "defType";
	String SELECT = "select";
	String SO_TIMEOUT = "sotimeout";
	String CONNECTION_TIMEOUT = "connectiontimeout";
	String MAX_CONNECTION_HOST = "maxconnectionperhost";
	String MAX_TOTAL_CONNECTION = "maxtotalconnection";
	String FOLLOW_REDIRECTS = "followredirects";
	String ALLOW_COMPRESSION = "allowcompression";
	String MAX_RETRIES = "maxretries";
	
	String QF = "qf";
	String PF = "pf";
	String PS = "ps";
	String MM = "mm";
	String BQ = "bq";
	String BF = "bf";
	String SORT = "sort";
	String Q_ALT = "q.alt";
	String ROWS = "rows";
	String START = "start";
	String Q = "q";
	String FQ = "fq";
	
	String LOCATION = "LOCATION";
	String KEYWORD = "KEYWORD";

	//Solr Date pattern
	
	String SOLR_DATE_PATTERN = "E MMM dd hh:mm:ss Z yyyy";
	String REQ_SOLR_DATE_PATTERN = "MMM-dd-yyyy";
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
	public static final String FACILITY_ADMIN="facility_admin";
	public static final String FACILITY_USER="facility_user";
	public static final String ROLE_MERION_ADMIN="ROLE_MERION_ADMIN";
	public static final String ROLE_JOB_SEEKER="ROLE_JOB_SEEKER";
	public static final String ROLE_FACILITY_ADMIN="FACILITY_ADMIN";
	public static final String ROLE_FACILITY_USER="ROLE_FACILITY_USER";

	// Payment Gateway
	public static final String CREDIT_CARD = "creditCard";
	public static final String INVOICE = "invoice";

	//For Successful Login
	public static final String USER_EMAIL="userEmail";
	public static final String USER_NAME="userName";
	
	//For Duplicate Submission
	public static final String LAST_PLACE_KEY="LAST_PLACE_KEY";
	
}
