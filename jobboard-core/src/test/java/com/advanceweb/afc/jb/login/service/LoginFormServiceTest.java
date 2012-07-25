package com.advanceweb.afc.jb.login.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.LoginFormDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 24th July 2012
 */

public class LoginFormServiceTest extends ServiceTest {

	@Autowired
	private LoginFormService loginFormService;
	
	@Test
	public void validateLogin(){
		
		String emailAddress = "manish@yahoo.com";
		String password = "deo";
		
		LoginFormDTO loginFormDTOForUser = (LoginFormDTO) loginFormService
				.validateLoginFormValues(emailAddress, password);
	}
	
}
