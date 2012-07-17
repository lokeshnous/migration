package com.advanceweb.afc.jb.search.engine.solr;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Repository;

@Repository("readSolrServerDetails")
public class ReadSolrServerDetails {
	
	/**
	 * Reads Solr Server details from the property file and put it into the Map
	 * @param solrConfiguration
	 * @return Map
	 */
	public Map<String, String> getServerDetails(Properties solrConfiguration){
		Map<String, String> serverDetailsMap = new HashMap<String, String>();
		serverDetailsMap.put("serverUrl",solrConfiguration.getProperty("url") );
		serverDetailsMap.put("solrservice",solrConfiguration.getProperty("solrservice") );
		serverDetailsMap.put("user",solrConfiguration.getProperty("user") );
		serverDetailsMap.put("sotimeout", solrConfiguration.getProperty("sotimeout"));
		serverDetailsMap.put("connectiontimeout", solrConfiguration.getProperty("connectiontimeout"));
		serverDetailsMap.put("maxconnectionperhost", solrConfiguration.getProperty("maxconnectionperhost"));
		serverDetailsMap.put("maxtotalconnection", solrConfiguration.getProperty("maxtotalconnection"));
		serverDetailsMap.put("followredirects", solrConfiguration.getProperty("followredirects"));
		serverDetailsMap.put("allowcompression", solrConfiguration.getProperty("allowcompression"));
		serverDetailsMap.put("maxretries", solrConfiguration.getProperty("maxretries"));
		return serverDetailsMap;
	}
	
	/**
	 * Reads Solr query details from the property file and put it into a Map
	 * @param solrConfiguration
	 * @return Map
	 */
	public Map<String, String> getSolrQueryDetails(Properties solrConfiguration){
		Map<String, String> solrQueryDetails = new HashMap<String, String>();
		solrQueryDetails.put("city", solrConfiguration.getProperty("city"));
		solrQueryDetails.put("company", solrConfiguration.getProperty("company"));
		solrQueryDetails.put("radius", solrConfiguration.getProperty("radius"));
		solrQueryDetails.put("posted_dt", solrConfiguration.getProperty("posted_dt"));
		solrQueryDetails.put("state", solrConfiguration.getProperty("state"));
		solrQueryDetails.put("rows", solrConfiguration.getProperty("rows"));
		solrQueryDetails.put("start", solrConfiguration.getProperty("start"));
		
		return solrQueryDetails;
	}
	

}
