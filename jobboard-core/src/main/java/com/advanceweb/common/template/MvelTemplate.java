/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.template;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mvel2.templates.TemplateRuntime;

/**
 * MvelTemplate implements AdvanceTemplate using the Mvel template engine. The
 * template file is passed as a constructor parameter. This is done to make it
 * usable under an IOC and non IOC containers equally well. The filename of the
 * template is stored locally and not the content of the file itself. For
 * performance reasons, this may be modified to store the template String
 * locally, avoiding the need to do disk i/o for every request.
 * 
 * @author sukeshnambiar
 * 
 */
public class MvelTemplate implements AdvanceTemplate {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MvelTemplate.class);

	/** The template name. */
	private String templateName;

	/**
	 * Instantiates a new mvel template.
	 *
	 * @param templateName the template name
	 */
	public MvelTemplate(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * Uses a mvel engine to merge the template text with the parameters
	 * passed in the map
	 * 
	 * @param params
	 *            Map containing the parameters to be replaced at the
	 *            placeholders.
	 * @return The merged string
	 */
	@Override
	public String process(@SuppressWarnings("rawtypes") Map params) {
		InputStream is = MvelTemplate.class.getResourceAsStream(templateName);
		String result = (String) TemplateRuntime.eval(is, params);
		try {
			is.close();
		} catch (IOException ex) {
			LOGGER.debug("Error processing template " + templateName, ex);
		}
		return result;
	}
}
