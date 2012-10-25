package com.advanceweb.common.template;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mvel2.templates.TemplateRuntime;

public class MvelTemplate implements AdvanceTemplate {
	private static final Logger LOGGER = Logger.getLogger(MvelTemplate.class);

	private String templateName;

	public MvelTemplate(String templateName) {
		this.templateName = templateName;
	}

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
