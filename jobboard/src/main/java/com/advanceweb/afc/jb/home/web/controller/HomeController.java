/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.home.web.controller;

/**
 * @author nishantn
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.NewsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.EmployerNewsFeedService;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.afc.jb.employer.web.controller.EmployerProfileManagementForm;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.web.utils.CopyUtil;
import com.advanceweb.afc.jb.web.utils.ReadFile;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

@Controller
@RequestMapping(value = "/healthcare")
public class HomeController extends AbstractController {

	/** The Constant FEATURED_EMPS_COUNT. */
	private static final String FEATURED_EMPS_COUNT = "count";

	/** The Constant PREV_FEATURED_EMP. */
	private static final String PREV_FEATURED_EMP = "prev";

	/** The Constant NEXT_FEATURED_EMP. */
	private static final String NEXT_FEATURED_EMP = "next";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(HomeController.class);

	/** The basedirectorypath. */
	@Value("${basedirectorypath}")
	private String basedirectorypath;

	/** The directory. */
	@Value("${directory}")
	private String directory;

	/** The career images directory. */
	@Value("${careerImagesDirectory}")
	private String careerImagesDirectory;

	/** The healthcarenewsfilename. */
	@Value("${healthcarenewsfilename}")
	private String healthcarenewsfilename;

	/** The careertoolfilename. */
	@Value("${careertoolfilename}")
	private String careertoolfilename;

	/** The logo path. */
	@Value("${logoPath}")
	private String logoPath;

	/** The media path. */
	@Value("${mediaPath}")
	private String mediaPath;

	/** The followuplinkfacebook. */
	@Value("${followuplinkfacebook}")
	private String followuplinkfacebook;

	/** The followuplinktwitter. */
	@Value("${followuplinktwitter}")
	private String followuplinktwitter;

	/** The followuplinkyoutube. */
	@Value("${followuplinkyoutube}")
	private String followuplinkyoutube;

	/** The followuplinklinkedin. */
	@Value("${followuplinklinkedin}")
	private String followuplinklinkedin;

	/** The manage feature employer profile. */
	@Autowired
	private ManageFeaturedEmployerProfile manageFeatureEmployerProfile;

	/** The ad service. */
	@Autowired
	private AdService adService;

	/** The employer news feed service. */
	@Autowired
	private EmployerNewsFeedService employerNewsFeedService;

	/** The Constant NEWS_LIST. */
	private static final String NEWS_LIST = "HomePageNewsList";

	/** The check session map. */
	@Autowired
	private CheckSessionMap checkSessionMap;

	/** The seo configuration. */
	@Autowired
	@Resource(name = "seoConfiguration")
	private Properties seoConfiguration;

	/**
	 * Gets the html contents.
	 * 
	 * @param request
	 *            the request
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @param jobSearchResultForm
	 *            the job search result form
	 * @return the html contents
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String gethtmlContents(HttpServletRequest request, Model model,
			HttpSession session,
			@ModelAttribute JobSearchResultForm jobSearchResultForm) {
		if (request.getQueryString() != null) {

			Map<String, String> sessionMap = checkSessionMap
					.getSearchSessionMap(session);
			sessionMap.put(MMJBCommonConstants.SEARCH_TYPE,
					MMJBCommonConstants.BASIC_SEARCH_TYPE);
			sessionMap.put(SearchParamDTO.KEYWORDS,
					request.getParameter("keyword"));
			sessionMap.put(SearchParamDTO.CITY_STATE,
					request.getParameter("citystate"));
			sessionMap.put(SearchParamDTO.RADIUS,
					request.getParameter("radius"));
			sessionMap.put(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));
			jobSearchResultForm.setCityState(request.getParameter("citystate"));
			jobSearchResultForm.setRadius(request.getParameter("radius"));
			jobSearchResultForm.setAutoload(true);
			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);
			session.setAttribute(MMJBCommonConstants.AUTOLOAD, "true");
		}
		try {

			/*
			 * if (new File(basedirectorypath + directory +
			 * healthcarenewsfilename) .exists()) { String htmlhealthcontent =
			 * ReadFile .htmlReader(basedirectorypath + directory +
			 * healthcarenewsfilename); model.addAttribute("healthcarenew",
			 * htmlhealthcontent); } else { model.addAttribute("healthcarenew",
			 * "<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />"
			 * ); }
			 */

			Map<String, List<NewsDTO>> newsMap = employerNewsFeedService
					.getNewsFromXML();

			List<NewsDTO> newsDTOList = newsMap.get(NEWS_LIST);
			if (null != newsDTOList && newsDTOList.size() > 5) {
				List<NewsDTO> modNewsDTOList = newsDTOList.subList(0, 5);
				model.addAttribute("newsDTOList", modNewsDTOList);
			} else {
				model.addAttribute("newsDTOList", newsDTOList);
			}

			session.removeAttribute(NEXT_FEATURED_EMP);
			session.removeAttribute(PREV_FEATURED_EMP);
			session.removeAttribute(FEATURED_EMPS_COUNT);

			int firstIndex = 0, lastIndex = 2;
			session.setAttribute(PREV_FEATURED_EMP, firstIndex);
			session.setAttribute(NEXT_FEATURED_EMP, lastIndex);

			List<CompanyProfileDTO> companyProfileDTOList = manageFeatureEmployerProfile
					.getEmployerList(firstIndex, 2);
			Long companyProfileDTOListCount = manageFeatureEmployerProfile
					.getEmployerListCount();
			session.setAttribute(FEATURED_EMPS_COUNT,
					companyProfileDTOListCount);
			session.setAttribute("companyProfileDTOList", companyProfileDTOList);
			model.addAttribute("followuplinkfacebook", followuplinkfacebook);
			model.addAttribute("followuplinktwitter", followuplinktwitter);
			model.addAttribute("followuplinkyoutube", followuplinkyoutube);
			model.addAttribute("followuplinklinkedin", followuplinklinkedin);
			// JobSearchResultForm jobSearchResultForm = new
			// JobSearchResultForm();
			model.addAttribute("jobSearchResultForm", jobSearchResultForm);
			model.addAttribute("isHomePage", true);

			// populate the Ads
			populateAds(request, session, model, PageNames.HOME);

		} catch (Exception e) {
			model.addAttribute("healthcarenew",
					"<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
			LOGGER.error(
					"Error occurred while getting the html content for home page",
					e);
		}
		// Get the SEO Details
		getSEODetails(model, request);
		// Create the URL for home page to get all jobs titles.
		String jobsUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/jobs/alljobs.html";
		model.addAttribute("jobsUrl", jobsUrl);
		String jobsUrlMessage = seoConfiguration.getProperty(
				"homepage.jobsurlmessage").trim();
		String staticContent = seoConfiguration.getProperty(
				"homepage.staticContent").trim();
		model.addAttribute("jobsUrlTitle", jobsUrlMessage);
		model.addAttribute("staticContent", staticContent);

		if (session.getAttribute("showCareersType") != null) {
			String showCareersType = (String) session
					.getAttribute("showCareersType");
			model.addAttribute("showCareersType", showCareersType);
			session.removeAttribute("showCareersType");
		}
		return "home";
	}

	/**
	 * The method generate the site map for application.
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/generateSiteMap", method = RequestMethod.GET)
	/*
	 * public ModelAndView startSiteMapGeneratot(HttpServletRequest request,
	 * Model model, HttpSession session){ ModelAndView modelAndView = new
	 * ModelAndView(); File myDir = new File("C:\\"); WebSitemapGenerator wsg;
	 * try { String websiteAdd = "http://"+request.getRequestURL().toString()
	 * .replace(request.getServletPath(), ""); wsg = new
	 * WebSitemapGenerator(websiteAdd, myDir); JobSearchResultDTO
	 * jobSearchResultDTO = null; JobSearchResultForm jobSearchResultForm = new
	 * JobSearchResultForm();
	 * 
	 * // Set the search type for SOLR facets String searchName =
	 * MMJBCommonConstants.KEYWORD_SEARCH;
	 * jobSearchResultForm.setSearchName(searchName);
	 * jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);
	 * 
	 * // merge the parameters int searchSeq = MMJBCommonConstants.ZERO_INT;
	 * String sessionId = ""; Map<String, String> paramMap = new HashMap<String,
	 * String>(); paramMap.put(SearchParamDTO.KEYWORDS, "*");
	 * paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
	 * paramMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(searchSeq + 1));
	 * paramMap.put(SearchParamDTO.SEARCH_NAME, searchName.trim());
	 * 
	 * // For testing. Remove it while committing
	 * paramMap.put(MMJBCommonConstants.SORT_PARAM,
	 * MMJBCommonConstants.POSTED_DT);
	 * paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
	 * MMJBCommonConstants.EMPTY);
	 * paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
	 * MMJBCommonConstants.EMPTY);
	 * paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
	 * MMJBCommonConstants.EMPTY);
	 * paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
	 * MMJBCommonConstants.EMPTY);
	 * paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
	 * MMJBCommonConstants.EMPTY); paramMap.put(MMJBCommonConstants.SORT_ORDER,
	 * MMJBCommonConstants.DESC_STR);
	 * paramMap.put(MMJBCommonConstants.FACET_SORT,
	 * MMJBCommonConstants.INDEX_STR);
	 * 
	 * try { for(int i = 1 ; i<20 ; i++){ long start = (i - 1) * 3000; long rows
	 * = 3000; jobSearchResultDTO = jobSearchService.jobSearch( paramMap, start,
	 * rows); List<JobDTO> jobDTOList = jobSearchResultDTO.getResultList();
	 * WebSitemapUrl url = null; String title = null; String jobId = null; for
	 * (JobDTO jobDTO : jobDTOList) { title =
	 * MMUtils.isNull(jobDTO.getJobTitle()); jobId =
	 * MMUtils.isNull(String.valueOf(jobDTO.getJobId())); if (!title.isEmpty())
	 * { title = title .replaceAll(
	 * MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN, ""); } url = new
	 * WebSitemapUrl
	 * .Options(websiteAdd+"/search/jobview/"+jobId+"/"+title+".html")
	 * .lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.HOURLY).build();
	 * wsg.addUrl(url);
	 * 
	 * }
	 * 
	 * } } catch (JobBoardException e) {
	 * LOGGER.debug("Error occured while getting the Job Search Result from SOLR..."
	 * ); } wsg.write(); } catch (MalformedURLException e1) {
	 * e1.printStackTrace(); }catch (Exception e) { LOGGER.error(e.getMessage(),
	 * e); } return modelAndView; }
	 */
	/**
	 * populate the ads for home ,featured employers list and featured employer
	 * detail page
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param pageName
	 */
	private void populateAds(HttpServletRequest request, HttpSession session,
			Model model, String pageName) {
		String bannerString = null;
		try {

			ClientContext clientContext = getClientContextDetails(request,
					session, pageName);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addAttribute(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addAttribute(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);

			if (pageName.equalsIgnoreCase(PageNames.HOME)) {
				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_TOP;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				model.addAttribute(MMJBCommonConstants.ADPGRIGHT_TOP,
						bannerString);

				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_MIDDLE;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				model.addAttribute(MMJBCommonConstants.ADPGRIGHT_MIDDLE,
						bannerString);

				// size = AdSize.IAB_WIDE_SKYSCRAPER;
				// position = AdPosition.RIGHT_TOP;
				// bannerString = adService
				// .getBanner(clientContext, size, position).getTag();
				// model.addAttribute(MMJBCommonConstants.ADPGRIGHT_TOP_MIDDLE,
				// bannerString);
			}

		} catch (Exception e) {
			LOGGER.error(
					"Error occurred while getting the html content for Ads", e);
		}
	}

	/**
	 * Get the SEO details.
	 * 
	 * @param model
	 * @param request
	 * @param jobDTO
	 */
	private void getSEODetails(Model model, HttpServletRequest request) {
		String metaDesc = seoConfiguration.getProperty(
				"homepage.meta.description").trim();
		// merge the parameters
		String metaTitle = seoConfiguration.getProperty("homepage.meta.title")
				.trim();
		model.addAttribute("metaDesc", metaDesc);
		model.addAttribute("metaTitle", metaTitle);
		model.addAttribute("canonicalUrl", request.getRequestURL());
	}

	/**
	 * Gets the featuredemployerslist.
	 * 
	 * @param request
	 *            the request
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @return the featuredemployerslist
	 */
	@RequestMapping(value = "/featuredemployers", method = RequestMethod.GET)
	public String getfeaturedemployerslist(HttpServletRequest request,
			Model model, HttpSession session) {
		List<CompanyProfileDTO> companyProfileDTOList = manageFeatureEmployerProfile
				.getEmployerList();
		model.addAttribute("companyProfileDTOList", companyProfileDTOList);
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.addAttribute("jobSearchResultForm", jobSearchResultForm);
		// populate the Ads
		populateAds(request, session, model, PageNames.FEATURED_EMPS);
		return "featuredemployers";
	}

	/**
	 * Method called to get the featured employer details by facility Id
	 * 
	 * @param employerProfileManagementForm
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/featuredemployerdetails", method = RequestMethod.GET)
	public String getfeaturedemployerbyid(
			EmployerProfileManagementForm employerProfileManagementForm,
			HttpServletRequest request, Model model, HttpSession session) {
		int facilityId = Integer.parseInt(request.getParameter("id"));
		facilityId = manageFeatureEmployerProfile.getParentGroup(facilityId);
		CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile
				.getEmployerDetails(facilityId);
		employerProfileManagementForm.setCompanyName(companyProfileDTO
				.getCompanyName());
		employerProfileManagementForm.setCompanyNews(companyProfileDTO
				.getCompanyNews());
		employerProfileManagementForm.setCompanyOverview(companyProfileDTO
				.getCompanyOverview());
		employerProfileManagementForm.setCompanyWebsite(companyProfileDTO
				.getCompanyWebsite());
		employerProfileManagementForm.setCompanyEmail(companyProfileDTO
				.getCompanyEmail());
		employerProfileManagementForm.setPositionTitle(companyProfileDTO
				.getPositionTitle());
		employerProfileManagementForm.setLogoPath(companyProfileDTO
				.getLogoPath());
		employerProfileManagementForm.setFacilityId(facilityId);
		if (null != companyProfileDTO.getPrimaryColor()) {
			employerProfileManagementForm.setPrimaryColor(companyProfileDTO
					.getPrimaryColor().replace("HEX #", "#"));
		}
		// TODO: Remove the hard codes used to check video : file server is not
		// fully mentained
		String path = request.getRequestURL().toString()
				.replace(request.getRequestURI(), MMJBCommonConstants.EMPTY);

		/*
		 * String path = null; try { path = InetAddress.getByName(
		 * request.getServerName()).getHostAddress().toString();
		 * if(request.getLocalPort() != 80){ path = path + ":" +
		 * request.getLocalPort(); } } catch (UnknownHostException e) {
		 * LOGGER.debug(e.getMessage(), e); }
		 */
		path = path + mediaPath + companyProfileDTO.getPositionalMedia();
		model.addAttribute("windowmediaplayerfilepath", path);
		model.addAttribute("employerProfileManagementForm",
				employerProfileManagementForm);
		model.addAttribute(
				"companyNameEncoded",
				MMUtils.encodeString(
						employerProfileManagementForm
								.getCompanyName()
								.trim()
								.replaceAll(
										MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
										"").trim()).toLowerCase());
		// populate the Ads
		populateAds(request, session, model, PageNames.FEATURED_EMP);
		return "featuredemployerdetails";
	}

	/**
	 * Gets the photo.
	 * 
	 * @param id
	 *            the id
	 * @param response
	 *            the response
	 * @param request
	 *            the request
	 * @return the photo
	 */
	@RequestMapping("/logo")
	public void getPhoto(@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			BufferedImage originalImage = ImageIO.read(new File(logoPath
					+ request.getParameter("id") + ".jpg"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
			// Display the image
			write(response, result.getBody());
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
	}

	/**
	 * Gets the image.
	 * 
	 * @param imageId
	 *            the image id
	 * @param response
	 *            the response
	 * @param request
	 *            the request
	 * @return the image
	 */
	@RequestMapping("/viewImage")
	public void getImage(@RequestParam("id") String imageId,
			HttpServletResponse response, HttpServletRequest request) {

		try {
			BufferedImage originalImage = ImageIO.read(new File(imageId));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage,
					imageId.substring(imageId.length() - 3, imageId.length()),
					baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
			// Display the image
			write(response, result.getBody());
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
	}

	/**
	 * Handle get my bytes request.
	 * 
	 * @param imageInByte
	 *            the image in byte
	 * @return the response entity
	 */
	public ResponseEntity<byte[]> handleGetMyBytesRequest(byte[] imageInByte) {
		// Get bytes from somewhere...
		byte[] byteData = imageInByte;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(byteData.length);

		return new ResponseEntity<byte[]>(byteData, responseHeaders,
				HttpStatus.OK);
	}

	/*
	 * public void getPhoto( HttpServletResponse response) { try{ //
	 * BufferedImage originalImage = // ImageIO.read(new
	 * File("C:\\Users\\SHISHER\\Desktop\\images\\123.jpg")); //
	 * System.out.println("in photo////////////////"); // ByteArrayOutputStream
	 * baos = new ByteArrayOutputStream(); // ImageIO.write( originalImage,
	 * "jpg", baos ); // baos.flush(); // byte[] imageInByte =
	 * baos.toByteArray(); // baos.close();
	 * 
	 * // Send the request as GET //ResponseEntity<byte[]> result =
	 * restTemplate.
	 * exchange("http://localhost:8080/spring-rest-provider/krams/person/{id}",
	 * HttpMethod.GET, entity, byte[].class, id); ResponseEntity<byte[]> result
	 * =retriveLogo(); // Display the image write(response, result.getBody());
	 * }catch(Exception e){
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * public ResponseEntity<byte[]> retriveLogo() throws IOException { byte[]
	 * data = extractBytes("D:\\images\\MercyRNlogo.jpg"); final HttpHeaders
	 * headers = new HttpHeaders();
	 * headers.setContentType(MediaType.IMAGE_JPEG); return new
	 * ResponseEntity<byte[]>(data, headers, HttpStatus.OK); }
	 * 
	 * 
	 * public byte[] extractBytes (String ImageName) throws IOException { //
	 * open image File imgPath = new File(ImageName); BufferedImage
	 * bufferedImage = ImageIO.read(imgPath); int type = bufferedImage.getType()
	 * == 0? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
	 * 
	 * BufferedImage resizeImageHintJpg = resizeImageWithHint(bufferedImage,
	 * type);
	 * 
	 * // get DataBufferBytes from Raster WritableRaster raster =
	 * resizeImageHintJpg .getRaster(); DataBufferByte data = (DataBufferByte)
	 * raster.getDataBuffer();
	 * 
	 * return ( data.getData() ); }
	 * 
	 * 
	 * private BufferedImage resizeImageWithHint(BufferedImage originalImage,
	 * int type){
	 * 
	 * BufferedImage resizedImage = new
	 * BufferedImage(Integer.parseInt(imgwidth), Integer.parseInt(imgheight),
	 * type); Graphics2D g = resizedImage.createGraphics();
	 * g.drawImage(originalImage, 0, 0, Integer.parseInt(imgwidth),
	 * Integer.parseInt(imgheight), null); g.dispose();
	 * g.setComposite(AlphaComposite.Src);
	 * 
	 * g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	 * RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	 * g.setRenderingHint(RenderingHints.KEY_RENDERING,
	 * RenderingHints.VALUE_RENDER_QUALITY);
	 * g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 * RenderingHints.VALUE_ANTIALIAS_ON);
	 * 
	 * return resizedImage; }
	 */
	/**
	 * Write.
	 * 
	 * @param response
	 *            the response
	 * @param bao
	 *            the bao
	 */
	public void write(HttpServletResponse response, ByteArrayOutputStream bao) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			bao.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	/**
	 * Writes the report to the output stream
	 */
	public void write(HttpServletResponse response, byte[] byteArray) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			outputStream.write(byteArray);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	/**
	 * Copy html files.
	 * 
	 * @param request
	 *            the request
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/copyhtmltolocal", method = RequestMethod.GET)
	public String copyHtmlFiles(HttpServletRequest request, Model model) {
		try {
			File directorycreation = new File(basedirectorypath + directory);
			directorycreation.mkdir();
			List<String> list = new ArrayList<String>();
			list.add(healthcarenewsfilename);
			list.add(careertoolfilename);
			CopyUtil.copy(list, basedirectorypath + directory);
			model.addAttribute("copyhtml", true);
		} catch (Exception e) {// Catch exception if any
			// System.err.println("Error: " + e.getMessage());
			model.addAttribute("copyhtml", "");
			LOGGER.error("Error while copying the HTML files" + e);
		}

		return "jspviewcontent";
	}

	/**
	 * get the Next/Prev featured employer list
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/featuredEmplist", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getFeaturedEmplist(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		JSONObject jsonObject = new JSONObject();
		String moveBy = request.getParameter("moveBy");
		int firstIndex = 0, lastIndex = 2;
		if (moveBy.equalsIgnoreCase(NEXT_FEATURED_EMP)) {
			int oldNext = (Integer) session.getAttribute(NEXT_FEATURED_EMP);
			int oldPrev = (Integer) session.getAttribute(PREV_FEATURED_EMP);
			lastIndex = oldNext + 2;
			firstIndex = oldPrev + 2;
		}
		if (moveBy.equalsIgnoreCase(PREV_FEATURED_EMP)) {
			int oldNext = (Integer) session.getAttribute(NEXT_FEATURED_EMP);
			int oldPrev = (Integer) session.getAttribute(PREV_FEATURED_EMP);
			lastIndex = oldNext - 2;
			firstIndex = oldPrev - 2;
		}
		session.setAttribute(NEXT_FEATURED_EMP, lastIndex);
		session.setAttribute(PREV_FEATURED_EMP, firstIndex);

		List<CompanyProfileDTO> companyProfileDTOList = manageFeatureEmployerProfile
				.getEmployerList(firstIndex, 2);
		Long companyProfileDTOListCount = manageFeatureEmployerProfile
				.getEmployerListCount();
		session.setAttribute(FEATURED_EMPS_COUNT, companyProfileDTOListCount);
		session.setAttribute("companyProfileDTOList", companyProfileDTOList);
		return jsonObject;
	}

	/**
	 * Get the homeFeaturedEmps page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/homeFeaturedEmps")
	public ModelAndView getjobboardsearchresultsBody(
			HttpServletResponse response, HttpServletRequest request,
			Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homeFeaturedEmps");
		return modelAndView;
	}

	/**
	 * Method helps to view the all career tools.
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewallcareertools")
	public ModelAndView viewallcareertools(HttpServletResponse response,
			HttpServletRequest request, Model model, HttpSession session) {
		if (new File(basedirectorypath + directory + careertoolfilename)
				.exists()) {
			try {
				if (new File(basedirectorypath + directory + careertoolfilename)
						.exists()) {
					String htmlcareercontent = ReadFile
							.htmlReader(basedirectorypath + directory
									+ careertoolfilename);
					Document doc = Jsoup.parse(htmlcareercontent);
					Elements link = doc.select("img");
					for (Element element : link) {
						String linkSrc = element.attr("src");
						element.attr("src", request.getContextPath()
								+ "/healthcare/viewImage.html?id="
								+ basedirectorypath + directory
								+ careerImagesDirectory + "/" + linkSrc);
					}
					model.addAttribute("careerstoolresource", doc);
				}

			} catch (IOException e) {
				LOGGER.error(
						"Error occurred while getting the html content for careertools page",
						e);
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewallcareertools");
		populateAds(request, session, model, PageNames.FEATURED_EMP);
		return modelAndView;
	}

	/**
	 * Navigate to site Map page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/sitemap")
	public ModelAndView siteMapPage(HttpServletResponse response,
			HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sitemap");
		populateAds(request, session, model, PageNames.JOBSEEKER_SITE_MAP);
		return modelAndView;
	}

	/**
	 * Method which will redirect the request to error page
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public String getErrorPageContents(HttpServletRequest request, Model model,
			HttpSession session) {
		model.addAttribute("isHomePage", true);
		return "defaultErrorPage";
	}

	/**
	 * The method helps to show the career part depending on career type
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/showCareersPart", method = RequestMethod.GET)
	public ModelAndView showCareersPart(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		// Get the career type to show
		String careersType = (String) request.getParameter("careerType");

		if (careersType.equalsIgnoreCase("careers")) {
			try {
				if (new File(basedirectorypath + directory + careertoolfilename)
						.exists()) {
					String htmlcareercontent = ReadFile
							.htmlReader(basedirectorypath + directory
									+ careertoolfilename);
					Document doc = Jsoup.parse(htmlcareercontent);
					Elements link = doc.select("img");
					for (Element element : link) {
						String linkSrc = element.attr("src");
						element.attr("src", request.getContextPath()
								+ "/healthcare/viewImage.html?id="
								+ basedirectorypath + directory
								+ careerImagesDirectory + linkSrc);
					}
					model.addAttribute("careerstoolresource", doc);
					session.setAttribute("careerstools", doc);
				}
				modelAndView.setViewName("jobboardCareerResource");
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		} else if (careersType.equalsIgnoreCase("resumeBuilder")) {
			try {

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			modelAndView.setViewName("jobBoardCareerAdvanceBuilder");
		} else if (careersType.equalsIgnoreCase("messanger")) {
			try {

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			modelAndView.setViewName("jobBoardCareerAdvanceMessenger");
		}

		return modelAndView;
	}

	/**
	 * The method helps to navigate job seeker dashboard page if he logged in
	 * other wise navigate to login page.
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/navigateJsDashboard", method = RequestMethod.GET)
	public ModelAndView navigateJobseekerDashboard(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for Login
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			modelAndView
					.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		} else {
			modelAndView
					.setViewName("redirect:/commonLogin/login.html?page=jobSeeker");
		}

		return modelAndView;
	}

	/**
	 * The method helps to navigate job seeker dashboard page if he logged in
	 * other wise navigate to login page.
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/showCareers", method = RequestMethod.GET)
	public ModelAndView showCareers(HttpServletRequest request, Model model,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		// Get the career type
		String careerType = request.getParameter("careerType");

		session.setAttribute("showCareersType", careerType);
		if (careerType.equalsIgnoreCase("career")) {
			modelAndView.setViewName("redirect:/healthcare/index.html");
		} else if (careerType.equalsIgnoreCase("messanger")) {
			modelAndView.setViewName("redirect:/healthcare/index.html");
		} else if (careerType.equalsIgnoreCase("resumeBuilder")) {
			modelAndView.setViewName("redirect:/healthcare/index.html");
		}

		return modelAndView;
	}

	/**
	 * This Method called to get the featured employer details by job Id from
	 * search grid
	 * 
	 * @param employerProfileManagementForm
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/featuredempJobDetails", method = RequestMethod.GET)
	public String featuredempJobDetails(
			EmployerProfileManagementForm employerProfileManagementForm,
			@RequestParam("id") int jobId, HttpServletRequest request,
			Model model, HttpSession session) {
		int facilityId = manageFeatureEmployerProfile.getFaciliyId(jobId);
		CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile
				.getEmployerDetails(facilityId);
		employerProfileManagementForm.setCompanyName(companyProfileDTO
				.getCompanyName());
		employerProfileManagementForm.setCompanyNews(companyProfileDTO
				.getCompanyNews());
		employerProfileManagementForm.setCompanyOverview(companyProfileDTO
				.getCompanyOverview());
		employerProfileManagementForm.setCompanyWebsite(companyProfileDTO
				.getCompanyWebsite());
		employerProfileManagementForm.setCompanyEmail(companyProfileDTO
				.getCompanyEmail());
		employerProfileManagementForm.setPositionTitle(companyProfileDTO
				.getPositionTitle());
		employerProfileManagementForm.setLogoPath(companyProfileDTO
				.getLogoPath());
		employerProfileManagementForm.setFacilityId(facilityId);
		if (null != companyProfileDTO.getPrimaryColor()) {
			employerProfileManagementForm.setPrimaryColor(companyProfileDTO
					.getPrimaryColor().replace("HEX #", "#"));
		}
		// TODO: Remove the hard codes used to check video : file server is not
		// fully maintained
		String path = request.getRequestURL().toString()
				.replace(request.getRequestURI(), MMJBCommonConstants.EMPTY);

		path = path + mediaPath + companyProfileDTO.getPositionalMedia();
		model.addAttribute("windowmediaplayerfilepath", path);
		model.addAttribute("employerProfileManagementForm",
				employerProfileManagementForm);
		model.addAttribute(
				"companyNameEncoded",
				MMUtils.encodeString(
						employerProfileManagementForm
								.getCompanyName()
								.trim()
								.replaceAll(
										MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
										"").trim()).toLowerCase());
		// populate the Ads
		populateAds(request, session, model, PageNames.FEATURED_EMP);
		return "featuredemployerdetails";
	}

}
