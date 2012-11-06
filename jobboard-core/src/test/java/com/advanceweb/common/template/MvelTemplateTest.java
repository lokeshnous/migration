package com.advanceweb.common.template;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.ServiceTest;

public class MvelTemplateTest extends ServiceTest {
	private static final Logger LOGGER = Logger
			.getLogger(MvelTemplateTest.class);
	private static final String ERROR_MSG = "The parameter %s is not replaced with %s in the mvel template";
	@Autowired
	private AdvanceTemplate mvelTestTemplate;

	@Test
	public void processTest() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("auid", 297453);
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("topic", "nursing");
		params.put("vars", vars);
		String result = mvelTestTemplate.process(params);
		LOGGER.debug(result);
		assertTrue(String.format(ERROR_MSG, "topic", "nursing"),
				result.indexOf("nursing") >= 0);
		assertTrue(String.format(ERROR_MSG, "auid", "297453"),
				result.indexOf("297453") >= 0);
	}
}
