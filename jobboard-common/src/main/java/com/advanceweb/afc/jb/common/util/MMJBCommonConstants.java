package com.advanceweb.afc.jb.common.util;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public interface MMJBCommonConstants {
	String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z]).{8,20})";
	// String MOBILE_PATTERN = "((?=.*\\d).{10})";
	String MOBILE_PATTERN = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
	String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String MMDDYYYY_PATTERN = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
	String NUMERICS_PATTERN = "[\\p{Digit}&&[123456789]]+";
	String RESUME_TYPE_RESUME_BUILDER = "ADVANCE Resume Builder";
	String RESUME_TYPE_UPLOAD = "Upload Existing Resume";
	String RESUME_TYPE_COPY_PASTE = "Copy and Paste";
	// For DropDowns
	String RESUME_TYPE = "ResumeType";
	String DESIRED_JOB_TITLE = "JobTitle";
	String EMPLOYMENT_TYPE = "EmploymentType";
	String PHONE_TYPE = "PhoneType";
	String CAREER_LEVEL = "CareerLevel";
	String ANNUAL_SALARY = "AnnualSalary";
	String LANGUAGE_TYPE = "Language";
	String LANGUAGE_PROFICIENCY_TYPE = "LanguageProficiencyLevel";
	String WORK_AUTH_US = "WorkAuthorization";
	String RELOCATE = "Relocate";
	String RESUME_VISIBILITY = "ResumeVisibility";
	String VISIBILITY = "Visibility";

	// Relocate
	String RELOCATE_YES = "Yes";
	String RELOCATE_NO = "No";

	// Resume Visibility
	String VISIBILITY_PUBLIC = "1";
	String VISIBILITY_PRIVATE = "0";

	long RESUME_MAX_SIZE = 50; // 750KB

	String FILE_TYPE_DOC = "doc";
	String FILE_TYPE_DOCX = "docx";
	String FILE_TYPE_PDF = "pdf";

	// For SOLR search
	int ZERO_INT = 0;
	String NULL_STR = "null";
	String TEMP_SESSION_ID = "JS0011";
	// String SPACE_OPN_BRCKT = " (";
	// String CLSG_BRCKT = ")";
	String EMPTY = "";
	// String CLSD_BRACES = "}";
	// String SPACE = " ";
	String EQUAL_TO = "=";
	String USER = "admin";
	// String SELECT_SLASH_QUESTIONMARK = "select/?";
	String COMMA = ",";

	String AUTOLOAD = "autoload";
	String SAVE_SEARCH_ID = "saveSearchId";

	String CITY = "city";
	String STATE = "state";
	// String COMPANY = "company";

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

	// newly added field
	String TEMPLATE_ID_STRING = "TemplateId";
	String PACKAGE_NAME_STRING = "PackageName";
	String IS_PREMIUM_STRING = "IsPremium";
	String IS_UNIVERSAL_GEO_STRING = "IsUniversalGeo";
	String HIDE_CITY_STRING = "HideCity";
	String HIDE_STATE_STRING = "HideState";
	String HIDE_POSCODE_STRING = "HidePostcode";
	String HIDE_COUNTRY_STRING = "HideCountry";
	String COUNTRY_STRING = "Country";

	String TOTAL_NO_RECORDS = "TotalNoRecords";
	String JSON_ROWS = "jsonRows";

	String SELECT = "select";

	// Search type Names
	String LOCATION_SEARCH = "LOCATION";
	String KEYWORD_SEARCH = "KEYWORD";

	// Solr Date pattern
	String JSON_DATE_FORMAT = "MMM-dd-yyyy";
	// For Resume

	String POST_RESUME = "postresume";

	// Date Patterns
	String DATE_PATTERN = "yyyy-MM-dd";
	String DISP_DATE_PATTERN = "MM/dd/yyyy";
	String NEWDATE_PATTERN = "EEE MMM d HH:mm:ss z yyyy";
	String SQL_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

	String ERROR_STRING = "error";
	String OK_STRING = "ok";

	String CLICKTYPE_VIEW = "view";
	String CLICKTYPE_APPLY = "apply";
	String CLICKTYPE_CLICK = "click";
	String DROP_DOWN = "Dropdown";
	String CHECK_BOX = "CheckBox";
	String RADIO_BUTTON = "Radio";
	String LABEL_COUNTRY = "Country";
	String LABEL_STATE = "State / Province";
	String LABEL_SUSBSCRIPTION = "Subscriptions";
	// If you change the following value, you should change in
	// mer_profile_attrib as well.
	// Colum name Screen Name
	String JOB_SEEKER_REGISTRATION = "JobSeeker Registration";
	String JOB_SEEKER_PROFILE_SETTINGS = "JobSeeker Edit Profile Settings";
	String FIRST_NAME = "First Name";
	String LAST_NAME = "Last Name";
	String MIDDLE_NAME = "Middle Name";
	String EMAIL_ADDRESS = "E-Mail Address";
	String PHONE_NUMBER = "Phone Number";
	String ZERO = "0";
	// for employer
	String POSITION_TITLE = "Position Title";
	String STREET_ADD = "Street Address";
	String ZIP_CODE = "Zip Code";
	String PRIMARY_PHONE = "Primary Phone";
	String SECONDARY_PHONE = "Secondary Phone";
	String COUNTRY = "Country";
	String CITY_EMP = "City";
	String STATE_PROVINCE = "State / Province";
	String COMPANY_EMP = "Company";
	String NS_CUSTOMER_ID = "ns_customer_id";

	// For Save this
	String SEARCH_TYPE = "searchtype";
	String SEMICOLON = ";";
	String LANGUAGE_ENGLISH = "English";
	String REFERENCE_TYPE_PERSONAL = "Personal";
	String BASIC_SEARCH_TYPE = "basic";
	String SAVE_SEARCH_NAME = "saveSearchName";

	// For User Roles
	String MERION_ADMIN = "merion_admin";
	String JOBSEEKER = "jobseeker";
	String JOB_SEEKER = "jobSeeker";
	String EMPLOYER = "employer";
	String AGENCY = "agency";
	String FACILITY_ADMIN = "facility_admin";
	String FACILITY_USER = "facility_user";
	String ROLE_MERION_ADMIN = "ROLE_MERION_ADMIN";
	String ROLE_JOB_SEEKER = "ROLE_JOB_SEEKER";
	String ROLE_FACILITY_ADMIN = "ROLE_FACILITY_ADMIN";
	String ROLE_FACILITY_USER = "ROLE_FACILITY_USER";
	String FACILITY = "FACILITY";
	String FACILITY_GROUP = "FACILITY_GROUP";
	String FACILITY_SYSTEM = "FACILITY_SYSTEM";
	String ROLE_FACILITY = "ROLE_FACILITY";
	String ROLE_FACILITY_GROUP = "ROLE_FACILITY_GROUP";
	String ROLE_FACILITY_SYSTEM = "ROLE_FACILITY_SYSTEM";

	// For Login Failure URL
	String JOBSEEKER_LOGIN_FAILURE_URL = "/commonLogin/login.html?error=true&page=jobSeeker";
	String EMPLOYER_LOGIN_FAILURE_URL = "/commonLogin/login.html?error=true&page=employer";
	String AGENCY_LOGIN_FAILURE_URL = "/commonLogin/login.html?error=true&page=agency";

	// For Logout URL
	String JOBSEEKER_LOGOUT_URL = "/commonLogin/login.html?page=jobSeeker";
	String EMPLOYER_LOGOUT_URL = "/commonLogin/login.html?page=employer";
	String AGENCY_LOGOUT_URL = "/commonLogin/login.html?page=agency";

	// Payment Gateway
	String CREDIT_CARD = "ccp";
	String INVOICE = "inv";

	// For Successful Login
	String USER_ID = "userId";
	String USER_EMAIL = "userEmail";
	String USER_NAME = "userName";
	String PAGE_VALUE = "pageValue";
	String FACILITY_ID = "facilityId";

	// For Duplicate Submission
	String LAST_PLACE_KEY = "LAST_PLACE_KEY";

	String PERFORM_SAVED_SEARCH = "performSearch";

	// For Account Setting
	String PRIMARY = "PRIMARY";
	String BILLING = "BILLING";

	// For Post New Job
	String POST_NEW_JOB = "Active";
	String POST_JOB_SCHEDULED = "Scheduled";
	String POST_JOB_DRAFT = "Draft";
	String POST_JOB_EXPIRED = "Expired";
	String POST_JOB_INACTIVE = "Inactive";
	byte ACTIVE = 1;
	byte INACTIVE = 0;

	// For Apply job type
	String APPLY_TO_URL = "ApplyToURL";
	String APPLY_TO_ATS = "ApplyToATS";
	String APPLY_TO_EMAIL = "ApplyToEMail";

	// for packages
	String PACKAGE_ESPOST = "ezpost";
	String PACKAGE_GOLD = "gold";
	String PACKAGE_PLATINUM = "platinum";
	String PACKAGE_JBPOSTSLOT = "jbPostSlot";
	String PACKAGE_STJOBPOSTING = "stJobPosting";
	String PACKAGE_SILVER = "silver";

	// for Branding template
	String IMAGE_TYPE_JPG = ".jpg";
	String IMAGE_TYPE_GIF = ".gif";
	String IMAGE_TYPE_PNG = ".png";
	String IMAGE_TYPE_TIF = ".tif";

	// Media file format-Manage Featured Employer Profile
	String VIDEO_TYPE_MOV = ".mov";
	String VIDEO_TYPE_MPG = ".mpg";
	String MEDIA_TYPE_AVI = ".avi";
	String MEDIA_TYPE_WMV = ".wmv";
	String MEDIA_TYPE_MPEG = ".mpeg";
	String MEDIA_TYPE_MPEG_4 = ".mp4";
	String MEDIA_TYPE_PPT = ".ppt";
	String MEDIA_TYPE_PPTX = ".pptx";
	String USER_DTO = "USER_DTO";

	String RECAPTCHA_PRIVATE_KEY = "6Lel19USAAAAAHC7mqzT-Q0WpThoqiKr0DnhYtpN";

	int AUTO_RENEWAL_DAYS = 29;
	// For Job search results
	int DEFAULT_PAGE_SIZE = 20;
	int ADDS_PER_PAGE = 10;
	String SEARCH_RESULTS_LIST = "searchResultsList";
	String NO_OF_PAGES = "noOfPages";
	String CURRENT_PAGE = "currentPage";
	String RECORDS_PER_PAGE = "displayRecordsPerPage";
	String RECORDS_COUNT = "totalNoOfRecords";
	String START_ROW = "startRow";
	String END_ROW = "endRow";
	String BEGIN_VAL = "beginVal";
	String BEGIN = "begin";
	String PAGE = "page";
	// Kartik Add many Variable for account setting and Mail send
	String WEB_MAIL_SERVER = "merion@nousinfosystems.com";
	String EMAIL_MESSAGE = "Please enter correct Email address";
	String EMAIL_NULL_MESSAGE = "Email Id already Exists!";
	String PHONE_NO = "Please enter the correct Phone Number (XXX-XXX-XXXX)";
	String PHONE_NULL_NO = "Please enter the Phone number";
	String EMAIL_MESSAGE_BLANK = "Please enter required fields.";
	String UPDATE_ERROR = " Error occurred. Please contact your Administrator.";
	// Job Types
	String JOB_TYPE = "JOB_TYPE";
	String JOB_TYPE_ADDON = "ADDON";
	String JOB_TYPE_COMBO = "JOB_TYPE_COMBO";

	String NEXT = "next";
	int[] FILTER_VALS = { 20, 30, 40, 50 };

	// For jobboard links
	String FOLLOWUP_LINK_FACEBOOK = "followuplinkfacebook";
	String FOLLOWUP_LINK_TWITTER = "followuplinktwitter";
	String FOLLOWUP_LINK_YOUTUBE = "followuplinkyoutube";
	String FOLLOWUP_LINK_LINKEDIN = "followuplinklinkedin";
	// Manage access permission
	String FULL_ACCESS = "5";
	String MANAGEEDITACCESS = "6";

	String DATABASE_ERROR_CODE = "Database failure occured.Please contact your technical support team to resolve the issue.";
	String UNIQUEKEY_PRIMARYKEY_VIOLATED = "Unique Key/Primary Key violated in database.Please contact your Database Administrator.";
	String SYSTEM_ERROR_CODE = "System encountered unexpected error.Please contact your techinal support team to resolve the issue.";
	int EMPLOYER_ROLE_ID = 3;
	String PHONE = "Phone";

	// Added for set alerts task
	String SET_ALERT = "setAlertPage";
	String DO_NOT_HAVE_CREDITS = "You do not have sufficient credits to post the job";
	String DO_NOT_HAVE_CREDITS_REPOST = "You do not have sufficient credits to repost the job";
	String STATUS_ACTIVE = "Active";
	String STATUS_INACTIVE = "Inactive";

	// Added for inventory
	String DAYS = "days";
	String STANDARD_JOB_POSTING = "Standard Job Posting";
	String JOB_POSTING_SLOT = "Job Posting Slot";
	String BASIC_JOB_TYPE = "Basic";
	String INVENTORY = "inventoryPage";
	String PURCHASE_JOB_POST_FORM = "purchaseJobPostForm";
	int PLAN_DAYS = 30;
	String TOTAL_ACTIVE_JOB_STRING = "TotalNoOfActiveJobs";

	// FOR MAIL RELATED
	String SUBJECT_OF_MAIL = "A job opportunity sent to you by";
	String BODY_OFMAIL_FIRST = "Here’s a job opportunity that ";
	String BODY_OFMAIL_SECOND = " thought might interest you.";
	String JOB_TITLE_HEADING = "Job title:";
	String COMAPNY_NAME_HEADING = "Company name:";
	String URL_LINK_FIRST = "<a href=?";
	String URL_LINK_SECOND = "><b>View this job now</b></a> to learn more and submit your application.";
	String URL_REDIRECT_MAIL = "redirect:/healthcarejobs/advanceweb.html";
	String ERROR_SENDING_MAIL = "ERROR For sending mail option of SendToFriend method";

}
