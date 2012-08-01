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
	private static final String USER = "admin";
	private static final String SELECT_SLASH_QUESTIONMARK = "select/?";
	
	private static final String KEYWORDS = "keywords";
	private static final String CITY_STATE = "cityState";
	private static final String RADIUS = "radius";
	private static final String SESSION_ID = "sessionid";
	
	private static final String SEARCH_SEQ = "search_seq";
	private static final String QUERY_TYPE = "queryType";
	private static final String CITY = "city";
	private static final String STATE = "state";
	private static final String COMPANY = "company";
	private static final String POSTED_DT = "posted_dt";
	
	private static final String B_01 = ":b01";
	private static final String B_02 = ":b02";
	private static final String B_03 = ":b03";
	private static final String B_04 = ":b04";
	private static final String B_05 = ":b05";
	private static final String B_06 = ":b06";
	
	private static final String COMPANY = "Company";
	private static final String JOB_TITLE = "JobTitle";
	private static final String CAP_CITY = "City";
	private static final String POSTED_DATE = "PostedDate";
	private static final String APPLY_ONLINE = "ApplyOnline";
	private static final String BLIND_AD = "BlindAd";
	private static final String FACILITY_NAME = "FacilityName";
	private static final String EMAIL_DISPLAY = "EmailDisplay";
	private static final String EMAIL = "Email";
	private static final String IS_INTERNATIONAL = "IsInternational";
	private static final String IS_NATIONAL = "IsNational";
	private static final String IS_FEATURED = "IsFeatured";
	private static final String JOB_COUNT = "JobCount";
	private static final String JOB_ID = "JobId";
	private static final String JOB_NUMBER = "Job Number";
	private static final String JOB_GEO = "Job Geo";
	private static final String JOB_POSITION = "JobPosition";
	private static final String JOB_GEO_0_LATLON = "jobGeo0LatLon";
	private static final String JOB_GEO_1_LATLON = "jobGeo1LatLon";
	private static final String URL_DISPLAY = "URLDisplay";
	
	private static final String TOTAL_NO_RECORDS = "TotalNoRecords";
	private static final String JSON_ROWS = "jsonRows";
	
	private static final String DEF_TYPE = "defType";
	private static final String SELECT = "select";
	private static final String SO_TIMEOUT = "sotimeout";
	private static final String CONNECTION_TIMEOUT = "connectiontimeout";
	private static final String MAX_CONNECTION_HOST = "maxconnectionperhost";
	private static final String MAX_TOTAL_CONNECTION = "maxtotalconnection";
	private static final String FOLLOW_REDIRECTS = "followredirects";
	private static final String ALLOW_COMPRESSION = "allowcompression";
	private static final String MAX_RETRIES = "maxretries";
	
	private static final String QF = "qf";
	private static final String PF = "pf";
	private static final String PS = "ps";
	private static final String MM = "mm";
	private static final String BQ = "bq";
	private static final String BF = "bf";
	private static final String SORT = "sort";
	private static final String Q_ALT = "q.alt";
	private static final String ROWS = "rows";
	private static final String START = "start";
	private static final String Q = "q";

	//For Resume
	
	public static final String POST_RESUME = "postresume";

	// Date Patterns
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DISP_DATE_PATTERN = "MM/dd/yyyy";
	public static final String NEWDATE_PATTERN = "EEE MMM d HH:mm:ss z yyyy";
	
	
	
	public static final String ERROR_STRING = "error";
	public static final String OK_STRING = "ok";
	
}
