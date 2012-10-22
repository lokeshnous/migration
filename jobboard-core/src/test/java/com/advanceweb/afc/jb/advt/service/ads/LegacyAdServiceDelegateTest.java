package com.advanceweb.afc.jb.advt.service.ads;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:JbCoreSpringBeans.xml", "classpath:HibernateSessionFactory.xml"})
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
public class LegacyAdServiceDelegateTest {
	@Autowired
	private LegacyAdServiceDelegate delegate;
	
	@Ignore("Not ready for test")
	@Test
	public void testGetBanner() {
		String bannerString = delegate.getBanner(new ClientContext(), AdSize.IAB_MEDIUM_RECTANGLE,
				AdPosition.TOP).getTag();
		assertNotNull("Banner String for Ad", bannerString);
		//System.out.println(bannerString);
		// fail("Not yet implemented");
	}

}
