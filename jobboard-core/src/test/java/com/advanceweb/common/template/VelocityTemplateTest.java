package com.advanceweb.common.template;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.ServiceTestBase;

public class VelocityTemplateTest extends ServiceTestBase {
	private static final Logger LOGGER = Logger
			.getLogger(VelocityTemplateTest.class);
	private static final String ERROR_MSG = "The parameter %s is not replaced with %s in the velocity template";
	@Autowired
	private AdvanceTemplate velocityTestTemplate;

	@Test
	public void test() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("auid", 297453);
		params.put("url", "http://ox-d.advanceweb.com");
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("topic", "nurse");
		params.put("vars", vars);
		String result = velocityTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "topic", "nurse"),
				result.indexOf("nurse") >= 0);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
	}

}
