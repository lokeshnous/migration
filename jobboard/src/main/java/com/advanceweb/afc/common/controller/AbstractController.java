/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.afc.jb.web.constants.RequestHeaderNames;
import com.advanceweb.common.client.ClientContext;

public abstract class AbstractController {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	/** The profile registration. */
	@Autowired
	private ProfileRegistration profileRegistration;

	/** The client application. */
	@Value("${client.application}")
	private String clientApplication;
	
	/** The check session map. */
	@Autowired
	private CheckSessionMap checkSessionMap;

	/**
	 * Get the client context details
	 * 
	 * @param request
	 * @param session
	 * @param string 
	 * @return
	 */
	protected ClientContext getClientContextDetails(HttpServletRequest request,
			HttpSession session, String pageName) {
		String currentSearch = null;
		String previousSearch = null;
		String clientLocation = null;
		String userSelectedLocation = null;
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);

		ClientContext clientContext = new ClientContext();
		clientContext.setProperty(ClientContext.CLIENT_APPLICATION,
				clientApplication);
		clientContext.setProperty(ClientContext.CLIENT_USER_AGENT,
				request.getHeader(RequestHeaderNames.USER_AGENT));
		clientContext.setProperty(ClientContext.CLIENT_SESSIONID,
				session.getId());
		clientContext.setProperty(ClientContext.CLIENT_IP,
				request.getLocalAddr());
		clientContext.setProperty(ClientContext.CLIENT_PAGE, pageName);
		clientContext.setProperty(ClientContext.CLIENT_REFERRER,
				request.getHeader(RequestHeaderNames.REFERER));
		clientContext.setProperty(ClientContext.CLIENT_HOSTNAME,
				request.getHeader(RequestHeaderNames.HOST));
		clientContext.setProperty(ClientContext.CLIENT_REQUEST_URL, request
				.getRequestURL().toString());
		if (sessionMap.get(SearchParamDTO.KEYWORDS) != null) {
			currentSearch = sessionMap.get(SearchParamDTO.KEYWORDS);
		}
		if (session.getAttribute(MMJBCommonConstants.PREV_JOB_SEARCH_KEYWORDS) != null) {
			previousSearch = (String) session
					.getAttribute(MMJBCommonConstants.PREV_JOB_SEARCH_KEYWORDS);
		}
		if (sessionMap.get(SearchParamDTO.CITY_STATE) != null
				&& !sessionMap.get(SearchParamDTO.CITY_STATE).isEmpty()) {
			userSelectedLocation = sessionMap.get(SearchParamDTO.CITY_STATE);
		}
		clientContext.setProperty(ClientContext.USER_CURRENT_SEARCH,
				currentSearch);
		clientContext.setProperty(ClientContext.USER_PREVIOUS_SEARCH,
				previousSearch);
		clientContext.setProperty(ClientContext.CLIENT_LOCATION, clientLocation);
		
		// load the location when jobs are searched by categories
		if (sessionMap.get(SearchParamDTO.SEARCH_NAME) != null
				&& sessionMap.get(SearchParamDTO.SEARCH_NAME).equalsIgnoreCase(
						MMJBCommonConstants.BROWSE_SEARCH)) {
			if (!sessionMap.get(MMJBCommonConstants.THIRD_FQ_PARAM).isEmpty()) {
				userSelectedLocation = sessionMap
						.get(MMJBCommonConstants.THIRD_FQ_PARAM)
						.replace(MMJBCommonConstants.FQ_STATE, "")
						.replace("\"", "");
			}
		}
		clientContext.setProperty(ClientContext.USER_SELECTED_LOCATION, userSelectedLocation);
		
		// Check for login User details
		int userId = 0;
		String userLocation = null;
		String userProfession = null;
		String userRole = null;
		String userGender = null;
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			List<UserRoleDTO> userRoleDTOs = userService.getUserRole(userId);
			userRole = userRoleDTOs.get(0).getRoleName();
			// TODO: Currently jobseeker is considering
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration
					.viewProfile(userId);
			for (ProfileAttribDTO profileAttribDTO : jsRegistrationDTO
					.getAttribList()) {

				if (profileAttribDTO.getStrLabelValue() != null
						&& profileAttribDTO.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.MYPROFESSION)) {
					if (isInteger(profileAttribDTO.getStrLabelValue())) {
						userProfession = getProfessionByList(profileAttribDTO);
					} else {
						userProfession = profileAttribDTO.getStrLabelValue();
					}
				}
				if (profileAttribDTO.getStrLabelValue() != null
						&& profileAttribDTO.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.CAP_CITY)
						&& !isInteger(profileAttribDTO.getStrLabelValue())) {
					userLocation = profileAttribDTO.getStrLabelValue();
				}
			}

			// TODO:gender not storing
			// userGender = null;
		}
		clientContext
				.setProperty(ClientContext.USER_ID, String.valueOf(userId));
		clientContext.setProperty(ClientContext.USER_LOCATION, userLocation);
		clientContext
				.setProperty(ClientContext.USER_PROFESSION, userProfession);
		clientContext.setProperty(ClientContext.USER_GENDER, userGender);
		clientContext.setProperty(ClientContext.USER_ROLE, userRole);
		return clientContext;
	}

	/**
	 * Get the Profession name by dropdown list
	 * 
	 * @param userProfession
	 * @param profileAttribDTO
	 * @return
	 */
	private String getProfessionByList(
			ProfileAttribDTO profileAttribDTO) {
		String userProfession = null;
		for (DropDownDTO dropDown : profileAttribDTO.getDropdown()) {
			if (profileAttribDTO.getStrLabelValue().equals(
					dropDown.getOptionId())) {
				userProfession = dropDown.getOptionName();
			}
		}
		return userProfession;
	}
	
	/**
	 * Check for integer to get the other profession value
	 * 
	 * @param input
	 * @return
	 */
	private boolean isInteger(String input) {
		boolean status = false;
		try {
			Integer.parseInt(input);
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

}
