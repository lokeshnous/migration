package com.advanceweb.afc.jb.webapp.web.job.seeker.activity.controllers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.dto.CreateResumeDTO;
import com.advanceweb.afc.jb.registration.ProfileRegistration;
import com.advanceweb.afc.jb.resume.CreateResumeService;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerRegistrationForm;
import com.advanceweb.afc.jb.webapp.web.forms.resume.CreateResume;
import com.advanceweb.afc.jb.webapp.web.helper.ReadDocFile;
import com.advanceweb.afc.jb.jobseeker.activity.*;

/**
 * This controller belongs to all jobseekers activity
 * 
 * @author sharadk
 * 
 */

@Controller
public class JobSeekerActivityController {

	@Autowired
	private JobSeekerActivity jobSeekerActivity;

	public JobSeekerActivityController() {
	}

	/**
	 * to get applied Job
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/viewAppliedJob", method = RequestMethod.GET)
	public ModelAndView getAppliedJob(Map model) {

		List<AppliedJobDTO> appliedJobDTO = jobSeekerActivity
				.getAppliedJobs(13100);

		return new ModelAndView("jobSeekerActivity");
	}

	/**
	 * delete action for applied job
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAppliedJob")
	public ModelAndView deleteAppliedJob(@RequestParam("id") Long id) {

		jobSeekerActivity.deleteAppliedJobs(id);
		return new ModelAndView("jobSeekerActivity");
	}

	/**
	 * delete action for Saved job
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteSavedJob")
	public ModelAndView deleteSavedJob(@RequestParam("id") Long id) {

		jobSeekerActivity.deleteSavedJobs(id);
		return new ModelAndView("jobSeekerActivity");
	}

	/**
	 * to get Saved Job
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/viewSavedJob", method = RequestMethod.GET)
	public ModelAndView getSavedJob(Map model) {
		List<SavedJobDTO> savedJobDTO = jobSeekerActivity.getSavedJobs(13100);

		return new ModelAndView("jobSeekerActivity");
	}

	public void setJobSeekerActivity(JobSeekerActivity jobSeekerActivity) {
		this.jobSeekerActivity = jobSeekerActivity;
	}

}
