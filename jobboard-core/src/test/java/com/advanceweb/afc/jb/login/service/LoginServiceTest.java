package com.advanceweb.afc.jb.login.service;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.jb.test.ServiceTestBase;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 24th July 2012
 */

public class LoginServiceTest extends ServiceTestBase {

	private static final Logger LOGGER = Logger
			.getLogger(LoginServiceTest.class);

	@Autowired
	private LoginService loginService;

	/**
	 * JUnit test case for login Form page
	 */
	@Test
	public void testValidateLogin() {
		try {
			String emailAddress = "manish@yahoo.com";
			String password = "deo";
			assertNotNull("Login Failed",loginService.validateLoginFormValues(emailAddress,
					password));

		} catch (Exception e) {
			LOGGER.error(e);

		}
	}

	/**
	 * JUnit test case for forgot password functionality
	 */
	@Test
	public void testEmailThePassword() {
		try {
			String emailAddress = "manish@yahoo.com";
			assertNotNull("Forgot password failed",loginService.getUserEmailDetails(emailAddress));
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

}
