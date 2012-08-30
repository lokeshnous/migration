package com.advanceweb.afc.jb.employer.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

public class EmployerRegistrationTest extends ServiceTest{
	
	@Autowired
	private ProfileRegistration profileRegistration;
	
	@Test
	public void createNewProfile(){
		EmployerProfileDTO profileDto = new EmployerProfileDTO();
		MerUserDTO merUserDTO = new MerUserDTO();
		merUserDTO.setEmailId("murali@nousinfo.com");
		merUserDTO.setFirstName("murali");
		merUserDTO.setLastName("krsihna");
		merUserDTO.setMiddleName("ch");
		merUserDTO.setPassword("cmkcmkcmk1q");
		
		profileDto.setMerUserDTO(merUserDTO);
		merUserDTO = profileRegistration.createNewProfile(profileDto);
		Assert.assertNotNull(merUserDTO);
		
	}
	
}
