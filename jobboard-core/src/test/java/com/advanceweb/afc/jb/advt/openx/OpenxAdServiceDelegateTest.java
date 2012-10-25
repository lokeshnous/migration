package com.advanceweb.afc.jb.advt.openx;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.jb.test.ServiceTest;

public class OpenxAdServiceDelegateTest extends ServiceTest {

	@Autowired
	OpenxAdServiceDelegate openxAdServiceDelegate;

	@Test
	public void test() {
		Banner banner = openxAdServiceDelegate.getBanner(null,
				AdSize.IAB_LEADERBOARD, null);
		System.out.println(banner.getTag());
	}

}
