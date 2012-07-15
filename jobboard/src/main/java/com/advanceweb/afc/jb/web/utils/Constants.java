package com.advanceweb.afc.jb.web.utils;
/**
 * 
 * @author nishantn
 * Constants are declared here
 *
 */
public final class Constants {

//	public static final String XML_FILE_NAME_HEALTHCARE = "D:\\xml\\HealthCareNews.xml";
//	public static final String XML_FILE_NAME_CAREERTOOL = "D:\\xml\\CareerToolResource.xml";
	public static final String XML_FILE_NAME_HEALTHCARE = "D:\\HealthCareNews.xml";
	public static final String XML_FILE_NAME_CAREERTOOL = "D:\\CareerToolResource.xml";


	// PRIVATE //

	/**
	   The caller references the constants using <tt>Constants.EMPTY_STRING</tt>, 
	   and so on. Thus, the caller should be prevented from constructing objects of 
	   this class, by declaring this private constructor. 
	 */
	private Constants(){
		throw new AssertionError();
	}
}
