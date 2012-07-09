package com.advanceweb.afc.jb.webapp.web.controllers.resume;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.resume.ResumeService;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 9, 2012
 */

@Controller
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/manageResume", method = RequestMethod.GET)
	public String getResumes(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {

		List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(1);

		for (ResumeDTO resumeDTO : resumeDTOList) {
			System.out.println(resumeDTO);
		}

		return "manageResume";
	}

}
