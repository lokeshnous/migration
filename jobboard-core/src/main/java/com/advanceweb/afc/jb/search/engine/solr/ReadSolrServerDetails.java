package com.advanceweb.afc.jb.search.engine.solr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Repository;

@Repository("readSolrServerDetails")
public class ReadSolrServerDetails {

	/**
	 * Reads Solr Server details from the property file and put it into the Map
	 * 
	 * @param solrConfiguration
	 * @return Map
	 */
	public Map<String, String> getServerDetails(Properties solrConfiguration) {
		Map<String, String> serverDetailsMap = new HashMap<String, String>();
		serverDetailsMap.put("serverUrl", solrConfiguration.getProperty("url"));
		serverDetailsMap.put("solrservice",
				solrConfiguration.getProperty("solrservice"));
		serverDetailsMap.put("user", solrConfiguration.getProperty("user"));
		serverDetailsMap.put("sotimeout",
				solrConfiguration.getProperty("sotimeout"));
		serverDetailsMap.put("connectiontimeout",
				solrConfiguration.getProperty("connectiontimeout"));
		serverDetailsMap.put("maxconnectionperhost",
				solrConfiguration.getProperty("maxconnectionperhost"));
		serverDetailsMap.put("maxtotalconnection",
				solrConfiguration.getProperty("maxtotalconnection"));
		serverDetailsMap.put("followredirects",
				solrConfiguration.getProperty("followredirects"));
		serverDetailsMap.put("allowcompression",
				solrConfiguration.getProperty("allowcompression"));
		serverDetailsMap.put("maxretries",
				solrConfiguration.getProperty("maxretries"));
		return serverDetailsMap;
	}

	/**
	 * Reads Solr query details from the property file and put it into a Map
	 * 
	 * @param solrConfiguration
	 * @return Map
	 */
	public Map<String, String> getSolrQueryDetails(Properties solrConfiguration) {
		Map<String, String> solrQueryDetails = new HashMap<String, String>();
		solrQueryDetails.put("city", solrConfiguration.getProperty("city"));
		solrQueryDetails.put("company",
				solrConfiguration.getProperty("company"));
		solrQueryDetails.put("radius", solrConfiguration.getProperty("radius"));
		solrQueryDetails.put("posted_dt",
				solrConfiguration.getProperty("posted_dt"));
		solrQueryDetails.put("state", solrConfiguration.getProperty("state"));
		solrQueryDetails.put("rows", solrConfiguration.getProperty("rows"));
		solrQueryDetails.put("start", solrConfiguration.getProperty("start"));

		return solrQueryDetails;
	}

	public QueryResponse getSolrResponse(Map<String, String> serverDetailsMap,
			Map<String, String> solrQueryDetails, Map<String, String> paramMap,
			long start, long rows) {
		QueryResponse response = null;
		HttpSolrServer server = null;
		server = new HttpSolrServer(serverDetailsMap.get("serverUrl")
				.toString() + serverDetailsMap.get("solrservice").toString());
		server.setSoTimeout(Integer.parseInt(serverDetailsMap.get("sotimeout")));
		server.setConnectionTimeout(Integer.parseInt(serverDetailsMap
				.get("connectiontimeout")));
		server.setDefaultMaxConnectionsPerHost(Integer
				.parseInt(serverDetailsMap.get("maxconnectionperhost")));
		server.setMaxTotalConnections(Integer.parseInt(serverDetailsMap
				.get("maxtotalconnection")));

		server.setFollowRedirects(Boolean.parseBoolean(serverDetailsMap
				.get("followredirects"))); // defaults to false
		server.setAllowCompression(Boolean.parseBoolean(serverDetailsMap
				.get("allowcompression")));
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
			searchquery.addFacetField(solrQueryDetails.get("posted_dt"));
			searchquery.addFacetField(solrQueryDetails.get("state"));
			searchquery.add("rows", String.valueOf(rows));
			searchquery.add("start", String.valueOf(start));
			//System.out.println("Search query===>>>" + searchquery);

			try {
				response = server.query(searchquery);
			} catch (SolrServerException e1) {
				//e1.printStackTrace();
				return null;
			}
		}

		return response;
	}

	public void displayResults(List<JobSearchDTO> jobSearchDTOList) {

		/*for (JobSearchDTO jobSrchDTO : jobSearchDTOList) {
			System.out.println("@Company===>>" + jobSrchDTO.getCompany());
			System.out.println("@Job Title===>>" + jobSrchDTO.getJobTitle());
			System.out.println("@City===>>" + jobSrchDTO.getCity());
			System.out
					.println("@Posted Date===>>" + jobSrchDTO.getPostedDate());
			System.out.println("@Apply Online===>>"
					+ jobSrchDTO.getApplyOnline());
			System.out.println("@Blind Ad===>>" + jobSrchDTO.getBlindAd());
			System.out.println("@Facility Name===>>"
					+ jobSrchDTO.getFacilityName());
			System.out.println("@Email Display===>>"
					+ jobSrchDTO.getEmailDisplay());
			System.out.println("@Email===>>" + jobSrchDTO.getEmail());
			System.out.println("@Is Inter===>>"
					+ jobSrchDTO.isInternationalJob());
			System.out.println("@Is National===>>"
					+ jobSrchDTO.isNationalJob());
			System.out.println("@Is Featured===>>" + jobSrchDTO.isFeatured());
			System.out.println("@Job count===>>" + jobSrchDTO.getJobCount());
			System.out.println("@Job id===>>" + jobSrchDTO.getJobId());
			System.out
					.println("@Job Number===>>" + jobSrchDTO.getJobNumber());
			System.out.println("@Job Geo===>>" + jobSrchDTO.getJobGeo());
			System.out.println("@Job position===>>"
					+ jobSrchDTO.getJobPosition());
			System.out.println("@jobGeo0LatLon===>>"
					+ jobSrchDTO.getJobGeo0LatLon());
			System.out.println("@jobGeo1LatLon===>>"
					+ jobSrchDTO.getJobGeo1LatLon());
			System.out.println("@URL Display===>>"
					+ jobSrchDTO.getUrlDisplay());
		}*/

	}

}
