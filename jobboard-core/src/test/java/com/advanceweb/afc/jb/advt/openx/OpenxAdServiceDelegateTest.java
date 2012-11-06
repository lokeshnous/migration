package com.advanceweb.afc.jb.advt.openx;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;
import com.advanceweb.jb.test.ServiceTest;

public class OpenxAdServiceDelegateTest extends ServiceTest {
	private static final Logger LOGGER = Logger
			.getLogger(OpenxAdServiceDelegateTest.class);

	private static final String ERROR_MESSAGE = "Ad tag for %s did not return auid %s";

	@Autowired
	private OpenxAdServiceDelegate openxAdServiceDelegate;

	AdSize[] adSizes = { AdSize.IAB_LEADERBOARD, AdSize.IAB_MEDIUM_RECTANGLE,
			new AdSize(120, 90) };
	String[] auids = { "284880", "284879", "309613" };

	@Test
	public void testGetBanner() {
		for (int i = 0; i < adSizes.length; i++) {
			AdSize adSize = adSizes[i];
			String auid = auids[i];
			Banner banner = openxAdServiceDelegate.getBanner(
					new ClientContext(), adSize, null);
			String tag = banner.getTag();
			LOGGER.debug(tag);
			assertTrue(String.format(ERROR_MESSAGE, adSize, auid),
					tag.indexOf(auid) >= 0);
		}
	}
}
