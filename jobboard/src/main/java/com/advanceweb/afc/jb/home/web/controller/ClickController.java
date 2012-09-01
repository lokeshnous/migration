package com.advanceweb.afc.jb.home.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advanceweb.afc.jb.common.ClickEventDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.event.service.ClickService;

@Controller
public class ClickController {

	@Autowired
	private ClickService clickService;

	private static final Logger LOGGER = Logger
			.getLogger("ClickController.class");

	@ResponseBody
	@RequestMapping(value = "/clickevent", method = RequestMethod.GET)
	public String getclickevent(@RequestParam("jobid") int jobid,
			@RequestParam("type") String type, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String finalresult = MMJBCommonConstants.ERROR_STRING;

		try {
			if (type.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_CLICK)) {
				// System.out.println(request.getParameter("type"));
				update(jobid, clickService,
						MMJBCommonConstants.CLICKTYPE_CLICK);
			} else if (type
					.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_APPLY)) {
				// System.out.println(request.getParameter("type"));
				update(jobid, clickService,
						MMJBCommonConstants.CLICKTYPE_APPLY);
			} else if (type
					.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_VIEW)) {
				// System.out.println(request.getParameter("type"));
				update(jobid, clickService,
						MMJBCommonConstants.CLICKTYPE_VIEW);
			}
			finalresult = MMJBCommonConstants.OK_STRING;
			response.setStatus(response.SC_OK);
		} catch (Exception e) {
			finalresult = MMJBCommonConstants.ERROR_STRING;
			response.setStatus(response.SC_NOT_FOUND);
			LOGGER.info("ERROR");
		}
		return finalresult;
	}

	private static final synchronized void update(int jobid,
			ClickService clickService, String type) {
		ClickEventDTO clickEventDTO = clickService.retrieveAllClicks(jobid);
		clickEventDTO.setJobid(jobid);
		if (type.equals(MMJBCommonConstants.CLICKTYPE_CLICK)) {
			clickEventDTO.setClicks(clickEventDTO.getClicks() + 1);
		} else if (type.equals(MMJBCommonConstants.CLICKTYPE_APPLY)) {
			clickEventDTO.setApplies(clickEventDTO.getApplies() + 1);
		} else if (type.equals(MMJBCommonConstants.CLICKTYPE_VIEW)) {
			clickEventDTO.setViews(clickEventDTO.getViews() + 1);
		}
		clickService.saveClickEvent(clickEventDTO);
	}

}
