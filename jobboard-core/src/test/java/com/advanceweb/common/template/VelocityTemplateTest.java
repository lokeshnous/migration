package com.advanceweb.common.template;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.ServiceTestBase;

/**
 * Test case for the Velocity template. The test uses a template file similar to
 * the one used for ad tags. This file covers pretty much what we need to use
 * the template for.
 * 
 * @author sukeshnambiar
 * 
 */
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

		String result;

		// Check the template without var parameters
		result = velocityTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
		assertFalse(
				"A var parameter is added in the tag even though no variables are passed",
				result.indexOf("vars") >= 0);

		assertFalse(
				"A tid parameter is added in the tag even though no variables are passed",
				result.indexOf("tid") >= 0);

		// Check with var parameter keyword
		Map<String, Object> vars = new HashMap<String, Object>();
		params.put("vars", vars);

		vars.put("keyword", "nurse");
		result = velocityTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
		assertTrue(String.format(ERROR_MSG, "keyword", "nurse"),
				result.indexOf("nurse") >= 0);

		// Check with state variable
		vars.put("state", "new york");

		result = velocityTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
		assertTrue(String.format(ERROR_MSG, "keyword", "nurse"),
				result.indexOf("\"nurse\"") >= 0);
		assertTrue(String.format(ERROR_MSG, "state", "new york"),
				result.indexOf("\"state\":\"new york\"") >= 0);

		// Check with state variable
		vars.put("city", "Puerto Rico");
		result = velocityTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
		assertTrue(String.format(ERROR_MSG, "keyword", "nurse"),
				result.indexOf("\"nurse\"") >= 0);
		assertTrue(String.format(ERROR_MSG, "state", "new york"),
				result.indexOf("\"state\":\"new york\"") >= 0);
		assertTrue(String.format(ERROR_MSG, "state", "new york"),
				result.indexOf("\"city\":\"Puerto Rico\"") >= 0);

		// Remove the state variable
		vars.remove("state");
		result = velocityTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
		assertTrue(String.format(ERROR_MSG, "keyword", "nurse"),
				result.indexOf("\"nurse\"") >= 0);
		assertFalse(
				"State variable is added even though it is not specified in the variables",
				result.indexOf("\"state\":\"new york\"") >= 0);
		assertTrue(String.format(ERROR_MSG, "state", "new york"),
				result.indexOf("\"city\":\"Puerto Rico\"") >= 0);

		params.put("tid", 30);
		result = velocityTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
		assertTrue(String.format(ERROR_MSG, "keyword", "nurse"),
				result.indexOf("\"nurse\"") >= 0);
		assertFalse(
				"State variable is added even though it is not specified in the variables",
				result.indexOf("\"state\":\"new york\"") >= 0);
		assertTrue(String.format(ERROR_MSG, "state", "new york"),
				result.indexOf("\"city\":\"Puerto Rico\"") >= 0);
		assertTrue(String.format(ERROR_MSG, "tid", "30"),
				result.indexOf("\"tid\":\"30\"") > 0);

	}
}
