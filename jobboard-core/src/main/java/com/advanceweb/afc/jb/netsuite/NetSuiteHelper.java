package com.advanceweb.afc.jb.netsuite;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.netsuite.service.impl.NSCustomerServiceImpl;


@Repository("netSuiteHelper")
public class NetSuiteHelper {

	private static final Logger LOGGER = Logger
			.getLogger(NSCustomerServiceImpl.class);
	
	private static final String PROPERTIES_FILE_NAME = "netSuite.properties";
	
	/**
	 * 
	 * @return
	 */
	
	public Properties getNSProperties() {
		Properties entries = null;
		try {
			entries = PropertiesLoaderUtils.loadAllProperties(PROPERTIES_FILE_NAME);
		} catch (IOException e) {
			LOGGER.info("ERROR in NetSuiteHelper", e);
		}
		return entries;
	}

	
	
	
	

}
