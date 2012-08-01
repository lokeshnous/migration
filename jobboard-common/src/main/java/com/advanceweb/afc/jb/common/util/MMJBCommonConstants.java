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
	public static final String SLASH_SELECT_SLASH = "/select/?";
	public static final String AMP = "&";
	public static final String EQUAL_TO = "=";
	public static final String POST_RESUME = "postresume";

	// Date Patterns
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DISP_DATE_PATTERN = "MM/dd/yyyy";
	public static final String NEWDATE_PATTERN = "EEE MMM d HH:mm:ss z yyyy";
	
	public static final String ERROR_STRING = "error";
	public static final String OK_STRING = "ok";
	
}
