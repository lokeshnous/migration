package com.advanceweb.afc.jb.search.engine.solr;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Repository;



/**
 * This class has been created as a helper class for the Solr related Job search
 * functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 10 July 2012
 */ 

@Repository("readSolrServerDetails")
public class ReadSolrServerDetails {
  
	private static final Logger LOGGER = Logger
			.getLogger("ReadSolrServerDetails.class");
	
	/**
	 * Reads Solr Server details from the property file and put it into the Map
	 * 
	 * @param solrConfiguration
	 * @return Map
	 */
	public Map<String, String> getServerDetails(final Properties solrConfiguration) {
		final Map<String, String> serverDetailsMap = new HashMap<String, String>();
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
	public Map<String, String> getSolrQueryDetails(final Properties solrConfiguration) {
		final Map<String, String> solrQueryDetails = new HashMap<String, String>();
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

	/**
	 * This method will be used to set the server details to HttpSolrServer
	 * object, query the query string and get the QueryResponse from the Solr
	 * server
	 * 
	 * @param serverDetailsMap
	 * @param solrQueryDetails
	 * @param paramMap
	 * @param start
	 * @param rows
	 * @return QueryResponse
	 */
	public QueryResponse getSolrResponse(final Map<String, String> serverDetailsMap,
			final Map<String, String> solrQueryDetails, final Map<String, String> paramMap,
			final long start, final long rows) {
		QueryResponse response = null;
		HttpSolrServer server = null;
		//boolean isSuccessful = true;
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

		final String searchString = paramMap.get("keywords") + "+"
				+ paramMap.get("city_state") + "+" + paramMap.get("radius");
		if (searchString != null && searchString.length() > 0) {

			final SolrQuery searchquery = new SolrQuery();
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
				LOGGER.debug(e1);
				// e1.printStackTrace();
				//isSuccessful = false;
			}
		}

		//if(isSuccessful){
			return response;
		/*}else{
			return null;
		}*/
	}

	/*public JSONObject getJSONResultTest(List<JobSearchDTO> jobSearchDTOList) {

		JSONObject jobSrchJsonObj = new JSONObject();
		JSONArray jsonRows = new JSONArray();
		
		for (JobSearchDTO jobSrchDTO : jobSearchDTOList) {
			
			JSONObject jobSrchJson = new JSONObject();
			
			System.out.println("@Company===>>" + jobSrchDTO.getCompany());
			
			jobSrchJson.put("Company", jobSrchDTO.getCompany());
			
			
			System.out.println("@JobTitle===>>" + jobSrchDTO.getJobTitle());
			jobSrchJson.put("JobTitle", jobSrchDTO.getJobTitle());
			
			System.out.println("@City===>>" + jobSrchDTO.getCity());
			jobSrchJson.put("City", jobSrchDTO.getCity());
			
			System.out.println("@PostedDate===>>" + jobSrchDTO.getPostedDate());
			jobSrchJson.put("PostedDate", jobSrchDTO.getPostedDate());
			
			System.out.println("@Apply Online===>>"
					+ jobSrchDTO.getApplyOnline());
			jobSrchJson.put("ApplyOnline", jobSrchDTO.getApplyOnline());
			
			System.out.println("@Blind Ad===>>" + jobSrchDTO.getBlindAd());
			jobSrchJson.put("BlindAd", jobSrchDTO.getBlindAd());
			
			System.out.println("@Facility Name===>>"
					+ jobSrchDTO.getFacilityName());
			jobSrchJson.put("FacilityName", jobSrchDTO.getFacilityName());
			
			System.out.println("@Email Display===>>"
					+ jobSrchDTO.getEmailDisplay());
			jobSrchJson.put("EmailDisplay", jobSrchDTO.getEmailDisplay());
			
			System.out.println("@Email===>>" + jobSrchDTO.getEmail());
			jobSrchJson.put("Email", jobSrchDTO.getEmail());
			
			System.out.println("@Is Inter===>>"
					+ jobSrchDTO.isInternationalJob());
			jobSrchJson.put("IsInternational", jobSrchDTO.isInternationalJob());
			
			System.out.println("@Is National===>>" + jobSrchDTO.isNationalJob());
			jobSrchJson.put("IsNational", jobSrchDTO.isNationalJob());
			
			System.out.println("@Is Featured===>>" + jobSrchDTO.isFeatured());
			jobSrchJson.put("IsFeatured", jobSrchDTO.isFeatured());
			
			System.out.println("@Job count===>>" + jobSrchDTO.getJobCount());
			jobSrchJson.put("JobCount", jobSrchDTO.getJobCount());
			
			System.out.println("@Job id===>>" + jobSrchDTO.getJobId());
			jobSrchJson.put("JobId", jobSrchDTO.getJobId());
			
			System.out.println("@Job Number===>>" + jobSrchDTO.getJobNumber());
			jobSrchJson.put("Job Number", jobSrchDTO.getJobNumber());
			
			System.out.println("@Job Geo===>>" + jobSrchDTO.getJobGeo());
			jobSrchJson.put("Job Geo", jobSrchDTO.getJobGeo());
			
			System.out.println("@Job position===>>"
					+ jobSrchDTO.getJobPosition());
			jobSrchJson.put("JobPosition", jobSrchDTO.getJobPosition());
			
			System.out.println("@jobGeo0LatLon===>>"
					+ jobSrchDTO.getJobGeo0LatLon());
			jobSrchJson.put("jobGeo0LatLon", jobSrchDTO.getJobGeo0LatLon());
			
			System.out.println("@jobGeo1LatLon===>>"
					+ jobSrchDTO.getJobGeo1LatLon());
			jobSrchJson.put("jobGeo1LatLon", jobSrchDTO.getJobGeo1LatLon());
			LOGGER.info("@URL Display===>>" + jobSrchDTO.getUrlDisplay());
			jobSrchJson.put("URLDisplay", jobSrchDTO.getUrlDisplay());
			
			jsonRows.add(jobSrchJson);
		}
		
		jobSrchJsonObj.put("jsonRows",jsonRows );

		
		return jobSrchJsonObj;
	}*/
	
	
	public JSONObject convertToJSON(final JobSearchResultDTO jSResultDTO) {
		final JSONObject jobSrchJsonObj = new JSONObject();
		final JSONArray jsonRows = new JSONArray();
		
		final SolrJobSearchResultDTO solrJSResultDTO = jSResultDTO.getSolrJobSearchResultDTO();
		final List<JobSearchDTO> jobSearchDTOList = solrJSResultDTO.getSearchResultList();
		
		for (JobSearchDTO jobSrchDTO : jobSearchDTOList) {
			
			JSONObject jobSrchJson = new JSONObject();
			
			//System.out.println("@Company===>>" + jobSrchDTO.getCompany());
			
			jobSrchJson.put("Company", jobSrchDTO.getCompany());
			
			
			//System.out.println("@JobTitle===>>" + jobSrchDTO.getJobTitle());
			jobSrchJson.put("JobTitle", jobSrchDTO.getJobTitle());
			
			//System.out.println("@City===>>" + jobSrchDTO.getCity());
			jobSrchJson.put("City", jobSrchDTO.getCity());
			
			//System.out.println("@PostedDate===>>" + jobSrchDTO.getPostedDate());
			jobSrchJson.put("PostedDate", jobSrchDTO.getPostedDate());
			
			//System.out.println("@Apply Online===>>"
					//+ jobSrchDTO.getApplyOnline());
			jobSrchJson.put("ApplyOnline", jobSrchDTO.getApplyOnline());
			
			//System.out.println("@Blind Ad===>>" + jobSrchDTO.getBlindAd());
			jobSrchJson.put("BlindAd", jobSrchDTO.getBlindAd());
			
			//System.out.println("@Facility Name===>>"
				//	+ jobSrchDTO.getFacilityName());
			jobSrchJson.put("FacilityName", jobSrchDTO.getFacilityName());
			
			//System.out.println("@Email Display===>>"
					//+ jobSrchDTO.getEmailDisplay());
			jobSrchJson.put("EmailDisplay", jobSrchDTO.getEmailDisplay());
			
			//System.out.println("@Email===>>" + jobSrchDTO.getEmail());
			jobSrchJson.put("Email", jobSrchDTO.getEmail());
			
			//System.out.println("@Is Inter===>>"
			//		+ jobSrchDTO.isInternationalJob());
			jobSrchJson.put("IsInternational", jobSrchDTO.isInternationalJob());
			
			//System.out.println("@Is National===>>" + jobSrchDTO.isNationalJob());
			jobSrchJson.put("IsNational", jobSrchDTO.isNationalJob());
			
			//System.out.println("@Is Featured===>>" + jobSrchDTO.isFeatured());
			jobSrchJson.put("IsFeatured", jobSrchDTO.isFeatured());
			
			//System.out.println("@Job count===>>" + jobSrchDTO.getJobCount());
			jobSrchJson.put("JobCount", jobSrchDTO.getJobCount());
			
			//System.out.println("@Job id===>>" + jobSrchDTO.getJobId());
			jobSrchJson.put("JobId", jobSrchDTO.getJobId());
			
			//System.out.println("@Job Number===>>" + jobSrchDTO.getJobNumber());
			jobSrchJson.put("Job Number", jobSrchDTO.getJobNumber());
			
			//System.out.println("@Job Geo===>>" + jobSrchDTO.getJobGeo());
			jobSrchJson.put("Job Geo", jobSrchDTO.getJobGeo());
			
			//System.out.println("@Job position===>>"
			//		+ jobSrchDTO.getJobPosition());
			jobSrchJson.put("JobPosition", jobSrchDTO.getJobPosition());
			
			//System.out.println("@jobGeo0LatLon===>>"
			//		+ jobSrchDTO.getJobGeo0LatLon());
			jobSrchJson.put("jobGeo0LatLon", jobSrchDTO.getJobGeo0LatLon());
			
			//System.out.println("@jobGeo1LatLon===>>"
			//		+ jobSrchDTO.getJobGeo1LatLon());
			jobSrchJson.put("jobGeo1LatLon", jobSrchDTO.getJobGeo1LatLon());
			LOGGER.info("@URL Display===>>" + jobSrchDTO.getUrlDisplay());
			jobSrchJson.put("URLDisplay", jobSrchDTO.getUrlDisplay());
			
			jsonRows.add(jobSrchJson);
			
		}
		jobSrchJsonObj.put("jsonRows", jsonRows);
		
		return jobSrchJsonObj;
	}
	

}
