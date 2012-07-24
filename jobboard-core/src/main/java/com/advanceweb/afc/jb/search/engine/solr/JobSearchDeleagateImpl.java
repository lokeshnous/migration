package com.advanceweb.afc.jb.search.engine.solr;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
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
	private static ReadSolrServerDetails readSSDetails;

	@Autowired
	@Resource(name = "solrConfiguration")
	private static Properties solrConfiguration;

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

		// Checking whether server url is accessible
		boolean serverAccessible = false;
		final String url = serverDetailsMap.get("serverUrl")
				+ serverDetailsMap.get("solrservice")
				+ serverDetailsMap.get("user");

		try {
			final HttpURLConnection connection = (HttpURLConnection) new URL(
					url).openConnection();
			connection.connect();

			if (connection.getResponseCode() == 200) {
				serverAccessible = true;
				LOGGER.debug("Server URL " + url + " is accessible.");
			}
		} catch (final MalformedURLException e) {
			serverAccessible = false;
			LOGGER.debug( e);
			LOGGER.debug("Server URL " + url + " is not accessible.");
		} catch (final IOException e) {
			LOGGER.debug( e);
			serverAccessible = false;
			LOGGER.debug("Server URL " + url + " is not accessible.");
		}

		if (serverAccessible) {

			if (("".equalsIgnoreCase(paramMap.get("keywords"))
					|| paramMap.get("keywords") == null) 
					&& ("".equalsIgnoreCase(paramMap.get("city_state"))
							|| paramMap.get("city_state") == null)
							&& ("".equalsIgnoreCase(paramMap.get("radius"))
									|| paramMap.get("radius") == null)) {

				LOGGER.debug("Empty Search criteria. Please enter a search criteria to seach jobs.");
				return null;

			} else {

				response = readSSDetails.getSolrResponse(
						serverDetailsMap, solrQueryDetails, paramMap, start,
						rows);

				solrJSResultDTO = new SolrJobSearchResultDTO();
				
				LOGGER.debug("Number of search records===>>>"
				 + response.getResults().getNumFound());

				solrJSResultDTO.setTotalNumSearchResult(response
						.getResults().getNumFound());

				List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
				jobSearchDTOList = response.getBeans(JobSearchDTO.class);

				// For displaying the results. Should be removed when UI will
				// come.
				//JSONObject jobSrchJsonObj = readSolrServerDetails.getJSONResult(jobSearchDTOList);

				final Map<String, List<Count>> facetMap = new HashMap<String, List<Count>>();
				final List<FacetField> facetFieldList = response.getFacetFields();

				for (FacetField facetField : facetFieldList) {
					facetMap.put(facetField.getName(), facetField.getValues());
					LOGGER.debug("@Facet Name===>>"+
					 facetField.getName()+",@Facet Values(Categories)===>>>"
					 + facetMap.get(facetField.getName()));
				}

				solrJSResultDTO.setFacetMap(facetMap);
				solrJSResultDTO.setSearchResultList(jobSearchDTOList);

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
