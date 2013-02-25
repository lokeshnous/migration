package com.advanceweb.afc.jb.job.web.controller;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.common.util.XmlUrl;
import com.advanceweb.afc.jb.common.util.XmlUrlSet;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
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

	/**
	 * The method helps to create sitemap.xml file.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main(HttpServletRequest request) {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        
        // for loop to generate all the links by querying against database
        JobSearchResultDTO jobSearchResultDTO = null;
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();

		// Set the search type for SOLR facets
		String searchName = MMJBCommonConstants.KEYWORD_SEARCH;
		jobSearchResultForm.setSearchName(searchName);
		jobSearchResultForm.setSearchtype(MMJBCommonConstants.BASIC_SEARCH_TYPE);

		// merge the parameters
		int searchSeq = MMJBCommonConstants.ZERO_INT;
		String sessionId = "";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(SearchParamDTO.KEYWORDS, "*");
		paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
		paramMap.put(SearchParamDTO.SEARCH_SEQ,
				String.valueOf(searchSeq + 1));
		paramMap.put(SearchParamDTO.SEARCH_NAME, searchName.trim());

		// For testing. Remove it while committing
		paramMap.put(MMJBCommonConstants.SORT_PARAM, MMJBCommonConstants.POSTED_DT);
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
			int divider = (int) Math.ceil((double)totalJobs/20);
			for(int i = 1 ; i<=20 ; i++){
				long start = (i - 1) * divider;
				long rows = divider;
				List<JobDTO> jobDTOList = null;
				String title = null;
				String jobId = null;
				try {
					jobSearchResultDTO = jobSearchService.jobSearch(
							paramMap, start, rows);
					jobDTOList = jobSearchResultDTO.getResultList();
				} catch (JobBoardException je) {
					LOGGER.error(je.getMessage(), je);
				}catch (Exception e) {
					LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
				}
				if(jobDTOList != null){
				for (JobDTO jobDTO : jobDTOList) {
					title = MMUtils.isNull(jobDTO.getJobTitle()).trim();
					jobId = MMUtils.isNull(String.valueOf(jobDTO.getJobId()));
					if (!title.isEmpty()) {
						title = title
								.replaceAll(
										MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,
										"").toLowerCase().replace(" ", "-");
					} 
					 create(xmlUrlSet, jobId+"/"+title+".html", XmlUrl.Priority.HIGH, request);
				}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

        return xmlUrlSet;
    }

	/**
	 * The method helps to create the links for sitemap 
	 * 
	 * @param xmlUrlSet
	 * @param link
	 * @param priority
	 * @param request
	 */
    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority,HttpServletRequest request) {
        xmlUrlSet.addUrl(new XmlUrl(request.getRequestURL().toString()
				.replace(request.getServletPath(), "")+"/search/jobview/" + link, priority));
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
		return String.valueOf(NumberFormat.getInstance().format(totalNoOfActiveJobs));
	}
    
    /*@RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
    public String getRobots(HttpServletRequest request) {
        return (Arrays.asList("mysite.com", "www.mysite.com").contains(request.getServerName())) ?
                "robotsAllowed" : "robotsDisallowed";
    }*/
	
}
