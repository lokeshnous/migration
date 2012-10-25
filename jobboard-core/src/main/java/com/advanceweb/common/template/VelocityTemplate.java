package com.advanceweb.common.template;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;

public class VelocityTemplate implements AdvanceTemplate {

	@Autowired
	private VelocityEngine velocityEngine;

	private String templateString;

	public VelocityTemplate(String templateFile) {
		templateString = templateFile;
	}

	@Override
	public String process(@SuppressWarnings("rawtypes") Map params) {
		if (templateString == null) {
			return null;
		} else {
			return transform(templateString, params);
		}
	}

	private String transform(String template,
			@SuppressWarnings("rawtypes") Map params) {

		VelocityContext vc = new VelocityContext(params);
		StringWriter writer = new StringWriter();

		// Use a template
		Template templ = velocityEngine.getTemplate(template);
		templ.merge(vc, writer);

		return writer.toString();
	}

}
