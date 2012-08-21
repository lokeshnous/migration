package com.advanceweb.afc.jb.jobseeker.web.controller;

import com.advanceweb.afc.jb.common.JobApplicationDTO;
import com.advanceweb.afc.jb.job.web.controller.JobApplicationForm;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a Converter from Form Bean to DTO or from DTO to Form Bean
 */
public class TransformJobApplication {

public JobApplicationDTO transformAnonymousUserJobApplyDTO(final JobApplicationForm anoUserForm){
		
		
                JobApplicationDTO dto = new JobApplicationDTO();
                //TODO:currently not using
/*				dto.setUserName(anoUserForm.getUserName());
				dto.setUserEmail(anoUserForm.getEmail());
				dto.setFileContent(anoUserForm.getFileContent());
*/				
		return dto;
		
	}
	
}
