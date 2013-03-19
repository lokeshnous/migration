package com.advanceweb.afc.jb.job.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobTitleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.common.util.XmlUrl;
import com.advanceweb.afc.jb.common.util.XmlUrlSet;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchFacetDTO;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;

@Controller
@RequestMapping("/web")
public class SiteMapController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(SiteMapController.class);

	/** The job search service. */
	@Autowired
	private JobSearchService jobSearchService;

	/** The job countparam map. */
	private static Map<String, String> jobCountparamMap = new HashMap<String, String>();

	/** The lookup service. */
	@Autowired
	private LookupService lookupService;

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/**
	 * The method helps to create sitemap.xml file by adding the site home page, job title category pages
	 * ,employer category pages, locations category pages,location region category pages and all
	 * job view pages URL. Which will be given to google sitemap generator tool to submit sitemap.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
	@ResponseBody
	public void main(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		try {
		 out = response.getWriter();
		 StringBuffer sb=new StringBuffer();
		 
		 JAXBContext jc;
		 StringWriter writer = new StringWriter();
		try {
		jc = JAXBContext.newInstance(XmlUrlSet.class);
		XmlUrlSet xmlUrlSet = new XmlUrlSet();

		// add the site home page URL
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/healthcare/index.html", XmlUrl.Priority.HIGH));
		
		// add the job title category page URL
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/title.html", XmlUrl.Priority.HIGH));
				
		// add the employer category page URL
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/employer.html", XmlUrl.Priority.HIGH));
				
		// add the location category page URL
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/location.html", XmlUrl.Priority.HIGH));

		// Add the category pages URL
		browseJobsByCategory(xmlUrlSet, request);

		// Add the all job pages URL
		JobSearchResultDTO jobSearchResultDTO = null;

		// merge the parameters
		int searchSeq = MMJBCommonConstants.ZERO_INT;
		String sessionId = "";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(SearchParamDTO.KEYWORDS, "*");
		paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
		paramMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(searchSeq + 1));
		paramMap.put(SearchParamDTO.SEARCH_NAME,
				MMJBCommonConstants.KEYWORD_SEARCH.trim());
		paramMap.put(MMJBCommonConstants.SORT_PARAM,
				MMJBCommonConstants.POSTED_DT);
		paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.SORT_ORDER,
				MMJBCommonConstants.DESC_STR);
		paramMap.put(MMJBCommonConstants.FACET_SORT,
				MMJBCommonConstants.INDEX_STR);
		try {
			int totalJobs = Integer.parseInt(activeJobs().replace(",", ""));
			int divider = (int) Math.ceil((double) totalJobs / 3);
			for (int i = 1; i <= 3; i++) {
				long start = (i - 1) * divider;
				long rows = divider;
				List<JobDTO> jobDTOList = null;
				String title = null;
				String jobId = null;
				try {
					jobSearchResultDTO = jobSearchService.jobSearch(paramMap,
							start, rows);
					jobDTOList = jobSearchResultDTO.getResultList();
				} catch (JobBoardException je) {
					LOGGER.error(je.getMessage(), je);
				} catch (Exception e) {
					LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
				}
				if (jobDTOList != null) {
					for (JobDTO jobDTO : jobDTOList) {
						title = MMUtils.isNull(jobDTO.getJobTitle()).trim();
						jobId = MMUtils
								.isNull(String.valueOf(jobDTO.getJobId()));
						if (!title.isEmpty()) {
							title = title
									.replaceAll(
											MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
											"").toLowerCase().replace(" ", "-");
						}
						addJobViewURL(xmlUrlSet, jobId + "/" + title + ".html",
								XmlUrl.Priority.HIGH, request);
					}
				}
			}
			// Add the 
			writer = new StringWriter();
			Marshaller m = jc.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");
	        m.marshal(xmlUrlSet, writer);
	        
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		} catch (JAXBException jaxbe) {
			LOGGER.error(jaxbe.getMessage(), jaxbe);
		}
		sb.append(writer.toString()); 
		out.println(sb.toString());
		out.flush();
		} catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}

	}
	
	/**
	 * The method helps to add the jobtitle, employer and locations category pages URL to sitemap xml file.
	 * 
	 * @param xmlUrlSet
	 * @param request
	 */
	private void browseJobsByCategory(XmlUrlSet xmlUrlSet,
			HttpServletRequest request) {
		// Add job titles URLs
		try {
			List<JobTitleDTO> positionList = jobSearchService.getJobTitleList();
			String jobTitle;
			// set the employers list in dictionary format
			for (JobTitleDTO titleDTO : positionList) {
				if (titleDTO != null
						&& !titleDTO.getJobtitle().trim().isEmpty()) {
					jobTitle = titleDTO.getJobtitle().trim();
					addJobTitleURL(xmlUrlSet, MMUtils.encodeString(jobTitle)
							.toLowerCase() + ".html", XmlUrl.Priority.HIGH,
							request);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		// Add the Employer URL 
		JobSearchResultDTO jobSearchResultDTO = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		String sessionId = "";
		paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
		paramMap.put(MMJBCommonConstants.SEARCH_TYPE,
				MMJBCommonConstants.BASIC_SEARCH_TYPE);
		paramMap.put(MMJBCommonConstants.FacilityId_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FacilityId_NAME_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		// set the sort order for search results
		paramMap.put(MMJBCommonConstants.SORT_ORDER,
				MMJBCommonConstants.DESC_STR);

		paramMap.put(SearchParamDTO.KEYWORDS, MMJBCommonConstants.EMPTY);
		paramMap.put(SearchParamDTO.CITY_STATE, MMJBCommonConstants.EMPTY);
		paramMap.put(SearchParamDTO.RADIUS, MMJBCommonConstants.EMPTY);
		paramMap.put(SearchParamDTO.REFINED, String.valueOf(false));
		paramMap.put(SearchParamDTO.SEARCH_NAME, MMJBCommonConstants.BROWSE_SEARCH.trim());
		paramMap.put(MMJBCommonConstants.SORT_PARAM,
				MMJBCommonConstants.POSTED_DT);
		paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
				MMJBCommonConstants.EMPTY);
		paramMap.put(MMJBCommonConstants.FACET_SORT,
				MMJBCommonConstants.INDEX_STR);
		paramMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(0));
		int page = 1;
		int recordsPerPage = 0;

		try {
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			List<String> employerList = (List<String>) generateRefineResults(jobSearchResultDTO.getFacetMap()
					.get(SearchFacetDTO.FACET_COMPANY_ID_NAME));
		try {
			Map<String, List<String>> emplyrsByName = new TreeMap<String, List<String>>();
			Set<String> nameList = new HashSet<String>();
			int totalEmployers = 0;
			// set the employers list in dictionary format
			String emplyrName = null;
			for (String job : employerList) {
				emplyrName = job.substring(job.indexOf("_") + 1);
				if (emplyrName != null
						&& !emplyrName.split("\\(")[0].trim().isEmpty()) {
					String nameLetter = emplyrName.substring(0, 1)
							.toUpperCase();
					if (nameList.add(nameLetter)) {
						List<String> jobList = new ArrayList<String>();
						jobList.add(job);
						emplyrsByName.put(nameLetter, jobList);
					} else {
						emplyrsByName.get(nameLetter).add(job);
					}
					totalEmployers++;
				}
			}
			int totalKeyCount = emplyrsByName.keySet().size();
			int rowsCount = (int) Math
					.ceil((double) (totalEmployers + totalKeyCount) / 3);
			Iterator<Entry<String, List<String>>> keyIt = emplyrsByName
					.entrySet().iterator();
			int i = 0, j = 1;
			while (keyIt.hasNext()) {
				Entry<String, List<String>> entry = (Entry<String, List<String>>) keyIt
						.next();
				if (!(i < (rowsCount * j))) {
					if (j == 1) {
						rowsCount = rowsCount + (i - rowsCount);
					}
					j++;
				}
				List<String> empNames = entry.getValue();
				int empId = 0;
				for (String empName : empNames) {
					try {
						empId = Integer.parseInt(empName.substring(0,
								empName.indexOf("_")));
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					emplyrName = empName.substring(empName.indexOf("_") + 1);
					empName = emplyrName.substring(0,
							emplyrName.lastIndexOf("(")).trim();
					addEmployerURL(
							xmlUrlSet,
							empId,
							MMUtils.encodeString(
									empName.trim()
											.replaceAll(
													MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
													"")).toLowerCase()
									+ ".html", XmlUrl.Priority.HIGH, request);
				}
				i++;
				i = entry.getValue().size() + i;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		// Add the Locations URL 
		try {
			List<String> jobsStatesList = (List<String>) generateRefineResults(jobSearchResultDTO.getFacetMap()
					.get(SearchFacetDTO.FACET_STATE));
			String stateFullName;
			String locationURL;
			for (String state : jobsStatesList) {
				state = state.substring(0, state.lastIndexOf("(")).trim();
				// Add the state full name by DB
				stateFullName = lookupService.getStateFullName(state);
				locationURL = MMUtils.encodeString(stateFullName) + "-" + state;
				addLocationURL(xmlUrlSet, state.toLowerCase() + ".html",
						XmlUrl.Priority.HIGH, request);
				addLocationRegionListURL(xmlUrlSet, locationURL.toLowerCase() + ".html",
						XmlUrl.Priority.HIGH, request);
				browseJobsBySubCategory(xmlUrlSet, request, locationURL.toLowerCase(), paramMap);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		}
	}

	/**
	 * The method helps to add the location region category pages URL to sitemap xml file.
	 * 
	 * @param xmlUrlSet
	 * @param request
	 * @param location
	 * @param paramMap 
	 */
	private void browseJobsBySubCategory(XmlUrlSet xmlUrlSet,
			HttpServletRequest request, String location, Map<String, String> paramMap) {

		JobSearchResultDTO jobSearchResultDTO = null;

		// set the FQ parameters
		String state = MMUtils.decodeString(location.split("-")[location
				.split("-").length - 1].trim());
		paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
				MMJBCommonConstants.FQ_STATE_LOWER_CASE + state + '"');
		int page = 1;
		int recordsPerPage = 0;

		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			// set the max areas count
			recordsPerPage = MMJBCommonConstants.JOB_SEARCH_MAX_AREAS;
			long start = (page - 1) * recordsPerPage;
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(paramMap, start,
					rows);

		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		if (jobSearchResultDTO != null) {
			// get the areas list
			List<String> areaList = generateRegionResults(jobSearchResultDTO
					.getFacetMap().get(SearchFacetDTO.FACET_AREA));
			for (String area : areaList) {
				area = area.replace(MMJBCommonConstants.METRO_AREA, "").trim()
						.toLowerCase();
				addLocationRegionURL(xmlUrlSet, state.toLowerCase(), MMUtils.encodeString(area),
						XmlUrl.Priority.HIGH, request);
			}
		}

	}

	/**
	 * This is used to get the Region results.
	 * 
	 * @param facetList
	 * @return List<String>
	 */

	private List<String> generateRegionResults(List<SearchFacetDTO> facetList) {
		List<String> displayFacetList = new ArrayList<String>();
		String displayFacet = MMJBCommonConstants.EMPTY;
		if (null != facetList) {
			for (SearchFacetDTO dto : facetList) {
				if (null != dto
						&& MMJBCommonConstants.ZERO_INT != dto.getCount()) {
					displayFacet = dto.getFacetValue();
					displayFacet = displayFacet.concat(SPACE);
					displayFacet = displayFacet
							.concat(MMJBCommonConstants.METRO_AREA);
					displayFacetList.add(displayFacet);
				}
			}
		}
		return displayFacetList;
	}

	/**
	 * This method provides the facetList along with the count of jobs in each
	 * facet
	 * 
	 * @param facetList
	 * @return displayFacetList
	 */
	private List<String> generateRefineResults(List<SearchFacetDTO> facetList) {
		List<String> displayFacetList = new ArrayList<String>();
		String displayFacet = MMJBCommonConstants.EMPTY;
		if (null != facetList) {
			for (SearchFacetDTO dto : facetList) {
				if (null != dto
						&& MMJBCommonConstants.ZERO_INT != dto.getCount()) {
					displayFacet = dto.getFacetValue();
					displayFacet = displayFacet
							.concat(MMJBCommonConstants.SPACE_OPN_BRCKT);
					displayFacet = displayFacet.concat(String.valueOf(dto
							.getCount()));
					displayFacet = displayFacet
							.concat(MMJBCommonConstants.CLSG_BRCKT);
					displayFacetList.add(displayFacet);
				}
			}
		}
		return displayFacetList;
	}

	/**
	 * The method helps to create the links for job view page
	 * 
	 * @param xmlUrlSet
	 * @param link
	 * @param priority
	 * @param request
	 */
	private void addJobViewURL(XmlUrlSet xmlUrlSet, String link,
			XmlUrl.Priority priority, HttpServletRequest request) {
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/jobview/" + link, priority));
	}

	/**
	 * The method helps to create the links for individual job title page
	 * 
	 * @param xmlUrlSet
	 * @param link
	 * @param priority
	 * @param request
	 */
	private void addJobTitleURL(XmlUrlSet xmlUrlSet, String link,
			XmlUrl.Priority priority, HttpServletRequest request) {
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/title/" + link, priority));
	}

	/**
	 * The method helps to create the links for individual employer page
	 * 
	 * @param xmlUrlSet
	 * @param facilityId
	 * @param link
	 * @param priority
	 * @param request
	 */
	private void addEmployerURL(XmlUrlSet xmlUrlSet, int facilityId,
			String link, XmlUrl.Priority priority, HttpServletRequest request) {
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/employer/" + facilityId + "/" + link, priority));
	}

	/**
	 * The method helps to create the links for individual location page
	 * 
	 * @param xmlUrlSet
	 * @param link
	 * @param priority
	 * @param request
	 */
	private void addLocationURL(XmlUrlSet xmlUrlSet, String link,
			XmlUrl.Priority priority, HttpServletRequest request) {
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/location/" + link, priority));
	}
	
	/**
	 * The method helps to create the links for location regions list page
	 * 
	 * @param xmlUrlSet
	 * @param link
	 * @param priority
	 * @param request
	 */
	private void addLocationRegionListURL(XmlUrlSet xmlUrlSet, String link,
			XmlUrl.Priority priority, HttpServletRequest request) {
		xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")
				+ "/search/location/" + link , priority));
	}

	/**
	 * The method helps to create the links for individual location region page
	 * 
	 * @param xmlUrlSet
	 * @param state
	 * @param area
	 * @param priority
	 * @param request
	 */
	private void addLocationRegionURL(XmlUrlSet xmlUrlSet, String state,
			String area, XmlUrl.Priority priority, HttpServletRequest request) {
		xmlUrlSet
				.addUrl(new XmlUrl(request.getRequestURL().toString()
						.replace(request.getServletPath(), "")
						+ "/search/location/" + state + "/" + area + ".html",
						priority));
	}

	/**
	 * The method help to count the active jobs.
	 * 
	 * @return
	 */
	public String activeJobs() {
		long totalNoOfActiveJobs = 0;
		try {
			JobSearchResultDTO jobSearchResultDTO = null;
			// merge the parameters
			jobCountparamMap.put(SearchParamDTO.KEYWORDS,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.CITY_STATE,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.RADIUS,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.REFINED, String.valueOf(false));
			jobCountparamMap.put(SearchParamDTO.SESSION_ID,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(SearchParamDTO.SEARCH_NAME,
					MMJBCommonConstants.BROWSE_SEARCH);
			jobCountparamMap.put(MMJBCommonConstants.SEARCH_TYPE,
					MMJBCommonConstants.BASIC_SEARCH_TYPE);
			jobCountparamMap.put(MMJBCommonConstants.SORT_PARAM,
					MMJBCommonConstants.POSTED_DT);
			jobCountparamMap.put(MMJBCommonConstants.FIRST_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.SECOND_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.THIRD_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FIFTH_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FacilityId_NAME_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.FacilityId_FQ_PARAM,
					MMJBCommonConstants.EMPTY);
			jobCountparamMap.put(MMJBCommonConstants.SORT_ORDER,
					MMJBCommonConstants.DESC_STR);
			jobCountparamMap.put(MMJBCommonConstants.FACET_SORT,
					MMJBCommonConstants.INDEX_STR);
			jobCountparamMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(0));
			try {
				jobSearchResultDTO = jobSearchService.jobSearch(
						jobCountparamMap, 0, 0);

			} catch (JobBoardException e) {
				LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
			}
			if (jobSearchResultDTO != null) {
				totalNoOfActiveJobs = (int) jobSearchResultDTO.getResultCount();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return String.valueOf(NumberFormat.getInstance().format(
				totalNoOfActiveJobs));
	}
}
