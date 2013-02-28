/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common.util;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public interface MMJBCommonConstants {
	
	/** The password pattern. */
	String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z]).{8,20})";
	// String MOBILE_PATTERN = "((?=.*\\d).{10})";
	/** The numeric pattern. */
	String NUMERIC_PATTERN = "\\d+$";
	
	/** The mobile pattern. */
	String MOBILE_PATTERN = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
	
	/** The email pattern. */
	String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/** The mmddyyyy pattern. */
	String MMDDYYYY_PATTERN = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
	
	/** The numerics pattern. */
	String NUMERICS_PATTERN = "[\\p{Digit}&&[0123456789]]+";
	
	/** The resume type resume builder. */
	String RESUME_TYPE_RESUME_BUILDER = "ADVANCE Resume Builder";
	
	/** The resume type upload. */
	String RESUME_TYPE_UPLOAD = "Upload Existing Resume";
	
	/** The resume type copy paste. */
	String RESUME_TYPE_COPY_PASTE = "Copy and Paste";
	
	/** The ignore special char pattern. */
	String IGNORE_SPECIAL_CHAR_PATTERN = "[^a-zA-Z0-9\\s]+";
	// For DropDowns
	/** The resume type. */
	String RESUME_TYPE = "ResumeType";
	
	/** The desired job title. */
	String DESIRED_JOB_TITLE = "JobTitle";
	
	/** The employment type. */
	String EMPLOYMENT_TYPE = "EmploymentType";
	
	/** The phone type. */
	String PHONE_TYPE = "PhoneType";
	
	/** The career level. */
	String CAREER_LEVEL = "CareerLevel";
	
	/** The annual salary. */
	String ANNUAL_SALARY = "AnnualSalary";
	
	/** The language type. */
	String LANGUAGE_TYPE = "Language";
	
	/** The language proficiency type. */
	String LANGUAGE_PROFICIENCY_TYPE = "LanguageProficiencyLevel";
	
	/** The work auth us. */
	String WORK_AUTH_US = "WorkAuthorization";
	
	/** The relocate. */
	String RELOCATE = "Relocate";
	
	/** The resume visibility. */
	String RESUME_VISIBILITY = "ResumeVisibility";
	
	/** The visibility. */
	String VISIBILITY = "Visibility";
	
	/** The myprofession. */
	String MYPROFESSION = "My Profession";
	
	/** The profession others. */
	String PROFESSION_OTHERS = "Others";
	
	/** The myindustry. */
	String MYINDUSTRY = "My Industry";
	
	/** The healthcare. */
	String HEALTHCARE = "Health Care";

	// Relocate
	/** The relocate yes. */
	String RELOCATE_YES = "Yes";
	
	/** The relocate no. */
	String RELOCATE_NO = "No";

	// Resume Visibility
	/** The visibility public. */
	String VISIBILITY_PUBLIC = "1";
	
	/** The visibility private. */
	String VISIBILITY_PRIVATE = "0";

	/** The resume max size. */
	long RESUME_MAX_SIZE = 50; // 750KB

	/** The file type doc. */
	String FILE_TYPE_DOC = "doc";
	
	/** The file type docx. */
	String FILE_TYPE_DOCX = "docx";
	
	/** The file type pdf. */
	String FILE_TYPE_PDF = "pdf";

	// For SOLR search
	/** The zero int. */
	int ZERO_INT = 0;
	
	/** The null str. */
	String NULL_STR = "null";
	
	/** The temp session id. */
	String TEMP_SESSION_ID = "JS0011";
	// String SPACE_OPN_BRCKT = " (";
	// String CLSG_BRCKT = ")";
	/** The empty. */
	String EMPTY = "";
	// String CLSD_BRACES = "}";
	// String SPACE = " ";
	/** The equal to. */
	String EQUAL_TO = "=";
	
	/** The user. */
	String USER = "admin";
	// String SELECT_SLASH_QUESTIONMARK = "select/?";
	/** The comma. */
	String COMMA = ",";
	
	/** The commaspace. */
	String COMMASPACE = ", ";

	/** The autoload. */
	String AUTOLOAD = "autoload";
	
	/** The save search id. */
	String SAVE_SEARCH_ID = "saveSearchId";
	
	/** The retain search. */
	String RETAIN_SEARCH = "retainSaveSearch";
	
	/** The daily alert. */
	String DAILY_ALERT = "3";
	
	/** The city. */
	String CITY = "city";
	
	/** The state. */
	String STATE = "state";
	
	/** The company. */
	String COMPANY = "company";
	
	/** The company id name. */
	String COMPANY_ID_NAME = "companyIdName";

	/** The ad text. */
	String AD_TEXT = "AdText";
	
	/** The cap company. */
	String CAP_COMPANY = "Company";
	
	/** The job title. */
	String JOB_TITLE = "JobTitle";
	
	/** The job title encode. */
	String JOB_TITLE_ENCODE = "JobTitleEncode";
	
	/** The cap city. */
	String CAP_CITY = "City";
	
	/** The posted date. */
	String POSTED_DATE = "PostedDate";
	
	/** The apply online. */
	String APPLY_ONLINE = "ApplyOnline";
	
	/** The blind ad. */
	String BLIND_AD = "BlindAd";
	
	/** The facility name. */
	String FACILITY_NAME = "FacilityName";
	
	/** The email display. */
	String EMAIL_DISPLAY = "EmailDisplay";
	
	/** The email. */
	String EMAIL = "Email";
	
	/** The is international. */
	String IS_INTERNATIONAL = "IsInternational";
	
	/** The is national. */
	String IS_NATIONAL = "IsNational";
	
	/** The is featured. */
	String IS_FEATURED = "IsFeatured";
	
	/** The job count. */
	String JOB_COUNT = "JobCount";
	
	/** The job id. */
	String JOB_ID = "JobId";
	
	/** The job number. */
	String JOB_NUMBER = "Job Number";
	
	/** The job geo. */
	String JOB_GEO = "Job Geo";
	
	/** The job position. */
	String JOB_POSITION = "JobPosition";
	
	/** The JO b_ ge o_0_ latlon. */
	String JOB_GEO_0_LATLON = "jobGeo0LatLon";
	
	/** The JO b_ ge o_1_ latlon. */
	String JOB_GEO_1_LATLON = "jobGeo1LatLon";
	
	/** The url display. */
	String URL_DISPLAY = "URLDisplay";
	
	/** The url. */
	String URL = "url";
	
	/** The job status. */
	String JOB_STATUS = "jobStatus";
	
	/** The blind ad company txt. */
	String BLIND_AD_COMPANY_TXT = "Name Withheld";
	
	/** The uiniversal geo txt. */
	String UINIVERSAL_GEO_TXT = "Multiple Locations";

	// newly added field
	/** The template id string. */
	String TEMPLATE_ID_STRING = "TemplateId";
	
	/** The package name string. */
	String PACKAGE_NAME_STRING = "PackageName";
	
	/** The is premium string. */
	String IS_PREMIUM_STRING = "IsPremium";
	
	/** The is universal geo string. */
	String IS_UNIVERSAL_GEO_STRING = "IsUniversalGeo";
	
	/** The hide city string. */
	String HIDE_CITY_STRING = "HideCity";
	
	/** The hide state string. */
	String HIDE_STATE_STRING = "HideState";
	
	/** The hide poscode string. */
	String HIDE_POSCODE_STRING = "HidePostcode";
	
	/** The hide country string. */
	String HIDE_COUNTRY_STRING = "HideCountry";
	
	/** The country string. */
	String COUNTRY_STRING = "Country";

	/** The total no records. */
	String TOTAL_NO_RECORDS = "TotalNoRecords";
	
	/** The json rows. */
	String JSON_ROWS = "jsonRows";

	/** The select. */
	String SELECT = "select";

	// Search type Names
	/** The location search. */
	String LOCATION_SEARCH = "LOCATION-FILTER-JB2";
	
	/** The keyword search. */
	String KEYWORD_SEARCH = "KEYWORD-FILTER-JB2";
	
	/** The browse search. */
	String BROWSE_SEARCH = "BROWSE-JB2";

	// Solr param
	/** The sort param. */
	String SORT_PARAM = "sortParam";
	
	/** The is fq param lowercase. */
	String IS_FQ_PARAM_LOWERCASE = "isFqParamLowercase";
	
	/** The first fq param. */
	String FIRST_FQ_PARAM = "firstFQParam";
	
	/** The second fq param. */
	String SECOND_FQ_PARAM = "secondFQParam";
	
	/** The third fq param. */
	String THIRD_FQ_PARAM = "thirdFQParam";
	
	/** The fourth fq param. */
	String FOURTH_FQ_PARAM = "fouthFQParam";
	
	/** The fifth fq param. */
	String FIFTH_FQ_PARAM = "fifthFQParam";
	
	/** The Facility id_ nam e_ f q_ param. */
	String FacilityId_NAME_FQ_PARAM = "facilityIdNameFQParam";
	
	/** The Facility id_ f q_ param. */
	String FacilityId_FQ_PARAM = "facilityIdFQParam";

	/** The posted dt. */
