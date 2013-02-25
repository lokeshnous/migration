/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.template;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.EscapeTool;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The VelocityTemplate implements AdvanceTemplate using the apache Velocity
 * template engine. The template file is passed as a constructor parameter. This
 * is done to make it usable under an IOC and non IOC containers equally well.
 * The filename of the template is stored locally and not the content of the
 * file itself. For performance reasons, this may be modified to store the
 * template String locally, avoiding the need to do disk i/o for every request.
 * 
 * @author sukeshnambiar
 * 
 */
public class VelocityTemplate implements AdvanceTemplate {

	/** The velocity engine. */
	@Autowired
	private VelocityEngine velocityEngine;

	/** The template file. */
	private String templateFile;

	/**
	 * Instantiates a new velocity template.
	 *
	 * @param templateFile the template file
	 */
	public VelocityTemplate(String templateFile) {
		this.templateFile = templateFile;
	}

	/**
	 * Uses a velocity engine to merge the template text with the parameters
	 * passed in the map
	 * 
	 * @param params
	 *            Map containing the parameters to be replaced at the
	 *            Placeholder.
	 * @return The merged string
	 */
	@Override
	public String process(Map<String, Object> params) {
		VelocityContext context = new VelocityContext(params);
		
		// Add the escape VelocityTool
		context.put("esc", new EscapeTool());

		// Use a template
		Template templ = velocityEngine.getTemplate(templateFile);

		// Merge the template
		StringWriter writer = new StringWriter();
		templ.merge(context, writer);

		return writer.toString();
	}

}
