package com.advanceweb.afc.jb.advt.openx;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;
import com.advanceweb.jb.test.ServiceTestBase;

public class OpenxAdServiceDelegateTest extends ServiceTestBase {
	private static final Logger LOGGER = Logger
			.getLogger(OpenxAdServiceDelegateTest.class);

	private static final String ERROR_MESSAGE = "Ad tag for %s did not return auid %s";

	@Autowired
	private OpenxAdServiceDelegate openxAdServiceDelegate;

	private AdSize[] adSizes = { AdSize.IAB_LEADERBOARD,
			AdSize.IAB_MEDIUM_RECTANGLE
	/* , new AdSize(120, 90) */};
	private String[] auids = { "284880", "323620"/* , "309613" */};

	@Test
	public void testGetBanner() {
		for (int i = 0; i < adSizes.length; i++) {
			AdSize adSize = adSizes[i];
			String auid = auids[i];
			Banner banner = openxAdServiceDelegate.getBanner(getTestContext(),
					adSize, null);
			String tag = banner.getTag();
			LOGGER.debug(tag);
			assertTrue(String.format(ERROR_MESSAGE, adSize, auid),
					tag.indexOf(auid) >= 0);
		}
	}

	private ClientContext getTestContext() {
		ClientContext ctx = new ClientContext();
		ctx.setProperty(ClientContext.USER_CURRENT_SEARCH, "Nursing");
		ctx.setProperty(ClientContext.CLIENT_LOCATION, "Irving, TX");
		return ctx;
	}
}