//	String POSTED_DT = "posted_dt";
	
	/** To sort based on relevance */
	String POSTED_DT = "score";
	
	/** The sort order. */
	String SORT_ORDER = "sortOrder";
	
	/** The desc str. */
	String DESC_STR = "desc";
	
	/** The asc str. */
	String ASC_STR = "asc";
	
	/** The count str. */
	String COUNT_STR = "count";
	
	/** The index str. */
	String INDEX_STR = "index";
	
	/** The facet sort. */
	String FACET_SORT = "facetSort";
	// Solr Date pattern
	/** The json date format. */
	String JSON_DATE_FORMAT = "MMM-dd-yyyy";
	// For Resume

	/** The post resume. */
	String POST_RESUME = "postresume";

	// Date Patterns
	/** The date pattern. */
	String DATE_PATTERN = "yyyy-MM-dd";
	
	/** The disp date pattern. */
	String DISP_DATE_PATTERN = "MM/dd/yyyy";
	
	/** The newdate pattern. */
	String NEWDATE_PATTERN = "EEE MMM d HH:mm:ss z yyyy";
	
	/** The sql date pattern. */
	String SQL_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
	
	/** The app date format. */
	String APP_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	/** The disp date range. */
	String DISP_DATE_RANGE = "dd/MM/yyyy";
	
	/** The error string. */
	String ERROR_STRING = "error";
	
	/** The ok string. */
	String OK_STRING = "ok";

	/** The clicktype jobview. */
	String CLICKTYPE_JOBVIEW = "1";
	
	/** The clicktype emailfriend. */
	String CLICKTYPE_EMAILFRIEND = "2";
	
	/** The clicktype printjob. */
	String CLICKTYPE_PRINTJOB = "3";
	
	/** The clicktype applynow. */
	String CLICKTYPE_APPLYNOW = "4";
	
	/** The clicktype applyemail. */
	String CLICKTYPE_APPLYEMAIL = "5";
	
	/** The clicktype companyname. */
	String CLICKTYPE_COMPANYNAME = "6";
	
	/** The clicktype websiteurl. */
	String CLICKTYPE_WEBSITEURL = "7";
	
	/** The clicktype jobsumlinks. */
	String CLICKTYPE_JOBSUMLINKS = "8";
	
	/** The clicktype socialshare. */
	String CLICKTYPE_SOCIALSHARE = "9";
	
	/** The clicktype savejob. */
	String CLICKTYPE_SAVEJOB = "10";
	
	/** The drop down. */
	String DROP_DOWN = "Dropdown";
	
	/** The check box. */
	String CHECK_BOX = "CheckBox";
	
	/** The radio button. */
	String RADIO_BUTTON = "Radio";
	
	/** The label country. */
	String LABEL_COUNTRY = "Country";
	
	/** The label state. */
	String LABEL_STATE = "State / Province";
	
	/** The label susbscription. */
	String LABEL_SUSBSCRIPTION = "Subscriptions";
	// If you change the following value, you should change in
	// mer_profile_attrib as well.
	// Colum name Screen Name
	/** The job seeker registration. */
	String JOB_SEEKER_REGISTRATION = "JobSeeker Registration";
	
	/** The job seeker profile settings. */
	String JOB_SEEKER_PROFILE_SETTINGS = "JobSeeker Edit Profile Settings";
	
	/** The first name. */
	String FIRST_NAME = "First Name";
	
	/** The last name. */
	String LAST_NAME = "Last Name";
	
	/** The middle name. */
	String MIDDLE_NAME = "Middle Name";
	
	/** The email address. */
	String EMAIL_ADDRESS = "E-Mail Address";
	
	/** The phone number. */
	String PHONE_NUMBER = "Phone Number";
	
	/** The zero. */
	String ZERO = "0";
	// for employer
	/** The position title. */
	String POSITION_TITLE = "Position Title";
	
	/** The street add. */
	String STREET_ADD = "Street Address";
	
	/** The zip code. */
	String ZIP_CODE = "Zip Code";
	
	/** The primary phone. */
	String PRIMARY_PHONE = "Primary Phone";
	
	/** The secondary phone. */
	String SECONDARY_PHONE = "Secondary Phone";
	
	/** The country. */
	String COUNTRY = "Country";
	
	/** The city emp. */
	String CITY_EMP = "City";
	
	/** The state province. */
	String STATE_PROVINCE = "State / Province";
	
	/** The company emp. */
	String COMPANY_EMP = "Company";
	
	/** The ns customer id. */
	String NS_CUSTOMER_ID = "ns_customer_id";

	// For Save this
	/** The search type. */
	String SEARCH_TYPE = "searchtype";
	
	/** The semicolon. */
	String SEMICOLON = ";";
	
	/** The language english. */
	String LANGUAGE_ENGLISH = "English";
	
	/** The reference type personal. */
	String REFERENCE_TYPE_PERSONAL = "Personal";
	
	/** The basic search type. */
	String BASIC_SEARCH_TYPE = "basic";
	
	/** The basic search type resume. */
	String BASIC_SEARCH_TYPE_RESUME = "resume";
	
	/** The save search name. */
	String SAVE_SEARCH_NAME = "saveSearchName";

	// latest searches count
	/** The latest searches limit. */
	int LATEST_SEARCHES_LIMIT = 3;

	// For User Roles
	/** The merion admin. */
	String MERION_ADMIN = "merion_admin";
	
	/** The jobseeker. */
	String JOBSEEKER = "jobseeker";
	
	/** The job seeker. */
	String JOB_SEEKER = "jobSeeker";
	
	/** The employer. */
	String EMPLOYER = "employer";
	
	/** The managejobseeker. */
	String MANAGEJOBSEEKER = "manageJobSeeker";
	
	/** The agency. */
	String AGENCY = "agency";
	
	/** The facility admin. */
	String FACILITY_ADMIN = "facility_admin";
	
	/** The facility user. */
	String FACILITY_USER = "facility_user";
	
	/** The facility full access. */
	String FACILITY_FULL_ACCESS = "Full_Access";
	
	/** The facility post edit. */
	String FACILITY_POST_EDIT = "Post_Edit";
	
	/** The role advance pass user. */
	String ROLE_ADVANCE_PASS_USER = "ROLE_ADVANCE_PASS_USER";
	
	/** The role merion admin. */
	String ROLE_MERION_ADMIN = "ROLE_MERION_ADMIN";
	
	/** The role job seeker. */
	String ROLE_JOB_SEEKER = "ROLE_JOB_SEEKER";
	
	/** The role facility admin. */
	String ROLE_FACILITY_ADMIN = "ROLE_FACILITY_ADMIN";
	
	/** The role facility user. */
	String ROLE_FACILITY_USER = "ROLE_FACILITY_USER";
	
	/** The facility. */
	String FACILITY = "FACILITY";
	
	/** The facility group. */
	String FACILITY_GROUP = "FACILITY_GROUP";
	
	/** The facility system. */
	String FACILITY_SYSTEM = "FACILITY_SYSTEM";
	
	/** The role facility. */
	String ROLE_FACILITY = "ROLE_FACILITY";
	
	/** The role facility group. */
	String ROLE_FACILITY_GROUP = "ROLE_FACILITY_GROUP";
	
	/** The role facility system. */
	String ROLE_FACILITY_SYSTEM = "ROLE_FACILITY_SYSTEM";
	
	/** The role facility full access. */
	String ROLE_FACILITY_FULL_ACCESS = "ROLE_FACILITY_FULL_ACCESS";
	
	/** The role facility post edit. */
	String ROLE_FACILITY_POST_EDIT = "ROLE_FACILITY_POST_EDIT";

	// For Spring Security Remember Me Login(Cookie Name)
	/** The spring security remember me cookie. */
	String SPRING_SECURITY_REMEMBER_ME_COOKIE = "SPRING_SECURITY_REMEMBER_ME_COOKIE";

	// Payment Gateway
	/** The credit card. */
	String CREDIT_CARD = "ccp";
	
	/** The invoice. */
	String INVOICE = "inv";

	// For Successful Login
	/** The user id. */
	String USER_ID = "userId";
	
	/** The user email. */
	String USER_EMAIL = "userEmail";
	
	/** The user name. */
	String USER_NAME = "userName";
	
	/** The page value. */
	String PAGE_VALUE = "pageValue";
	
	/** The facility id. */
	String FACILITY_ID = "facilityId";
	
	/** The login date time. */
	String LOGIN_DATE_TIME = "dateTime";
	// For Agency Impersonation
	/** The agency user id. */
	String AGENCY_USER_ID = "agencyUserId";
	
	/** The agency facility id. */
	String AGENCY_FACILITY_ID = "agencyFacilityId";
	
	/** The agency user name. */
	String AGENCY_USER_NAME = "agencyUserName";
	
	/** The agency email. */
	String AGENCY_EMAIL = "agencyEmail";

	// For Duplicate Submission
	/** The last place key. */
	String LAST_PLACE_KEY = "LAST_PLACE_KEY";

	/** The perform saved search. */
	String PERFORM_SAVED_SEARCH = "performSearch";

	// For Account Setting
	/** The primary. */
	String PRIMARY = "PRIMARY";
	
	/** The billing. */
	String BILLING = "BILLING";

	// For Post New Job
	/** The post new job. */
	String POST_NEW_JOB = "Active";
	
	/** The post job scheduled. */
	String POST_JOB_SCHEDULED = "Scheduled";
	
	/** The post job draft. */
	String POST_JOB_DRAFT = "Draft";
	
	/** The post job expired. */
	String POST_JOB_EXPIRED = "Expired";
	
	/** The post job inactive. */
	String POST_JOB_INACTIVE = "Inactive";
	
	/** The active. */
	byte ACTIVE = 1;
	
	/** The inactive. */
	byte INACTIVE = 0;
	
	/** The standard posting. */
	String STANDARD_POSTING = "Job Posting";
	
	/** The slot posting. */
	String SLOT_POSTING = "Job Slot";
	// For Apply job type
	/** The apply to url. */
	String APPLY_TO_URL = "ApplyToURL";
	
	/** The apply to ats. */
	String APPLY_TO_ATS = "ApplyToATS";
	
	/** The apply to email. */
	String APPLY_TO_EMAIL = "ApplyToEMail";

	// for packages
	/** The package espost. */
	String PACKAGE_ESPOST = "ezpost";
	
	/** The package gold. */
	String PACKAGE_GOLD = "gold";
	
	/** The package platinum. */
	String PACKAGE_PLATINUM = "platinum";
	
	/** The package jbpostslot. */
	String PACKAGE_JBPOSTSLOT = "jbPostSlot";
	
	/** The package stjobposting. */
	String PACKAGE_STJOBPOSTING = "stJobPosting";
	
	/** The package silver. */
	String PACKAGE_SILVER = "silver";

	// For offline purchased packages in Sandbox (test)
