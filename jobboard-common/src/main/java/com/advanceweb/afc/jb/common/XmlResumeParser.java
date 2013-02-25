/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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