package com.advanceweb.afc.jb.web.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class ReadSolrServerDetails {
	
	
	
	public Map<String, String> getServerDetails(Properties solrConfiguration){
		Map<String, String> serverDetailsMap = new HashMap<String, String>();
		//System.out.println("@@@@@@@@@@@@@@@@@serverUrl===="+solrConfiguration.getProperty("url"));
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
	

}
