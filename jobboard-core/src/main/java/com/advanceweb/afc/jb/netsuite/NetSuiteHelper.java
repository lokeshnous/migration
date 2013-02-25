/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.netsuite;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.netsuite.service.impl.NSCustomerServiceImpl;


@Repository("netSuiteHelper")
public class NetSuiteHelper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(NSCustomerServiceImpl.class);
	
	/** The Constant PROPERTIES_FILE_NAME. */
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
			LOGGER.error("ERROR in NetSuiteHelper", e);
		}
		return entries;
	}

	
	
	
	

}
