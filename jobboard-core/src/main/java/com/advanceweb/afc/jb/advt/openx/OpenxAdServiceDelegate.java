package com.advanceweb.afc.jb.advt.openx;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.advt.service.impl.AdServiceDelegate;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;
import com.advanceweb.common.template.AdvanceTemplate;

@Component
public class OpenxAdServiceDelegate implements AdServiceDelegate {

	private static final Logger LOGGER = Logger
			.getLogger(OpenxAdServiceDelegate.class);

	@Resource(name = "openxConfiguration")
	@Autowired
	Properties openxProperties;

	@Autowired
	private AdvanceTemplate adTagTemplate;
	
	@Autowired
	private AdvanceTemplate defaultAdTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Banner getBanner(ClientContext context, AdSize size,
			AdPosition position) {
		Banner banner = new Banner();
		if (size != null) {
			banner.setSize(new AdSize(size));
		}

		LOGGER.debug(("Received request from context " + context.toString() +" for banner size " + size.toString()));

		Map params = new HashMap();
		params.put("url", openxProperties.getProperty("openx.url"));
		params.put("size", size);

		String auid = openxProperties.getProperty(getAuidKey(context, size));
		if (auid == null || auid.isEmpty()) {
			// Set the default banner
			banner.setTag(defaultAdTemplate.process(params));
		} else {
			params.put("auid", auid);
			banner.setTag(adTagTemplate.process(params));
		}
		LOGGER.debug("Received ad tag " + banner.getTag());

		return banner;
	}

	private String getAuidKey(ClientContext context, AdSize size) {
		return String.format("openx.auid.%sx%s", size.getWidth(),
				size.getHeight());
	}
}
