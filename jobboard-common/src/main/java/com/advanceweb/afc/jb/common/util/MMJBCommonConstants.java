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
	public static final String MOBILE_PATTERN = "((?=.*\\d).{10})";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final String RESUME_TYPE_RESUME_BUILDER = "Create";
	public static final String RESUME_TYPE_UPLOAD = "Upload";
	public static final String RESUME_TYPE_COPY_PASTE = "CopyPaste";
	// For DropDowns 
	public static final String EMPLOYMENT_TYPE = "EmploymentType";
	public static final String VISIBILITY = "Visibility" ;
	public static final String WORK_AUTH_US = "WorkAuthUS";
	public static final String RELOCATE = "Relocate";
	
	//Resume Visibility
	public static final String VISIBILITY_PUBLIC = "Public" ;
	public static final String VISIBILITY_PRIVATE = "Private";

	// For SOLR search
	String SLASH = "/";
	String PLUS = "+";
	String SLASH_SELECT_SLASH = "/select/?";
	String AMP = "&";
	String QUESTION_MARK = "?";
	String EQUAL_TO = "=";
	String USER = "admin";
	String SELECT_SLASH_QUESTIONMARK = "select/?";
	
	String KEYWORDS = "keywords";
	String CITY_STATE = "cityState";
	String RADIUS = "radius";
	String SESSION_ID = "sessionid";
	
	String SEARCH_SEQ = "search_seq";
	String QUERY_TYPE = "queryType";
	String CITY = "city";
	String STATE = "state";
	String COMPANY = "company";
	String POSTED_DT = "posted_dt";
	
	String B_01 = ":b01";
	String B_02 = ":b02";
	String B_03 = ":b03";
	String B_04 = ":b04";
	String B_05 = ":b05";
	String B_06 = ":b06";
	
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
	

}
