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

	private static final Logger LOGGER = Logger
			.getLogger(SOLRSearchHelper.class);

	private static final String SO_TIMEOUT = "sotimeout";
	private static final String CONNECTION_TIMEOUT = "connectiontimeout";
	private static final String MAX_CONNECTION_HOST = "maxconnectionperhost";
	private static final String MAX_TOTAL_CONNECTION = "maxtotalconnection";
	private static final String FOLLOW_REDIRECTS = "followredirects";
	private static final String ALLOW_COMPRESSION = "allowcompression";
	private static final String MAX_RETRIES = "maxretries";

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
			LOGGER.info("Server URL " + url + " is not accessible.");
		} catch (final IOException e) {
			LOGGER.debug(e);
			serverAccessible = false;
			LOGGER.info("Server URL " + url + " is not accessible.");
		}

		return serverAccessible;

	}
}
