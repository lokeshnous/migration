package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.search.SearchParamDTO;

/**
 * 
 * @author Pramoda Patil
 * 
 * @Version 1.0
 * @Since 9th Oct, 2012
 */
@Component("jobSearchValidator")
public class JobSearchValidator {

	private static final String CURRENT_URL = "currentUrl";

	@Value("${ajaxMsg}")
	private String ajaxMsg;

	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${ajaxNavigationPath}")
	private String ajaxNavigationPath;

	@Value("${jobSearchValidateKeyword}")
	private String jbSearchValKeyword;

	@Value("${jobSearchValidateCity}")
	private String jbSearchValCity;

	/**
	 * validate job apply method like by ATS, Website or Email and navigate to
	 * respected site if by ATS or Website.
	 * 
	 * @param jobId
	 * @param jobApplyTypeDTO
	 * @return
	 */
	public boolean validateApplyType(int jobId, JSONObject jsonObject,
			JobApplyTypeDTO jobApplyTypeDTO) {
		// apply job by apply type like by ATS, Website or Email
		boolean status = true;

		if (jobApplyTypeDTO != null
				&& (jobApplyTypeDTO.getApplyMethod().equalsIgnoreCase(
						MMJBCommonConstants.APPLY_TO_ATS) || jobApplyTypeDTO
						.getApplyMethod().equalsIgnoreCase(
								MMJBCommonConstants.APPLY_TO_URL))) {
			status = false;
			jsonObject.put("applyMethod", jobApplyTypeDTO.getApplyMethod());
			jsonObject.put("applyLink", jobApplyTypeDTO.getApplyLink());
		}
		return status;
	}

	/**
	 * Check for Login and navigate to login page if not logged in.
	 * 
	 * @param map
	 * @param jobId
	 * @param currentUrl
	 * @param session
	 * @param jsonObject
	 * @param request 
	 */
	public boolean isLoggedIn(Map<String, Object> map, int jobId,String jobTitle,
			String currentUrl, HttpSession session, JSONObject jsonObject,
			HttpServletRequest request) {
		boolean status = true;
		// Check for job seeker login
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			//map.put("loginForm", new LoginForm());
			jsonObject.put(ajaxNavigationPath, request.getContextPath()
					+ "/jobsearch/jobseekerApplyJobPopUp"+dothtmlExtention);
			session.setAttribute("jobId", jobId);
			session.setAttribute("jobTitle", jobTitle);
			session.setAttribute(CURRENT_URL, currentUrl);
			status = false;
		}
		return status;
	}

	/**
	 * Method called to validate the search criteria
	 * 
	 * @param jobSearchResultForm
	 * @param session 
	 * @param jsonObject
	 */
	public JSONObject validateJobSearch(JobSearchResultForm jobSearchResultForm, HttpSession session
			) {
		
		JSONObject jsonObject = null;
		if (session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) != null) {			
//			String searchtype =((HashMap<String, String>) session
//					.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP))
//					.get(MMJBCommonConstants.SEARCH_TYPE);
			String searchtype = jobSearchResultForm.getSearchtype();	
			if(searchtype.equalsIgnoreCase(MMJBCommonConstants.KEYWORD_SEARCH))
				if (StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())) {
					jsonObject = new JSONObject();
					jsonObject.put(ajaxMsg, jbSearchValKeyword);
				} else if ((!jobSearchResultForm.getRadius().equalsIgnoreCase(
						MMJBCommonConstants.ZERO))
						&& StringUtils.isEmpty(jobSearchResultForm.getCityState()
								.trim())) {
					jsonObject = new JSONObject();
					jsonObject.put(ajaxMsg, jbSearchValCity);
				}
		}
		return jsonObject;
	}
	
	
	/**
	 * validate the Email pattern 
	 * 
	 * @param emailAddress
	 *            emailAddress.
	 * @return true for valid
	 * 		   false for invalid mail.
	 */

	public boolean validateEmailPattern(String emailAddress) {
		Pattern pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}
}
