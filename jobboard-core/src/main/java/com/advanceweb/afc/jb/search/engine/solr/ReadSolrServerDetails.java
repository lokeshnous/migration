package com.advanceweb.afc.jb.search.engine.solr;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import com.advanceweb.afc.jb.common.MetaSearchIndexDTO;
import com.advanceweb.afc.jb.common.MetaSearchInputDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;



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
	
	private static String rowsString = "rows";
	private static String startString = "start";
	private static String sessionID = "sessionid";
	private static String queryType = "querytype";
	private static String searchSeq = "search_seq";
	
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
		solrQueryDetails.put(rowsString, solrConfiguration.getProperty(rowsString));
		solrQueryDetails.put(startString, solrConfiguration.getProperty(startString));

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
			final long start, final long rows, final List<MetaSearchInputDTO> mSInputList) {
		QueryResponse response = null;
		HttpSolrServer server = null;
		/*server = new HttpSolrServer(paramMap.get("urlString"));*/
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

		/*final String searchString = paramMap.get("keywords") + "+"
				+ paramMap.get("cityState") + "+" + paramMap.get("radius");*/
		//final String searchString = paramMap.get("searchQueryString");
		
		SolrQuery searchquery = createSOLRQuery(mSInputList, solrQueryDetails, paramMap);
		
		
		System.out.println("Search query===>>>" + searchquery);

		try {
			response = server.query(searchquery);
		} catch (SolrServerException e1) {
			LOGGER.debug(e1);
			return null;
		}
		return response;
	
	}
	
	public SolrQuery createSOLRQuery(List<MetaSearchInputDTO> mSInputList, Map<String, String> solrQueryDetails,
			Map<String, String> paramMap){
		
		String searchString = "";
		SolrQuery searchquery = null;
		
		for(MetaSearchInputDTO obj: mSInputList){
			if(obj.getInputName().toString().equalsIgnoreCase("q")){
				searchString = paramMap.get("keywords") + "+"
						+ paramMap.get("cityState") + "+" + paramMap.get("radius");
			}
		}
		
		LOGGER.info("!!!!!!!Search String=="+searchString);
		
		if (searchString != null && searchString.length() > 0) {

			searchquery = new SolrQuery();
			searchquery.setQuery(searchString);
			searchquery.setFacet(true);
			searchquery.addFacetField(solrQueryDetails.get("city"));
			searchquery.addFacetField(solrQueryDetails.get("company"));
			// searchquery.addFacetField(solrQueryDetails.get("radius"));
			searchquery.addFacetField(solrQueryDetails.get("posted_dt"));
			searchquery.addFacetField(solrQueryDetails.get("state"));
			/*searchquery.add(rowsString, String.valueOf(rows));
			searchquery.add(startString, String.valueOf(start));*/
			
			for(MetaSearchInputDTO obj: mSInputList){
				
				if(obj.getInputName().equalsIgnoreCase(rowsString)){
					searchquery.add(rowsString, paramMap.get(rowsString));
				}
				if(obj.getInputName().equalsIgnoreCase(startString)){
					searchquery.add(startString, paramMap.get(startString));
				}
				if(obj.getInputName().equalsIgnoreCase(sessionID)){
					searchquery.add("sessionid", paramMap.get(sessionID));
				}
				if(obj.getInputName().equalsIgnoreCase(queryType)){
					searchquery.add("querytype", paramMap.get(queryType));
				}
				if(obj.getInputName().equalsIgnoreCase(searchSeq)){
					searchquery.add("search_seq", paramMap.get(searchSeq));
				}
			}
		}
		return searchquery;
	}
	

	public JSONObject convertToJSON(final JobSearchResultDTO jSResultDTO) {
		final JSONObject jobSrchJsonObj = new JSONObject();
		final JSONArray jsonRows = new JSONArray();
		
		final SolrJobSearchResultDTO solrJSResultDTO = jSResultDTO.getSolrJobSearchResultDTO();
		final List<JobSearchDTO> jobSearchDTOList = solrJSResultDTO.getSearchResultList();
		
		for (JobSearchDTO jobSrchDTO : jobSearchDTOList) {
			
			final JSONObject jobSrchJson = new JSONObject();
			LOGGER.info("@Company===>>" + jobSrchDTO.getCompany());
			jobSrchJson.put("Company", jobSrchDTO.getCompany());
			LOGGER.info("@JobTitle===>>" + jobSrchDTO.getJobTitle());
			jobSrchJson.put("JobTitle", jobSrchDTO.getJobTitle());
			LOGGER.info("@City===>>" + jobSrchDTO.getCity());
			jobSrchJson.put("City", jobSrchDTO.getCity());
			LOGGER.info("@PostedDate===>>" + jobSrchDTO.getPostedDate());
			jobSrchJson.put("PostedDate", jobSrchDTO.getPostedDate());
			LOGGER.info("@Apply Online===>>"+ jobSrchDTO.getApplyOnline());
			jobSrchJson.put("ApplyOnline", jobSrchDTO.getApplyOnline());
			LOGGER.info("@Blind Ad===>>" + jobSrchDTO.getBlindAd());
			jobSrchJson.put("BlindAd", jobSrchDTO.getBlindAd());
			LOGGER.info("@Facility Name===>>"+ jobSrchDTO.getFacilityName());
			jobSrchJson.put("FacilityName", jobSrchDTO.getFacilityName());
			LOGGER.info("@Email Display===>>" + jobSrchDTO.getEmailDisplay());
			jobSrchJson.put("EmailDisplay", jobSrchDTO.getEmailDisplay());
			LOGGER.info("@Email===>>" + jobSrchDTO.getEmail());
			jobSrchJson.put("Email", jobSrchDTO.getEmail());
			
			LOGGER.info("@Is Inter===>>"+ jobSrchDTO.isInternationalJob());
			jobSrchJson.put("IsInternational", jobSrchDTO.isInternationalJob());
			LOGGER.info("@Is National===>>" + jobSrchDTO.isNationalJob());
			jobSrchJson.put("IsNational", jobSrchDTO.isNationalJob());
			LOGGER.info("@Is Featured===>>" + jobSrchDTO.isFeatured());
			jobSrchJson.put("IsFeatured", jobSrchDTO.isFeatured());
			LOGGER.info("@Job count===>>" + jobSrchDTO.getJobCount());
			jobSrchJson.put("JobCount", jobSrchDTO.getJobCount());
			LOGGER.info("@Job id===>>" + jobSrchDTO.getJobId());
			jobSrchJson.put("JobId", jobSrchDTO.getJobId());
			LOGGER.info("@Job Number===>>" + jobSrchDTO.getJobNumber());
			jobSrchJson.put("Job Number", jobSrchDTO.getJobNumber());
			LOGGER.info("@Job Geo===>>" + jobSrchDTO.getJobGeo());
			jobSrchJson.put("Job Geo", jobSrchDTO.getJobGeo());
			LOGGER.info("@Job position===>>"+ jobSrchDTO.getJobPosition());
			jobSrchJson.put("JobPosition", jobSrchDTO.getJobPosition());
			LOGGER.info("@jobGeo0LatLon===>>"+ jobSrchDTO.getJobGeo0LatLon());
			jobSrchJson.put("jobGeo0LatLon", jobSrchDTO.getJobGeo0LatLon());
			LOGGER.info("@jobGeo1LatLon===>>"+ jobSrchDTO.getJobGeo1LatLon());
			jobSrchJson.put("jobGeo1LatLon", jobSrchDTO.getJobGeo1LatLon());
			LOGGER.info("@URL Display===>>" + jobSrchDTO.getUrlDisplay());
			jobSrchJson.put("URLDisplay", jobSrchDTO.getUrlDisplay());
			
			jsonRows.add(jobSrchJson);
			
		}
		jobSrchJsonObj.put("jsonRows", jsonRows);
		
		return jobSrchJsonObj;
	}
	
	public boolean isServerAccessible(String url){
		
		boolean serverAccessible = false;
		
		try {
			final HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
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
		
		return serverAccessible;
		
	}
	
	public Map<String, StringBuffer> getSearchQuery(List<MetaSearchInputDTO> mSInputList, List<MetaSearchIndexDTO> srchIndexList, final Map<String, String> paramMap){
		
		Map<String, StringBuffer> urlParamMap = new HashMap<String, StringBuffer>();
		
		StringBuffer urlStr = new StringBuffer();
		for(MetaSearchIndexDTO obj: srchIndexList){
			urlStr.append(obj.getSearchHost());
			urlStr.append(MMJBCommonConstants.SLASH);
			urlStr.append(obj.getSearchIndexGroup());
			urlStr.append(MMJBCommonConstants.SLASH);
			urlStr.append(obj.getSearchIndexName());
			//urlStr.append(MMJBCommonConstants.SLASH_SELECT_SLASH);
		}
		
		LOGGER.info("URL Str===>>"+urlStr);
		
		/*StringBuffer paramStr = new StringBuffer();
		Map<String,String> paramInputMap = new HashMap<String, String>();
		for(MetaSearchInputDTO obj: mSInputList){
			LOGGER.info("@@@@@@@@@@@@@@"+obj.getInputName().toString().equalsIgnoreCase("q"));
			
			if(obj.getInputName().toString().equalsIgnoreCase("q")){
				
				//paramStr.append(MMJBCommonConstants.EQUAL_TO);
				paramStr.append(obj.getInputValue());
				
			}else{
				paramStr.append(MMJBCommonConstants.AMP);
				paramStr.append(obj.getInputName());
				paramStr.append(MMJBCommonConstants.EQUAL_TO);
				paramStr.append(obj.getInputValue());
			}
			
		}
		
		int index = paramStr.indexOf(":b01");
		paramStr.replace(index, index + ":b01".length(), paramMap.get("keywords"));
		
		index = paramStr.indexOf(":b02");
		paramStr.replace(index, index + ":b02".length(), paramMap.get(rowsString));
		
		index = paramStr.indexOf(":b03");
		paramStr.replace(index, index + ":b03".length(), paramMap.get(startString));
		
		index = paramStr.indexOf(":b04");
		paramStr.replace(index, index + ":b04".length(), paramMap.get("sessionId"));
		
		index = paramStr.indexOf(":b05");
		paramStr.replace(index, index + ":b05".length(), paramMap.get("searchName"));
		
		index = paramStr.indexOf(":b06");
		paramStr.replace(index, index + ":b06".length(), paramMap.get("searchSequence"));
		
		LOGGER.info("Param Str===="+paramStr);*/
		
		urlParamMap.put("urlString", urlStr);
		//urlParamMap.put("paramString", paramStr);
		
		return urlParamMap;
		
	}
	
	

}
