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
	String NUMERICS_PATTERN = "[\\p{Digit}&&[0123456789]]+";
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
	String MYPROFESSION = "My Profession";
	String PROFESSION_OTHERS = "Others";
	String MYINDUSTRY = "My Industry";
	String HEALTHCARE = "Health Care";

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
	String RETAIN_SEARCH = "retainSaveSearch";
	
	String CITY = "city";
	String STATE = "state";
	String COMPANY = "company";

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
	String JOB_STATUS = "jobStatus";

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
	String LOCATION_SEARCH = "LOCATION-FILTER-JB2";
	String KEYWORD_SEARCH = "KEYWORD-FILTER-JB2";
	String BROWSE_SEARCH = "BROWSE-JB2";

	// Solr param
	String SORT_PARAM = "sortParam";
	String FIRST_FQ_PARAM = "firstFQParam";
	String SECOND_FQ_PARAM = "secondFQParam";
	String THIRD_FQ_PARAM = "thirdFQParam";
	String FOURTH_FQ_PARAM = "fouthFQParam";
	String FIFTH_FQ_PARAM = "fifthFQParam";
	String POSTED_DT = "posted_dt";
	String SORT_ORDER = "sortOrder";
	String DESC_STR = "desc";
	String ASC_STR = "asc";
	String COUNT_STR = "count";
	String FACET_SORT = "facetSort";
	// Solr Date pattern
	String JSON_DATE_FORMAT = "MMM-dd-yyyy";
	// For Resume

	String POST_RESUME = "postresume";

	// Date Patterns
	String DATE_PATTERN = "yyyy-MM-dd";
	String DISP_DATE_PATTERN = "MM/dd/yyyy";
	String NEWDATE_PATTERN = "EEE MMM d HH:mm:ss z yyyy";
	String SQL_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
	String APP_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	String DISP_DATE_RANGE = "dd/MM/yyyy";
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
	String BASIC_SEARCH_TYPE_RESUME = "resume";
	String SAVE_SEARCH_NAME = "saveSearchName";

	// latest searches count
	int LATEST_SEARCHES_LIMIT = 3;
	
	// For User Roles
	String MERION_ADMIN = "merion_admin";
	String JOBSEEKER = "jobseeker";
	String JOB_SEEKER = "jobSeeker";
	String EMPLOYER = "employer";
	String MANAGEJOBSEEKER = "manageJobSeeker";
	String AGENCY = "agency";
	String FACILITY_ADMIN = "facility_admin";
	String FACILITY_USER = "facility_user";
	String FACILITY_FULL_ACCESS = "Full_Access";
	String FACILITY_POST_EDIT = "Post_Edit";
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
	String ROLE_FACILITY_FULL_ACCESS = "ROLE_FACILITY_FULL_ACCESS";
	String ROLE_FACILITY_POST_EDIT = "ROLE_FACILITY_POST_EDIT";

	// For Spring Security Remember Me Login(Cookie Name)
	String SPRING_SECURITY_REMEMBER_ME_COOKIE = "SPRING_SECURITY_REMEMBER_ME_COOKIE";

	// Payment Gateway
	String CREDIT_CARD = "ccp";
	String INVOICE = "inv";

	// For Successful Login
	String USER_ID = "userId";
	String USER_EMAIL = "userEmail";
	String USER_NAME = "userName";
	String PAGE_VALUE = "pageValue";
	String FACILITY_ID = "facilityId";
	String LOGIN_DATE_TIME = "dateTime";
	// For Agency Impersonation
	String AGENCY_USER_ID = "agencyUserId";
	String AGENCY_FACILITY_ID = "agencyFacilityId";
	String AGENCY_USER_NAME = "agencyUserName";
	String AGENCY_EMAIL = "agencyEmail";

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
	String STANDARD_POSTING = "Job Posting";
	String SLOT_POSTING = "Job Slot";
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

	//For offline purchased packages
	String GOLD_90 = "4809";
	String GOLD_180 = "4864";
	String GOLD_365 = "4808";
	String PLATINUM_90 = "4811";
	String PLATINUM_180 = "4865";
	String PLATINUM_365 = "4810";
	String SILVER_90 = "4813";
	String SILVER_180 = "4866";
	String SILVER_365 = "4812";
	String FEATURE_30 = "4867";
	String FEATURE_90 = "4868";
	String FEATURE_180 = "4870";
	String FEATURE_365 = "4869";
	String SCRAPE_90 = "947";
	String SCRAPE_180 = "913";
	String SCRAPE_365 = "4871";
	String XML_90 = "4872";
	String XML_180 = "963";
	String XML_365 = "976";
	
	// for Branding template
	String IMAGE_TYPE_JPG = ".jpg";
	String IMAGE_TYPE_GIF = ".gif";
	String IMAGE_TYPE_PNG = ".png";
	String IMAGE_TYPE_TIF = ".tif";
	int INT_SILVER = 1;
	int INT_GOLD = 2;
	int INT_PLATINUM = 3;
	String ADDITIONAL_IMAGE = "Additional Image";
	String VIDEO = "Video";
	int KILO_BYTE = 1000;
	int MEGA_BYTE = 1000000;

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
	int JOBSEACRH_DEFAULT_PAGE_SIZE = 20;
	int JOBSEACRH_FAST_FORWARD = 10;	
	int JOBSEARCH_GRID_PAGES_COUNT = 10;
	String FAST_FORWARD = "fastforward";
	String SEARCH_RESULTS_LIST = "searchResultsList";
	String NO_OF_PAGES = "noOfPages";
	String CURRENT_PAGE = "currentPage";
	String JOBSEACRH_PAGE_SIZE = "pageSize";
	String JOBSEACRH_PAGE_SORT = "isSorting";
	String SEARCHED_JOBSCOUNT = "searchedJobCount";
	String RESUME_RECORDS_COUNT = "totalNumberOfSearchedResume";
	String START_ROW = "startRow";
	String END_ROW = "endRow";
	String BEGIN_VAL = "beginVal";
	String BEGIN = "begin";
	String PAGE = "page";
	String CURRENT_SEARCH_LIST = "currentSearchList";
	String MILES = " Miles";
	String HASHMAP_KEY = "key";
	String HASHMAP_VALUE = "value";
	String PREV_JOB_SEARCH_KEYWORDS = "prevJobSearchKeywords";
	String RECORDS_PER_PAGE = "displayRecordsPerPage";
	String RECORDS_COUNT = "totalNoOfRecords";
	
	// Kartik Add many Variable for account setting and Mail send
	String WEB_MAIL_SERVER = "merion@nousinfosystems.com";
	String EMAIL_MESSAGE = "Please enter correct Email address.";
	String EMAIL_NULL_MESSAGE = "Email address already Exists!";
	String PHONE_NO = "Please enter the valid phone number in the following format(xxx) xxx-xxxx.";
	String PHONE_NULL_NO = "Please enter the phone number.";
	String EMAIL_MESSAGE_BLANK = "Please enter a required field.";
	String UPDATE_ERROR = " Error occurred. Please contact administrator.";
	// Job Types
	String JOB_TYPE = "JOB_TYPE";
	String JOB_TYPE_ADDON = "ADDON";
	String JOB_TYPE_COMBO = "JOB_TYPE_COMBO";

	// netsuite status code
	int STATUS_CODE_200 = 200;
	int STATUS_CODE_400 = 400;
	int STATUS_CODE_401 = 401;
	int STATUS_CODE_403 = 403;
	int STATUS_CODE_404 = 404;
	int STATUS_CODE_405 = 405;
	int STATUS_CODE_415 = 415;
	int STATUS_CODE_500 = 500;
	int STATUS_CODE_503 = 503;

	String SUCCESS_200 = "The RESTlet web service request was executed successfully.";
	String BAD_REQUEST_400 = "Invalid credit card information or billing address.";
	String UNAUTHORIZED_401 = "This is not a valid NetSuite login session for the RESTlet calls.";
	String FORBIDDEN_403 = "RESTlet request sent to invalid domain, meaning a domain other than https://rest.netsuite.com.";
	String NOT_FOUND_404 = "A RESTlet script is not defined in the RESTlet request.";
	String METHOD_NOT_ALLOWED_405 = "The RESTlet request method is not valid.";
	String UNSUPPORTED_MEDIA_TYPE_415 = "An unsupported content type was specified. (Only JSON and text are allowed.)";
	String INTERNAL_SERVER_ERROR_500 = "(unexpected errors): Occurs for non-user errors that cannot be recovered by resubmitting the same request. Contact Customer Support to file a case.";
	String SERVICE_UNAVAILABLE_503 = "The server is temporarily unavailable. Please contact your system administrator.";

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
	String AGEN_PER_PAGE = "agePermPage";

	String DATABASE_ERROR_CODE = "Database failed to support. Please contact your technical support team to resolve this issue.";
	String UNIQUEKEY_PRIMARYKEY_VIOLATED = "Unique Key/Primary Key violated in database.Please contact your Database Administrator.";
	String SYSTEM_ERROR_CODE = "System encountered unexpected error.Please contact your techinal support team to resolve the issue.";
	int EMPLOYER_ROLE_ID = 3;
	int JOBSEEKER_ROLE_ID = 2;
	String PHONE = "Phone";

	// Added for set alerts task
	String SET_ALERT = "setAlertPage";
	String DO_NOT_HAVE_CREDITS = "You do not have sufficient credits to post the job.";
	String DO_NOT_HAVE_CREDITS_REPOST = "You do not have sufficient credits to repost the job.";
	String STATUS_ACTIVE = "Active";
	String STATUS_INACTIVE = "Inactive";
	String STATUS_EXPIRED = "Expired";

	// Added for inventory
	String DAYS = "days";
	String STANDARD_JOB_POSTING = "30-Day Standard Job Posting";
	String JOB_POSTING_SLOT = "30-Day Job Posting Slot";
	String BASIC_JOB_TYPE = "Basic";
	String INVENTORY = "inventoryPage";
	String PURCHASE_JOB_POST_FORM = "purchaseJobPostForm";
	String PURCHASE_RESUME_SEARCH_FORM = "purchaseResumeSearchForm";
	// Purchase types
	String PURCHASE_JOB_POST = "jobPost";
	String PURCHASE_RESUME_SEARCH = "resumeSearch";
	// Product type
	String RESUME_SEARCH_PACKAGE = "RESUME_SEARCH";

	int PLAN_DAYS = 30;
	String TOTAL_ACTIVE_JOB_STRING = "TotalNoOfActiveJobs";
	String POST_JOB_PAGE = "postJobPage";
	int JOB_POST_TYPE_POSTING_ID = 1;
	int JOB_POST_TYPE_SLOT_ID = 2;
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

	// For Refine Search
	String SPACE_OPN_BRCKT = " (";
	String CLSG_BRCKT = ")";
	String DISPLAY_RADIUS = "displayRadius";
	String FQ_REFINE_KEYWORD = "{!tag=dt}";
	String FQ_REFINE_KEYWORD2 = "\"";
	String FQ_JOB_POSITION = "jobposition:\"";
	String FQ_COMPANY = "company:\"";
	String FQ_STATE = "state:\"";
	String FQ_CITY = "city:\"";
	String FQ_AREA = "area:\"";
	String REFINED = "refined";
	String REFINERADIUS = "refineRadius";
	String RADIUS = "radius";
	String AREA = "area";
	String METRO_AREA = "Metro Area";
	String BROWSE_BY_LOCATION_REG = "browseByLocationReg";

	// Added for Resume search
	String RESUME_LOCATION_SEARCH = "CANDIDATE-LOCATION";
	String RESUME_KEYWORD_SEARCH = "CANDIDATE-KEYWORD";
	String RESUME_SEARCH_SESSION_MAP = "resumeSessionMap";
	String IS_RESUME_PACKAGE_ACTIVE = "isResumePackageActive";

	// For Social Media sign up
	String FACEBOOK = "Facebook";
	String LINKEDIN = "LinkedIn";
	String FACEBOOK_PROFILE_ATTR_ID = "27";
	String LINKEDIN_PROFILE_ATTR_ID = "28";

	String RESUME_DESIRED_POSTION = "DesiredJobTitle";
	String APPLICANT_NAME = "ApplicantName";
	String LOCATION = "Location";
	String EXPERIENCE = "Experience";
	String UPLOAD_RESUME_ID = "UploadResumeId";
	String PUBLISH_RESUME_ID = "PublishResumeId";
	
	String RESUME_SEARCH_JSON_LIST = "resSrchJsonList";

	// for browseByJobs
	String FIRST_PARAMETER = "firstFQParam";

	// for reCapcha
	String PUBLIC_KEY = "6Lel19USAAAAAPRKVOy7gFpRBpn6iSPONG1o9ouZ";
	String PRIVATE_KEY = "6Lel19USAAAAAHC7mqzT-Q0WpThoqiKr0DnhYtpN";

	String MODULE_STRING = "module";
	String KEYWORD_STRING = "keywords";

	int PRINT_JS_SUBSCRIPTION = 1;
	int DIGITAL_JS_SUBSCRIPTION = 2;
	int ENEWS_JS_SUBSCRIPTION = 3;
	int EMAIL_JS_SUBSCRIPTION = 4;
	int DIGITAL_SUBSCRIPTION = 5;
	int ENEWS_LETTER_SUBSCRIPTION = 6;
	int EMAIL_SUBSCRIPTION = 7;

	// SEO info
	int JOBTITLES_GRID_PAGESIZE = 100;
	String JOBTITLES_NEXT_PAGE_URL = "nextPageUrl";
	String JOBTITLES_PREV_PAGE_URL = "prevPageUrl";
	int JOB_SEARCH_MAX_AREAS = 100;
	//Email Scheduler constants

	String SCHEDULER_DAY = "30DayEmailSch";
	String SOLR_PARAM_FQ="posted_dt:[NOW-?dayDAY TO NOW]";
	int DAILY_SCHEDULER=3;
	int MONTHLY_SCHEDULER=6;
	int WEEKLY_SCHEDULER=4;
	int BIWEEKLY_SCHEDULER=5;
	int YEARLY_SCHEDULER=7;
	String SCHEDULE_BIWEEKLY="15";
	String SCHEDULE_WEEKLY="7";
	String SCHEDULE_MONTHLY="30";
	String SCHEDULE_YEARLY="365";
	String SCHEDULE_DAILY="1";
	
	// variables to populate the ads
	String ADPAGETOP = "adPageTop";
	String ADPAGEBOTTOM = "adPageBottom";
	String ADPGRIGHT_TOP = "adPageRightTop";
	String ADPGRIGHT_MIDDLE = "adPageRightMiddle";
	String ADPGCENTER_MIDDLE_LIST = "adPageCenterMiddleList";
	String COUNTRY_USA = "USA";
	String COUNTRY_CA = "CA";
	int COUNTRY_USA_VAL = 182;
	int COUNTRY_CA_VAL = 32;
}
