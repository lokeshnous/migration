package com.advanceweb.afc.jb.search.engine.solr;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.search.engine.solr.JobSearchDTO;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.engine.solr.ReadSolrServerDetails;
import com.advanceweb.afc.jb.search.engine.solr.SolrJobSearchResultDTO;

@Service("jobSearchDeleagate")
public class JobSearchDeleagateImpl implements JobSearchDeleagate {

	@Autowired
	ReadSolrServerDetails readSolrServerDetails;

	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties solrConfiguration;

	@PostConstruct
	public void init() {
	}

	@Override
	public JobSearchResultDTO jobSearch(String searchName,
			Map<String, String> paramMap, long rows, long start) {

		HttpSolrServer server = null;
		QueryResponse response = null;
		JobSearchResultDTO jobSearchResultDTO = null;

		SolrJobSearchResultDTO solrJobSearchResultDTO = null;
		JobSearchDTO jobSearchDTO = new JobSearchDTO();

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
				System.out.println("Server URL " + url + " is accessible.");
			}
		} catch (final MalformedURLException e) {
			serverAccessibility = false;
			System.out.println("e1==" + e);
			System.out.println("Server URL " + url + " is not accessible.");
		} catch (final IOException e) {
			System.out.println("e2==" + e);
			serverAccessibility = false;
			System.out.println("Server URL " + url + " is not accessible.");
		}

		if (serverAccessibility) {

			if (!"".equalsIgnoreCase(paramMap.get("titlesearch"))
					&& paramMap.get("titlesearch") != null) {

				server = new HttpSolrServer(serverDetailsMap.get("serverUrl")
						.toString()
						+ serverDetailsMap.get("solrservice").toString());
				server.setSoTimeout(Integer.parseInt(serverDetailsMap
						.get("sotimeout")));
				server.setConnectionTimeout(Integer.parseInt(serverDetailsMap
						.get("connectiontimeout")));
				server.setDefaultMaxConnectionsPerHost(Integer
						.parseInt(serverDetailsMap.get("maxconnectionperhost")));
				server.setMaxTotalConnections(Integer.parseInt(serverDetailsMap
						.get("maxtotalconnection")));

				server.setFollowRedirects(Boolean.parseBoolean(serverDetailsMap
						.get("followredirects"))); // defaults to false
				server.setAllowCompression(Boolean
						.parseBoolean(serverDetailsMap.get("allowcompression")));
				server.setMaxRetries(Integer.parseInt(serverDetailsMap
						.get("maxretries")));
				server.setParser(new XMLResponseParser());

				String searchString = paramMap.get("titlesearch");
				if (searchString != null && searchString.length() > 0) {

					SolrQuery searchquery = new SolrQuery();
					searchquery.setQuery(searchString);
					searchquery.setFacet(true);
					searchquery.addFacetField(solrQueryDetails.get("city"));
					searchquery.addFacetField(solrQueryDetails.get("company"));
					// searchquery.addFacetField(solrQueryDetails.get("radius"));
					searchquery
							.addFacetField(solrQueryDetails.get("posted_dt"));
					searchquery.addFacetField(solrQueryDetails.get("state"));
					searchquery.add("rows", String.valueOf(rows));
					searchquery.add("start", String.valueOf(start));
					System.out.println("Search query===>>>" + searchquery);

					try {
						response = server.query(searchquery);
					} catch (SolrServerException e1) {
						e1.printStackTrace();
					}
				}
				solrJobSearchResultDTO = new SolrJobSearchResultDTO();
				System.out.println("Number of search records===>>>"
						+ response.getResults().getNumFound());

				solrJobSearchResultDTO.setTotalNumSearchResult(response
						.getResults().getNumFound());

				List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
				jobSearchDTOList = response.getBeans(JobSearchDTO.class);

				Iterator<JobSearchDTO> itr = jobSearchDTOList.iterator();

				while (itr.hasNext()) {

					jobSearchDTO = new JobSearchDTO();
					jobSearchDTO = itr.next();
					System.out.println("@Company===>>"
							+ jobSearchDTO.getCompany());
					System.out.println("@Job Title===>>"
							+ jobSearchDTO.getJobTitle());
					System.out.println("@City===>>" + jobSearchDTO.getCity());
					System.out.println("@Posted Date===>>"
							+ jobSearchDTO.getPostedDate());
					System.out.println("@Apply Online===>>"
							+ jobSearchDTO.getApplyOnline());
					System.out.println("@Blind Ad===>>"
							+ jobSearchDTO.getBlindAd());
					System.out.println("@Facility Name===>>"
							+ jobSearchDTO.getFacilityName());
					System.out.println("@Email Display===>>"
							+ jobSearchDTO.getEmailDisplay());
					System.out.println("@Email===>>" + jobSearchDTO.getEmail());
					System.out.println("@Is Inter===>>"
							+ jobSearchDTO.isInternational());
					System.out.println("@Is National===>>"
							+ jobSearchDTO.isNational());
					System.out.println("@Is Featured===>>"
							+ jobSearchDTO.isFeatured());
					System.out.println("@Job count===>>"
							+ jobSearchDTO.getJobCount());
					System.out
							.println("@Job id===>>" + jobSearchDTO.getJobId());
					System.out.println("@Job Number===>>"
							+ jobSearchDTO.getJobNumber());
					System.out.println("@Job Geo===>>"
							+ jobSearchDTO.getJobGeo());
					System.out.println("@Job position===>>"
							+ jobSearchDTO.getJobPosition());
					System.out.println("@jobGeo0LatLon===>>"
							+ jobSearchDTO.getJobGeo0LatLon());
					System.out.println("@jobGeo1LatLon===>>"
							+ jobSearchDTO.getJobGeo1LatLon());
					System.out.println("@URL Display===>>"
							+ jobSearchDTO.getUrlDisplay());

				}

				Map<String, List<Count>> facetMap = new HashMap<String, List<Count>>();
				List<FacetField> facetFieldList = response.getFacetFields();

				for (FacetField facetField : facetFieldList) {
					facetMap.put(facetField.getName(), facetField.getValues());
					System.out.println("@Facet Name===>>"+ facetField.getName()+",@Facet Values(Categories)===>>>"
							+ facetMap.get(facetField.getName()));
				}

				solrJobSearchResultDTO.setFacetMap(facetMap);
				solrJobSearchResultDTO.setSearchResultList(jobSearchDTOList);

				jobSearchResultDTO = new JobSearchResultDTO();
				jobSearchResultDTO
						.setSolrJobSearchResultDTO(solrJobSearchResultDTO);

				return jobSearchResultDTO;
			} else {
				System.out
						.println("Empty Search criteria. Please enter a search criteria to seach jobs.");
				return null;
			}
		} else {
			System.out
					.println("Server url is not correct. Please check the url.");
			return null;
		}

	}

}
