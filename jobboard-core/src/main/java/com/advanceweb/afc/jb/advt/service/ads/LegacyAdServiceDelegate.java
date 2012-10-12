package com.advanceweb.afc.jb.advt.service.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.advt.service.impl.AdServiceDelegate;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;

public class LegacyAdServiceDelegate implements AdServiceDelegate {
	private static final Logger LOGGER = Logger
			.getLogger(LegacyAdServiceDelegate.class);
	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties config;

	@Override
	public Banner getBanner(ClientContext context, AdSize size, String location) {
		Banner banner = new Banner();
		if (size != null) {
			banner.setSize(new AdSize(size));
		}
		String connectString = getAdURL(context, size, location);
		try {
			banner.setTag(getResponse(connectString));
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return banner;
	}

	private String getAdURL(ClientContext context, AdSize size, String location) {
		return ("http://ads.advanceweb.com/FetchAd.ashx?TAG=AD_FREQUENCY_GROUP:ADFREQUENCYGROUP_AFC_LEADERBOARD;WEB_AD_TYPE_GROUP:WEBADTYPEGROUP_LEADERBOARD;PUBLICATION:PUB_AC");
	}

	private String getResponse(String connectString) throws IOException {
		URL url = new URL(connectString);
		InputStream is = url.openStream();

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// Read the data and write to outstream
		int chr = is.read();
		while (chr != -1) {
			os.write(chr);
			chr = is.read();
		}

		// Close the streams
		is.close();
		os.close();

		return os.toString();
	}
}
