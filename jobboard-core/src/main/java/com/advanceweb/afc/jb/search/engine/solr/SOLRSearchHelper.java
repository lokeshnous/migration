package com.advanceweb.afc.jb.search.engine.solr;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * This class has been created as a helper class for the Solr related Job search
 * functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 10 July 2012
 */

@Repository("sOLRSearchHelper")
public class SOLRSearchHelper {

	private static final Logger LOGGER = Logger
			.getLogger("SOLRSearchHelper.class");

	/**
	 * Reads Solr Server details from the property file and put it into the Map
	 * @param solrConfiguration
	 * @return Map
	 */
	public Map<String, String> getServerDetails(
			final Properties solrConfiguration) {
		final Map<String, String> serverDetailsMap = new HashMap<String, String>();
		serverDetailsMap.put(MMJBCommonConstants.SO_TIMEOUT,
				solrConfiguration.getProperty(MMJBCommonConstants.SO_TIMEOUT));
		serverDetailsMap.put(MMJBCommonConstants.CONNECTION_TIMEOUT,
				solrConfiguration.getProperty(MMJBCommonConstants.CONNECTION_TIMEOUT));
		serverDetailsMap.put(MMJBCommonConstants.MAX_CONNECTION_HOST,
				solrConfiguration.getProperty(MMJBCommonConstants.MAX_CONNECTION_HOST));
		serverDetailsMap.put(MMJBCommonConstants.MAX_TOTAL_CONNECTION,
				solrConfiguration.getProperty(MMJBCommonConstants.MAX_TOTAL_CONNECTION));
		serverDetailsMap.put(MMJBCommonConstants.FOLLOW_REDIRECTS,
				solrConfiguration.getProperty(MMJBCommonConstants.FOLLOW_REDIRECTS));
		serverDetailsMap.put(MMJBCommonConstants.ALLOW_COMPRESSION,
				solrConfiguration.getProperty(MMJBCommonConstants.ALLOW_COMPRESSION));
		serverDetailsMap.put(MMJBCommonConstants.MAX_RETRIES,
				solrConfiguration.getProperty(MMJBCommonConstants.MAX_RETRIES));
		return serverDetailsMap;
	}

	/**
	 * This method will check whether the SOLR server url is accessible or not
	 * @param url
	 * @return boolean
	 */

	public boolean isServerAccessible(String url) {

		boolean serverAccessible = false;

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
			LOGGER.debug("Server URL " + url + " is not accessible.");
		} catch (final IOException e) {
			LOGGER.debug(e);
			serverAccessible = false;
			LOGGER.debug("Server URL " + url + " is not accessible.");
		}

		return serverAccessible;

	}


}
