package com.advanceweb.afc.jb.advt.legacy;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.advt.legacy.LegacyAdServiceDelegate;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
public class LegacyAdServiceDelegateTest {
	private static final Logger LOGGER = Logger.getLogger(LegacyAdServiceDelegateTest.class); 
	@Autowired
	private LegacyAdServiceDelegate delegate;
	
	@Test
	public void testGetBanner() {
		String bannerString = delegate.getBanner(new ClientContext(), AdSize.IAB_MEDIUM_RECTANGLE,
				AdPosition.TOP).getTag();
		LOGGER.info(bannerString);
		assertNotNull("Banner String for Ad", bannerString);
		//System.out.println(bannerString);
		// fail("Not yet implemented");
	}

}
