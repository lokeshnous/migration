package com.advanceweb.afc.jb.login.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 24th July 2012
 */

public class LoginServiceTest extends ServiceTest {

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

			loginService.validateLoginFormValues(emailAddress, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * JUnit test case for forgot password functionality
	 */
	@Test
	public void testEmailThePassword() {
		try {
			String emailAddress = "manish@yahoo.com";
			loginService.getUserEmailDetails(emailAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}