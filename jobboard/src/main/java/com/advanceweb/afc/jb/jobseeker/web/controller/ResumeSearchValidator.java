/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.resume.web.controller.SearchResumeForm;


/**
 * This class is meant for all the validations required for resume search.
 * @author Reetesh R N
 * @Version 1.0
 * @Since 5th November, 2012
 */
@Component("resumeSearchValidator")
public class ResumeSearchValidator {

	/** The ajax msg. */
	@Value("${ajaxMsg}")
	private String ajaxMsg;
	
	/** The resume search validate keyword. */
	@Value("${resumeSearchValidateKeyword}")
	private String resumeSearchValidateKeyword;

	/*@Value("${resumeSearchValidateCity}")
	private String resumeSearchValidateCity;*/
	
	/**
	 * Method called to validate the resume search criteria
	 * 
	 * @param jobSearchResultForm
	 * @param jsonObject
	 */
	public JSONObject validateResumeSearch(SearchResumeForm searchResumeForm) {
		JSONObject jsonObject = null;
		if (StringUtils.isEmpty(searchResumeForm.getKeywords().trim())) {
			jsonObject = new JSONObject();
			jsonObject.put(ajaxMsg, resumeSearchValidateKeyword);
		} /*else if ((!searchResumeForm.getRadius().equalsIgnoreCase(
				MMJBCommonConstants.ZERO))
				&& StringUtils.isEmpty(searchResumeForm.getCityState()
						.trim())) {
			jsonObject = new JSONObject();
			jsonObject.put(ajaxMsg, resumeSearchValidateCity);
		}*/
		return jsonObject;
	}
	
}
