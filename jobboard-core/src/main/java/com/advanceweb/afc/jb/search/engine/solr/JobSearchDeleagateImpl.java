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

	// Logger log = Logger.getLogger("logfile");

	@Autowired
	private ReadSolrServerDetails readSolrServerDetails;

	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties solrConfiguration;

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
			final Map<String, String> paramMap, final long rows,
			final long start) {

		// log.info("");
		// HttpSolrServer server = null;
		QueryResponse response = null;
		JobSearchResultDTO jobSearchResultDTO = null;
		SolrJobSearchResultDTO solrJobSearchResultDTO = null;

		Map<String, String> serverDetailsMap = readSolrServerDetails
				.getServerDetails(solrConfiguration);
		Map<String, String> solrQueryDetails = readSolrServerDetails
				.getSolrQueryDetails(solrConfiguration);

		// Checking whether server url is accessible
		boolean serverAccessibility = false;
		String url = serverDetailsMap.get("serverUrl")
				+ serverDetailsMap.get("solrservice")
				+ serverDetailsMap.get("user");

		try {
			final HttpURLConnection connection = (HttpURLConnection) new URL(
					url).openConnection();
			connection.connect();

			if (connection.getResponseCode() == 200) {
				serverAccessibility = true;
				// System.out.println("Server URL " + url + " is accessible.");
			}
		} catch (final MalformedURLException e) {
			serverAccessibility = false;
			// System.out.println("e1==" + e);
			// System.out.println("Server URL " + url + " is not accessible.");
		} catch (final IOException e) {
			// System.out.println("e2==" + e);
			serverAccessibility = false;
			// System.out.println("Server URL " + url + " is not accessible.");
		}

		if (serverAccessibility) {

			if ("".equalsIgnoreCase(paramMap.get("titlesearch"))
					|| paramMap.get("titlesearch") == null) {

				// System.out
				// .println("Empty Search criteria. Please enter a search criteria to seach jobs.");
				return null;

			} else {

				response = readSolrServerDetails.getSolrResponse(
						serverDetailsMap, solrQueryDetails, paramMap, start,
						rows);

				solrJobSearchResultDTO = new SolrJobSearchResultDTO();
				// System.out.println("Number of search records===>>>"
				// + response.getResults().getNumFound());

				solrJobSearchResultDTO.setTotalNumSearchResult(response
						.getResults().getNumFound());

				List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
				jobSearchDTOList = response.getBeans(JobSearchDTO.class);

				// For displaying the results. Should be removed when UI will
				// come.
				readSolrServerDetails.displayResults(jobSearchDTOList);

				Map<String, List<Count>> facetMap = new HashMap<String, List<Count>>();
				List<FacetField> facetFieldList = response.getFacetFields();

				for (FacetField facetField : facetFieldList) {
					facetMap.put(facetField.getName(), facetField.getValues());
					// System.out.println("@Facet Name===>>"+
					// facetField.getName()+",@Facet Values(Categories)===>>>"
					// + facetMap.get(facetField.getName()));
				}

				solrJobSearchResultDTO.setFacetMap(facetMap);
				solrJobSearchResultDTO.setSearchResultList(jobSearchDTOList);

				jobSearchResultDTO = new JobSearchResultDTO();
				jobSearchResultDTO
						.setSolrJobSearchResultDTO(solrJobSearchResultDTO);

				return jobSearchResultDTO;

			}

		} else {
			// System.out
			// .println("Server url is not correct. Please check the url.");
			return null;
		}

	}

}
