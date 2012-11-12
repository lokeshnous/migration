package com.advanceweb.jb.test;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * The ServiceTestBase is the base class for all the test for Jobboard Service
 * layer. This class is created to setup the spring applicationcontext for
 * running the junit tests The applicationcotnext for the test is defined in
 * /src/test/resources/applicationContext-test.xml file
 * 
 * The @Ingnore annotation will prevent Junit from looking for any test within
 * the class. The absence of the annotation will cause JUnit tests to fail
 * reporting an error <tt>No runnable methods</tt> while running the tests.
 * 
 * @author sukeshnambiar
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
@Ignore
public class ServiceTestBase {

}