//	String GOLD_90 = "4809";
//	String GOLD_180 = "4864";
//	String GOLD_365 = "4808";
//	String PLATINUM_90 = "4811";
//	String PLATINUM_180 = "4865";
//	String PLATINUM_365 = "4810";
//	String SILVER_90 = "4813";
//	String SILVER_180 = "4866";
//	String SILVER_365 = "4812";
//	String FEATURE_30 = "4867";
//	String FEATURE_90 = "4868";
//	String FEATURE_180 = "4870";
//	String FEATURE_365 = "4869";
//	String SCRAPE_90 = "947";
//	String SCRAPE_180 = "913";
//	String SCRAPE_365 = "4871";
//	String XML_90 = "4872";
//	String XML_180 = "963";
//	String XML_365 = "976";

	// For production NetSuite purchased packages
	/** The GOL d_90. */
	String GOLD_90 = "2919";
	
	/** The GOL d_180. */
	String GOLD_180 = "2929";
	
	/** The GOL d_365. */
	String GOLD_365 = "2920";
	
	/** The PLATINU m_90. */
	String PLATINUM_90 = "2917";
	
	/** The PLATINU m_180. */
	String PLATINUM_180 = "2928";
	
	/** The PLATINU m_365. */
	String PLATINUM_365 = "2918";
	
	/** The SILVE r_90. */
	String SILVER_90 = "2922";
	
	/** The SILVE r_180. */
	String SILVER_180 = "2927";
	
	/** The SILVE r_365. */
	String SILVER_365 = "2921";
	
	/** The FEATUR e_30. */
	String FEATURE_30 = "2930";
	
	/** The FEATUR e_90. */
	String FEATURE_90 = "2931";
	
	/** The FEATUR e_180. */
	String FEATURE_180 = "2932";
	
	/** The FEATUR e_365. */
	String FEATURE_365 = "2933";
	
	/** The SCRAP e_90. */
	String SCRAPE_90 = "945";
	
	/** The SCRAP e_180. */
	String SCRAPE_180 = "913";
	
	/** The SCRAP e_365. */
	String SCRAPE_365 = "929";
	
	/** The XM l_90. */
	String XML_90 = "991";
	
	/** The XM l_180. */
	String XML_180 = "959";
	
	/** The XM l_365. */
	String XML_365 = "975";
	
	// Net suite promotion codes	
	/** The PROMOCOD e_15 advoff. */
	String PROMOCODE_15ADVOFF = "15ADVoff";
	
	/** The ns discount item id. */
	String NS_DISCOUNT_ITEM_ID="2934";
	//	For Sandbox (Test)
