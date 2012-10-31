package com.advanceweb.common.template;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.ServiceTest;

public class MvelTemplateTest extends ServiceTest {
	@Autowired
	AdvanceTemplate mvelTestTemplate;

	@Test
	public void processTest() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("auid", 297453);
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("topic", "murali ananth ");
		params.put("vars", vars);
	//	System.out.println(mvelTestTemplate.process(params));
	}

}
