package com.advanceweb.afc.jb.employer.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.jb.test.ServiceTestBase;

public class EmployerRegistrationTest extends ServiceTestBase {

	@Autowired
	private ProfileRegistration profileRegistration;

	@Test
	public void createNewProfile() {
		EmployerProfileDTO profileDto = new EmployerProfileDTO();
		UserDTO merUserDTO = new UserDTO();
		merUserDTO.setEmailId("murali@nousinfo.com");
		merUserDTO.setFirstName("murali");
		merUserDTO.setLastName("krsihna");
		merUserDTO.setMiddleName("ch");
		merUserDTO.setPassword("cmkcmkcmk1q");

		profileDto.setMerUserDTO(merUserDTO);
		merUserDTO = profileRegistration.createUser(profileDto);
		Assert.assertNotNull(merUserDTO);

	}

	/*
	 * @Test public void createEmployer(){
	 * 
	 * UserDTO userDTO = new UserDTO(); userDTO.setFirstName("Test123");
	 * userDTO.setMiddleName("Test123"); userDTO.setLastName("Test123");
	 * userDTO.setStreetAddress("Addr1"); userDTO.setZipCode("123123");
	 * userDTO.setCity("city"); userDTO.setState("TestState");
	 * userDTO.setCountry("TestCountry");
	 * userDTO.setEmailId("Test123@gmail.com"); userDTO.setPosition("Test123");
	 * userDTO.setCompany("Test123"); userDTO.setPrimaryPhone("(000) 888-9999");
	 * userDTO.setSecondaryPhone("(000) 888-9979");
	 * 
	 * nsCustomerService.createCustomer(userDTO);
	 * 
	 * }
	 */

}
