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

	// For DropDowns
	public static final String EMPLOYMENT_TYPE = "EmploymentType";

	// For SOLR search
	public static final String SLASH = "/";
	public static final String PLUS = "+";
	public static final String SLASH_SELECT_SLASH = "/select/?";
	public static final String AMP = "&";
	public static final String QUESTION_MARK = "?";
	public static final String EQUAL_TO = "=";
	public static final String USER = "admin";
	public static final String SELECT_SLASH_QUESTIONMARK = "select/?";
	
	public static final String KEYWORDS = "keywords";
	public static final String CITY_STATE = "cityState";
	public static final String RADIUS = "radius";
	public static final String SESSION_ID = "sessionid";
	
	public static final String SEARCH_SEQ = "search_seq";
	public static final String QUERY_TYPE = "queryType";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COMPANY = "company";
	public static final String POSTED_DT = "posted_dt";
	
	public static final String B_01 = ":b01";
	public static final String B_02 = ":b02";
	public static final String B_03 = ":b03";
	public static final String B_04 = ":b04";
	public static final String B_05 = ":b05";
	public static final String B_06 = ":b06";
	
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

	//For Resume
	
	public static final String POST_RESUME = "postresume";

	// Date Patterns
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DISP_DATE_PATTERN = "MM/dd/yyyy";
	public static final String NEWDATE_PATTERN = "EEE MMM d HH:mm:ss z yyyy";
	
	
	
	public static final String ERROR_STRING = "error";
	public static final String OK_STRING = "ok";
	
}
