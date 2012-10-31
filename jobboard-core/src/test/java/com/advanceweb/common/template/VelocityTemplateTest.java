package com.advanceweb.common.template;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.ServiceTest;

public class VelocityTemplateTest extends ServiceTest {
	@Autowired
	AdvanceTemplate velocityTestTemplate = new VelocityTemplate(
			"/templates/openx_ad_tag.vtl");

	@Test
	public void test() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("auid", 297453);
		params.put("url", "http://ox-d.advanceweb.com");
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("topic", "nurse");
		params.put("vars", vars);
	//	System.out.println(velocityTestTemplate.process(params));
	}

}
