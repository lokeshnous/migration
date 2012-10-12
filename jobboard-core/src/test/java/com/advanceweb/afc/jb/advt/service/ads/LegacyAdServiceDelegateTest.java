package com.advanceweb.afc.jb.advt.service.ads;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.common.client.ClientContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:JbCoreSpringBeans.xml", "classpath:HibernateSessionFactory.xml"})
@Transactional
public class LegacyAdServiceDelegateTest {

	@Test
	public void testGetBanner() {
		LegacyAdServiceDelegate delegate = new LegacyAdServiceDelegate();
		String bannerString = delegate.getBanner(new ClientContext(), null,
				null).getTag();
		System.out.println(bannerString);
		// fail("Not yet implemented");
	}

}
