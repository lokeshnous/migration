package com.advanceweb.afc.jb.employer.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.JobPostInventoryService;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.web.controller.SearchResumeForm;
import com.advanceweb.afc.jb.search.service.ResumeSearchService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.afc.jb.user.UserSubscriptionService;
import com.advanceweb.afc.jb.user.web.controller.TransformUserubscription;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * @author Prince
 * @version 1.0
 * @since 27th August, 2012
 */

@Controller
@RequestMapping("/employer")
public class EmployerDashBoardController extends AbstractController {

	private static final Logger LOGGER = Logger
			.getLogger(EmployerDashBoardController.class);

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ResumeSearchService resumeSearchService;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private UserSubscriptionService userSubService;

	@Autowired
	private AdService adService;

	@Autowired
	private TransformUserubscription userubscription;

	@Autowired
	private JobPostInventoryService inventoryService;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	

	private @Value("${funnelChartPath}")
	String funnelChartPath;
	
	private static final String EMPLOYERDASHBOARDFORM = "employerDashBoardForm";
	private static final String JBPOSTTOTALLIST = "jbPostTotalList";
	private static final String COUNT = "count";
	private static final String AVAQUANTITY = "avaQuantity";

	@RequestMapping("/employerDashBoard")
	public ModelAndView displayDashBoard(
			@ModelAttribute(EMPLOYERDASHBOARDFORM) MetricsForm employerDashBoardForm,
			HttpSession session, HttpServletRequest request)
			throws JobBoardServiceException {
		SearchResumeForm searchResumeForm = new SearchResumeForm();
		session.removeAttribute(JBPOSTTOTALLIST);
		session.removeAttribute(COUNT);
		session.removeAttribute(AVAQUANTITY);
		String enableAccess = "true";
		String enablePostEditAccess = "true";
		ModelAndView model = new ModelAndView();
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		EmployerInfoDTO roleList = facilityService.facilityDetails(userId);
		if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.FULL_ACCESS)) {
			enableAccess = "false";
			model.addObject("enableAccess", enableAccess);
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.MANAGEEDITACCESS)) {
			enablePostEditAccess = "false";
			model.addObject("enablePostEditAccess", enablePostEditAccess);
		}

		int resumeSearchCount = 0;
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = resumeSearchService
				.viewMySavedSearches(userId);
		resumeSearchCount = saveSearchedJobsDTOList.size();
		employerDashBoardForm.setResumeSearchCount(resumeSearchCount);

		model.addObject("enableAccess", enableAccess);
		model.addObject("enablePostEditAccess", enablePostEditAccess);
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();

		// Available Job Postings

		List<JobPostingInventoryDTO> inventiryDTO = inventoryService
				.getInventoryDetails(userId, facilityId);
		int avaQuantity = 0;
		for (JobPostingInventoryDTO dto : inventiryDTO) {
			avaQuantity = avaQuantity + dto.getAvailableQty();

		}

		// active job posting
		int count = loginService.getactivejobposting(facilityId);

		// Get All facilities
		List<DropDownDTO> downDTOs = new ArrayList<DropDownDTO>();
		try {
			downDTOs = facilityService.getFacilityGroup(facilityId);
		} catch (JobBoardException e) {
			LOGGER.info("Error occurred while getting data for metrics" + e);
		}

		// Retrieve Current subscriptions of the user
		List<DropDownDTO> currentSubs = getCurrentSubscriptions(facilityId);
		Set<DropDownDTO> set = new HashSet<DropDownDTO>();
		for (DropDownDTO dto : currentSubs) {
			set.add(dto);
		}

		// getting the metrics details
		jbPostTotalList = getMetricsDetails(facilityId);

		model.addObject("downDTOs", downDTOs);
		model.addObject(JBPOSTTOTALLIST, jbPostTotalList);
		session.setAttribute(JBPOSTTOTALLIST, jbPostTotalList);
		session.setAttribute(COUNT, count);
		session.setAttribute(AVAQUANTITY, avaQuantity);

		model.addObject("currentSubs", set);
		model.addObject("searchResumeForm", searchResumeForm);
		model.addObject(EMPLOYERDASHBOARDFORM, employerDashBoardForm);
		model.setViewName("employerDashboard");
		// get the Ads
		populateAdsForEmployerDashboard(request, session, model);

		return model;

	}

	/**
	 * Populate the Ads for employer dashboard page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void populateAdsForEmployerDashboard (HttpServletRequest request,
			HttpSession session, ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.EMPLOYER_DASHBOARD);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageTop", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageBtm", bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method is used to display the metrics details for selected employer
	 * in the drop down
	 * 
	 * @param employerDashBoardForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewEmployerMetrics", method = RequestMethod.GET)
	public @ResponseBody
	void viewEmployerMetrics(
			@ModelAttribute(EMPLOYERDASHBOARDFORM) MetricsForm employerDashBoardForm,
			BindingResult result, HttpSession session,
			@RequestParam("selEmployerId") int selEmployerId) {
		session.removeAttribute(JBPOSTTOTALLIST);
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		// getting the metrics details
		jbPostTotalList = getMetricsDetails(selEmployerId);
		session.setAttribute(JBPOSTTOTALLIST, jbPostTotalList);

	}

	/**
	 * Get the metric details page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/metricsDetails")
	public ModelAndView getMetricsDetails(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("metricsDetails");
		return modelAndView;
	}

	/**
	 * This method is to get the metrics details for facility
	 * 
	 * @param facilityId
	 * @return
	 */
	private List<MetricsDTO> getMetricsDetails(int facilityId) {
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		MetricsDTO metricsDTO = new MetricsDTO();
		// Get the job post details of logged in employer
		List<MetricsDTO> metricsDTOs = facilityService
				.getJobPostTotal(facilityId);

		// Getting metrics values from look up table
		List<DropDownDTO> metricsList = populateDropdownsService
				.populateDropdown("Metrics");

		// jbPostTotalList will be having job post total details for metrics
		int views = 0;
		int clicks = 0;
		int applies = 0;
		int size = metricsDTOs.size();
		for (int i = 0; i < metricsDTOs.size(); i++) {
			MetricsDTO dto = new MetricsDTO();
			dto = (MetricsDTO) metricsDTOs.get(i);
			views = views + dto.getViews();
			clicks = clicks + dto.getClicks();
			applies = applies + dto.getApplies();
		}
		metricsDTO.setMetricsName(metricsList.get(0).getOptionName());
		metricsDTO.setViews(views);
		metricsDTO.setClicks(clicks);
		metricsDTO.setApplies(applies);
		jbPostTotalList.add(0, metricsDTO);
		metricsDTO = new MetricsDTO();

		// Calculating average per job posting
		int avgViews = 0;
		int avgClicks = 0;
		int avgApplies = 0;
		if (size > 0) {
			avgViews = views / size;
			avgClicks = clicks / size;
			avgApplies = applies / size;
		}
		metricsDTO.setMetricsName(metricsList.get(1).getOptionName());
		metricsDTO.setViews(avgViews);
		metricsDTO.setClicks(avgClicks);
		metricsDTO.setApplies(avgApplies);
		jbPostTotalList.add(1, metricsDTO);
		metricsDTO = new MetricsDTO();

		// Calculating site - wide average per job posting
		int swAvgViews = 0;
		int swAvgClicks = 0;
		int swAvgApplies = 0;
		long count = 0;
		try {
			count = facilityService.getEmployerCount();
		} catch (JobBoardException e) {
			LOGGER.info("Error occured while getting the Result from Database");
		}

		if (count > 0) {
			swAvgViews = (int) (views / count);
			swAvgClicks = (int) (clicks / count);
			swAvgApplies = (int) (applies / count);
		}
		metricsDTO.setMetricsName(metricsList.get(2).getOptionName());
		metricsDTO.setViews(swAvgViews);
		metricsDTO.setClicks(swAvgClicks);
		metricsDTO.setApplies(swAvgApplies);
		jbPostTotalList.add(2, metricsDTO);

		return jbPostTotalList;
	}

	@RequestMapping(value = "/showMertics", method = RequestMethod.GET)
	public ModelAndView employerMetrics(
			HttpSession session,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("selEmployerId") int selEmployerId,
			@ModelAttribute(EMPLOYERDASHBOARDFORM) MetricsForm employerDashBoardForm,
			BindingResult result) throws JobBoardException {
		session.removeAttribute(JBPOSTTOTALLIST);
		java.util.Date startFrom = null;
		java.util.Date endFrom = null;
		String pattern = MMJBCommonConstants.DISP_DATE_RANGE;
		DateFormat formater = new SimpleDateFormat(pattern, Locale.US);

		try {
			startFrom = formater.parse(startDate);

			endFrom = formater.parse(endDate);
		} catch (Exception e) {
			LOGGER.error("date format is not correct");
		}

		ModelAndView model = new ModelAndView();
		MetricsDTO metricsDTO = new MetricsDTO();
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();

		List<MetricsDTO> jobstatDTOs = loginService.employerMetrics(startFrom,
				endFrom, selEmployerId);

		// Getting metrics values from look up table
		List<DropDownDTO> metricsList = populateDropdownsService
				.populateDropdown("Metrics");

		// jbPostTotalList will be having job post total details for metrics
		int views = 0;
		int clicks = 0;
		int applies = 0;
		int size = jobstatDTOs.size();
		MetricsDTO dto = new MetricsDTO();
		for (int i = 0; i < jobstatDTOs.size(); i++) {

			dto = (MetricsDTO) jobstatDTOs.get(i);
			views = views + dto.getViews();
			clicks = clicks + dto.getClicks();
			applies = applies + dto.getApplies();
		}
		metricsDTO.setMetricsName(metricsList.get(0).getOptionName());
		metricsDTO.setViews(views);
		metricsDTO.setClicks(clicks);
		metricsDTO.setApplies(applies);
		jbPostTotalList.add(0, metricsDTO);
		metricsDTO = new MetricsDTO();
		// Calculating average per job posting
		int avgViews = 0;
		int avgClicks = 0;
		int avgApplies = 0;
		if (size > 0) {
			avgViews = views / size;
			avgClicks = clicks / size;
			avgApplies = applies / size;
		}
		metricsDTO.setMetricsName(metricsList.get(1).getOptionName());
		metricsDTO.setViews(avgViews);
		metricsDTO.setClicks(avgClicks);
		metricsDTO.setApplies(avgApplies);
		jbPostTotalList.add(1, metricsDTO);
		metricsDTO = new MetricsDTO();
		// Calculating site - wide average per job posting
		int swAvgViews = 0;
		int swAvgClicks = 0;
		int swAvgApplies = 0;
		long count = 0;
		try {
			count = userService.getEmployerCount();
		} catch (JobBoardException e) {
			LOGGER.info("Error occured while getting the Result from Database");
		}
		if (count > 0) {
			swAvgViews = (int) (views / count);
			swAvgClicks = (int) (clicks / count);
			swAvgApplies = (int) (applies / count);
		}
		metricsDTO.setMetricsName(metricsList.get(2).getOptionName());
		metricsDTO.setViews(swAvgViews);
		metricsDTO.setClicks(swAvgClicks);
		metricsDTO.setApplies(swAvgApplies);
		jbPostTotalList.add(2, metricsDTO);
		model.addObject(JBPOSTTOTALLIST, jbPostTotalList);
		session.setAttribute(JBPOSTTOTALLIST, jbPostTotalList);
		model.addObject(EMPLOYERDASHBOARDFORM, employerDashBoardForm);
		model.addObject("searchResumeForm", new SearchResumeForm());
		model.setViewName("employerDashboard");
		return model;
	}

	/**
	 * 
	 * @param session
	 * @param facilityId
	 * @param employerDashBoardForm
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/availableJobPostings", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject availableJobPostings(
			HttpSession session,
			@RequestParam("selEmployerId") int facilityId,
			@ModelAttribute("employerDashBoardForm") EmployerDashBoardForm employerDashBoardForm,
			BindingResult result) throws JobBoardException {
		JSONObject jsonObject = new JSONObject();

		session.removeAttribute(AVAQUANTITY);
		session.removeAttribute(COUNT);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		List<JobPostingInventoryDTO> inventiryDTO = inventoryService
				.getInventoryDetails(userId, facilityId);
		int avaQuantity = 0;
		for (JobPostingInventoryDTO dto : inventiryDTO) {
			avaQuantity = avaQuantity + dto.getAvailableQty();

		}

		int count = loginService.getactivejobposting(facilityId);

		jsonObject.put(AVAQUANTITY, avaQuantity);
		jsonObject.put(COUNT, count);
		session.setAttribute(COUNT, count);
		session.setAttribute(AVAQUANTITY, avaQuantity);
		return jsonObject;

	}

	@RequestMapping("/getExcelSheet")
	public ModelAndView getXLS(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			HSSFWorkbook workbook) throws ClassNotFoundException,
			ServletRequestBindingException, JobBoardServiceException,
			IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment;filename=mmreport.xls");
		response.setHeader("Cache-Control", "no-cache");

		ServletOutputStream outputStream = null;

		@SuppressWarnings("unchecked")
		List<MetricsDTO> dtos = (List<MetricsDTO>) session
				.getAttribute(JBPOSTTOTALLIST);

		String output = ServletRequestUtils.getStringParameter(request,
				"output");
		int avaQuantity = (Integer) session.getAttribute(AVAQUANTITY);
		int count = (Integer) session.getAttribute(COUNT);

		HSSFSheet sheet = workbook.createSheet("MM Job Board");

		HSSFRow header = sheet.createRow(0);
		int rowNum = 1;
		header.createCell(4).setCellValue("Views");

		header.createCell(5).setCellValue("Clicks");

		header.createCell(6).setCellValue("Applies");

		for (MetricsDTO dtolist : dtos) {

			HSSFRow row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(dtolist.getMetricsName());

			row.createCell(4).setCellValue(dtolist.getViews());
			row.createCell(5).setCellValue(dtolist.getClicks());
			row.createCell(6).setCellValue(dtolist.getApplies());

		}

		HSSFRow row2 = sheet.createRow(6);

		row2.createCell(6).setCellValue("Available Job Postings");

		HSSFRow row3 = sheet.createRow(5);

		row3.createCell(6).setCellValue("Active Job Postings");

		row3.createCell(8).setCellValue(count);

		row2.createCell(8).setCellValue(avaQuantity);

		try {
			// FileInputStream obtains input bytes from the image file
			InputStream inputStream = new FileInputStream(
					funnelChartPath);
		

		// Get the contents of an InputStream as a byte[].
		byte[] bytes = IOUtils.toByteArray(inputStream);
		// Adds a picture to the workbook
		int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
		// close the input stream
		inputStream.close();
		
		// Drawing drawing = sheet.createDrawingPatriarch();
		@SuppressWarnings("unused")
		CreationHelper helper = workbook.getCreationHelper();
		// ClientAnchor anchor = helper.createClientAnchor();
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFClientAnchor anchor = new HSSFClientAnchor();
		anchor.setRow1(15);
		// Creates a picture
		HSSFPicture pict = (HSSFPicture) patriarch.createPicture(anchor,
				pictureIdx);

		pict.resize(0.50);

		if (output == null || "".equals(output)) {

			outputStream = response.getOutputStream();
			workbook.write(outputStream);

			if (outputStream != null) {
				outputStream.close();
			}

		}
		} catch (Exception e) {
			ModelAndView model = new ModelAndView();
			model.addObject(EMPLOYERDASHBOARDFORM, new MetricsForm());
			model.addObject("searchResumeForm", new SearchResumeForm());
			model.setViewName("employerDashboard");
			return model;
		}
		return new ModelAndView();
	}

	/**
	 * Retrieve Current subscriptions of the user
	 * 
	 * @param userId
	 * @return
	 */
	private List<DropDownDTO> getCurrentSubscriptions(int facilityId) {

		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getFacilitySubList();

		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentFacilitySub(facilityId);

		List<DropDownDTO> currentSubs = userubscription
				.jsSubscriptionDTOToJobSeekerSubscriptionForm(currentSubsList,
						listSubscriptions);
		return currentSubs;

	}
}
