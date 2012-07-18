package com.advanceweb.afc.jb;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:resources/HibernateSessionFactory.xml" })
public class ServiceTest extends AbstractJUnit4SpringContextTests {

	@BeforeClass
	public static void setup() {

	}
	
	@Test
	public void testMethod(){
		
	}

}
