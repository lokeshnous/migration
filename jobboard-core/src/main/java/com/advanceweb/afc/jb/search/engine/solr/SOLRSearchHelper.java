/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.engine.solr;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class has been created as a helper class for the Solr related Job search
 * functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 10 July 2012
 */

@Component("solrSearchHelper")
public class SOLRSearchHelper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(SOLRSearchHelper.class);

	/** The Constant SO_TIMEOUT. */
	private static final String SO_TIMEOUT = "sotimeout";
	
	/** The Constant CONNECTION_TIMEOUT. */
	private static final String CONNECTION_TIMEOUT = "connectiontimeout";
	
	/** The Constant MAX_CONNECTION_HOST. */
	private static final String MAX_CONNECTION_HOST = "maxconnectionperhost";
	
	/** The Constant MAX_TOTAL_CONNECTION. */
	private static final String MAX_TOTAL_CONNECTION = "maxtotalconnection";
	
	/** The Constant FOLLOW_REDIRECTS. */
	private static final String FOLLOW_REDIRECTS = "followredirects";
	
	/** The Constant ALLOW_COMPRESSION. */
	private static final String ALLOW_COMPRESSION = "allowcompression";
	
	/** The Constant MAX_RETRIES. */
	private static final String MAX_RETRIES = "maxretries";

	/** The solr configuration. */
	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties solrConfiguration;

	/**
	 * This method creates a HttpSolrServer instance by setting all the required
	 * server parameters.
	 * 
	 * @param queryDTO
	 *            represents all the SOLR server parameter values from the DB.
	 * @param serverDetailsMap
	 *            contains all the server details being red from the property
	 *            file.
	 * @return instance of HttpSolrServer
	 */

	public HttpSolrServer getSolrServerInstance(String baseURL) {

		HttpSolrServer server = new HttpSolrServer(baseURL);

		server.setSoTimeout(Integer.parseInt(solrConfiguration
				.getProperty(SO_TIMEOUT)));
		server.setConnectionTimeout(Integer.parseInt(solrConfiguration
				.getProperty(CONNECTION_TIMEOUT)));
		server.setDefaultMaxConnectionsPerHost(Integer
				.parseInt(solrConfiguration.getProperty(MAX_CONNECTION_HOST)));
		server.setMaxTotalConnections(Integer.parseInt(solrConfiguration
				.getProperty(MAX_TOTAL_CONNECTION)));
		server.setFollowRedirects(Boolean.parseBoolean(solrConfiguration
				.getProperty(FOLLOW_REDIRECTS)));
		// defaults to false
		server.setAllowCompression(Boolean.parseBoolean(solrConfiguration
				.getProperty(ALLOW_COMPRESSION)));
		server.setMaxRetries(Integer.parseInt(solrConfiguration
				.getProperty(MAX_RETRIES)));
		server.setParser(new XMLResponseParser());
		
		return server;

	}

	/**
	 * This method will check whether the SOLR server url is accessible or not
	 * 
	 * @param url
	 *            represents the SOLR server url
	 * @return boolean represents whether server is accessible or not by
	 *         returning true or false.
	 */

	public boolean isServerAccessible(String url) {

		boolean serverAccessible = false;

		try {

			String testURL = url + "/select";
			LOGGER.debug("Server URL To Check is " + testURL);

			final HttpURLConnection connection = (HttpURLConnection) new URL(
					testURL).openConnection();
			connection.connect();

			if (connection.getResponseCode() == 200) {
				serverAccessible = true;
				LOGGER.debug("Server URL " + url + " is accessible.");
			}
		} catch (final MalformedURLException e) {
			serverAccessible = false;
			LOGGER.error("Server URL " + url + " is not accessible.",e);
		} catch (final IOException e) {
			serverAccessible = false;
			LOGGER.error("Server URL " + url + " is not accessible.",e);
		}

		return serverAccessible;

	}
}
