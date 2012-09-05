package com.advanceweb.afc.jb.jobseeker.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class JobSeekerRegistrationTest  extends ServiceTest{
	
	@Autowired
	private ProfileRegistration profileRegistration;
	
	@Test
	public void createNewProfile(){
		JobSeekerRegistrationDTO jsDTO = new JobSeekerRegistrationDTO();
		UserDTO merUserDTO = new UserDTO();
		merUserDTO.setEmailId("sasibhushanam@nousinfo.com");
		merUserDTO.setFirstName("SasiBhushana");
		merUserDTO.setLastName("Matcha");
		merUserDTO.setMiddleName("");
		merUserDTO.setPassword("Sasibhushan");

		jsDTO.setMerUserDTO(merUserDTO);
		merUserDTO = profileRegistration.createEmployerProfile(jsDTO);
		Assert.assertNotNull(merUserDTO);
	}
	
	@Ignore("Not ready to test")
	@Test
	public void getJobSeekerDetails(){

		JobSeekerRegistrationDTO dto = (JobSeekerRegistrationDTO) profileRegistration.viewProfile(4);
		UserDTO merDTO = dto.getMerUserDTO();
		Assert.assertEquals("sasibhushanam@nousinfo.com", merDTO.getEmailId());
		Assert.assertEquals("SasiBhushana", merDTO.getFirstName());
		Assert.assertEquals("Matcha", merDTO.getLastName());
		Assert.assertEquals("Sasibhushan", merDTO.getPassword());
		Assert.assertEquals(4, merDTO.getUserId());
		Assert.assertEquals(null, merDTO.getMiddleName());
		
	}
	
	
	@Test
	public void updateJobSeekerDetails(){
		JobSeekerRegistrationDTO jsDTO = new JobSeekerRegistrationDTO();
		UserDTO merUserDTO = new UserDTO();
		merUserDTO.setEmailId("sasibhushanam@nousinfo.com");
		merUserDTO.setFirstName("SasiBhushana");
		merUserDTO.setLastName("Matcha");
		merUserDTO.setMiddleName("");
		merUserDTO.setPassword("Sasibhushan");
		merUserDTO.setUserId(30);

		jsDTO.setMerUserDTO(merUserDTO);
		boolean bSaved = profileRegistration.modifyProfile(jsDTO);
		Assert.assertTrue("Data Saved Successfully", bSaved);
	}
	
}
