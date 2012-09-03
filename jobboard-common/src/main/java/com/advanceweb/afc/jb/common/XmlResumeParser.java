package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:23:57 PM
 */
public interface XmlResumeParser {

	/**
	 * 
	 * @param xmlString
	 */
	ResumeDTO parseXmlToResume(String xmlString);

	/**
	 * 
	 * @param xmlResponseObject
	 */
	List<ResumeDTO> parseXmlToResumeList(String xmlResponseObject);

}