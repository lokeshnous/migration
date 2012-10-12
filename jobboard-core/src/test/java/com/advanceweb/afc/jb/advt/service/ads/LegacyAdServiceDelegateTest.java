package com.advanceweb.afc.jb.advt.service.ads;

import static org.junit.Assert.*;

import org.junit.Test;

import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

public class LegacyAdServiceDelegateTest {

	@Test
	public void testGetBanner() {
		LegacyAdServiceDelegate delegate = new LegacyAdServiceDelegate();
		String bannerString = delegate.getBanner(null, null, null).getTag();
		System.out.println(bannerString);
		// fail("Not yet implemented");
	}

}
