/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.home.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.event.service.ClickService;

@Controller
public class ClickController {

	/** The click service. */
	@Autowired
	private ClickService clickService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("ClickController.class");

	/**
	 * Gets the clickevent.
	 *
	 * @param jobId the job id
	 * @param type the type
	 * @param request the request
	 * @param response the response
	 * @return the clickevent
	 */
	@ResponseBody
	@RequestMapping(value = "/clickevent", method = RequestMethod.GET)
	public String getclickevent(@RequestParam("jobid") int jobId,
			@RequestParam("type") String type, HttpServletRequest request,
			HttpServletResponse response) {
		String finalresult = MMJBCommonConstants.ERROR_STRING;

		try {
			clickService.saveClickEvent(jobId, type);
			finalresult = MMJBCommonConstants.OK_STRING;
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			finalresult = MMJBCommonConstants.ERROR_STRING;
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			LOGGER.error("Error occured while updating the click event", e);
		}
		return finalresult;
	}
}
