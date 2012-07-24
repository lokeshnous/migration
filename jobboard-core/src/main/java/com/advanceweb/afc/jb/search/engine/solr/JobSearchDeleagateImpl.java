package com.advanceweb.afc.jb.search.engine.solr;

import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class has been created for the Solr Server Job search functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 10 July 2012
 */

@Service("jobSearchDeleagate")
public class JobSearchDeleagateImpl implements JobSearchDeleagate {

	private static final Logger LOGGER = Logger
	.getLogger("JobSearchDeleagateImpl.class");

	@Autowired
	private ReadSolrServerDetails readSSDetails;

	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties solrConfiguration;
	
	@Autowired
	private SolrJobSearchResult solrJobSearchResult;

	@PostConstruct
	public void init() {
		// init() for loading the Properties file
	}

	/**
	 * Does the Solr Server Job Search
	 * 
	 * @param searchName
	 * @param paramMap
	 * @param rows
	 * @param start
	 * @return JobSearchResultDTO
	 */

	@Override
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> paramMap, final long start, final long rows) {

		QueryResponse response = null;
		JobSearchResultDTO jSResultDTO = null;
		SolrJobSearchResultDTO solrJSResultDTO = null;

		final Map<String, String> serverDetailsMap = readSSDetails
				.getServerDetails(solrConfiguration);
		final Map<String, String> solrQueryDetails = readSSDetails
				.getSolrQueryDetails(solrConfiguration);

		final String url = serverDetailsMap.get("serverUrl")
				+ serverDetailsMap.get("solrservice")
				+ serverDetailsMap.get("user");
		
		// Checking whether server url is accessible
		boolean serverAccessible = readSSDetails.isServerAccessible(url);
		if (serverAccessible) {

			if (("".equalsIgnoreCase(paramMap.get("keywords"))
					|| paramMap.get("keywords") == null) 
					&& ("".equalsIgnoreCase(paramMap.get("cityState"))
							|| paramMap.get("cityState") == null)
							&& ("".equalsIgnoreCase(paramMap.get("radius"))
									|| paramMap.get("radius") == null)) {

				LOGGER.debug("Empty Search criteria. Please enter a search criteria to seach jobs.");
				return null;

			} else {

				response = readSSDetails.getSolrResponse(
						serverDetailsMap, solrQueryDetails, paramMap, start,
						rows);
				solrJSResultDTO = solrJobSearchResult.getSolrJSResult(response);
				
				jSResultDTO = new JobSearchResultDTO();
				jSResultDTO
						.setSolrJobSearchResultDTO(solrJSResultDTO);

				return jSResultDTO;

			}

		} else {
			 LOGGER.debug("Server url is not correct. Please check the url.");
			return null;
		}

	}

}
