package com.advanceweb.afc.jb.advt.service.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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

@Component
public class LegacyAdServiceDelegate implements AdServiceDelegate {
	private static final Logger LOGGER = Logger
			.getLogger(LegacyAdServiceDelegate.class);
	@Autowired
	@Resource(name = "adsConfiguration")
	private Properties adsConfiguration;

	@Override
	public Banner getBanner(ClientContext context, AdSize size,
			AdPosition position) {
		Banner banner = new Banner();
		boolean status = true;
		String tag = null;
		if (size != null) {
			banner.setSize(new AdSize(size));
		}
		try {
			if (position == AdPosition.TOP) {
				URL adUrl = getAdURL(context, size, position);
				tag = getResponse(adUrl);
			} else {
				status = false;
			}
			LOGGER.debug("Recieved ad-tag" + tag);
		} catch (MalformedURLException ex) {
			status = false;
			LOGGER.error(ex.getMessage(), ex);
		} catch (IOException ex) {
			status = false;
			LOGGER.error(ex.getMessage(), ex);
		}catch (Exception ex) {
			status = false;
			LOGGER.error(ex.getMessage(), ex);
		}
		if(!status){
			tag = "<p>Ad Not Available for " + size.toString() +"</p>";
		}
		banner.setTag(tag);
		return banner;
	}

	private URL getAdURL(ClientContext context, AdSize size, AdPosition position)
			throws MalformedURLException {
		StringBuffer sbUrl = new StringBuffer();
		sbUrl.append(adsConfiguration.getProperty("ads.server.url"));

		// Create the TAG
		String tag = "AD_FREQUENCY_GROUP:ADFREQUENCYGROUP_AFC_LEADERBOARD;WEB_AD_TYPE_GROUP:WEBADTYPEGROUP_LEADERBOARD;PUBLICATION:PUB_AC";
		sbUrl.append("?TAG=").append(tag);

		// Add other options
		addParam(sbUrl, "userAgent",
				context.getProperty(ClientContext.CLIENT_USER_AGENT));
		addParam(sbUrl, "userId", context.getProperty(ClientContext.USER_ID));
		addParam(sbUrl, "clientIp",
				context.getProperty(ClientContext.CLIENT_IP));
		addParam(sbUrl, "absoluteUri",
				context.getProperty(ClientContext.CLIENT_REQUEST_URL));
		addParam(sbUrl, "sessionId",
				context.getProperty(ClientContext.CLIENT_SESSIONID));
		addParam(sbUrl, "referralUrl",
				context.getProperty(ClientContext.CLIENT_REFERRER));

		URL url = new URL(sbUrl.toString());

		LOGGER.info("Advertisement URL = " + url.toExternalForm());
		LOGGER.info("Advertisement size = " + size);
		LOGGER.info("Advertisement Position = " + position);
		return url;
	}

	private void addParam(StringBuffer sb, String param, String value) {
		if (value != null) {
			sb.append('&');
			sb.append(param);
			sb.append('=');
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException ex) {
				LOGGER.error(ex);
				// For lack of other options, just value it as it is
				sb.append(value);
			}
		}
	}

	private String getResponse(URL url) throws IOException {
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
