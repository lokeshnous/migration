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
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	//For DropDowns
	public static final String EMPLOYMENT_TYPE="EmploymentType";
	
	//For SOLR search
	public static final String SLASH = "/";
	public static final String SLASH_SELECT_SLASH = "/select/?";
	public static final String AMP = "&";
	public static final String EQUAL_TO = "=";
}
