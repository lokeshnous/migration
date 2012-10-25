package com.advanceweb.common.template;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.BaseTest;

public class MvelTemplateTest  extends BaseTest{
	@Autowired AdvanceTemplate mvelTestTemplate;
	@Test
	public void processTest() {
		@SuppressWarnings("rawtypes")
		Map params = new HashMap();
		params.put("auid",297453);
		Map vars = new HashMap();
		vars.put("topic", "murali ananth ");
		params.put("vars", vars);
		System.out.println(mvelTestTemplate.process(params));
	}

}
