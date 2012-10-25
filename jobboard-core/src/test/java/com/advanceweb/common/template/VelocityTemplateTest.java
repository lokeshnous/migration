package com.advanceweb.common.template;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.BaseTest;

public class VelocityTemplateTest extends BaseTest{
	@Autowired
	AdvanceTemplate velocityTestTemplate = new VelocityTemplate(
			"/templates/openx_ad_tag.vtl");

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void test() {
		
		Map params = new HashMap();
		params.put("auid", 297453);
		params.put("url", "http://ox-d.advanceweb.com");
		Map vars = new HashMap();
		vars.put("topic", "nurse");
		params.put("vars", vars);
		System.out.println(velocityTestTemplate.process(params));
	}

}