//	String NS_DISCOUNT_ITEM_ID="5724";
	
	
	// for Branding template
	/** The image type jpg. */
	String IMAGE_TYPE_JPG = ".jpg";
	
	/** The image type gif. */
	String IMAGE_TYPE_GIF = ".gif";
	
	/** The image type png. */
	String IMAGE_TYPE_PNG = ".png";
	
	/** The image type tif. */
	String IMAGE_TYPE_TIF = ".tif";
	
	/** The int silver. */
	int INT_SILVER = 1;
	
	/** The int gold. */
	int INT_GOLD = 2;
	
	/** The int platinum. */
	int INT_PLATINUM = 3;
	
	/** The additional image. */
	String ADDITIONAL_IMAGE = "Additional Image";
	
	/** The video. */
	String VIDEO = "Video";
	
	/** The kilo byte. */
	int KILO_BYTE = 1000;
	
	/** The mega byte. */
	int MEGA_BYTE = 1000000;

	// Media file format-Manage Featured Employer Profile
	/** The video type mov. */
	String VIDEO_TYPE_MOV = ".mov";
	
	/** The video type mpg. */
	String VIDEO_TYPE_MPG = ".mpg";
	
	/** The media type avi. */
	String MEDIA_TYPE_AVI = ".avi";
	
	/** The media type wmv. */
	String MEDIA_TYPE_WMV = ".wmv";
	
	/** The media type mpeg. */
	String MEDIA_TYPE_MPEG = ".mpeg";
	
	/** The MEDI a_ typ e_ mpe g_4. */
	String MEDIA_TYPE_MPEG_4 = ".mp4";
	
	/** The media type ppt. */
	String MEDIA_TYPE_PPT = ".ppt";
	
	/** The media type pptx. */
	String MEDIA_TYPE_PPTX = ".pptx";
	
	/** The user dto. */
	String USER_DTO = "USER_DTO";

	/** The recaptcha private key. */
	String RECAPTCHA_PRIVATE_KEY = "6Lel19USAAAAAHC7mqzT-Q0WpThoqiKr0DnhYtpN";

	/** The auto renewal days. */
	int AUTO_RENEWAL_DAYS = 29;
	// For Job search results
	/** The default page size. */
	int DEFAULT_PAGE_SIZE = 20;
	
	/** The jobseacrh default page size. */
	int JOBSEACRH_DEFAULT_PAGE_SIZE = 20;
	
	/** The jobseacrh fast forward. */
	int JOBSEACRH_FAST_FORWARD = 10;
	
	/** The jobsearch grid pages count. */
	int JOBSEARCH_GRID_PAGES_COUNT = 10;
	
	/** The fast forward. */
	String FAST_FORWARD = "fastforward";
	
	/** The search results list. */
	String SEARCH_RESULTS_LIST = "searchResultsList";
	
	/** The no of pages. */
	String NO_OF_PAGES = "noOfPages";
	
	/** The current page. */
	String CURRENT_PAGE = "currentPage";
	
	/** The jobseacrh page size. */
	String JOBSEACRH_PAGE_SIZE = "pageSize";
	
	/** The jobseacrh page sort. */
	String JOBSEACRH_PAGE_SORT = "isSorting";
	
	/** The searched jobscount. */
	String SEARCHED_JOBSCOUNT = "searchedJobCount";
	
	/** The resume records count. */
	String RESUME_RECORDS_COUNT = "totalNumberOfSearchedResume";
	
	/** The start row. */
	String START_ROW = "startRow";
	
	/** The end row. */
	String END_ROW = "endRow";
	
	/** The begin val. */
	String BEGIN_VAL = "beginVal";
	
	/** The begin. */
	String BEGIN = "begin";
	
	/** The page. */
	String PAGE = "page";
	
	/** The current search list. */
	String CURRENT_SEARCH_LIST = "currentSearchList";
	
	/** The miles. */
	String MILES = " Miles";
	
	/** The hashmap key. */
	String HASHMAP_KEY = "key";
	
	/** The hashmap value. */
	String HASHMAP_VALUE = "value";
	
	/** The prev job search keywords. */
	String PREV_JOB_SEARCH_KEYWORDS = "prevJobSearchKeywords";
	
	/** The records per page. */
	String RECORDS_PER_PAGE = "displayRecordsPerPage";
	
	/** The records count. */
	String RECORDS_COUNT = "totalNoOfRecords";

	// Kartik Add many Variable for account setting and Mail send
	/** The web mail server. */
	String WEB_MAIL_SERVER = "merion@nousinfosystems.com";
	
	/** The email message. */
	String EMAIL_MESSAGE = "Please enter correct Email address.";
	
	/** The email null message. */
	String EMAIL_NULL_MESSAGE = "Email address already Exists!";
	
	/** The phone no. */
	String PHONE_NO = "Please enter a valid phone number in this format: (xxx) xxx-xxxx.";
	
	/** The phone null no. */
	String PHONE_NULL_NO = "Please enter the phone number.";
	
	/** The email message blank. */
	String EMAIL_MESSAGE_BLANK = "Please enter a required field.";
	
	/** The update error. */
	String UPDATE_ERROR = " Error occurred. Please contact administrator.";
	// Job Types
	/** The job type. */
	String JOB_TYPE = "JOB_TYPE";
	
	/** The job type addon. */
	String JOB_TYPE_ADDON = "ADDON";
	
	/** The job type combo. */
	String JOB_TYPE_COMBO = "JOB_TYPE_COMBO";

	// netsuite status code
	/** The STATU s_ cod e_200. */
	int STATUS_CODE_200 = 200;
	
	/** The STATU s_ cod e_400. */
	int STATUS_CODE_400 = 400;
	
	/** The STATU s_ cod e_401. */
	int STATUS_CODE_401 = 401;
	
	/** The STATU s_ cod e_403. */
	int STATUS_CODE_403 = 403;
	
	/** The STATU s_ cod e_404. */
	int STATUS_CODE_404 = 404;
	
	/** The STATU s_ cod e_405. */
	int STATUS_CODE_405 = 405;
	
	/** The STATU s_ cod e_415. */
	int STATUS_CODE_415 = 415;
	
	/** The STATU s_ cod e_500. */
	int STATUS_CODE_500 = 500;
	
	/** The STATU s_ cod e_503. */
	int STATUS_CODE_503 = 503;
	
	/** The status code default. */
	int STATUS_CODE_DEFAULT = 9999;
	
	/** The SUCCES s_200. */
	String SUCCESS_200 = "The RESTlet web service request was executed successfully.";
	
	/** The BA d_ reques t_400. */
	String BAD_REQUEST_400 = "Invalid credit card / invoice information or billing address.";
	
	/** The UNAUTHORIZE d_401. */
	String UNAUTHORIZED_401 = "This is not a valid NetSuite login session for the RESTlet calls.";
	
	/** The FORBIDDE n_403. */
	String FORBIDDEN_403 = "RESTlet request sent to invalid domain, meaning a domain other than https://rest.netsuite.com.";
	
	/** The NO t_ foun d_404. */
	String NOT_FOUND_404 = "A RESTlet script is not defined in the RESTlet request.";
	
	/** The METHO d_ no t_ allowe d_405. */
	String METHOD_NOT_ALLOWED_405 = "The RESTlet request method is not valid.";
	
	/** The UNSUPPORTE d_ medi a_ typ e_415. */
	String UNSUPPORTED_MEDIA_TYPE_415 = "An unsupported content type was specified. (Only JSON and text are allowed.)";
	
	/** The INTERNA l_ serve r_ erro r_500. */
	String INTERNAL_SERVER_ERROR_500 = "(unexpected errors): Occurs for non-user errors that cannot be recovered by resubmitting the same request. Contact Customer Support to file a case.";
	
	/** The SERVIC e_ unavailabl e_503. */
	String SERVICE_UNAVAILABLE_503 = "The server is temporarily unavailable. Please contact your system administrator.";
	
	/** The default nserror msg. */
	String DEFAULT_NSERROR_MSG = "Unexpected NetSuit error occured. Please contact your system administrator.";

	/** The next. */
	String NEXT = "next";
	
	/** The filter vals. */
	int[] FILTER_VALS = { 20, 30, 40, 50 };

	// For jobboard links
	/** The followup link facebook. */
	String FOLLOWUP_LINK_FACEBOOK = "followuplinkfacebook";
	
	/** The followup link twitter. */
	String FOLLOWUP_LINK_TWITTER = "followuplinktwitter";
	
	/** The followup link youtube. */
	String FOLLOWUP_LINK_YOUTUBE = "followuplinkyoutube";
	
	/** The followup link linkedin. */
	String FOLLOWUP_LINK_LINKEDIN = "followuplinklinkedin";
	// Manage access permission
	/** The full access. */
	String FULL_ACCESS = "5";
	
	/** The manageeditaccess. */
	String MANAGEEDITACCESS = "6";
	
	/** The agen per page. */
	String AGEN_PER_PAGE = "agePermPage";

	/** The database error code. */
	String DATABASE_ERROR_CODE = "Database failed to support. Please contact your technical support team to resolve this issue.";
	
	/** The uniquekey primarykey violated. */
	String UNIQUEKEY_PRIMARYKEY_VIOLATED = "Unique Key/Primary Key violated in database.Please contact your Database Administrator.";
	
	/** The system error code. */
	String SYSTEM_ERROR_CODE = "System encountered unexpected error.Please contact your techinal support team to resolve the issue.";
	
	/** The employer role id. */
	int EMPLOYER_ROLE_ID = 3;
	
	/** The jobseeker role id. */
	int JOBSEEKER_ROLE_ID = 2;
	
	/** The phone. */
	String PHONE = "Phone";

	// Added for set alerts task
	/** The set alert. */
	String SET_ALERT = "setAlertPage";
	
	/** The do not have credits. */
	String DO_NOT_HAVE_CREDITS = "You do not have sufficient credits to post the job.";
	
	/** The do not have credits repost. */
	String DO_NOT_HAVE_CREDITS_REPOST = "You do not have sufficient credits to repost the job.";
	
	/** The status active. */
	String STATUS_ACTIVE = "Active";
	
	/** The status inactive. */
	String STATUS_INACTIVE = "Inactive";
	
	/** The status expired. */
	String STATUS_EXPIRED = "Expired";

	// Added for inventory
	/** The days. */
	String DAYS = "days";
	
	/** The standard job posting. */
	String STANDARD_JOB_POSTING = "30-Day Standard Job Posting";
	
	/** The job posting slot. */
	String JOB_POSTING_SLOT = "30-Day Job Posting Slot";
	
	/** The basic job type. */
	String BASIC_JOB_TYPE = "Basic";
	
	/** The inventory. */
	String INVENTORY = "inventoryPage";
	
	/** The purchase job post form. */
	String PURCHASE_JOB_POST_FORM = "purchaseJobPostForm";
	
	/** The purchase resume search form. */
	String PURCHASE_RESUME_SEARCH_FORM = "purchaseResumeSearchForm";
	// Purchase types
	/** The purchase job post. */
	String PURCHASE_JOB_POST = "jobPost";
	
	/** The purchase resume search. */
	String PURCHASE_RESUME_SEARCH = "resumeSearch";
	// Product type
	/** The resume search package. */
	String RESUME_SEARCH_PACKAGE = "RESUME_SEARCH";

	/** The plan days. */
	int PLAN_DAYS = 30;
	
	/** The total active job string. */
	String TOTAL_ACTIVE_JOB_STRING = "TotalNoOfActiveJobs";
	
	/** The post job page. */
	String POST_JOB_PAGE = "postJobPage";
	
	/** The job post type posting id. */
	int JOB_POST_TYPE_POSTING_ID = 1;
	
	/** The job post type slot id. */
	int JOB_POST_TYPE_SLOT_ID = 2;
	// FOR MAIL RELATED
	/** The subject of mail. */
	String SUBJECT_OF_MAIL = "A job opportunity sent to you by";
	
	/** The body ofmail first. */
	String BODY_OFMAIL_FIRST = "Here’s a job opportunity that ";
	
	/** The body ofmail second. */
	String BODY_OFMAIL_SECOND = " thought might interest you.";
	
	/** The job title heading. */
	String JOB_TITLE_HEADING = "Job title:";
	
	/** The comapny name heading. */
	String COMAPNY_NAME_HEADING = "Company name:";
	
	/** The url link first. */
	String URL_LINK_FIRST = "<a href=?";
	
	/** The url link second. */
	String URL_LINK_SECOND = "><b>View this job now</b></a> to learn more and submit your application.";
	
	/** The url redirect mail. */
	String URL_REDIRECT_MAIL = "redirect:/healthcare/index.html";
	
	/** The error sending mail. */
	String ERROR_SENDING_MAIL = "ERROR For sending mail option of SendToFriend method";

	// For Refine Search
	/** The space opn brckt. */
	String SPACE_OPN_BRCKT = " (";
	
	/** The clsg brckt. */
	String CLSG_BRCKT = ")";
	
	/** The display radius. */
	String DISPLAY_RADIUS = "displayRadius";
	
	/** The fq refine keyword. */
	String FQ_REFINE_KEYWORD = "{!tag=dt}";
	
	/** The F q_ refin e_ keywor d2. */
	String FQ_REFINE_KEYWORD2 = "\"";
	
	/** The fq job position. */
	String FQ_JOB_POSITION = "jobposition:\"";
	
	/** The fq company. */
	String FQ_COMPANY = "company:\"";
	
	/** The fq facility id. */
	String FQ_FACILITY_ID = "facility_id:\"";
	
	/** The fq state. */
	String FQ_STATE = "state:\"";
	
	/** The fq state lower case. */
	String FQ_STATE_LOWER_CASE = "state2:\"";
	
	/** The fq city. */
	String FQ_CITY = "city:\"";
	
	/** The fq city lower case. */
	String FQ_CITY_LOWER_CASE = "city2:\"";
	
	/** The fq area. */
	String FQ_AREA = "area:\"";
	
	/** The fq area lower case. */
	String FQ_AREA_LOWER_CASE = "area2:\"";
	
	/** The refined. */
	String REFINED = "refined";
	
	/** The refineradius. */
	String REFINERADIUS = "refineRadius";
	
	/** The radius. */
	String RADIUS = "radius";
	
	/** The area. */
	String AREA = "area";
	
	/** The metro area. */
	String METRO_AREA = "Metro Area";
	
	/** The browse by location reg. */
	String BROWSE_BY_LOCATION_REG = "browseByLocationReg";

	// Added for Resume search
	/** The resume location search. */
	String RESUME_LOCATION_SEARCH = "CANDIDATE-LOCATION";
	
	/** The resume keyword search. */
	String RESUME_KEYWORD_SEARCH = "CANDIDATE-KEYWORD";
	
	/** The resume search session map. */
	String RESUME_SEARCH_SESSION_MAP = "resumeSessionMap";
	
	/** The is resume package active. */
	String IS_RESUME_PACKAGE_ACTIVE = "isResumePackageActive";

	// For Social Media sign up
	/** The facebook. */
	String FACEBOOK = "Facebook";
	
	/** The linkedin. */
	String LINKEDIN = "LinkedIn";
	
	/** The facebook profile attr id. */
	String FACEBOOK_PROFILE_ATTR_ID = "27";
	
	/** The linkedin profile attr id. */
	String LINKEDIN_PROFILE_ATTR_ID = "28";

	/** The resume desired postion. */
	String RESUME_DESIRED_POSTION = "DesiredJobTitle";
	
	/** The applicant name. */
	String APPLICANT_NAME = "ApplicantName";
	
	/** The location. */
	String LOCATION = "Location";
	
	/** The experience. */
	String EXPERIENCE = "Experience";
	
	/** The upload resume id. */
	String UPLOAD_RESUME_ID = "UploadResumeId";
	
	/** The publish resume id. */
	String PUBLISH_RESUME_ID = "PublishResumeId";

	/** The resume search json list. */
	String RESUME_SEARCH_JSON_LIST = "resSrchJsonList";

	// for browseByJobs
	/** The first parameter. */
	String FIRST_PARAMETER = "firstFQParam";

	// for reCapcha
	/** The public key. */
	String PUBLIC_KEY = "6Lel19USAAAAAPRKVOy7gFpRBpn6iSPONG1o9ouZ";
	
	/** The private key. */
	String PRIVATE_KEY = "6Lel19USAAAAAHC7mqzT-Q0WpThoqiKr0DnhYtpN";

	/** The module string. */
	String MODULE_STRING = "module";
	
	/** The keyword string. */
	String KEYWORD_STRING = "keywords";

	/** The print js subscription. */
	int PRINT_JS_SUBSCRIPTION = 1;
	
	/** The digital js subscription. */
	int DIGITAL_JS_SUBSCRIPTION = 2;
	
	/** The enews js subscription. */
	int ENEWS_JS_SUBSCRIPTION = 3;
	
	/** The email js subscription. */
	int EMAIL_JS_SUBSCRIPTION = 4;
	
	/** The digital subscription. */
	int DIGITAL_SUBSCRIPTION = 5;
	
	/** The enews letter subscription. */
	int ENEWS_LETTER_SUBSCRIPTION = 6;
	
	/** The email subscription. */
	int EMAIL_SUBSCRIPTION = 7;

	// SEO info
	/** The jobtitles grid pagesize. */
	int JOBTITLES_GRID_PAGESIZE = 100;
	
	/** The jobtitles next page url. */
	String JOBTITLES_NEXT_PAGE_URL = "nextPageUrl";
	
	/** The jobtitles prev page url. */
	String JOBTITLES_PREV_PAGE_URL = "prevPageUrl";
	
	/** The job search max areas. */
	int JOB_SEARCH_MAX_AREAS = 100;
	// Email Scheduler constants

	/** The scheduler day. */
	String SCHEDULER_DAY = "30DayEmailSch";
	
	/** The solr param fq. */
	String SOLR_PARAM_FQ = "posted_dt:[NOW-?dayDAY TO NOW]";
	
	/** The daily scheduler. */
	int DAILY_SCHEDULER = 3;
	
	/** The monthly scheduler. */
	int MONTHLY_SCHEDULER = 6;
	
	/** The weekly scheduler. */
	int WEEKLY_SCHEDULER = 4;
	
	/** The biweekly scheduler. */
	int BIWEEKLY_SCHEDULER = 5;
	
	/** The yearly scheduler. */
	int YEARLY_SCHEDULER = 7;
	
	/** The schedule biweekly. */
	String SCHEDULE_BIWEEKLY = "15";
	
	/** The schedule weekly. */
	String SCHEDULE_WEEKLY = "7";
	
	/** The schedule monthly. */
	String SCHEDULE_MONTHLY = "30";
	
	/** The schedule yearly. */
	String SCHEDULE_YEARLY = "365";
	
	/** The schedule daily. */
	String SCHEDULE_DAILY = "1";

	// variables to populate the ads
	/** The adpagetop. */
	String ADPAGETOP = "adPageTop";
	
	/** The adpagebottom. */
	String ADPAGEBOTTOM = "adPageBottom";
	
	/** The adpgright top. */
	String ADPGRIGHT_TOP = "adPageRightTop";
	
	/** The adpgright middle. */
	String ADPGRIGHT_MIDDLE = "adPageRightMiddle";
	
	/** The adpgright top middle. */
	String ADPGRIGHT_TOP_MIDDLE = "adPageRightTopMiddle";
	
	/** The adpgcenter middle list. */
	String ADPGCENTER_MIDDLE_LIST = "adPageCenterMiddleList";
	
	/** The country usa. */
	String COUNTRY_USA = "USA";
	
	/** The country ca. */
	String COUNTRY_CA = "CA";
	
	/** The country usa val. */
	int COUNTRY_USA_VAL = 182;
	
	/** The country ca val. */
	int COUNTRY_CA_VAL = 32;

	/** The subscription subtype print. */
	String SUBSCRIPTION_SUBTYPE_PRINT = "PRINT";
	
	/** The subscription subtype dig. */
	String SUBSCRIPTION_SUBTYPE_DIG = "DIGITAL";
	
	/** The subscription subtype news. */
	String SUBSCRIPTION_SUBTYPE_NEWS = "ENEWSLETTER";
	
	/** The subscription subtype email. */
	String SUBSCRIPTION_SUBTYPE_EMAIL = "EMAILER";

	// Alert Constant	
	/** The administrator changes. */
	int ADMINISTRATOR_CHANGES=1;
    
    /** The your job listing failed to auto renew. */
    int YOUR_JOB_LISTING_FAILED_TO_AUTO_RENEW =2;
	
	/** The new job posting credits available. */
	int NEW_JOB_POSTING_CREDITS_AVAILABLE=3;
	
	/** The job posting expiring soon. */
	int JOB_POSTING_EXPIRING_SOON=4;
	
	/** The sales receipt. */
	int SALES_RECEIPT=5;
	
	/** The your job has expired. */
	int YOUR_JOB_HAS_EXPIRED=6;
	
	/** The no active postings on advance. */
	int NO_ACTIVE_POSTINGS_ON_ADVANCE =7;
	
}
