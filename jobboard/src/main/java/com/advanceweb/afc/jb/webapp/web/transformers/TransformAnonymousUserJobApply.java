package com.advanceweb.afc.jb.webapp.web.transformers;

import java.util.ArrayList;
import java.util.List;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;
import com.advanceweb.afc.jb.webapp.web.forms.jobapply.AnonymousUserJobApplyForm;
import com.advanceweb.afc.jb.webapp.web.forms.resume.CertificationsForm;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a Converter from Form Bean to DTO or from DTO to Form Bean
 */
public class TransformAnonymousUserJobApply {

public AnonymousUserJobApplyDTO transformAnonymousUserJobApplyDTO(AnonymousUserJobApplyForm anoUserForm){
		
		
                AnonymousUserJobApplyDTO dto = new AnonymousUserJobApplyDTO();
/*				dto.setUserName(anoUserForm.getUserName());
				dto.setUserEmail(anoUserForm.getEmail());
				dto.setFileContent(anoUserForm.getFileContent());
*/				

		return dto;
		
	}
	
}
